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

package net.zehrer.no2.ui.edit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.zehrer.no2.ui.NO2UiPlugin;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

public class EObjectItemProvider extends ItemProviderAdapter implements ITableItemLabelProvider, IItemPropertySource , IChangeNotifier{

	/**
	 * This keeps track of all the supported types checked by
	 * {@link #isFactoryForType isFactoryForType}.
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();
	
	public EObjectItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		
		supportedTypes.add(ITableItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
		supportedTypes.add(IChangeNotifier.class);
		

	}

	/**
	 * If this is defined to be something other than an empty list, it is used
	 * to implement {@link #getChildren getChildren}, including in determining
	 * whether to cache children and, if so, in setting up the store. It is also
	 * used to deduce the appropriate feature for an <code>AddCommand</code>,
	 * <code>RemoveCommand</code> or <code>MoveCommand</code> in
	 * {@link #createCommand createCommand}. If you override those methods, then
	 * you don't need to implement this.
	 */
	// @Override
	// protected Collection<? extends EStructuralFeature>
	// getChildrenFeatures(Object object) {
	//		
	// // sync this methode with Page.generateTableColumns
	//
	// childrenFeatures = new ArrayList<EStructuralFeature>();
	// EObject eObject = (EObject) object;
	// EClass eClass = eObject.eClass();
	//		
	// for (EAttribute eAttribute : eClass.getEAllAttributes()) {
	// // TODO: analyse the ReflectiveItemProvider cause it seems it handle some
	// special cases e.g. FeatureMap
	// childrenFeatures.add(eAttribute);
	// }
	//	      
	// return childrenFeatures;
	// }

	// / ----- ItemProviderAdapter -----

	/**
	 * Return the resource locator for the NEW item provider's resources in this
	 * subclass
	 * 
	 * @category ItemProviderAdapter
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return NO2UiPlugin.INSTANCE;
	}

	// ----- ITableItemLabelProvider ------

	/**
	 * @category ItemProviderAdapter
	 */
	@Override
	public String getColumnText(Object object, int columnIndex) {

		EObject eObject = (EObject) object;
		EAttribute eAttribute = eObject.eClass().getEAllAttributes().get(columnIndex);

		Object value = eObject.eGet(eAttribute);
		if (value != null)
			return value.toString();

		return "";
	}

	// ----- IItemLabelProvider ----

	/**
	 * This does the same thing as ILabelProvider.getText, it fetches the label
	 * text specific to this object instance.
	 * 
	 * @category ItemProviderAdapter
	 */
	@Override
	public String getText(Object object) {

		StringBuffer result = new StringBuffer();

		if (object instanceof ENamedElement) {
			ENamedElement eNamedObj = (ENamedElement) object;

			if (eNamedObj.getName() != null) {
				result.append(eNamedObj.getName());
			}

		}

		if (object instanceof EClassifier) {
			EClassifier eClassifier = (EClassifier) object;

			// if (! eClassifier.getETypeParameters().isEmpty()) {
			// result.append("<");
			// for (Iterator<ETypeParameter> i =
			// eClassifier.getETypeParameters().iterator(); i.hasNext();) {
			// ETypeParameter eTypeParameter = i.next();
			// result.append(ETypeParameterItemProvider.getText(eTypeParameter));
			// // BUG??
			// if (i.hasNext()) {
			// result.append(", ");
			// }
			// }
			// result.append(">");
			// }

			if (eClassifier.getInstanceTypeName() != null) {
				result.append(" [");
				result.append(eClassifier.getInstanceTypeName());
				result.append("]");
			}
		}

		// if (!eClass.getEGenericSuperTypes().isEmpty()) {
		// result.append(" -> ");
		// for (Iterator<EGenericType> i =
		// eClass.getEGenericSuperTypes().iterator(); i.hasNext();) {
		// EGenericType eGenericSuperType = i.next();
		// //
		// result.append(EGenericTypeItemProvider.getText(eGenericSuperType));
		// if (i.hasNext()) {
		// result.append(", ");
		// }
		// }
		// }

		return result.toString();
	}

	/**
	 * This returns EClass image from the EcoreEditPlugin
	 * 
	 * @category ItemProviderAdapter
	 */
	@Override
	public Object getImage(Object object) {

		return overlayImage(object, EcoreEditPlugin.INSTANCE.getImage("full/obj16/EObject"));
	}

	// ----- IItemPropertySource

	/**
	 * @category ItemProviderAdapter
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {

		itemPropertyDescriptors = new ArrayList<IItemPropertyDescriptor>();

		for (EStructuralFeature eFeature : ((EObject) object).eClass().getEAllStructuralFeatures()) {
			if (!(eFeature instanceof EReference) || !((EReference) eFeature).isContainment()) {
				itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getFeatureText(eFeature),
						"TODO Property Description", // TODO How to handle
														// feature description'?
						eFeature, eFeature.isChangeable(), ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
			}
		}

		return itemPropertyDescriptors;
	}

	// ----- ItemProviderAdaper

	/**
	 * Get a feature name of the given feature. TODO: At the moment it handles
	 * only EStructualFeatures, this should be improved.
	 * 
	 * @category ItemProviderAdapter
	 */
	@Override
	protected String getFeatureText(Object feature) {
		String featureName;
		if (feature instanceof EStructuralFeature) {
			EStructuralFeature eFeature = (EStructuralFeature) feature;
			// TODO How to change this name? e.g. by an annotaion? -> define and
			// add editor support
			featureName = eFeature.getName();
		} else {
			featureName = "TODO";
		}
		return featureName;
	}


	@Override
	public boolean isAdapterForType(Object type) {

		return  supportedTypes.contains(type) || super.isAdapterForType(type);
	}

	/**
	 * This handles notification by calling
	 * {@link #fireNotifyChanged(Notification) fireNotifyChanged}.
	 * @category AdapterImpl
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);
		
		fireNotifyChanged(new ViewerNotification (
				notification,notification.getNotifier(),false,true));
		
		// TODO: handle containment references different as attributes
		//super.notifyChanged(notification);  // disable as long just adapter impl
	}

}
