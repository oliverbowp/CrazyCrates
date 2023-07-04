package com.badbones69.crazycrates.api.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Collections;
import java.util.List;

public class CommandHelp {

    private CommandSender sender;
    private Player player;

    public CommandHelp() {

    }

    public List<String> getAliases() {
        return Collections.emptyList();
    }

    public CommandHelp setSender(CommandSender sender) {
        this.sender = sender;

        if (sender instanceof Player) {
            this.player = (Player) sender;
        }

        return this;
    }

    public CommandSender getSender() {
        return this.sender;
    }

    public Player getPlayer() {
        return this.player;
    }
}