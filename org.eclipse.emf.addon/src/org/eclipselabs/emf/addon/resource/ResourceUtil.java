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

package org.eclipselabs.emf.addon.resource;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipselabs.emf.addon.Messages;

public class ResourceUtil {

	/**
	 * Returns a diagnostic describing the errors and warnings listed in the
	 * resource and the specified exception (if any).
	 * 
	 * @category ModelEdit
	 * 
	 * TODO: fix messages 
	 */
	public static Diagnostic analyzeResourceProblems(Resource resource, Exception exception) {
		if (!resource.getErrors().isEmpty() || !resource.getWarnings().isEmpty()) { // getString("_UI_CreateModelError_message",
																					// resource.getURI()
			BasicDiagnostic basicDiagnostic = new BasicDiagnostic(Diagnostic.ERROR, "net.zehrer.no2.model.editor", 0, Messages._UI_CreateModelError_message,
					new Object[] { exception == null ? (Object) resource : exception });
			basicDiagnostic.merge(EcoreUtil.computeDiagnostic(resource, true));
			return basicDiagnostic;
		} else if (exception != null) { 
//			getString("_UI_CreateModelError_message", resource.getURI())
			return new BasicDiagnostic(Diagnostic.ERROR, "net.zehrer.no2.model.editor", 0, Messages._UI_CreateModelError_message, new Object[] { exception });
		} else {
			return Diagnostic.OK_INSTANCE;
		}
	}
	
	
	/**
	 * This returns whether something has been persisted to the URI of the
	 * specified resource. The implementation uses the URI converter from the
	 * editor's resource set to try to open an input stream.
	 * 
	 * @category ModelEdit
	 */
	public static boolean isPersisted(Resource resource, ResourceSet resourceSet) {
		boolean result = false;
		try {
			InputStream stream = resourceSet.getURIConverter().createInputStream(resource.getURI());
			if (stream != null) {
				result = true;
				stream.close();
			}
		} catch (IOException e) {
			// Ignore
		}
		return result;
	}
}
