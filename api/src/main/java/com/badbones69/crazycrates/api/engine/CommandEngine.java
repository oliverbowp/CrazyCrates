package com.badbones69.crazycrates.api.engine;

import java.util.LinkedList;

public abstract class CommandEngine implements CommandExecutable {

    private final LinkedList<String> aliases = new LinkedList<>();
    private final LinkedList<String> requiredArgs = new LinkedList<>();
    private final LinkedList<String> optionalArgs = new LinkedList<>();

    private final LinkedList<CommandEngine> commands = new LinkedList<>();

    public void run(CommandInfo info) {

    }

    public void addAlias(String alias) {
        this.aliases.add(alias);
    }

    public void removeAlias(String alias) {
        this.aliases.remove(alias);
    }

    private boolean requirementCheck(CommandInfo info) {
        return false;
    }

    private boolean inputCheck(CommandInfo info) {
        if (info.getArgs().size() < this.requiredArgs.size()) {
            info.message("Too few args.");
            return false;
        }

        if (info.getArgs().size() > this.requiredArgs.size() + this.optionalArgs.size()) {
            info.message("Too many args.");
            return false;
        }

        return true;
    }
}