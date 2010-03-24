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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import net.zehrer.no2.Messages;
import net.zehrer.no2.NO2EditorPlugin;
import net.zehrer.no2.util.ResourceUtil;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;

public class WorkspaceResourceManager implements IResourceChangeListener {
	
	protected ModelEditor editor;

	protected ProblemIndication problemIndication;
	
	
	/**
	 * Resources that have been removed since last activation.
	 */
	protected Collection<Resource> removedResources = new ArrayList<Resource>();

	/**
	 * Resources that have been changed since last activation.
	 */
	protected Collection<Resource> changedResources = new ArrayList<Resource>();

	/**
	 * Resources that have been saved since last activation.
	 */
	protected Collection<Resource> savedResources = new ArrayList<Resource>();
	
	public WorkspaceResourceManager (ModelEditor editor) {
		this.editor = editor;

	}

	public void resourceChanged(IResourceChangeEvent event) {
		IResourceDelta delta = event.getDelta();
		try {

			final ResourceDeltaVisitor visitor = new ResourceDeltaVisitor(getEditingDomain().getResourceSet());
			visitor.setSavedResources(savedResources);

			delta.accept(visitor);

			if (!visitor.getRemovedResources().isEmpty()) {
				getDisplay().asyncExec(new Runnable() {
					public void run() {
						removedResources.addAll(visitor.getRemovedResources());
						if (!isDirty()) {
							getSite().getPage().closeEditor(getEditor(), false);
						}
					}
				});
			}

			if (!visitor.getChangedResources().isEmpty()) {
				getDisplay().asyncExec(new Runnable() {
					public void run() {
						changedResources.addAll(visitor.getChangedResources());
						if (getSite().getPage().getActiveEditor() == getEditor() ) {
							handleActivate();
						}
					}
				});
			}
		} catch (CoreException exception) {
			NO2EditorPlugin.INSTANCE.log(exception);
		}
	}
		
	public void handleActivate() {
		// Recompute the read only state.
		//
		if (getEditingDomain().getResourceToReadOnlyMap() != null) {
			getEditingDomain().getResourceToReadOnlyMap().clear();

			// Refresh any actions that may become enabled or disabled.
			// TODO: understand :)
			getEditor().setSelection(getEditor().getSelection());
		}

		if (!removedResources.isEmpty()) {
			if (handleDirtyConflict()) {
				getSite().getPage().closeEditor(getEditor(), false);
			} else {
				removedResources.clear();
				changedResources.clear();
				savedResources.clear();
			}
		} else if (!changedResources.isEmpty()) {
			changedResources.removeAll(savedResources);
			handleChangedResources();
			changedResources.clear();
			savedResources.clear();
		}
	}
	
	/**
	 * Handles what to do with changed resources on activation. 
	 * 
	 * @category ModelEdit
	 */
	protected void handleChangedResources() {
		if (!changedResources.isEmpty() && (!isDirty() || handleDirtyConflict())) {
			if (isDirty()) {
				changedResources.addAll(getEditingDomain().getResourceSet().getResources());
			}
			getEditingDomain().getCommandStack().flush();

			problemIndication.setState(false);
			for (Resource resource : changedResources) {
				if (resource.isLoaded()) {
					resource.unload();
					try {
						resource.load(Collections.EMPTY_MAP);
					} catch (IOException exception) {
						if (!problemIndication.containsKey(resource)) {
							problemIndication.put(resource, ResourceUtil.analyzeResourceProblems(resource, exception));
						}
					}
				}
			}

			if (AdapterFactoryEditingDomain.isStale(getEditor().getSelection())) {
				getEditor().setSelection(StructuredSelection.EMPTY);
			}

			problemIndication.setState(true);
			problemIndication.update();
		}
	}
	
	
	public void doSave(IProgressMonitor progressMonitor) {
		
	}
	
	// ---- some getter / setter 
	
	public Collection<Resource> getSavedResources() {
		return this.savedResources;
	}
	
	public void setProblemIndication (ProblemIndication problemIndication) {
		this.problemIndication = problemIndication;
	}	
	
	// ----- protected
	
	/**
	 * Shows a dialog that asks if conflicting changes should be discarded. 
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	protected boolean handleDirtyConflict() {
		return MessageDialog.openQuestion(getSite().getShell(), Messages._UI_FileConflict_label, Messages._UI_FileConflict_label);
	}
	
	protected IWorkbenchPartSite getSite() {
		return this.editor.getSite();
	}
	
	protected ModelEditor getEditor() {
		return this.editor;
	}

	protected Display getDisplay() {
		return PlatformUI.getWorkbench().getDisplay();
	}
	
	protected boolean isDirty() {
		return this.editor.isDirty();
	}
	
	protected AdapterFactoryEditingDomain getEditingDomain() {
		return (AdapterFactoryEditingDomain) getEditor().getEditingDomain();
	}
	
	
}
