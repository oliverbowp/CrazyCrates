package com.badbones69.crazycrates.api.storage.objects;

import com.google.gson.annotations.Expose;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserData {

    private final UUID uuid;

    @Expose
    private ConcurrentHashMap<String, Integer> keys = new ConcurrentHashMap<>();

    public UserData(UUID uuid) {
        this.uuid = uuid;
    }

    public void addKey(String crateName, int amount) {
        if (getKeys().containsKey(crateName)) {

            keys.put(crateName, getKey(crateName) + amount);
            return;
        }

        keys.put(crateName, amount);
    }

    public void removeKey(String crateName, int amount) {
        if (!getKeys().containsKey(crateName)) {
            Bukkit.getLogger().warning("They don't have any keys to remove.");
            return;
        }

        keys.remove(crateName, keys.get(crateName) - amount);
    }

    public int getKey(String crateName) {
        if (!getKeys().containsKey(crateName)) {
            Bukkit.getLogger().warning("They don't have any keys to fetch.");
            return 0;
        }

        return getKeys().get(crateName);
    }

    public Map<String, Integer> getKeys() {
        return Collections.unmodifiableMap(keys);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }
}