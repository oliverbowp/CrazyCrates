package com.badbones69.crazycrates.api.v2.storage.managers.locations;

import com.badbones69.crazycrates.api.v2.ApiManager;
import com.badbones69.crazycrates.api.v2.storage.interfaces.LocationManager;
import com.badbones69.crazycrates.api.v2.storage.types.locations.JsonCrateData;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import org.bukkit.Location;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;

public class JsonCrateHandler extends JsonCrateData implements LocationManager {

    private final Path path;

    public JsonCrateHandler(Path path) {
        super(path);

        this.path = path;
    }

    @Override
    public void load() {
        ApiManager.getStickCore().getFileHandler().addFile(new JsonCrateData(this.path));
    }

    @Override
    public void save() {
        ApiManager.getStickCore().getFileHandler().saveFile(new JsonCrateData(this.path));
    }

    @Override
    public void convert(StorageType storageType) {
        // TODO() Switch between data types
    }

    @Override
    public void convertLegacy(StorageType storageType) {
        // TODO() Convert legacy data types
    }

    @Override
    public void addLocation(String crateName, Location location) {
        if (!hasLocation(crateName)) crates.put(crateName, location);
    }

    @Override
    public boolean hasLocation(String crateName) {
        return getCrates().containsKey(crateName);
    }

    @Override
    public Location getLocation(String crateName) {
        if (hasLocation(crateName)) return getCrates().get(crateName);

        return null;
    }

    @Override
    public void removeLocation(String crateName) {
        if (hasLocation(crateName)) crates.remove(crateName);
    }

    @Override
    public Map<String, Location> getCrates() {
        return Collections.unmodifiableMap(crates);
    }
}