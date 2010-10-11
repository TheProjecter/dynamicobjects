/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.zehrer.no2.semantic.editor.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see net.zehrer.no2.semantic.editor.model.EditorFactory
 * @model kind="package"
 * @generated
 */
public interface EditorPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://no2.zehrer.net/editor/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EditorPackage eINSTANCE = net.zehrer.no2.semantic.editor.model.impl.EditorPackageImpl.init();

	/**
	 * The meta object id for the '{@link net.zehrer.no2.semantic.editor.model.impl.AbstractNodeImpl <em>Abstract Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.zehrer.no2.semantic.editor.model.impl.AbstractNodeImpl
	 * @see net.zehrer.no2.semantic.editor.model.impl.EditorPackageImpl#getAbstractNode()
	 * @generated
	 */
	int ABSTRACT_NODE = 1;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE__PARENT = 0;

	/**
	 * The feature id for the '<em><b>Grammar Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE__GRAMMAR_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE__ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Syntax Error</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE__SYNTAX_ERROR = 3;

	/**
	 * The feature id for the '<em><b>Total Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE__TOTAL_OFFSET = 4;

	/**
	 * The feature id for the '<em><b>Total Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE__TOTAL_LINE = 5;

	/**
	 * The feature id for the '<em><b>Total Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE__TOTAL_LENGTH = 6;

	/**
	 * The number of structural features of the '<em>Abstract Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE_FEATURE_COUNT = 7;

	/**
	 * The operation id for the '<em>Serialize</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE___SERIALIZE = 0;

	/**
	 * The operation id for the '<em>Get Leaf Nodes</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE___GET_LEAF_NODES = 1;

	/**
	 * The operation id for the '<em>Get Leaf Nodes</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE___GET_LEAF_NODES__ABSTRACTNODE = 2;

	/**
	 * The operation id for the '<em>All Syntax Errors</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE___ALL_SYNTAX_ERRORS = 3;

	/**
	 * The operation id for the '<em>Total End Line</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE___TOTAL_END_LINE = 4;

	/**
	 * The operation id for the '<em>Get Offset</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE___GET_OFFSET = 5;

	/**
	 * The operation id for the '<em>Get Line</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE___GET_LINE = 6;

	/**
	 * The operation id for the '<em>Get Length</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE___GET_LENGTH = 7;

	/**
	 * The operation id for the '<em>End Line</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE___END_LINE = 8;

	/**
	 * The number of operations of the '<em>Abstract Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE_OPERATION_COUNT = 9;

	/**
	 * The meta object id for the '{@link net.zehrer.no2.semantic.editor.model.impl.CompositeNodeImpl <em>Composite Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.zehrer.no2.semantic.editor.model.impl.CompositeNodeImpl
	 * @see net.zehrer.no2.semantic.editor.model.impl.EditorPackageImpl#getCompositeNode()
	 * @generated
	 */
	int COMPOSITE_NODE = 0;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__PARENT = ABSTRACT_NODE__PARENT;

	/**
	 * The feature id for the '<em><b>Grammar Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__GRAMMAR_ELEMENT = ABSTRACT_NODE__GRAMMAR_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__ELEMENT = ABSTRACT_NODE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Syntax Error</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__SYNTAX_ERROR = ABSTRACT_NODE__SYNTAX_ERROR;

	/**
	 * The feature id for the '<em><b>Total Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__TOTAL_OFFSET = ABSTRACT_NODE__TOTAL_OFFSET;

	/**
	 * The feature id for the '<em><b>Total Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__TOTAL_LINE = ABSTRACT_NODE__TOTAL_LINE;

	/**
	 * The feature id for the '<em><b>Total Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__TOTAL_LENGTH = ABSTRACT_NODE__TOTAL_LENGTH;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__CHILDREN = ABSTRACT_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Lookahead Leaf Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__LOOKAHEAD_LEAF_NODES = ABSTRACT_NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Composite Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE_FEATURE_COUNT = ABSTRACT_NODE_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Serialize</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE___SERIALIZE = ABSTRACT_NODE___SERIALIZE;

	/**
	 * The operation id for the '<em>Get Leaf Nodes</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE___GET_LEAF_NODES = ABSTRACT_NODE___GET_LEAF_NODES;

	/**
	 * The operation id for the '<em>Get Leaf Nodes</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE___GET_LEAF_NODES__ABSTRACTNODE = ABSTRACT_NODE___GET_LEAF_NODES__ABSTRACTNODE;

	/**
	 * The operation id for the '<em>All Syntax Errors</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE___ALL_SYNTAX_ERRORS = ABSTRACT_NODE___ALL_SYNTAX_ERRORS;

	/**
	 * The operation id for the '<em>Total End Line</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE___TOTAL_END_LINE = ABSTRACT_NODE___TOTAL_END_LINE;

	/**
	 * The operation id for the '<em>Get Offset</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE___GET_OFFSET = ABSTRACT_NODE___GET_OFFSET;

	/**
	 * The operation id for the '<em>Get Line</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE___GET_LINE = ABSTRACT_NODE___GET_LINE;

	/**
	 * The operation id for the '<em>Get Length</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE___GET_LENGTH = ABSTRACT_NODE___GET_LENGTH;

	/**
	 * The operation id for the '<em>End Line</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE___END_LINE = ABSTRACT_NODE___END_LINE;

	/**
	 * The number of operations of the '<em>Composite Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE_OPERATION_COUNT = ABSTRACT_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link net.zehrer.no2.semantic.editor.model.impl.LeafNodeImpl <em>Leaf Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.zehrer.no2.semantic.editor.model.impl.LeafNodeImpl
	 * @see net.zehrer.no2.semantic.editor.model.impl.EditorPackageImpl#getLeafNode()
	 * @generated
	 */
	int LEAF_NODE = 2;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__PARENT = ABSTRACT_NODE__PARENT;

	/**
	 * The feature id for the '<em><b>Grammar Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__GRAMMAR_ELEMENT = ABSTRACT_NODE__GRAMMAR_ELEMENT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__ELEMENT = ABSTRACT_NODE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Syntax Error</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__SYNTAX_ERROR = ABSTRACT_NODE__SYNTAX_ERROR;

	/**
	 * The feature id for the '<em><b>Total Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__TOTAL_OFFSET = ABSTRACT_NODE__TOTAL_OFFSET;

	/**
	 * The feature id for the '<em><b>Total Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__TOTAL_LINE = ABSTRACT_NODE__TOTAL_LINE;

	/**
	 * The feature id for the '<em><b>Total Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__TOTAL_LENGTH = ABSTRACT_NODE__TOTAL_LENGTH;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__TEXT = ABSTRACT_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__FEATURE = ABSTRACT_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__HIDDEN = ABSTRACT_NODE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Leaf Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE_FEATURE_COUNT = ABSTRACT_NODE_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Serialize</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE___SERIALIZE = ABSTRACT_NODE___SERIALIZE;

	/**
	 * The operation id for the '<em>Get Leaf Nodes</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE___GET_LEAF_NODES = ABSTRACT_NODE___GET_LEAF_NODES;

	/**
	 * The operation id for the '<em>Get Leaf Nodes</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE___GET_LEAF_NODES__ABSTRACTNODE = ABSTRACT_NODE___GET_LEAF_NODES__ABSTRACTNODE;

	/**
	 * The operation id for the '<em>All Syntax Errors</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE___ALL_SYNTAX_ERRORS = ABSTRACT_NODE___ALL_SYNTAX_ERRORS;

	/**
	 * The operation id for the '<em>Total End Line</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE___TOTAL_END_LINE = ABSTRACT_NODE___TOTAL_END_LINE;

	/**
	 * The operation id for the '<em>Get Offset</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE___GET_OFFSET = ABSTRACT_NODE___GET_OFFSET;

	/**
	 * The operation id for the '<em>Get Line</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE___GET_LINE = ABSTRACT_NODE___GET_LINE;

	/**
	 * The operation id for the '<em>Get Length</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE___GET_LENGTH = ABSTRACT_NODE___GET_LENGTH;

	/**
	 * The operation id for the '<em>End Line</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE___END_LINE = ABSTRACT_NODE___END_LINE;

	/**
	 * The number of operations of the '<em>Leaf Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE_OPERATION_COUNT = ABSTRACT_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link net.zehrer.no2.semantic.editor.model.impl.SyntaxErrorImpl <em>Syntax Error</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.zehrer.no2.semantic.editor.model.impl.SyntaxErrorImpl
	 * @see net.zehrer.no2.semantic.editor.model.impl.EditorPackageImpl#getSyntaxError()
	 * @generated
	 */
	int SYNTAX_ERROR = 3;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAX_ERROR__MESSAGE = 0;

	/**
	 * The feature id for the '<em><b>Node</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAX_ERROR__NODE = 1;

	/**
	 * The feature id for the '<em><b>Issue Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAX_ERROR__ISSUE_CODE = 2;

	/**
	 * The number of structural features of the '<em>Syntax Error</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAX_ERROR_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Syntax Error</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAX_ERROR_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link net.zehrer.no2.semantic.editor.model.CompositeNode <em>Composite Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite Node</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.CompositeNode
	 * @generated
	 */
	EClass getCompositeNode();

	/**
	 * Returns the meta object for the containment reference list '{@link net.zehrer.no2.semantic.editor.model.CompositeNode#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.CompositeNode#getChildren()
	 * @see #getCompositeNode()
	 * @generated
	 */
	EReference getCompositeNode_Children();

	/**
	 * Returns the meta object for the reference list '{@link net.zehrer.no2.semantic.editor.model.CompositeNode#getLookaheadLeafNodes <em>Lookahead Leaf Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Lookahead Leaf Nodes</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.CompositeNode#getLookaheadLeafNodes()
	 * @see #getCompositeNode()
	 * @generated
	 */
	EReference getCompositeNode_LookaheadLeafNodes();

	/**
	 * Returns the meta object for class '{@link net.zehrer.no2.semantic.editor.model.AbstractNode <em>Abstract Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Node</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode
	 * @generated
	 */
	EClass getAbstractNode();

	/**
	 * Returns the meta object for the container reference '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#getParent()
	 * @see #getAbstractNode()
	 * @generated
	 */
	EReference getAbstractNode_Parent();

	/**
	 * Returns the meta object for the reference '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getGrammarElement <em>Grammar Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Grammar Element</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#getGrammarElement()
	 * @see #getAbstractNode()
	 * @generated
	 */
	EReference getAbstractNode_GrammarElement();

	/**
	 * Returns the meta object for the reference '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#getElement()
	 * @see #getAbstractNode()
	 * @generated
	 */
	EReference getAbstractNode_Element();

	/**
	 * Returns the meta object for the containment reference '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getSyntaxError <em>Syntax Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Syntax Error</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#getSyntaxError()
	 * @see #getAbstractNode()
	 * @generated
	 */
	EReference getAbstractNode_SyntaxError();

	/**
	 * Returns the meta object for the attribute '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getTotalOffset <em>Total Offset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Total Offset</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#getTotalOffset()
	 * @see #getAbstractNode()
	 * @generated
	 */
	EAttribute getAbstractNode_TotalOffset();

	/**
	 * Returns the meta object for the attribute '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getTotalLine <em>Total Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Total Line</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#getTotalLine()
	 * @see #getAbstractNode()
	 * @generated
	 */
	EAttribute getAbstractNode_TotalLine();

	/**
	 * Returns the meta object for the attribute '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getTotalLength <em>Total Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Total Length</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#getTotalLength()
	 * @see #getAbstractNode()
	 * @generated
	 */
	EAttribute getAbstractNode_TotalLength();

	/**
	 * Returns the meta object for the '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#serialize() <em>Serialize</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Serialize</em>' operation.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#serialize()
	 * @generated
	 */
	EOperation getAbstractNode__Serialize();

	/**
	 * Returns the meta object for the '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getLeafNodes() <em>Get Leaf Nodes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Leaf Nodes</em>' operation.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#getLeafNodes()
	 * @generated
	 */
	EOperation getAbstractNode__GetLeafNodes();

	/**
	 * Returns the meta object for the '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getLeafNodes(net.zehrer.no2.semantic.editor.model.AbstractNode) <em>Get Leaf Nodes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Leaf Nodes</em>' operation.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#getLeafNodes(net.zehrer.no2.semantic.editor.model.AbstractNode)
	 * @generated
	 */
	EOperation getAbstractNode__GetLeafNodes__AbstractNode();

	/**
	 * Returns the meta object for the '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#allSyntaxErrors() <em>All Syntax Errors</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>All Syntax Errors</em>' operation.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#allSyntaxErrors()
	 * @generated
	 */
	EOperation getAbstractNode__AllSyntaxErrors();

	/**
	 * Returns the meta object for the '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#totalEndLine() <em>Total End Line</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Total End Line</em>' operation.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#totalEndLine()
	 * @generated
	 */
	EOperation getAbstractNode__TotalEndLine();

	/**
	 * Returns the meta object for the '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getOffset() <em>Get Offset</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Offset</em>' operation.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#getOffset()
	 * @generated
	 */
	EOperation getAbstractNode__GetOffset();

	/**
	 * Returns the meta object for the '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getLine() <em>Get Line</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Line</em>' operation.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#getLine()
	 * @generated
	 */
	EOperation getAbstractNode__GetLine();

	/**
	 * Returns the meta object for the '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getLength() <em>Get Length</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Length</em>' operation.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#getLength()
	 * @generated
	 */
	EOperation getAbstractNode__GetLength();

	/**
	 * Returns the meta object for the '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#endLine() <em>End Line</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>End Line</em>' operation.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#endLine()
	 * @generated
	 */
	EOperation getAbstractNode__EndLine();

	/**
	 * Returns the meta object for class '{@link net.zehrer.no2.semantic.editor.model.LeafNode <em>Leaf Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Leaf Node</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.LeafNode
	 * @generated
	 */
	EClass getLeafNode();

	/**
	 * Returns the meta object for the attribute '{@link net.zehrer.no2.semantic.editor.model.LeafNode#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.LeafNode#getText()
	 * @see #getLeafNode()
	 * @generated
	 */
	EAttribute getLeafNode_Text();

	/**
	 * Returns the meta object for the attribute '{@link net.zehrer.no2.semantic.editor.model.LeafNode#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feature</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.LeafNode#getFeature()
	 * @see #getLeafNode()
	 * @generated
	 */
	EAttribute getLeafNode_Feature();

	/**
	 * Returns the meta object for the attribute '{@link net.zehrer.no2.semantic.editor.model.LeafNode#isHidden <em>Hidden</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hidden</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.LeafNode#isHidden()
	 * @see #getLeafNode()
	 * @generated
	 */
	EAttribute getLeafNode_Hidden();

	/**
	 * Returns the meta object for class '{@link net.zehrer.no2.semantic.editor.model.SyntaxError <em>Syntax Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Syntax Error</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.SyntaxError
	 * @generated
	 */
	EClass getSyntaxError();

	/**
	 * Returns the meta object for the attribute '{@link net.zehrer.no2.semantic.editor.model.SyntaxError#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.SyntaxError#getMessage()
	 * @see #getSyntaxError()
	 * @generated
	 */
	EAttribute getSyntaxError_Message();

	/**
	 * Returns the meta object for the container reference '{@link net.zehrer.no2.semantic.editor.model.SyntaxError#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Node</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.SyntaxError#getNode()
	 * @see #getSyntaxError()
	 * @generated
	 */
	EReference getSyntaxError_Node();

	/**
	 * Returns the meta object for the attribute '{@link net.zehrer.no2.semantic.editor.model.SyntaxError#getIssueCode <em>Issue Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Issue Code</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.SyntaxError#getIssueCode()
	 * @see #getSyntaxError()
	 * @generated
	 */
	EAttribute getSyntaxError_IssueCode();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EditorFactory getEditorFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link net.zehrer.no2.semantic.editor.model.impl.CompositeNodeImpl <em>Composite Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.zehrer.no2.semantic.editor.model.impl.CompositeNodeImpl
		 * @see net.zehrer.no2.semantic.editor.model.impl.EditorPackageImpl#getCompositeNode()
		 * @generated
		 */
		EClass COMPOSITE_NODE = eINSTANCE.getCompositeNode();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_NODE__CHILDREN = eINSTANCE.getCompositeNode_Children();

		/**
		 * The meta object literal for the '<em><b>Lookahead Leaf Nodes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_NODE__LOOKAHEAD_LEAF_NODES = eINSTANCE.getCompositeNode_LookaheadLeafNodes();

		/**
		 * The meta object literal for the '{@link net.zehrer.no2.semantic.editor.model.impl.AbstractNodeImpl <em>Abstract Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.zehrer.no2.semantic.editor.model.impl.AbstractNodeImpl
		 * @see net.zehrer.no2.semantic.editor.model.impl.EditorPackageImpl#getAbstractNode()
		 * @generated
		 */
		EClass ABSTRACT_NODE = eINSTANCE.getAbstractNode();

		/**
		 * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_NODE__PARENT = eINSTANCE.getAbstractNode_Parent();

		/**
		 * The meta object literal for the '<em><b>Grammar Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_NODE__GRAMMAR_ELEMENT = eINSTANCE.getAbstractNode_GrammarElement();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_NODE__ELEMENT = eINSTANCE.getAbstractNode_Element();

		/**
		 * The meta object literal for the '<em><b>Syntax Error</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_NODE__SYNTAX_ERROR = eINSTANCE.getAbstractNode_SyntaxError();

		/**
		 * The meta object literal for the '<em><b>Total Offset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_NODE__TOTAL_OFFSET = eINSTANCE.getAbstractNode_TotalOffset();

		/**
		 * The meta object literal for the '<em><b>Total Line</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_NODE__TOTAL_LINE = eINSTANCE.getAbstractNode_TotalLine();

		/**
		 * The meta object literal for the '<em><b>Total Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_NODE__TOTAL_LENGTH = eINSTANCE.getAbstractNode_TotalLength();

		/**
		 * The meta object literal for the '<em><b>Serialize</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ABSTRACT_NODE___SERIALIZE = eINSTANCE.getAbstractNode__Serialize();

		/**
		 * The meta object literal for the '<em><b>Get Leaf Nodes</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ABSTRACT_NODE___GET_LEAF_NODES = eINSTANCE.getAbstractNode__GetLeafNodes();

		/**
		 * The meta object literal for the '<em><b>Get Leaf Nodes</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ABSTRACT_NODE___GET_LEAF_NODES__ABSTRACTNODE = eINSTANCE.getAbstractNode__GetLeafNodes__AbstractNode();

		/**
		 * The meta object literal for the '<em><b>All Syntax Errors</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ABSTRACT_NODE___ALL_SYNTAX_ERRORS = eINSTANCE.getAbstractNode__AllSyntaxErrors();

		/**
		 * The meta object literal for the '<em><b>Total End Line</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ABSTRACT_NODE___TOTAL_END_LINE = eINSTANCE.getAbstractNode__TotalEndLine();

		/**
		 * The meta object literal for the '<em><b>Get Offset</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ABSTRACT_NODE___GET_OFFSET = eINSTANCE.getAbstractNode__GetOffset();

		/**
		 * The meta object literal for the '<em><b>Get Line</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ABSTRACT_NODE___GET_LINE = eINSTANCE.getAbstractNode__GetLine();

		/**
		 * The meta object literal for the '<em><b>Get Length</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ABSTRACT_NODE___GET_LENGTH = eINSTANCE.getAbstractNode__GetLength();

		/**
		 * The meta object literal for the '<em><b>End Line</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ABSTRACT_NODE___END_LINE = eINSTANCE.getAbstractNode__EndLine();

		/**
		 * The meta object literal for the '{@link net.zehrer.no2.semantic.editor.model.impl.LeafNodeImpl <em>Leaf Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.zehrer.no2.semantic.editor.model.impl.LeafNodeImpl
		 * @see net.zehrer.no2.semantic.editor.model.impl.EditorPackageImpl#getLeafNode()
		 * @generated
		 */
		EClass LEAF_NODE = eINSTANCE.getLeafNode();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LEAF_NODE__TEXT = eINSTANCE.getLeafNode_Text();

		/**
		 * The meta object literal for the '<em><b>Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LEAF_NODE__FEATURE = eINSTANCE.getLeafNode_Feature();

		/**
		 * The meta object literal for the '<em><b>Hidden</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LEAF_NODE__HIDDEN = eINSTANCE.getLeafNode_Hidden();

		/**
		 * The meta object literal for the '{@link net.zehrer.no2.semantic.editor.model.impl.SyntaxErrorImpl <em>Syntax Error</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.zehrer.no2.semantic.editor.model.impl.SyntaxErrorImpl
		 * @see net.zehrer.no2.semantic.editor.model.impl.EditorPackageImpl#getSyntaxError()
		 * @generated
		 */
		EClass SYNTAX_ERROR = eINSTANCE.getSyntaxError();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYNTAX_ERROR__MESSAGE = eINSTANCE.getSyntaxError_Message();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYNTAX_ERROR__NODE = eINSTANCE.getSyntaxError_Node();

		/**
		 * The meta object literal for the '<em><b>Issue Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYNTAX_ERROR__ISSUE_CODE = eINSTANCE.getSyntaxError_IssueCode();

	}

} //EditorPackage
