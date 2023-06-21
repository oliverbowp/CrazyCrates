package com.badbones69.crazycrates.api.engine.arguments;

import org.bukkit.entity.Player;
import java.util.List;

abstract public class ArgumentType {
    public abstract List<String> getValues(Player player);
}