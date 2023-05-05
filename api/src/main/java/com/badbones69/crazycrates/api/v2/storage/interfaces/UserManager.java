package com.badbones69.crazycrates.api.v2.storage.interfaces;

import com.badbones69.crazycrates.api.v2.storage.objects.UserData;

import java.util.Map;
import java.util.UUID;

public interface UserManager {

    void addUser(UUID uuid);

    UserData getUser(UUID uuid);

    void addKey(UUID uuid, String crateName);

    void removeKey(UUID uuid, String crateName);

    Map<String, Integer> getKeys(UUID uuid, String crateName);

    Map<UUID, UserData> getUsers(UUID uuid);

}