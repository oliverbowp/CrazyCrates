package com.badbones69.crazycrates.api.engine;

public interface CommandExecutable {

    boolean execute(CommandInfo info);

    String getDescription();

    String getPermission();

}