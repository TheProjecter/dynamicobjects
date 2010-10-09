/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.zehrer.no2.semantic.editor.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.CompositeNode#getChildren <em>Children</em>}</li>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.CompositeNode#getLookaheadLeafNodes <em>Lookahead Leaf Nodes</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.zehrer.no2.semantic.editor.model.EditorPackage#getCompositeNode()
 * @model
 * @generated
 */
public interface CompositeNode extends AbstractNode {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link net.zehrer.no2.semantic.editor.model.AbstractNode}.
	 * It is bidirectional and its opposite is '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see net.zehrer.no2.semantic.editor.model.EditorPackage#getCompositeNode_Children()
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#getParent
	 * @model opposite="parent" containment="true"
	 * @generated
	 */
	EList<AbstractNode> getChildren();

	/**
	 * Returns the value of the '<em><b>Lookahead Leaf Nodes</b></em>' reference list.
	 * The list contents are of type {@link net.zehrer.no2.semantic.editor.model.LeafNode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lookahead Leaf Nodes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lookahead Leaf Nodes</em>' reference list.
	 * @see net.zehrer.no2.semantic.editor.model.EditorPackage#getCompositeNode_LookaheadLeafNodes()
	 * @model
	 * @generated
	 */
	EList<LeafNode> getLookaheadLeafNodes();

} // CompositeNode
