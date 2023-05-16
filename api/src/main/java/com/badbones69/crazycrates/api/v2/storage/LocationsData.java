package com.badbones69.crazycrates.api.v2.storage;

import com.google.gson.annotations.Expose;
import com.ryderbelserion.stick.paper.Stick;
import com.ryderbelserion.stick.paper.storage.FileExtension;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import org.bukkit.Location;
import java.io.File;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocationsData extends FileExtension {

    public LocationsData(Path path) {
        super("locations.json", path, StorageType.JSON);
    }

    @Expose
    private static ConcurrentHashMap<String, Location> crates = new ConcurrentHashMap<>();

    public static void load(Stick stick, Path path) {
        stick.getFileHandler().addFile(new LocationsData(path));
    }

    public static void save(Stick stick, Path path) {
        stick.getFileHandler().saveFile(new LocationsData(path));
    }

    public static void addLocation(String crateName, Location location) {
        if (!crates.containsKey(crateName)) crates.put(crateName, location);
    }

    public static void removeLocation(String crateName) {
        if (hasLocation(crateName)) crates.remove(crateName);
    }

    public static boolean hasLocation(String crateName) {
        return getCrates().containsKey(crateName);
    }

    public static Location getLocation(String crateName) {
        return getCrates().get(crateName);
    }

    public static String getCrateName(String crateName) {
        return crateName;
    }

    public static Map<String, Location> getCrates() {
        return Collections.unmodifiableMap(crates);
    }
}