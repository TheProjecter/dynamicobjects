/*******************************************************************************
 * Copyright (c) 2010 Stephan Zehrer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Stephan Zehrer - initial API and implementation
 *******************************************************************************/

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.cdo.eresource.CDOResource;
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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.net4j.Net4jUtil;
import org.eclipse.net4j.acceptor.IAcceptor;
import org.eclipse.net4j.connector.IConnector;
import org.eclipse.net4j.db.DBUtil;
import org.eclipse.net4j.db.IDBAdapter;
import org.eclipse.net4j.db.IDBConnectionProvider;
import org.eclipse.net4j.db.h2.H2Adapter;
import org.eclipse.net4j.jvm.JVMUtil;
import org.eclipse.net4j.util.container.ContainerUtil;
import org.eclipse.net4j.util.container.IManagedContainer;
import org.eclipse.net4j.util.om.OMPlatform;
import org.eclipse.net4j.util.om.log.PrintLogHandler;
import org.eclipse.net4j.util.om.trace.PrintTraceHandler;
import org.h2.jdbcx.JdbcDataSource;

public class StandaloneCDODynamic {

	EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
	
	private static final String DEFAULT_REPOSITORY_NAME = "test";

	public StandaloneCDODynamic() {
		// Enable logging and tracing
		OMPlatform.INSTANCE.setDebugging(true);
		OMPlatform.INSTANCE.addLogHandler(PrintLogHandler.CONSOLE);
		OMPlatform.INSTANCE.addTraceHandler(PrintTraceHandler.CONSOLE);
	}

	public static void main(String[] args) {
		StandaloneCDODynamic sample = new StandaloneCDODynamic();

		sample.run();
	}

	protected void run() {

		// TODO update URL
		JdbcDataSource dataSource = getJdbcDataSource("jdbc:h2:/Users/steve/Temporary/" + DEFAULT_REPOSITORY_NAME);

		IStore cdoStore = getStore(dataSource);
		IRepository cdoRepository = getRepository(cdoStore);
		
		IManagedContainer container = getContainer();
		CDOServerUtil.addRepository(container, cdoRepository);

		IAcceptor acceptor = JVMUtil.getAcceptor(container, "default"); //$NON-NLS-1$
		IConnector connector = JVMUtil.getConnector(container, "default"); //$NON-NLS-1$

		// Open session
		CDOSession cdoSession = getSession(connector);

		// Create dynamic package and class
		EPackage rootPackage = createRootPackage();
		EClass eClass = createEmptyClass(rootPackage);
		cdoSession.getPackageRegistry().putEPackage(rootPackage);

		// Open transaction
		CDOTransaction transaction = cdoSession.openTransaction();

		// Get or create resource
		CDOResource cdoResource = transaction.getOrCreateResource("/resource"); //$NON-NLS-1$

		EObject eObject = EcoreUtil.create(eClass);
		cdoResource.getContents().add(eObject);
		transaction.commit();
		
		// Cleanup
		cdoSession.close();
		connector.close();
		container.deactivate();
	}

	protected JdbcDataSource getJdbcDataSource(String url) {
		// Setup JdbcDataSource
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL(url);
		return dataSource;
	}

	protected IStore getStore(JdbcDataSource dataSource) {
		// Setup Store
		IMappingStrategy mappingStrategy = CDODBUtil.createHorizontalMappingStrategy(true);
		// TODO : change Adapter if an other DB is used.
		IDBAdapter dbAdapter = new H2Adapter();
		IDBConnectionProvider dbConnectionProvider = DBUtil.createConnectionProvider(dataSource);
		return CDODBUtil.createStore(mappingStrategy, dbAdapter, dbConnectionProvider);
	}

	protected IRepository getRepository(IStore store) {
		// Setup Repository
		Map<String, String> props = new HashMap<String, String>();
		props.put(IRepository.Props.OVERRIDE_UUID, DEFAULT_REPOSITORY_NAME);
	    props.put(IRepository.Props.SUPPORTING_AUDITS, "true");
	    props.put(IRepository.Props.SUPPORTING_BRANCHES, "false");	
		props.put(IRepository.Props.CURRENT_LRU_CAPACITY, "100000");
		props.put(IRepository.Props.REVISED_LRU_CAPACITY, "10000");
		return CDOServerUtil.createRepository(DEFAULT_REPOSITORY_NAME, store, props);
	}

	protected IManagedContainer getContainer() {
		IManagedContainer container = ContainerUtil.createContainer();
		Net4jUtil.prepareContainer(container);
		JVMUtil.prepareContainer(container);
		CDONet4jUtil.prepareContainer(container);
		CDONet4jServerUtil.prepareContainer(container);
		container.activate();
		return container;
	}

	
	protected CDOSession getSession(IConnector connector) {
		CDOSessionConfiguration config = CDONet4jUtil.createSessionConfiguration();
		config.setConnector(connector);
		config.setRepositoryName(DEFAULT_REPOSITORY_NAME);
		
		return config.openSession();		
	}


	protected EPackage createRootPackage() {

		// create root package
		EPackage rootPackage = ecoreFactory.createEPackage();
		rootPackage.setName("root");
		rootPackage.setNsPrefix("test");
		rootPackage.setNsURI("http://no2.zehrer.net/root");

		return rootPackage;
	}

	protected EClass createEmptyClass(EPackage aPackage) {

		EClass eClass = ecoreFactory.createEClass();
		eClass.setName("Foo");

		if (aPackage != null)
			aPackage.getEClassifiers().add(eClass);

		return eClass;
	}
}
