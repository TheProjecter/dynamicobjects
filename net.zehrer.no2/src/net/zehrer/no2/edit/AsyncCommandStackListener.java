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

package net.zehrer.no2.edit;

import java.util.EventObject;

import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.swt.widgets.Display;

/**
 * CommandStackListener make a async call for notification  
 */
public class AsyncCommandStackListener implements CommandStackListener {
	
	CommandStackListener listner;

	public AsyncCommandStackListener(CommandStackListener listner) {
		this.listner = listner;
	}

	public void commandStackChanged(final EventObject event) {

		Display.getCurrent().asyncExec(new Runnable() {
			public void run() {
				listner.commandStackChanged(event);

			}
		});
	}

}
