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

package net.zehrer.no2.model.provider;

import java.util.Collection;
import java.util.Iterator;

import net.zehrer.no2.model.DataModelEditPlugin;
import net.zehrer.no2.model.ModelPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

public class EClassItemProvider extends ItemProviderAdapter implements ITreeItemContentProvider, IItemLabelProvider, IEditingDomainItemProvider {

	// implements 
	// IStructuredItemContentProvider,
	// IItemLabelProvider,IItemPropertySource
	// extends ReflectiveItemProvider

	public EClassItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);

	}

	// / ----- ItemProviderAdapter -----

	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {

		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ModelPackage.Literals.NO2_MODEL__CONTENTS);
		}
		return childrenFeatures;
	}

	/**
	 * Return the resource locator for the NEW item provider's resources in this
	 * subclass
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return DataModelEditPlugin.INSTANCE;
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object.
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {

		super.collectNewChildDescriptors(newChildDescriptors, object);

		if (object instanceof EClass) {

			// dynamic Object creation
			EClass eClass = (EClass) object;
			EFactory eFactory = eClass.getEPackage().getEFactoryInstance();
			EObject eObject = eFactory.create(eClass);

			// add command
			newChildDescriptors.add(createChildParameter(ModelPackage.Literals.NO2_MODEL__CONTENTS, eObject));
		}
	}
	
//	
//	 @Override
//	 protected EReference getChildFeature (Object object, Object child) {
//		
//	 return ModelPackage.Literals.NO2_MODEL__CONTENTS;
//		
//	 }
//	

	// ----- IItemLabelProvider ----

	/**
	 * This does the same thing as ILabelProvider.getText, it fetches the label
	 * text specific to this object instance.
	 */
	@Override
	public String getText(Object object) {

		EClass eClass = (EClass) object;
		StringBuffer result = new StringBuffer();
		if (eClass.getName() != null) {
			result.append(eClass.getName());
		}

		if (!eClass.getETypeParameters().isEmpty()) {
			result.append("<");
			for (Iterator<ETypeParameter> i = eClass.getETypeParameters().iterator(); i.hasNext();) {
				ETypeParameter eTypeParameter = i.next();
				// result.append(ETypeParameterItemProvider.getText(eTypeParameter));
				if (i.hasNext()) {
					result.append(", ");
				}
			}
			result.append(">");
		}

		if (!eClass.getEGenericSuperTypes().isEmpty()) {
			result.append(" -> ");
			for (Iterator<EGenericType> i = eClass.getEGenericSuperTypes().iterator(); i.hasNext();) {
				EGenericType eGenericSuperType = i.next();
				// result.append(EGenericTypeItemProvider.getText(eGenericSuperType));
				if (i.hasNext()) {
					result.append(", ");
				}
			}
		}
		if (eClass.getInstanceTypeName() != null) {
			result.append(" [");
			result.append(eClass.getInstanceTypeName());
			result.append("]");
		}

		return result.toString();
	}

	/**
	 * This returns EClass image from the EcoreEditPlugin
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, EcoreEditPlugin.INSTANCE.getImage("full/obj16/EClass"));
	}

	// ----- CreateChildCommand.Helper ----

	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {

		if (feature == ModelPackage.Literals.NO2_MODEL__CONTENTS) {
			return getString("_UI_CreateChild_EObject");

			// new Object[] { getTypeText(child),getFeatureText(feature),
			// getTypeText(owner)}
		}

		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * This returns the icon image for
	 * {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 */
	@Override
	public Object getCreateChildImage(Object owner, Object feature, Object child, Collection<?> selection) {

		if (feature == ModelPackage.Literals.NO2_MODEL__CONTENTS) {
			return EMFEditPlugin.INSTANCE.getImage("full/ctool16/CreateChild");
		}

		return super.getCreateChildImage(owner, feature, child, selection);
	}

}



// /**
// * Get a translated string from the resource locator, with substitutions.
// */
// public String getUntranslatedString(String key)
// {
// String result;
// try {
// result = getNewResourceLocator().getString(key);
// } catch (MissingResourceException e1) {
// try {
// result = getResourceLocator().getString(key);
// } catch (MissingResourceException e2) {
// result = key + " (text not found)";
// }
// }
// return result;
// }
