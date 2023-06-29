package com.badbones69.crazycrates.commands.v2;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.HelpEntry;
import co.aikar.commands.annotation.*;
import com.badbones69.crazycrates.CrazyCrates;
import com.badbones69.crazycrates.api.configs.types.PluginConfig;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;
import static com.badbones69.crazycrates.api.utils.MessageUtils.*;

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
                .replaceAll("\\{page}", String.valueOf(page));

        if (page <= 0 || start >= entries.size()) {
            send(sender, invalidPage, this.plugin.getApiManager());
            return;
        }

        String hoverFormat = plugin.getApiManager().getPluginConfig().getProperty(PluginConfig.HELP_PAGE_HOVER_FORMAT);
        String hoverAction = plugin.getApiManager().getPluginConfig().getProperty(PluginConfig.HELP_PAGE_HOVER_ACTION);

        String header = plugin.getApiManager().getPluginConfig().getProperty(PluginConfig.HELP_PAGE_HEADER)
                .replaceAll("\\{page}", String.valueOf(page));

        send(sender, header, false, this.plugin.getApiManager());

        for (int i = start; i < (start + maxPage); i++) {
            if (entries.size()-1 < i) continue;

            HelpEntry command = entries.get(i);

            if (!command.shouldShow()) continue;

            String name = command.getCommandPrefix() + command.getCommand();
            String desc = command.getDescription();

            String format = plugin.getApiManager().getPluginConfig().getProperty(PluginConfig.HELP_PAGE_FORMAT)
                    .replaceAll("\\{command}", name)
                    .replaceAll("\\{description}", desc);

            String builtCommand = command.getParameters().length > 0 ? format.replaceAll("%args%", command.getParameterSyntax()) : format;

            if (sender instanceof Player player) {
                hover(
                        player,
                        builtCommand,
                        hoverFormat.replaceAll("%command%", name).replaceAll("%args%", command.getParameterSyntax()),
                        name,
                        ClickEvent.Action.valueOf(hoverAction.toUpperCase()));
            } else {
                send(sender, format, false, this.plugin.getApiManager());
            }
        }

        String pageTag = plugin.getApiManager().getPluginConfig().getProperty(PluginConfig.HELP_PAGE_GO_TO_PAGE);
        String footer = plugin.getApiManager().getPluginConfig().getProperty(PluginConfig.HELP_PAGE_FOOTER);
        String backButton = plugin.getApiManager().getPluginConfig().getProperty(PluginConfig.HELP_PAGE_BACK);
        String nextButton = plugin.getApiManager().getPluginConfig().getProperty(PluginConfig.HELP_PAGE_NEXT);

        if (sender instanceof Player player) {
            if (page > 1) {
                int number = page-1;

                hover(player, footer.replaceAll("%page%", String.valueOf(page)),  pageTag.replaceAll("%page%", String.valueOf(number)), backButton,"/crazycrates help " + number, ClickEvent.Action.RUN_COMMAND);
            } else if (page < entries.size()) {
                int number = page+1;

                hover(player, footer.replaceAll("%page%", String.valueOf(page)),  pageTag.replaceAll("%page%", String.valueOf(number)), nextButton,"/crazycrates help " + number, ClickEvent.Action.RUN_COMMAND);
            }
        } else {
            send(sender, footer.replaceAll("%page%", String.valueOf(page)), false, this.plugin.getApiManager());
        }
    }
}