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

package net.zehrer.no2.model;


public class ModelSession {

	
	/**
	 * This is for implementing {@link IEditorPart} and simply saves the model
	 * file.
	 * 
	 * @category EditorPart
	 */
//	@Override
//	public void doSave(IProgressMonitor progressMonitor) {
//		// Save only resources that have actually changed.
//
//		final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
//		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
//		saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8"); // initialObjectCreationPage.getEncoding()
//		saveOptions.put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE); // <---
//																			// !!!!!!
//																			// TODO:
//																			// test
//
//		// Do the work within an operation because this is a long running
//		// activity that modifies the workbench.
//		//
//		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
//			// This is the method that gets invoked when the operation runs.
//			//
//			@Override
//			public void execute(IProgressMonitor monitor) {
//				// Save the resources to the file system.
//				//
//				boolean first = true;
//				for (Resource resource : modelEditingDomain.getResourceSet().getResources()) {
//					if ((first || !resource.getContents().isEmpty() || isPersisted(resource)) && !modelEditingDomain.isReadOnly(resource)) {
//						try {
//							long timeStamp = resource.getTimeStamp();
//							resource.save(saveOptions);
//							if (resource.getTimeStamp() != timeStamp) {
//								savedResources.add(resource);
//							}
//						} catch (Exception exception) {
//							resourceToDiagnosticMap.put(resource, analyzeResourceProblems(resource, exception));
//						}
//						first = false;
//					}
//				}
//			}
//		};
//
//		updateProblemIndication = false;
//		try {
//			// This runs the options, and shows progress.
//			//
//			new ProgressMonitorDialog(getSite().getShell()).run(true, false, operation);
//
//			// Refresh the necessary state.
//			//
//			((BasicCommandStack) modelEditingDomain.getCommandStack()).saveIsDone();
//			firePropertyChange(IEditorPart.PROP_DIRTY);
//		} catch (Exception exception) {
//			// Something went wrong that shouldn't.
//			//
//			NO2EditorPlugin.INSTANCE.log(exception);
//		}
//		updateProblemIndication = true;
//		updateProblemIndication();
//	}
	
}
