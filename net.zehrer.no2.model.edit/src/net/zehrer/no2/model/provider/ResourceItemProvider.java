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

package net.zehrer.no2.model.provider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

public class ResourceItemProvider extends ItemProviderAdapter implements  ITreeItemContentProvider, IItemLabelProvider, IEditingDomainItemProvider {

	public ResourceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);

	}

	/**
	 * This returns the label text for the adapted class.
	 */
	@Override
	public String getText(Object object) {
		Resource resource = (Resource) object;
		return resource.getURI() == null ? "" : resource.getURI().lastSegment();
	}

}
