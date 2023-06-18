package com.badbones69.crazycrates.api;

import ch.jalu.configme.SettingsManager;
import ch.jalu.configme.SettingsManagerBuilder;
import com.Zrips.CMI.Modules.ModuleHandling.CMIModule;
import com.badbones69.crazycrates.api.configs.ConfigBuilder;
import com.badbones69.crazycrates.api.configs.types.PluginSettings;
import com.badbones69.crazycrates.api.configs.types.sections.PluginSupportSection;
import com.badbones69.crazycrates.api.crates.CrateManager;
import com.badbones69.crazycrates.api.enums.HologramSupport;
import com.badbones69.crazycrates.api.holograms.interfaces.HologramManager;
import com.badbones69.crazycrates.api.holograms.types.CMIHologramSupport;
import com.badbones69.crazycrates.api.holograms.types.DecentHologramSupport;
import com.badbones69.crazycrates.api.storage.interfaces.UserManager;
import com.badbones69.crazycrates.api.storage.types.file.json.JsonUserManager;
import com.badbones69.crazycrates.api.storage.types.file.json.crates.JsonCrateHandler;
import com.badbones69.crazycrates.api.storage.types.file.yaml.YamlUserManager;
import com.ryderbelserion.stick.paper.Stick;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.nio.file.Path;

public class ApiManager {

    private static Stick stick;

    private final Path path;
    private static JavaPlugin plugin;

    public ApiManager(Path path, JavaPlugin javaPlugin) {
        this.path = path;

        plugin = javaPlugin;
    }

    private UserManager userManager;
    private CrateManager crateManager;

    private SettingsManager pluginSettings;
    private SettingsManager configSettings;

    private HologramManager hologramManager;

    public ApiManager load() {
        // This must go first.
        // This handles everything related to my personal plugin core.
        stick = new Stick(this.path, plugin.getName());

        // Create plugin-settings.yml
        File pluginSettings = new File(this.path.toFile(), "plugin-settings.yml");

        this.pluginSettings = SettingsManagerBuilder
                .withYamlFile(pluginSettings)
                .useDefaultMigrationService()
                .configurationData(ConfigBuilder.buildPluginSettings())
                .create();

        // Create config.yml
        File configSettings = new File(this.path.toFile(), "config.yml");

        this.configSettings = SettingsManagerBuilder
                .withYamlFile(configSettings)
                .useDefaultMigrationService()
                .configurationData(ConfigBuilder.buildConfigSettings())
                .create();

        this.crateManager = new CrateManager(this.pluginSettings);

        this.crateManager.loadCrates();

        JsonCrateHandler jsonCrateHandler = new JsonCrateHandler(
                this.path,
                plugin.getServer()
        );

        jsonCrateHandler.load();

        switch (this.pluginSettings.getProperty(PluginSettings.DATA_TYPE)) {
            case json -> this.userManager = new JsonUserManager(this.path, this.crateManager);
            case yaml -> this.userManager = new YamlUserManager(new File(this.path.toFile(), "users.yml"), this.crateManager);
        }

        this.userManager.load();

        return this;
    }

    public void reload(boolean reloadCommand) {
        boolean hologramsToggle = this.pluginSettings.getProperty(PluginSupportSection.HOLOGRAMS_SUPPORT_ENABLED);

        if (hologramsToggle) {
            if (this.hologramManager != null) this.hologramManager.purge(plugin);

            HologramSupport hologramType = this.pluginSettings.getProperty(PluginSupportSection.HOLOGRAMS_SUPPORT_TYPE);

            switch (hologramType) {
                case cmi_holograms -> {
                    if (CMIModule.holograms.isEnabled()) {
                        this.hologramManager = new CMIHologramSupport();

                        return;
                    }

                    plugin.getLogger().warning("CMI support is enabled by you but the CMI Hologram Module is not enabled.");
                    plugin.getLogger().warning("Please go to Modules.yml in CMI & turn on the hologram module: Restart is required.");
                }

                case decent_holograms -> {
                    this.hologramManager = new DecentHologramSupport();
                    plugin.getLogger().warning("DecentHologram Support is enabled.");
                }
            }
        }

        // If the command is /crazycrates reload.
        if (reloadCommand) {
            this.pluginSettings.reload();
            
            this.configSettings.reload();

            this.crateManager = new CrateManager(this.pluginSettings);

            this.crateManager.loadCrates();

            JsonCrateHandler jsonCrateHandler = new JsonCrateHandler(
                    this.path,
                    plugin.getServer()
            );

            jsonCrateHandler.save();
            jsonCrateHandler.load();

            switch (this.pluginSettings.getProperty(PluginSettings.DATA_TYPE)) {
                case json -> this.userManager = new JsonUserManager(this.path, this.crateManager);
                case yaml -> this.userManager = new YamlUserManager(new File(this.path.toFile(), "users.yml"), this.crateManager);
            }

            this.userManager.load();
        }
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static Stick getStickCore() {
        return stick;
    }

    public UserManager getUserManager() {
        return this.userManager;
    }

    public CrateManager getCrateManager() {
        return this.crateManager;
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