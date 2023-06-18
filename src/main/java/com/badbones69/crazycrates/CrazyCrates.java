package com.badbones69.crazycrates;

import ch.jalu.configme.SettingsManager;
import com.badbones69.crazycrates.api.ApiManager;
import com.badbones69.crazycrates.api.CrazyManager;
import com.badbones69.crazycrates.api.EventLogger;
import com.badbones69.crazycrates.api.FileManager;
import com.badbones69.crazycrates.api.holograms.interfaces.HologramManager;
import com.badbones69.crazycrates.listeners.v2.DataListener;
import com.badbones69.crazycrates.support.structures.blocks.ChestStateHandler;
import dev.triumphteam.cmd.bukkit.BukkitCommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class CrazyCrates extends JavaPlugin implements Listener {

    private static CrazyCrates plugin;

    private ApiManager apiManager;

    private FileManager fileManager;
    private CrazyManager crazyManager;
    private ChestStateHandler chestStateHandler;
    private EventLogger eventLogger;

    @Override
    public void onEnable() {
        plugin = this;

        this.apiManager = new ApiManager(getDataFolder().toPath(), plugin);
        this.apiManager.load();

        getServer().getPluginManager().registerEvents(new DataListener(), this);
    }

    @Override
    public void onDisable() {
        //SessionManager.endCrates();

        //QuickCrate.removeAllRewards();

        if (this.apiManager.getHolograms() != null) this.apiManager.getHolograms().purge(this);
    }

    public static CrazyCrates getPlugin() {
        return plugin;
    }

    public ApiManager getApiManager() {
        return this.apiManager;
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

    public HologramManager getHolograms() {
        return getApiManager().getHolograms();
    }

    public SettingsManager getConfigSettings() {
        return getApiManager().getConfigSettings();
    }

    public SettingsManager getPluginSettings() {
        return getApiManager().getPluginSettings();
    }
}