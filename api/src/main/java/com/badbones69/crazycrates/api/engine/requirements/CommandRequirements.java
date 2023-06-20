package com.badbones69.crazycrates.api.engine.requirements;

import com.badbones69.crazycrates.api.engine.CommandInfo;
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

    public boolean check(CommandInfo info, boolean notifySender) {
        if (this.asPlayer && !info.isPlayer()) {
            if (notifySender) info.message("Not a player.");
            return false;
        }

        // The command is valid.
        if (info.getSender() instanceof ConsoleCommandSender) return true;

        if (this.permission != null && !info.hasPermission(info.getPlayer(), this.permission)
        || this.rawPermission != null && !info.hasPermission(info.getPlayer(), this.rawPermission)) {
            if (notifySender) info.message("Player has no permission.");
            return false;
        }

        // The command is valid.
        return true;
    }
}