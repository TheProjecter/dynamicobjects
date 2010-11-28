/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.zehrer.common.lang.impl;

import net.zehrer.common.interval.IntervalPackage;

import net.zehrer.common.interval.impl.IntervalPackageImpl;

import net.zehrer.common.lang.LangFactory;
import net.zehrer.common.lang.LangPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypeParameter;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LangPackageImpl extends EPackageImpl implements LangPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iComparableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType integerEDataType = null;

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
	 * @see net.zehrer.common.lang.LangPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private LangPackageImpl() {
		super(eNS_URI, LangFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link LangPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static LangPackage init() {
		if (isInited) return (LangPackage)EPackage.Registry.INSTANCE.getEPackage(LangPackage.eNS_URI);

		// Obtain or create and register package
		LangPackageImpl theLangPackage = (LangPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof LangPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new LangPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		IntervalPackageImpl theIntervalPackage = (IntervalPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(IntervalPackage.eNS_URI) instanceof IntervalPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(IntervalPackage.eNS_URI) : IntervalPackage.eINSTANCE);

		// Create package meta-data objects
		theLangPackage.createPackageContents();
		theIntervalPackage.createPackageContents();

		// Initialize created meta-data
		theLangPackage.initializePackageContents();
		theIntervalPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theLangPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(LangPackage.eNS_URI, theLangPackage);
		return theLangPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIComparable() {
		return iComparableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getInteger() {
		return integerEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LangFactory getLangFactory() {
		return (LangFactory)getEFactoryInstance();
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
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		iComparableEClass = createEClass(ICOMPARABLE);

		// Create data types
		integerEDataType = createEDataType(INTEGER);
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
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters
		ETypeParameter iComparableEClass_T = addETypeParameter(iComparableEClass, "T");

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(iComparableEClass, Comparable.class, "IComparable", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		EOperation op = addEOperation(iComparableEClass, ecorePackage.getEInt(), "compareTo", 0, 1, IS_UNIQUE, IS_ORDERED);
		EGenericType g1 = createEGenericType(iComparableEClass_T);
		addEParameter(op, g1, "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		// Initialize data types
		initEDataType(integerEDataType, Integer.class, "Integer", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //LangPackageImpl
