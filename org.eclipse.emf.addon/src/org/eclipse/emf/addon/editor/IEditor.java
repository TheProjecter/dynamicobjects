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

package org.eclipse.emf.addon.editor;

import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.jface.viewers.StructuredViewer;

public interface IEditor {
	
	/**
	 * @return return the EditingDomainActionBarContributor or null if not supported.
	 */
	public EditingDomainActionBarContributor getActionBarContributor();
	
	/**
	 * Creates the context menu for the outline view.
	 * @param viewer
	 */
	public void createContextMenuFor(StructuredViewer viewer);

}
