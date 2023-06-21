package com.badbones69.crazycrates;

import com.badbones69.crazycrates.api.ApiManager;
import com.badbones69.crazycrates.api.CrazyManager;
import com.badbones69.crazycrates.api.EventLogger;
import com.badbones69.crazycrates.api.FileManager;
import com.badbones69.crazycrates.api.holograms.interfaces.HologramManager;
import com.badbones69.crazycrates.support.structures.blocks.ChestStateHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class CrazyCrates extends JavaPlugin implements Listener {
    private ApiManager apiManager;

    private FileManager fileManager;
    private CrazyManager crazyManager;
    private ChestStateHandler chestStateHandler;
    private EventLogger eventLogger;

    @Override
    public void onEnable() {
        apiManager = new ApiManager(getDataFolder().toPath());
        apiManager.load(this);

        //getServer().getPluginManager().registerEvents(new DataListener(), this);
    }

    @Override
    public void onDisable() {
        //SessionManager.endCrates();

        //QuickCrate.removeAllRewards();

        if (apiManager.getHolograms() != null) apiManager.getHolograms().purge(this);
    }

    public ApiManager getApiManager() {
        return apiManager;
    }

    public HologramManager getHolograms() {
        return getApiManager().getHolograms();
    }

    public FileManager getFileManager() {
        return this.fileManager;
    }

    public CrazyManager getCrazyManager() {
        return this.crazyManager;
    }

    public ChestStateHandler getChestStateHandler() {
        return this.chestStateHandler;
    }

    public EventLogger getEventLogger() {
        return this.eventLogger;
    }
}