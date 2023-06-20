package com.badbones69.crazycrates.api.engine;

import java.util.LinkedList;

public abstract class CommandEngine {

    private final LinkedList<String> aliases = new LinkedList<>();

    private final LinkedList<CommandEngine> commands = new LinkedList<>();

    public void run(CommandInfo info) {
        String activeCommand = info.getCurrentCommand();

        if (!info.getArgs().isEmpty()) {

        }
    }

    public abstract boolean execute(CommandInfo info);

    public abstract String getDescription();

    public abstract String getPermission();

    public void addAlias(String alias) {
        this.aliases.add(alias);
    }

    public void removeAlias(String alias) {
        this.aliases.remove(alias);
    }
}