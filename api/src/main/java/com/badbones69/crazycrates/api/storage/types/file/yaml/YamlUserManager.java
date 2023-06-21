package com.badbones69.crazycrates.api.storage.types.file.yaml;

import com.badbones69.crazycrates.api.crates.CrateManager;
import com.badbones69.crazycrates.api.storage.interfaces.UserManager;
import com.badbones69.crazycrates.api.storage.objects.UserData;
import com.badbones69.crazycrates.objects.Crate;
import com.ryderbelserion.stick.paper.Stick;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class YamlUserManager extends YamlConfiguration implements UserManager {

    private final File file;
    private final CrateManager crateManager;

    private final ConcurrentHashMap<UUID, UserData> userData = new ConcurrentHashMap<>();

    public YamlUserManager(File file, CrateManager crateManager) {
        this.file = file;

        this.crateManager = crateManager;
    }

    @Override
    public void load(Stick stick) {
        try {
            if (!this.file.exists()) this.file.createNewFile();

            load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private File getFile() {
        return this.file;
    }

    private Configuration getConfiguration() {
        return this;
    }

    @Override
    public void save(Stick stick) {
        if (!this.userData.isEmpty()) {
            this.userData.forEach((uuid, user) -> {
                user.getKeys().forEach((crate, keys) -> set("users." + uuid + "." + crate, keys));

                try {
                    save(this.file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            return;
        }

        try {
            save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void convert(File file, UUID uuid, StorageType storageType, Crate crate) {
        //TODO() Switch between data types
    }

    @Override
    public void convertLegacy(File file, UUID uuid, StorageType storageType, Crate crate) {
        //TODO() Convert old files
    }

    @Override
    public void addUser(UUID uuid, Crate crate) {
        ConfigurationSection section = getConfigurationSection("users");

        if (section != null && section.contains("users." + uuid)) {
            section.getKeys(false).forEach(value -> {
                if (crateManager.getCrates().contains(crate) && crate.getCrateName().equals(value)) {
                    int amount = getInt("users." + uuid + "." + crate.getCrateName());

                    addKey(uuid, amount, crate);
                }
            });

            return;
        }

        if (!this.userData.containsKey(uuid)) this.userData.put(uuid, new UserData(uuid));
    }

    @Override
    public UserData getUser(UUID uuid, Crate crate) {
        return this.userData.get(uuid);
    }

    @Override
    public void addKey(UUID uuid, int amount, Crate crate) {
        if (this.userData.containsKey(uuid)) this.userData.get(uuid).addKey(crate, amount);
    }

    @Override
    public void removeKey(UUID uuid, int amount, Crate crate) {
        if (this.userData.containsKey(uuid)) this.userData.get(uuid).removeKey(crate, amount);
    }

    @Override
    public Map<String, Integer> getKeys(UUID uuid, Crate crate) {
        return getUser(uuid, crate).getKeys();
    }

    @Override
    public Map<UUID, UserData> getUsers(UUID uuid) {
        return Collections.unmodifiableMap(this.userData);
    }
}