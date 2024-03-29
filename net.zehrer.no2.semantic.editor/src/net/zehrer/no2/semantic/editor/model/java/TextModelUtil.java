/*******************************************************************************
 * TODO 
 * 
 * Initial Code from ParsetreeUtil of XText
 * 
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 *
 *******************************************************************************/
package net.zehrer.no2.semantic.editor.model.java;

import java.util.List;

import net.zehrer.common.interval.EIntInterval;
import net.zehrer.no2.semantic.editor.model.AbstractNode;
import net.zehrer.no2.semantic.editor.model.CompositeNode;
import net.zehrer.no2.semantic.editor.model.EditorFactory;
import net.zehrer.no2.semantic.editor.model.EditorPackage;
import net.zehrer.no2.semantic.editor.model.LeafNode;
import net.zehrer.no2.semantic.editor.model.SyntaxError;
import net.zehrer.no2.semantic.editor.model.impl.LeafNodeImpl;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.TypedRegion;

public class TextModelUtil {

	private static final char[] separator = System.getProperty("line.separator").toCharArray();

	private static void checkArgument(AbstractNode abstractParserNode) {
		int classifierID = abstractParserNode.eClass().getClassifierID();
		if (classifierID != EditorPackage.COMPOSITE_NODE && classifierID != EditorPackage.LEAF_NODE) {
			throw new IllegalArgumentException("Illegal subtype of AbstarctParserNode " + abstractParserNode.eClass().getName());
		}
	}

	public static int line(AbstractNode _this) {
		checkArgument(_this);
		AbstractNode rootContainer = (AbstractNode) EcoreUtil.getRootContainer(_this);
		EList<LeafNode> leafNodes = rootContainer.getLeafNodes(_this);
		int line = 1;
		for (LeafNode leafNode : leafNodes) {
			String text = leafNode.getText();
			line += countLines(text);
		}
		return line;
	}

	public static int totalEndLine(AbstractNode _this) {
		int line = _this.getTotalLine();
		String text = _this.serialize();
		line += countLines(text);
		return line;
	}

	public static int countLines(String text) {
		return countLines(text, separator);
	}

	public static int countLines(String text, char[] separator) {
		int line = 0;
		char[] charArray = text.toCharArray();
		if (separator.length == 1) {
			for (int i = 0; i < charArray.length; i++) {
				if (charArray[i] == separator[0]) {
					line++;
				}
			}
		} else if (separator.length == 2) {
			for (int i = 0; i < charArray.length; i++) {
				if (charArray[i] == separator[0] && charArray.length > i + 1 && charArray[i + 1] == separator[1]) {
					line++;
					i++;
				} else if (charArray[i] == separator[1]) {
					line++;
				}
			}
		} else {
			throw new IllegalArgumentException("Separators with more than two characters are unexpected");
		}
		return line;
	}

	public static String serialize(AbstractNode _this) {
		if (_this instanceof LeafNodeImpl)
			return serialize((LeafNodeImpl) _this);
		checkArgument(_this);
		StringBuilder buffer = new StringBuilder(Math.max(16, _this.getLength()));
		// EList<LeafNode> leafNodes = _this.getLeafNodes();
		// for (LeafNode leafNode : leafNodes) {
		// buff.append(leafNode.getText());
		// }
		serialize(_this, buffer);
		return buffer.toString();
	}

	public static void serialize(AbstractNode node, StringBuilder buffer) {
		if (node instanceof LeafNode)
			buffer.append(((LeafNode) node).getText());
		else {
			CompositeNode parent = (CompositeNode) node;
			EList<AbstractNode> children = parent.getChildren();
			for (int i = 0; i < children.size(); i++) {
				serialize(children.get(i), buffer);
			}
		}
	}

	public static String serialize(LeafNodeImpl _this) {
		return _this.getText();
	}

	public static EList<LeafNode> getLeafNodes(AbstractNode _this) {
		return getLeafNodes(_this, null);
	}

	public static EList<LeafNode> getLeafNodes(AbstractNode _this, AbstractNode to) {
		checkArgument(_this);
		BasicEList<LeafNode> result = new BasicEList<LeafNode>();
		TreeIterator<EObject> allContents = _this.eAllContents();
		while (allContents.hasNext()) {
			EObject current = allContents.next();
			if (current.equals(to))
				return result;
			if (current instanceof LeafNode) {
				result.add((LeafNode) current);
			}
		}
		return result;
	}

