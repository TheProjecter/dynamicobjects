/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.zehrer.common.interval;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see net.zehrer.common.interval.intervalFactory
 * @model kind="package"
 * @generated
 */
public interface intervalPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "interval";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://zehrer.net/common/interval/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	intervalPackage eINSTANCE = net.zehrer.common.interval.impl.intervalPackageImpl.init();

	/**
	 * The meta object id for the '{@link java.lang.Comparable <em>IComparable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Comparable
	 * @see net.zehrer.common.interval.impl.intervalPackageImpl#getIComparable()
	 * @generated
	 */
	int ICOMPARABLE = 1;

	/**
	 * The number of structural features of the '<em>IComparable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICOMPARABLE_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link net.zehrer.common.interval.impl.EIntervalImpl <em>EInterval</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.zehrer.common.interval.impl.EIntervalImpl
	 * @see net.zehrer.common.interval.impl.intervalPackageImpl#getEInterval()
	 * @generated
	 */
	int EINTERVAL = 0;

	/**
	 * The feature id for the '<em><b>Lower Limit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EINTERVAL__LOWER_LIMIT = ICOMPARABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Upper Limit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EINTERVAL__UPPER_LIMIT = ICOMPARABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>EInterval</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EINTERVAL_FEATURE_COUNT = ICOMPARABLE_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link net.zehrer.common.interval.EInterval <em>EInterval</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EInterval</em>'.
	 * @see net.zehrer.common.interval.EInterval
	 * @generated
	 */
	EClass getEInterval();

	/**
	 * Returns the meta object for the reference '{@link net.zehrer.common.interval.EInterval#getLowerLimit <em>Lower Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Lower Limit</em>'.
	 * @see net.zehrer.common.interval.EInterval#getLowerLimit()
	 * @see #getEInterval()
	 * @generated
	 */
	EReference getEInterval_LowerLimit();

	/**
	 * Returns the meta object for the reference '{@link net.zehrer.common.interval.EInterval#getUpperLimit <em>Upper Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Upper Limit</em>'.
	 * @see net.zehrer.common.interval.EInterval#getUpperLimit()
	 * @see #getEInterval()
	 * @generated
	 */
	EReference getEInterval_UpperLimit();

	/**
	 * Returns the meta object for class '{@link java.lang.Comparable <em>IComparable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IComparable</em>'.
	 * @see java.lang.Comparable
	 * @model instanceClass="java.lang.Comparable" typeParameters="T"
	 * @generated
	 */
	EClass getIComparable();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	intervalFactory getintervalFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link net.zehrer.common.interval.impl.EIntervalImpl <em>EInterval</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.zehrer.common.interval.impl.EIntervalImpl
		 * @see net.zehrer.common.interval.impl.intervalPackageImpl#getEInterval()
		 * @generated
		 */
		EClass EINTERVAL = eINSTANCE.getEInterval();

		/**
		 * The meta object literal for the '<em><b>Lower Limit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EINTERVAL__LOWER_LIMIT = eINSTANCE.getEInterval_LowerLimit();

		/**
		 * The meta object literal for the '<em><b>Upper Limit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EINTERVAL__UPPER_LIMIT = eINSTANCE.getEInterval_UpperLimit();

		/**
		 * The meta object literal for the '{@link java.lang.Comparable <em>IComparable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Comparable
		 * @see net.zehrer.common.interval.impl.intervalPackageImpl#getIComparable()
		 * @generated
		 */
		EClass ICOMPARABLE = eINSTANCE.getIComparable();

	}

} //intervalPackage
