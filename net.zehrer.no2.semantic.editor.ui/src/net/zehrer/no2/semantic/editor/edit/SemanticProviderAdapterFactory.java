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

package net.zehrer.no2.semantic.editor.edit;

import org.eclipselabs.emf.addon.edit.AbstractModelAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

public class SemanticProviderAdapterFactory extends AbstractModelAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {

	public SemanticProviderAdapterFactory() {
		super();

		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
//		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class); //IStructuredContentProvider
		supportedTypes.add(ITableItemLabelProvider.class);

		// add Ecore package, a adapter has to support all classes of a package
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
		
//		if (notifier instanceof EPackage)
//			return createPackageAdapter();
		
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
	
	// ---- LeafNodeItemProvider

	/**
	 * Singelton attribute for the related Item Provider
	 */
	protected LeafNodeItemProvider leafNodeItemProvider;
	

	public Adapter createECoreAdapter() {

		if (leafNodeItemProvider == null) {
			leafNodeItemProvider = new LeafNodeItemProvider(this);
		}

		return leafNodeItemProvider;
	}
	
	// ---- CompositeNodeItemProvider
	
	/**
	 * Singelton attribute for the related Item Provider
	 */
	protected CompositeNodeItemProvider compositeNodeItemProvider;
	
	/**
	 * 
	 */
	public Adapter getResourceItemProvider() {

		if (this.compositeNodeItemProvider == null) {
			this.compositeNodeItemProvider = new CompositeNodeItemProvider(this);
		}

		return this.compositeNodeItemProvider;
	}
	
	// ---- IDocumentItemProvider

	/**
	 * Singelton attribute for the related Item Provider
	 */
	protected IDocumentItemProvider iDocumentItemProvider;
	
	/**
	 * 
	 */
	public Adapter getEObjectItemProvider() {

		if (this.iDocumentItemProvider == null) {
			this.iDocumentItemProvider = new IDocumentItemProvider(this);
		}

		return this.iDocumentItemProvider;
	}

	// --- IDisposable ---

	@Override
	public void dispose() {
		compositeNodeItemProvider = null;
		iDocumentItemProvider = null;
	}

}
