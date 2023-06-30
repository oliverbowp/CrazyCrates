package com.badbones69.crazycrates.api.commands.example;

import com.badbones69.crazycrates.api.ApiManager;
import com.badbones69.crazycrates.api.commands.CommandEngine;
import com.badbones69.crazycrates.api.commands.CommandInfo;
import com.badbones69.crazycrates.api.commands.reqs.CommandRequirements;
import com.badbones69.crazycrates.api.commands.reqs.CommandRequirementsBuilder;
import com.badbones69.crazycrates.api.configs.types.PluginConfig;

public class TestCommand extends CommandEngine {

    private final ApiManager apiManager;

    public TestCommand(ApiManager apiManager) {
        this.apiManager = apiManager;

        addAlias("test");

        CommandRequirements builder = new CommandRequirementsBuilder()
                .asPlayer(false)
                .withRawPermission("test.permission")
                .build();

        setRequirements(builder);
    }

    @Override
    protected boolean execute(CommandInfo info) {
        info.reply("<red>Guten Tag!</red>");

        generateHelp(1, this.apiManager.getPluginConfig().getProperty(PluginConfig.MAX_HELP_PAGE_ENTRIES), info.getSender(), this.apiManager);

        return true;
    }
}