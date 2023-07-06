package com.badbones69.crazycrates.commands.v2;

import ch.jalu.configme.SettingsManager;
import com.badbones69.crazycrates.CrazyCrates;
import com.badbones69.crazycrates.api.commands.CommandContext;
import com.badbones69.crazycrates.api.commands.CommandEngine;
import com.badbones69.crazycrates.api.commands.reqs.CommandRequirementsBuilder;
import com.badbones69.crazycrates.api.commands.sender.args.Argument;
import com.badbones69.crazycrates.api.commands.sender.args.builder.IntArgument;
import com.badbones69.crazycrates.api.configs.types.Locale;
import com.badbones69.crazycrates.api.configs.types.PluginConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class HelpCommand extends CommandEngine {

    private final CrazyCrates plugin = JavaPlugin.getPlugin(CrazyCrates.class);

    private final SettingsManager config = plugin.getApiManager().getPluginConfig();
    private final Locale locale = plugin.getApiManager().getLocale();

    public HelpCommand() {
        super();

        addAlias("help");

        this.requiredArgs.add(new Argument("page", 0, new IntArgument(5)));

        this.description = "The help command for crazycrates.";

        this.requirements = new CommandRequirementsBuilder()
                .withRawPermission("crazycrates.command.help")
                .asPlayer(false)
                .build();
    }

    @Override
    protected void perform(CommandContext context) {
        int arg = context.getArgAsInt(0, true, locale.NOT_A_NUMBER(), "{number}");

        plugin.getBaseCommand().generateHelp(arg, this.config.getProperty(PluginConfig.MAX_HELP_PAGE_ENTRIES), context, this.config);
    }
}