package com.badbones69.crazycrates.commands.v2;

import ch.jalu.configme.SettingsManager;
import com.badbones69.crazycrates.CrazyCrates;
import com.badbones69.crazycrates.api.commands.CommandEngine;
import com.badbones69.crazycrates.api.commands.CommandContext;
import com.badbones69.crazycrates.api.commands.reqs.CommandRequirementsBuilder;
import com.badbones69.crazycrates.api.configs.types.Locale;
import com.badbones69.crazycrates.api.configs.types.PluginConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class BaseCommand extends CommandEngine {

    private final CrazyCrates plugin = JavaPlugin.getPlugin(CrazyCrates.class);

    private final SettingsManager config = plugin.getApiManager().getPluginConfig();
    private final Locale locale = plugin.getApiManager().getLocale();

    public BaseCommand() {
        super();

        this.prefix = "crazycrates";

        this.ignoreInput = true;

        this.requirements = new CommandRequirementsBuilder()
                .asPlayer(false)
                .build();

        this.optionalMsg = locale.OPTIONAL_ARGUMENT();
        this.requiredMsg = locale.REQUIRED_ARGUMENT();

        this.tooFewArgs = locale.NOT_ENOUGH_ARGS();
        this.tooManyArgs = locale.TOO_MANY_ARGS();
    }

    @Override
    protected void perform(CommandContext context) {
        generateHelp(1, this.config.getProperty(PluginConfig.MAX_HELP_PAGE_ENTRIES), context, this.config);
    }
}