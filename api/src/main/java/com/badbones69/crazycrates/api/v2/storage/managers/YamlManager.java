package com.badbones69.crazycrates.api.v2.storage.managers;

import com.badbones69.crazycrates.api.v2.storage.interfaces.UserManager;
import com.badbones69.crazycrates.api.v2.storage.objects.UserData;
import com.badbones69.crazycrates.api.v2.storage.types.YamlStorage;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class YamlManager extends YamlStorage implements UserManager {

    private final Path path;

    public YamlManager(Path path) {
        super(path);

        this.path = path;
    }

    @Override
    public void load() {

    }

    @Override
    public void save() {

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

            Objects.requireNonNull(section.getConfigurationSection("." + uuid)).getKeys(true).forEach(value -> {
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

    }

    @Override
    public UserData getUser(UUID uuid) {
        return null;
    }

    @Override
    public void addKey(UUID uuid, String crateName, int amount) {

    }

    @Override
    public void removeKey(UUID uuid, String crateName, int amount) {

    }

    @Override
    public Map<String, Integer> getKeys(UUID uuid, String crateName) {
        return null;
    }

    @Override
    public Map<UUID, UserData> getUsers(UUID uuid) {
        return null;
    }
}