/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.zehrer.no2.semantic.editor.model.impl;

import java.lang.reflect.InvocationTargetException;

import net.zehrer.no2.semantic.editor.model.AbstractNode;
import net.zehrer.no2.semantic.editor.model.CompositeNode;
import net.zehrer.no2.semantic.editor.model.EditorPackage;
import net.zehrer.no2.semantic.editor.model.LeafNode;
import net.zehrer.no2.semantic.editor.model.SyntaxError;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.text.DocumentEvent;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.impl.AbstractNodeImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.impl.AbstractNodeImpl#getGrammarElement <em>Grammar Element</em>}</li>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.impl.AbstractNodeImpl#getElement <em>Element</em>}</li>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.impl.AbstractNodeImpl#getSyntaxError <em>Syntax Error</em>}</li>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.impl.AbstractNodeImpl#getTotalOffset <em>Total Offset</em>}</li>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.impl.AbstractNodeImpl#getTotalLine <em>Total Line</em>}</li>
 *   <li>{@link net.zehrer.no2.semantic.editor.model.impl.AbstractNodeImpl#getTotalLength <em>Total Length</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractNodeImpl extends MinimalEObjectImpl.Container implements AbstractNode {
	/**
	 * The cached value of the '{@link #getGrammarElement() <em>Grammar Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGrammarElement()
	 * @generated
	 * @ordered
	 */
	protected EObject grammarElement;

	/**
	 * The cached value of the '{@link #getElement() <em>Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElement()
	 * @generated
	 * @ordered
	 */
	protected EObject element;

	/**
	 * The cached value of the '{@link #getSyntaxError() <em>Syntax Error</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSyntaxError()
	 * @generated
	 * @ordered
	 */
	protected SyntaxError syntaxError;

	/**
	 * The default value of the '{@link #getTotalOffset() <em>Total Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalOffset()
	 * @generated
	 * @ordered
	 */
	protected static final int TOTAL_OFFSET_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTotalOffset() <em>Total Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalOffset()
	 * @generated
	 * @ordered
	 */
	protected int totalOffset = TOTAL_OFFSET_EDEFAULT;

	/**
	 * The default value of the '{@link #getTotalLine() <em>Total Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalLine()
	 * @generated
	 * @ordered
	 */
	protected static final int TOTAL_LINE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getTotalLine() <em>Total Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalLine()
	 * @generated
	 * @ordered
	 */
	protected int totalLine = TOTAL_LINE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTotalLength() <em>Total Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalLength()
	 * @generated
	 * @ordered
	 */
	protected static final int TOTAL_LENGTH_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getTotalLength() <em>Total Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalLength()
	 * @generated
	 * @ordered
	 */
	protected int totalLength = TOTAL_LENGTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EditorPackage.Literals.ABSTRACT_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompositeNode getParent() {
		if (eContainerFeatureID() != EditorPackage.ABSTRACT_NODE__PARENT) return null;
		return (CompositeNode)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParent(CompositeNode newParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParent, EditorPackage.ABSTRACT_NODE__PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParent(CompositeNode newParent) {
		if (newParent != eInternalContainer() || (eContainerFeatureID() != EditorPackage.ABSTRACT_NODE__PARENT && newParent != null)) {
			if (EcoreUtil.isAncestor(this, newParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParent != null)
				msgs = ((InternalEObject)newParent).eInverseAdd(this, EditorPackage.COMPOSITE_NODE__CHILDREN, CompositeNode.class, msgs);
			msgs = basicSetParent(newParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditorPackage.ABSTRACT_NODE__PARENT, newParent, newParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getGrammarElement() {
		if (grammarElement != null && grammarElement.eIsProxy()) {
			InternalEObject oldGrammarElement = (InternalEObject)grammarElement;
			grammarElement = eResolveProxy(oldGrammarElement);
			if (grammarElement != oldGrammarElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EditorPackage.ABSTRACT_NODE__GRAMMAR_ELEMENT, oldGrammarElement, grammarElement));
			}
		}
		return grammarElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetGrammarElement() {
		return grammarElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGrammarElement(EObject newGrammarElement) {
		EObject oldGrammarElement = grammarElement;
		grammarElement = newGrammarElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditorPackage.ABSTRACT_NODE__GRAMMAR_ELEMENT, oldGrammarElement, grammarElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getElement() {
		if (element != null && element.eIsProxy()) {
			InternalEObject oldElement = (InternalEObject)element;
			element = eResolveProxy(oldElement);
			if (element != oldElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EditorPackage.ABSTRACT_NODE__ELEMENT, oldElement, element));
			}
		}
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject basicGetElement() {
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElement(EObject newElement) {
		EObject oldElement = element;
		element = newElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditorPackage.ABSTRACT_NODE__ELEMENT, oldElement, element));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SyntaxError getSyntaxError() {
		return syntaxError;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSyntaxError(SyntaxError newSyntaxError, NotificationChain msgs) {
		SyntaxError oldSyntaxError = syntaxError;
		syntaxError = newSyntaxError;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EditorPackage.ABSTRACT_NODE__SYNTAX_ERROR, oldSyntaxError, newSyntaxError);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSyntaxError(SyntaxError newSyntaxError) {
		if (newSyntaxError != syntaxError) {
			NotificationChain msgs = null;
			if (syntaxError != null)
				msgs = ((InternalEObject)syntaxError).eInverseRemove(this, EditorPackage.SYNTAX_ERROR__NODE, SyntaxError.class, msgs);
			if (newSyntaxError != null)
				msgs = ((InternalEObject)newSyntaxError).eInverseAdd(this, EditorPackage.SYNTAX_ERROR__NODE, SyntaxError.class, msgs);
			msgs = basicSetSyntaxError(newSyntaxError, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditorPackage.ABSTRACT_NODE__SYNTAX_ERROR, newSyntaxError, newSyntaxError));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTotalOffset() {
		return totalOffset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTotalOffset(int newTotalOffset) {
		int oldTotalOffset = totalOffset;
		totalOffset = newTotalOffset;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditorPackage.ABSTRACT_NODE__TOTAL_OFFSET, oldTotalOffset, totalOffset));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTotalLine() {
		return totalLine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTotalLine(int newTotalLine) {
		int oldTotalLine = totalLine;
		totalLine = newTotalLine;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditorPackage.ABSTRACT_NODE__TOTAL_LINE, oldTotalLine, totalLine));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTotalLength() {
		return totalLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTotalLength(int newTotalLength) {
		int oldTotalLength = totalLength;
		totalLength = newTotalLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EditorPackage.ABSTRACT_NODE__TOTAL_LENGTH, oldTotalLength, totalLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String serialize() {
		return TextModelUtil.serialize(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LeafNode> getLeafNodes() {
		return TextModelUtil.getLeafNodes(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LeafNode> getLeafNodes(AbstractNode to) {
		return TextModelUtil.getLeafNodes(this, to);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SyntaxError> allSyntaxErrors() {
		if (this instanceof CompositeNodeImpl) { 
		return TextModelUtil.allSyntaxErrors((CompositeNodeImpl) this);
		} else if (this instanceof LeafNodeImpl) { 
		return TextModelUtil.allSyntaxErrors((LeafNodeImpl) this);
		} else {
		return TextModelUtil.allSyntaxErrors((AbstractNodeImpl) this);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int totalEndLine() {
		return TextModelUtil.totalEndLine(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getOffset() {
		return TextModelUtil.getOffset(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLine() {
		return TextModelUtil.getLine(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLength() {
		return TextModelUtil.getLength(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int endLine() {
		return TextModelUtil.endLine(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void update(DocumentEvent event) {
		TextModelUtil.update(this, event);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EditorPackage.ABSTRACT_NODE__PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParent((CompositeNode)otherEnd, msgs);
			case EditorPackage.ABSTRACT_NODE__SYNTAX_ERROR:
				if (syntaxError != null)
					msgs = ((InternalEObject)syntaxError).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EditorPackage.ABSTRACT_NODE__SYNTAX_ERROR, null, msgs);
				return basicSetSyntaxError((SyntaxError)otherEnd, msgs);
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
			case EditorPackage.ABSTRACT_NODE__PARENT:
				return basicSetParent(null, msgs);
			case EditorPackage.ABSTRACT_NODE__SYNTAX_ERROR:
				return basicSetSyntaxError(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case EditorPackage.ABSTRACT_NODE__PARENT:
				return eInternalContainer().eInverseRemove(this, EditorPackage.COMPOSITE_NODE__CHILDREN, CompositeNode.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EditorPackage.ABSTRACT_NODE__PARENT:
				return getParent();
			case EditorPackage.ABSTRACT_NODE__GRAMMAR_ELEMENT:
				if (resolve) return getGrammarElement();
				return basicGetGrammarElement();
			case EditorPackage.ABSTRACT_NODE__ELEMENT:
				if (resolve) return getElement();
				return basicGetElement();
			case EditorPackage.ABSTRACT_NODE__SYNTAX_ERROR:
				return getSyntaxError();
			case EditorPackage.ABSTRACT_NODE__TOTAL_OFFSET:
				return getTotalOffset();
			case EditorPackage.ABSTRACT_NODE__TOTAL_LINE:
				return getTotalLine();
			case EditorPackage.ABSTRACT_NODE__TOTAL_LENGTH:
				return getTotalLength();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EditorPackage.ABSTRACT_NODE__PARENT:
				setParent((CompositeNode)newValue);
				return;
			case EditorPackage.ABSTRACT_NODE__GRAMMAR_ELEMENT:
				setGrammarElement((EObject)newValue);
				return;
			case EditorPackage.ABSTRACT_NODE__ELEMENT:
				setElement((EObject)newValue);
				return;
			case EditorPackage.ABSTRACT_NODE__SYNTAX_ERROR:
				setSyntaxError((SyntaxError)newValue);
				return;
			case EditorPackage.ABSTRACT_NODE__TOTAL_OFFSET:
				setTotalOffset((Integer)newValue);
				return;
			case EditorPackage.ABSTRACT_NODE__TOTAL_LINE:
				setTotalLine((Integer)newValue);
				return;
			case EditorPackage.ABSTRACT_NODE__TOTAL_LENGTH:
				setTotalLength((Integer)newValue);
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
			case EditorPackage.ABSTRACT_NODE__PARENT:
				setParent((CompositeNode)null);
				return;
			case EditorPackage.ABSTRACT_NODE__GRAMMAR_ELEMENT:
				setGrammarElement((EObject)null);
				return;
			case EditorPackage.ABSTRACT_NODE__ELEMENT:
				setElement((EObject)null);
				return;
			case EditorPackage.ABSTRACT_NODE__SYNTAX_ERROR:
				setSyntaxError((SyntaxError)null);
				return;
			case EditorPackage.ABSTRACT_NODE__TOTAL_OFFSET:
				setTotalOffset(TOTAL_OFFSET_EDEFAULT);
				return;
			case EditorPackage.ABSTRACT_NODE__TOTAL_LINE:
				setTotalLine(TOTAL_LINE_EDEFAULT);
				return;
			case EditorPackage.ABSTRACT_NODE__TOTAL_LENGTH:
				setTotalLength(TOTAL_LENGTH_EDEFAULT);
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
			case EditorPackage.ABSTRACT_NODE__PARENT:
				return getParent() != null;
			case EditorPackage.ABSTRACT_NODE__GRAMMAR_ELEMENT:
				return grammarElement != null;
			case EditorPackage.ABSTRACT_NODE__ELEMENT:
				return element != null;
			case EditorPackage.ABSTRACT_NODE__SYNTAX_ERROR:
				return syntaxError != null;
			case EditorPackage.ABSTRACT_NODE__TOTAL_OFFSET:
				return totalOffset != TOTAL_OFFSET_EDEFAULT;
			case EditorPackage.ABSTRACT_NODE__TOTAL_LINE:
				return totalLine != TOTAL_LINE_EDEFAULT;
			case EditorPackage.ABSTRACT_NODE__TOTAL_LENGTH:
				return totalLength != TOTAL_LENGTH_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case EditorPackage.ABSTRACT_NODE___SERIALIZE:
				return serialize();
			case EditorPackage.ABSTRACT_NODE___GET_LEAF_NODES:
				return getLeafNodes();
			case EditorPackage.ABSTRACT_NODE___GET_LEAF_NODES__ABSTRACTNODE:
				return getLeafNodes((AbstractNode)arguments.get(0));
			case EditorPackage.ABSTRACT_NODE___ALL_SYNTAX_ERRORS:
				return allSyntaxErrors();
			case EditorPackage.ABSTRACT_NODE___TOTAL_END_LINE:
				return totalEndLine();
			case EditorPackage.ABSTRACT_NODE___GET_OFFSET:
				return getOffset();
			case EditorPackage.ABSTRACT_NODE___GET_LINE:
				return getLine();
			case EditorPackage.ABSTRACT_NODE___GET_LENGTH:
				return getLength();
			case EditorPackage.ABSTRACT_NODE___END_LINE:
				return endLine();
			case EditorPackage.ABSTRACT_NODE___UPDATE__DOCUMENTEVENT:
				update((DocumentEvent)arguments.get(0));
				return null;
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (totalOffset: ");
		result.append(totalOffset);
		result.append(", totalLine: ");
		result.append(totalLine);
		result.append(", totalLength: ");
		result.append(totalLength);
		result.append(')');
		return result.toString();
	}

} //AbstractNodeImpl
