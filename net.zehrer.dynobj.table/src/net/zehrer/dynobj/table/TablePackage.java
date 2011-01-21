/**
 * <copyright>
 (c) Stephan Zehrer 2010 
 * </copyright>
 *
 * $Id$
 */
package net.zehrer.dynobj.table;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
 * @see net.zehrer.dynobj.table.TableFactory
 * @model kind="package"
 * @generated
 */
public interface TablePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "table";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://zehrer.net/dynobj/table";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dyntab";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TablePackage eINSTANCE = net.zehrer.dynobj.table.impl.TablePackageImpl.init();

	/**
	 * The meta object id for the '{@link net.zehrer.dynobj.table.impl.TabelViewImpl <em>Tabel View</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.zehrer.dynobj.table.impl.TabelViewImpl
	 * @see net.zehrer.dynobj.table.impl.TablePackageImpl#getTabelView()
	 * @generated
	 */
	int TABEL_VIEW = 0;

	/**
	 * The number of structural features of the '<em>Tabel View</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABEL_VIEW_FEATURE_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link net.zehrer.dynobj.table.TabelView <em>Tabel View</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tabel View</em>'.
	 * @see net.zehrer.dynobj.table.TabelView
	 * @generated
	 */
	EClass getTabelView();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TableFactory getTableFactory();

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
		 * The meta object literal for the '{@link net.zehrer.dynobj.table.impl.TabelViewImpl <em>Tabel View</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.zehrer.dynobj.table.impl.TabelViewImpl
		 * @see net.zehrer.dynobj.table.impl.TablePackageImpl#getTabelView()
		 * @generated
		 */
		EClass TABEL_VIEW = eINSTANCE.getTabelView();

	}

} //TablePackage
