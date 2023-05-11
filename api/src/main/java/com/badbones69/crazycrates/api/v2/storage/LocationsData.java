package com.badbones69.crazycrates.api.v2.storage;

import com.google.gson.annotations.Expose;
import org.bukkit.Location;
import us.crazycrew.crazycore.paper.CrazyCore;
import us.crazycrew.crazycore.paper.files.FileExtension;
import us.crazycrew.crazycore.paper.files.enums.FileType;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LocationsData extends FileExtension {

    public LocationsData(Path path) {
        super("locations.json", path, FileType.JSON);
    }

    @Expose
    private static ConcurrentHashMap<String, Location> crates = new ConcurrentHashMap<>();

    public static void load(CrazyCore crazyCore, Path path) {
        crazyCore.getFileHandler().addFile(new LocationsData(path).setData(true));
    }

    public static void save(CrazyCore crazyCore, Path path) {
        crazyCore.getFileHandler().saveFile(new LocationsData(path).setData(true));
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