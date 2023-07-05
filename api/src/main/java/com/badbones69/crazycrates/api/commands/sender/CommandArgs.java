package com.badbones69.crazycrates.api.commands.sender;

public interface CommandArgs {

    int getArgAsInt(int index, boolean notifySender, String invalidArg);

    long getArgAsLong(int index, boolean notifySender, String invalidArg);

    double getArgAsDouble(int index, boolean notifySender, String invalidArg);

    boolean getArgAsBoolean(int index, boolean notifySender, String invalidArg);

    float getArgAsFloat(int index, boolean notifySender, String invalidArg);

}