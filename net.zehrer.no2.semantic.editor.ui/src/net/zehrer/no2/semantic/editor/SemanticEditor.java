package net.zehrer.no2.semantic.editor;

import net.zehrer.no2.semantic.editor.coloring.ColorManager;
import net.zehrer.no2.semantic.editor.outline.OutlineContentProvider;
import net.zehrer.no2.semantic.editor.outline.OutlineLabelProvider;
import net.zehrer.no2.semantic.editor.text.SemanticDocumentProvider;
import net.zehrer.no2.ui.outline.GenericContentOutlinePage;
import net.zehrer.no2.ui.outline.IEditor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory.Descriptor.Registry;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class SemanticEditor extends AbstractDecoratedTextEditor implements IEditor { // extends TextEditor {

	private ColorManager colorManager;
	private GenericContentOutlinePage fOutlinePage;
	
	private EditingDomain editingDomain;
	private ComposedAdapterFactory adapterFactory;

	public SemanticEditor() {
		super();
		colorManager = new ColorManager();
		
		setSourceViewerConfiguration(new EditorConfiguration(colorManager));
		
		initializeEditingDomain();
		
		// TODO: inject?
		setDocumentProvider(new SemanticDocumentProvider(this.editingDomain.getResourceSet()));
	}

	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}
	
	public void doSave(IProgressMonitor monitor) {
		super.doSave(monitor);
		if (fOutlinePage != null)
			fOutlinePage.update();
	}
	
	//TODO SourceViewerDecorationSupport
	
	@SuppressWarnings("rawtypes")
	public Object getAdapter(Class required) {
		if (IContentOutlinePage.class.equals(required)) {
			if (fOutlinePage == null) {
				fOutlinePage= createOutlinePage();
			}
			return fOutlinePage;
		}
		return super.getAdapter(required);
	}

	private GenericContentOutlinePage createOutlinePage() {
		
		GenericContentOutlinePage outlinePage = new GenericContentOutlinePage(this);
		outlinePage.setContentProvider(new OutlineContentProvider());
		outlinePage.setLabelProvider(new OutlineLabelProvider());
		
		IEditorInput input = getEditorInput();
		if (input != null) {
			// Get the input by the DocumentProvider 
			fOutlinePage.setInput(getDocumentProvider().getDocument(input));
		}
		
		return outlinePage;
	}
	
	private void initializeEditingDomain() {
		adapterFactory = new ComposedAdapterFactory(Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		
		org.eclipse.emf.common.command.BasicCommandStack commandStack = new org.eclipse.emf.common.command.BasicCommandStack();
		// CommandStackListeners can listen for changes. Not sure whether this is needed.
		
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory,commandStack, new java.util.LinkedHashMap<Resource, Boolean>());
	}

	// ------ IEditor  -----
	
	@Override
	public EditingDomainActionBarContributor getActionBarContributor() {
		return null;
	}

	@Override
	public void createContextMenuFor(StructuredViewer viewer) {
		
	}
	

}
