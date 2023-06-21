package com.badbones69.crazycrates.api.engine;

import com.badbones69.crazycrates.ColorUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandInfo {

    private final CommandSender sender;
    private Player player;
    private final ArrayList<String> args;
    private final String currentCommand;

    public CommandInfo(CommandSender sender, ArrayList<String> args, String currentCommand) {
        this.sender = sender;

        if (sender instanceof Player) this.player = (Player) sender;

        this.args = args;
        this.currentCommand = currentCommand;
    }

    public void message(String value) {
        if (value.isEmpty() || value.isBlank()) return;

        sender.sendMessage(ColorUtils.color(value));
    }

    public boolean hasPermission(Player player, String rawPermission) {
        return player.hasPermission(rawPermission);
    }

    public boolean hasPermission(Player player, Permission permission) {
        return player.hasPermission(permission);
    }

    public boolean isPlayer() {
        return this.player != null;
    }

    public Player getPlayer() {
        return this.player;
    }

    public CommandSender getSender() {
        return this.sender;
    }

    public List<String> getArgs() {
        return Collections.unmodifiableList(this.args);
    }

    public String getCurrentCommand() {
        return this.currentCommand;
    }
}