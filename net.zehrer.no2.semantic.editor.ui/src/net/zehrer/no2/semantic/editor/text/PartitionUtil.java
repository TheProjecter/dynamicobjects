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

package net.zehrer.no2.semantic.editor.text;

import net.zehrer.no2.semantic.editor.model.AbstractNode;
import net.zehrer.no2.semantic.editor.model.CompositeNode;
import net.zehrer.no2.semantic.editor.model.LeafNode;
import net.zehrer.no2.text.IResourceDocument;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.BadPositionCategoryException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TypedPosition;

public class PartitionUtil {
	
	private String positionCategory = null;
	private String contentType = null;
	
	public PartitionUtil(String positionCategory, String contentType) {
		this.positionCategory = positionCategory;
		this.contentType = contentType;
	}
	
	public  void partitionDocment (IResourceDocument document) throws BadLocationException, BadPositionCategoryException {
		
		CompositeNode rootNode = (CompositeNode) document.getTextModel();
		partitionNode(rootNode, document);

	}
	
	protected void partitionNode(AbstractNode node, IDocument document) throws BadLocationException, BadPositionCategoryException {

		if ( node instanceof LeafNode) {					
			document.addPosition(positionCategory, new TypedPosition(node.getOffset(), node.getLength(), contentType));
		}
		else {
			for (AbstractNode childNode : ((CompositeNode)node).getChildren()) 
			  partitionNode(childNode, document);		
		}
	}
}
