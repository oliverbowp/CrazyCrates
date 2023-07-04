package com.badbones69.crazycrates.api.commands.sender.args.builder;

import com.badbones69.crazycrates.api.commands.sender.args.ArgumentType;

import java.util.ArrayList;
import java.util.List;

public class IntArgument extends ArgumentType {

    @Override
    public List<String> getPossibleValues() {
        List<String> numbers = new ArrayList<>();

        for (int i = 1; i <= 100; i++) numbers.add(String.valueOf(i));

        return numbers;
    }
}