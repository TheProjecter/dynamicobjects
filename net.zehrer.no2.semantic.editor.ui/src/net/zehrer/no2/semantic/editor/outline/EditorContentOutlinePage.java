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

import net.zehrer.no2.semantic.editor.SemanticEditor;
import net.zehrer.no2.semantic.editor.model.AbstractNode;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

public class EditorContentOutlinePage extends ContentOutlinePage {

	private SemanticEditor fTextEditor;
	private IDocumentProvider fDocumentProvider;
	
	private OutlineContentProvider fContentProvider;
	
	private IEditorInput fInput;

	public EditorContentOutlinePage(IDocumentProvider documentProvider, SemanticEditor semanticEditor) {
		super();
		this.fDocumentProvider = documentProvider;
		this.fTextEditor = semanticEditor;
	}

	public void createControl(Composite parent) {

		super.createControl(parent);
		TreeViewer viewer = getTreeViewer();
		
		this.fContentProvider = new OutlineContentProvider();

		viewer.setContentProvider(this.fContentProvider);
		viewer.setLabelProvider(new OutlineLabelProvider());
		
		viewer.addSelectionChangedListener(this);

		viewer.setInput(getDocument());
	}

	
	public void selectionChanged(SelectionChangedEvent event) {

		super.selectionChanged(event);

		ISelection selection= event.getSelection();
		if (selection.isEmpty()) {
			fTextEditor.resetHighlightRange();
		}
		else {
			AbstractNode node = (AbstractNode) ((IStructuredSelection) selection).getFirstElement();

			try {
				fTextEditor.setHighlightRange(node.getOffset(), node.getLength(), true);
			} catch (IllegalArgumentException x) {
				fTextEditor.resetHighlightRange();
			}
		}
	}

	public void setInput(IEditorInput editorInput) {
		this.fInput = editorInput;
		update();
	}
	
	protected Object getDocument() {
		if (fInput != null) {
			return fDocumentProvider.getDocument(fInput);
		}
		
		return null;
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
				viewer.setInput(getDocument());
				viewer.expandAll();
				control.setRedraw(true);
			}
		}
	}
}
