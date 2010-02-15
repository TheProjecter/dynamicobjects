/*******************************************************************************
 * Copyright (c) 2010 Stephan Zehrer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephan Zehrer - initial API and implementation
 *******************************************************************************/

package net.zehrer.no2.model.adapter;

import net.zehrer.no2.model.ModelPackage;
import net.zehrer.no2.model.NO2Model;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class NO2ModelAdapter extends AdapterImpl {

	@Override
	public void notifyChanged(Notification notification) {
		
		// if nothing changed ... do nothing
		if (notification.isTouch()) return;
		
		
		if (notification.getNotifier() instanceof NO2Model) {
			NO2Model model = (NO2Model)notification.getNotifier();
			
			if (notification.getFeatureID(NO2Model.class) ==  ModelPackage.NO2_MODEL__CONTENTS) 
			{
				if (notification.getEventType() == Notification.ADD) {
					
					EObject value = (EObject) notification.getNewValue();
					Resource resource = model.addClass(value.eClass());  
					resource.getContents().add(value);
				}

				if (notification.getEventType() == Notification.REMOVE) {
					
					EcoreUtil.remove((EObject)notification.getOldValue());

			}
				
			}
		}
		
	}
}
