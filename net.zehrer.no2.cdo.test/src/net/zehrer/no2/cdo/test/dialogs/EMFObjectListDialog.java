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

package net.zehrer.no2.cdo.test.dialogs;

import net.zehrer.no2.ui.dialogs.AbstractEMFDialog;
import net.zehrer.no2.ui.provider.AttributeLabelProvider;

import org.eclipse.emf.cdo.internal.ui.SharedIcons;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPage;

public class EMFObjectListDialog extends AbstractEMFDialog {

	EList<EObject> input;

	public EMFObjectListDialog(IWorkbenchPage page, EList<EObject> input) {
		super(page);
		this.input = input;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		
		setTitle("Hello");
//		
//		setTitle(CDOItemProvider.getSessionLabel(session));
		setTitleImage(SharedIcons.getImage(SharedIcons.WIZBAN_PACKAGE_MANAGER));

		// configure columns
//		addNoColumn("No.",viewer);
		
		EClass type = null;
		
		if (input.size() > 0)
		  type = input.get(0).eClass();
		
		if (type != null)
			generateTableColumns(viewer,type);
		
		viewer.setContentProvider(new EListContentProvider());
		viewer.setInput(this.input);

		return composite;
	}


	protected void generateTableColumns(TableViewer viewer, EClass type) {

		TableViewerColumn column = null;
		for (EAttribute attribute : type.getEAllAttributes()) {
			column = addColumn(viewer, attribute.getName(), 100);
			column.setLabelProvider(new AttributeLabelProvider(attribute));
		}
	}
	
	public static class EListContentProvider implements IStructuredContentProvider {

		public EListContentProvider() {
		}

		public void dispose() {
		}

		@Override
		public Object[] getElements(Object inputElement) {			
			return ((EList<EObject>) inputElement).toArray();
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			
		}
	}
	
}
