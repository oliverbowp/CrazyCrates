package com.badbones69.crazycrates.api.commands.example;

import com.badbones69.crazycrates.api.ApiManager;
import com.badbones69.crazycrates.api.commands.CommandEngine;
import com.badbones69.crazycrates.api.commands.CommandInfo;
import com.badbones69.crazycrates.api.commands.reqs.CommandRequirements;
import com.badbones69.crazycrates.api.commands.reqs.CommandRequirementsBuilder;
import com.badbones69.crazycrates.api.configs.types.PluginConfig;

public class BaseCommand extends CommandEngine {

    private final ApiManager apiManager;

    public BaseCommand(ApiManager apiManager) {
        this.apiManager = apiManager;

        CommandRequirements builder = new CommandRequirementsBuilder()
                .build();

        setPrefix("crazycrates");

        setRequirements(builder);
    }

    @Override
    protected boolean execute(CommandInfo info) {
        generateHelp(1, this.apiManager.getPluginConfig().getProperty(PluginConfig.MAX_HELP_PAGE_ENTRIES), info.getSender(), this.apiManager);

        return true;
    }
}