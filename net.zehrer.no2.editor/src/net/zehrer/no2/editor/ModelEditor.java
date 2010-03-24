/*******************************************************************************
 * Copyright (c) 2009 - 2010 Stephan Zehrer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Stephan Zehrer - initial API and implementation
 *******************************************************************************/

package net.zehrer.no2.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;

import net.zehrer.no2.NO2EditorPlugin;
import net.zehrer.no2.adapter.ProblemIndicationAdapter;
import net.zehrer.no2.handler.OpenModelEditorHandler;
import net.zehrer.no2.model.NO2Model;
import net.zehrer.no2.model.adapter.NO2ModelAdapter;
import net.zehrer.no2.model.factory.DynamicItemProviderAdapterFactory;
import net.zehrer.no2.model.factory.ECoreItemProviderAdapterFactory;
import net.zehrer.no2.model.impl.NO2ModelImpl;
import net.zehrer.no2.model.util.EClassResource;
import net.zehrer.no2.util.ResourceUtil;
import net.zehrer.no2.util.Session;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.UnwrappingSelectionProvider;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheet;
import org.eclipse.ui.views.properties.PropertySheetPage;

/**
 * This is an example of a Model model editor. <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class ModelEditor extends EditorPart implements IEditingDomainProvider, ISelectionProvider, IMenuListener,  IGotoMarker {

	// IViewerProvider,

	protected AdapterFactoryEditingDomain modelEditingDomain;

	protected ComposedAdapterFactory modelAdapterFactory;

	protected TabelEditorPage tablePage;

	protected DataContentOutlinePage contentOutlinePage;
	
	protected PropertySheetPage propertySheetPage;

	/**
	 * This keeps track of the active content viewer, which may be either one of
	 * the viewers in the pages or the content outline viewer.
	 */
	protected Viewer currentViewer; // TODO check regarding OUTLINE?

	/**
	 * This listens to which ever viewer is active.
	 * 
	 * @generated
	 */
	protected ISelectionChangedListener selectionChangedListener;

	/**
	 * This keeps track of all the
	 * {@link org.eclipse.jface.viewers.ISelectionChangedListener}s that are
	 * listening to this editor. 
	 * @generated
	 */
	protected Collection<ISelectionChangedListener> selectionChangedListeners = new ArrayList<ISelectionChangedListener>();

	/**
	 * This keeps track of the selection of the editor as a whole. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ISelection editorSelection = StructuredSelection.EMPTY;

	/**
	 * The MarkerHelper is responsible for creating workspace resource markers
	 * presented in Eclipse's Problems View. 
	 * 
	 * @generated
	 */
