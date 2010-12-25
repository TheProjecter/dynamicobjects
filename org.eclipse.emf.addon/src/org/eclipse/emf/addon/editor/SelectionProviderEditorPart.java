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

package org.eclipse.emf.addon.editor;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.part.EditorPart;

public abstract class SelectionProviderEditorPart extends EditorPart implements ISelectionProvider {
	

	public SelectionProviderEditorPart() {
		super();
	}
	
	// -------    ISelectionProvider    ------

	/**
	 * This keeps track of the selection of the editor as a whole. 
	 */
	protected ISelection editorSelection = StructuredSelection.EMPTY;

	/**
	 * This listens to which ever viewer is active. 
	 */
	protected ISelectionChangedListener selectionChangedListener;
	
	/**
	 * This keeps track of all the
	 * {@link org.eclipse.jface.viewers.ISelectionChangedListener}s that are
	 * listening to this editor. 
	 */
	protected Collection<ISelectionChangedListener> selectionChangedListeners = new ArrayList<ISelectionChangedListener>();
	
	/**
	 * This implements {@link org.eclipse.jface.viewers.ISelectionProvider}.
	 * @category ISelectionProvider
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);
	}

	/**
	 * This implements {@link org.eclipse.jface.viewers.ISelectionProvider}.
	 * @category ISelectionProvider
	 */
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	/**
	 * This implements {@link org.eclipse.jface.viewers.ISelectionProvider} to
	 * set this editor's overall selection. Calling this result will notify the
	 * listeners. 
	 * @category ISelectionProvider
	 */
	public void setSelection(ISelection selection) {
		this.editorSelection = selection;
	
		for (ISelectionChangedListener listener : selectionChangedListeners) {
			listener.selectionChanged(new SelectionChangedEvent(this, selection));
		}
	}

	/**
	 * This implements {@link org.eclipse.jface.viewers.ISelectionProvider} to
	 * return this editor's overall selection. 
	 * @category ISelectionProvider
	 */
	public ISelection getSelection() {
		return this.editorSelection;
	}
	
	


}