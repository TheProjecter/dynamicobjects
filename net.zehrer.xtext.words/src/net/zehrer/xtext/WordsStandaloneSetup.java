
package net.zehrer.xtext;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class WordsStandaloneSetup extends WordsStandaloneSetupGenerated{

	public static void doSetup() {
		new WordsStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

