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

import java.io.IOException;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>NO2 Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.zehrer.no2.model.NO2Model#getClassResources <em>Class Resources</em>}</li>
 *   <li>{@link net.zehrer.no2.model.NO2Model#getResourceSet <em>Resource Set</em>}</li>
 *   <li>{@link net.zehrer.no2.model.NO2Model#getArchiveURI <em>Archive URI</em>}</li>
 *   <li>{@link net.zehrer.no2.model.NO2Model#isInit <em>Init</em>}</li>
 *   <li>{@link net.zehrer.no2.model.NO2Model#getContents <em>Contents</em>}</li>
 *   <li>{@link net.zehrer.no2.model.NO2Model#getEditingDomainProvider <em>Editing Domain Provider</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.zehrer.no2.model.ModelPackage#getNO2Model()
 * @model
 * @generated
 */
public interface NO2Model extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = " Copyright (c) 2009 - 2010 Stephan Zehrer and others.\n All rights reserved. This program and the accompanying materials\n are made available under the terms of the Eclipse Public License v1.0\n which accompanies this distribution, and is available at\n http://www.eclipse.org/legal/epl-v10.html\n\n";

	/**
	 * Returns the value of the '<em><b>Class Resources</b></em>' map.
	 * The key is of type {@link org.eclipse.emf.ecore.EClass},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class Resources</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class Resources</em>' map.
	 * @see net.zehrer.no2.model.ModelPackage#getNO2Model_ClassResources()
	 * @model mapType="net.zehrer.no2.model.EClassToURIMapEntry<org.eclipse.emf.ecore.EClass, org.eclipse.emf.ecore.EString>"
	 * @generated
	 */
	EMap<EClass, String> getClassResources();

	/**
	 * Returns the value of the '<em><b>Resource Set</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Set</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Set</em>' attribute.
	 * @see net.zehrer.no2.model.ModelPackage#getNO2Model_ResourceSet()
	 * @model default="" dataType="net.zehrer.no2.model.ResourceSet" transient="true" changeable="false"
	 * @generated
	 */
	ResourceSet getResourceSet();


	/**
	 * Returns the value of the '<em><b>Archive URI</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Archive URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Archive URI</em>' attribute.
	 * @see #setArchiveURI(URI)
	 * @see net.zehrer.no2.model.ModelPackage#getNO2Model_ArchiveURI()
	 * @model default="" dataType="net.zehrer.no2.model.URI" transient="true"
	 * @generated
	 */
	URI getArchiveURI();

	/**
	 * Sets the value of the '{@link net.zehrer.no2.model.NO2Model#getArchiveURI <em>Archive URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Archive URI</em>' attribute.
	 * @see #getArchiveURI()
	 * @generated
	 */
	void setArchiveURI(URI value);

	/**
	 * Returns the value of the '<em><b>Init</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Init</em>' attribute.
	 * @see net.zehrer.no2.model.ModelPackage#getNO2Model_Init()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	boolean isInit();

	/**
	 * Returns the value of the '<em><b>Contents</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contents</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contents</em>' reference list.
	 * @see net.zehrer.no2.model.ModelPackage#getNO2Model_Contents()
	 * @model
	 * @generated
	 */
	EList<EObject> getContents();

	/**
	 * Returns the value of the '<em><b>Editing Domain Provider</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Editing Domain Provider</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Editing Domain Provider</em>' attribute.
	 * @see #setEditingDomainProvider(IEditingDomainProvider)
	 * @see net.zehrer.no2.model.ModelPackage#getNO2Model_EditingDomainProvider()
	 * @model default="" dataType="net.zehrer.no2.model.IEditingDomainProvider" transient="true"
	 * @generated
	 */
	IEditingDomainProvider getEditingDomainProvider();

	/**
	 * Sets the value of the '{@link net.zehrer.no2.model.NO2Model#getEditingDomainProvider <em>Editing Domain Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Editing Domain Provider</em>' attribute.
	 * @see #getEditingDomainProvider()
	 * @generated
	 */
	void setEditingDomainProvider(IEditingDomainProvider value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="net.zehrer.no2.model.IOException"
	 * @generated
	 */
	void createModelResource(String fileName) throws IOException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="net.zehrer.no2.model.Resource"
	 * @generated
	 */
	Resource addClass(EClass type);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="net.zehrer.no2.model.Resource" uriDataType="net.zehrer.no2.model.URI"
	 * @generated
	 */
	Resource getResource(URI uri);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" dataType="net.zehrer.no2.model.Resource"
	 * @generated
	 */
	Resource getHeadModelResource();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="net.zehrer.no2.model.Resource" uriDataType="net.zehrer.no2.model.URI"
	 * @generated
	 */
	Resource createResource(URI uri);


} // NO2Model
