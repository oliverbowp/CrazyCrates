package com.badbones69.crazycrates.api.storage.types.locations;

import com.google.gson.annotations.Expose;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import java.util.HashMap;
import java.util.UUID;

public class CrateLocation {

    private int id;

    @Expose
    private final HashMap<Integer, CustomLocation> locations = new HashMap<>();

    private final Location location;

    private final String crateName;

    public CrateLocation(String crateName, Location location) {
        this.location = location;

        this.crateName = crateName;
    }

    public void addLocation() {
        CustomLocation customLocation = new CustomLocation(crateName, this.location.getWorld().getUID(), this.location.x(), this.location.y(), this.location.z());

        /*if (locations.containsKey(this.id)) {
            Map.Entry<Integer, CustomLocation> entries = locations.entrySet().iterator().next();

            AtomicInteger newValue = new AtomicInteger(entries.getKey());

            locations.put(newValue.incrementAndGet(), customLocation);

            return;
        }*/

        Bukkit.getLogger().warning(String.valueOf(customLocation.world()));
        Bukkit.getLogger().warning(customLocation.crateName());
        Bukkit.getLogger().warning(String.valueOf(customLocation.x()));
        Bukkit.getLogger().warning(String.valueOf(customLocation.y()));
        Bukkit.getLogger().warning(String.valueOf(customLocation.z()));

        Bukkit.getLogger().warning(String.valueOf(id+1));

        // If it does not have it at all.
        locations.put(id+1, customLocation);
    }
}

record CustomLocation(String crateName, UUID world, double x, double y, double z) {}