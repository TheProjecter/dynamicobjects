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

package net.zehrer.no2.ui.edit;

import java.util.Collection;

import net.zehrer.no2.ui.NO2UiPlugin;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

public class EPackageItemProvider extends ItemProviderAdapter implements ITreeItemContentProvider, IItemLabelProvider {


	public EPackageItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);

	}

	// / ----- ItemProviderAdapter -----

	/**
	 * This specifies how to implement {@link #getChildren} and is used to
	 * deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand},
	 * {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in
	 * {@link #createCommand}.
	 * 
	 * @generated NOT
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {

		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(EcorePackage.Literals.EPACKAGE__ECLASSIFIERS);
			childrenFeatures.add(EcorePackage.Literals.EPACKAGE__ESUBPACKAGES);
		}
		return childrenFeatures;

	}

	/**
	 * Return the resource locator for the NEW item provider's resources in this
	 * subclass
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return NO2UiPlugin.INSTANCE;
	}

	// ----- IItemLabelProvider ----

	/**
	 * This does the same thing as ILabelProvider.getText, it fetches the label
	 * text specific to this object instance.
	 */
	@Override
	public String getText(Object object) {
		EPackage ePackage = (EPackage) object;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(ePackage.getName());
		return stringBuffer.toString();
	}

	/**
	 * This returns EPackage image from the EcoreEditPlugin
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, EcoreEditPlugin.INSTANCE.getImage("full/obj16/EPackage"));
	}


}

