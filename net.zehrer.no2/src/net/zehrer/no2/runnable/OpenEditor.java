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

package net.zehrer.no2.runnable;

import org.eclipse.core.resources.IFile;
import org.eclipse.ui.*;
import org.eclipse.ui.ide.IDE;

public class OpenEditor implements Runnable {
	
	private IFile file = null;
	
	public OpenEditor (IFile file) {
		this.file = file;
	}

	public void run() {
		IWorkbenchPage page =
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		try {
			IDE.openEditor(page, file, true);
		} catch (PartInitException e) {
		}

	}

}
