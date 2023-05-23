package com.badbones69.crazycrates.api.v2.holograms.interfaces;

import com.badbones69.crazycrates.objects.CrateHologram;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public interface Holograms {

    void create(Location location, CrateHologram crateHologram, JavaPlugin plugin);

    void remove(Location location, JavaPlugin plugin);

    void purge(JavaPlugin plugin);

}