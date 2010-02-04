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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.INotifyChangedListener;

public abstract class AbstractModelAdapterFactory extends AdapterFactoryImpl implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {

	/**
	 * This keeps track of all the supported types checked by
	 * {@link #isFactoryForType isFactoryForType}.
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	public AbstractModelAdapterFactory() {
		super();

		// supportedTypes.add(IEditingDomainItemProvider.class);
		// supportedTypes.add(IStructuredItemContentProvider.class);
		// supportedTypes.add(ITreeItemContentProvider.class);
		// supportedTypes.add(IItemLabelProvider.class);
		// supportedTypes.add(IItemPropertySource.class);

	}

	// ---- AdapterFactory ----

	/**
	 * @see AdapterFactory#isFactoryForType
	 */
	@Override
	public boolean isFactoryForType(Object type) {

		return supportedTypes.contains(type);
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
	 * This implementation substitutes the factory itself as the key for the
	 * adapter.
	 * @see AdapterFactory#adapt(Notifier target, Object type);
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}



	// / ----- IChangeNotifier

	/**
	 * This is used to implement
	 * {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This adds a listener.
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	// / ----- IChangeNotifier & ComposeableAdapterFactory

	/**
	 * This delegates to {@link #changeNotifier} and to
	 * {@link #parentAdapterFactory}.
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	// / ---- ComposeableAdapterFactory

	/**
	 * This keeps track of the root adapter factory that delegates to thi
	 * adapter factory.
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!--begin-user-doc --> <!-- end-user-doc -->
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return this.parentAdapterFactory == null ? this : this.parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * This disposes all of the item providers created by the factory.
	 */
	abstract public void dispose();
}