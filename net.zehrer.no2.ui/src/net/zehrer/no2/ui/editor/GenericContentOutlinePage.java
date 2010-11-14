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

import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

public class GenericContentOutlinePage extends ContentOutlinePage {
	
	protected Object outlineInput;
	
	protected IStatusLineManager contentOutlineStatusLineManager;

	protected IEditor editor;
	protected IContentProvider contentProvider;
	protected ILabelProvider labelProvider;
	
	
	public GenericContentOutlinePage(IEditor editor) {
		this.editor = editor;
		
	}

	@Override
	public void createControl(Composite parent) {
		
		super.createControl(parent);
 		 
		// Set up the tree viewer.
		TreeViewer viewer = getTreeViewer();
		viewer.setContentProvider(this.contentProvider);
		viewer.setLabelProvider(this.labelProvider);
		
		viewer.setInput(getInput());	
				
		// Make sure our popups work.
		editor.createContextMenuFor(viewer);
		
		// Handle selections 
		// see http://wiki.eclipse.org/FAQ_How_do_I_make_a_view_respond_to_selection_changes_in_another_view%3F
		getSite().setSelectionProvider(viewer);

		// TODO: set the selection ?
//		Resource firstModel = findFirstResource(resourceSet, "ecore");  // official ecore extension?
//		if (firstModel != null)
//			viewer.setSelection(new StructuredSelection(firstModel), true);
		
	}
	
	@Override
	public void makeContributions(IMenuManager menuManager, IToolBarManager toolBarManager, IStatusLineManager statusLineManager) {
		super.makeContributions(menuManager, toolBarManager, statusLineManager);
		this.contentOutlineStatusLineManager = statusLineManager;
	}
	
	@Override
	public void setActionBars(IActionBars actionBars) {
		super.setActionBars(actionBars);
		
		EditingDomainActionBarContributor actionBarContributor = editor.getActionBarContributor();
		if ( actionBarContributor != null ) {
			actionBarContributor.shareGlobalActions(this, actionBars);
		}
	}
	

	public void setContentProvider(IContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}
	
	public void setLabelProvider(ILabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}
	
	public void setInput(Object outlineInput) {
		this.outlineInput = outlineInput;
		update();
	}

	protected Object getInput() {
		return this.outlineInput;	
	}
	
	/**
	 * Updates the outline page.
	 */
	public void update() {
		TreeViewer viewer = getTreeViewer();

		if (viewer != null) {
			Control control= viewer.getControl();
			if (control != null && !control.isDisposed()) {
				control.setRedraw(false);
				viewer.setInput(getInput());
				viewer.expandAll();
				control.setRedraw(true);
			}
		}
	}
	

}
