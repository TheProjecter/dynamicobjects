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

package net.zehrer.no2.model.factory;

import net.zehrer.no2.model.provider.EObjectItemProvider;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;

public class DynamicItemProviderAdapterFactory extends
		AbstractModelAdapterFactory implements ComposeableAdapterFactory,
		IChangeNotifier, IDisposable {

	public DynamicItemProviderAdapterFactory() {
		super();

		supportedTypes.add(ITableItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
		supportedTypes.add(IChangeNotifier.class);
	}

	// ---- AdapterFactory ----

	/**
	 * @see AdapterFactory#isFactoryForType
	 */
	@Override
	public boolean isFactoryForType(Object object) {

		// it doesn't matter which kind of package, this factory is generic for
		// any dynamic objects
		if (object instanceof EPackage)
			return true;

		return supportedTypes.contains(object);
	}

	/**
	 * Singleton attribute for the related Item Provider
	 */
	protected EObjectItemProvider eObjectItemProvider;

	/**
	 * @category AdapterFactoryImpl
	 */
	@Override
	protected Adapter createAdapter(Notifier target) {

		
		if (this.eObjectItemProvider == null) {
			this.eObjectItemProvider = new EObjectItemProvider(this);
		}

		return this.eObjectItemProvider;
	}

	// --- IDisposable ---
	/**
	 * @category AbstractModelAdapterFactory
	 */
	@Override
	public void dispose() {
		this.eObjectItemProvider = null;
	}

}
