package com.badbones69.crazycrates.api.commands.example;

import com.badbones69.crazycrates.api.commands.CommandContext;
import com.badbones69.crazycrates.api.commands.CommandEngine;
import com.badbones69.crazycrates.api.commands.reqs.CommandRequirementsBuilder;
import com.badbones69.crazycrates.api.commands.sender.args.Argument;
import com.badbones69.crazycrates.api.commands.sender.args.builder.BooleanArgument;
import com.badbones69.crazycrates.api.commands.sender.args.builder.DoubleArgument;
import com.badbones69.crazycrates.api.commands.sender.args.builder.FloatArgument;
import com.badbones69.crazycrates.api.commands.sender.args.builder.IntArgument;

public class TestCommand2 extends CommandEngine {

    public TestCommand2() {
        super();

        addAlias("testx2");

        this.requiredArgs.add(new Argument("booleans", 0, new BooleanArgument()));
        this.requiredArgs.add(new Argument("integers", 1, new IntArgument()));
        this.requiredArgs.add(new Argument("doubles", 2, new DoubleArgument()));
        this.optionalArgs.add(new Argument("floats", 3, new FloatArgument()));

        this.description = "A test command x2 that normally is hidden.";

        this.requirements = new CommandRequirementsBuilder()
                .withRawPermission("test.permission")
                .asPlayer(true)
                .build();
    }

    @Override
    protected void perform(CommandContext context) {
        context.reply("<red>Guten Tag x2!</red>");
    }
}