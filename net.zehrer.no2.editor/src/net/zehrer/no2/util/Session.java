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

package net.zehrer.no2.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

public class Session extends WorkspaceModifyOperation{
	
	/**
	 * Resources that have been saved.
	 */
	protected Collection<Resource> savedResources;
	
	protected ProblemIndication problemIndication;
	
	protected AdapterFactoryEditingDomain modelEditingDomain;
	protected ResourceSet resourceSet;
	
	final Map<Object, Object> saveOptions = new HashMap<Object, Object>();

	
	public Session (AdapterFactoryEditingDomain modelEditingDomain) {
		this.modelEditingDomain = modelEditingDomain;	
		this.resourceSet = modelEditingDomain.getResourceSet();
		
		// Save only resources that have actually changed.

		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8"); // initialObjectCreationPage.getEncoding()
		saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE); // <---
																			// !!!!!!
																			// TODO:
																			// test
	}
	
	public void setSavedResources (Collection<Resource> savedResources) {
		this.savedResources = savedResources;
	}

	public void setProblemIndication (ProblemIndication problemIndication) {
		this.problemIndication = problemIndication;
	}
	
	
	@Override
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		// Save the resources to the file system.
		//
		boolean first = true;
		for (Resource resource : modelEditingDomain.getResourceSet().getResources()) {
			if ((first || !resource.getContents().isEmpty() || ResourceUtil.isPersisted(resource, this.resourceSet)) && !modelEditingDomain.isReadOnly(resource)) {
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
	

	
}
