package com.badbones69.crazycrates.commands.v2;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.HelpEntry;
import co.aikar.commands.annotation.*;
import com.badbones69.crazycrates.CrazyCrates;
import com.badbones69.crazycrates.api.configs.types.PluginConfig;
import com.badbones69.crazycrates.api.utils.ColorUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;

@CommandAlias("crazycrates")
@Description("Manage or use CrazyCrates.")
public class CrateBaseCommand extends BaseCommand {

    private final CrazyCrates plugin = JavaPlugin.getPlugin(CrazyCrates.class);

    @Subcommand("help")
    @Description("The base help command for CrazyCrates")
    @CommandPermission("crazycrates.command.help")
    public void help(CommandSender sender, @Syntax("[page]") CommandHelp help) {
        help.setPerPage(this.plugin.getApiManager().getPluginConfig().getProperty(PluginConfig.MAX_HELP_PAGE_ENTRIES));

        generateHelp(help.getPage(), help.getPerPage(), help.getHelpEntries(), sender);
    }

    private void generateHelp(int page, int maxPage, List<HelpEntry> entries, CommandSender sender) {
        int start = maxPage * (page - 1);

        String invalidPage = this.plugin.getApiManager().getPluginConfig().getProperty(PluginConfig.INVALID_HELP_PAGE)
                .replace("{page}", String.valueOf(page));

        if (page <= 0 || start >= entries.size()) {
            sender.sendMessage(ColorUtils.color(invalidPage));
            return;
        }

        String header = plugin.getApiManager().getPluginConfig().getProperty(PluginConfig.HELP_PAGE_HEADER)
                .replace("{page}", String.valueOf(page));

        sender.sendMessage(ColorUtils.color(header));

        for (int i = start; i < (start + maxPage); i++) {
            if (entries.size()-1 < i) continue;

            HelpEntry command = entries.get(i);

            if (!command.shouldShow()) continue;

            String name = command.getCommandPrefix() + command.getCommand();
            String desc = command.getDescription();

            String format = plugin.getApiManager().getPluginConfig().getProperty(PluginConfig.HELP_PAGE_FORMAT)
                    .replace("{command}", name)
                    .replace("{description}", desc);

            sender.sendMessage(ColorUtils.color(format));
        }

        String footer = plugin.getApiManager().getPluginConfig().getProperty(PluginConfig.HELP_PAGE_FOOTER);

        sender.sendMessage(ColorUtils.color(footer));
    }
}