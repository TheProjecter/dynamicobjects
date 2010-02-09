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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import net.zehrer.no2.NO2EditorPlugin;
import net.zehrer.no2.handler.OpenModelEditorHandler;
import net.zehrer.no2.model.NO2Model;
import net.zehrer.no2.model.adapter.NO2ModelAdapter;
import net.zehrer.no2.model.factory.DynamicItemProviderAdapterFactory;
import net.zehrer.no2.model.factory.ECoreItemProviderAdapterFactory;
import net.zehrer.no2.model.impl.NO2ModelImpl;
import net.zehrer.no2.model.util.EClassResource;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.ui.MarkerHelper;
import org.eclipse.emf.common.ui.editor.ProblemEditorPart;
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
import org.eclipse.emf.ecore.util.EcoreUtil;
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
import org.eclipse.emf.edit.ui.util.EditUIMarkerHelper;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
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
public class ModelEditor extends MultiPageEditorPart implements IEditingDomainProvider, ISelectionProvider, IMenuListener, IViewerProvider, IGotoMarker {

	/**
	 * This keeps track of the editing domain that is used to track all changes
	 * to the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AdapterFactoryEditingDomain modelEditingDomain;

	/**
	 * This is the one adapter factory used for providing views of the model.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ComposedAdapterFactory modelAdapterFactory;

	/**
	 * This is the content outline page. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated 
	 */
	protected DataContentOutlinePage contentOutlinePage;

	/**
	 * This is the viewer that shadows the selection in the content outline. The
	 * parent relation must be correctly defined for this to work. 
	 * 
	 * @generated NOT
	 */
	//protected TreeViewer selectionViewer;
	
	/**
	 * This shows how a table view works. A table can be used as a list with
	 * icons. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected TableViewer tableViewer;

	/**
	 * This is the property sheet page. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected PropertySheetPage propertySheetPage;


	/**
	 * This keeps track of the active content viewer, which may be either one of
	 * the viewers in the pages or the content outline viewer. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Viewer currentViewer;  //TODO check regardin OUTLINE?

	/**
	 * This listens to which ever viewer is active. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected ISelectionChangedListener selectionChangedListener;

	/**
	 * This keeps track of all the
	 * {@link org.eclipse.jface.viewers.ISelectionChangedListener}s that are
	 * listening to this editor. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
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
	 * presented in Eclipse's Problems View. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected MarkerHelper markerHelper = new EditUIMarkerHelper();

	/**
	 * TODO: add comment 
	 * TODO: add multi metaModel support. 
	 */
	private NO2Model no2Model;

	private Resource no2Resource;
	private Resource metaModelResource;   //TODO support several metaModel's

	private URI archiveURI;
	private URI metaModelURI;


