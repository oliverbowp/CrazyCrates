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
import com.badbones69.crazycrates.api.storage.types.file.json.crates.JsonCrateManager;
import com.badbones69.crazycrates.api.storage.types.file.yaml.YamlUserManager;
import com.ryderbelserion.stick.core.utils.FileUtils;
import com.ryderbelserion.stick.paper.Stick;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ApiManager {

    private final JavaPlugin plugin;
    private final Path path;
    private final Stick stick;

    public ApiManager(JavaPlugin plugin, Path path) {
        this.plugin = plugin;

        this.path = path;

        this.stick = new Stick(path, plugin.getName());
    }

    private UserManager userManager;
    private CrateManager crateManager;

    private SettingsManager pluginConfig;
    private Locale locale;
    private SettingsManager config;

    private HologramManager hologramManager;

    public ApiManager load() {
        // Create plugin-config.yml
        File pluginConfig = new File(this.path + "plugin-config.yml");

        this.pluginConfig = SettingsManagerBuilder
                .withYamlFile(pluginConfig)
                .useDefaultMigrationService()
                .configurationData(ConfigBuilder.buildPluginConfig())
                .create();

        // Migrate the locale files.
        File localeDir = new File(this.path.toFile(), "locale");
        migrateLocale(localeDir);

        // Load the locale file.
        File localeFile = new File(localeDir, this.pluginConfig.getProperty(PluginConfig.LOCALE_FILE));

        this.locale = new Locale(localeFile);

        // Create config.yml
        File config = new File(this.path.toFile(), "config.yml");

        this.config = SettingsManagerBuilder
                .withYamlFile(config)
                .useDefaultMigrationService()
                .configurationData(ConfigBuilder.buildConfig())
                .create();

        this.crateManager = new CrateManager(this.plugin);
        this.crateManager.loadCrates();

        JsonCrateManager jsonCrateManager = new JsonCrateManager(
                this.path,
                this.plugin.getServer(),
                this.stick
        );

        jsonCrateManager.load();

        init();

        return this;
    }

    public void reload(boolean reloadCommand) {
        boolean hologramsToggle = this.pluginConfig.getProperty(PluginSupportSection.HOLOGRAMS_SUPPORT_ENABLED);

        if (hologramsToggle) {
            if (this.hologramManager != null) this.hologramManager.purge();

            HologramSupport hologramType = this.pluginConfig.getProperty(PluginSupportSection.HOLOGRAMS_SUPPORT_TYPE);

            switch (hologramType) {
                case cmi_holograms -> {
                    if (CMIModule.holograms.isEnabled()) {
                        this.hologramManager = new CMIHologramSupport();

                        return;
                    }

                    this.plugin.getLogger().warning("CMI support is enabled by you but the CMI Hologram Module is not enabled.");
                    this.plugin.getLogger().warning("Please go to Modules.yml in CMI & turn on the hologram module: Restart is required.");
                }

                case decent_holograms -> {
                    this.hologramManager = new DecentHologramSupport();
                    this.plugin.getLogger().warning("DecentHologram Support is enabled.");
                }
            }
        }

        // If the command is /crazycrates reload.
        if (reloadCommand) {
            this.pluginConfig.reload();
            this.config.reload();

            this.crateManager = new CrateManager(this.plugin);
            this.crateManager.loadCrates();

            JsonCrateManager jsonCrateManager = new JsonCrateManager(
                    this.path,
                    this.plugin.getServer(),
                    this.stick
            );

            jsonCrateManager.reload();

            init();
        }
    }

    private void init() {
        switch (this.pluginConfig.getProperty(PluginConfig.DATA_TYPE)) {
            case json -> this.userManager = new JsonUserManager(this.path, this.crateManager);
            case yaml -> this.userManager = new YamlUserManager(new File(this.path.toFile(), "users.yml"), this.crateManager);
        }

    private void migrateLocale(File localeDir) {
        File messages = new File(this.path + "messages.yml");
        File newFile = new File(localeDir, "en-US.yml");

        if (!localeDir.exists()) {
            if (!localeDir.mkdirs()) {
                this.plugin.getLogger().severe("Could not create crates directory! " +  localeDir.getAbsolutePath());
                return;
            }

            if (messages.exists()) {
                File renamedFile = new File(this.path + "en-US.yml");

                if (messages.renameTo(renamedFile)) this.plugin.getLogger().info("Renamed " + messages.getName() + " to " + renamedFile.getName());

                try {
                    Files.move(renamedFile.toPath(), newFile.toPath());
                    this.plugin.getLogger().warning("Moved " + renamedFile.getPath() + " to " + newFile.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        FileUtils.extract("/locale/", this.path, false);
    }

    // Config Management.
    public SettingsManager getPluginConfig() {
        return this.pluginConfig;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public SettingsManager getConfig() {
        return this.config;
    }

    // Crate Management
    public HologramManager getHolograms() {
        return this.hologramManager;
    }

    public UserManager getUserManager() {
        return this.userManager;
    }

    public CrateManager getCrateManager() {
        return this.crateManager;
    }
}