	public static EList<SyntaxError> allSyntaxErrors(CompositeNode compositeNodeImpl) {
		BasicEList<SyntaxError> basicEList = new BasicEList<SyntaxError>();
		addAllSyntaxErrors(compositeNodeImpl, basicEList);
		return basicEList;
	}

	public static void addAllSyntaxErrors(CompositeNode node, BasicEList<SyntaxError> result) {
		if (node.getSyntaxError() != null)
			result.add(node.getSyntaxError());
		List<AbstractNode> children = node.getChildren();
		for (int i = 0; i < children.size(); i++) {
			AbstractNode child = children.get(i);
			if (child instanceof LeafNode) {
				if (child.getSyntaxError() != null)
					result.addUnique(child.getSyntaxError());
			} else {
				addAllSyntaxErrors((CompositeNode) child, result);
			}
		}
	}

	public static EList<SyntaxError> allSyntaxErrors(LeafNode leafNodeImpl) {
		BasicEList<SyntaxError> basicEList = new BasicEList<SyntaxError>();
		if (leafNodeImpl.getSyntaxError() != null)
			basicEList.add(leafNodeImpl.getSyntaxError());
		return basicEList;
	}

	public static EList<SyntaxError> allSyntaxErrors(AbstractNode abstractNodeImpl) {
		return null;
	}

	/**
	 * @param abstractNode
	 * @return
	 */
	// public static int getOffset(AbstractNode abstractNode) {
	//
	// if (abstractNode instanceof LeafNode)
	// return abstractNode.getTotalOffset();
	//
	// final CompositeNode node = (CompositeNode) abstractNode;
	//
	// for (int i = 0; i < node.getChildren().size(); i++) {
	// final AbstractNode child = node.getChildren().get(i);
	//
	// if (child instanceof CompositeNode) {
	// if (hasLeafNodes((CompositeNode) child))
	// return getOffset(child);
	// } else {
	// final LeafNode leaf = (LeafNode) child;
	// if (!leaf.isHidden())
	// return leaf.getTotalOffset();
	// }
	// }
	//
	// // every child node is a hidden node, return total offset
	// return abstractNode.getTotalOffset();
	// }

//	private static boolean hasLeafNodes(CompositeNode child) {
//		for (AbstractNode node : child.getChildren()) {
//			if (node instanceof CompositeNode) {
//				if (hasLeafNodes((CompositeNode) node))
//					return true;
//			} else {
//				return true;
//			}
//		}
//		return false;
//	}

	/**
	 * @param abstractNode
	 * @return
	 */
	public static int getLine(AbstractNode abstractNode) {
		if (abstractNode instanceof LeafNode)
			return abstractNode.getTotalLine();
		final CompositeNode node = (CompositeNode) abstractNode;
		for (int i = 0; i < node.getChildren().size(); i++) {
			final AbstractNode child = node.getChildren().get(i);
			if (child instanceof CompositeNode)
				return getLine(child);
			final LeafNode leaf = (LeafNode) child;
			// if (!leaf.isHidden())
			return leaf.getTotalLine();
		}
		// every child node is a hidden node, return total line
		return abstractNode.getTotalLine();
	}

	/**
	 * @param abstractNode
	 * @return
	 */
	// public static int getLength(AbstractNode abstractNode) {
	//
	// if (abstractNode instanceof LeafNode)
	// return abstractNode.getTotalLength();
	//
	// final CompositeNode node = (CompositeNode) abstractNode;
	// for (int i = node.getChildren().size() - 1; i >= 0; i--) {
	//
	// final AbstractNode child = node.getChildren().get(i);
	// if (child instanceof CompositeNode)
	// return child.getOffset() - abstractNode.getOffset() + child.getLength();
	//
	// if (!((LeafNode) child).isHidden())
	// return child.getTotalOffset() - abstractNode.getOffset() +
	// child.getTotalLength();
	// }
	// // every child node is a hidden node, return total length
	// return abstractNode.getTotalLength();
	// }

