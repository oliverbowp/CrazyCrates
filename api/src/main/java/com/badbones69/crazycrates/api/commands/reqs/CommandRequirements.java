package com.badbones69.crazycrates.api.commands.reqs;

import com.badbones69.crazycrates.api.commands.CommandInfo;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.permissions.Permission;

public class CommandRequirements {

    private final boolean asPlayer;
    private Permission permission;
    private String rawPermission;

    public CommandRequirements(boolean asPlayer, Permission permission, String rawPermission) {
        this.asPlayer = asPlayer;

        if (permission != null) this.permission = permission;

        if (!rawPermission.isEmpty() || !rawPermission.isBlank()) this.rawPermission = rawPermission;
    }

    public boolean checkRequirements(boolean notifySender, CommandInfo info) {
        // TODO() Check if command sender is player.
        if (asPlayer && !info.isPlayer()) {
            if (notifySender) info.reply("<red>You are not a player.</red>");

            return false;
        }

        if (info.getSender() instanceof ConsoleCommandSender) return true;

        if (this.permission != null && !info.hasPermission(this.permission) || this.rawPermission != null && !info.hasPermission(this.rawPermission)) {
            if (notifySender) info.reply("<red>Player has no permission.</red>");

            return false;
        }

        // The command is valid.
        return true;
    }
}