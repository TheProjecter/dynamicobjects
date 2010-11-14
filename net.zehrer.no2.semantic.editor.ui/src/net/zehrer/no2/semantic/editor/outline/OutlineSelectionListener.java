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

package net.zehrer.no2.semantic.editor.outline;

import net.zehrer.no2.semantic.editor.model.AbstractNode;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;

public class OutlineSelectionListener implements ISelectionListener {
	
	private ITextEditor fTextEditor;

	public OutlineSelectionListener(ITextEditor fTextEditor) {
		this.fTextEditor = fTextEditor;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {

		if (selection.isEmpty()) {
			fTextEditor.resetHighlightRange();
		}

		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			Object o = ss.getFirstElement();
			if (o instanceof AbstractNode) {
				AbstractNode node = (AbstractNode) o;

				try {
					fTextEditor.setHighlightRange(node.getOffset(), node.getLength(), true);
				} catch (IllegalArgumentException x) {
					fTextEditor.resetHighlightRange();
				}
			}
		}

	}

}
