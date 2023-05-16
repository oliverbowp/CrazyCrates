package com.badbones69.crazycrates.api.v2;

import ch.jalu.configme.SettingsManager;
import ch.jalu.configme.SettingsManagerBuilder;
import com.badbones69.crazycrates.api.v2.configs.ConfigBuilder;
import com.badbones69.crazycrates.api.v2.storage.interfaces.UserManager;
import com.ryderbelserion.stick.paper.Stick;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.nio.file.Path;

public class ApiManager {

    private static Stick stick;

    private UserManager userManager;

    private SettingsManager pluginSettings;

    private final Path path;
    private final JavaPlugin plugin;

    public ApiManager(Path path, JavaPlugin plugin) {
        this.path = path;

        this.plugin = plugin;
    }

    public ApiManager load() {
        // This must go first.
        stick = new Stick(this.path, plugin.getName());

        File pluginSettings = new File(this.path.toFile(), "plugin-settings.yml");

        this.pluginSettings = SettingsManagerBuilder
                .withYamlFile(pluginSettings)
                .useDefaultMigrationService()
                .configurationData(ConfigBuilder.buildConfigurationData())
                .create();

        //this.userManager = new JsonManager(this.path);

        //this.userManager.load();
        //LocationsData.load(stick, this.path);

        //UUID uuid = UUID.fromString("64ccbf4e-87d2-490f-9370-8c4e53df9013");

        //this.userManager.addUser(uuid);

        //this.userManager.save();

        //this.userManager.addUser(uuid);

        //this.userManager.addKey(uuid, "test", 5);
        //this.userManager.addKey(uuid, "test2", 6);

        //LocationsData.load(this.crazyCore, this.path);

        //LocationsData.addLocation("test", new Location(Bukkit.getWorld("world"), 1, 1, 1, 1, 1));
        //LocationsData.addLocation("test2", new Location(Bukkit.getWorld("world"), 3, 4, 5, 6, 1));

        //LocationsData.save(this.crazyCore, this.path);
        //this.userManager.save(this.crazyCore);

        return this;
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
}