	/**
	 * @param abstractNode
	 * @return
	 */
	public static int endLine(AbstractNode abstractNode) {
		if (abstractNode instanceof LeafNode)
			return abstractNode.totalEndLine();
		final CompositeNode node = (CompositeNode) abstractNode;
		for (int i = node.getChildren().size() - 1; i >= 0; i--) {
			final AbstractNode child = node.getChildren().get(i);
			if (child instanceof CompositeNode)
				return endLine(child);
			// if (!((LeafNode) child).isHidden())
			return child.totalEndLine();
		}
		// every child node is a hidden node, return total endLine
		return abstractNode.totalEndLine();
	}

	/**
	 * Return the related leaf node
	 * 
	 * @param _this
	 * @param offset
	 * @return
	 */
	public static LeafNode getLeafNode(AbstractNode _this, int offset) {

		LeafNode result = null;

		// 1st: check of offset starts in this node
		if (!checkNodeOffset(_this, offset))
			return null; // exit condition

		// 2nd: in yes analyze deeper
		if (_this instanceof LeafNode) {
			result = (LeafNode) _this;
		} else {
			CompositeNode parent = (CompositeNode) _this;

			EList<AbstractNode> children = parent.getChildren();
			for (AbstractNode abstractNode : children) {
				result = getLeafNode(abstractNode, offset);
				if (result != null)
					break;
			}
		}

		return result;
	}

	/**
	 * Return a list of leaf nodes beginning with "start offset" and end with
	 * "end offset" including all leaf nodes in between
	 * 
	 * @param _this
	 *            starting composite node
	 * @param sOffset
	 *            - is the start offset
	 * @param eOffset
	 *            - is the end offset
	 * @return the list of leaf nodes
	 */
	public static EList<LeafNode> getLeafNodes(AbstractNode _this, int sOffset, int eOffset) {

		BasicEList<LeafNode> result = new BasicEList<LeafNode>();

		addLeafNodes(_this, sOffset, eOffset, result);

		return result;
	}

	/**
	 * Return the related leaf node
	 * 
	 * @param _this
	 * @param offset
	 * @return
	 */
	public static int addLeafNodes(AbstractNode _this, int indexOffset, int endOffset, EList<LeafNode> list) {

		// 1st: check the offset for start and end in this node
		if (!checkNodeOffset(_this, indexOffset)) {
			return indexOffset; // search for start
		}

		int result = indexOffset;

		// 2nd: in yes analyze deeper
		if (_this instanceof LeafNode) {
			LeafNode leafNode = (LeafNode) _this;
			list.add(leafNode);

			result = leafNode.getOffset() + leafNode.getLength();
			if (result > endOffset)
				result = -1; // exit condition

		} else {
			CompositeNode parent = (CompositeNode) _this;

			EList<AbstractNode> children = parent.getChildren();
			for (AbstractNode abstractNode : children) {
				result = addLeafNodes(abstractNode, result, endOffset, list);
				if (result == -1)
					break;
			}
		}

		return result;
	}

	/**
	 * Return true if offset within _this node
	 * 
	 * @param _this
	 * @param offset
	 */
	public static boolean checkNodeOffset(AbstractNode _this, int offset) {

		int fOffset = _this.getOffset();
		int fLength = _this.getLength();

		// fOffset <= offset <= (fOffset + fLength)

		if ((fOffset <= offset)) {
			if (offset < fOffset + fLength) {
				return true;
			}
		}

		return false;
	}

	public static void update(AbstractNode _this, DocumentEvent event) {
		if (_this.includes(event.getOffset())) {
			updateModel(_this, event, null);			
		} else {
			// check the offset??
			appendModel(_this,event,null);
		}
	}

