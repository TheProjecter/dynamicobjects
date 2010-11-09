/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.zehrer.no2.semantic.editor.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.DocumentEvent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getParent <em>Parent</em>}</li>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getGrammarElement <em>Grammar Element</em>}</li>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getElement <em>Element</em>}</li>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getSyntaxError <em>Syntax Error</em>}</li>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getTotalOffset <em>Total Offset</em>}</li>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getTotalLine <em>Total Line</em>}</li>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getTotalLength <em>Total Length</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.zehrer.no2.semantic.editor.model.EditorPackage#getAbstractNode()
 * @model abstract="true"
 * @generated
 */
public interface AbstractNode extends EObject {
	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link net.zehrer.no2.semantic.editor.model.CompositeNode#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(CompositeNode)
	 * @see net.zehrer.no2.semantic.editor.model.EditorPackage#getAbstractNode_Parent()
	 * @see net.zehrer.no2.semantic.editor.model.CompositeNode#getChildren
	 * @model opposite="children" transient="false"
	 * @generated
	 */
	CompositeNode getParent();

	/**
	 * Sets the value of the '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(CompositeNode value);

	/**
	 * Returns the value of the '<em><b>Grammar Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grammar Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grammar Element</em>' reference.
	 * @see #setGrammarElement(EObject)
	 * @see net.zehrer.no2.semantic.editor.model.EditorPackage#getAbstractNode_GrammarElement()
	 * @model
	 * @generated
	 */
	EObject getGrammarElement();

	/**
	 * Sets the value of the '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getGrammarElement <em>Grammar Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grammar Element</em>' reference.
	 * @see #getGrammarElement()
	 * @generated
	 */
	void setGrammarElement(EObject value);

	/**
	 * Returns the value of the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element</em>' reference.
	 * @see #setElement(EObject)
	 * @see net.zehrer.no2.semantic.editor.model.EditorPackage#getAbstractNode_Element()
	 * @model
	 * @generated
	 */
	EObject getElement();

	/**
	 * Sets the value of the '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getElement <em>Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(EObject value);

	/**
	 * Returns the value of the '<em><b>Syntax Error</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link net.zehrer.no2.semantic.editor.model.SyntaxError#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Syntax Error</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Syntax Error</em>' containment reference.
	 * @see #setSyntaxError(SyntaxError)
	 * @see net.zehrer.no2.semantic.editor.model.EditorPackage#getAbstractNode_SyntaxError()
	 * @see net.zehrer.no2.semantic.editor.model.SyntaxError#getNode
	 * @model opposite="node" containment="true"
	 * @generated
	 */
	SyntaxError getSyntaxError();

	/**
	 * Sets the value of the '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getSyntaxError <em>Syntax Error</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Syntax Error</em>' containment reference.
	 * @see #getSyntaxError()
	 * @generated
	 */
	void setSyntaxError(SyntaxError value);

	/**
	 * Returns the value of the '<em><b>Total Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Total Offset</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Total Offset</em>' attribute.
	 * @see #setTotalOffset(int)
	 * @see net.zehrer.no2.semantic.editor.model.EditorPackage#getAbstractNode_TotalOffset()
	 * @model
	 * @generated
	 */
	int getTotalOffset();

	/**
	 * Sets the value of the '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getTotalOffset <em>Total Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Total Offset</em>' attribute.
	 * @see #getTotalOffset()
	 * @generated
	 */
	void setTotalOffset(int value);

	/**
	 * Returns the value of the '<em><b>Total Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Total Line</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Total Line</em>' attribute.
	 * @see #setTotalLine(int)
	 * @see net.zehrer.no2.semantic.editor.model.EditorPackage#getAbstractNode_TotalLine()
	 * @model
	 * @generated
	 */
	int getTotalLine();

	/**
	 * Sets the value of the '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getTotalLine <em>Total Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Total Line</em>' attribute.
	 * @see #getTotalLine()
	 * @generated
	 */
	void setTotalLine(int value);

	/**
	 * Returns the value of the '<em><b>Total Length</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Total Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Total Length</em>' attribute.
	 * @see #setTotalLength(int)
	 * @see net.zehrer.no2.semantic.editor.model.EditorPackage#getAbstractNode_TotalLength()
	 * @model default="-1" ordered="false"
	 * @generated
	 */
	int getTotalLength();

	/**
	 * Sets the value of the '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getTotalLength <em>Total Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Total Length</em>' attribute.
	 * @see #getTotalLength()
	 * @generated
	 */
	void setTotalLength(int value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return TextModelUtil.serialize(this);'"
	 * @generated
	 */
	String serialize();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return TextModelUtil.getLeafNodes(this);'"
	 * @generated
	 */
	EList<LeafNode> getLeafNodes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model toRequired="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return TextModelUtil.getLeafNodes(this, to);'"
	 * @generated
	 */
	EList<LeafNode> getLeafNodes(AbstractNode to);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='if (this instanceof CompositeNodeImpl) { \nreturn TextModelUtil.allSyntaxErrors((CompositeNodeImpl) this);\n} else if (this instanceof LeafNodeImpl) { \nreturn TextModelUtil.allSyntaxErrors((LeafNodeImpl) this);\n} else {\nreturn TextModelUtil.allSyntaxErrors((AbstractNodeImpl) this);\n}'"
	 * @generated
	 */
	EList<SyntaxError> allSyntaxErrors();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return TextModelUtil.totalEndLine(this);'"
	 * @generated
	 */
	int totalEndLine();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return TextModelUtil.getOffset(this);'"
	 * @generated
	 */
	int getOffset();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return TextModelUtil.getLine(this);'"
	 * @generated
	 */
	int getLine();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return TextModelUtil.getLength(this);'"
	 * @generated
	 */
	int getLength();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='return TextModelUtil.endLine(this);'"
	 * @generated
	 */
	int endLine();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model eventDataType="net.zehrer.no2.semantic.editor.model.DocumentEvent"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='TextModelUtil.update(this, event);'"
	 * @generated
	 */
	void update(DocumentEvent event);

} // AbstractNode
