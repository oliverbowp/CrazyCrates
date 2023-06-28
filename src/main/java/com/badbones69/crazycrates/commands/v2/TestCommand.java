package com.badbones69.crazycrates.commands.v2;

import com.badbones69.crazycrates.CrazyCrates;
import dev.triumphteam.cmd.core.BaseCommand;
import dev.triumphteam.cmd.core.annotation.Command;
import dev.triumphteam.cmd.core.annotation.Default;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.UUID;

@Command("test")
public class TestCommand extends BaseCommand {

    private final CrazyCrates plugin = JavaPlugin.getPlugin(CrazyCrates.class);

    @Default
    public void execute(CommandSender sender) {
        //this.plugin.getApiManager().getUserManager().addKey(UUID.fromString("64ccbf4e-87d2-490f-9370-8c4e53df9013"), 3, this.plugin.getApiManager().getCrateManager().getCrates().get(1));
        this.plugin.getApiManager().getUserManager().addUser(UUID.fromString("64ccbf4e-87d2-490f-9370-8c4e53df9013"), this.plugin.getApiManager().getCrateManager().getCrates().get(1));
    }
}