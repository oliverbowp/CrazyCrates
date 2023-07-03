package com.badbones69.crazycrates.api.commands.example;

import com.badbones69.crazycrates.api.commands.CommandEngine;
import com.badbones69.crazycrates.api.commands.CommandContext;
import com.badbones69.crazycrates.api.commands.reqs.CommandRequirements;
import com.badbones69.crazycrates.api.commands.reqs.CommandRequirementsBuilder;

public class BaseCommand extends CommandEngine {

    public BaseCommand() {
        CommandRequirements builder = new CommandRequirementsBuilder()
                .withRawPermission("example.test")
                .asPlayer(true)
                .build();

        setPrefix("crazycrates");

        setRequirements(builder);
    }

    @Override
    protected void perform(CommandContext context) {
        //generateHelp(1, this.apiManager.getPluginConfig().getProperty(PluginConfig.MAX_HELP_PAGE_ENTRIES), info.getSender(), this.apiManager);
    }
}