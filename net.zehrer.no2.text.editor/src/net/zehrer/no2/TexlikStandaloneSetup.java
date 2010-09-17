
package net.zehrer.no2;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class TexlikStandaloneSetup extends TexlikStandaloneSetupGenerated{

	public static void doSetup() {
		new TexlikStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

