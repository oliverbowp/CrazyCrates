package com.badbones69.crazycrates.api.v2.storage.managers;

import com.badbones69.crazycrates.api.v2.storage.interfaces.UserManager;
import com.badbones69.crazycrates.api.v2.storage.objects.UserData;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import java.io.File;
import java.util.Map;
import java.util.UUID;

public class YamlManager implements UserManager {

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