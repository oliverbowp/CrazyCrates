package com.badbones69.crazycrates.api.commands;

import com.badbones69.crazycrates.api.ApiManager;
import com.badbones69.crazycrates.api.commands.reqs.CommandRequirements;
import com.badbones69.crazycrates.api.configs.types.PluginConfig;
import net.kyori.adventure.text.event.ClickEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import static com.badbones69.crazycrates.api.utils.MessageUtils.hover;
import static com.badbones69.crazycrates.api.utils.MessageUtils.send;

public abstract class CommandEngine implements TabCompleter, CommandExecutor {

    private final LinkedList<String> aliases = new LinkedList<>();
    private final LinkedList<CommandEngine> subCommands = new LinkedList<>();

    private CommandRequirements requirements;
    private String prefix;

    public void execute(CommandContext context) {
        String alias = context.getAlias();

        if (!requirements.checkRequirements(true, context)) return;

        perform(context);
    }

    // Requirements
    public void setRequirements(CommandRequirements requirements) {
        this.requirements = requirements;
    }

    // Prefixes
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return this.prefix;
    }

    // Aliases
    public void addAlias(String alias) {
        this.aliases.add(alias);
    }

    public void removeAlias(String alias) {
        this.aliases.remove(alias);
    }

    // Subcommands
    public void addSubCommand(CommandEngine engine) {
        this.subCommands.add(engine);
    }

    public void removeSubCommand(CommandEngine engine) {
        this.subCommands.forEach(command -> {
            if (command.aliases.getFirst().equals(engine.aliases.getFirst())) {
                this.subCommands.remove(engine);
            }
        });
    }

    protected abstract void perform(CommandContext context);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        CommandContext context =
                new CommandContext(
                        sender,
                        label,
                        List.of(args)
                );

        this.execute(context);

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();

            subCommands.forEach(value -> completions.add(value.aliases.get(0)));

            return completions;
        }

        return null;
    }

    public List<String> getAliases() {
        return Collections.unmodifiableList(this.aliases);
    }

    public List<CommandEngine> getSubCommands() {
        return Collections.unmodifiableList(this.subCommands);
    }

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

            //String builtCommand = command.getParameters().length > 0 ? format.replaceAll("\\{args}", command.getParameterSyntax()) : format;

            /*if (sender instanceof Player player) {
                hover(
                        player,
                        builtCommand,
                        hoverFormat.replaceAll("\\{command}", name).replaceAll("\\{args}", command.getParameterSyntax()),
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

                hover(player, footer.replaceAll("\\{page}", String.valueOf(page)),  pageTag.replaceAll("\\{page}", String.valueOf(number)), backButton,"/crazycrates help " + number, ClickEvent.Action.RUN_COMMAND);
            } else if (page < aliases.size()) {
                int number = page+1;

                hover(player, footer.replaceAll("\\{page}", String.valueOf(page)),  pageTag.replaceAll("\\{page}", String.valueOf(number)), nextButton,"/crazycrates help " + number, ClickEvent.Action.RUN_COMMAND);
            }
        } else {
            send(sender, footer.replaceAll("\\{page}", String.valueOf(page)), false, apiManager);
        }
    }
}