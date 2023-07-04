package com.badbones69.crazycrates.api.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.List;

public class CommandHelp {

    private CommandSender sender;
    private Player player;

    private int page;
    private int maxPage;

    private List<String> aliases;

    public CommandHelp(CommandEngine engine) {
        this.aliases = engine.getAliases();
    }

    public List<String> getAliases() {
        return this.aliases;
    }

    // Current Page
    public CommandHelp setPage(int page) {
        this.page = page;

        return this;
    }

    public int getPage() {
        return this.page;
    }

    // Max Pages
    public CommandHelp setMaxPage(int maxPage) {
        this.maxPage = maxPage;

        return this;
    }

    public int getMaxPage() {
        return this.maxPage;
    }

    // Senders.
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