	/**
	 * This listens for when the outline becomes active
	 * @generated
	 */
	protected IPartListener partListener = new IPartListener() {
		
		public void partActivated(IWorkbenchPart p) {
//			if (p instanceof ContentOutline) {
//				if (((ContentOutline) p).getCurrentPage() == contentOutlinePage) {
//					getActionBarContributor().setActiveEditor(ModelEditor.this);
//
//					setCurrentViewer(contentOutlineViewer);
//				}
//			} else 
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
	 * Resources that have been removed since last activation. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Collection<Resource> removedResources = new ArrayList<Resource>();

	/**
	 * Resources that have been changed since last activation. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Collection<Resource> changedResources = new ArrayList<Resource>();

	/**
	 * Resources that have been saved. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	protected Collection<Resource> savedResources = new ArrayList<Resource>();

	/**
	 * Map to store the diagnostic associated with a resource. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Map<Resource, Diagnostic> resourceToDiagnosticMap = new LinkedHashMap<Resource, Diagnostic>();

	/**
	 * Controls whether the problem indication should be updated. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected boolean updateProblemIndication = true;
	
	
	/**
	 * Adapter used to update the problem indication when resources are demanded
	 * loaded. 
	 * TODO: extract class 
	 */
	protected EContentAdapter problemIndicationAdapter = new EContentAdapter() {
		@Override
		public void notifyChanged(Notification notification) {
			if (notification.getNotifier() instanceof Resource) {
				switch (notification.getFeatureID(Resource.class)) {
				case Resource.RESOURCE__IS_LOADED:
				case Resource.RESOURCE__ERRORS:
				case Resource.RESOURCE__WARNINGS: {
					Resource resource = (Resource) notification.getNotifier();
					Diagnostic diagnostic = analyzeResourceProblems(resource, null);
					if (diagnostic.getSeverity() != Diagnostic.OK) {
						resourceToDiagnosticMap.put(resource, diagnostic);
					} else {
						resourceToDiagnosticMap.remove(resource);
					}
	
					if (updateProblemIndication) {
						getSite().getShell().getDisplay().asyncExec(new Runnable() {
							public void run() {
								updateProblemIndication();
							}
						});
					}
					break;
				}
				}
			} else {
				super.notifyChanged(notification);
			}
		}
	
		@Override
		protected void setTarget(Resource target) {
			basicSetTarget(target);
		}
	
		@Override
		protected void unsetTarget(Resource target) {
			basicUnsetTarget(target);
		}
	};


	/**
	 * This listens for workspace changes. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected IResourceChangeListener resourceChangeListener = new IResourceChangeListener() {
		public void resourceChanged(IResourceChangeEvent event) {
			IResourceDelta delta = event.getDelta();
			try {
	
				final ResourceDeltaVisitor visitor = new ResourceDeltaVisitor(modelEditingDomain.getResourceSet());
				visitor.setSavedResources(savedResources);
				
				delta.accept(visitor);
	
				if (!visitor.getRemovedResources().isEmpty()) {
					getSite().getShell().getDisplay().asyncExec(new Runnable() {
						public void run() {
							removedResources.addAll(visitor.getRemovedResources());
							if (!isDirty()) {
								getSite().getPage().closeEditor(ModelEditor.this, false);
							}
						}
					});
				}
	
				if (!visitor.getChangedResources().isEmpty()) {
					getSite().getShell().getDisplay().asyncExec(new Runnable() {
						public void run() {
							changedResources.addAll(visitor.getChangedResources());
							if (getSite().getPage().getActiveEditor() == ModelEditor.this) {
								handleActivate();
							}
						}
					});
				}
			} catch (CoreException exception) {
				NO2EditorPlugin.INSTANCE.log(exception);
			}
		}
	};


	/**
	 * This creates a model editor. 
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	public ModelEditor() {
		super();
		initializeEditingDomain();
	}

	/**
	 * This is here for the listener to be able to call it. 
	 * @generated NOT
	 * @category WorkbenchPart
	 * TODO: check how far required?
	 */
//	@Override
//	protected void firePropertyChange(int action) {
//		super.firePropertyChange(action);
//	}

	/**
	 * This is for implementing {@link IEditorPart} and simply saves the model
	 * file. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category EditorPart
	 */
	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		// Save only resources that have actually changed.
		//
		final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
	
		// Do the work within an operation because this is a long running
		// activity that modifies the workbench.
		//
		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			// This is the method that gets invoked when the operation runs.
			//
			@Override
			public void execute(IProgressMonitor monitor) {
				// Save the resources to the file system.
				//
				boolean first = true;
				for (Resource resource : modelEditingDomain.getResourceSet().getResources()) {
					if ((first || !resource.getContents().isEmpty() || isPersisted(resource)) && !modelEditingDomain.isReadOnly(resource)) {
						try {
							long timeStamp = resource.getTimeStamp();
							resource.save(saveOptions);
							if (resource.getTimeStamp() != timeStamp) {
								savedResources.add(resource);
							}
						} catch (Exception exception) {
							resourceToDiagnosticMap.put(resource, analyzeResourceProblems(resource, exception));
						}
						first = false;
					}
				}
			}
		};
	
		updateProblemIndication = false;
		try {
			// This runs the options, and shows progress.
			//
			new ProgressMonitorDialog(getSite().getShell()).run(true, false, operation);
	
			// Refresh the necessary state.
			//
			((BasicCommandStack) modelEditingDomain.getCommandStack()).saveIsDone();
			firePropertyChange(IEditorPart.PROP_DIRTY);
		} catch (Exception exception) {
			// Something went wrong that shouldn't.
			//
			NO2EditorPlugin.INSTANCE.log(exception);
		}
		updateProblemIndication = true;
		updateProblemIndication();
	}

