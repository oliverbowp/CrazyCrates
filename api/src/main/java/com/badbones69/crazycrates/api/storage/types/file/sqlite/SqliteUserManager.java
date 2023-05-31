package com.badbones69.crazycrates.api.storage.types.file.sqlite;

import com.badbones69.crazycrates.api.storage.interfaces.UserManager;
import com.badbones69.crazycrates.api.storage.objects.UserData;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

public non-sealed class SqliteUserManager extends SqliteStorage implements UserManager {

    private final Path path;

    public SqliteUserManager(Path path) {
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