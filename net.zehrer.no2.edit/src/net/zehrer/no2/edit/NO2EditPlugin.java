package net.zehrer.no2.edit;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;

/**
 * The activator class controls the plug-in life cycle
 */
public class NO2EditPlugin extends EMFPlugin {

	// TODO: check out application generation of e4
	
	/**
	 * The plug-in ID
	 */
	public static final String PLUGIN_ID = "net.zehrer.no2.edit"; //$NON-NLS-1$

	/**
	 * The singleton instance of the plugin.
	 */
	public static final NO2EditPlugin INSTANCE = new NO2EditPlugin();

	/**
	 * Keep track of the singleton.
	 */
	private static Implementation plugin;

	/**
	 * Create the instance.
	 */
	public NO2EditPlugin() {
		super(new ResourceLocator[] { NO2EditPlugin.INSTANCE, });
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * 
	 * @return the singleton instance.
	 */
	@Override
	public ResourceLocator getPluginResourceLocator() {
		return plugin;
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * 
	 * @return the singleton instance.
	 */
	public static Implementation getPlugin() {
		return plugin;
	}
	
	
	/**
	 * The actual implementation of the Eclipse <b>Plugin</b>.
	 */
	public static class Implementation extends EclipsePlugin {
		/**
		 * Creates an instance.
		 */
		public Implementation() {
			super();
	
			// Remember the static instance.
			//
			plugin = this;
		}
	}


}
