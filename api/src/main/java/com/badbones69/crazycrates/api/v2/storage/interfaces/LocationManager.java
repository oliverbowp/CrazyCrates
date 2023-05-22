package com.badbones69.crazycrates.api.v2.storage.interfaces;

import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import org.bukkit.Location;
import java.util.Map;

public interface LocationManager {

    void load();

    void save();

    void convert(StorageType storageType);

    void convertLegacy();

    void addLocation(String crateName, Location location);

    boolean hasLocation(String crateName);

    Location getLocation(String crateName);

    void removeLocation(String crateName);

    Map<String, Location> getCrates();

}