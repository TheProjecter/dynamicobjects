/*******************************************************************************
 * Copyright (c) 2010 Stephan Zehrer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephan Zehrer - initial API and implementation
 *******************************************************************************/

package net.zehrer.no2.editor;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import net.zehrer.no2.Messages;
import net.zehrer.no2.model.NO2Model;
import net.zehrer.no2.model.adapter.NO2ModelAdapter;
import net.zehrer.no2.model.impl.NO2ModelImpl;
import net.zehrer.no2.model.util.EClassResource;
import net.zehrer.no2.util.ResourceUtil;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipselabs.emf.addon.editor.IEMFResourceEditor;
import org.eclipselabs.emf.addon.resource.AbstractResourceManager;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.ui.IEditorInput;

public class WorkspaceResourceManager extends AbstractResourceManager {
	
	final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
	
	/**
	 * TODO: add comment 
	 * TODO: add multi metaModel support.
	 */
	private NO2Model no2Model;

	private Resource no2Resource;
	private Resource metaModelResource; // TODO support several metaModel's

	private URI archiveURI;
	private URI metaModelURI;
	

	public WorkspaceResourceManager (IEMFResourceEditor editor) {
		super();
		
		setEditor(editor);

		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8"); // initialObjectCreationPage.getEncoding()
		saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE); // <---
		
	}

	
	/**
	 * (Default) operation implementation for "doSave".
	 */
	@Override
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		// Save the resources to the file system.
		//
		boolean first = true;
		for (Resource resource : getEditingDomain().getResourceSet().getResources()) {
			if ((first || !resource.getContents().isEmpty() || ResourceUtil.isPersisted(resource, getEditingDomain().getResourceSet())) && !getEditingDomain().isReadOnly(resource)) {
				try {
					long timeStamp = resource.getTimeStamp();
					resource.save(saveOptions);
					if (resource.getTimeStamp() != timeStamp) {
						savedResources.add(resource);
					}
				} catch (Exception exception) {
					this.problemIndication.put(resource, ResourceUtil.analyzeResourceProblems(resource, exception));
				}
				first = false;
			}
		}
	}
	
	
	public EClassResource getEClassResource() {
		ResourceSet resourceSet = no2Model.getResourceSet();
		return new EClassResource(no2Model.getClassResources().get(0), resourceSet);
	}
	
	/**
	 * Return the resource of the meta model.
	 * 
	 * @return
	 * @category ModelEdit
	 */
	public Resource getMetaModelResource() {
		return this.metaModelResource;
	}
	
	/**
	 * Return the absolute URI of the meta model. e.g. used to open the ECore
	 * default editor.
	 * 
	 * @return
	 * @category ModelEdit
	 */
	public URI getMetaModelURI() {
		URIConverter converter = no2Model.getResourceSet().getURIConverter();
		return converter.normalize(metaModelURI);
	}
	
	/**
	 * Get current model instance
	 * 
	 * @category ModelEdit
	 */
	public NO2Model getModel() {
		return this.no2Model;
	}
	
	public Object getOutlinePageInput() {
		return getMetaModelResource().getEObject("/");
	}

	/**
	 * This is the method called to load a resource into the editing domain's
	 * resource set based on the editor's input. 
	 * @generated NOT
	 */
	public void createModel(IEditorInput editorInput) {

		archiveURI = NO2ModelImpl.getArchiveURI(EditUIUtil.getURI(editorInput));

		Diagnostic diagnostic = null;
		Exception exception = null;

		// Get resource set and setup URI mapping
		ResourceSet resourceSet = getEditingDomain().getResourceSet();

		// configure the URI map
		resourceSet.getURIConverter().getURIMap().put(URI.createURI("/"), archiveURI);

		// ------- load NO2Model ------------

		URI no2URI = URI.createURI("/no2.xmi"); // TODO: use central name

		try {
			// Load the resource
			no2Resource = resourceSet.getResource(no2URI, true);
			no2Resource.load(null); // TODO: what is about params on load?
			no2Model = (NO2Model) no2Resource.getEObject("/");
			no2Model.setArchiveURI(archiveURI); // TODO: do internal
			no2Model.setEditingDomainProvider(getEditor());

			// function for adding new created objects into the correct resource
			no2Model.eAdapters().add(new NO2ModelAdapter());

		} catch (Exception e) {
			exception = e;
		}

		if (no2Resource != null) {
			diagnostic = ResourceUtil.analyzeResourceProblems(no2Resource, exception);
		} else {
			diagnostic = new BasicDiagnostic(Diagnostic.ERROR, "net.zehrer.no2.model.editor", 0, Messages._UI_CreateModelError_message , new Object[] { exception }); // TODO: getString("_UI_CreateModelError_message", no2URI)
		}

		if (diagnostic.getSeverity() != Diagnostic.OK) {
			problemIndication.put(no2Resource, diagnostic); // TODO check
																	// again
																	// orignial
																	// code!

			// TODO: what happens when exception occurs? What are possible
			// problems?
			// - Wrong format
			// - other error
		}

		// ------- load ECore Model (MetaModel) ------------

		metaModelURI = URI.createURI("/metamodel.ecore"); // TODO: use central
															// name

		try {
			// Load the resource through the editing domain.
			// TODO: BUG in no2Model.getResouces (cause resourceSet is not set
			// after load!!!!)
			metaModelResource = no2Model.getResource(metaModelURI);

		} catch (Exception e) {
			exception = e;
		}

		if (metaModelResource != null) {
			diagnostic = ResourceUtil.analyzeResourceProblems(metaModelResource, exception);
		} else {
			diagnostic = new BasicDiagnostic(Diagnostic.ERROR, "net.zehrer.no2.model.editor", 0, Messages._UI_CreateModelError_message,
					new Object[] { exception }); // TODO:getString("_UI_CreateModelError_message", metaModelURI)
		}
		if (diagnostic.getSeverity() != Diagnostic.OK) {
			problemIndication.put(metaModelResource, diagnostic);
		}


	}
}
