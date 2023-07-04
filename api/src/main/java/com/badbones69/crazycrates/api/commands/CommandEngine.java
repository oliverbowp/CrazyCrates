package com.badbones69.crazycrates.api.commands;

import com.badbones69.crazycrates.api.commands.reqs.CommandRequirements;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.*;

public abstract class CommandEngine implements TabCompleter, CommandExecutor {

    private final LinkedList<String> aliases = new LinkedList<>();
    private final LinkedList<CommandEngine> subCommands = new LinkedList<>();

    private CommandRequirements requirements;
    private CommandHelp commandHelp;
    private String prefix;

    public void execute(CommandContext context) {
        String alias = context.getAlias();

        if (!context.getArgs().isEmpty()) {
            for (CommandEngine command : this.subCommands) {
                boolean exists = context.getArgs().stream().findFirst().isPresent();

                if (exists) {
                    String value = context.getArgs().stream().findFirst().get();

                    if (command.aliases.contains(value)) {
                        context.getArgs().remove(0);
                        command.execute(context);
                        return;
                    }
                }
            }
        }

        if (!requirements.checkRequirements(true, context)) return;

        perform(context);
    }

    // Requirements
    public void setRequirements(CommandRequirements requirements) {
        this.requirements = requirements;
    }

    public void setCommandHelp(CommandHelp commandHelp) {
        this.commandHelp = commandHelp;
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
                        new ArrayList<>(Arrays.asList(args))
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
}