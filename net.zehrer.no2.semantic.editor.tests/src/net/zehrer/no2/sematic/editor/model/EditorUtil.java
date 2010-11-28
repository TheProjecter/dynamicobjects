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

package net.zehrer.no2.sematic.editor.model;

import net.zehrer.no2.semantic.editor.model.AbstractNode;
import net.zehrer.no2.semantic.editor.model.CompositeNode;
import net.zehrer.no2.semantic.editor.model.EditorFactory;
import net.zehrer.no2.semantic.editor.model.LeafNode;


public class EditorUtil {
	
	public static String logGraph (CompositeNode node) {
		
		StringBuilder buffer = new StringBuilder(Math.max(16, node.getLength()));

		logGraph(node, buffer);
		return buffer.toString();
	}
	

	public static void logGraph(AbstractNode node, StringBuilder buffer) {
		if (node instanceof LeafNode) {
			buffer.append("LeafNode (" + node.getOffset() + ":" + node.getLength() + ") : ");
			buffer.append(((LeafNode) node).getText() + "\n");
		}
		else {
			CompositeNode parent = (CompositeNode) node;
			buffer.append("CompositeNode (" + parent.getOffset() + ":" + parent.getLength() + ")\n");
			for (AbstractNode childNode : parent.getChildren()) {
				logGraph(childNode, buffer);
			}
		}
	}

	public static LeafNode getNode (String text) {
		LeafNode leafNode = EditorFactory.eINSTANCE.createLeafNode();
		leafNode.setText(text);
		return leafNode;
	}


}
