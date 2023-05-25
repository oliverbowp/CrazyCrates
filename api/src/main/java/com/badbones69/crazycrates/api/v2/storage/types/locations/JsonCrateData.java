package com.badbones69.crazycrates.api.v2.storage.types.locations;

import com.google.gson.annotations.Expose;
import com.ryderbelserion.stick.paper.storage.FileExtension;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import org.bukkit.Location;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

public class JsonCrateData extends FileExtension{

    public JsonCrateData(Path path) {
        super("locations.json", path, StorageType.JSON);
    }

    @Expose
    protected static ConcurrentHashMap<String, Location> crates = new ConcurrentHashMap<>();
}