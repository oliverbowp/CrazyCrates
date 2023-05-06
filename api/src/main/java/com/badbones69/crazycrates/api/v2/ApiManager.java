package com.badbones69.crazycrates.api.v2;

import ch.jalu.configme.SettingsManager;
import ch.jalu.configme.SettingsManagerBuilder;
import com.badbones69.crazycrates.api.v2.configs.ConfigBuilder;
import com.badbones69.crazycrates.api.v2.storage.LocationsData;
import com.badbones69.crazycrates.api.v2.storage.interfaces.UserManager;
import com.badbones69.crazycrates.api.v2.storage.managers.JsonManager;
import com.badbones69.crazycrates.api.v2.storage.types.JsonStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import us.crazycrew.crazycore.paper.CrazyCore;
import java.io.File;
import java.nio.file.Path;
import java.util.UUID;

public class ApiManager {

    private CrazyCore crazyCore;

    private UserManager userManager;

    private LocationsData locationsData;

    private SettingsManager pluginSettings;

    private final Path path;

    public ApiManager(Path path) {
        this.path = path;
    }

    public ApiManager load() {
        this.crazyCore = new CrazyCore(this.path, "CrazyCrates");

        File pluginSettings = new File(this.path.toFile(), "plugin-settings.yml");

        this.pluginSettings = SettingsManagerBuilder
                .withYamlFile(pluginSettings)
                .useDefaultMigrationService()
                .configurationData(ConfigBuilder.buildConfigurationData())
                .create();

        this.userManager = new JsonManager();

        this.userManager.load(this.crazyCore, this.path);

        UUID uuid = UUID.fromString("64ccbf4e-87d2-490f-9370-8c4e53df9013");

        this.userManager.addUser(uuid);

        this.userManager.addKey(uuid, "test", 5);
        this.userManager.addKey(uuid, "test2", 6);

        //this.locationsData = new LocationsData(this.path);
        //this.locationsData.load(this.crazyCore, this.path);

        //this.locationsData.addLocation("test", new Location(Bukkit.getWorld("second"), 1, 1, 1, 1, 1));
        //this.locationsData.addLocation("test2", new Location(Bukkit.getWorld("other"), 3, 4, 5, 6, 1));

        //this.locationsData.save(this.crazyCore, this.path);
        this.userManager.save(this.crazyCore, this.path);

        return this;
    }

    public ApiManager save() {
        return this;
    }

    public CrazyCore getCrazyCore() {
        return this.crazyCore;
    }

    public UserManager getUserManager() {
        return this.userManager;
    }

    public LocationsData getLocationsData() {
        return this.locationsData;
    }

    public SettingsManager getPluginSettings() {
        return this.pluginSettings;
    }
}