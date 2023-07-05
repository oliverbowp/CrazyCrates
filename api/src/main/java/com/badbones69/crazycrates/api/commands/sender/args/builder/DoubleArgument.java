package com.badbones69.crazycrates.api.commands.sender.args.builder;

import com.badbones69.crazycrates.api.commands.sender.args.ArgumentType;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DoubleArgument extends ArgumentType {

    @Override
    public List<String> getPossibleValues() {
        List<String> numbers = new ArrayList<>();

        DecimalFormat decimalFormat = new DecimalFormat("0.0");

        for (double value = 0.1; value <= 100; value += 0.1) {
            String formattedNumber = decimalFormat.format(value);
            numbers.add(formattedNumber);
        }

        return numbers;
    }
}