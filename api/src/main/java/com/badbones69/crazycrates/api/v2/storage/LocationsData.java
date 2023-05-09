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

    public void load(CrazyCore crazyCore, Path path) {
        crazyCore.getFileHandler().addFile(new LocationsData(path).setData(true));
    }

    public void save(CrazyCore crazyCore, Path path) {
        crazyCore.getFileHandler().saveFile(new LocationsData(path).setData(true));
    }

    public void addLocation(String crateName, Location location) {
        //crates.putIfAbsent(crateName, location);
        crates.put(crateName, location);

        System.out.println(crates.size());
    }

    public void removeLocation(String crateName) {
        if (hasLocation(crateName)) crates.remove(crateName);
    }

    public boolean hasLocation(String crateName) {
        return getCrates().containsKey(crateName);
    }

    public Location getLocation(String crateName) {
        return getCrates().get(crateName);
    }

    public String getCrateName(String crateName) {
        return crateName;
    }

    public Map<String, Location> getCrates() {
        return Collections.unmodifiableMap(crates);
    }
}