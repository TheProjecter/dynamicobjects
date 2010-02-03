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

/**
 * 
 */
package net.zehrer.no2.edit.ui.action;

import java.util.Collection;

import net.zehrer.no2.edit.command.CreateObjectCommand;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.StaticSelectionCommandAction;
import org.eclipse.jface.viewers.ISelection;

/**
 * A object creation action is implemented by the CreateObjectCommand.
 */
public class CreateObjectAction extends StaticSelectionCommandAction {

	// TODO: write a replacement for StaticSelectionCommandAction, support of
	// old interfaces is not required here.

	/**
	 * This describes the child to be created.
	 */
	protected Object descriptor;

	/**
	 * This constructs an instance of an action that uses the given editing
	 * domain to create a object instance specified by <code>descriptor</code>
	 * for the single EClass in the <code>selection</code>.
	 */
	public CreateObjectAction(EditingDomain editingDomain, ISelection selection, Object descriptor) {
		super(editingDomain);
		this.descriptor = descriptor;
		configureAction(selection);
	}

	/**
	 * This creates the command for
	 * {@link StaticSelectionCommandAction#createActionCommand}.
	 */
	@Override
	protected Command createActionCommand(EditingDomain editingDomain, Collection<?> collection) {

		if (collection.size() == 1) {

			CommandParameter parameter = (CommandParameter) descriptor;

			return new CreateObjectCommand(editingDomain, (EStructuralFeature) parameter.feature, parameter.value, collection);

			// return CreateObjectCommand.create(editingDomain, descriptor,
			// collection);
		}

		return UnexecutableCommand.INSTANCE;
	}

}
