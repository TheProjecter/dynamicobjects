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
 * @see net.zehrer.common.interval.intervalPackage
 * @generated
 */
public interface intervalFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	intervalFactory eINSTANCE = net.zehrer.common.interval.impl.intervalFactoryImpl.init();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	intervalPackage getintervalPackage();

} //intervalFactory
