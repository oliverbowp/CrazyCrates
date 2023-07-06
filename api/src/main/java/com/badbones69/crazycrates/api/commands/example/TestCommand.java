package com.badbones69.crazycrates.api.commands.example;

import com.badbones69.crazycrates.api.commands.CommandEngine;
import com.badbones69.crazycrates.api.commands.CommandContext;
import com.badbones69.crazycrates.api.commands.example.comp.ExampleComponent;
import com.badbones69.crazycrates.api.commands.reqs.CommandRequirementsBuilder;
import com.badbones69.crazycrates.api.commands.sender.args.Argument;
import com.badbones69.crazycrates.api.commands.sender.args.builder.IntArgument;

public class TestCommand extends CommandEngine {

    public TestCommand() {
        super();

        String alias = "test";

        addAlias(alias);

        this.requiredArgs.add(new Argument("page", 0, new IntArgument(20)));

        addDescription(alias, "A test command that is normally hidden.");

        this.requirements = new CommandRequirementsBuilder()
                .withRawPermission("test.permission")
                .asPlayer(false)
                .build();
    }

    @Override
    protected void perform(CommandContext context) {
        ExampleComponent.test(context.getPlayer());
    }
}