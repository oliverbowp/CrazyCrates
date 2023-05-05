package com.badbones69.crazycrates.api.v2.storage;

import com.badbones69.crazycrates.api.v2.storage.types.JsonStorage;
import com.badbones69.crazycrates.api.v2.storage.interfaces.UserManager;
import com.badbones69.crazycrates.api.v2.storage.objects.UserData;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class DataManager extends JsonStorage implements UserManager {

    @Override
    public void addKey(UUID uuid, String crateName) {
        getUser(uuid).addKey(crateName, 1);
    }

    @Override
    public void removeKey(UUID uuid, String crateName) {
        getUser(uuid).removeKey(crateName, 1);
    }

    @Override
    public Map<String, Integer> getKeys(UUID uuid, String crateName) {
        return getUser(uuid).getKeys();
    }

    @Override
    public Map<UUID, UserData> getUsers(UUID uuid) {
        return Collections.unmodifiableMap(userData);
    }

    @Override
    public void addUser(UUID uuid) {
        if (!userData.containsKey(uuid)) userData.put(uuid, new UserData(uuid));
    }

    @Override
    public UserData getUser(UUID uuid) {
        addUser(uuid);

        return userData.get(uuid);
    }
}