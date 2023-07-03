package com.badbones69.crazycrates.api.commands.reqs;

import com.badbones69.crazycrates.api.commands.CommandContext;
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

    public boolean checkRequirements(boolean notifySender, CommandContext context) {
        if (asPlayer && !context.isPlayer()) {
            if (notifySender) context.reply("<red>You are not a player.</red>");

            return false;
        }

        if (context.getSender() instanceof ConsoleCommandSender) return true;

        if (this.permission != null && !context.hasPermission(this.permission) || this.rawPermission != null && !context.hasPermission(this.rawPermission)) {
            if (notifySender) context.reply("<red>Player has no permission.</red>");

            return false;
        }

        // The command is valid.
        return true;
    }
}