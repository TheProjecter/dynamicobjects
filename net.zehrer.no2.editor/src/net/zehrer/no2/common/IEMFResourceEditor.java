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

package net.zehrer.no2.common;

import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IEditorPart;


/**
 * Just a combination of several standard interfaces 
 * with the option of an IResourceManagerProvider -> TODO
 */
public interface IEMFResourceEditor extends IEditingDomainProvider, ISelectionProvider, IEditorPart {

	
	
	//public IResourceManager getResourceManager();
}
