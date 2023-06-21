package com.badbones69.crazycrates.api.storage.interfaces;

import com.badbones69.crazycrates.api.storage.objects.UserData;
import com.badbones69.crazycrates.objects.Crate;
import com.ryderbelserion.stick.paper.Stick;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;

import java.io.File;
import java.util.Map;
import java.util.UUID;

public interface UserManager {

    void load(Stick stick);

    void save(Stick stick);

    void convert(File file, UUID uuid, StorageType storageType, Crate crate);

    void convertLegacy(File file, UUID uuid, StorageType storageType, Crate crate);

    void addUser(UUID uuid, Crate crate);

    UserData getUser(UUID uuid, Crate crate);

    void addKey(UUID uuid, int amount, Crate crate);

    void removeKey(UUID uuid, int amount, Crate crate);

    Map<String, Integer> getKeys(UUID uuid, Crate crate);

    Map<UUID, UserData> getUsers(UUID uuid);

}