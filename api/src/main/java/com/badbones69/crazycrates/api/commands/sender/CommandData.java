package com.badbones69.crazycrates.api.commands.sender;

public class CommandData {

    private String description;

    public CommandData(String description) {
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}