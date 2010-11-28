/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.zehrer.common.interval;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see net.zehrer.common.interval.IntervalPackage
 * @generated
 */
public interface IntervalFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IntervalFactory eINSTANCE = net.zehrer.common.interval.impl.IntervalFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>EInt Interval</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>EInt Interval</em>'.
	 * @generated
	 */
	EIntInterval createEIntInterval();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	IntervalPackage getIntervalPackage();

} //IntervalFactory
