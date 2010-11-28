/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.zehrer.no2.semantic.editor.model;

import net.zehrer.common.interval.IntervalPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
	 * The feature id for the '<em><b>Lower Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE__LOWER_LIMIT = IntervalPackage.EINT_INTERVAL__LOWER_LIMIT;

	/**
	 * The feature id for the '<em><b>Upper Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE__UPPER_LIMIT = IntervalPackage.EINT_INTERVAL__UPPER_LIMIT;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE__PARENT = IntervalPackage.EINT_INTERVAL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE__ELEMENT = IntervalPackage.EINT_INTERVAL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Syntax Error</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE__SYNTAX_ERROR = IntervalPackage.EINT_INTERVAL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE__OFFSET = IntervalPackage.EINT_INTERVAL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Total Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE__TOTAL_LINE = IntervalPackage.EINT_INTERVAL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE__LENGTH = IntervalPackage.EINT_INTERVAL_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE__NAME = IntervalPackage.EINT_INTERVAL_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Abstract Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_NODE_FEATURE_COUNT = IntervalPackage.EINT_INTERVAL_FEATURE_COUNT + 7;

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
	 * The feature id for the '<em><b>Lower Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__LOWER_LIMIT = ABSTRACT_NODE__LOWER_LIMIT;

	/**
	 * The feature id for the '<em><b>Upper Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__UPPER_LIMIT = ABSTRACT_NODE__UPPER_LIMIT;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__PARENT = ABSTRACT_NODE__PARENT;

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
	 * The feature id for the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__OFFSET = ABSTRACT_NODE__OFFSET;

	/**
	 * The feature id for the '<em><b>Total Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__TOTAL_LINE = ABSTRACT_NODE__TOTAL_LINE;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__LENGTH = ABSTRACT_NODE__LENGTH;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__NAME = ABSTRACT_NODE__NAME;

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
	 * The meta object id for the '{@link net.zehrer.no2.semantic.editor.model.impl.LeafNodeImpl <em>Leaf Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.zehrer.no2.semantic.editor.model.impl.LeafNodeImpl
	 * @see net.zehrer.no2.semantic.editor.model.impl.EditorPackageImpl#getLeafNode()
	 * @generated
	 */
	int LEAF_NODE = 2;

	/**
	 * The feature id for the '<em><b>Lower Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__LOWER_LIMIT = ABSTRACT_NODE__LOWER_LIMIT;

	/**
	 * The feature id for the '<em><b>Upper Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__UPPER_LIMIT = ABSTRACT_NODE__UPPER_LIMIT;

	/**
	 * The feature id for the '<em><b>Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__PARENT = ABSTRACT_NODE__PARENT;

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
	 * The feature id for the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__OFFSET = ABSTRACT_NODE__OFFSET;

	/**
	 * The feature id for the '<em><b>Total Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__TOTAL_LINE = ABSTRACT_NODE__TOTAL_LINE;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__LENGTH = ABSTRACT_NODE__LENGTH;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__NAME = ABSTRACT_NODE__NAME;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE__TEXT = ABSTRACT_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Leaf Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEAF_NODE_FEATURE_COUNT = ABSTRACT_NODE_FEATURE_COUNT + 1;

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
	 * The meta object id for the '<em>Document Event</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jface.text.DocumentEvent
	 * @see net.zehrer.no2.semantic.editor.model.impl.EditorPackageImpl#getDocumentEvent()
	 * @generated
	 */
	int DOCUMENT_EVENT = 4;


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
	 * Returns the meta object for the attribute '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getOffset <em>Offset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Offset</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#getOffset()
	 * @see #getAbstractNode()
	 * @generated
	 */
	EAttribute getAbstractNode_Offset();

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
	 * Returns the meta object for the attribute '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#getLength()
	 * @see #getAbstractNode()
	 * @generated
	 */
	EAttribute getAbstractNode_Length();

	/**
	 * Returns the meta object for the attribute '{@link net.zehrer.no2.semantic.editor.model.AbstractNode#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see net.zehrer.no2.semantic.editor.model.AbstractNode#getName()
	 * @see #getAbstractNode()
	 * @generated
	 */
	EAttribute getAbstractNode_Name();

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
	 * Returns the meta object for data type '{@link org.eclipse.jface.text.DocumentEvent <em>Document Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Document Event</em>'.
	 * @see org.eclipse.jface.text.DocumentEvent
	 * @model instanceClass="org.eclipse.jface.text.DocumentEvent" serializeable="false"
	 * @generated
	 */
	EDataType getDocumentEvent();

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
		 * The meta object literal for the '<em><b>Offset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_NODE__OFFSET = eINSTANCE.getAbstractNode_Offset();

		/**
		 * The meta object literal for the '<em><b>Total Line</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_NODE__TOTAL_LINE = eINSTANCE.getAbstractNode_TotalLine();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_NODE__LENGTH = eINSTANCE.getAbstractNode_Length();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_NODE__NAME = eINSTANCE.getAbstractNode_Name();

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

		/**
		 * The meta object literal for the '<em>Document Event</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jface.text.DocumentEvent
		 * @see net.zehrer.no2.semantic.editor.model.impl.EditorPackageImpl#getDocumentEvent()
		 * @generated
		 */
		EDataType DOCUMENT_EVENT = eINSTANCE.getDocumentEvent();

	}

} //EditorPackage
