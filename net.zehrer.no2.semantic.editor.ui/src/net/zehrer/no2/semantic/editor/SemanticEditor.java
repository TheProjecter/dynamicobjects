package net.zehrer.no2.semantic.editor;

import net.zehrer.no2.semantic.editor.coloring.ColorManager;
import net.zehrer.no2.semantic.editor.model.provider.EditorItemProviderAdapterFactory;
import net.zehrer.no2.semantic.editor.outline.OutlineContentProvider;
import net.zehrer.no2.semantic.editor.outline.OutlineLabelProvider;
import net.zehrer.no2.semantic.editor.outline.OutlineSelectionListener;
import net.zehrer.no2.semantic.editor.text.SemanticDocumentProvider;
import net.zehrer.no2.ui.editor.GeneriContentPropertySheetPage;
import net.zehrer.no2.ui.editor.GenericContentOutlinePage;
import net.zehrer.no2.ui.editor.IEditor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory.Descriptor.Registry;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;

public class SemanticEditor extends TextEditor implements IEditor { // extends TextEditor {

	private ColorManager colorManager;
	
	private GenericContentOutlinePage fOutlinePage;
	private GeneriContentPropertySheetPage fPropertyPage;
	
	private OutlineSelectionListener outlineSelectionListener;
	
	private AdapterFactoryEditingDomain editingDomain;
	private ComposedAdapterFactory adapterFactory;

	public SemanticEditor() {
		super();
		colorManager = new ColorManager();
		
		setSourceViewerConfiguration(new EditorConfiguration(colorManager));
		
		initializeEditingDomain();
		
		// TODO: inject?
		setDocumentProvider(new SemanticDocumentProvider(this.editingDomain.getResourceSet()));

		showHighlightRangeOnly(false);
		
		this.outlineSelectionListener = new OutlineSelectionListener(this);
	}


	
	public void doSave(IProgressMonitor monitor) {
		super.doSave(monitor);
		if (fOutlinePage != null)
			fOutlinePage.update();
	}
	
	//TODO SourceViewerDecorationSupport
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class key) {
		if (key.equals(IContentOutlinePage.class)) {
			return getOutlinePage();
		} else if (key.equals(IPropertySheetPage.class)) {
		  return getPropertySheetPage();
//		} else if (key.equals(IGotoMarker.class)) {
//			return this;
		} else {
			return super.getAdapter(key);
		}
	}

	private IContentOutlinePage getOutlinePage() {
		// TODO use injection to avoid singelton
		
		if (fOutlinePage == null) {
			fOutlinePage = new GenericContentOutlinePage(this);
			fOutlinePage.setContentProvider(new OutlineContentProvider());
			fOutlinePage.setLabelProvider(new OutlineLabelProvider());
			
			IEditorInput input = getEditorInput();
			if (input != null) {
				// Get the input by the DocumentProvider 
				fOutlinePage.setInput(getDocumentProvider().getDocument(input));
			}
		}
		
		return fOutlinePage;
	}
	
	private IPropertySheetPage getPropertySheetPage() {
		
		if (fPropertyPage == null) { 
			fPropertyPage = new GeneriContentPropertySheetPage(editingDomain, this);
			fPropertyPage.setPropertySourceProvider(new AdapterFactoryContentProvider(adapterFactory));
		}
		
		return fPropertyPage;
	}
	
	private void initializeEditingDomain() {
		
		adapterFactory = new ComposedAdapterFactory(Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new EditorItemProviderAdapterFactory());
		
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
	
	
	// --------------------
	
	/**
	 * @see AbstractTextEditor#createPartControl(Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		
		getSite().getPage().addSelectionListener(outlineSelectionListener);
	}
	
	@Override
	public void dispose() {
		colorManager.dispose();
		
		getSite().getPage().removeSelectionListener(outlineSelectionListener);
		
		super.dispose();
	}
}
