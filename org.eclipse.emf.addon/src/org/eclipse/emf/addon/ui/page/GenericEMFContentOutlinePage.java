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

package org.eclipse.emf.addon.ui.page;

import org.eclipse.emf.addon.editor.IEditor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.widgets.Composite;

public class GenericEMFContentOutlinePage extends GenericContentOutlinePage implements ISelectionChangedListener {
	
	protected ComposedAdapterFactory adapterFactory;

	public GenericEMFContentOutlinePage(IEditor editor) {
		super(editor);
		
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);		
	}

	@Override
	public void createControl(Composite parent) {

		setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		super.createControl(parent);		
	}

	public void addAdapterFactory (AdapterFactory adapterFactory) {
		this.adapterFactory.addAdapterFactory(adapterFactory);
	}
	

}
