package com.badbones69.crazycrates.api.commands.example;

import com.badbones69.crazycrates.api.commands.CommandEngine;
import com.badbones69.crazycrates.api.commands.CommandContext;
import com.badbones69.crazycrates.api.commands.reqs.CommandRequirementsBuilder;

public class TestCommand extends CommandEngine {

    public TestCommand() {
        addAlias("test");

        this.isCommandVisible = false;

        this.description = "A test command that normally is hidden.";

        this.requirements = new CommandRequirementsBuilder()
                .withRawPermission("test.permission")
                .asPlayer(true)
                .build();
    }

    @Override
    protected void perform(CommandContext context) {
        context.reply("<red>Guten Tag!</red>");
    }
}