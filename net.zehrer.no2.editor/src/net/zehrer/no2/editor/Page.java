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

import net.zehrer.no2.NO2EditorPlugin;
import net.zehrer.no2.model.util.EClassResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;


/**
 *  This page class covers several things: 
 *  - generate the page content, in this case a table
 *  - act as adapter of the corresponding EClass (TODO) for
 *		- update ...
 */
public class Page extends AdapterImpl {
	
	protected Composite container;  
	protected ComposedAdapterFactory modelAdapterFactory;
	
	protected EClassResource classResource;
	
	/**
	 * This shows how a table view works. A table can be used as a list with
	 * icons.
	 */
	protected TableViewer tableViewer;  // created by createPageControl
	

	public Page( Composite container, ComposedAdapterFactory modelAdapterFactory,  EClassResource classResource ) {
		this.container = container;
		this.modelAdapterFactory = modelAdapterFactory;
		this.classResource = classResource;
	}
	
	public Control createPageControl() {

		tableViewer = new TableViewer(this.container);
		Table table = tableViewer.getTable();
		TableLayout layout = new TableLayout();
		table.setLayout(layout);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		generateTableColumns(table,layout);
		tableViewer.setColumnProperties(new String[] { "a", "b" });  // What's that?
	
		tableViewer.setContentProvider(new AdapterFactoryContentProvider(modelAdapterFactory));
		tableViewer.setLabelProvider(new AdapterFactoryLabelProvider(modelAdapterFactory));
		
		tableViewer.setInput(classResource.getResource());
		
		// add this page as adapter for the class to :
		// - name change -> TODO: how to handle from here?
		// - attribute change -> TODO :)
		// - something else?
		classResource.getType().eAdapters().add(this);
		
		return table;
	}
	
	protected void generateTableColumns (Table table, TableLayout layout ) {
		
		EClass eClass = this.classResource.getType();
		
		for (EAttribute attribute : eClass.getEAllAttributes()) {
			TableColumn objectColumn = new TableColumn(table, SWT.NONE);
			layout.addColumnData(new ColumnWeightData(3, 30, true));
			objectColumn.setText(attribute.getName());
			//objectColumn.setResizable(true);  <- already set in constructor -> Why again?
		}
			
//		
//		TableColumn objectColumn = new TableColumn(table, SWT.NONE);
//		layout.addColumnData(new ColumnWeightData(3, 100, true));
//		objectColumn.setText(getString("_UI_ObjectColumn_label"));
//		objectColumn.setResizable(true);

//		TableColumn selfColumn = new TableColumn(table, SWT.NONE);
//		layout.addColumnData(new ColumnWeightData(2, 100, true));
//		selfColumn.setText(getString("_UI_SelfColumn_label"));
//		selfColumn.setResizable(true);
	}
	
	
	public String getPageName() {
		return this.classResource.getType().getName();  //TODO use an other name 
	}

	public TableViewer getTableView() {
		return this.tableViewer;
	}
		
	//TODO: NLS
	private static String getString(String key) {
		return NO2EditorPlugin.INSTANCE.getString(key);
	}
	
	// ---- Adapter ----
	
	@Override
	public void notifyChanged(Notification notification) {
		
		// if nothing changed ... do nothing
		//if (notification.isTouch()) return;
	}
	
}
