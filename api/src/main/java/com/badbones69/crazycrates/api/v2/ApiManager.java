package com.badbones69.crazycrates.api.v2;

import ch.jalu.configme.SettingsManager;
import ch.jalu.configme.SettingsManagerBuilder;
import com.Zrips.CMI.Modules.ModuleHandling.CMIModule;
import com.badbones69.crazycrates.api.v2.configs.ConfigBuilder;
import com.badbones69.crazycrates.api.v2.configs.types.sections.PluginSupportSection;
import com.badbones69.crazycrates.api.v2.enums.HologramSupport;
import com.badbones69.crazycrates.api.v2.holograms.interfaces.HologramManager;
import com.badbones69.crazycrates.api.v2.holograms.types.CMIHologramSupport;
import com.badbones69.crazycrates.api.v2.holograms.types.DecentHologramSupport;
import com.badbones69.crazycrates.api.v2.holograms.types.FancyHologramSupport;
import com.badbones69.crazycrates.api.v2.storage.interfaces.UserManager;
import com.ryderbelserion.stick.paper.Stick;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.nio.file.Path;

public class ApiManager {

    private static Stick stick;

    private final Path path;
    private final JavaPlugin plugin;

    public ApiManager(Path path, JavaPlugin plugin) {
        this.path = path;

        this.plugin = plugin;
    }

    private UserManager userManager;

    private SettingsManager pluginSettings;
    private SettingsManager configSettings;

    private HologramManager hologramManager;

    public ApiManager load() {
        // This must go first.
        stick = new Stick(this.path, plugin.getName());

        File pluginSettings = new File(this.path.toFile(), "plugin-settings.yml");

        this.pluginSettings = SettingsManagerBuilder
                .withYamlFile(pluginSettings)
                .useDefaultMigrationService()
                .configurationData(ConfigBuilder.buildPluginSettings())
                .create();

        File configSettings = new File(this.path.toFile(), "config.yml");

        this.configSettings = SettingsManagerBuilder
                .withYamlFile(configSettings)
                .useDefaultMigrationService()
                .configurationData(ConfigBuilder.buildConfigSettings())
                .create();

        reload(false);

        //this.userManager = new JsonManager(this.path);

        //this.userManager.load();

        return this;
    }

    public void reload(boolean reloadCommand) {
        boolean hologramsToggle = this.pluginSettings.getProperty(PluginSupportSection.HOLOGRAMS_SUPPORT_ENABLED);

        if (hologramsToggle) {
            if (this.hologramManager != null) this.hologramManager.purge(this.plugin);

            HologramSupport hologramType = this.pluginSettings.getProperty(PluginSupportSection.HOLOGRAMS_SUPPORT_TYPE);

            switch (hologramType) {
                case cmi_holograms -> {
                    if (CMIModule.holograms.isEnabled()) {
                        this.hologramManager = new CMIHologramSupport();
                        this.plugin.getLogger().warning("CMI Hologram Support is enabled.");

                        return;
                    }

                    this.plugin.getLogger().warning("CMI support is enabled by you but the CMI Hologram Module is not enabled.");
                    this.plugin.getLogger().warning("Please go to Modules.yml in CMI & turn on the hologram module: Restart is required.");
                }

                case fancy_holograms -> {
                    this.hologramManager = new DecentHologramSupport();
                    this.plugin.getLogger().warning("DecentHologram Support is enabled.");
                }

                case decent_holograms -> {
                    this.hologramManager = new FancyHologramSupport();
                    this.plugin.getLogger().warning("FancyHologram Support is enabled.");
                }
            }
        }

        if (reloadCommand) {
            this.pluginSettings.reload();
            
            this.configSettings.reload();
        }
    }

    public static Stick getStickCore() {
        return stick;
    }

    public UserManager getUserManager() {
        return this.userManager;
    }

    public SettingsManager getPluginSettings() {
        return this.pluginSettings;
    }

    public SettingsManager getConfigSettings() {
        return this.configSettings;
    }

    public HologramManager getHolograms() {
        return this.hologramManager;
    }
}