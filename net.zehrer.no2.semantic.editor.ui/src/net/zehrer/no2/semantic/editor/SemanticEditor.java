package net.zehrer.no2.semantic.editor;

import net.zehrer.no2.semantic.editor.coloring.ColorManager;
import net.zehrer.no2.semantic.editor.outline.DebugContentOutlinePage;
import net.zehrer.no2.semantic.editor.provider.SemanticDocumentProvider;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory.Descriptor.Registry;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class SemanticEditor extends TextEditor {

	private ColorManager colorManager;
	private DebugContentOutlinePage fOutlinePage;
	
	private EditingDomain editingDomain;
	private ComposedAdapterFactory adapterFactory;

	public SemanticEditor() {
		super();
		colorManager = new ColorManager();
		
		setSourceViewerConfiguration(new EditorConfiguration(colorManager));
		
		initializeEditingDomain();
		
		// TODO: inject?
		setDocumentProvider(new SemanticDocumentProvider());
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
	
	public Object getAdapter(Class required) {
		if (IContentOutlinePage.class.equals(required)) {
			if (fOutlinePage == null) {
				fOutlinePage= new DebugContentOutlinePage(getDocumentProvider(), this);
				if (getEditorInput() != null)
					fOutlinePage.setInput(getEditorInput());
			}
			return fOutlinePage;
		}
		return super.getAdapter(required);
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
	

}
