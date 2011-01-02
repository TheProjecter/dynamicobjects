/*******************************************************************************
 * Copyright (c) 2009 Stephan Zehrer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephan Zehrer - initial API and implementation
 *******************************************************************************/

package org.eclipselabs.emf.addon.resource;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class ResourceDeltaVisitor implements IResourceDeltaVisitor {
	

	protected ResourceSet resourceSet = null;
	
	protected Collection<Resource> changedResources = new ArrayList<Resource>();
	protected Collection<Resource> removedResources = new ArrayList<Resource>();
	
	protected Collection<Resource> savedResources = new ArrayList<Resource>();

	public ResourceDeltaVisitor (ResourceSet aResourceSet) {
		this.resourceSet = aResourceSet;
	}
	
	public void setSavedResources (Collection<Resource> aSavedResources) {
		this.savedResources = aSavedResources;
	}
	
	public boolean visit(IResourceDelta delta) {
		if (delta.getResource().getType() == IResource.FILE) {
			if (delta.getKind() == IResourceDelta.REMOVED || delta.getKind() == IResourceDelta.CHANGED && delta.getFlags() != IResourceDelta.MARKERS) {
				Resource resource = resourceSet.getResource(URI.createPlatformResourceURI(delta.getFullPath().toString(), true), false);
				if (resource != null) {
					if (delta.getKind() == IResourceDelta.REMOVED) {
						removedResources.add(resource);
					} else if (!savedResources.remove(resource)) {
						changedResources.add(resource);
					}
				}
			}
		}

		return true;
	}

	public Collection<Resource> getChangedResources() {
		return changedResources;
	}

	public Collection<Resource> getRemovedResources() {
		return removedResources;
	}

}
