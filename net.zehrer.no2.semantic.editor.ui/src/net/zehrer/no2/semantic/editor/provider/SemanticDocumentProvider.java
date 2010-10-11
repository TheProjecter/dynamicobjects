package net.zehrer.no2.semantic.editor.provider;

//import javax.annotation.PostConstruct;
//import javax.inject.Inject;

import net.zehrer.no2.provider.ResourceDocumentProvider;
import net.zehrer.no2.semantic.editor.model.AbstractNode;
import net.zehrer.no2.semantic.editor.partitioner.SemanticTextPartitionScanner;

import org.eclipse.core.runtime.CoreException;
//import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.IFileEditorInput;

public class SemanticDocumentProvider extends ResourceDocumentProvider {
	
	public SemanticDocumentProvider(EditingDomain editingDomain) {
		super(editingDomain);
	}

	private AbstractNode textModel = null;
	
//	@Inject private Logger logger;
	
	
	protected IDocument createDocument(Object element) throws CoreException {

		// call StorageDocumentProvider.createDocument
		// creating an empty document,  call setDocumentContent & setupDocument
		IDocument document = super.createDocument(element);
		
		if (document != null) {
			
			// setup DocumentPartitioner(
			IDocumentPartitioner partitioner =
				new FastPartitioner(
					new SemanticTextPartitionScanner(),
					SemanticTextPartitionScanner.PARTITION_TYPES);
			partitioner.connect(document);
			
			document.setDocumentPartitioner(partitioner);
		}
		
		return document;
	}
	
	@Override
	protected boolean setDocumentContent(IDocument document, IFileEditorInput editorInput) throws CoreException {
		
		    EObject model = getResource().getEObject("/");
	
		    if (model instanceof AbstractNode) {
		    	setTextModel((AbstractNode)model);
		    	
		    	document.set(getTextModel().serialize());
		    	return true;
		    }
		
		return false;
		
	}

	protected void setTextModel(AbstractNode model) {
		this.textModel = model;
	}
	
	public AbstractNode getTextModel() {
		return this.textModel;
	}


}
