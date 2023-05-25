package com.badbones69.crazycrates.api.storage.interfaces;

import com.badbones69.crazycrates.api.storage.objects.UserData;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;

import java.io.File;
import java.util.Map;
import java.util.UUID;

public interface UserManager {

    void load();

    void save();

    void convert(File file, UUID uuid, StorageType storageType);

    void convertLegacy(File file, UUID uuid, StorageType storageType);

    void addUser(UUID uuid);

    UserData getUser(UUID uuid);

    void addKey(UUID uuid, String crateName, int amount);

    void removeKey(UUID uuid, String crateName, int amount);

    Map<String, Integer> getKeys(UUID uuid, String crateName);

    Map<UUID, UserData> getUsers(UUID uuid);

}