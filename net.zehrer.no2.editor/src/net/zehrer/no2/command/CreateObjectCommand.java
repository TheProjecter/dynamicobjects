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

package net.zehrer.no2.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import net.zehrer.no2.Messages;
import net.zehrer.no2.model.ModelPackage;
import net.zehrer.no2.model.NO2Model;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandActionDelegate;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * This command wraps an {@link AddCommand} to provide the higher-level
 * operation of "instantiate" the appropriate (dynamic) object of a selected
 * EClass and adding this to the repository.
 * 
 * In fact, all of the possible object instances are created at the moment by a
 * generic item provider before this command is created, and specified in the
 * <code>newChildDescriptor</code> argument to {@link #create create()}
 * 
 * TODO if this is required for CreateObjects ..
 * 
 * As a result, this command essentially just creates and executes the
 * appropriate lower-level EMF command, and delegates matters of appearance
 * (text, icon, result) to the appropriate item provider, so that it may be
 * handled correctly for the given model.
 * 
 */
public class CreateObjectCommand extends CommandWrapper implements CommandActionDelegate {

	/**
	 * This is the editing domain in which this command operates.
	 */
	protected EditingDomain domain;

	/**
	 * This is the model which is managing the new object
	 */
	protected NO2Model model;

	/**
	 * This is the new object to be added.
	 */
	protected EObject newObject;

	/**
	 * This is the value to be returned by {@link #getAffectedObjects}. The
	 * affected objects are different after an execute or redo from after an
	 * undo, so we record them.
	 */
	protected Collection<?> affectedObjects;

	/**
	 * This is the collection of objects that were selected when this command
	 * was created. After an undo, these are considered the affected objects.
	 */
	protected  Collection<?> selection;
	
	/**
	 * This constructor initializes an instance 
	 */
	public CreateObjectCommand(EditingDomain domain, NO2Model model, EClass eClass) {
		super();
		this.domain = domain;
		this.model = model;
		
		
		Collection<EClass> list = new ArrayList<EClass>();
		list.add(eClass);
		this.selection = list;
	
		
		// dynamic Object creation
		EFactory eFactory = eClass.getEPackage().getEFactoryInstance();
		this.newObject = eFactory.create(eClass);
		
		
		
		setLabel(Messages._UI_CreateObjectCommand_Label);
		setDescription(Messages._UI_CreateObjectCommand_Description);
		
	}
	
	
	// ----- CommandWrapper ----
	/**
	 * This creates the wrapped {@link AddCommand} or {@link SetCommand} that
	 * adds the child object to the model. If the owner, feature, or child is
	 * <code>null</code>, or if the feature is single-valued and has already
	 * been set to an object, {@link UnexecutableCommand#INSTANCE} will be
	 * returned.
	 */
	@Override
	protected Command createCommand() {

		if (newObject == null) {
			return UnexecutableCommand.INSTANCE;
		}
		
		return AddCommand.create(domain, model, ModelPackage.Literals.NO2_MODEL__CONTENTS, newObject);

	}

	/**
	 * This executes the wrapped command and sets the affected objects to the
	 * collection returned by <code>helper.getCreateChildResult()</code>.
	 */
	@Override
	public void execute() {
		super.execute();
		affectedObjects = Collections.singletonList(newObject);
	}

	/**
	 * This undoes the wrapped command and sets the affected objects to the
	 * original selection.
	 */
	@Override
	public void undo() {
		super.undo();
		affectedObjects = selection; // TODO: clarify why?
	}

	/**
	 * This redoes the wrapped command and sets the affected objects to the
	 * collection returned by <code>helper.getCreateChildResult()</code>.
	 */
	@Override
	public void redo() {
		super.redo();
		affectedObjects = Collections.singletonList(newObject);
	}

	/**
	 * This returns the affected objects.
	 */
	@Override
	public Collection<?> getAffectedObjects() {
		return affectedObjects == null ? Collections.EMPTY_LIST : affectedObjects;
	}

	/**
	 * This returns the result of this command by delegating to
	 * <code>helper.getCreateChildResult()</code>.
	 */
	@Override
	public Collection<?> getResult() {
		Collection<?> result = Collections.singletonList(newObject);
		return result == null ? Collections.EMPTY_LIST : result;
	}

	// ---- CommandActionDelegate ---

	@Override  
	public String getText() {
		 return Messages._UI_CreateObjectCommand_Text; 
	}

	@Override 
	public String getToolTipText() {
		return Messages._UI_CreateObjectCommand_ToolTip; 
	}
	
	@Override 
	public Object getImage() {
		return null;  // no image
	}



}
