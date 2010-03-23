/*******************************************************************************
 * 
 * Copyright (c) 2004 - 2010 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Eike Stepper - initial API and implementation
 *    Victor Roldan Betancort - maintenance
 *    Stephan Zehrer - extract and adapt the class from PackageRegistryDialog
 *******************************************************************************/

package net.zehrer.no2.ui.provider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.cdo.common.model.CDOPackageInfo;
import org.eclipse.emf.cdo.common.model.CDOPackageRegistry;
import org.eclipse.emf.cdo.internal.common.model.CDOPackageRegistryImpl;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.net4j.util.ObjectUtil;

public class EPackageContentProvider implements IStructuredContentProvider, ITreeContentProvider {
	private static final Object[] NO_ELEMENTS = {};

	private CDOSession session;

	public EPackageContentProvider(CDOSession session) {
		this.session = session;
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if (newInput instanceof EPackage) {
			if (!ObjectUtil.equals(this.session, newInput)) {
				this.session = (CDOSession) newInput;
			}
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement != session) {
			return NO_ELEMENTS;
		}
		
//		CDOPackageRegistryImpl test;
		
		//Source: EMFUtil.getSortedRegistryEntries(session.getPackageRegistry());
		CDOPackageRegistry packageRegistry = session.getPackageRegistry();
		
		//CDOPackageRegistryImpl
		
		List<EPackage> result = new ArrayList<EPackage>();
		for (Object value : packageRegistry.values()) {
//			if (value instanceof EPackage) {
//	            result.add((EPackage)value);
//	        }
			
			if (value instanceof CDOPackageInfo) {
				result.add(((CDOPackageInfo)value).getEPackage(true));
			}
	      }

		return result.toArray(new EPackage[result.size()]);

	}

	@Override
	public Object[] getChildren(Object obj) {
		
		if (obj instanceof EPackage) {
			EPackage ePackage = (EPackage) obj;
			List<ENamedElement> result = new ArrayList<ENamedElement>();
			for (EObject value : ePackage.eContents()) {
				if (value instanceof EClass || value instanceof EPackage)
					result.add((ENamedElement)value);
			}
			
			return result.toArray(new ENamedElement[result.size()]);
		}
		
		return null;
	}

	@Override
	public Object getParent(Object element) {
		
			// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
	
		if (element instanceof EPackage)
			return true;
		
		return false;
	}
}
