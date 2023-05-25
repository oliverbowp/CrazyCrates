package com.badbones69.crazycrates.api.storage.managers;

import com.badbones69.crazycrates.api.ApiManager;
import com.badbones69.crazycrates.api.storage.interfaces.UserManager;
import com.badbones69.crazycrates.api.storage.objects.UserData;
import com.badbones69.crazycrates.api.storage.types.JsonStorage;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class JsonManager extends JsonStorage implements UserManager {

    private final Path path;

    public JsonManager(Path path) {
        super(path);

        this.path = path;
    }

    @Override
    public void load() {
        ApiManager.getStickCore().getFileHandler().addFile(new JsonStorage(this.path));
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

        if (section != null) {
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