package net.zehrer.no2.cdo;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.internal.net4j.protocol.CDOClientProtocolFactory;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.emf.cdo.net4j.CDOSessionConfiguration;
import org.eclipse.emf.cdo.server.CDOServerUtil;
import org.eclipse.emf.cdo.server.IRepository;
import org.eclipse.emf.cdo.server.IStore;
import org.eclipse.emf.cdo.server.db.CDODBUtil;
import org.eclipse.emf.cdo.server.db.mapping.IMappingStrategy;
import org.eclipse.emf.cdo.server.net4j.CDONet4jServerUtil;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.net4j.FactoriesProtocolProvider;
import org.eclipse.net4j.Net4jUtil;
import org.eclipse.net4j.acceptor.IAcceptor;
import org.eclipse.net4j.connector.IConnector;
import org.eclipse.net4j.db.DBUtil;
import org.eclipse.net4j.db.IDBAdapter;
import org.eclipse.net4j.db.IDBConnectionProvider;
import org.eclipse.net4j.db.h2.H2Adapter;
import org.eclipse.net4j.jvm.JVMUtil;
import org.eclipse.net4j.protocol.IProtocolProvider;
import org.eclipse.net4j.util.container.IManagedContainer;
import org.eclipse.net4j.util.container.IPluginContainer;
import org.eclipse.net4j.util.lifecycle.LifecycleUtil;
import org.h2.jdbcx.JdbcDataSource;
import org.osgi.framework.BundleContext;

public class CDOServerPlugin extends Plugin {

	public static final String PLUGIN_ID = "net.zehrer.no2.cdo";
	
	private static final String DEFAULT_REPOSITORY_NAME = "no2";
	
	private static CDOServerPlugin plugin;
	
//	private ServiceTracker tracker;

	private IRepository repository = null;
	private CDOResource resource = null;
	private CDOSession session = null;
	private CDOTransaction transaction = null;

	private IAcceptor acceptor = null;
	private IConnector connector = null;
	
	/**
	 * The constructor
	 */
	public CDOServerPlugin() {
	}

	
	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static CDOServerPlugin getDefault() {
		return plugin;
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		plugin = this;

//		tracker = new ServiceTracker(context, PreferencesService.class.getName(), null);
//		tracker.open();
//		
//		// grab the service
//		service = (PreferencesService) tracker.getService();
//		Preferences preferences = service.getSystemPreferences();
		
//		preferences.put(COLOR, "lavender");
//		
//		System.out.println("My favourite color is: " + preferences.get(COLOR, "")); 
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		// clean up
		
		plugin = null;
		 
		if (resource != null)
			LifecycleUtil.deactivate(resource);
 
		if (transaction != null)
			LifecycleUtil.deactivate(transaction);
 
		if (session != null)
			LifecycleUtil.deactivate(session);
 
		if (acceptor != null)
			LifecycleUtil.deactivate(acceptor);
 
		if (connector != null)
			LifecycleUtil.deactivate(connector);
 
		if (repository != null)
			LifecycleUtil.deactivate(repository);

//		tracker.close();
//		tracker = null;

	}
	
	/**
	 * Getter for transaction including lazy initialization
	 * 
	 * @return the transaction
	 */
	public CDOTransaction getTransaction() {
		if (transaction == null) {
			transaction = getSession().openTransaction();
		}
 
		return transaction;
	}

	/**
	 * Getter for resource (e.g. if you use only one central resource)
	 * 
	 * @return the resource
	 */
	public CDOResource getResource(String path) {
		if (resource == null) {
			/*
			 * getOrCreateResource will handle both loading existing resources
			 * (equivalent to transaction.getResource()) and
			 * creating/initializing a new one (equivalent to
			 * transaction.createResource())
			 */
			resource = getTransaction().getOrCreateResource(path);
		}
 
		return resource;
	}
	
	/**
	 * Getter for session including lazy initialization
	 *  
	 * @return the CDO session
	 */
	public CDOSession getSession() {
		if (session == null) {
			
			// [Session] -> [CDOSessionConfiguration] -> [IConnector]  
			// [IAcceptor] <-> [IManagedContainer] <-> [IRepository] -> [IStore]
			// 
			// [JVMConnector] <-> [JVMAcceptor]
			
			IManagedContainer container = setupContainer();
		    
			// create repository 
			if (repository == null) {
				IStore store = createStore();			// create store
				repository = createRepository(store);	// create repository
				CDOServerUtil.addRepository(container, repository);
			}

		    IProtocolProvider protocolProvider = new FactoriesProtocolProvider(new CDOClientProtocolFactory());

		    
			// initialize acceptor
			// source http://wiki.eclipse.org/Run_a_CDO_container_inside_eclipse_runtime
			if (acceptor == null) {
			  acceptor = JVMUtil.getAcceptor(container, "default");
			}

			
			// --- the embedded session is work
			// this will replace the acceptor / connector detour
			// TODO: on which bugzilla issue?
			
			// CDO 3.0 (embedded session)
//			CDOSessionConfiguration config = CDOServerUtil.createSessionConfiguration();
//			session = config.openSession();
			
			// --- now "connect" to the acceptor 
			
			if (connector == null) {
				connector = JVMUtil.getConnector(container, "default");
			}
				
			CDOSessionConfiguration config = CDONet4jUtil.createSessionConfiguration();
			config.setConnector(connector);
			config.setRepositoryName(DEFAULT_REPOSITORY_NAME);
			
			session = config.openSession();

		}
		return session;
	}

	
	private IManagedContainer setupContainer() {
		IManagedContainer container = IPluginContainer.INSTANCE;
	    Net4jUtil.prepareContainer(container); // Register Net4j factories
	    JVMUtil.prepareContainer(container);
	    CDONet4jUtil.prepareContainer(container); // Register CDO factories 
	    CDONet4jServerUtil.prepareContainer(container);		    
	    container.activate();
	    return container;
	}
	
	/** 
	 * Create and initialize/configure a repository 
	 * @param store - the store for the repository 
	 * @return the CDO repository created
	 */
	private IRepository createRepository(IStore store) {
		Map<String, String> props = new HashMap<String, String>();
	      props.put(IRepository.Props.OVERRIDE_UUID, "no2");
	      props.put(IRepository.Props.SUPPORTING_AUDITS, "true");
	      props.put(IRepository.Props.SUPPORTING_BRANCHES, "false");	      
	      props.put(IRepository.Props.CURRENT_LRU_CAPACITY, "100000");
	      props.put(IRepository.Props.REVISED_LRU_CAPACITY, "10000");
		return CDOServerUtil.createRepository(DEFAULT_REPOSITORY_NAME, store, props); 
	}
 
	/** 
	 * create the CDO store 
	 * @return the CDO store to use with the repository 
	 */
	private IStore createStore() {
	      JdbcDataSource dataSource = new JdbcDataSource();
	      dataSource.setURL("jdbc:h2:/Users/steve/Temporary/no2");   // TODO: find other way for URL

	      IMappingStrategy mappingStrategy = CDODBUtil.createHorizontalMappingStrategy(true);
	      IDBAdapter dbAdapter = new H2Adapter();
	      IDBConnectionProvider dbConnectionProvider = DBUtil.createConnectionProvider(dataSource);
	      return CDODBUtil.createStore(mappingStrategy, dbAdapter, dbConnectionProvider);
	}
}
