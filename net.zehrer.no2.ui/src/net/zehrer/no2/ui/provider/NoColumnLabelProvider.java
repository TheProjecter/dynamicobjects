/*******************************************************************************
 * Copyright (c) 2010 Stephan Zehrer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephan Zehrer - initial API and implementation
 *     Source: http://dev.eclipse.org/newslists/news.eclipse.platform.rcp/msg20173.html
 *******************************************************************************/

package net.zehrer.no2.ui.provider;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class NoColumnLabelProvider extends ColumnLabelProvider {
	
	private Table table = null;
	private Color color = null;
	
	public NoColumnLabelProvider (Table table) {
		this.table = table;
	}

	public NoColumnLabelProvider (Table table, Color color) {
		this.table = table;
		this.color = color;
	}
	
	@Override
    public void update(ViewerCell cell) {
        cell.setText(table.indexOf((TableItem)cell.getItem()) + 1 +"");
        cell.setBackground(this.color);
    }
	
//	@Override
//	public Color getBackground(Object element) {
//		return this.color;
//	}

}
