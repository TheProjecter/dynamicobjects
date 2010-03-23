package net.zehrer.no2.ui.views;

import net.zehrer.no2.cdo.CDOServerPlugin;
import net.zehrer.no2.ui.provider.EPackageContentProvider;

import org.eclipse.emf.cdo.internal.ui.SharedIcons;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class PackageView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "net.zehrer.no2.ui.views.PackageView";

	private TreeViewer viewer;
//	private DrillDownAdapter drillDownAdapter;
	private Action action1;
	private Action action2;
	private Action doubleClickAction;

	private CDOSession session;

	/**
	 * The constructor.
	 */
	public PackageView() {
	}

	/**
	 * @see IWorkbenchPart#createPartControl(Composite)
	 */
	public void createPartControl(Composite parent) {

		CDOServerPlugin cdoServer = CDOServerPlugin.getDefault();

		this.session = cdoServer.getSession();

		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

//		drillDownAdapter = new DrillDownAdapter(viewer);
		viewer.setContentProvider(new EPackageContentProvider(this.session));

		viewer.setLabelProvider(new ViewLabelProvider());
		// viewer.setSorter(new NameSorter());
		viewer.setInput(this.session); // getViewSite()

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "net.zehrer.no2.ui.views.viewer");
		// makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				PackageView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		// manager.add(action1);
		manager.add(new Separator());
		// manager.add(action2);
	}

	private void fillContextMenu(IMenuManager manager) {
		// manager.add(action1);
		// manager.add(action2);
		manager.add(new Separator());
//		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		// manager.add(action1);
		// manager.add(action2);
		manager.add(new Separator());
//		drillDownAdapter.addNavigationActions(manager);
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	class ViewLabelProvider extends LabelProvider {

		public String getText(Object obj) {
			
			if (obj instanceof ENamedElement)
				return ((ENamedElement)obj).getName();
				
			return obj.toString();
		}

		public Image getImage(Object obj) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			if (obj instanceof EPackage)
				return SharedIcons.getDescriptor(SharedIcons.OBJ_EPACKAGE_DYNAMIC).createImage();
			
			if (obj instanceof EClass)
				return SharedIcons.getDescriptor(SharedIcons.OBJ_ECLASS).createImage();
			
			return null;
		}
	}


}



// --- from example

// private void showMessage(String message) {
// MessageDialog.openInformation(
// viewer.getControl().getShell(),
// "Package View",
// message);
// }

// private void makeActions() {
// action1 = new Action() {
// public void run() {
// showMessage("Action 1 executed");
// }
// };
// action1.setText("Action 1");
// action1.setToolTipText("Action 1 tooltip");
// action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
// getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
//	
// action2 = new Action() {
// public void run() {
// showMessage("Action 2 executed");
// }
// };
// action2.setText("Action 2");
// action2.setToolTipText("Action 2 tooltip");
// action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
// getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
// doubleClickAction = new Action() {
// public void run() {
// ISelection selection = viewer.getSelection();
// Object obj = ((IStructuredSelection)selection).getFirstElement();
// showMessage("Double-click detected on "+obj.toString());
// }
// };
// }

// /*
// * The content provider class is responsible for
// * providing objects to the view. It can wrap
// * existing objects in adapters or simply return
// * objects as-is. These objects may be sensitive
// * to the current input of the view, or ignore
// * it and always show the same content
// * (like Task List, for example).
// */
// 
// class TreeObject implements IAdaptable {
// private String name;
// private TreeParent parent;
//	
// public TreeObject(String name) {
// this.name = name;
// }
// public String getName() {
// return name;
// }
// public void setParent(TreeParent parent) {
// this.parent = parent;
// }
// public TreeParent getParent() {
// return parent;
// }
// public String toString() {
// return getName();
// }
// public Object getAdapter(Class key) {
// return null;
// }
// }
//
// class TreeParent extends TreeObject {
// private ArrayList children;
// public TreeParent(String name) {
// super(name);
// children = new ArrayList();
// }
// public void addChild(TreeObject child) {
// children.add(child);
// child.setParent(this);
// }
// public void removeChild(TreeObject child) {
// children.remove(child);
// child.setParent(null);
// }
// public TreeObject [] getChildren() {
// return (TreeObject [])children.toArray(new TreeObject[children.size()]);
// }
// public boolean hasChildren() {
// return children.size()>0;
// }
// }
//
// class ViewContentProvider implements IStructuredContentProvider,
// ITreeContentProvider {
// private TreeParent invisibleRoot;
//
// public void inputChanged(Viewer v, Object oldInput, Object newInput) {
// }
// public void dispose() {
// }
// public Object[] getElements(Object parent) {
// if (parent.equals(getViewSite())) {
// if (invisibleRoot==null) initialize();
// return getChildren(invisibleRoot);
// }
// return getChildren(parent);
// }
// public Object getParent(Object child) {
// if (child instanceof TreeObject) {
// return ((TreeObject)child).getParent();
// }
// return null;
// }
// public Object [] getChildren(Object parent) {
// if (parent instanceof TreeParent) {
// return ((TreeParent)parent).getChildren();
// }
// return new Object[0];
// }
// public boolean hasChildren(Object parent) {
// if (parent instanceof TreeParent)
// return ((TreeParent)parent).hasChildren();
// return false;
// }
// /*
// * We will set up a dummy model to initialize tree heararchy.
// * In a real code, you will connect to a real model and
// * expose its hierarchy.
// */

//
// class NameSorter extends ViewerSorter {
// }