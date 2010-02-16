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

package net.zehrer.no2;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	// BUNDLE_NAME = package name -> TODO: get from class information?
	private static final String BUNDLE_NAME = "net.zehrer.no2.messages"; //$NON-NLS-1$
	
	public static String _UI_CreateObjectCommand_Description;
	public static String _UI_CreateObjectCommand_Label;
	public static String _UI_CreateObjectCommand_Text;
	public static String _UI_CreateObjectCommand_ToolTip;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
