package com.badbones69.crazycrates.listeners.v2;

import com.badbones69.crazycrates.CrazyCrates;
import com.badbones69.crazycrates.api.ApiManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class DataListener implements Listener {

    private final CrazyCrates plugin = CrazyCrates.getPlugin();

    private final ApiManager apiManager = plugin.getApiManager();

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        apiManager.getUserManager().addUser(uuid, null);

        apiManager.getCrateManager().getCrates().forEach(crate -> {
            if (crate.getCrateConfig().isStartingKeysEnabled()) apiManager.getUserManager().addKey(uuid, crate.getCrateConfig().getStartingKeysAmount(), crate);
        });

        apiManager.getUserManager().save();
    }
}