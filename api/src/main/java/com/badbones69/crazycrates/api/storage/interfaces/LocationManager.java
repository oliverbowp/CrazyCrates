package com.badbones69.crazycrates.api.storage.interfaces;

import com.badbones69.crazycrates.api.storage.CrateData;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import org.bukkit.Location;
import java.io.File;
import java.util.Map;

public interface LocationManager {

    void load();

    void save();

    void reload();

    void convert(File file, StorageType storageType);

    void convertLegacy(File file, StorageType storageType);

    void addLocation(String crateName, Location location);

    boolean hasLocation(String crateName);

    CrateData getLocation(String crateName);

    void removeLocation(String crateName);

    Map<String, CrateData> getCrates();

}