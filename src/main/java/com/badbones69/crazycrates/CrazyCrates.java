package com.badbones69.crazycrates;

import com.badbones69.crazycrates.api.*;
import com.badbones69.crazycrates.api.holograms.interfaces.HologramManager;
import com.badbones69.crazycrates.commands.v2.TestCommand;
import com.badbones69.crazycrates.listeners.v2.DataListener;
import com.badbones69.crazycrates.support.structures.blocks.ChestStateHandler;
import dev.triumphteam.cmd.bukkit.BukkitCommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class CrazyCrates extends JavaPlugin implements Listener {

    private ApiManager apiManager;

    private FileManager fileManager;
    private CrazyManager crazyManager;
    private ChestStateHandler chestStateHandler;
    private EventLogger eventLogger;

    private final BukkitCommandManager<CommandSender> manager = BukkitCommandManager.create(this);

    @Override
    public void onEnable() {
        apiManager = new ApiManager(this, this.getDataFolder().toPath());
        apiManager.load();

        manager.registerCommand(new TestCommand());

        getServer().getPluginManager().registerEvents(new DataListener(), this);
    }

    @Override
    public void onDisable() {
        //SessionManager.endCrates();

        //QuickCrate.removeAllRewards();

        if (apiManager.getHolograms() != null) apiManager.getHolograms().purge();
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