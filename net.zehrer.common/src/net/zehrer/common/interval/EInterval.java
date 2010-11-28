/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.zehrer.common.interval;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EInterval</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.zehrer.common.interval.EInterval#getLowerLimit <em>Lower Limit</em>}</li>
 *   <li>{@link net.zehrer.common.interval.EInterval#getUpperLimit <em>Upper Limit</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.zehrer.common.interval.IntervalPackage#getEInterval()
 * @model abstract="true" superTypes="lang.IComparable<net.zehrer.common.interval.EInterval<T>>" TBounds="lang.IComparable<T>"
 * @generated
 */
public interface EInterval<T extends Comparable<T>> extends EObject, Comparable<EInterval<T>> {
	/**
	 * Returns the value of the '<em><b>Lower Limit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lower Limit</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lower Limit</em>' reference.
	 * @see #setLowerLimit(Comparable)
	 * @see net.zehrer.common.interval.IntervalPackage#getEInterval_LowerLimit()
	 * @model required="true"
	 * @generated
	 */
	T getLowerLimit();

	/**
	 * Sets the value of the '{@link net.zehrer.common.interval.EInterval#getLowerLimit <em>Lower Limit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower Limit</em>' reference.
	 * @see #getLowerLimit()
	 * @generated
	 */
	void setLowerLimit(T value);

	/**
	 * Returns the value of the '<em><b>Upper Limit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upper Limit</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Upper Limit</em>' reference.
	 * @see #setUpperLimit(Comparable)
	 * @see net.zehrer.common.interval.IntervalPackage#getEInterval_UpperLimit()
	 * @model required="true"
	 * @generated
	 */
	T getUpperLimit();

	/**
	 * Sets the value of the '{@link net.zehrer.common.interval.EInterval#getUpperLimit <em>Upper Limit</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Limit</em>' reference.
	 * @see #getUpperLimit()
	 * @generated
	 */
	void setUpperLimit(T value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean intersects(EInterval<T> other);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	EInterval<T> intersect(EInterval<T> other);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean isBelow(T value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean isAbove(T value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean equals(Object other);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean equals(EInterval<T> other);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	int hashCode();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	boolean includes(T value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
//	int compareTo(EInterval<T> other);

} // EInterval
