/**
 * <copyright>
 (c) Stephan Zehrer 2010 
 * </copyright>
 *
 * $Id$
 */
package net.zehrer.dynobj.table.impl;

import net.zehrer.dynobj.table.TabelView;
import net.zehrer.dynobj.table.TableFactory;
import net.zehrer.dynobj.table.TablePackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TablePackageImpl extends EPackageImpl implements TablePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tabelViewEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see net.zehrer.dynobj.table.TablePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TablePackageImpl() {
		super(eNS_URI, TableFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TablePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TablePackage init() {
		if (isInited)
			return (TablePackage) EPackage.Registry.INSTANCE.getEPackage(TablePackage.eNS_URI);

		// Obtain or create and register package
		TablePackageImpl theTablePackage = (TablePackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TablePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new TablePackageImpl());

		isInited = true;

		// Create package meta-data objects
		theTablePackage.createPackageContents();

		// Initialize created meta-data
		theTablePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTablePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TablePackage.eNS_URI, theTablePackage);
		return theTablePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTabelView() {
		return tabelViewEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableFactory getTableFactory() {
		return (TableFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		tabelViewEClass = createEClass(TABEL_VIEW);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(tabelViewEClass, TabelView.class, "TabelView", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //TablePackageImpl
