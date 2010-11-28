/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.zehrer.common.interval.impl;


import net.zehrer.common.interval.EIntInterval;
import net.zehrer.common.interval.EInterval;
import net.zehrer.common.interval.IntervalFactory;
import net.zehrer.common.interval.IntervalPackage;

import net.zehrer.common.lang.LangPackage;
import net.zehrer.common.lang.impl.LangPackageImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IntervalPackageImpl extends EPackageImpl implements IntervalPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eIntervalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eIntIntervalEClass = null;

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
	 * @see net.zehrer.common.interval.IntervalPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private IntervalPackageImpl() {
		super(eNS_URI, IntervalFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link IntervalPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static IntervalPackage init() {
		if (isInited) return (IntervalPackage)EPackage.Registry.INSTANCE.getEPackage(IntervalPackage.eNS_URI);

		// Obtain or create and register package
		IntervalPackageImpl theIntervalPackage = (IntervalPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof IntervalPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new IntervalPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		LangPackageImpl theLangPackage = (LangPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(LangPackage.eNS_URI) instanceof LangPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(LangPackage.eNS_URI) : LangPackage.eINSTANCE);

		// Create package meta-data objects
		theIntervalPackage.createPackageContents();
		theLangPackage.createPackageContents();

		// Initialize created meta-data
		theIntervalPackage.initializePackageContents();
		theLangPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theIntervalPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(IntervalPackage.eNS_URI, theIntervalPackage);
		return theIntervalPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEInterval() {
		return eIntervalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEInterval_LowerLimit() {
		return (EReference)eIntervalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEInterval_UpperLimit() {
		return (EReference)eIntervalEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEIntInterval() {
		return eIntIntervalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEIntInterval_LowerLimit() {
		return (EAttribute)eIntIntervalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEIntInterval_UpperLimit() {
		return (EAttribute)eIntIntervalEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntervalFactory getIntervalFactory() {
		return (IntervalFactory)getEFactoryInstance();
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
		eIntervalEClass = createEClass(EINTERVAL);
		createEReference(eIntervalEClass, EINTERVAL__LOWER_LIMIT);
		createEReference(eIntervalEClass, EINTERVAL__UPPER_LIMIT);

		eIntIntervalEClass = createEClass(EINT_INTERVAL);
		createEAttribute(eIntIntervalEClass, EINT_INTERVAL__LOWER_LIMIT);
		createEAttribute(eIntIntervalEClass, EINT_INTERVAL__UPPER_LIMIT);
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

		// Obtain other dependent packages
		LangPackage theLangPackage = (LangPackage)EPackage.Registry.INSTANCE.getEPackage(LangPackage.eNS_URI);

		// Create type parameters
		ETypeParameter eIntervalEClass_T = addETypeParameter(eIntervalEClass, "T");

		// Set bounds for type parameters
		EGenericType g1 = createEGenericType(theLangPackage.getIComparable());
		EGenericType g2 = createEGenericType(eIntervalEClass_T);
		g1.getETypeArguments().add(g2);
		eIntervalEClass_T.getEBounds().add(g1);

		// Add supertypes to classes
		g1 = createEGenericType(theLangPackage.getIComparable());
		g2 = createEGenericType(this.getEInterval());
		g1.getETypeArguments().add(g2);
		EGenericType g3 = createEGenericType(eIntervalEClass_T);
		g2.getETypeArguments().add(g3);
		eIntervalEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theLangPackage.getIComparable());
		g2 = createEGenericType(this.getEIntInterval());
		g1.getETypeArguments().add(g2);
		eIntIntervalEClass.getEGenericSuperTypes().add(g1);

		// Initialize classes and features; add operations and parameters
		initEClass(eIntervalEClass, EInterval.class, "EInterval", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(eIntervalEClass_T);
		initEReference(getEInterval_LowerLimit(), g1, null, "lowerLimit", null, 1, 1, EInterval.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(eIntervalEClass_T);
		initEReference(getEInterval_UpperLimit(), g1, null, "upperLimit", null, 1, 1, EInterval.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(eIntervalEClass, ecorePackage.getEBoolean(), "intersects", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getEInterval());
		g2 = createEGenericType(eIntervalEClass_T);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(eIntervalEClass, null, "intersect", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getEInterval());
		g2 = createEGenericType(eIntervalEClass_T);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "other", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getEInterval());
		g2 = createEGenericType(eIntervalEClass_T);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(eIntervalEClass, ecorePackage.getEBoolean(), "isBelow", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(eIntervalEClass_T);
		addEParameter(op, g1, "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(eIntervalEClass, ecorePackage.getEBoolean(), "isAbove", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(eIntervalEClass_T);
		addEParameter(op, g1, "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(eIntervalEClass, ecorePackage.getEBoolean(), "equals", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(eIntervalEClass, ecorePackage.getEBoolean(), "equals", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getEInterval());
		g2 = createEGenericType(eIntervalEClass_T);
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(eIntervalEClass, ecorePackage.getEInt(), "hashCode", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(eIntervalEClass, ecorePackage.getEBoolean(), "includes", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(eIntervalEClass_T);
		addEParameter(op, g1, "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(eIntIntervalEClass, EIntInterval.class, "EIntInterval", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEIntInterval_LowerLimit(), ecorePackage.getEIntegerObject(), "lowerLimit", null, 0, 1, EIntInterval.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEIntInterval_UpperLimit(), ecorePackage.getEIntegerObject(), "upperLimit", null, 0, 1, EIntInterval.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(eIntIntervalEClass, ecorePackage.getEBoolean(), "intersects", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getEIntInterval(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(eIntIntervalEClass, this.getEIntInterval(), "intersect", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getEIntInterval(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(eIntIntervalEClass, ecorePackage.getEBoolean(), "isBelow", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEIntegerObject(), "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(eIntIntervalEClass, ecorePackage.getEBoolean(), "isAbove", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEIntegerObject(), "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(eIntIntervalEClass, ecorePackage.getEBoolean(), "equals", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEJavaObject(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(eIntIntervalEClass, ecorePackage.getEBoolean(), "equals", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getEIntInterval(), "other", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(eIntIntervalEClass, ecorePackage.getEInt(), "hashCode", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(eIntIntervalEClass, ecorePackage.getEBoolean(), "includes", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEIntegerObject(), "value", 0, 1, IS_UNIQUE, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //IntervalPackageImpl
