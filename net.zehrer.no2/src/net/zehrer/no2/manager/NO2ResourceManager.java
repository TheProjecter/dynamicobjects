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

package net.zehrer.no2.manager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;

public class NO2ResourceManager {
	
	static public Resource createResource (IFile file, ResourceSet resourceSet) {
		
		// create URI from file path
		URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);

	    return resourceSet.createResource(uri);
	}
	
	static public Resource createResource (IFile file) {
		return createResource(file, new ResourceSetImpl());	
	}
	
	static public void saveModel (Resource resource, IProgressMonitor monitor) {
		
		monitor.setTaskName("Saving ...");
		// Save the contents of the resource to the file system.
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(XMLResource.OPTION_ENCODING, "UTF-8"); // initialObjectCreationPage.getEncoding()
		options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE); // <--- !!!!!! TODO: test	
		
		try {
			resource.save(options);
		} catch (IOException e) {
			// TODO Handler error?
		}
		
		monitor.worked(1);
	}
	
	static public Resource getResource (IFile file, ResourceSet resourceSet) {
		
		// create URI from file path
		URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		
		return resourceSet.getResource(uri, false);
		
	}
}
