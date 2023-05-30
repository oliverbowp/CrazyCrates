package com.badbones69.crazycrates.api.storage.managers;

import com.badbones69.crazycrates.api.ApiManager;
import com.badbones69.crazycrates.api.storage.interfaces.UserManager;
import com.badbones69.crazycrates.api.storage.objects.UserData;
import com.badbones69.crazycrates.api.storage.types.JsonStorage;
import com.badbones69.crazycrates.api.storage.types.locations.CrateLocation;
import com.badbones69.crazycrates.api.storage.types.locations.adapters.CustomTypeAdapter;
import com.google.gson.GsonBuilder;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import com.ryderbelserion.stick.paper.storage.types.file.json.adapters.LocationTypeAdapter;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class JsonUserManager extends JsonStorage implements UserManager {

    private final Path path;

    public JsonUserManager(Path path) {
        super(path);

        // Assign the path.
        this.path = path;
    }

    GsonBuilder builder = new GsonBuilder().disableHtmlEscaping()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .excludeFieldsWithoutExposeAnnotation()
            .registerTypeAdapter(Location.class, new LocationTypeAdapter())
            .registerTypeAdapter(CrateLocation.class, new CustomTypeAdapter());

    @Override
    public void load() {
        JsonStorage jsonStorage = new JsonStorage(this.path);

        //jsonStorage.setAdapters(CustomTypeAdapter.class, new CustomTypeAdapter());

        ApiManager.getStickCore().getFileHandler().addFile(jsonStorage);
    }

    @Override
    public void save() {
        ApiManager.getStickCore().getFileHandler().saveFile(new JsonStorage(this.path));
    }

    @Override
    public void convert(File file, UUID uuid, StorageType storageType) {

    }

    @Override
    public void convertLegacy(File file, UUID uuid, StorageType storageType) {
        if (!file.exists()) return;

        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        ConfigurationSection section = configuration.getConfigurationSection("Players");

        if (section == null) return;

        UserData data = new UserData(uuid);

        if (!userData.containsKey(uuid)) userData.put(uuid, data);

        section.getConfigurationSection("." + uuid).getKeys(true).forEach(value -> {
            if (!value.equals("Name")) {
                String amount = section.getString("." + uuid + "." + value);

                if (amount != null) {
                    data.addKey(value, Integer.parseInt(amount));

                    save();
                }
            }
        });
    }

    @Override
    public void addUser(UUID uuid) {
        convert(new File(this.path.toFile(), "data.yml"), uuid, StorageType.JSON);

        if (!userData.containsKey(uuid)) userData.put(uuid, new UserData(uuid));
    }

    @Override
    public UserData getUser(UUID uuid) {
        addUser(uuid);

        return userData.get(uuid);
    }

    @Override
    public void addKey(UUID uuid, String crateName, int amount) {
        if (userData.containsKey(uuid)) userData.get(uuid).addKey(crateName, amount); else addUser(uuid);
    }

    @Override
    public void removeKey(UUID uuid, String crateName, int amount) {
        if (userData.containsKey(uuid)) userData.get(uuid).removeKey(crateName, amount); else addUser(uuid);
    }

    @Override
    public Map<String, Integer> getKeys(UUID uuid, String crateName) {
        return getUser(uuid).getKeys();
    }

    @Override
    public Map<UUID, UserData> getUsers(UUID uuid) {
        return Collections.unmodifiableMap(userData);
    }
}