	public static DocumentEvent updateModel(AbstractNode _this, DocumentEvent event, CompositeNode parent) {

		// 1st: check of event starts in this node
		if (!_this.includes(event.getOffset()))
			return event;

		DocumentEvent result = event;

		// 2nd: distinguish type
		if (_this instanceof LeafNode) {
			result = updateModel((LeafNode) _this, result, parent);
		} else {
			CompositeNode fParent = (CompositeNode) _this;

			EList<AbstractNode> children = fParent.getChildren();
			for (AbstractNode abstractNode : children) {
				result = updateModel(abstractNode, result, fParent);

				if (result == null)
					break;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param _this
	 * @param event
	 * @return either null if even full covered in this node or
	 */
	public static DocumentEvent updateModel(LeafNode _this, DocumentEvent event, CompositeNode parent) {

		DocumentEvent result = null;

		int fOffset = _this.getOffset();
		int fLength = _this.getLength();

		int startIndex = event.getOffset() - fOffset;
		int endIndex = startIndex + event.getLength();

		// if the change range bigger as the current LeafNode?
		if (endIndex > fOffset + fLength) {

			int sOffset = fOffset + fLength;
			int sLength = endIndex - fOffset + fLength;

			// TODO fix SPLIT event
			// BUG !!!

			result = new DocumentEvent(event.getDocument(), sOffset, sLength, "");

			endIndex = fOffset + fLength;
		}

		// replace text
		StringBuffer buffer = new StringBuffer(_this.getText());
		buffer.replace(startIndex, endIndex, event.getText());

		// replace leafNode to notify the adapter
		// TODO: or update the NodeContentAdapter;
		_this.setText(buffer.toString());
		int index = parent.getChildren().indexOf(_this);
		parent.getChildren().set(index, _this);

		return result;
	}
	
	public static void appendModel (AbstractNode _this, DocumentEvent event, CompositeNode parent) {
		
		// distinguish type
		if (_this instanceof LeafNode) {
			LeafNode fLeaf = (LeafNode) _this;
			
			// append text
			StringBuffer buffer = new StringBuffer(fLeaf.getText());
			buffer.append(event.getText());
			fLeaf.setText(buffer.toString());
			
			// init model notification
			int index = parent.getChildren().indexOf(_this);
			parent.getChildren().set(index, _this);
		} else {
			CompositeNode fParent = (CompositeNode) _this;

			EList<AbstractNode> children = fParent.getChildren();
			
			appendModel(children.get(children.size()-1), event, fParent);

		}
		
	}


	public static TypedRegion createTypedRegion(AbstractNode _this, String type) {
		return new TypedRegion(_this.getOffset(), _this.getLength(), type);
	}

	// -----

	public static boolean group(AbstractNode _this, EIntInterval selection) {
		
		// selection size >= 1 

		if (_this instanceof CompositeNode) {
			CompositeNode fParent = (CompositeNode) _this;
			
			BasicEList<AbstractNode> intersectingNodes = null;
			
			EList<AbstractNode> children = fParent.getChildren();
			
			// 1st iterate through the graph and collect intersecting nodes
			for (AbstractNode abstractNode : children) {
				
				if (abstractNode.intersects(selection)) {
					
					// only of the selection intersects do ....
					if (abstractNode instanceof CompositeNode) {
						if (selection.isSubsetOf(abstractNode)) {
							// go more deeper in the graph
							group(abstractNode,selection);
							return true;
						} 
					}
					
				
					if (intersectingNodes == null) {
						intersectingNodes = new BasicEList<AbstractNode>();  // auto init? or generic init?
					}
					intersectingNodes.add(abstractNode);	
				}
			}
			
			// 2nd group intersecting nodes
			
			group (intersectingNodes,children, selection);
		}
		

		return true;
	}
	
	
	public static void group(BasicEList<AbstractNode> intersectingNodes,  EList<AbstractNode> children, EIntInterval selection ) {
		// intersectingNodes.size >= 1
		
		CompositeNode groupNode = EditorFactory.eINSTANCE.createCompositeNode();
		groupNode.setName("NEW");
	
		// get position of first intersecting node
		AbstractNode firstNode = intersectingNodes.get(0);
		int index = children.indexOf(firstNode);
		
		// replace the grouped nodes be the group and the complements ...
		// ... in right order (fill from the end otherwise the order is not correct)
		
		// 1. remove the intersecting nodes
		children.removeAll(intersectingNodes);
		
		// 2. rightComplement == last node 
		AbstractNode lastNode = intersectingNodes.get(intersectingNodes.size()-1);
		add(children, (AbstractNode) lastNode.rightComplementTo(selection), index);
		
		// 3. add the new group
		children.add(index, groupNode);
		
		// 4. leftComplement == 1st node 
		add(children, (AbstractNode) firstNode.leftComplementTo(selection), index);
		
		// add intersection's to the groups
		for (AbstractNode currentNode : intersectingNodes) {
			add(groupNode.getChildren(),(AbstractNode) currentNode.intersect(selection));
		}
		
	}


	private static <T> void add(EList<T> list, T object) {
		if (object != null)
			list.add(object);
	}
	
	// addes only if object is not null
	private static <T> void add(EList<T> list, T object, int pos) {
		if (object != null)
			list.add(pos, object);
	}
	
}
