package com.badbones69.crazycrates.api.v2.holograms.interfaces;

import com.badbones69.crazycrates.objects.CrateHologram;
import org.bukkit.Location;

public interface Holograms {

    void create(Location location, CrateHologram crateHologram);

    void remove(Location location);

    void purge();

}