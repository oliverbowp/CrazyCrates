package com.badbones69.crazycrates.api.commands.sender;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import java.util.List;

public interface CommandActor {

    void reply(String message);

    boolean hasPermission(Permission permission);

    boolean hasPermission(String rawPermission);

    CommandSender getSender();

    boolean isPlayer();

    Player getPlayer();

    String getAlias();

    List<String> getArgs();

}