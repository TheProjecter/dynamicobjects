/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.zehrer.no2.semantic.editor.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Leaf Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.LeafNode#getText <em>Text</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.zehrer.no2.semantic.editor.model.EditorPackage#getLeafNode()
 * @model
 * @generated
 */
public interface LeafNode extends AbstractNode {
	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see #setText(String)
	 * @see net.zehrer.no2.semantic.editor.model.EditorPackage#getLeafNode_Text()
	 * @model
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link net.zehrer.no2.semantic.editor.model.LeafNode#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

} // LeafNode
