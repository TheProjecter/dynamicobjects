/*******************************************************************************
 * Copyright (c) 2009 Stephan Zehrer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephan Zehrer - initial API and implementation
 *******************************************************************************/

package net.zehrer.no2.model.factory;

import net.zehrer.no2.model.provider.ENamedItemProvider;
import net.zehrer.no2.model.provider.EObjectItemProvider;
import net.zehrer.no2.model.provider.EPackageItemProvider;
import net.zehrer.no2.model.provider.ResourceItemProvider;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

public class ECoreItemProviderAdapterFactory extends AbstractModelAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {

	public ECoreItemProviderAdapterFactory() {
		super();

		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class); //IStructuredContentProvider
		supportedTypes.add(ITableItemLabelProvider.class);

		// add Ecore package, a adatper has to support all classes of a package
		// https://bugs.eclipse.org/bugs/show_bug.cgi?id=299887
		supportedTypes.add(EcorePackage.eINSTANCE);
	}

	// ---- AdapterFactory ----

	/**
	 * @see AdapterFactory#isFactoryForType
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		// similar to the implementation of EcoreItemProviderAdapter
		return supportedTypes.contains(object);
	}

	/**
	 * @see AdapterFactory#adapt(Object object, Object type)
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>) type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * @see AdapterFactory#adapt(Notifier target, Object type);
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		
		if (notifier instanceof EPackage)
			return createPackageAdapter();
		
//		if (notifier instanceof Resource)
//			return getResourceItemProvider();
		
		// TODO: find a nicer way to handel the dynamic instances e.g. create its own AdapterFactory for the pages.
		if (notifier instanceof DynamicEObjectImpl)  
			return getEObjectItemProvider();
		
		if (((EObject)notifier).eClass().getEPackage() == EcorePackage.eINSTANCE) {
			return createECoreAdapter();
		}
		return null;
	}

	// ---- EPackageItemProvider
	
	/**
	 * Singelton attribute for the related Item Provider
	 */
	protected EPackageItemProvider ePackageItemProvider;
	
	/**
	 * This creates an adapter for a EPackage instance
	 */
	public Adapter createPackageAdapter() {

		if (ePackageItemProvider == null) {
			ePackageItemProvider = new EPackageItemProvider(this);
		}

		return ePackageItemProvider;
	}
	
	// ---- ENamedItemProvider

	/**
	 * Singelton attribute for the related Item Provider
	 */
	protected ENamedItemProvider eCoreItemProvider;
	
	/**
	 * This creates an adapter for a meta model (Ecore??)
	 */
	public Adapter createECoreAdapter() {

		if (eCoreItemProvider == null) {
			eCoreItemProvider = new ENamedItemProvider(this);
		}

		return eCoreItemProvider;
	}
	
	/**
	 * Singelton attribute for the related Item Provider
	 */
	protected ResourceItemProvider resourceItemProvider;
	
	/**
	 * 
	 */
	public Adapter getResourceItemProvider() {

		if (this.resourceItemProvider == null) {
			this.resourceItemProvider = new ResourceItemProvider(this);
		}

		return this.resourceItemProvider;
	}
	
	// ---- EObjectItemProvider

	/**
	 * Singelton attribute for the related Item Provider
	 */
	protected EObjectItemProvider eObjectItemProvider;
	
	/**
	 * 
	 */
	public Adapter getEObjectItemProvider() {

		if (this.eObjectItemProvider == null) {
			this.eObjectItemProvider = new EObjectItemProvider(this);
		}

		return this.eObjectItemProvider;
	}

	// --- IDisposable ---

	@Override
	public void dispose() {
		eCoreItemProvider = null;
		ePackageItemProvider = null;
	}

}
