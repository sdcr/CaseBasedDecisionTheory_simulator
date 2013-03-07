package simulation.core.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.core.runtime.spi.RegistryContributor;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class SimulationPluginManager extends Observable {

	public static final String EXTENSION_POINT_ID = "simulation.extensionpoint.simulationplugin";
	private IExtensionRegistry extensionRegistry;

	private BundleContext context;
	private Map<ISimulationPlugin,String> pluginToContributorID;
	
	/**
	 * Sets up itself as the listener for new simulation plugin extensions.
	 */
	public SimulationPluginManager(BundleContext context) {
		this.context = context;
		extensionRegistry = RegistryFactory.getRegistry();
		pluginToContributorID = new HashMap<ISimulationPlugin, String>();
	}

	
	public List<ISimulationPlugin> getActiveISimulationPlugins() {
		List<ISimulationPlugin> activeISimulationPlugins = new ArrayList<ISimulationPlugin>();
		
		IConfigurationElement[] configurations = extensionRegistry
				.getConfigurationElementsFor(EXTENSION_POINT_ID);
		try {
			for (IConfigurationElement e : configurations) {
				final Object o = e.createExecutableExtension("class");
				if (o instanceof ISimulationPlugin) {
					ISimulationPlugin plugin = (ISimulationPlugin)o;
					activeISimulationPlugins.add(plugin);
					
					RegistryContributor contributor = null;
					String contributorID = null;
					if(e.getContributor() instanceof RegistryContributor){
						contributor = (RegistryContributor)e.getContributor();
						contributorID = contributor.getId();
					}
					if(contributorID!=null){
						pluginToContributorID.put(plugin, contributorID);
					}
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
		return activeISimulationPlugins;
	}

	public void installBundle(String filePath) throws FileNotFoundException, BundleException{
    	File f = new File(filePath);
    	FileInputStream fileStream;
    	
    	Bundle newBundle = null;
    	try {
    		fileStream = new FileInputStream(f);
    		//filePath here may become a problem
    		newBundle = context.installBundle(filePath, fileStream);
    		newBundle.start();
    	} catch (BundleException e1) {
    		try {
				newBundle.uninstall();
			} catch (BundleException e) {
				System.out.println("Could not uninstall the bundle which failed to start or install.");
				e.printStackTrace();
			}
    		throw e1;
    	}
	}
	
	public void uninstallBundle(ISimulationPlugin plugin) throws BundleException{
		String contributorID = pluginToContributorID.get(plugin);
		Bundle bundle = context.getBundle(Long.parseLong(contributorID));
		bundle.uninstall();
	}
}
