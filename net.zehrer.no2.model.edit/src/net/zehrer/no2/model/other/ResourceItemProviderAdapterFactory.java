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

package net.zehrer.no2.model.other;

import net.zehrer.no2.model.provider.ResourceItemProvider;

import org.eclipse.emf.common.notify.Adapter;

public class ResourceItemProviderAdapterFactory extends org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory {

	public ResourceItemProviderAdapterFactory() {
		super();
	}

	/**
	 * This creates an adapter for a
	 * {@link org.eclipse.emf.ecore.resource.Resource}.
	 * 
	 * @generated NOT   // BUG missing override in template
	 */
	@Override
	public Adapter createResourceAdapter() {
		return new ResourceItemProvider(this);
	}

}
