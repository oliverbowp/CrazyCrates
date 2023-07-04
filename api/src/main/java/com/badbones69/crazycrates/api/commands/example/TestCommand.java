package com.badbones69.crazycrates.api.commands.example;

import com.badbones69.crazycrates.api.commands.CommandEngine;
import com.badbones69.crazycrates.api.commands.CommandContext;
import com.badbones69.crazycrates.api.commands.reqs.CommandRequirements;
import com.badbones69.crazycrates.api.commands.reqs.CommandRequirementsBuilder;

public class TestCommand extends CommandEngine {

    public TestCommand() {
        addAlias("test");

        CommandRequirements builder = new CommandRequirementsBuilder()
                .withRawPermission("test.permission")
                .asPlayer(true)
                .build();

        setRequirements(builder);
    }

    @Override
    protected void perform(CommandContext context) {
        context.reply("<red>Guten Tag!</red>");
    }
}