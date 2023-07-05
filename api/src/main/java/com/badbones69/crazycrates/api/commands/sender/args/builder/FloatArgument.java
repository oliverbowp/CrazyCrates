package com.badbones69.crazycrates.api.commands.sender.args.builder;

import com.badbones69.crazycrates.api.commands.sender.args.ArgumentType;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FloatArgument extends ArgumentType {

    @Override
    public List<String> getPossibleValues() {
        List<String> numbers = new ArrayList<>();

        DecimalFormat decimalFormat = new DecimalFormat("0.0f");

        for (float value = 0.1f; value <= 100.0f; value += 0.1f) {
            String formattedValue = decimalFormat.format(value);
            numbers.add(formattedValue);
        }

        return numbers;
    }
}