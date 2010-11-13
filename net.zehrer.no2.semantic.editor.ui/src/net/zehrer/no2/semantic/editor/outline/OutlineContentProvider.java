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
import net.zehrer.no2.semantic.editor.model.CompositeNode;
import net.zehrer.no2.text.IResourceDocument;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class OutlineContentProvider implements ITreeContentProvider {
	
	private CompositeNode rootNode;
	
	public OutlineContentProvider () {

	}
	
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
		if (newInput != null) {
			IResourceDocument  document = (IResourceDocument) newInput;
			rootNode = (CompositeNode) document.getTextModel();
		}
		
	}

	/*
	 * @see IContentProvider#dispose
	 */
	@Override
	public void dispose() {
		if (rootNode != null) {
			rootNode = null;
		}
	}
	
	/*
	 * @see IStructuredContentProvider#getElements(Object)
	 */
	public Object[] getElements(Object element) {
		Object[] result = new Object[] { rootNode };
		return result;
	}

	/*
	 * @see ITreeContentProvider#hasChildren(Object)
	 */
	public boolean hasChildren(Object element) {
		return element instanceof CompositeNode;
	}

	/*
	 * @see ITreeContentProvider#getParent(Object)
	 */
	public Object getParent(Object element) {
		return null;
	}

	/*
	 * @see ITreeContentProvider#getChildren(Object)
	 */
	public Object[] getChildren(Object element) {
		if (element instanceof CompositeNode) {
			return getChildern( (CompositeNode) element);
		}

		return new Object[0];
	}

	public Object[] getChildern (CompositeNode node) {
		EList<AbstractNode> children = node.getChildren();
		return children.toArray();
	}

}
