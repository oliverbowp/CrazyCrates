package com.badbones69.crazycrates.api.commands;

import com.badbones69.crazycrates.api.commands.sender.CommandActor;
import com.ryderbelserion.stick.paper.utils.AdventureUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import java.util.Collections;
import java.util.List;

public class CommandContext implements CommandActor {

    private final CommandSender sender;
    private final String alias;
    private final List<String> args;

    private Player player;

    public CommandContext(CommandSender sender, String alias, List<String> args) {
        this.sender = sender;

        if (sender instanceof Player) {
            this.player = (Player) sender;
        }

        this.alias = alias;
        this.args = args;
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

    @Override
    public List<String> getArgs() {
        return Collections.unmodifiableList(this.args);
    }
}