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

package net.zehrer.no2.action;

import net.zehrer.no2.NO2EditorPlugin;

import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.PartInitException;

public class ShowPropertiesViewerAction extends Action {
	
	private EditingDomainActionBarContributor actionBarContributor;
	
	public ShowPropertiesViewerAction(EditingDomainActionBarContributor actionBarContributor) {
		super(NO2EditorPlugin.INSTANCE.getString("_UI_ShowPropertiesView_menu_item"));
		this.actionBarContributor = actionBarContributor;
	}
	
	@Override
	public void run() {
		try {
			actionBarContributor.getPage().showView("org.eclipse.ui.views.PropertySheet");
		} catch (PartInitException exception) {
			NO2EditorPlugin.INSTANCE.log(exception);
		}
	}
	

}
