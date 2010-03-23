package net.zehrer.no2.cdo.test.handlers;

import net.zehrer.no2.cdo.CDOServerPlugin;
import net.zehrer.no2.cdo.test.dialogs.CDOPackageListDialog;
import net.zehrer.no2.cdo.test.dialogs.EMFObjectListDialog;
import net.zehrer.no2.ui.dialogs.AbstractEMFDialog;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.internal.ui.dialogs.PackageRegistryDialog;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class CDOTestHandler extends AbstractHandler {
	
	private static String NS_URI = "http://no2.zehrer.net/root";
	
	private CDOTransaction cdoTransaction = null;
	private CDOResource cdoResource = null;
	
	/**
	 * The constructor.
	 */
	public CDOTestHandler() {

	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		
		CDOServerPlugin cdoServer = CDOServerPlugin.getDefault();
		
		CDOSession cdoSession = cdoServer.getSession();
//		cdoTransaction = cdoServer.getTransaction();
//		
//		EPackage rootPackage = createRootPackage();
//		
//		EClass eClass = createEmptyClass(rootPackage);
//		cdoSession.getPackageRegistry().putEPackage(rootPackage);
//		
//		
//		cdoResource = cdoTransaction.getOrCreateResource("/test");

		// dynamic Object creation
//		 EObject newObject = EcoreUtil.create(eClass);
//		cdoResource.getContents().add(newObject);
	
//		cdoTransaction.commit();
	
		PackageRegistryDialog dialog = new PackageRegistryDialog(window.getActivePage(),cdoSession);
//		TitleAreaDialog dialog =  new EMFObjectListDialog(window.getActivePage(),cdoResource.eContents());
		dialog.open();
				
		
//		MessageDialog.openInformation(
//				window.getShell(),
//				"CDO Test",
//				"Package Registry Size: " + cdoSession.getPackageRegistry().size());
		return null;
	}
	
	
	protected static EPackage createRootPackage() {
		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;

		// create root package
		EPackage rootPackage = ecoreFactory.createEPackage();
		rootPackage.setName("root");
		rootPackage.setNsPrefix("no2"); 
		rootPackage.setNsURI(NS_URI);

		return rootPackage;
	}
	
	protected static EClass createEmptyClass(EPackage aPackage) {

		EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;

		EClass eClass = ecoreFactory.createEClass();
		eClass.setName("TestClass"); 
		
		if (aPackage != null)
			aPackage.getEClassifiers().add(eClass);

		return eClass;
	}
}
