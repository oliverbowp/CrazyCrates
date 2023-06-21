package com.badbones69.crazycrates.api.holograms.interfaces;

import com.badbones69.crazycrates.objects.CrateHologram;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public interface HologramManager {

    void create(Location location, CrateHologram crateHologram);

    void remove(Location location);

    void purge();

}