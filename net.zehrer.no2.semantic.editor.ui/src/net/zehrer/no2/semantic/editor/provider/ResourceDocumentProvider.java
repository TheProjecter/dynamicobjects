package net.zehrer.no2.semantic.editor.provider;

//import net.zehrer.no2.semantic.editor.partitioner.DebugPartitioner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import net.zehrer.no2.semantic.editor.partitioner.SemanticTextPartitionScanner;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.FileDocumentProvider;
import org.eclipse.ui.part.FileEditorInput;

public class ResourceDocumentProvider extends FileDocumentProvider {

	protected IDocument createDocument(Object element) throws CoreException {

		IDocument document = super.createDocument(element);
		
		if (document != null) {
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
	protected void setDocumentContent(IDocument document, InputStream contentStream, String encoding) throws CoreException {

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
	
	private void initializeResourceObject(org.eclipse.ui.IEditorInput editorInput) {
		
		IFileEditorInput input = (FileEditorInput) editorInput;
		
		IFile inputFile = input.getFile();
		
		//TODO
		//net.zehrer.emftext.test.resource.test.mopp.TestNature.activate(inputFile.getProject());
		
		String path = inputFile.getFullPath().toString();
		URI uri = URI.createPlatformResourceURI(path, true);
		
		ResourceSet resourceSet = editingDomain.getResourceSet();
		
		net.zehrer.emftext.test.resource.test.ITestTextResource loadedResource = (net.zehrer.emftext.test.resource.test.ITestTextResource) resourceSet.getResource(uri, false);
		if (loadedResource == null) {
			try {
				org.eclipse.emf.ecore.resource.Resource demandLoadedResource = null;
				// here we do not use getResource(), because 'resource' might be null, which is ok
				// when initializing the resource object
				net.zehrer.emftext.test.resource.test.ITestTextResource currentResource = this.resource;
				if (currentResource != null && !currentResource.getURI().fileExtension().equals(uri.fileExtension())) {
					// do not attempt to load if file extension has changed in a 'save as' operation	
				}
				else {
					demandLoadedResource = resourceSet.getResource(uri, true);
				}
				if (demandLoadedResource instanceof net.zehrer.emftext.test.resource.test.ITestTextResource) {
					setResource((net.zehrer.emftext.test.resource.test.ITestTextResource) demandLoadedResource);
				} else {
					// the resource was not loaded by an EMFText resource, but some other EMF resource
					net.zehrer.emftext.test.resource.test.ui.TestUIPlugin.showErrorDialog("No EMFText resource.", "The file '" + uri.lastSegment() + "' of type '" + uri.fileExtension() + "' can not be handled by the TestEditor.");
					// close this editor because it can not present the resource
					close(false);
				}
			} catch (Exception e) {
				net.zehrer.emftext.test.resource.test.ui.TestUIPlugin.logError("Exception while loading resource in " + this.getClass().getSimpleName() + ".", e);
			}
		} else {
			setResource(loadedResource);
		}
	}
}
