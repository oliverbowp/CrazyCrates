package com.badbones69.crazycrates.api.storage;

import com.badbones69.crazycrates.api.crates.CrateConfig;
import com.badbones69.crazycrates.api.storage.objects.CrateLogData;
import com.badbones69.crazycrates.enums.KeyType;
import com.google.gson.annotations.Expose;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrateData {

    @Expose
    private final ArrayList<CustomLocation> locations = new ArrayList<>();

    @Expose
    private final ArrayList<CrateLogData> logs = new ArrayList<>();

    public void logCrateEvent(Player player, CrateConfig crateConfig, KeyType keyType) {
        String name = player.getName();
        String crate = crateConfig.getCrateName();
        String type = crateConfig.getType();
        String keyName = crateConfig.getKeyName();
        String keyItem = crateConfig.getKeyItem().getType().name();

        CrateLogData crateLogData = new CrateLogData(name, null, crate, type, keyName, keyItem, keyType.getName());

        this.logs.add(crateLogData);
    }

    public void logKeyEvent(Player player, CommandSender sender, CrateConfig crateConfig, KeyType keyType) {
        String name = player.getName();
        String senderName = sender.getName();
        String keyName = crateConfig.getKeyName();

        CrateLogData crateLogData = new CrateLogData(name, senderName, null, null, keyName, null, keyType.getName());

        this.logs.add(crateLogData);
    }

    public void addLocation(CustomLocation location) {
        this.locations.add(location);
    }

    public void removeLocation(CustomLocation location) {
        this.locations.remove(location);
    }

    public List<CrateLogData> getLogs() {
        return Collections.unmodifiableList(this.logs);
    }

    public List<CustomLocation> getLocation() {
        return Collections.unmodifiableList(this.locations);
    }
}