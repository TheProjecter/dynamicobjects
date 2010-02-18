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

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorPart;

public class RefreshViewerAction extends Action {
	
	private EditingDomainActionBarContributor actionBarContributor;
	
	public RefreshViewerAction(EditingDomainActionBarContributor actionBarContributor) {
		super(NO2EditorPlugin.INSTANCE.getString("_UI_RefreshViewer_menu_item"));
		this.actionBarContributor = actionBarContributor;
	}
	
	@Override
	public boolean isEnabled() {
		return actionBarContributor.getActiveEditor() instanceof IViewerProvider;
	}

	@Override
	public void run() {
		IEditorPart activeEditorPart = actionBarContributor.getActiveEditor();
		if (activeEditorPart instanceof IViewerProvider) {
			Viewer viewer = ((IViewerProvider) activeEditorPart).getViewer();
			if (viewer != null) {
				viewer.refresh();
			}
		}
	}
	

}
