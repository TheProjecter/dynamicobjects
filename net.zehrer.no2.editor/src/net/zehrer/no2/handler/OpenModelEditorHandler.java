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

package net.zehrer.no2.handler;

import net.zehrer.no2.NO2EditorPlugin;
import net.zehrer.no2.editor.ModelEditor;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.ide.IDE;

public class OpenModelEditorHandler extends AbstractHandler {

	public static final String COMMAND_ID = OpenModelEditorHandler.class.getName();

	protected ModelEditor editor;

	public OpenModelEditorHandler(ModelEditor editor) {
		this.editor = editor;

		// register this handler
		//TODO: move registration into plugin.xml?
		IHandlerService handlerService = (IHandlerService) PlatformUI.getWorkbench().getService(IHandlerService.class);
		handlerService.activateHandler(COMMAND_ID, this);

	}

	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = workbenchWindow.getActivePage();

		Shell shell = workbenchWindow.getShell();

		// URI uri = URI.createPlatformPluginURI(modelPath, true);

		// Open an editor on the new file.

		try {
			URIEditorInput editorInput = new URIEditorInput(editor.getMetaModelURI());

			// TODO: update status if no editor is available
			IEditorDescriptor desc = workbench.getEditorRegistry().getDefaultEditor(".ecore");
			if (desc != null) {
				IDE.openEditor(page, editorInput, desc.getId());				
			}

		} catch (PartInitException exception) {
			MessageDialog.openError(shell, getString("_UI_OpenEditorError_label"), exception.getMessage());
			return false;
		}

		return null;

		//TODO: clarify what is task of this code ...
		// if (activePart instanceof ISetSelectionTarget) {
		// final ISelection targetSelection = new
		// StructuredSelection(modelFile);
		// getShell().getDisplay().asyncExec(new Runnable() {
		// public void run() {
		// ((ISetSelectionTarget) activePart).selectReveal(targetSelection);
		// }
		// });
	}

	protected String getString(String name) {
		return NO2EditorPlugin.INSTANCE.getString(name);
	}

}
