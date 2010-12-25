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

package org.eclipse.emf.addon.adapter;


import org.eclipse.emf.addon.editor.ProblemIndication;
import org.eclipse.emf.addon.resource.ResourceUtil;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;

public class ProblemIndicationAdapter extends EContentAdapter {
	
	protected ProblemIndication problemIndication;
	
	public ProblemIndicationAdapter (ProblemIndication problemIndication) {
		this.problemIndication = problemIndication;

	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getNotifier() instanceof Resource) {
			switch (notification.getFeatureID(Resource.class)) {
			case Resource.RESOURCE__IS_LOADED:
			case Resource.RESOURCE__ERRORS:
			case Resource.RESOURCE__WARNINGS: {
				Resource resource = (Resource) notification.getNotifier();
				Diagnostic diagnostic = ResourceUtil.analyzeResourceProblems(resource, null);
				if (diagnostic.getSeverity() != Diagnostic.OK) {
					problemIndication.put(resource, diagnostic);
				} else {
					problemIndication.remove(resource);
				}
				problemIndication.update();
				}
				break;
			}
		} else {
			super.notifyChanged(notification);
		}
	}

	@Override
	protected void setTarget(Resource target) {
		basicSetTarget(target);
	}

	@Override
	protected void unsetTarget(Resource target) {
		basicUnsetTarget(target);
	}
}
