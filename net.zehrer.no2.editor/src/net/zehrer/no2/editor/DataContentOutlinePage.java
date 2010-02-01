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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.StructuredSelection;
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

		// TODO: What is input for outline?
		// Resource eResource = modelEditor.getMetaModelResource();
		// set input on root package
		// eResource.getEObject("/")

		ResourceSet resourceSet = modelEditor.getEditingDomain().getResourceSet();
		// set the outline to resource set. Resource set represent the complete
		// file content...
		viewer.setInput(resourceSet);
		
		//viewer.setInput(modelEditor.getEditorInput());  // ORIGINAL for the generator

		// Make sure our popups work.
		modelEditor.createContextMenuFor(viewer);

		// TODO: find officualECore extension
		Resource firstModel = findFirstResource(resourceSet, "ecore");
		if (firstModel != null)
			viewer.setSelection(new StructuredSelection(firstModel), true);
		
		// TODO: cleanup after testing
		//viewer.addSelectionChangedListener(this);
		// ADD the JFace Viewer as a Selection Provider to the View site.
		getSite().setSelectionProvider(viewer);
		
	}

	/**
	 * Return the first Resource in the given ResourceSet with the given extension or null if not found
	 * 
	 * @param resourceSet
	 * @param ext - the extension to search
	 * @return return either the Resource or null for not found.
	 */
	// TODO: move this methode for reuse?
	private Resource findFirstResource(ResourceSet resourceSet, String ext) {

		for (Resource resource : resourceSet.getResources()) {
			if (resource.getURI().fileExtension() == ext)
				return resource;
		}

		return null;
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
	

//	@Override
//	public void selectionChanged(SelectionChangedEvent event) {
//		super.selectionChanged(event);
//	}

}
