package simulation.core.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.core.runtime.spi.RegistryContributor;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

/**
 * Instantiates the ISimulationPlugins defined by all active bundles. Plugin 
 * instantiation is only done on object creation of this class. Furthermore,  
 * this class allows the installing and uninstalling of new bundles. 
 * 
 * This class should only be instantiated once on application startup.
 * @author S-lenovo
 */
public class SimulationPluginManager {

	public static final String EXTENSION_POINT_ID = "simulation.extensionpoint.simulationplugin";

	private BundleContext context;
	
	/** 
	 * Maps from an ISimulationPlugin object to the ID of 
	 * the bundle in which the plugin was defined. 
	 * */ 
	private Map<ISimulationPlugin,Long> pluginToContributorID;
	
	/**
	 * Instantiates the ISimulationPlugin objects which are defined by active bundles.
	 * @param context
	 */
	public SimulationPluginManager(BundleContext context) {
		this.context = context;
		pluginToContributorID = new HashMap<ISimulationPlugin, Long>();
		instantiateISimulationPlugins();
	}

	/**
	 * Instantiates the ISimulationPlugin objects of defined by active bundles.
	 */
	private void instantiateISimulationPlugins(){
		IConfigurationElement[] configurations = RegistryFactory.getRegistry()
				.getConfigurationElementsFor(EXTENSION_POINT_ID);

		for (IConfigurationElement configElement : configurations) {
			Object instantiatedExtension = null;
			try {
				instantiatedExtension = configElement.createExecutableExtension("class");
			} catch (CoreException e) {
				e.printStackTrace();
			}
			if (instantiatedExtension instanceof ISimulationPlugin) {
				storePluginAndContributorID((ISimulationPlugin)instantiatedExtension, configElement);
			}
		}
	}

	/**
	 * Puts the plugin object and the ID of the bundle, i.e. the contributor, which defines this plugin in a
	 * hashmap.
	 * @param plugin The ISimulationPlugin object to be stored.
	 * @param configElement The IConfigurationElement which contains the information about the plugins contributor.
	 */
	private void storePluginAndContributorID(ISimulationPlugin plugin, IConfigurationElement configElement) {
		if (configElement.getContributor() instanceof RegistryContributor) {
			RegistryContributor contributor = (RegistryContributor) configElement.getContributor();
			pluginToContributorID.put(plugin, Long.parseLong(contributor.getId()));
		}
	}
	
	/**
	 * @return A list of the ISimulationPlugin objects, defined by bundles 
	 * which were active when this class was instantiated.
	 */
	public List<ISimulationPlugin> getISimulationPlugins() {
		List<ISimulationPlugin> ISimulationPlugins = new ArrayList<ISimulationPlugin>();
		for(ISimulationPlugin plugin : pluginToContributorID.keySet())
			ISimulationPlugins.add(plugin);
		return ISimulationPlugins;
	}

	/**
	 * Installs and starts a new bundle. To access a ISimulationPlugin object defined by the new bundle,
	 * the application must be restarted and this object reinstantiated.
	 * @param filePath The path to the bundle which should be installed. E.g. the path to a *.jar file.
	 * @throws FileNotFoundException
	 * @throws BundleException
	 */
	public void installBundle(String filePath) throws FileNotFoundException, BundleException{
    	File file = new File(filePath);
    	
    	Bundle installedBundle = null;
    	try {
    		FileInputStream fileStream = new FileInputStream(file);
    		installedBundle = context.installBundle(filePath, fileStream);
    		installedBundle.start();
    	} catch (BundleException e) {
    		try {
				installedBundle.uninstall();
			} catch (BundleException e2) {
				System.out.println("Could not uninstall the bundle which failed to start or install.");
				e2.printStackTrace();
			}
    		//throwing the exception again, so that the calling class also reacts on the error.
    		throw e;
    	}
	}
	
	/**
	 * Uninstalls the bundle which defines the passed ISimulationPlugin. Subsequently, the application
	 * should be restarted and this class reinstantiated.
	 * @param plugin The plugin, of which the defining bundle should be uninstalled.
	 * @throws BundleException
	 */
	public void uninstallBundle(ISimulationPlugin plugin) throws BundleException{
		Long contributorID = pluginToContributorID.get(plugin);
		context.getBundle(contributorID).uninstall();
	}
}
