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
package net.zehrer.no2.model.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collection;
import net.zehrer.no2.model.ModelFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.zehrer.no2.model.ModelPackage;
import net.zehrer.no2.model.NO2Model;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>NO2 Model</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.zehrer.no2.model.impl.NO2ModelImpl#getClassResources <em>Class Resources</em>}</li>
 *   <li>{@link net.zehrer.no2.model.impl.NO2ModelImpl#getResourceSet <em>Resource Set</em>}</li>
 *   <li>{@link net.zehrer.no2.model.impl.NO2ModelImpl#getArchiveURI <em>Archive URI</em>}</li>
 *   <li>{@link net.zehrer.no2.model.impl.NO2ModelImpl#isInit <em>Init</em>}</li>
 *   <li>{@link net.zehrer.no2.model.impl.NO2ModelImpl#getContents <em>Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NO2ModelImpl extends EObjectImpl implements NO2Model {

	// TODO: using external strings

	private static String NS_URI = "http://no2.zehrer.net/";

	// URI formater (well just the counter of the uri)
	private static DecimalFormat URIformater = new DecimalFormat("0000"); // TODO: customize format
	
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = " Copyright (c) 2009 Stephan Zehrer and others.\n All rights reserved. This program and the accompanying materials\n are made available under the terms of the Eclipse Public License v1.0\n which accompanies this distribution, and is available at\n http://www.eclipse.org/legal/epl-v10.html\n\n";

	/**
	 * The cached value of the '{@link #getClassResources()
	 * <em>Class Resources</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getClassResources()
	 * @generated
	 * @ordered
	 */
	protected EMap<EClass, String> classResources;

	/**
	 * The default value of the '{@link #getResourceSet() <em>Resource Set</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResourceSet()
	 * @generated NOT
	 * @ordered
	 */
	protected static final ResourceSet RESOURCE_SET_EDEFAULT = null;   //TODO: clarify why the generation produce now something else.

	/**
	 * The cached value of the '{@link #getResourceSet() <em>Resource Set</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResourceSet()
	 * @generated
	 * @ordered
	 */
	protected ResourceSet resourceSet = RESOURCE_SET_EDEFAULT;

	/**
	 * The default value of the '{@link #getArchiveURI() <em>Archive URI</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getArchiveURI()
	 * @generated NOT
	 * @ordered
	 */
	protected static final URI ARCHIVE_URI_EDEFAULT = null;  //TODO: see RESOURCE_SET_EDEFAULT

	/**
	 * The cached value of the '{@link #getArchiveURI() <em>Archive URI</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getArchiveURI()
	 * @generated
	 * @ordered
	 */
	protected URI archiveURI = ARCHIVE_URI_EDEFAULT;
	
	/**
	 * The default value of the '{@link #isInit() <em>Init</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInit()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INIT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #getContents() <em>Contents</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContents()
	 * @generated
	 * @ordered
	 */
	protected EList<EObject> contents;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected NO2ModelImpl() {
		super();
	}

	protected void init () {
		
		if (this.eResource() != null) {
			// CASE 1: this model was loaded 
			Resource aResouce = this.eResource();
			this.setResourceSet(aResouce.getResourceSet());
			
			
			// TODO: get archiveURI from resource
			//URI resourceURI = aResouce.getURI();

		} else {
			// CASE 2: this model is new created 
			this.setResourceSet(new ResourceSetImpl());
		}
	}
	
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.NO2_MODEL;
	}

	/**
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<EClass, String> getClassResources() {
		if (classResources == null) {
			classResources = new EcoreEMap<EClass,String>(ModelPackage.Literals.CLASS_RESOURCE, ClassResourceImpl.class, this, ModelPackage.NO2_MODEL__CLASS_RESOURCES);
		}
		return classResources;
	}

	/**
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceSet getResourceSet() {
		return resourceSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void setResourceSet(ResourceSet newResourceSet) {
		ResourceSet oldResourceSet = resourceSet;
		resourceSet = newResourceSet;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NO2_MODEL__RESOURCE_SET, oldResourceSet, resourceSet));
	}

	/**
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URI getArchiveURI() {
		return archiveURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT 
	 */
	public void setArchiveURI(URI newArchiveURI) {
			
		URI oldArchiveURI = archiveURI;
		archiveURI = newArchiveURI;
		
		// -------- Set URI mapping ------------
		
		if (!isInit())
			init();
		
		getResourceSet().getURIConverter().getURIMap().put(
				URI.createURI("/"),archiveURI);
		
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NO2_MODEL__ARCHIVE_URI, oldArchiveURI, archiveURI));

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isInit() {
	  return resourceSet != null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EObject> getContents() {
		if (contents == null) {
			contents = new EObjectResolvingEList<EObject>(EObject.class, this, ModelPackage.NO2_MODEL__CONTENTS);
		}
		return contents;
	}

	/**
	 * <!-- begin-user-doc --> 
	 * Create an archive with the inital resources for
	 * the ecore model and this (NO2) meta model. 
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void createModelResource(String fileName) throws IOException {

		init();
		setArchiveURI(getArchiveURI(fileName));

		// ---------- NO2 Model ----------------
		// Create a resource of this NO2 model in the archive.
		Resource no2ModelResouce = createResource(URI.createURI("/no2.xmi")); // TODO: use central name
		no2ModelResouce.getContents().add(this);

		// ---------- Meta Model ----------------

		// Create a resource of the MetaModel in the archive.
		URI metaModelURI = URI.createURI("/metamodel.ecore");	// TODO: use central name
		Resource metaModelResouce = createResource(metaModelURI);

		// Create root package of the MetaModel
		EPackage rootPackage = createRootPackage();
		rootPackage.setNsURI(metaModelURI.toString());
		metaModelResouce.getContents().add(rootPackage);
		
		// Create inital class
		EClass eClass = createEmptyClass(rootPackage);
		Resource modelResource = addClass(eClass);
		
		// ---------- Save All ----------------

		// Save the contents of the resource to the file system.
		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(XMLResource.OPTION_ENCODING, "UTF-8"); // initialObjectCreationPage.getEncoding()
		options.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE); // <--- !!!!!! TODO: test	
		
		no2ModelResouce.save(options);
		metaModelResouce.save(options);
		modelResource.save(options);  
	}
	
	/**
	 * <!-- begin-user-doc --> 
	 * Return either a new or an existing resource.
	 * <!-- end-user-doc -->
	 * @return The resource of the give class type
	 * @generated NOT
	 */
	public Resource addClass(EClass type) {
		
		String URIstr = getClassResources().get(type);
		Resource resource = null;

		// check if class is alrady mapped
		if (URIstr == null) {
			// create a new resource for this type of class
			
			URI modelURI = URI.createURI("/" + URIformater.format(getClassResources().size()) + ".xmi");
			// create and add ClassResource

			BasicEMap.Entry<EClass,String> classResource =  createClassResouce(type, modelURI);
			getClassResources().add(classResource);
			resource  = createResource(modelURI);
		
		} else {
			// get resource internal list
			resource = getResource(URI.createURI(URIstr));
		}
		
		return resource;
	}

	/**
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Resource getResource(URI uri) {
		
		if (!isInit())
			init();
		
		return getResourceSet().getResource(uri, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Resource getHeadModelResource() {
		
		if (getClassResources().size() > 0) {
			
			// TODO: find a marker for head class
			Map.Entry<EClass,String>  classResource = getClassResources().get(0);
			return getResource(URI.createURI(classResource.getValue()));
		}	
	
		return null;  // usually should not occure, initalClass should exist.
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Resource createResource(URI uri) {

		URI resourceURI = resourceSet.getURIConverter().normalize(uri);
		return resourceSet.createResource(resourceURI);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.NO2_MODEL__CLASS_RESOURCES:
				return ((InternalEList<?>)getClassResources()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.NO2_MODEL__CLASS_RESOURCES:
				if (coreType) return getClassResources();
				else return getClassResources().map();
			case ModelPackage.NO2_MODEL__RESOURCE_SET:
				return getResourceSet();
			case ModelPackage.NO2_MODEL__ARCHIVE_URI:
				return getArchiveURI();
			case ModelPackage.NO2_MODEL__INIT:
				return isInit();
			case ModelPackage.NO2_MODEL__CONTENTS:
				return getContents();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.NO2_MODEL__CLASS_RESOURCES:
				((EStructuralFeature.Setting)getClassResources()).set(newValue);
				return;
			case ModelPackage.NO2_MODEL__ARCHIVE_URI:
				setArchiveURI((URI)newValue);
				return;
			case ModelPackage.NO2_MODEL__CONTENTS:
				getContents().clear();
				getContents().addAll((Collection<? extends EObject>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.NO2_MODEL__CLASS_RESOURCES:
				getClassResources().clear();
				return;
			case ModelPackage.NO2_MODEL__ARCHIVE_URI:
				setArchiveURI(ARCHIVE_URI_EDEFAULT);
				return;
			case ModelPackage.NO2_MODEL__CONTENTS:
				getContents().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.NO2_MODEL__CLASS_RESOURCES:
				return classResources != null && !classResources.isEmpty();
			case ModelPackage.NO2_MODEL__RESOURCE_SET:
				return RESOURCE_SET_EDEFAULT == null ? resourceSet != null : !RESOURCE_SET_EDEFAULT.equals(resourceSet);
			case ModelPackage.NO2_MODEL__ARCHIVE_URI:
				return ARCHIVE_URI_EDEFAULT == null ? archiveURI != null : !ARCHIVE_URI_EDEFAULT.equals(archiveURI);
			case ModelPackage.NO2_MODEL__INIT:
				return isInit() != INIT_EDEFAULT;
			case ModelPackage.NO2_MODEL__CONTENTS:
				return contents != null && !contents.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (resourceSet: ");
		result.append(resourceSet);
		result.append(", archiveURI: ");
		result.append(archiveURI);
		result.append(')');
		return result.toString();
	}

	// ---- static methods
	
	public static URI getArchiveURI(URI resourceURI) {

		return URI.createURI("archive:" + resourceURI.toString() + "!/");
	}

	public static URI getArchiveURI(String fileName) {

		return getArchiveURI(URI.createPlatformResourceURI(fileName, true));
	}	
	
	@SuppressWarnings("unchecked")
	protected static BasicEMap.Entry<EClass,String> createClassResouce(EClass type, URI uri) {

		BasicEMap.Entry<EClass,String> classResource =
			(BasicEMap.Entry<EClass,String>) EcoreUtil.create(ModelPackage.Literals.CLASS_RESOURCE ); 
		
		classResource.setKey(type);
		classResource.setValue(uri.toString());

		return classResource;
	}

	protected static EPackage createRootPackage() {
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;

		// create root package
		EPackage rootPackage = ecoreFactory.createEPackage();
		rootPackage.setName("root");
		rootPackage.setNsPrefix("no2"); // TODO: allow user to set namespace
		rootPackage.setNsURI(NS_URI);

		return rootPackage;
	}

	protected static EClass createEmptyClass(EPackage aPackage) {

		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;

		EClass eClass = ecoreFactory.createEClass();
		eClass.setName("NewType"); // TODO: allow user to set (or "NewClass"?) Type sounds more general :)
		
		if (aPackage != null)
			aPackage.getEClassifiers().add(eClass);

		return eClass;
	}

} // NO2ModelImpl
