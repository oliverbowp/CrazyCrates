package com.badbones69.crazycrates.commands.example;

import ch.jalu.configme.SettingsManager;
import com.badbones69.crazycrates.CrazyCrates;
import com.badbones69.crazycrates.api.commands.CommandEngine;
import com.badbones69.crazycrates.api.commands.CommandContext;
import com.badbones69.crazycrates.api.commands.reqs.CommandRequirementsBuilder;
import com.badbones69.crazycrates.api.commands.sender.args.Argument;
import com.badbones69.crazycrates.api.commands.sender.args.builder.IntArgument;
import com.badbones69.crazycrates.api.configs.types.PluginConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class BaseCommand extends CommandEngine {

    private final CrazyCrates plugin = JavaPlugin.getPlugin(CrazyCrates.class);

    private final SettingsManager config = plugin.getApiManager().getPluginConfig();

    public BaseCommand() {
        super();

        this.prefix = "crazycrates";

        this.ignoreInput = true;

        this.requirements = new CommandRequirementsBuilder()
                .withRawPermission("example.test")
                .asPlayer(false)
                .build();

        this.optionalArgs.add(new Argument("page", 0, new IntArgument(10)));

        this.optionalMsg = "<green>This argument is optional</green>";
        this.requiredMsg = "<red>This argument is not optional</red>";

        this.tooFewArgs = "<red>Too few args</red>";
        this.tooManyArgs = "<green>Too many args</green>";
    }

    @Override
    protected void perform(CommandContext context) {
        generateHelp(1, this.config.getProperty(PluginConfig.MAX_HELP_PAGE_ENTRIES), context, this.config);
    }
}