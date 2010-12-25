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

import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;

import net.zehrer.no2.NO2EditorPlugin;
import net.zehrer.no2.handler.OpenModelEditorHandler;
import net.zehrer.no2.model.factory.DynamicItemProviderAdapterFactory;
import net.zehrer.no2.model.factory.ECoreItemProviderAdapterFactory;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.addon.adapter.ProblemIndicationAdapter;
import org.eclipse.emf.addon.editor.IEMFResourceEditor;
import org.eclipse.emf.addon.editor.IEditor;
import org.eclipse.emf.addon.editor.ProblemIndication;
import org.eclipse.emf.addon.editor.SelectionProviderEditorPart;
import org.eclipse.emf.addon.ui.page.GenericContentPropertySheetPage;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
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
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.ide.IGotoMarker;
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
public class ModelEditor extends SelectionProviderEditorPart implements IEMFResourceEditor, IMenuListener,  IGotoMarker, IEditor {

	// IViewerProvider,

	/**
	 * This keeps track of the editing domain that is used to track all changes to the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdapterFactoryEditingDomain modelEditingDomain;

	/**
	 * This keeps track of the editing domain that is used to track all changes to the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory modelAdapterFactory;

	/**
	 * This is the content outline page.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected DataContentOutlinePage contentOutlinePage;
	
	
	/**
	 * This is the property sheet page.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GenericContentPropertySheetPage propertySheetPage;

	protected TabelEditorPage tablePage;
	
	/**
	 * This keeps track of the active content viewer, which may be either one of the viewers in the pages or the content outline viewer.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Viewer currentViewer; // TODO check regarding OUTLINE?


	/**
	 * This listens for when the outline becomes active
	 *
	 * @generated NOT
	 */
	protected IPartListener partListener = new IPartListener() {

		public void partActivated(IWorkbenchPart p) {
//			 if (p instanceof ContentOutline) {
//			 if (((ContentOutline) p).getCurrentPage() == contentOutlinePage)
//			 {
//			 getActionBarContributor().setActiveEditor(ModelEditor.this);
//			
//			 setCurrentViewer(contentOutlineViewer);
//			 }
//			 } else
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
	 * Map to store the diagnostic associated with a resource.
	 */
	protected ProblemIndication problemIndication = new ProblemIndication();
	
	
	/**
	 * Adapter used to update the problem indication when resources are demanded
	 * loaded.
	 * TODO: move into a NO2 project
	 */
	protected EContentAdapter problemIndicationAdapter = new ProblemIndicationAdapter(problemIndication);

	/**
	 * This listens for workspace changes.
	 */
	protected WorkspaceResourceManager resourceManager = new WorkspaceResourceManager(this);
	
	
	/**
	 * This creates a model editor.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
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
		
		// Do the work within an operation because this is a long running
		// activity that modifies the workbench.

		problemIndication.setState(false);
		try {
			// This runs the options, and shows progress.
			new ProgressMonitorDialog(getSite().getShell()).run(true, false, this.resourceManager);

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
	 * This is called during startup.
	 * @generated NOT
	 * @category MultiPageEditorPart
	 */
	@Override
	public void init(IEditorSite site, IEditorInput editorInput) {

		setSite(site);
		setInputWithNotify(editorInput);
		setPartName(editorInput.getName());
		
		site.setSelectionProvider(this); // TODO: still required?
		
		//TODO: for what is the part listener required?
//		site.getPage().addPartListener(partListener);
		
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
		resourceManager.createModel(getEditorInput());

		// --------- problemIndicationAdapter ------------

		problemIndication.setSource(getEditingDomain().getResourceSet()); 
		modelEditingDomain.getResourceSet().eAdapters().add(problemIndicationAdapter);

		// This is the page for the table viewer.

		// Create the table page
		tablePage = new TabelEditorPage(this, this.modelAdapterFactory, resourceManager.getEClassResource());
			tablePage.createControl(parent);
//			Control table = tablePage.getControl();
				
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
	@SuppressWarnings("rawtypes")
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
	 * @generated NOT
	 * @category MultiPageEditorPart
	 */
	@Override
	public void dispose() {
		problemIndication.setState(false);

		ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceManager);

		// TODO: PartListener
//		getSite().getPage().removePartListener(partListener);

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
	 * TODO: extract class
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

//	/**
//	 * This makes sure that one content viewer, either for the current page or
//	 * the outline view, if it has focus, is the current one.
//	 * 
//	 * @generated
//	 * @category ModelEdit
//	 */
//	public void setCurrentViewer(Viewer viewer) {
//		// If it is changing...
//		//
//		if (currentViewer != viewer) {
//			if (selectionChangedListener == null) {
//				// Create the listener on demand.
//				//
//				selectionChangedListener = new ISelectionChangedListener() {
//					// This just notifies those things that are affected by the
//					// section.
//					//
//					public void selectionChanged(SelectionChangedEvent selectionChangedEvent) {
//						setSelection(selectionChangedEvent.getSelection());
//					}
//				};
//			}
//
//			// Stop listening to the old one.
//			//
//			if (currentViewer != null) {
//				currentViewer.removeSelectionChangedListener(selectionChangedListener);
//			}
//
//			// Start listening to the new one.
//			//
//			if (viewer != null) {
//				viewer.addSelectionChangedListener(selectionChangedListener);
//			}
//
//			// Remember it.
//			//
//			currentViewer = viewer;
//
//			// Set the editors selection based on the current viewer's
//			// selection.
//			//
//			setSelection(currentViewer == null ? StructuredSelection.EMPTY : currentViewer.getSelection());
//		}
//	}

	/**
	 * This sets the selection into whichever viewer is active.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
			contentOutlinePage = new DataContentOutlinePage (this, this.resourceManager);
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
			propertySheetPage = new GenericContentPropertySheetPage(modelEditingDomain, this);
			propertySheetPage.setPropertySourceProvider(new AdapterFactoryContentProvider(modelAdapterFactory));
		}

		return propertySheetPage;
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
	 * This sets up the editing domain for the model editor. 
	 * @generated NOT
	 * @category ModelEdit
	 */
	protected void initializeEditingDomain() {
		// Create an adapter factory that yields item providers.
		//
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
	public void createContextMenuFor(StructuredViewer viewer) {
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
		return true;
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
		// NOT SUPPORTED !!
	}


	/**
	 * @see SelectionProviderEditorPart#setSelection(ISelection)
	 * @category ISelectionProvider
	 */
	@Override
	public void setSelection(ISelection selection) {
		super.setSelection(selection);

		// TODO: why is status line not an usual listener?
		setStatusLineManager(selection);
	}

	



}
