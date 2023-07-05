package com.badbones69.crazycrates.api.commands.example;

import ch.jalu.configme.SettingsManager;
import com.badbones69.crazycrates.api.commands.CommandEngine;
import com.badbones69.crazycrates.api.commands.CommandContext;
import com.badbones69.crazycrates.api.commands.reqs.CommandRequirementsBuilder;
import com.badbones69.crazycrates.api.configs.types.PluginConfig;

public class BaseCommand extends CommandEngine {

    private final SettingsManager config;

    public BaseCommand(SettingsManager config) {
        super();

        this.config = config;

        this.prefix = "crazycrates";

        this.description = "The base command";

        this.ignoreInput = true;

        this.requirements = new CommandRequirementsBuilder()
                .withRawPermission("example.test")
                .asPlayer(true)
                .build();

        this.optionalArgs.add(new Argument("page", 0, new IntArgument(10)));

        this.optionalMsg = "<green>This argument is optional</green>";
        this.requiredMsg = "<red>This argument is not optional</red>";
    }

    @Override
    protected void perform(CommandContext context) {
        context.reply("This is the base command with 0 args.");
        //generateHelp(1, this.config.getProperty(PluginConfig.MAX_HELP_PAGE_ENTRIES), context);
    }

    private void generateHelp(int page, int maxPage, CommandContext context) {
        int startPage = maxPage * (page - 1);

        if (page <= 0 || startPage >= this.getAliases().size()) {
            context.reply("<red>Page is invalid</red>");
            return;
        }

        context.reply(this.config.getProperty(PluginConfig.HELP_PAGE_HEADER).replaceAll("\\{page}", String.valueOf(page)));

        for (int i = startPage; i < (startPage + maxPage); i++) {
            if (getAliases().size()-1<i) continue;
            //if (this.isCommandVisible) continue;

            String command = this.getAliases().get(i);

            String name = this.prefix + command;
            String desc = this.description;

            String format = this.config.getProperty(PluginConfig.HELP_PAGE_FORMAT)
                    .replaceAll("\\{command}", command)
                    .replaceAll("\\{description}", desc);
        }

        /*

        String invalidPage = apiManager.getPluginConfig().getProperty(PluginConfig.INVALID_HELP_PAGE)
                .replaceAll("\\{page}", String.valueOf(page));

        if (page <= 0 || start >= aliases.size()) {
            //send(sender, invalidPage, apiManager);
            return;
        }

        String hoverFormat = apiManager.getPluginConfig().getProperty(PluginConfig.HELP_PAGE_HOVER_FORMAT);
        String hoverAction = apiManager.getPluginConfig().getProperty(PluginConfig.HELP_PAGE_HOVER_ACTION);

        String header = apiManager.getPluginConfig().getProperty(PluginConfig.HELP_PAGE_HEADER)
                .replaceAll("\\{page}", String.valueOf(page));

        //send(sender, header, false, apiManager);

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

            if (sender instanceof Player player) {
                hover(
                        player,
                        builtCommand,
                        hoverFormat.replaceAll("\\{command}", name).replaceAll("\\{args}", command.getParameterSyntax()),
                        name,
                        ClickEvent.Action.valueOf(hoverAction.toUpperCase()));
            } else {

            }

            //send(sender, format, false, apiManager);
        }

        //String pageTag = apiManager.getPluginConfig().getProperty(PluginConfig.HELP_PAGE_GO_TO_PAGE);
        //String footer = apiManager.getPluginConfig().getProperty(PluginConfig.HELP_PAGE_FOOTER);
        //String backButton = apiManager.getPluginConfig().getProperty(PluginConfig.HELP_PAGE_BACK);
        //String nextButton = apiManager.getPluginConfig().getProperty(PluginConfig.HELP_PAGE_NEXT);

        if (sender instanceof Player player) {
            if (page > 1) {
                int number = page-1;

                hover(player, footer.replaceAll("\\{page}", String.valueOf(page)),  pageTag.replaceAll("\\{page}", String.valueOf(number)), backButton,"/crazycrates help " + number, ClickEvent.Action.RUN_COMMAND);
            } else if (page < aliases.size()) {
                int number = page+1;

                hover(player, footer.replaceAll("\\{page}", String.valueOf(page)),  pageTag.replaceAll("\\{page}", String.valueOf(number)), nextButton,"/crazycrates help " + number, ClickEvent.Action.RUN_COMMAND);
            }
        } else {
            //send(sender, footer.replaceAll("\\{page}", String.valueOf(page)), false, apiManager);
        }
        */
    }
}