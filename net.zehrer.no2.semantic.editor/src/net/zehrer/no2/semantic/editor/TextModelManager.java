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

package net.zehrer.no2.semantic.editor;

import net.zehrer.no2.semantic.editor.model.CompositeNode;
import net.zehrer.no2.semantic.editor.model.EditorFactory;
import net.zehrer.no2.semantic.editor.model.LeafNode;

import org.eclipse.emf.ecore.resource.Resource;

public class TextModelManager {

	static public void modelInit(Resource resource) {
		
		CompositeNode cNode = EditorFactory.eINSTANCE.createCompositeNode();
		
		LeafNode  lNode = EditorFactory.eINSTANCE.createLeafNode();
	
		cNode.getChildren().add(lNode);
		
		lNode.setText("Hallo World!");
		//lNode.setHidden(false);
	
		resource.getContents().add(cNode);
	}
	
}
