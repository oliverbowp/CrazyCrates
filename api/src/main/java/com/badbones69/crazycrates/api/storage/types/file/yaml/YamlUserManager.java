package com.badbones69.crazycrates.api.storage.types.file.yaml;

import com.badbones69.crazycrates.api.crates.CrateManager;
import com.badbones69.crazycrates.api.storage.interfaces.UserManager;
import com.badbones69.crazycrates.api.storage.objects.UserData;
import com.badbones69.crazycrates.api.objects.Crate;
import com.ryderbelserion.stick.core.storage.enums.StorageType;
import org.bukkit.Bukkit;
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
    public void load() {
        try {
            if (!this.file.exists()) this.file.createNewFile();

            load(this.file);
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

    private void saveFile() {
        try {
            save(this.file);
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        // If user data empty return.
        if (this.userData.isEmpty()) return;

        // If the player is not leaving, continue here as we are stopping the server or doing periodic save.
        this.userData.forEach((id, user) -> {
            user.getKeys().forEach((crateMap, keys) -> set("users." + id + "." + crateMap, keys));

            // Save the file then load the changes back in.
            saveFile();
        });
    }

    @Override
    public void saveSingular(UUID uuid) {
        // If user data empty return.
        if (this.userData.isEmpty()) return;

        // Check if user data contains keys.
        if (this.userData.containsKey(uuid)) {
            // Add keys to file.
            this.userData.get(uuid).getKeys().forEach((crateMap, keys) -> set("users." + uuid + "." + crateMap, keys));
            // Remove user when done.
            this.userData.remove(uuid);

            // Save the file then load the changes back in.
            saveFile();
        }
    }

    @Override
    public void convert(File file, UUID uuid, StorageType storageType, Crate crate) {
        //TODO() Add a second data type.
        //TODO() Convert from it.
    }

    @Override
    public void convertLegacy(File file, UUID uuid, StorageType storageType, Crate crate) {
        YamlConfiguration legacy = YamlConfiguration.loadConfiguration(file);

        ConfigurationSection section = legacy.getConfigurationSection("Players");

        addUser(uuid, crate);

        if (section != null && section.contains(String.valueOf(uuid))) {
            section.getKeys(false).forEach(value -> {
                int amount = legacy.getInt("Players." + uuid + "." + value);

                Bukkit.getLogger().warning("Keys: " + amount + " UUID:" + uuid + " Crate: " + value);

                addKey(uuid, amount, crate);

                save();
            });
        }
    }

    @Override
    public void addUser(UUID uuid, Crate crate) {
        ConfigurationSection section = getConfigurationSection("users");

        if (section != null && section.contains(String.valueOf(uuid))) {
            section.getKeys(false).forEach(value -> {
                if (this.crateManager.getCrates().contains(crate) && crate.getCrateName().equals(value)) {
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
        if (Bukkit.getPlayer(uuid) == null) {
            ConfigurationSection section = getConfigurationSection("users");

            if (section != null && section.contains(String.valueOf(uuid))) {
                // Add user if it doesn't exist.
                addUser(uuid, crate);

                // Loop through the section and get the amount of each crate.
                section.getKeys(false).forEach(value -> {
                    int currentAmount = getInt("users." + uuid + "." + value);

                    // Populate the hashmap.
                    addKey(uuid, currentAmount, crate);
                });
            }
        }

        return this.userData.get(uuid);
    }

    @Override
    public void addKey(UUID uuid, int amount, Crate crate) {
        if (Bukkit.getPlayer(uuid) == null) {
            ConfigurationSection section = getConfigurationSection("users");

            if (section != null && section.contains(String.valueOf(uuid))) {
                int currentAmount = getInt("users." + uuid + "." + crate.getCrateName());

                set("users." + uuid + "." + crate.getCrateName(), currentAmount+amount);
                saveFile();

                return;
            }

            return;
        }

        if (this.userData.containsKey(uuid)) this.userData.get(uuid).addKey(crate, amount);
    }

    @Override
    public void removeKey(UUID uuid, int amount, Crate crate) {
        //TODO() If the user is not online, remove directly from the yml file.
        if (Bukkit.getPlayer(uuid) == null) {
            ConfigurationSection section = getConfigurationSection("users");

            if (section != null && section.contains(String.valueOf(uuid))) {
                int currentAmount = getInt("users." + uuid + "." + crate.getCrateName());

                if (currentAmount-amount < 0) {
                    Bukkit.getLogger().warning("Amount cannot be less then 0.");
                    return;
                }

                set("users." + uuid + "." + crate.getCrateName(), currentAmount-amount);
                saveFile();

                return;
            }

            return;
        }

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