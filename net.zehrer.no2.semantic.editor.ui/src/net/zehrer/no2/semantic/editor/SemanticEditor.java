package net.zehrer.no2.semantic.editor;

import java.util.EventObject;

import net.zehrer.no2.edit.AsyncCommandStackListener;
import net.zehrer.no2.semantic.editor.coloring.ColorManager;
import net.zehrer.no2.semantic.editor.model.provider.EditorItemProviderAdapterFactory;
import net.zehrer.no2.semantic.editor.outline.OutlineContentProvider;
import net.zehrer.no2.semantic.editor.outline.OutlineLabelProvider;
import net.zehrer.no2.semantic.editor.outline.OutlineSelectionListener;
import net.zehrer.no2.semantic.editor.text.SemanticDocumentProvider;
import net.zehrer.no2.ui.editor.GenericContentPropertySheetPage;
import net.zehrer.no2.ui.editor.GenericContentOutlinePage;
import net.zehrer.no2.ui.editor.IEditor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory.Descriptor.Registry;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;

public class SemanticEditor extends TextEditor implements IEditor, CommandStackListener { 

	private ColorManager colorManager;
	
	private GenericContentOutlinePage fOutlinePage;
	private GenericContentPropertySheetPage fPropertyPage;
	
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

	/*
	 * @see org.eclipse.ui.texteditor.AbstractTextEditor#doSave(IProgressMonitor)
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		
//		if (fOutlinePage != null)
//			fOutlinePage.update();
		
		// Do the work within an operation because this is a long running
		// activity that modifies the workbench.

//		problemIndication.setState(false);

			// This runs the options, and shows progress.
//			new ProgressMonitorDialog(getSite().getShell()).run(true, false, this.resourceManager);
		super.doSave(monitor);
		
			// Refresh the necessary state.
		((BasicCommandStack) editingDomain.getCommandStack()).saveIsDone();
		firePropertyChange(IEditorPart.PROP_DIRTY);

		
//		problemIndication.setState(true);
//		problemIndication.update();
		
	}
	

	/*
	 * @see org.eclipse.ui.texteditor.AbstractTextEditor#isDirty()
	 */
	@Override
	public boolean isDirty() {
		return super.isDirty() || ((BasicCommandStack) editingDomain.getCommandStack()).isSaveNeeded() ;
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


	/*
	 * @see org.eclipse.ui.texteditor.AbstractTextEditor#firePropertyChange(int)
	 */
	@Override
	protected void firePropertyChange(int property) {
		super.firePropertyChange(property);
		
		// TODO: is there a official why to do this 
		if (fPropertyPage != null && !fPropertyPage.getControl().isDisposed()) {
			fPropertyPage.refresh();
		}

		if (fOutlinePage != null && !fOutlinePage.getControl().isDisposed()) {
			fOutlinePage.refresh();
		}
	}
	
	/*
	 * @see org.eclipse.ui.editors.text.TextEditor#initializeEditor()
	 */
	@Override
	protected void initializeEditor() {
		super.initializeEditor();
		setEditorContextMenuId("#SemanticEditorContext"); //$NON-NLS-1$
	}
	
	// ----------

	private void initializeEditingDomain() {
		
		adapterFactory = new ComposedAdapterFactory(Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new EditorItemProviderAdapterFactory());
		
		BasicCommandStack commandStack = new BasicCommandStack();
		
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory,commandStack, new java.util.LinkedHashMap<Resource, Boolean>());

		// The editor is listen for changes 
		commandStack.addCommandStackListener(new AsyncCommandStackListener(this));
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
			fPropertyPage = new GenericContentPropertySheetPage(editingDomain, this);
			fPropertyPage.setPropertySourceProvider(new AdapterFactoryContentProvider(adapterFactory));
		}
		
		return fPropertyPage;
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

	// ------ CommandStackListener
	
	/**
	 * @category CommandStackListener
	 */
	@Override
	public void commandStackChanged(EventObject event) {
		firePropertyChange(IEditorPart.PROP_DIRTY);		
		
		// Try to select the affected objects.
		//
//		Command mostRecentCommand = ((CommandStack) event.getSource()).getMostRecentCommand();
//		if (mostRecentCommand != null) {
//			setSelectionToViewer(mostRecentCommand.getAffectedObjects());
//		}
	}


}
