package simulation.core.control.plugins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.spi.RegistryContributor;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class PluginBundleMapper {

	/** 
	 * Maps from an ISimulationPlugin object to the ID of 
	 * the bundle in which the plugin was defined. 
	 * */ 
	private Map<ISimulationPlugin,Long> pluginToContributorID;

	public PluginBundleMapper() {
		pluginToContributorID = new HashMap<ISimulationPlugin, Long>();
	}

	/**
	 * Puts the plugin object and the ID of the bundle, i.e. the contributor, which defines this plugin in a
	 * hashmap.
	 * @param plugin The ISimulationPlugin object to be stored.
	 * @param configElement The IConfigurationElement which contains the information about the plugins contributor.
	 */
	public void putPlugin(ISimulationPlugin plugin, IConfigurationElement configElement) {
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
	 * @param plugin
	 * @return The ID of the bundle which defines the passed plugin.
	 */
	public Long getBundleID(ISimulationPlugin plugin){
		return pluginToContributorID.get(plugin);
	}
}
