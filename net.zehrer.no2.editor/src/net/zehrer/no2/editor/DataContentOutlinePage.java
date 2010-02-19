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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

// The content outline is just a tree.
//
public class DataContentOutlinePage extends ContentOutlinePage implements ISelectionChangedListener {

	protected AdapterFactory adapterFactory;
	protected ModelEditor modelEditor;

	protected IStatusLineManager contentOutlineStatusLineManager;
	

	public DataContentOutlinePage(AdapterFactory adapterFactory, ModelEditor modelEditor) {

		this.adapterFactory = adapterFactory;
		this.modelEditor = modelEditor;
		
	}

	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		
		TreeViewer viewer = getTreeViewer();

		// Set up the tree viewer.
		viewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		viewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		viewer.setInput(modelEditor.getMetaModelResource().getEObject("/"));		
		//viewer.setInput(modelEditor.getEditorInput());  // ORIGINAL form the generator

		// Make sure our popups work.
		modelEditor.createContextMenuFor(viewer);

		// TODO: set the selection ?
//		Resource firstModel = findFirstResource(resourceSet, "ecore");  // official ecore extension?
//		if (firstModel != null)
//			viewer.setSelection(new StructuredSelection(firstModel), true);
		
		getSite().setSelectionProvider(viewer);
		
	}

	@Override
	public void makeContributions(IMenuManager menuManager, IToolBarManager toolBarManager, IStatusLineManager statusLineManager) {
		super.makeContributions(menuManager, toolBarManager, statusLineManager);
		this.contentOutlineStatusLineManager = statusLineManager;
	}

	@Override
	public void setActionBars(IActionBars actionBars) {
		super.setActionBars(actionBars);
		
		modelEditor.getActionBarContributor().shareGlobalActions(this, actionBars);
	}
	
}
