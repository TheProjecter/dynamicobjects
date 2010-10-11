/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.zehrer.no2.semantic.editor.model.impl;

import java.util.Collection;

import net.zehrer.no2.semantic.editor.model.AbstractNode;
import net.zehrer.no2.semantic.editor.model.CompositeNode;
import net.zehrer.no2.semantic.editor.model.EditorPackage;
import net.zehrer.no2.semantic.editor.model.LeafNode;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Composite Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.impl.CompositeNodeImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.impl.CompositeNodeImpl#getLookaheadLeafNodes <em>Lookahead Leaf Nodes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompositeNodeImpl extends AbstractNodeImpl implements CompositeNode {
	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<AbstractNode> children;

	/**
	 * The cached value of the '{@link #getLookaheadLeafNodes() <em>Lookahead Leaf Nodes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLookaheadLeafNodes()
	 * @generated
	 * @ordered
	 */
	protected EList<LeafNode> lookaheadLeafNodes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompositeNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EditorPackage.Literals.COMPOSITE_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbstractNode> getChildren() {
		if (children == null) {
			children = new EObjectContainmentWithInverseEList<AbstractNode>(AbstractNode.class, this, EditorPackage.COMPOSITE_NODE__CHILDREN, EditorPackage.ABSTRACT_NODE__PARENT);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LeafNode> getLookaheadLeafNodes() {
		if (lookaheadLeafNodes == null) {
			lookaheadLeafNodes = new EObjectResolvingEList<LeafNode>(LeafNode.class, this, EditorPackage.COMPOSITE_NODE__LOOKAHEAD_LEAF_NODES);
		}
		return lookaheadLeafNodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EditorPackage.COMPOSITE_NODE__CHILDREN:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EditorPackage.COMPOSITE_NODE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EditorPackage.COMPOSITE_NODE__CHILDREN:
				return getChildren();
			case EditorPackage.COMPOSITE_NODE__LOOKAHEAD_LEAF_NODES:
				return getLookaheadLeafNodes();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EditorPackage.COMPOSITE_NODE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends AbstractNode>)newValue);
				return;
			case EditorPackage.COMPOSITE_NODE__LOOKAHEAD_LEAF_NODES:
				getLookaheadLeafNodes().clear();
				getLookaheadLeafNodes().addAll((Collection<? extends LeafNode>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case EditorPackage.COMPOSITE_NODE__CHILDREN:
				getChildren().clear();
				return;
			case EditorPackage.COMPOSITE_NODE__LOOKAHEAD_LEAF_NODES:
				getLookaheadLeafNodes().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EditorPackage.COMPOSITE_NODE__CHILDREN:
				return children != null && !children.isEmpty();
			case EditorPackage.COMPOSITE_NODE__LOOKAHEAD_LEAF_NODES:
				return lookaheadLeafNodes != null && !lookaheadLeafNodes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //CompositeNodeImpl
