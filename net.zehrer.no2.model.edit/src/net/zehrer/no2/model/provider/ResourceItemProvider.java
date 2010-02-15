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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

public class ResourceItemProvider extends ItemProviderAdapter implements  ITreeItemContentProvider, IItemLabelProvider, IEditingDomainItemProvider {

	public ResourceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);

	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object.
	 */
//	@Override
//	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
//		super.collectNewChildDescriptors(newChildDescriptors, object);
//
//		newChildDescriptors.add(createChildParameter(EcorePackage.Literals.EPACKAGE__ECLASSIFIERS, EcoreFactory.eINSTANCE.createEClass()));
//
//	}
	
	@Override
	protected Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		
		// sync this methode with Page.generateTableColumns

		childrenFeatures = new ArrayList<EStructuralFeature>();
		Resource resource = (Resource) object;
//		EClass eClass = eObject.eClass();
//		
//	    for (EAttribute eAttribute : eClass.getEAllAttributes()) {
//	    	// TODO: analyse the ReflectiveItemProvider cause it seems it handle some special cases e.g. FeatureMap
//	    	childrenFeatures.add(eAttribute);
//	    }
	      
		return childrenFeatures;
	}

	/**
	 * This returns the label text for the adapted class.
	 */
	@Override
	public String getText(Object object) {
		Resource resource = (Resource) object;
		return resource.getURI() == null ? "" : resource.getURI().lastSegment();
	}

}
