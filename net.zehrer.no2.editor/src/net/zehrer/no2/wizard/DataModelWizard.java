/*******************************************************************************
 * Copyright (c) 2009 Stephan Zehrer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephan Zehrer - initial API and implementation
 *******************************************************************************/

package net.zehrer.no2.wizard;

import java.io.IOException;

import net.zehrer.no2.NO2EditorPlugin;
import net.zehrer.no2.model.ModelFactory;
import net.zehrer.no2.model.NO2Model;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;

// TODO: external strings update
public class DataModelWizard extends Wizard implements INewWizard {

	/**
	 * Remember the workbench during initialization.
	 */
	protected IWorkbench workbench;

	/**
	 * Remember the selection during initialization for populating the default
	 * container.
	 */
	protected IStructuredSelection selection;

	protected DataModelWizardNewFileCreationPage newFileCreationPage;

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;

		setWindowTitle(getString("_UI_Wizard_label"));
		setDefaultPageImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(getImage("full/wizban/NewModel")));
	}

	@Override
	public void addPages() {

		newFileCreationPage = new DataModelWizardNewFileCreationPage(selection);
		newFileCreationPage.setTitle(getString("_UI_ModelModelWizard_label"));
		newFileCreationPage.setDescription(getString("_UI_ModelModelWizard_description"));
		newFileCreationPage.setFileName(getString("_UI_ModelEditorFilenameDefaultBase") + "." + NO2EditorPlugin.FILE_EXTENSIONS.get(0));
		addPage(newFileCreationPage);
	}

	/**
	 * Do the work after everything is specified.
	 */
	@Override
	public boolean performFinish() {
		try {
			// Remember the file.
			//
			final IFile modelFile = getModelFile();

			// Do the work within an operation.
			//
			WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
				@Override
				protected void execute(IProgressMonitor progressMonitor) {

					NO2Model model = ModelFactory.eINSTANCE.createNO2Model();

					// Get the URI of the model file.
					String fileName = modelFile.getFullPath().toString();

					try {
						model.createModelResource(fileName);
					} catch (IOException exception) {
						NO2EditorPlugin.INSTANCE.log(exception);
					} finally {
						progressMonitor.done();
					}

				}
			};

			getContainer().run(false, false, operation);

			// Select the new file resource in the current view.
			//
			IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
			IWorkbenchPage page = workbenchWindow.getActivePage();
			final IWorkbenchPart activePart = page.getActivePart();
			if (activePart instanceof ISetSelectionTarget) {
				final ISelection targetSelection = new StructuredSelection(modelFile);
				getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						((ISetSelectionTarget) activePart).selectReveal(targetSelection);
					}
				});
			}

			// Open an editor on the new file.
			//
			try {
				page.openEditor(new FileEditorInput(modelFile), workbench.getEditorRegistry().getDefaultEditor(modelFile.getFullPath().toString()).getId());
			} catch (PartInitException exception) {
				MessageDialog.openError(workbenchWindow.getShell(), getString("_UI_OpenEditorError_label"), exception.getMessage());
				return false;
			}

			return true;
		} catch (Exception exception) {
			NO2EditorPlugin.INSTANCE.log(exception);
			return false;
		}
	}

	/**
	 * Get the file from the page.
	 */
	public IFile getModelFile() {
		return newFileCreationPage.getModelFile();
	}

	protected String getString(String aName) {
		return NO2EditorPlugin.INSTANCE.getString(aName);
	}

	protected Object getImage(String aName) {
		return NO2EditorPlugin.INSTANCE.getImage("full/wizban/NewModel");
	}

}
