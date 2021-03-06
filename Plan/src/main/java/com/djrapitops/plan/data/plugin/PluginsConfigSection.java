package main.java.com.djrapitops.plan.data.plugin;

import com.djrapitops.plugin.api.config.ConfigNode;
import com.djrapitops.plugin.api.utility.log.Log;
import main.java.com.djrapitops.plan.systems.file.config.ConfigSystem;

import java.io.IOException;

/**
 * Class responsible for generating and generating settings for PluginData
 * objects to the config.
 *
 * @author Rsl1122
 * @since 3.5.0
 */
public class PluginsConfigSection {

    public boolean hasSection(PluginData dataSource) {
        ConfigNode section = getPluginsSection();
        String pluginName = dataSource.getSourcePlugin();
        return section.getChildren().containsKey(pluginName)
                && section.getConfigNode(pluginName).getChildren().containsKey("Enabled");
    }

    private ConfigNode getPluginsSection() {
        return ConfigSystem.getInstance().getConfig().getConfigNode("Plugins");
    }

    public void createSection(PluginData dataSource) {
        ConfigNode section = getPluginsSection();
        String pluginName = dataSource.getSourcePlugin();

        section.set(pluginName + ".Enabled", true);
        try {
            section.sort();
            section.save();
        } catch (IOException e) {
            Log.toLog(this.getClass().getName(), e);
        }
    }

    public boolean isEnabled(PluginData dataSource) {
        ConfigNode section = getPluginsSection();

        String pluginName = dataSource.getSourcePlugin();
        return section.getBoolean(pluginName + ".Enabled");
    }
}
