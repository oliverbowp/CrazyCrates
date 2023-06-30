package com.badbones69.crazycrates;

import com.badbones69.crazycrates.api.*;
import com.badbones69.crazycrates.api.commands.example.BaseCommand;
import com.badbones69.crazycrates.api.commands.example.TestCommand;
import com.badbones69.crazycrates.api.configs.types.PluginConfig;
import com.badbones69.crazycrates.api.holograms.interfaces.HologramManager;
import com.badbones69.crazycrates.listeners.v2.DataListener;
import com.badbones69.crazycrates.support.structures.blocks.ChestStateHandler;
import com.ryderbelserion.stick.paper.utils.PaperUtils;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrazyCrates extends JavaPlugin implements Listener {

    private ApiManager apiManager;

    private FileManager fileManager;
    private CrazyManager crazyManager;
    private ChestStateHandler chestStateHandler;
    private EventLogger eventLogger;

    private BaseCommand baseCommand;

    private boolean isEnabled;

    @Override
    public void onEnable() {
        if (PaperUtils.isSpigot()) {
            List<String> msg = List.of(
                    "We no longer support Spigot servers.",
                    "It is recommended that you switch to https://papermc.io/",
                    "The plugin will now shut-down!");

            msg.forEach(this.getLogger()::warning);

            this.getServer().getPluginManager().disablePlugin(this);

            this.isEnabled = false;

            return;
        }

        this.apiManager = new ApiManager(this, this.getDataFolder().toPath());
        this.apiManager.load();

        this.baseCommand = new BaseCommand(this.apiManager);

        this.baseCommand.addSubCommand(new TestCommand());

        PluginCommand command = this.getCommand(this.baseCommand.getPrefix());

        if (command != null) {
            command.setExecutor(this.baseCommand);

            command.setTabCompleter(this.baseCommand);
        }

        getServer().getPluginManager().registerEvents(new DataListener(), this);

        this.isEnabled = true;
    }

    @Override
    public void onDisable() {
        if (!this.isEnabled) return;

        //SessionManager.endCrates();

        //QuickCrate.removeAllRewards();

        if (this.apiManager.getUserManager() != null) this.apiManager.getUserManager().save();

        if (this.apiManager.getHolograms() != null) this.apiManager.getHolograms().purge();
    }

    public ApiManager getApiManager() {
        return this.apiManager;
    }

    public boolean verbose() {
        return getApiManager().getPluginConfig().getProperty(PluginConfig.VERBOSE_LOGGING);
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