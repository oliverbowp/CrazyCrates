package com.badbones69.crazycrates.api.engine.examples;

import com.badbones69.crazycrates.api.engine.CommandEngine;
import com.badbones69.crazycrates.api.engine.CommandInfo;

public class ExampleCommand extends CommandEngine {

    public ExampleCommand() {
        // Add an example alias.
        addAlias("example");
    }

    @Override
    public boolean execute(CommandInfo info) {
        return false;
    }

    @Override
    public String getDescription() {
        return "An example command.";
    }

    @Override
    public String getPermission() {
        return "fancy.permission";
    }
}