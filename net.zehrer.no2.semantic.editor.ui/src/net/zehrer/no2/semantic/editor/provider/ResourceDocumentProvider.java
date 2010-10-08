package net.zehrer.no2.semantic.editor.provider;

//import net.zehrer.no2.semantic.editor.partitioner.DebugPartitioner;
import javax.inject.Inject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.editors.text.FileDocumentProvider;
import org.eclipse.ui.part.FileEditorInput;

public abstract class ResourceDocumentProvider extends FileDocumentProvider {
	
	@Inject protected EditingDomain editingDomain;

	private Resource resource;
	
	/*
	 * @see AbstractDocumentProvider#createDocument(Object)
	 */
	@Override
	protected IDocument createDocument(Object element) throws CoreException {

		if (element instanceof IFileEditorInput) {
			IDocument document= createEmptyDocument();
			
			IFileEditorInput fileInput = (IFileEditorInput) element;
			
			if (initializeResourceObject (fileInput) &&
				setDocumentContent(document, fileInput)) {
				
				setupDocument(element, document);
				return document;
			}
		}

		return null;
	}
	

	abstract protected boolean setDocumentContent(IDocument document, IFileEditorInput editorInput) throws CoreException;

	
	// Initial source EMFText generated editor code
	protected boolean initializeResourceObject(IEditorInput editorInput) {
		
		
		IFileEditorInput input = (FileEditorInput) editorInput;
		
		IFile inputFile = input.getFile();
		
		//TODO
		//net.zehrer.emftext.test.resource.test.mopp.TestNature.activate(inputFile.getProject());
		
		// create URI from file path
		String path = inputFile.getFullPath().toString();
		URI uri = URI.createPlatformResourceURI(path, true);
		
		ResourceSet resourceSet = editingDomain.getResourceSet();
	
		Resource loadedResource = resourceSet.getResource(uri, false);
		
		if (loadedResource == null) {
			try {
				Resource demandLoadedResource = null;
				// here we do not use getResource(), because 'resource' might be null, which is ok
				// when initializing the resource object
				Resource currentResource = this.resource;
				if (currentResource != null && !currentResource.getURI().fileExtension().equals(uri.fileExtension())) {
					// do not attempt to load if file extension has changed in a 'save as' operation	
				}
				else {
					demandLoadedResource = resourceSet.getResource(uri, true);
				}
				
				setResource(demandLoadedResource);
				return true;

				//TODO ERROR HANDLING (e.g. check for correct model content )
				// -> close editor

			} catch (Exception e) {
				// TODO TestUIPlugin.logError("Exception while loading resource in " + this.getClass().getSimpleName() + ".", e);
				return false;
			}
		} else {
			setResource(loadedResource);
			return true;
		}
	}

	
	protected void setResource(Resource resource) {
		assert resource != null;
		this.resource = resource;
		if (this.resource.getErrors().isEmpty()) {
			EcoreUtil.resolveAll(this.resource);
		}
	}
	
	public Resource getResource() {
		return this.resource;
	}
	
	
}
