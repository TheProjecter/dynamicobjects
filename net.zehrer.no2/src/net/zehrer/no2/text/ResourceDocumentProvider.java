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

package net.zehrer.no2.text;

//import net.zehrer.no2.semantic.editor.partitioner.DebugPartitioner;
//import javax.inject.Inject;  // how to solve in e4/SDK4.0?

import net.zehrer.no2.manager.NO2ResourceManager;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.editors.text.FileDocumentProvider;
import org.eclipse.ui.editors.text.StorageDocumentProvider;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Abstracted document provider specialized for a EMF resource.
 * TODO: analysis if support of sharing is complete.
 */
public abstract class ResourceDocumentProvider extends FileDocumentProvider {
	
	//@Inject 
	protected ResourceSet resourceSet;

	
	public  ResourceDocumentProvider (ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	/**
	 * @see StorageDocumentProvider#createEmptyDocument()
	 */
	@Override
	protected IResourceDocument createEmptyDocument() {
		return new ResourceDocument();
	}
	
	/*
	 * @see AbstractDocumentProvider#createDocument(Object)
	 */
	@Override
	protected IResourceDocument createDocument(Object element) throws CoreException {
		
		// similar implementation as in the super class

		if (element instanceof IFileEditorInput) {
			IResourceDocument document = createEmptyDocument();
			
			IFileEditorInput fileInput = (IFileEditorInput) element;
			
			if (initializeResourceObject (document, fileInput) &&
				setDocumentContent(document, fileInput)) {
				
				setupDocument(element, document);
				return document;
			}
		}

		return null;
	}
	
	protected boolean setDocumentContent(IResourceDocument document, IEditorInput editorInput, String encoding) throws CoreException {
		if (editorInput instanceof IFileEditorInput) {
		  return setDocumentContent (document, (IFileEditorInput) editorInput);
		}  
		
		return super.setDocumentContent( document,  editorInput,  encoding);
	}
	
	/*
	 * @see FileDocumentProvider#disposeElementInfo(Object, AbstractDocumentProvider.ElementInfo)
	 */
	@Override
	protected void disposeElementInfo(Object element, ElementInfo info) {
		if (info.fDocument instanceof IResourceDocument) {
			IResourceDocument document = (IResourceDocument) info.fDocument;
			document.disposeResource();
		}
		super.disposeElementInfo(element, info);
	}
	
	/*
	 * Copy of the XtextDocumentProvider implementation
	 * @see FileDocumentProvider#isDeleted(Object)
	 */
	@Override
	public boolean isDeleted(Object element) {
		if (element instanceof IFileEditorInput) {
			final IFileEditorInput input = (IFileEditorInput) element;

			final IPath path = input.getFile().getLocation();
			if (path == null) {
				// return true;
				return !input.getFile().exists(); // fixed for EFS compatibility
			}
			return !path.toFile().exists();
		}
		return super.isDeleted(element);
	}
	
	
	/**
	 * Abstract subtask of  {@link #createDocument(Object)}.
	 * Set the text content of the document based on the model specific document provider
	 * @param document
	 * @param editorInput
	 * @return true - if document content is set.
	 * @throws CoreException
	 */
	abstract protected boolean setDocumentContent(IResourceDocument document, IFileEditorInput editorInput) throws CoreException;
	
	
	/*
	 * @see AbstractDocumentProvider#doSaveDocument(IProgressMonitor, Object, IDocument, boolean)
	 */
	@Override
	protected void doSaveDocument(IProgressMonitor monitor, Object element, IDocument document, boolean overwrite) throws CoreException {
		
		if (element instanceof IFileEditorInput && document instanceof IResourceDocument) {

			monitor.beginTask("Save Document", 1);

			NO2ResourceManager.saveModel(((IResourceDocument)document).getResource(), monitor);
						
		} else {
			super.doSaveDocument(monitor, element, document, overwrite);
		}	
	}

	
	// Initial source EMFText generated editor code
	protected boolean initializeResourceObject(IResourceDocument document, IEditorInput editorInput) {
		
		IFileEditorInput input = (FileEditorInput) editorInput;
		
		IFile file = input.getFile();
		URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		Resource loadedResource = this.resourceSet.getResource(uri, false);
		
		
		//TODO
		//net.zehrer.emftext.test.resource.test.mopp.TestNature.activate(inputFile.getProject());
			
		if (loadedResource == null) {
			try {
				Resource demandLoadedResource = null;

				Resource currentResource = document.getResource();
				
				if (currentResource != null && !currentResource.getURI().fileExtension().equals(uri.fileExtension())) {
					// do not attempt to load if file extension has changed in a 'save as' operation	
				}
				else {
					demandLoadedResource = resourceSet.getResource(uri, true);
				}
				
				document.setResource(demandLoadedResource);
				return true;

				//TODO ERROR HANDLING (e.g. check for correct model content )
				// -> close editor

			} catch (Exception e) {
				// TODO TestUIPlugin.logError("Exception while loading 
				// resource in
				//  " + this.getClass().getSimpleName() + ".", e);
				return false;
			}
		} else {
			document.setResource(loadedResource);
			return true;
		}
	}
	
}
