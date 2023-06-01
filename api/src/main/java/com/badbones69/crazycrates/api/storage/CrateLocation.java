package com.badbones69.crazycrates.api.storage;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrateLocation {

    @Expose
    private final ArrayList<CustomLocation> locations = new ArrayList<>();

    public void addLocation(CustomLocation location) {
        locations.add(location);
    }

    public void removeLocation(CustomLocation location) {
        locations.remove(location);
    }

    public List<CustomLocation> getLocation() {
        return Collections.unmodifiableList(locations);
    }
}