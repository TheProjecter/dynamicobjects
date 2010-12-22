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

package net.zehrer.no2.semantic.editor.model.command;

import net.zehrer.common.interval.EIntInterval;
import net.zehrer.no2.semantic.editor.model.CompositeNode;

import org.eclipse.emf.edit.command.AbstractOverrideableCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

public class GroupCommand extends AbstractOverrideableCommand {
	/**
	 * This caches the label.
	 */
//	protected static final String LABEL = EditPlugin.INSTANCE.getString("_UI_GroupCommand_label");

	/**
	 * This caches the description.
	 */
//	protected static final String DESCRIPTION = EditPlugin.INSTANCE.getString("_UI_GroupCommand_description");

	/**
	 * This is the owner object upon which the command will act.
	 */
	protected CompositeNode owner;

	/**
	 * This is the owner object upon which the command will act.
	 */
	protected EIntInterval selection;

	public GroupCommand(EditingDomain domain, CompositeNode owner, EIntInterval selection) {
		super(domain);

		this.owner = owner;
		this.selection = selection;
	}

	@Override
	protected boolean prepare() {

		return true;
	}

	@Override
	public void doExecute() {
		owner.groupNodes(selection);
	}

	@Override
	public boolean doCanUndo() {
		return false; 
	}

	@Override
	public void doUndo() {

	}

	@Override
	public void doRedo() {
		doExecute();
	}

}