	/**
	 * This also changes the editor's input. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @category EditorPart
	 */
	@Override
	public void doSaveAs() {
		SaveAsDialog saveAsDialog = new SaveAsDialog(getSite().getShell());
		saveAsDialog.open();
		IPath path = saveAsDialog.getResult();
		if (path != null) {
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			if (file != null) {
				doSaveAs(URI.createPlatformResourceURI(file.getFullPath().toString(), true), new FileEditorInput(file));
			}
		}
	}

	/**
	 * This always returns true because it is not currently supported. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category EditorPart
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return true;
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
		site.setSelectionProvider(this);
		site.getPage().addPartListener(partListener);
		ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListener, IResourceChangeEvent.POST_CHANGE);
	}

	/**
	 * This is the method used by the framework to install your own controls.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 * @category MultiPageEditorPart
	 */
	@Override
	public void createPages() {
		
		
		// Creates the model from the editor input
		createModel();

		// This is the page for the table viewer.
		{
			ResourceSet resourceSet = no2Model.getResourceSet();
			EClassResource classResource = new EClassResource(no2Model.getClassResources().get(0), resourceSet);
			
			// Create the table page
			TabelEditorPage tablePage = new TabelEditorPage(this, this.modelAdapterFactory, classResource);
			tablePage.createControl(getContainer());
			Control table = tablePage.getControl();
			
			tableViewer = tablePage.getTableViewer();

			
			int pageIndex = addPage(table);  // add tabel view as page.
			
			// TODO: write an adapter for updating the pageName; 
			setPageText(pageIndex, tablePage.getPageName());  //getString("_UI_TablePage_label")
			
		}

		// Ensures that this editor will only display the page's tab
		// area if there are more than one page
		//
		getContainer().addControlListener(new ControlAdapter() {
			boolean guard = false;

			@Override
			public void controlResized(ControlEvent event) {
				if (!guard) {
					guard = true;
					//hideTabs();
					guard = false;
				}
			}
		});

		getSite().getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				updateProblemIndication();
			}
		});

		createHandlers();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category MultiPageEditorPart
	 */
	@Override
	public void setFocus() {
		getControl(getActivePage()).setFocus();
	}

	/**
	 * This is how the framework determines which interfaces we implement.
	 * @generated
	 * @category MultiPageEditorPart
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
	 * stack. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category MultiPageEditorPart
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
		updateProblemIndication = false;
	
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceChangeListener);
	
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
	 * interface. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category IViewerProvider
	 */
	public Viewer getViewer() {
		return currentViewer;
	}

	/**
	 * This implements {@link org.eclipse.jface.action.IMenuListener} to help
	 * fill the context menus with contributions from the Edit menu.
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
	 * Returns a diagnostic describing the errors and warnings listed in the
	 * resource and the specified exception (if any). <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	public Diagnostic analyzeResourceProblems(Resource resource, Exception exception) {
		if (!resource.getErrors().isEmpty() || !resource.getWarnings().isEmpty()) {
			BasicDiagnostic basicDiagnostic = new BasicDiagnostic(Diagnostic.ERROR, "net.zehrer.no2.model.editor", 0, getString("_UI_CreateModelError_message", resource.getURI()),
					new Object[] { exception == null ? (Object) resource : exception });
			basicDiagnostic.merge(EcoreUtil.computeDiagnostic(resource, true));
			return basicDiagnostic;
		} else if (exception != null) {
			return new BasicDiagnostic(Diagnostic.ERROR, "net.zehrer.no2.model.editor", 0, getString("_UI_CreateModelError_message", resource.getURI()), new Object[] { exception });
		} else {
			return Diagnostic.OK_INSTANCE;
		}
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
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 * @category ModelEdit
	 */
	public void setStatusLineManager(ISelection selection) {
		//:currentViewer != null && currentViewer == contentOutlineViewer ? contentOutlineStatusLineManager 
		
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
	 * @generated NOT
	 * @category ModelEditor
	 */
	public IPropertySheetPage getPropertySheetPage() {
		if (propertySheetPage == null) {
			propertySheetPage = new DataContentPropertySheetPage(modelEditingDomain,this); 
			propertySheetPage.setPropertySourceProvider(new AdapterFactoryContentProvider(modelAdapterFactory));
		}
		
		return propertySheetPage;
	}

	/**
	 * Return the resource of the meta model.
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
	 * @category ModelEdit
	 */
	public NO2Model getModel() {
		return this.no2Model;
	}



	// ---- IDisposable
	
	/**
		 * This is the method called to load a resource into the editing domain's
		 * resource set based on the editor's input. 
		 * <!-- begin-user-doc --> 
		 * <!-- end-user-doc -->
		 * 
		 * @generated NOT
		 * @category ModelEdit
		 */
		public void createModel() {
			
			archiveURI = NO2ModelImpl.getArchiveURI(EditUIUtil.getURI(getEditorInput()));
			
			Diagnostic diagnostic = null;
			Exception exception = null;
			
			// Get resouce set and setup URI mapping
			ResourceSet resourceSet =  modelEditingDomain.getResourceSet();
	//		resourceSet.getURIConverter().getURIMap().put(
	//				URI.createURI("/"), archiveURI);
	
			// ------- load NO2Model ------------
			
			URI no2URI = URI.createURI(archiveURI + "no2.xmi");    //TODO: use central name
		
			try {
				// Load the resource 		
				no2Resource = resourceSet.getResource(no2URI, true);
				no2Resource.load(null);  //TODO: what is about params on load?
				no2Model = (NO2Model) no2Resource.getEObject("/");
				no2Model.setArchiveURI(archiveURI);  // TODO: do internal
				
				// function for adding new created objects into the correct resource
				no2Model.eAdapters().add(new NO2ModelAdapter());
				
		    } catch (Exception e) {
				exception = e;
			}
	
		    if (no2Resource != null) {
				diagnostic = analyzeResourceProblems(no2Resource, exception);
		    } else  {
		    	diagnostic = new BasicDiagnostic(Diagnostic.ERROR, "net.zehrer.no2.model.editor", 0, getString("_UI_CreateModelError_message", no2URI ), new Object[] { exception });
		    }
		    
			if (diagnostic.getSeverity() != Diagnostic.OK) {
				resourceToDiagnosticMap.put(no2Resource, diagnostic);  // TODO check again orignial code!
				
				//TODO: what happens when exception occures? What are possible problems?
				//- Wrong format
				//- other error
			}
		    	
			// ------- load ECore Modle (MetaModel) ------------
			
			metaModelURI = URI.createURI("/metamodel.ecore");  // TODO: use central name
			
			try {
				// Load the resource through the editing domain.
				// TODO: BUG in no2Model.getResouces (cause resourceSet is not set after load!!!!)
				metaModelResource = no2Model.getResource(metaModelURI);
	
		    } catch (Exception e) {
				exception = e;
			}
			
		    if (metaModelResource != null) {
				diagnostic = analyzeResourceProblems(metaModelResource, exception);
		    } else  {
		    	diagnostic = new BasicDiagnostic(Diagnostic.ERROR, "net.zehrer.no2.model.editor", 0, getString("_UI_CreateModelError_message", metaModelURI ), new Object[] { exception });
		    }
			if (diagnostic.getSeverity() != Diagnostic.OK) {
				resourceToDiagnosticMap.put(metaModelResource, diagnostic);
			}
					
			
			// --------- problemIndicationAdapter ------------ 
			
			// TODO: to understand 
			modelEditingDomain.getResourceSet().eAdapters().add(problemIndicationAdapter);
		}



	/**
	 * Return the absolute URI of the meta model.
	 * e.g. used to open the ECore default editor.
	 * @return
	 * @category ModelEdit
	 */
	public URI getMetaModelURI() {
		URIConverter converter = no2Model.getResourceSet().getURIConverter();
		return converter.normalize(metaModelURI);
	}



	/**
	 * Handles activation of the editor or it's associated views. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	protected void handleActivate() {
		// Recompute the read only state.
		//
		if (modelEditingDomain.getResourceToReadOnlyMap() != null) {
			modelEditingDomain.getResourceToReadOnlyMap().clear();
	
			// Refresh any actions that may become enabled or disabled.
			//
			setSelection(getSelection());
		}
	
		if (!removedResources.isEmpty()) {
			if (handleDirtyConflict()) {
				getSite().getPage().closeEditor(ModelEditor.this, false);
			} else {
				removedResources.clear();
				changedResources.clear();
				savedResources.clear();
			}
		} else if (!changedResources.isEmpty()) {
			changedResources.removeAll(savedResources);
			handleChangedResources();
			changedResources.clear();
			savedResources.clear();
		}
	}



	/**
	 * Handles what to do with changed resources on activation. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	protected void handleChangedResources() {
		if (!changedResources.isEmpty() && (!isDirty() || handleDirtyConflict())) {
			if (isDirty()) {
				changedResources.addAll(modelEditingDomain.getResourceSet().getResources());
			}
			modelEditingDomain.getCommandStack().flush();
	
			updateProblemIndication = false;
			for (Resource resource : changedResources) {
				if (resource.isLoaded()) {
					resource.unload();
					try {
						resource.load(Collections.EMPTY_MAP);
					} catch (IOException exception) {
						if (!resourceToDiagnosticMap.containsKey(resource)) {
							resourceToDiagnosticMap.put(resource, analyzeResourceProblems(resource, exception));
						}
					}
				}
			}
	
			if (AdapterFactoryEditingDomain.isStale(editorSelection)) {
				setSelection(StructuredSelection.EMPTY);
			}
	
			updateProblemIndication = true;
			updateProblemIndication();
		}
	}



	/**
	 * Updates the problems indication with the information described in the
	 * specified diagnostic. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	protected void updateProblemIndication() {
		if (updateProblemIndication) {
			BasicDiagnostic diagnostic = new BasicDiagnostic(Diagnostic.OK, "net.zehrer.no2.model.editor", 0, null, new Object[] { modelEditingDomain.getResourceSet() });
			for (Diagnostic childDiagnostic : resourceToDiagnosticMap.values()) {
				if (childDiagnostic.getSeverity() != Diagnostic.OK) {
					diagnostic.add(childDiagnostic);
				}
			}
	
			int lastEditorPage = getPageCount() - 1;
			if (lastEditorPage >= 0 && getEditor(lastEditorPage) instanceof ProblemEditorPart) {
				((ProblemEditorPart) getEditor(lastEditorPage)).setDiagnostic(diagnostic);
				if (diagnostic.getSeverity() != Diagnostic.OK) {
					setActivePage(lastEditorPage);
				}
			} else if (diagnostic.getSeverity() != Diagnostic.OK) {
				ProblemEditorPart problemEditorPart = new ProblemEditorPart();
				problemEditorPart.setDiagnostic(diagnostic);
				problemEditorPart.setMarkerHelper(markerHelper);
				try {
					addPage(++lastEditorPage, problemEditorPart, getEditorInput());
					setPageText(lastEditorPage, problemEditorPart.getPartName());
					setActivePage(lastEditorPage);
					//showTabs();
				} catch (PartInitException exception) {
					NO2EditorPlugin.INSTANCE.log(exception);
				}
			}
	
			if (markerHelper.hasMarkers(modelEditingDomain.getResourceSet())) {
				markerHelper.deleteMarkers(modelEditingDomain.getResourceSet());
				if (diagnostic.getSeverity() != Diagnostic.OK) {
					try {
						markerHelper.createMarkers(diagnostic);
					} catch (CoreException exception) {
						NO2EditorPlugin.INSTANCE.log(exception);
					}
				}
			}
		}
	}



	/**
	 * Shows a dialog that asks if conflicting changes should be discarded. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	protected boolean handleDirtyConflict() {
		return MessageDialog.openQuestion(getSite().getShell(), getString("_UI_FileConflict_label"), getString("_WARN_FileConflict"));
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
	
	//		modelAdapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
	
			
	//		modelAdapterFactory.addAdapterFactory(new ModelItemProviderAdapterFactory());
	//		modelAdapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
	
			// Create the command stack that will notify this editor as commands are
			// executed.
			//
			BasicCommandStack commandStack = new BasicCommandStack();
	
			// Add a listener to set the most recent command's affected objects to
			// be the selection of the viewer with focus.
			//
			commandStack.addCommandStackListener(new CommandStackListener() {
				public void commandStackChanged(final EventObject event) {
					getContainer().getDisplay().asyncExec(new Runnable() {
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	protected void doSaveAs(URI uri, IEditorInput editorInput) {
	
		// TODO: will not work any more with xdata file format!!!
		(modelEditingDomain.getResourceSet().getResources().get(0)).setURI(uri);
		setInputWithNotify(editorInput);
		setPartName(editorInput.getName());
		IProgressMonitor progressMonitor = getActionBars().getStatusLineManager() != null ? getActionBars().getStatusLineManager().getProgressMonitor() : new NullProgressMonitor();
		doSave(progressMonitor);
	}



	/**
	 * Instantiate any handlers specific to this view and activate them.
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
	 * @generated NOT
	 * @category ModelEdit
	 */
	protected void createContextMenuFor(StructuredViewer viewer) {
		MenuManager contextMenu = new MenuManager("#PopUp");
		contextMenu.add(new Separator("additions"));  //additions
		
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



	/**
	 * This returns whether something has been persisted to the URI of the
	 * specified resource. The implementation uses the URI converter from the
	 * editor's resource set to try to open an input stream. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	protected boolean isPersisted(Resource resource) {
		boolean result = false;
		try {
			InputStream stream = modelEditingDomain.getResourceSet().getURIConverter().createInputStream(resource.getURI());
			if (stream != null) {
				result = true;
				stream.close();
			}
		} catch (IOException e) {
			// Ignore
		}
		return result;
	}



	/**
	 * Returns whether the outline view should be presented to the user. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	protected boolean showOutlineView() {
		return true;
	}
	
	// ---- method not longer used ---
	
	/**
	 * This looks up a string in plugin.properties, making a substitution. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	private static String getString(String key, Object s1) {
		return NO2EditorPlugin.INSTANCE.getString(key, new Object[] { s1 });
	}



	/**
	 * This looks up a string in the plugin's plugin.properties file. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @category ModelEdit
	 */
	private static String getString(String key) {
		return NO2EditorPlugin.INSTANCE.getString(key);
	}



	/**
	 * If there is just one page in the multi-page editor part, this hides the
	 * single tab at the bottom. 
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 * @category ModelEdit
	 */
	protected void hideTabs() {
		if (getPageCount() <= 1) {
			setPageText(0, "");
			if (getContainer() instanceof CTabFolder) {
				((CTabFolder) getContainer()).setTabHeight(1);
				Point point = getContainer().getSize();
				getContainer().setSize(point.x, point.y + 6);
			}
		}
	}
	
	/**
	 * If there is more than one page in the multi-page editor part, this shows
	 * the tabs at the bottom. 
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 * @category ModelEdit
	 */
	protected void showTabs() {
		if (getPageCount() > 1) {
			setPageText(0, getString("_UI_SelectionPage_label"));
			if (getContainer() instanceof CTabFolder) {
				((CTabFolder) getContainer()).setTabHeight(SWT.DEFAULT);
				Point point = getContainer().getSize();
				getContainer().setSize(point.x, point.y - 6);
			}
		}
	}
}
