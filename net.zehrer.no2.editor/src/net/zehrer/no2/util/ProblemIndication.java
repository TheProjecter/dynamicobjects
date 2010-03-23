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

package net.zehrer.no2.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.PlatformUI;

public class ProblemIndication implements Runnable {

	protected Object source;
	protected boolean enabled = true; // TODO: syncronized??

	protected Map<Resource, Diagnostic> resourceToDiagnosticMap = new LinkedHashMap<Resource, Diagnostic>();

	public void setState(boolean enabled) {
		this.enabled = enabled;
	}

	public void setSource(Object source) {
		this.source = source;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public void put(Resource resource, Diagnostic diagnostic) {
		this.resourceToDiagnosticMap.put(resource, diagnostic);
	}

	public void remove(Resource resource) {
		this.resourceToDiagnosticMap.remove(resource);
	}
	
	public boolean containsKey(Resource resource) {
		return this.resourceToDiagnosticMap.containsKey(resource);
	}	
	

	public ProblemIndication() {
	}

	/**
	 * Updates the problems indication with the information described in the
	 * specified diagnostic.
	 */
	public void update() {

		if (isEnabled()) {
			PlatformUI.getWorkbench().getDisplay().asyncExec(this);
		}

	}

	public void run() {

		if (isEnabled()) {
			BasicDiagnostic diagnostic = new BasicDiagnostic(Diagnostic.OK, "net.zehrer.no2.model.editor", 0, null, new Object[] { this.source });
			for (Diagnostic childDiagnostic : resourceToDiagnosticMap.values()) {
				if (childDiagnostic.getSeverity() != Diagnostic.OK) {
					diagnostic.add(childDiagnostic);
				}
			}

			// int lastEditorPage = getPageCount() - 1;
			// if (lastEditorPage >= 0 && getEditor(lastEditorPage) instanceof
			// ProblemEditorPart) {
			// ((ProblemEditorPart)
			// getEditor(lastEditorPage)).setDiagnostic(diagnostic);
			// if (diagnostic.getSeverity() != Diagnostic.OK) {
			// setActivePage(lastEditorPage);
			// }
			// } else

			// if (diagnostic.getSeverity() != Diagnostic.OK) {
			// ProblemEditorPart problemEditorPart = new ProblemEditorPart();
			// problemEditorPart.setDiagnostic(diagnostic);
			// problemEditorPart.setMarkerHelper(markerHelper);
			// try {
			// addPage(++lastEditorPage, problemEditorPart, getEditorInput());
			// setPageText(lastEditorPage, problemEditorPart.getPartName());
			// setActivePage(lastEditorPage);
			// // showTabs();
			// } catch (PartInitException exception) {
			// NO2EditorPlugin.INSTANCE.log(exception);
			// }
			// }

			// if (markerHelper.hasMarkers(modelEditingDomain.getResourceSet()))
			// {
			// markerHelper.deleteMarkers(modelEditingDomain.getResourceSet());
			// if (diagnostic.getSeverity() != Diagnostic.OK) {
			// try {
			// markerHelper.createMarkers(diagnostic);
			// } catch (CoreException exception) {
			// NO2EditorPlugin.INSTANCE.log(exception);
			// }
			// }
			// }
		}

	}

}
