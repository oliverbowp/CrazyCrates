package com.badbones69.crazycrates.api.commands;

import com.badbones69.crazycrates.api.ApiManager;
import com.badbones69.crazycrates.api.commands.reqs.CommandRequirements;
import com.badbones69.crazycrates.api.configs.types.PluginConfig;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import static com.badbones69.crazycrates.api.utils.MessageUtils.hover;
import static com.badbones69.crazycrates.api.utils.MessageUtils.send;

public abstract class CommandEngine {

    private final LinkedList<String> aliases = new LinkedList<>();

    private CommandRequirements requirements;

    public void run(CommandInfo info) {
        String alias = info.getAlias();

        if (!requirements.checkRequirements(true, info)) return;

        execute(info);
    }

    public void setRequirements(CommandRequirements requirements) {
        this.requirements = requirements;
    }

    public void addAlias(String alias) {
        this.aliases.add(alias);
    }

    public void removeAlias(String alias) {
        this.aliases.remove(alias);
    }

    protected abstract boolean execute(CommandInfo info);

    @SuppressWarnings("SameParameterValue")
    protected void generateHelp(int page, int maxPage, CommandSender sender, ApiManager apiManager) {
        int start = maxPage * (page - 1);

        String invalidPage = apiManager.getPluginConfig().getProperty(PluginConfig.INVALID_HELP_PAGE)
                .replaceAll("\\{page}", String.valueOf(page));

        if (page <= 0 || start >= aliases.size()) {
            send(sender, invalidPage, apiManager);
            return;
        }

        String hoverFormat = apiManager.getPluginConfig().getProperty(PluginConfig.HELP_PAGE_HOVER_FORMAT);
        String hoverAction = apiManager.getPluginConfig().getProperty(PluginConfig.HELP_PAGE_HOVER_ACTION);

        String header = apiManager.getPluginConfig().getProperty(PluginConfig.HELP_PAGE_HEADER)
                .replaceAll("\\{page}", String.valueOf(page));

        send(sender, header, false, apiManager);

        for (int i = start; i < (start + maxPage); i++) {
            if (aliases.size()-1 < i) continue;

            String command = aliases.get(i);

            //if (!command.shouldShow()) continue;

            //String name = command.getCommandPrefix() + command.getCommand();
            //String desc = command.getDescription();

            String format = apiManager.getPluginConfig().getProperty(PluginConfig.HELP_PAGE_FORMAT)
                    .replaceAll("\\{command}", command);
                    //.replaceAll("\\{description}", desc);

            //String builtCommand = command.getParameters().length > 0 ? format.replaceAll("%args%", command.getParameterSyntax()) : format;

            /*if (sender instanceof Player player) {
                hover(
                        player,
                        builtCommand,
                        hoverFormat.replaceAll("%command%", name).replaceAll("%args%", command.getParameterSyntax()),
                        name,
                        ClickEvent.Action.valueOf(hoverAction.toUpperCase()));
            } else {

            }*/

            send(sender, format, false, apiManager);
        }

        String pageTag = apiManager.getPluginConfig().getProperty(PluginConfig.HELP_PAGE_GO_TO_PAGE);
        String footer = apiManager.getPluginConfig().getProperty(PluginConfig.HELP_PAGE_FOOTER);
        String backButton = apiManager.getPluginConfig().getProperty(PluginConfig.HELP_PAGE_BACK);
        String nextButton = apiManager.getPluginConfig().getProperty(PluginConfig.HELP_PAGE_NEXT);

        if (sender instanceof Player player) {
            if (page > 1) {
                int number = page-1;

                hover(player, footer.replaceAll("%page%", String.valueOf(page)),  pageTag.replaceAll("%page%", String.valueOf(number)), backButton,"/crazycrates help " + number, ClickEvent.Action.RUN_COMMAND);
            } else if (page < aliases.size()) {
                int number = page+1;

                hover(player, footer.replaceAll("%page%", String.valueOf(page)),  pageTag.replaceAll("%page%", String.valueOf(number)), nextButton,"/crazycrates help " + number, ClickEvent.Action.RUN_COMMAND);
            }
        } else {
            send(sender, footer.replaceAll("%page%", String.valueOf(page)), false, apiManager);
        }
    }

    public List<String> getAliases() {
        return Collections.unmodifiableList(this.aliases);
    }
}