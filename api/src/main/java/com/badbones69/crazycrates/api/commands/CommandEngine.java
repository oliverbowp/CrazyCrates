package com.badbones69.crazycrates.api.commands;

import com.badbones69.crazycrates.api.commands.reqs.CommandRequirements;
import com.badbones69.crazycrates.api.commands.sender.args.Argument;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class CommandEngine implements TabCompleter, CommandExecutor {

    // i.e. /crazycrates test
    private final LinkedList<String> aliases = new LinkedList<>();

    public final LinkedList<Argument> requiredArgs = new LinkedList<>();
    public final LinkedList<Argument> optionalArgs = new LinkedList<>();

    // i.e. the java classes.
    private final LinkedList<CommandEngine> subCommands = new LinkedList<>();

    // i.e. the command requirements for each class or command.
    public CommandRequirements requirements;

    // i.e. the command should be hidden.
    public boolean isCommandVisible;

    // i.e. the base of the command like /crazycrates
    // Only define this once!
    public String prefix;
    public String description;

    public void execute(CommandContext context) {
        String alias = context.getAlias();

        if (!context.getArgs().isEmpty()) {
            for (CommandEngine command : this.subCommands) {
                boolean exists = context.getArgs().stream().findFirst().isPresent();

                if (exists) {
                    String value = context.getArgs().stream().findFirst().get();

                    if (command.aliases.contains(value)) {
                        context.removeArgs(0);
                        command.execute(context);
                        return;
                    }
                }
            }
        }

        if (!this.requirements.checkRequirements(true, context)) return;

        if (!inputValidation(context)) return;

        perform(context);
    }

    public void addAlias(String alias) {
        this.aliases.add(alias);
    }

    public void removeAlias(String alias) {
        this.aliases.remove(alias);
    }

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

    private boolean inputValidation(CommandContext context) {
        if (context.getArgs().size() < this.requiredArgs.size()) {
            context.reply("Too few args.");
            return false;
        }

        if (context.getArgs().size() > this.requiredArgs.size() + this.optionalArgs.size()) {
            context.reply("Too many args.");
            return false;
        }

        return true;
    }

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

            this.subCommands.forEach(value -> completions.add(value.aliases.get(0)));

            return completions;
        }

        if (args.length >= 2) {
            int argToComplete = args.length + 1 - 2;

            if (this.requiredArgs.size() >= argToComplete) {
                ArrayList<Argument> arguments = new ArrayList<>();

                arguments.addAll(this.requiredArgs);
                arguments.addAll(this.optionalArgs);

                ArrayList<String> values = new ArrayList<>();
            }
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