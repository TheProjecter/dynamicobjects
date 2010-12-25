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

package org.eclipse.emf.addon.editor;

import org.eclipse.emf.addon.ui.page.GenericContentPropertySheetPage;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;

public abstract class AbstractEMFEditorPart extends SelectionProviderEditorPart implements IEditingDomainProvider, IGotoMarker, IEditor {

	protected AdapterFactoryEditingDomain editingDomain;
	protected ComposedAdapterFactory adapterFactory;

	protected GenericContentPropertySheetPage contentPropertySheetPage;
	
	
	protected IPropertySheetPage getPropertySheetPage() {
		
		if (contentPropertySheetPage == null) { 
			contentPropertySheetPage = new GenericContentPropertySheetPage(editingDomain, this);
			contentPropertySheetPage.setPropertySourceProvider(new AdapterFactoryContentProvider(adapterFactory));
		}
		
		return contentPropertySheetPage;
	}
	
	/**
	 * Returns whether the outline view should be presented to the user.
	 */
	protected boolean showOutlineView() {
		return false;
	}
	
	protected abstract IContentOutlinePage getContentOutlinePage();


	// ---- WorkbenchPart
	
    /**
     * {@inheritDoc}
	 * @category WorkbenchPart
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class key) {
		if (key.equals(IContentOutlinePage.class)) {
			return showOutlineView() ? getContentOutlinePage() : null;
		}
		else if (key.equals(IPropertySheetPage.class)) {
			return getPropertySheetPage();
		}
		else if (key.equals(IGotoMarker.class)) {
			return this;
		}
		else {
			return super.getAdapter(key);
		}
	}
	
	
	// ----  IEditingDomainProvider
	
	@Override
	public EditingDomain getEditingDomain() {
		return this.editingDomain;
	}
	
}
