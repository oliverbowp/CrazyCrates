package com.badbones69.crazycrates.listeners.v2;

import com.badbones69.crazycrates.CrazyCrates;
import com.badbones69.crazycrates.api.v2.ApiManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DataListener implements Listener {

    private final CrazyCrates plugin = CrazyCrates.getPlugin();

    private final ApiManager apiManager = plugin.getApiManager();

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        //JsonStorage.addUser(event.getPlayer().getUniqueId());

        //JsonStorage.addKey(event.getPlayer().getUniqueId(), "Example", 3);
        //JsonStorage.addKey(event.getPlayer().getUniqueId(), "Example x2", 5);

        //JsonStorage.save(apiManager.getCrazyCore().getFileHandler(), plugin.getDataFolder().toPath());
    }
}