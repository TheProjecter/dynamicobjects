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

package net.zehrer.common.interval.command;

import java.util.Collection;
import java.util.Collections;

import net.zehrer.common.interval.EIntInterval;

import org.eclipse.emf.edit.command.AbstractOverrideableCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * Intersects A and B
 * 
 * @author steve
 */
public class IntersectCommand extends AbstractOverrideableCommand {

	/**
	 * This is the interval object upon which the command will act.
	 */
	protected EIntInterval a;

	/**
	 * This is the interval object upon which the command will act.
	 */
	protected EIntInterval b;

	/**
	 * This is the collection of objects being added to the owner list.
	 */
	protected EIntInterval result;

	protected IntersectCommand(EditingDomain domain, EIntInterval a, EIntInterval b) {
		super(domain);

		this.a = a;
		this.b = b;
	}

	@Override
	public void doExecute() {
		this.result = a.intersect(b);
	}

	@Override
	public void doUndo() {

	}

	@Override
	public void doRedo() {
		doExecute();
	}

	@Override
	public Collection<?> doGetResult() {
		return Collections.singleton(result);
	}

}
