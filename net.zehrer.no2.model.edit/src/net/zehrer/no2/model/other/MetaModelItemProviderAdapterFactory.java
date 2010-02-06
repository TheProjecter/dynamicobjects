/*******************************************************************************
 * Copyright (c) 2009 - 2010 Stephan Zehrer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephan Zehrer - initial API and implementation
 *******************************************************************************/

package net.zehrer.no2.model.other;

import net.zehrer.no2.model.factory.AbstractModelAdapterFactory;

import org.eclipse.emf.common.notify.Adapter;

/**
 * This is the factory that is used to provide the interfaces needed to support
 * Viewers. The adapters generated by this factory convert EMF adapter
 * notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}. The
 * adapters also support Eclipse property sheets. Note that most of the adapters
 * are shared among multiple instances. <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class MetaModelItemProviderAdapterFactory extends AbstractModelAdapterFactory {
	/**
	 * This constructs an instance. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public MetaModelItemProviderAdapterFactory() {
		super();
		
		supportedTypes.add(MetaModelItemProvider.class);
	}

	/**
	 * This keeps track of the one adapter used for all
	 * {@link net.zehrer.no2.model.DataModel} instances. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MetaModelItemProvider dataModelItemProvider;

	/**
	 * This creates an adapter for a meta model (Ecore??)
	 * TODO: check how to reuse ecore stuff
	 * @generated
	 */
	public Adapter createMetaModelAdapter() {
		if (dataModelItemProvider == null) {
			dataModelItemProvider = new MetaModelItemProvider(this);
		}

		return dataModelItemProvider;
	}

	/**
	 * This disposes all of the item providers created by this factory. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void dispose() {
		if (dataModelItemProvider != null)
			dataModelItemProvider.dispose();
	}
}