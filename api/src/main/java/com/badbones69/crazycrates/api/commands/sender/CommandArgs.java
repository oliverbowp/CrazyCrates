package com.badbones69.crazycrates.api.commands.sender;

public interface CommandArgs {

    int getArgAsInt(int index, boolean notifySender, String invalidArg, String... placeholder);

    long getArgAsLong(int index, boolean notifySender, String invalidArg, String... placeholder);

    double getArgAsDouble(int index, boolean notifySender, String invalidArg, String... placeholder);

    boolean getArgAsBoolean(int index, boolean notifySender, String invalidArg, String... placeholder);

    float getArgAsFloat(int index, boolean notifySender, String invalidArg, String... placeholder);

}