package net.zehrer.no2.semantic.editor.provider;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import net.zehrer.no2.semantic.editor.model.AbstractNode;
import net.zehrer.no2.semantic.editor.partitioner.SemanticTextPartitionScanner;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.services.log.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.IFileEditorInput;

public class SemanticDocumentProvider extends ResourceDocumentProvider {
	
	private AbstractNode textModel = null;
	
	@Inject private Logger logger;
	
	@PostConstruct
	public void init() {
		// If you don't inject the Logger you could alternatively 
		// get it via the IEclipseContext with the following statement
		// Logger log= (Logger) context.get(Logger.class.getName());

		System.out.println(logger!=null);
		
		logger.info("Init SemanticDocumentProvider");
	}
	
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
		
		

		
//		Reader in= null;
//
//		try {
//
//			if (encoding == null)
//				encoding= getDefaultEncoding();
//
//			in= new BufferedReader(new InputStreamReader(contentStream, encoding), DEFAULT_FILE_SIZE);
//			StringBuffer buffer= new StringBuffer(DEFAULT_FILE_SIZE);
//			char[] readBuffer= new char[2048];
//			int n= in.read(readBuffer);
//			while (n > 0) {
//				buffer.append(readBuffer, 0, n);
//				n= in.read(readBuffer);
//			}
//
//			document.set(buffer.toString());
//
//		} catch (IOException x) {
//			String message= (x.getMessage() != null ? x.getMessage() : ""); //$NON-NLS-1$
//			IStatus s= new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID, IStatus.OK, message, x);
//			throw new CoreException(s);
//		} finally {
//			try {
//				if (in != null)
//					in.close();
//				else
//					contentStream.close();
//			} catch (IOException x) {
//			}
//		}
	}

	protected void setTextModel(AbstractNode model) {
		this.textModel = model;
	}
	
	public AbstractNode getTextModel() {
		return this.textModel;
	}


}