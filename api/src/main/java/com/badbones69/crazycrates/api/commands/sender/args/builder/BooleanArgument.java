package com.badbones69.crazycrates.api.commands.sender.args.builder;

import com.badbones69.crazycrates.api.commands.sender.args.ArgumentType;
import java.util.List;

public class BooleanArgument extends ArgumentType {

    @Override
    public List<String> getPossibleValues() {
        return List.of("true", "false");
    }
}