/**
 *  Copyright (c) 2009 Stephan Zehrer and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 * 
 *
 * $Id$
 */
package net.zehrer.no2.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see net.zehrer.no2.model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = " Copyright (c) 2009 - 2010 Stephan Zehrer and others.\n All rights reserved. This program and the accompanying materials\n are made available under the terms of the Eclipse Public License v1.0\n which accompanies this distribution, and is available at\n http://www.eclipse.org/legal/epl-v10.html\n\n";

	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://no2.zehrer.net/model.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "no2";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = net.zehrer.no2.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link net.zehrer.no2.model.impl.NO2ModelImpl <em>NO2 Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.zehrer.no2.model.impl.NO2ModelImpl
	 * @see net.zehrer.no2.model.impl.ModelPackageImpl#getNO2Model()
	 * @generated
	 */
	int NO2_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Class Resources</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO2_MODEL__CLASS_RESOURCES = 0;

	/**
	 * The feature id for the '<em><b>Resource Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO2_MODEL__RESOURCE_SET = 1;

	/**
	 * The feature id for the '<em><b>Archive URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO2_MODEL__ARCHIVE_URI = 2;

	/**
	 * The feature id for the '<em><b>Init</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO2_MODEL__INIT = 3;

	/**
	 * The feature id for the '<em><b>Contents</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO2_MODEL__CONTENTS = 4;

	/**
	 * The number of structural features of the '<em>NO2 Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NO2_MODEL_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link net.zehrer.no2.model.impl.EClassToURIMapEntryImpl <em>EClass To URI Map Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.zehrer.no2.model.impl.EClassToURIMapEntryImpl
	 * @see net.zehrer.no2.model.impl.ModelPackageImpl#getEClassToURIMapEntry()
	 * @generated
	 */
	int ECLASS_TO_URI_MAP_ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS_TO_URI_MAP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS_TO_URI_MAP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>EClass To URI Map Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ECLASS_TO_URI_MAP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '<em>URI</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.URI
	 * @see net.zehrer.no2.model.impl.ModelPackageImpl#getURI()
	 * @generated
	 */
	int URI = 2;


	/**
	 * The meta object id for the '<em>IO Exception</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.io.IOException
	 * @see net.zehrer.no2.model.impl.ModelPackageImpl#getIOException()
	 * @generated
	 */
	int IO_EXCEPTION = 3;


	/**
	 * The meta object id for the '<em>Resource</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.resource.Resource
	 * @see net.zehrer.no2.model.impl.ModelPackageImpl#getResource()
	 * @generated
	 */
	int RESOURCE = 4;


	/**
	 * The meta object id for the '<em>Resource Set</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.resource.ResourceSet
	 * @see net.zehrer.no2.model.impl.ModelPackageImpl#getResourceSet()
	 * @generated
	 */
	int RESOURCE_SET = 5;


	/**
	 * Returns the meta object for class '{@link net.zehrer.no2.model.NO2Model <em>NO2 Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>NO2 Model</em>'.
	 * @see net.zehrer.no2.model.NO2Model
	 * @generated
	 */
	EClass getNO2Model();

	/**
	 * Returns the meta object for the map '{@link net.zehrer.no2.model.NO2Model#getClassResources <em>Class Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Class Resources</em>'.
	 * @see net.zehrer.no2.model.NO2Model#getClassResources()
	 * @see #getNO2Model()
	 * @generated
	 */
	EReference getNO2Model_ClassResources();

	/**
	 * Returns the meta object for the attribute '{@link net.zehrer.no2.model.NO2Model#getResourceSet <em>Resource Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource Set</em>'.
	 * @see net.zehrer.no2.model.NO2Model#getResourceSet()
	 * @see #getNO2Model()
	 * @generated
	 */
	EAttribute getNO2Model_ResourceSet();

	/**
	 * Returns the meta object for the attribute '{@link net.zehrer.no2.model.NO2Model#getArchiveURI <em>Archive URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Archive URI</em>'.
	 * @see net.zehrer.no2.model.NO2Model#getArchiveURI()
	 * @see #getNO2Model()
	 * @generated
	 */
	EAttribute getNO2Model_ArchiveURI();

	/**
	 * Returns the meta object for the attribute '{@link net.zehrer.no2.model.NO2Model#isInit <em>Init</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Init</em>'.
	 * @see net.zehrer.no2.model.NO2Model#isInit()
	 * @see #getNO2Model()
	 * @generated
	 */
	EAttribute getNO2Model_Init();

	/**
	 * Returns the meta object for the reference list '{@link net.zehrer.no2.model.NO2Model#getContents <em>Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contents</em>'.
	 * @see net.zehrer.no2.model.NO2Model#getContents()
	 * @see #getNO2Model()
	 * @generated
	 */
	EReference getNO2Model_Contents();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>EClass To URI Map Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EClass To URI Map Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyType="org.eclipse.emf.ecore.EClass"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getEClassToURIMapEntry();

	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEClassToURIMapEntry()
	 * @generated
	 */
	EReference getEClassToURIMapEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEClassToURIMapEntry()
	 * @generated
	 */
	EAttribute getEClassToURIMapEntry_Value();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.common.util.URI <em>URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>URI</em>'.
	 * @see org.eclipse.emf.common.util.URI
	 * @model instanceClass="org.eclipse.emf.common.util.URI"
	 * @generated
	 */
	EDataType getURI();

	/**
	 * Returns the meta object for data type '{@link java.io.IOException <em>IO Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IO Exception</em>'.
	 * @see java.io.IOException
	 * @model instanceClass="java.io.IOException" serializeable="false"
	 * @generated
	 */
	EDataType getIOException();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.ecore.resource.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Resource</em>'.
	 * @see org.eclipse.emf.ecore.resource.Resource
	 * @model instanceClass="org.eclipse.emf.ecore.resource.Resource"
	 * @generated
	 */
	EDataType getResource();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.ecore.resource.ResourceSet <em>Resource Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Resource Set</em>'.
	 * @see org.eclipse.emf.ecore.resource.ResourceSet
	 * @model instanceClass="org.eclipse.emf.ecore.resource.ResourceSet"
	 * @generated
	 */
	EDataType getResourceSet();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

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
		 * The meta object literal for the '{@link net.zehrer.no2.model.impl.NO2ModelImpl <em>NO2 Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.zehrer.no2.model.impl.NO2ModelImpl
		 * @see net.zehrer.no2.model.impl.ModelPackageImpl#getNO2Model()
		 * @generated
		 */
		EClass NO2_MODEL = eINSTANCE.getNO2Model();

		/**
		 * The meta object literal for the '<em><b>Class Resources</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NO2_MODEL__CLASS_RESOURCES = eINSTANCE.getNO2Model_ClassResources();

		/**
		 * The meta object literal for the '<em><b>Resource Set</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NO2_MODEL__RESOURCE_SET = eINSTANCE.getNO2Model_ResourceSet();

		/**
		 * The meta object literal for the '<em><b>Archive URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NO2_MODEL__ARCHIVE_URI = eINSTANCE.getNO2Model_ArchiveURI();

		/**
		 * The meta object literal for the '<em><b>Init</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NO2_MODEL__INIT = eINSTANCE.getNO2Model_Init();

		/**
		 * The meta object literal for the '<em><b>Contents</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NO2_MODEL__CONTENTS = eINSTANCE.getNO2Model_Contents();

		/**
		 * The meta object literal for the '{@link net.zehrer.no2.model.impl.EClassToURIMapEntryImpl <em>EClass To URI Map Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.zehrer.no2.model.impl.EClassToURIMapEntryImpl
		 * @see net.zehrer.no2.model.impl.ModelPackageImpl#getEClassToURIMapEntry()
		 * @generated
		 */
		EClass ECLASS_TO_URI_MAP_ENTRY = eINSTANCE.getEClassToURIMapEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ECLASS_TO_URI_MAP_ENTRY__KEY = eINSTANCE.getEClassToURIMapEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ECLASS_TO_URI_MAP_ENTRY__VALUE = eINSTANCE.getEClassToURIMapEntry_Value();

		/**
		 * The meta object literal for the '<em>URI</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.common.util.URI
		 * @see net.zehrer.no2.model.impl.ModelPackageImpl#getURI()
		 * @generated
		 */
		EDataType URI = eINSTANCE.getURI();

		/**
		 * The meta object literal for the '<em>IO Exception</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.io.IOException
		 * @see net.zehrer.no2.model.impl.ModelPackageImpl#getIOException()
		 * @generated
		 */
		EDataType IO_EXCEPTION = eINSTANCE.getIOException();

		/**
		 * The meta object literal for the '<em>Resource</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.ecore.resource.Resource
		 * @see net.zehrer.no2.model.impl.ModelPackageImpl#getResource()
		 * @generated
		 */
		EDataType RESOURCE = eINSTANCE.getResource();

		/**
		 * The meta object literal for the '<em>Resource Set</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.ecore.resource.ResourceSet
		 * @see net.zehrer.no2.model.impl.ModelPackageImpl#getResourceSet()
		 * @generated
		 */
		EDataType RESOURCE_SET = eINSTANCE.getResourceSet();

	}

} //ModelPackage
