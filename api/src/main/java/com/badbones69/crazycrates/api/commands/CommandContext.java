package com.badbones69.crazycrates.api.commands;

import com.badbones69.crazycrates.api.commands.sender.CommandActor;
import com.badbones69.crazycrates.api.commands.sender.CommandArgs;
import com.ryderbelserion.stick.paper.utils.AdventureUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandContext implements CommandActor, CommandArgs {

    private final CommandSender sender;
    private String alias;
    private final ArrayList<String> args;

    private Player player;

    public CommandContext(CommandSender sender, String alias, ArrayList<String> args) {
        this.sender = sender;

        if (sender instanceof Player) {
            this.player = (Player) sender;
        }

        this.alias = alias;
        this.args = args;
    }

    public void reply(String message) {
        if (message.isBlank() || message.isEmpty()) return;

        Component component = AdventureUtils.parse(message, false);

        this.sender.sendMessage(component);
    }

    @Override
    public void reply(Component component) {
        this.sender.sendMessage(component);
    }

    /*@Override
    public void hover(String message, String text, String value, ClickEvent.Action action) {
        if (message.isBlank() || message.isEmpty()) return;

        Component component = AdventureUtils.parse(message, false)
                .append(AdventureUtils.parse(text, false)
                .hoverEvent(HoverEvent.showText(AdventureUtils.parse(text, false))))
                .clickEvent(ClickEvent.clickEvent(action, value));

        this.sender.sendMessage(component);
    }

    @Override
    public void hover(String message, String text, String button, String value, ClickEvent.Action action) {
        if (message.isBlank() || message.isEmpty()) return;

        Component component = AdventureUtils.parse(message, false)
                .append(AdventureUtils.parse(button, false)
                .hoverEvent(HoverEvent.showText(AdventureUtils.parse(text, false))))
                .clickEvent(ClickEvent.clickEvent(action, value));

        this.sender.sendMessage(component);
    }*/

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
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String getAlias() {
        return this.alias;
    }

    @Override
    public List<String> getArgs() {
        return Collections.unmodifiableList(this.args);
    }

    @Override
    public void removeArgs(int arg) {
        this.args.remove(arg);
    }

    @Override
    public int getArgAsInt(int index, boolean notifySender, String invalidArg) {
        String argument = this.args.get(index);

        Integer value = null;

        try {
            value = Integer.parseInt(argument);
        } catch (NumberFormatException exception) {
            if (notifySender) reply(invalidArg);
        }

        if (value != null) return value;

        return 1;
    }

    @Override
    public long getArgAsLong(int index, boolean notifySender, String invalidArg) {
        String argument = this.args.get(index);

        Long value = null;

        try {
            value = Long.parseLong(argument);
        } catch (NumberFormatException exception) {
            if (notifySender) reply(invalidArg);
        }

        if (value != null) return value;

        return 1L;
    }

    @Override
    public double getArgAsDouble(int index, boolean notifySender, String invalidArg) {
        String argument = this.args.get(index);

        Double value = null;

        try {
            value = Double.parseDouble(argument);
        } catch (NumberFormatException exception) {
            if (notifySender) reply(invalidArg);
        }

        if (value != null) return value;

        return 0.1;
    }

    @Override
    public boolean getArgAsBoolean(int index, boolean notifySender, String invalidArg) {
        String argument = this.args.get(index);

        if (argument == null) {
            if (notifySender) reply(invalidArg);

            return false;
        }

        String lowercase = argument.toLowerCase();

        switch (lowercase) {
            case "true", "on", "1" -> {
                return true;
            }
            case "false", "off", "0" -> {
                return false;
            }
            default -> {
                if (notifySender) reply(invalidArg);
                return false;
            }
        }
    }

    @Override
    public float getArgAsFloat(int index, boolean notifySender, String invalidArg) {
        String argument = this.args.get(index);

        Float value = null;

        try {
            value = Float.parseFloat(argument);
        } catch (NumberFormatException exception) {
            if (notifySender) reply(invalidArg);
        }

        if (value != null) return value;

        return 1F;
    }
}