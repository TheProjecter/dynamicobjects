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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import net.zehrer.no2.semantic.editor.SemanticEditor;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.DefaultPositionUpdater;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IPositionUpdater;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

public class DebugContentOutlinePage extends ContentOutlinePage {

	public IDocumentProvider fDocumentProvider;
	private SemanticEditor fTextEditor;
	private IEditorInput fInput;
	
	/**
	 * A segment element.
	 */
	protected static class Segment {
		public String name;
		public Position position;

		public Segment(String name, Position position) {
			this.name= name;
			this.position= position;
		}

		public String toString() {
			return name;
		}
	}

	class ContentProvider implements ITreeContentProvider {
		
		protected final static String SEGMENTS= "__segments"; //$NON-NLS-1$
		protected IPositionUpdater fPositionUpdater= new DefaultPositionUpdater(SEGMENTS);
		protected List<Segment> fContent= new ArrayList<Segment>();

		protected void parse(IDocument document) {
			
			try {
				ITypedRegion[] partitions = document.computePartitioning(0, document.getLength());
				for (int i = 0; i < partitions.length; i++) {
	
					try {
	
						int offset = partitions[i].getOffset();
						Position p= new Position(offset, partitions[i].getLength());
						document.addPosition(SEGMENTS, p);
						
						fContent.add(new Segment(MessageFormat.format("{0} {1}", new Object[] { partitions[i].getType(), new Integer(offset) }), p)); //$NON-NLS-1$
	
					} catch (BadPositionCategoryException x) {
					} catch (BadLocationException x) {
					}
				}
			} catch (BadLocationException e) {
			}	
		}
	
		
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

			
			if (newInput != null) {
				IDocument document= fDocumentProvider.getDocument(newInput);
				if (document != null) {
					document.addPositionCategory(SEGMENTS);
					document.addPositionUpdater(fPositionUpdater);
					fContent = new ArrayList<Segment>();
					parse(document);
				}
			}
		}

		/*
		 * @see IContentProvider#dispose
		 */
		@Override
		public void dispose() {
			if (fContent != null) {
				fContent.clear();
				fContent= null;
			}
		}
		
		
		/*
		 * @see IStructuredContentProvider#getElements(Object)
		 */
		public Object[] getElements(Object element) {
			return fContent.toArray();
		}

		/*
		 * @see ITreeContentProvider#hasChildren(Object)
		 */
		public boolean hasChildren(Object element) {
			return element == fInput;
		}

		/*
		 * @see ITreeContentProvider#getParent(Object)
		 */
		public Object getParent(Object element) {
			if (element instanceof Segment)
				return fInput;
			return null;
		}

		/*
		 * @see ITreeContentProvider#getChildren(Object)
		 */
		public Object[] getChildren(Object element) {
			if (element == fInput)
				return fContent.toArray();
			return new Object[0];
		}

	}

	public DebugContentOutlinePage(IDocumentProvider documentProvider, SemanticEditor semanticEditor) {
		super();
		this.fDocumentProvider = documentProvider;
		this.fTextEditor = semanticEditor;
	}

	public void createControl(Composite parent) {

		super.createControl(parent);

		TreeViewer viewer = getTreeViewer();
		viewer.setContentProvider(new ContentProvider());
		viewer.setLabelProvider(new LabelProvider());
		viewer.addSelectionChangedListener(this);

		if (fInput != null)
			viewer.setInput(fInput);
	}

	
	
	public void selectionChanged(SelectionChangedEvent event) {

		super.selectionChanged(event);

		ISelection selection= event.getSelection();
		if (selection.isEmpty())
			fTextEditor.resetHighlightRange();
		else {
			Segment segment = (Segment) ((IStructuredSelection) selection).getFirstElement();
			int start= segment.position.getOffset();
			int length= segment.position.getLength();
			try {
				fTextEditor.setHighlightRange(start, length, true);
			} catch (IllegalArgumentException x) {
				fTextEditor.resetHighlightRange();
			}
		}
	}

	public void setInput(IEditorInput editorInput) {
		this.fInput = editorInput;
		update();
	}
	
	/**
	 * Updates the outline page.
	 */
	public void update() {
		TreeViewer viewer= getTreeViewer();

		if (viewer != null) {
			Control control= viewer.getControl();
			if (control != null && !control.isDisposed()) {
				control.setRedraw(false);
				viewer.setInput(fInput);
				viewer.expandAll();
				control.setRedraw(true);
			}
		}
	}
}
