package com.badbones69.crazycrates.api.commands;

import com.badbones69.crazycrates.api.commands.sender.CommandActor;
import com.ryderbelserion.stick.paper.utils.AdventureUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class CommandInfo implements CommandActor {

    private final CommandSender sender;
    private Player player;

    private final String alias;

    public CommandInfo(CommandSender sender, String alias) {
        this.sender = sender;

        if (sender instanceof Player) this.player = (Player) sender;

        this.alias = alias;
    }

    @Override
    public void reply(String message) {
        Component component = AdventureUtils.parse(message, false);

        this.sender.sendMessage(component);
    }

    @Override
    public boolean hasPermission(Permission permission) {
        return this.player.hasPermission(permission);
    }

    @Override
    public boolean hasPermission(String rawPermission) {
        return this.player.hasPermission(rawPermission);
    }

    @Override
    public CommandSender getSender() {
        return this.sender;
    }

    @Override
    public boolean isPlayer() {
        return this.player != null;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public String getAlias() {
        return this.alias;
    }
}