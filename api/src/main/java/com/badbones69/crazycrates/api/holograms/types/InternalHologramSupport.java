package com.badbones69.crazycrates.api.holograms.types;

import com.badbones69.crazycrates.api.holograms.interfaces.HologramManager;
import com.badbones69.crazycrates.objects.CrateHologram;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

public class InternalHologramSupport implements HologramManager {

    @Override
    public void create(Location location, CrateHologram crateHologram, JavaPlugin plugin) {

    }

    @Override
    public void remove(Location location, JavaPlugin plugin) {

    }

    @Override
    public void purge(JavaPlugin plugin) {

    }
}