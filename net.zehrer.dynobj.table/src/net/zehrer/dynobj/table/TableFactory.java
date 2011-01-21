/**
 * <copyright>
 (c) Stephan Zehrer 2010 
 * </copyright>
 *
 * $Id$
 */
package net.zehrer.dynobj.table;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see net.zehrer.dynobj.table.TablePackage
 * @generated
 */
public interface TableFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TableFactory eINSTANCE = net.zehrer.dynobj.table.impl.TableFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Tabel View</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tabel View</em>'.
	 * @generated
	 */
	TabelView createTabelView();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TablePackage getTablePackage();

} //TableFactory
