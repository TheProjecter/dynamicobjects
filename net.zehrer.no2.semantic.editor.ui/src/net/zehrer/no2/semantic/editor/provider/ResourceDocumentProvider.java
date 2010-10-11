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

package net.zehrer.no2.semantic.editor.provider;

//import net.zehrer.no2.semantic.editor.partitioner.DebugPartitioner;
//import javax.inject.Inject;  // how to solve in e4/SDK4.0?

import net.zehrer.no2.manager.NO2ResourceManager;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.editors.text.FileDocumentProvider;
import org.eclipse.ui.part.FileEditorInput;

public abstract class ResourceDocumentProvider extends FileDocumentProvider {
	
	//@Inject 
	protected EditingDomain editingDomain;

	private Resource resource;
	
	public  ResourceDocumentProvider (EditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}
	
	protected void setResource(Resource resource) {
		assert resource != null;
		this.resource = resource;
		if (this.resource.getErrors().isEmpty()) {
			EcoreUtil.resolveAll(this.resource);
		}
	}

	public Resource getResource() {
		return this.resource;
	}
	
	
	/*
	 * @see AbstractDocumentProvider#createDocument(Object)
	 */
	@Override
	protected IDocument createDocument(Object element) throws CoreException {

		if (element instanceof IFileEditorInput) {
			IDocument document = createEmptyDocument();
			
			IFileEditorInput fileInput = (IFileEditorInput) element;
			
			if (initializeResourceObject (fileInput) &&
				setDocumentContent(document, fileInput)) {
				
				setupDocument(element, document);
				return document;
			}
		}

		return null;
	}
	
	/*
	 * @see AbstractDocumentProvider#doSaveDocument(IProgressMonitor, Object, IDocument, boolean)
	 */
	@Override
	protected void doSaveDocument(IProgressMonitor monitor, Object element, IDocument document, boolean overwrite) throws CoreException {
		
		// TODO SAVE
			
	}
	

	abstract protected boolean setDocumentContent(IDocument document, IFileEditorInput editorInput) throws CoreException;

	
	// Initial source EMFText generated editor code
	protected boolean initializeResourceObject(IEditorInput editorInput) {
		IFileEditorInput input = (FileEditorInput) editorInput;
		
		IFile file = input.getFile();
		ResourceSet resourceSet = editingDomain.getResourceSet();		
		URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		Resource loadedResource = resourceSet.getResource(uri, false);
		
		//TODO
		//net.zehrer.emftext.test.resource.test.mopp.TestNature.activate(inputFile.getProject());
			
		if (loadedResource == null) {
			try {
				Resource demandLoadedResource = null;
				// here we do not use getResource(), because 'resource' might be null, which is ok
				// when initializing the resource object
				Resource currentResource = this.resource;
				if (currentResource != null && !currentResource.getURI().fileExtension().equals(uri.fileExtension())) {
					// do not attempt to load if file extension has changed in a 'save as' operation	
				}
				else {
					demandLoadedResource = resourceSet.getResource(uri, true);
				}
				
				setResource(demandLoadedResource);
				return true;

				//TODO ERROR HANDLING (e.g. check for correct model content )
				// -> close editor

			} catch (Exception e) {
				// TODO TestUIPlugin.logError("Exception while loading 
				// resource in
				//  " + this.getClass().getSimpleName() + ".", e);
				return false;
			}
		} else {
			setResource(loadedResource);
			return true;
		}
	}
	
}
