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

package net.zehrer.no2.ui.dialogs;

import net.zehrer.no2.ui.provider.NoColumnLabelProvider;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractEMFDialog extends TitleAreaDialog {

	protected static final Color GRAY = getDisplay().getSystemColor(SWT.COLOR_GRAY);

	protected IWorkbenchPage page;
	// protected String title;

	protected TableViewer viewer;

	public AbstractEMFDialog(IWorkbenchPage page) {
		super(new Shell(page.getWorkbenchWindow().getShell()));
		this.page = page;
		setShellStyle(getShellStyle() | SWT.APPLICATION_MODAL | SWT.MAX | SWT.TITLE | SWT.RESIZE);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);

		this.viewer = new TableViewer(composite, SWT.NONE);

		// setup table
		Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(false);
		table.setLayoutData(createGridData());

		return composite;
	}

	protected TableViewerColumn addNoColumn(String columnName, TableViewer viewer) {
		TableViewerColumn column = addColumn(viewer, columnName, 20);
		column.setLabelProvider(new NoColumnLabelProvider(viewer.getTable()));
		return column;
	}

	protected TableViewerColumn addColumn(TableViewer viewer, String title, int width) {

		TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
		column.getColumn().setText(title);
		column.getColumn().setWidth(width);
		return column;
	}

	protected void refreshViewer() {
		page.getWorkbenchWindow().getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				try {
					viewer.refresh();
				} catch (RuntimeException ignore) {
				}
			}
		});
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.CLOSE_ID, IDialogConstants.CLOSE_LABEL, false);
	}

	@Override
	protected void buttonPressed(int buttonId) {
		switch (buttonId) {

		case IDialogConstants.CLOSE_ID:
			close();
			break;
		}
	}

	// @Override
	// protected void configureShell(Shell newShell) {
	// super.configureShell(newShell);
	// newShell.setText(title);
	// }

	/**
	 * Original source for org.eclipse.net4j.util.ui.UIUtil
	 */
	public static Display getDisplay() {
		Display display = Display.getCurrent();
		if (display == null) {
			try {
				display = PlatformUI.getWorkbench().getDisplay();
			} catch (RuntimeException ignore) {
			}
		}

		if (display == null) {
			display = Display.getDefault();
		}

		if (display == null) {
			display = new Display();
		}

		return display;
	}

	/**
	 * Original source for org.eclipse.net4j.util.ui.UIUtil
	 */
	public static GridData createGridData() {
		return new GridData(SWT.FILL, SWT.FILL, true, true);
	}
}
