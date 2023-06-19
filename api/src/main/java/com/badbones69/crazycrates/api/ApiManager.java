package com.badbones69.crazycrates.api;

import ch.jalu.configme.SettingsManager;
import ch.jalu.configme.SettingsManagerBuilder;
import com.Zrips.CMI.Modules.ModuleHandling.CMIModule;
import com.badbones69.crazycrates.api.configs.ConfigBuilder;
import com.badbones69.crazycrates.api.configs.types.Language;
import com.badbones69.crazycrates.api.configs.types.PluginConfig;
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
import com.ryderbelserion.stick.paper.utils.FileUtils;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

    private SettingsManager pluginConfig;
    private Language language;
    private SettingsManager config;

    private HologramManager hologramManager;

    public ApiManager load() {
        // This must go first.
        // This handles everything related to my personal plugin core.
        stick = new Stick(this.path, plugin.getName());

        // Create plugin-config.yml
        File pluginConfig = new File(this.path + "plugin-config.yml");

        this.pluginConfig = SettingsManagerBuilder
                .withYamlFile(pluginConfig)
                .useDefaultMigrationService()
                .configurationData(ConfigBuilder.buildPluginConfig())
                .create();

        File messages = new File(this.path + "messages.yml");
        File localeDir = new File(this.path.toFile(), "locale");
        File newFile = new File(localeDir, "lang-en.yml");

        if (!localeDir.exists()) {
            if (!localeDir.mkdirs()) {
                plugin.getLogger().severe("Could not create crates directory! " +  localeDir.getAbsolutePath());
                return this;
            }

            if (messages.exists()) {
                File renamedFile = new File(this.path + "lang-en.yml");

                if (messages.renameTo(renamedFile)) plugin.getLogger().info("Renamed " + messages.getName() + " to " + renamedFile.getName());

                try {
                    Files.move(renamedFile.toPath(), newFile.toPath());
                    plugin.getLogger().warning("Moved " + renamedFile.getPath() + " to " + newFile.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            FileUtils.extract("/locale/", this.path, false);
        }

        this.language = new Language(newFile);

        // Create config.yml
        File config = new File(this.path.toFile(), "config.yml");

        this.config = SettingsManagerBuilder
                .withYamlFile(config)
                .useDefaultMigrationService()
                .configurationData(ConfigBuilder.buildConfig())
                .create();

        this.crateManager = new CrateManager();

        this.crateManager.loadCrates();

        JsonCrateHandler jsonCrateHandler = new JsonCrateHandler(
                this.path,
                plugin.getServer()
        );

        jsonCrateHandler.load();

        switch (this.pluginConfig.getProperty(PluginConfig.DATA_TYPE)) {
            case json -> this.userManager = new JsonUserManager(this.path, this.crateManager);
            case yaml -> this.userManager = new YamlUserManager(new File(this.path.toFile(), "users.yml"), this.crateManager);
        }

        this.userManager.load();

        return this;
    }

    public void reload(boolean reloadCommand) {
        boolean hologramsToggle = this.pluginConfig.getProperty(PluginSupportSection.HOLOGRAMS_SUPPORT_ENABLED);

        if (hologramsToggle) {
            if (this.hologramManager != null) this.hologramManager.purge(plugin);

            HologramSupport hologramType = this.pluginConfig.getProperty(PluginSupportSection.HOLOGRAMS_SUPPORT_TYPE);

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
            this.pluginConfig.reload();
            
            this.config.reload();

            this.crateManager = new CrateManager();

            this.crateManager.loadCrates();

            JsonCrateHandler jsonCrateHandler = new JsonCrateHandler(
                    this.path,
                    plugin.getServer()
            );

            jsonCrateHandler.save();
            jsonCrateHandler.load();

            switch (this.pluginConfig.getProperty(PluginConfig.DATA_TYPE)) {
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

    public SettingsManager getPluginConfig() {
        return this.pluginConfig;
    }

    public Language getLocale() {
        return this.language;
    }

    public SettingsManager getConfig() {
        return this.config;
    }

    public HologramManager getHolograms() {
        return this.hologramManager;
    }
}