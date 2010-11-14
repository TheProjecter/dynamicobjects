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

package net.zehrer.no2.ui.editor;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.ui.IActionBars;

public class GeneriContentPropertySheetPage extends ExtendedPropertySheetPage {
	
	protected IEditor editor;

	public GeneriContentPropertySheetPage(AdapterFactoryEditingDomain editingDomain,  IEditor editor) {
		super(editingDomain);
		
		this.editor = editor;
	}
	
	
	@Override
	public void setActionBars(IActionBars actionBars) {
		super.setActionBars(actionBars);
		
		EditingDomainActionBarContributor actionBarContributor = editor.getActionBarContributor();
		if ( actionBarContributor != null ) {
			actionBarContributor.shareGlobalActions(this, actionBars);
		}
	}

}
