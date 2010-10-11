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

package net.zehrer.no2.model.util;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class EClassResource {

	protected EClass type;
	protected URI uri;
	protected Resource resource;

	public EClassResource(Map.Entry<EClass, String> entry, ResourceSet resourceSet ) {
		this.type = entry.getKey();
		this.uri = URI.createURI(entry.getValue());
		this.resource = resourceSet.getResource(this.uri, true);

	}
	
	public Resource getResource() {
		return this.resource;
	}
	
	public EClass getType() {
		return this.type;
	}
}
