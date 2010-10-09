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

package net.zehrer.no2.editor;

import java.util.List;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.ui.IActionBars;

public class DataContentPropertySheetPage extends ExtendedPropertySheetPage {
	
	protected ModelEditor modelEditor;

	public DataContentPropertySheetPage(AdapterFactoryEditingDomain editingDomain, ModelEditor modelEditor) {
		super(editingDomain);

		this.modelEditor = modelEditor;
	}

	@Override
	public void setSelectionToViewer(List<?> selection) {
		modelEditor.setSelectionToViewer(selection);
		modelEditor.setFocus();
	}
	
	@Override
	public void setActionBars(IActionBars actionBars) {
		super.setActionBars(actionBars);
		modelEditor.getActionBarContributor().shareGlobalActions(this, actionBars);
	}
}
