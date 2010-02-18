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

package net.zehrer.no2.action;

import net.zehrer.no2.command.CreateObjectCommand;
import net.zehrer.no2.editor.ModelEditor;
import net.zehrer.no2.model.NO2Model;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IActionDelegate2;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;

/**
 * 
 */
@SuppressWarnings("unused")
public class CreateDynamicObjectAction extends ActionDelegate implements IActionDelegate2 {

	protected static final URI PLATFORM_RESOURCE = URI.createPlatformResourceURI("/", false);

	
	protected Command command;
	protected EClass eClass;
	protected EditingDomain editingDomain;
	

	public CreateDynamicObjectAction() {
		super();
	}

	@Override
	public void runWithEvent(IAction action, Event event) {

//		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

	    // this guard is for extra security, but should not be necessary
	    if (editingDomain != null && command != null) {
	    
	      //TODO: both the command and the run requires the editingDomain
	      editingDomain.getCommandStack().execute(command);
	    }
	}

	/**
	 * @category ActionDelegate
	 */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {

		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			Object object = structuredSelection.getFirstElement();

			if ((object instanceof EClass) && (structuredSelection.size() == 1)) {
				eClass = (EClass) object;

				URI uri = eClass.eResource().getURI();
				ResourceSet resourceSet = eClass.eResource().getResourceSet();
				NO2Model no2Model = (NO2Model) resourceSet.getEObject(URI.createURI("/no2.xmi#/"), true);
				
				editingDomain = no2Model.getEditingDomainProvider().getEditingDomain();
				
				command = new CreateObjectCommand(editingDomain, no2Model, eClass);
				
				action.setEnabled(!eClass.isAbstract());
				return;
			}
		}
		eClass = null;
		command = null;
		editingDomain = null;
		
		action.setEnabled(false);
	}
}
