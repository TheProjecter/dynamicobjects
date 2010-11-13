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

import java.util.Collection;

import net.zehrer.no2.semantic.editor.NO2UiPlugin;
import net.zehrer.no2.semantic.editor.model.CompositeNode;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

public class LeafNodeItemProvider extends ItemProviderAdapter implements IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IEditingDomainItemProvider {

	// implements 
	// IStructuredItemContentProvider,
	// IItemLabelProvider,IItemPropertySource
	// extends ReflectiveItemProvider

	public LeafNodeItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);

	}

	// / ----- ItemProviderAdapter -----

	/**
	 * Return the resource locator for the NEW item provider's resources in this
	 * subclass
	 * @category ItemProviderAdapter
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return NO2UiPlugin.INSTANCE;
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object.
	 * @category ItemProviderAdapter
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {

	}
	
	// ----- IItemLabelProvider ----

	/**
	 * This does the same thing as ILabelProvider.getText, it fetches the label
	 * text specific to this object instance.
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
		
//			if (! eClassifier.getETypeParameters().isEmpty()) {
//				result.append("<");
//				for (Iterator<ETypeParameter> i = eClassifier.getETypeParameters().iterator(); i.hasNext();) {
//					ETypeParameter eTypeParameter = i.next();
//					result.append(ETypeParameterItemProvider.getText(eTypeParameter)); // BUG??
//					if (i.hasNext()) {
//						result.append(", ");
//					}
//				}
//				result.append(">");
//			}
			
			if (eClassifier.getInstanceTypeName() != null) {
				result.append(" [");
				result.append(eClassifier.getInstanceTypeName());
				result.append("]");
			}
		}



//		if (!eClass.getEGenericSuperTypes().isEmpty()) {
//			result.append(" -> ");
//			for (Iterator<EGenericType> i = eClass.getEGenericSuperTypes().iterator(); i.hasNext();) {
//				EGenericType eGenericSuperType = i.next();
//				// result.append(EGenericTypeItemProvider.getText(eGenericSuperType));
//				if (i.hasNext()) {
//					result.append(", ");
//				}
//			}
//		}


		return result.toString();
	}

	/**
	 * This returns EClass image from the EcoreEditPlugin
	 * @category ItemProviderAdapter
	 */
	@Override
	public Object getImage(Object object) {
		
		if (object instanceof CompositeNode) {
			return overlayImage(object, EcoreEditPlugin.INSTANCE.getImage("full/obj16/EPackage"));
		}		
		
		return overlayImage(object, EcoreEditPlugin.INSTANCE.getImage("full/obj16/EObject"));
	}

	// ----- CreateChildCommand.Helper ----

	/**
	 * @category ItemProviderAdapter
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {

//		if (feature == ModelPackage.Literals.NO2_MODEL__CONTENTS) {
//			return getString("_UI_CreateChild_EObject");
//
//			// new Object[] { getTypeText(child),getFeatureText(feature),
//			// getTypeText(owner)}
//		}

		return super.getCreateChildText(owner, feature, child, selection);
	}

	/**
	 * This returns the icon image for
	 * {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * @category ItemProviderAdapter
	 */
	@Override
	public Object getCreateChildImage(Object owner, Object feature, Object child, Collection<?> selection) {

//		if (feature == ModelPackage.Literals.NO2_MODEL__CONTENTS) {
//			return EMFEditPlugin.INSTANCE.getImage("full/ctool16/CreateChild");
//		}

		return super.getCreateChildImage(owner, feature, child, selection);
	}

}
