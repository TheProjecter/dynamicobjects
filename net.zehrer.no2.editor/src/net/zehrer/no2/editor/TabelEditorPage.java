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

package net.zehrer.no2.editor;

import net.zehrer.no2.model.util.EClassResource;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.Page;

/**
 * This page class covers several things: 
 * - generate the page content, in this case a table 
 * - act as adapter of the corresponding EClass (TODO) for - update
 * ...
 * 
 */
public class TabelEditorPage extends Page implements IPage, ISelectionChangedListener, ISelectionProvider {

	private ListenerList selectionChangedListeners = new ListenerList();

	protected TableViewer tableViewer; // created by createPageControl

	protected Composite container;
	protected ModelEditor modelEditor;
	protected AdapterFactory adapterFactory;

	protected EClassResource classResource;

	public TabelEditorPage(ModelEditor modelEditor, AdapterFactory adapterFactory, EClassResource classResource) {
		this.modelEditor = modelEditor;
		this.adapterFactory = adapterFactory;
		this.classResource = classResource;
	}

	/**
	 * @category Page
	 */
	@Override
	public void createControl(Composite parent) {

		tableViewer = new TableViewer(parent);

		Table table = tableViewer.getTable();
		TableLayout layout = new TableLayout();
		table.setLayout(layout);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		generateTableColumns(table, layout);
		tableViewer.setColumnProperties(new String[] { "a", "b" }); // What's
																	// that?

		tableViewer.setContentProvider(new AdapterFactoryContentProvider(this.adapterFactory));
		tableViewer.setLabelProvider(new AdapterFactoryLabelProvider(this.adapterFactory));

		tableViewer.setInput(classResource.getResource());

		// Make sure our popups work.
		modelEditor.createContextMenuFor(tableViewer);

		// add this page as adapter for the class to :
		// - name change -> TODO: how to handle from here?
		// - attribute (name) change -> TODO :)
		// - something else?
		// classResource.getType().eAdapters().add(this); // TODO: add adapter

		// ADD the JFace Viewer as a Selection Provider to the View site.
		 modelEditor.getSite().setSelectionProvider(tableViewer);

		// return table;
	}

	/**
	 * @category Page
	 */
	@Override
	public Control getControl() {
		if (tableViewer == null) {
			return null;
		}
		return tableViewer.getControl();
	}

	/**
	 * @category Page
	 */
	@Override
	public void setFocus() {
		tableViewer.getControl().setFocus();
	}

	/**
	 * @category TabelEditorPage
	 */
	protected void generateTableColumns(Table table, TableLayout layout) {

		EClass eClass = this.classResource.getType();

		for (EAttribute attribute : eClass.getEAllAttributes()) {
			TableColumn objectColumn = new TableColumn(table, SWT.NONE);
			layout.addColumnData(new ColumnWeightData(3, 30, true));
			objectColumn.setText(attribute.getName());
			// objectColumn.setResizable(true); <- already set in constructor ->
			// Why again?
		}

	}


	/**
	 * @category TabelEditorPage
	 */
	public TableViewer getTableViewer() {
		return this.tableViewer;
	}

	/**
	 * @category ISelectionChangedListener
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		fireSelectionChanged(event.getSelection());
	}

	// ----- ISelectionProvider

	/**
	 * Fires a selection changed event.
	 * 
	 * @param selection
	 *            the new selection
	 * @category ISelectionProvider
	 */
	protected void fireSelectionChanged(ISelection selection) {

		// create an event
		final SelectionChangedEvent event = new SelectionChangedEvent(this, selection);

		// fire the event
		Object[] listeners = selectionChangedListeners.getListeners();
		for (int i = 0; i < listeners.length; ++i) {
			final ISelectionChangedListener l = (ISelectionChangedListener) listeners[i];
			SafeRunner.run(new SafeRunnable() {
				public void run() {
					l.selectionChanged(event);
				}
			});
		}
	}

	/**
	 * @category ISelectionProvider
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);
	}

	/**
	 * @category ISelectionProvider
	 */
	public ISelection getSelection() {
		if (tableViewer == null) {
			return StructuredSelection.EMPTY;
		}
		return tableViewer.getSelection();
	}

	/**
	 * @category ISelectionProvider
	 */
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	/**
	 * @category ISelectionProvider
	 */
	public void setSelection(ISelection selection) {
		if (tableViewer != null) {
			tableViewer.setSelection(selection);
		}
	}

	// ---- AdapterImpl ----
	// TODO: Now how to handel? this is now a subclase of Page

	// @Override
	// public void notifyChanged(Notification notification) {
	//		
	// // if nothing changed ... do nothing
	// //if (notification.isTouch()) return;
	// }

}
