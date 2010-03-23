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

package net.zehrer.no2.ui.provider;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ColumnLabelProvider;

public class AttributeLabelProvider extends ColumnLabelProvider {

//	protected Table table;
	protected EAttribute attribute;

	public AttributeLabelProvider(EAttribute attribute) {
		this.attribute = attribute;
	}

	@Override
	public String getText(Object element) {
		EObject eObject = (EObject) element;

		Object value = eObject.eGet(attribute);
		if (value != null)
			return value.toString();

		return "";
	}

//	public void update(ViewerCell cell) {
//
//		EObject eObject = null;
//
//		TableItem item = (TableItem) cell.getItem();
//		cell.setText(table.indexOf((TableItem) cell.getItem()) + "");
//
//	}

}