//	protected MarkerHelper markerHelper = new EditUIMarkerHelper();

	/**
	 * TODO: add comment 
	 * TODO: add multi metaModel support.
	 */
	private NO2Model no2Model;

	private Resource no2Resource;
	private Resource metaModelResource; // TODO support several metaModel's

	private URI archiveURI;
	private URI metaModelURI;

	/**
	 * This listens for when the outline becomes active
	 * 
	 * @generated
	 */
	protected IPartListener partListener = new IPartListener() {

		public void partActivated(IWorkbenchPart p) {
			// if (p instanceof ContentOutline) {
			// if (((ContentOutline) p).getCurrentPage() == contentOutlinePage)
			// {
			// getActionBarContributor().setActiveEditor(ModelEditor.this);
			//
			// setCurrentViewer(contentOutlineViewer);
			// }
			// } else
			if (p instanceof PropertySheet) {
				if (((PropertySheet) p).getCurrentPage() == propertySheetPage) {
					getActionBarContributor().setActiveEditor(ModelEditor.this);
					handleActivate();
				}
			} else if (p == ModelEditor.this) {
				handleActivate();
			}
		}

		public void partBroughtToTop(IWorkbenchPart p) {
			// Ignore.
		}

		public void partClosed(IWorkbenchPart p) {
			// Ignore.
		}

		public void partDeactivated(IWorkbenchPart p) {
			// Ignore.
		}

		public void partOpened(IWorkbenchPart p) {
			// Ignore.
		}
	};


	
	/**
	 * Map to store the diagnostic associated with a resource. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	
	protected ProblemIndication problemIndication = new ProblemIndication();
	
	/**
	 * Adapter used to update the problem indication when resources are demanded
	 * loaded. TODO: extract class
	 */
	protected EContentAdapter problemIndicationAdapter = new ProblemIndicationAdapter(problemIndication);

	/**
	 * This listens for workspace changes. 
	 * 
	 * @generated
	 */
	protected WorkspaceResourceManager resourceManager = new WorkspaceResourceManager(this);
	
	/**
	 * This creates a model editor. 
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	public ModelEditor() {
		super();
		initializeEditingDomain();
	}

	/**
	 * This is for implementing {@link IEditorPart} and simply saves the model
	 * file.
	 * 
	 * @category EditorPart
	 */
	@Override
	public void doSave(IProgressMonitor progressMonitor) {

		//TODO: check to merge Resource Manager and "Session"
		
		// Do the work within an operation because this is a long running
		// activity that modifies the workbench.

		Session operation = new Session(this.modelEditingDomain);
		operation.setProblemIndication(problemIndication);
		operation.setSavedResources(resourceManager.getSavedResources());
		
		problemIndication.setState(false);
		try {
			// This runs the options, and shows progress.
			new ProgressMonitorDialog(getSite().getShell()).run(true, false, operation);

			// Refresh the necessary state.
			((BasicCommandStack) modelEditingDomain.getCommandStack()).saveIsDone();
			firePropertyChange(IEditorPart.PROP_DIRTY);
		} catch (Exception exception) {
			// Something went wrong that shouldn't.
			NO2EditorPlugin.INSTANCE.log(exception);
		}
		
		problemIndication.setState(true);
		problemIndication.update();
	}

	/**
	 * This always returns true because it is not currently supported.
	 * 
	 * @generated true
	 * @category EditorPart
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	/**
	 * This is called during startup. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @category MultiPageEditorPart
	 */
	@Override
	public void init(IEditorSite site, IEditorInput editorInput) {
		setSite(site);
		setInputWithNotify(editorInput);
		setPartName(editorInput.getName());
		
		site.setSelectionProvider(this); // TODO: still required?
		
		site.getPage().addPartListener(partListener);
		
		resourceManager.setProblemIndication(problemIndication);
		ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceManager, IResourceChangeEvent.POST_CHANGE);
	}

	/**
	 * This is the method used by the framework to install your own controls.
	 * 
	 * @generated NOT
	 * @category EditorPart
	 */
	@Override
	public void createPartControl(Composite parent) {

		// Creates the model from the editor input
		createModel();

		// This is the page for the table viewer.
		{
			ResourceSet resourceSet = no2Model.getResourceSet();
			EClassResource classResource = new EClassResource(no2Model.getClassResources().get(0), resourceSet);

			// Create the table page
			tablePage = new TabelEditorPage(this, this.modelAdapterFactory, classResource);
			tablePage.createControl(parent);
			Control table = tablePage.getControl();

		}
				
		problemIndication.update();

		createHandlers();
	}

	/**
	 * @category WorkbenchPart
	 */
	@Override
	public void setFocus() {
		tablePage.setFocus();
	}

	/**
	 * This is how the framework determines which interfaces we implement.
	 * @generated NOT
	 * @category WorkbenchPart
	 */
	@SuppressWarnings( { "rawtypes", "unchecked" })
	@Override
	public Object getAdapter(Class key) {
		if (IContentOutlinePage.class.equals(key)) {
			return showOutlineView() ? getContentOutlinePage() : null;
		} else if (key.equals(IPropertySheetPage.class)) {
			return getPropertySheetPage();
		} else if (key.equals(IGotoMarker.class)) {
			return this;
		} else {
			return super.getAdapter(key);
		}
	}

	/**
	 * This is for implementing {@link IEditorPart} and simply tests the command
	 * stack. 
	 * @generated NOT
	 * @category EditorPart
	 */
	@Override
	public boolean isDirty() {
		return ((BasicCommandStack) modelEditingDomain.getCommandStack()).isSaveNeeded();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category MultiPageEditorPart
	 */
	@Override
	public void dispose() {
		problemIndication.setState(false);

		ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceManager);

		getSite().getPage().removePartListener(partListener);

		modelAdapterFactory.dispose();

		if (getActionBarContributor().getActiveEditor() == this) {
			getActionBarContributor().setActiveEditor(null);
		}

		if (propertySheetPage != null) {
			propertySheetPage.dispose();
		}

		if (contentOutlinePage != null) {
			contentOutlinePage.dispose();
		}

		super.dispose();

	}

	/**
	 * This returns the editing domain as required by the
	 * {@link IEditingDomainProvider} interface. This is important for
	 * implementing the static methods of {@link AdapterFactoryEditingDomain}
	 * and for supporting {@link org.eclipse.emf.edit.ui.action.CommandAction}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category IEditingDomainProvider
	 */
	public EditingDomain getEditingDomain() {
		return modelEditingDomain;
	}

	/**
	 * This returns the viewer as required by the {@link IViewerProvider}
	 * interface. 
	 * @generated
	 * @category IViewerProvider
	 */
	public Viewer getViewer() {
		return currentViewer;
	}

	/**
	 * This implements {@link org.eclipse.jface.action.IMenuListener} to help
	 * fill the context menus with contributions from the Edit menu.
	 * 
	 * @generated
	 * @category IMenuListener
	 */
	public void menuAboutToShow(IMenuManager menuManager) {
		((IMenuListener) getEditorSite().getActionBarContributor()).menuAboutToShow(menuManager);
	}

	/**
	 * @generated
	 * @category IGotoMarker
	 */
	public void gotoMarker(IMarker marker) {
		try {
			if (marker.getType().equals(EValidator.MARKER)) {
				String uriAttribute = marker.getAttribute(EValidator.URI_ATTRIBUTE, null);
				if (uriAttribute != null) {
					URI uri = URI.createURI(uriAttribute);
					EObject eObject = modelEditingDomain.getResourceSet().getEObject(uri, true);
					if (eObject != null) {
						setSelectionToViewer(Collections.singleton(modelEditingDomain.getWrapper(eObject)));
					}
				}
			}
		} catch (CoreException exception) {
			NO2EditorPlugin.INSTANCE.log(exception);
		}
	}

	/**
	 * This implements {@link org.eclipse.jface.viewers.ISelectionProvider}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category ISelectionProvider
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.add(listener);
	}

	/**
	 * This implements {@link org.eclipse.jface.viewers.ISelectionProvider}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category ISelectionProvider
	 */
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListeners.remove(listener);
	}

	/**
	 * This implements {@link org.eclipse.jface.viewers.ISelectionProvider} to
	 * set this editor's overall selection. Calling this result will notify the
	 * listeners. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 * @category ISelectionProvider
	 */
	public void setSelection(ISelection selection) {
		editorSelection = selection;

		for (ISelectionChangedListener listener : selectionChangedListeners) {
			listener.selectionChanged(new SelectionChangedEvent(this, selection));
		}

		// TODO: why is status line not an usual listener?
		setStatusLineManager(selection);
	}

	/**
	 * This implements {@link org.eclipse.jface.viewers.ISelectionProvider} to
	 * return this editor's overall selection. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @category ISelectionProvider
	 */
	public ISelection getSelection() {
		return editorSelection;
	}



	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> TODO: extract class
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	public class ReverseAdapterFactoryContentProvider extends AdapterFactoryContentProvider {
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		public ReverseAdapterFactoryContentProvider(AdapterFactory adapterFactory) {
			super(adapterFactory);
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		@Override
		public Object[] getElements(Object object) {
			Object parent = super.getParent(object);
			return (parent == null ? Collections.EMPTY_SET : Collections.singleton(parent)).toArray();
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		@Override
		public Object[] getChildren(Object object) {
			Object parent = super.getParent(object);
			return (parent == null ? Collections.EMPTY_SET : Collections.singleton(parent)).toArray();
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		@Override
		public boolean hasChildren(Object object) {
			Object parent = super.getParent(object);
			return parent != null;
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		@Override
		public Object getParent(Object object) {
			return null;
		}
	}

	/**
	 * This makes sure that one content viewer, either for the current page or
	 * the outline view, if it has focus, is the current one.
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	public void setCurrentViewer(Viewer viewer) {
		// If it is changing...
		//
		if (currentViewer != viewer) {
			if (selectionChangedListener == null) {
				// Create the listener on demand.
				//
				selectionChangedListener = new ISelectionChangedListener() {
					// This just notifies those things that are affected by the
					// section.
					//
					public void selectionChanged(SelectionChangedEvent selectionChangedEvent) {
						setSelection(selectionChangedEvent.getSelection());
					}
				};
			}

			// Stop listening to the old one.
			//
			if (currentViewer != null) {
				currentViewer.removeSelectionChangedListener(selectionChangedListener);
			}

			// Start listening to the new one.
			//
			if (viewer != null) {
				viewer.addSelectionChangedListener(selectionChangedListener);
			}

			// Remember it.
			//
			currentViewer = viewer;

			// Set the editors selection based on the current viewer's
			// selection.
			//
			setSelection(currentViewer == null ? StructuredSelection.EMPTY : currentViewer.getSelection());
		}
	}

	/**
	 * This sets the selection into whichever viewer is active.
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	public void setSelectionToViewer(Collection<?> collection) {
		final Collection<?> theSelection = collection;
		// Make sure it's okay.
		//
		if (theSelection != null && !theSelection.isEmpty()) {
			Runnable runnable = new Runnable() {
				public void run() {
					// Try to select the items in the current content viewer of
					// the editor.
					//
					if (currentViewer != null) {
						currentViewer.setSelection(new StructuredSelection(theSelection.toArray()), true);
					}
				}
			};
			getSite().getShell().getDisplay().asyncExec(runnable);
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	public void setStatusLineManager(ISelection selection) {
		// :currentViewer != null && currentViewer == contentOutlineViewer ?
		// contentOutlineStatusLineManager

		IStatusLineManager statusLineManager = getActionBars().getStatusLineManager();

		if (statusLineManager != null) {
			if (selection instanceof IStructuredSelection) {
				Collection<?> collection = ((IStructuredSelection) selection).toList();
				switch (collection.size()) {
				case 0: {
					statusLineManager.setMessage(getString("_UI_NoObjectSelected"));
					break;
				}
				case 1: {
					String text = new AdapterFactoryItemDelegator(modelAdapterFactory).getText(collection.iterator().next());
					statusLineManager.setMessage(getString("_UI_SingleObjectSelected", text));
					break;
				}
				default: {
					statusLineManager.setMessage(getString("_UI_MultiObjectSelected", Integer.toString(collection.size())));
					break;
				}
				}
			} else {
				statusLineManager.setMessage("");
			}
		}
	}

	/**
	 * This accesses a cached version of the content outliner.
	 * 
	 * @generated NOT
	 * @category ModelEditor
	 */
	public IContentOutlinePage getContentOutlinePage() {

		if (contentOutlinePage == null) {
			contentOutlinePage = new DataContentOutlinePage(getModelAdapterFactory(), this);
		}

		return contentOutlinePage;
	}

	/**
	 * This accesses a cached version of the property sheet.
	 * 
	 * @generated NOT
	 * @category ModelEditor
	 */
	public IPropertySheetPage getPropertySheetPage() {
		if (propertySheetPage == null) {
			propertySheetPage = new DataContentPropertySheetPage(modelEditingDomain, this);
			propertySheetPage.setPropertySourceProvider(new AdapterFactoryContentProvider(modelAdapterFactory));
		}

		return propertySheetPage;
	}

	/**
	 * Return the resource of the meta model.
	 * 
	 * @return
	 * @category ModelEdit
	 */
	public Resource getMetaModelResource() {
		return this.metaModelResource;
	}

	/**
	 * @generated
	 * @category ModelEdit
	 */
	public EditingDomainActionBarContributor getActionBarContributor() {
		return (EditingDomainActionBarContributor) getEditorSite().getActionBarContributor();
	}

	/**
	 * @generated
	 * @category ModelEdit
	 */
	public IActionBars getActionBars() {
		return getActionBarContributor().getActionBars();
	}

	/**
	 * @generated
	 * @category ModelEdit
	 */
	public AdapterFactory getModelAdapterFactory() {
		return modelAdapterFactory;
	}

	/**
	 * Get current model instance
	 * 
	 * @category ModelEdit
	 */
	public NO2Model getModel() {
		return this.no2Model;
	}

	// ---- IDisposable

	/**
	 * This is the method called to load a resource into the editing domain's
	 * resource set based on the editor's input. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated NOT
	 * @category ModelEdit
	 */
	public void createModel() {

		archiveURI = NO2ModelImpl.getArchiveURI(EditUIUtil.getURI(getEditorInput()));

		Diagnostic diagnostic = null;
		Exception exception = null;

		// Get resouce set and setup URI mapping
		ResourceSet resourceSet = modelEditingDomain.getResourceSet();

		// configure the URI map
		// TODO : move all load / save
		resourceSet.getURIConverter().getURIMap().put(URI.createURI("/"), archiveURI);

		// ------- load NO2Model ------------

		URI no2URI = URI.createURI("/no2.xmi"); // TODO: use central name

		try {
			// Load the resource
			no2Resource = resourceSet.getResource(no2URI, true);
			no2Resource.load(null); // TODO: what is about params on load?
			no2Model = (NO2Model) no2Resource.getEObject("/");
			no2Model.setArchiveURI(archiveURI); // TODO: do internal
			no2Model.setEditingDomainProvider(this);

			// function for adding new created objects into the correct resource
			no2Model.eAdapters().add(new NO2ModelAdapter());

		} catch (Exception e) {
			exception = e;
		}

		if (no2Resource != null) {
			diagnostic = ResourceUtil.analyzeResourceProblems(no2Resource, exception);
		} else {
			diagnostic = new BasicDiagnostic(Diagnostic.ERROR, "net.zehrer.no2.model.editor", 0, getString("_UI_CreateModelError_message", no2URI), new Object[] { exception });
		}

		if (diagnostic.getSeverity() != Diagnostic.OK) {
			problemIndication.put(no2Resource, diagnostic); // TODO check
																	// again
																	// orignial
																	// code!

			// TODO: what happens when exception occures? What are possible
			// problems?
			// - Wrong format
			// - other error
		}

		// ------- load ECore Modle (MetaModel) ------------

		metaModelURI = URI.createURI("/metamodel.ecore"); // TODO: use central
															// name

		try {
			// Load the resource through the editing domain.
			// TODO: BUG in no2Model.getResouces (cause resourceSet is not set
			// after load!!!!)
			metaModelResource = no2Model.getResource(metaModelURI);

		} catch (Exception e) {
			exception = e;
		}

		if (metaModelResource != null) {
			diagnostic = ResourceUtil.analyzeResourceProblems(metaModelResource, exception);
		} else {
			diagnostic = new BasicDiagnostic(Diagnostic.ERROR, "net.zehrer.no2.model.editor", 0, getString("_UI_CreateModelError_message", metaModelURI),
					new Object[] { exception });
		}
		if (diagnostic.getSeverity() != Diagnostic.OK) {
			problemIndication.put(metaModelResource, diagnostic);
		}

		// --------- problemIndicationAdapter ------------

		problemIndication.setSource(resourceSet);  // will change??
		modelEditingDomain.getResourceSet().eAdapters().add(problemIndicationAdapter);
	}

	/**
	 * Return the absolute URI of the meta model. e.g. used to open the ECore
	 * default editor.
	 * 
	 * @return
	 * @category ModelEdit
	 */
	public URI getMetaModelURI() {
		URIConverter converter = no2Model.getResourceSet().getURIConverter();
		return converter.normalize(metaModelURI);
	}





	/**
	 * This sets up the editing domain for the model editor. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 * @category ModelEdit
	 */
	protected void initializeEditingDomain() {
		// Create an adapter factory that yields item providers.
		modelAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		modelAdapterFactory.addAdapterFactory(new ECoreItemProviderAdapterFactory());

		modelAdapterFactory.addAdapterFactory(new DynamicItemProviderAdapterFactory());

		// modelAdapterFactory.addAdapterFactory(new
		// EcoreItemProviderAdapterFactory());

		// modelAdapterFactory.addAdapterFactory(new
		// ModelItemProviderAdapterFactory());
		// modelAdapterFactory.addAdapterFactory(new
		// ReflectiveItemProviderAdapterFactory());

		// Create the command stack that will notify this editor as commands are
		// executed.
		//
		BasicCommandStack commandStack = new BasicCommandStack();

		// Add a listener to set the most recent command's affected objects to
		// be the selection of the viewer with focus.
		//
		
		commandStack.addCommandStackListener(new CommandStackListener() {
			
			public void commandStackChanged(final EventObject event) {
				
				
				// TODO test
				Display.getCurrent().asyncExec(new Runnable() {
					public void run() {
						firePropertyChange(IEditorPart.PROP_DIRTY);

						// Try to select the affected objects.
						//
						Command mostRecentCommand = ((CommandStack) event.getSource()).getMostRecentCommand();
						if (mostRecentCommand != null) {
							setSelectionToViewer(mostRecentCommand.getAffectedObjects());
						}
						if (propertySheetPage != null && !propertySheetPage.getControl().isDisposed()) {
							propertySheetPage.refresh();
						}
					}
				});
			}
		});

		// Create the editing domain with a special command stack.
		modelEditingDomain = new AdapterFactoryEditingDomain(modelAdapterFactory, commandStack, new HashMap<Resource, Boolean>());
	}


	/**
	 * Instantiate any handlers specific to this view and activate them.
	 * 
	 * @category ModelEdit
	 */
	protected void createHandlers() {

		// add the following handler
		new OpenModelEditorHandler(this); // this handler register it self

	}

	// ------

	/**
	 * This creates a context menu for the viewer and adds a listener as well
	 * registering the menu for extension.
	 * 
	 * @generated NOT
	 * @category ModelEdit
	 */
	protected void createContextMenuFor(StructuredViewer viewer) {
		MenuManager contextMenu = new MenuManager("#PopUp");
		contextMenu.add(new Separator("additions")); // additions

		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(this);
		Menu menu = contextMenu.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(contextMenu, new UnwrappingSelectionProvider(viewer));

		// TODO: drag & drop stuff -> understand what it does
		int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
		Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
		viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
		viewer.addDropSupport(dndOperations, transfers, new EditingDomainViewerDropAdapter(modelEditingDomain, viewer));
	}

	
	public void handleActivate() {
		this.resourceManager.handleActivate();
	}
	
	
	/**
	 * Returns whether the outline view should be presented to the user. 
	 * @generated
	 * @category ModelEdit
	 */
	protected boolean showOutlineView() {
		return false;
	}

	// ---- method not longer used ---

	/**
	 * This looks up a string in plugin.properties, making a substitution. 
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	private static String getString(String key, Object s1) {
		return NO2EditorPlugin.INSTANCE.getString(key, new Object[] { s1 });
	}

	/**
	 * This looks up a string in the plugin's plugin.properties file. 
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	private static String getString(String key) {
		return NO2EditorPlugin.INSTANCE.getString(key);
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub
		
	}





}
