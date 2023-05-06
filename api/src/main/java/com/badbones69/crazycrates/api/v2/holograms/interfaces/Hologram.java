package com.badbones69.crazycrates.api.v2.holograms.interfaces;

import org.bukkit.Location;

public interface Hologram {

    void create(Location location);

    void remove(Location location);

    void get(Location location);

    void purge();

}