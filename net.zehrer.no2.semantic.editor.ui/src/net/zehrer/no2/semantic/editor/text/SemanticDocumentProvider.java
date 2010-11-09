package net.zehrer.no2.semantic.editor.text;

//import javax.annotation.PostConstruct;
//import javax.inject.Inject;

import net.zehrer.no2.semantic.editor.model.AbstractNode;
import net.zehrer.no2.text.IResourceDocument;
import net.zehrer.no2.text.ResourceDocumentProvider;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.ui.IFileEditorInput;

public class SemanticDocumentProvider extends ResourceDocumentProvider {
	
	public SemanticDocumentProvider(ResourceSet resourceSet) {
		super(resourceSet);
	}
	
	@Override
	protected IResourceDocument createDocument(Object element) throws CoreException {

		// call ResourceDocumentProvider.createDocument
		// creating an empty document,  call setDocumentContent & setupDocument
		IResourceDocument document = super.createDocument(element);
		
		if (document != null) {
			
			// setup DocumentPartitioner(
			IDocumentPartitioner partitioner = new SemanticTextPartition();
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}
		
		return document;
	}
	
	/*
	 * @see ResourceDocumentProvider#setDocumentContent(IResourceDocument, IFileEditorInput)
	 */
	@Override
	protected boolean setDocumentContent(IResourceDocument document, IFileEditorInput editorInput) throws CoreException {
					
	    EObject model = document.getResource().getEObject("/");
		
	    if (model instanceof AbstractNode) {
	    	AbstractNode textModel = (AbstractNode) model;
	    	
	    	document.setTextModel(textModel);
	    	
	    	document.set(textModel.serialize());
	    	return true;
	    }
		

		return false;
		
	}

}
