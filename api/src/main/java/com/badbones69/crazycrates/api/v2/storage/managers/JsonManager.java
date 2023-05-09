package com.badbones69.crazycrates.api.v2.storage.managers;

import com.badbones69.crazycrates.api.v2.storage.interfaces.UserManager;
import com.badbones69.crazycrates.api.v2.storage.objects.UserData;
import com.badbones69.crazycrates.api.v2.storage.types.JsonStorage;
import us.crazycrew.crazycore.paper.CrazyCore;
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
    public void load(CrazyCore crazyCore) {
        crazyCore.getFileHandler().addFile(new JsonStorage(path).setData(true));
    }

    @Override
    public void save(CrazyCore crazyCore) {
        crazyCore.getFileHandler().saveFile(new JsonStorage(path).setData(true));
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