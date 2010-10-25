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

package net.zehrer.no2.text;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.text.IDocument;

/**
 * provides access to the EMF resource of this document
 */
public interface IResourceDocument extends IDocument {
	
	public void setResource(Resource resource);
	
	public Resource getResource ();

	public void disposeResource();
	
	/**
	 * Set the root model element of this document
	 * @param model 
	 */
	public void setTextModel(EObject model);
	
	/**
	 * Get the root model element of this document
	 * @return ECore object
	 */
	public EObject getTextModel ();
}
