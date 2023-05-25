package com.badbones69.crazycrates.api.v2.holograms.types;

import com.badbones69.crazycrates.api.v2.holograms.interfaces.HologramManager;
import com.badbones69.crazycrates.objects.CrateHologram;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.UUID;

public class DecentHologramSupport implements HologramManager {

    private final HashMap<Location, Hologram> holograms = new HashMap<>();

    @Override
    public void create(Location location, CrateHologram crateHologram, JavaPlugin plugin) {
        if (!crateHologram.isEnabled()) return;

        double height = crateHologram.getHeight();

        Hologram hologram = DHAPI.createHologram("CrazyCrates-" + UUID.randomUUID(), location.add(.5, height, .5));

        crateHologram.getMessages().forEach(line -> DHAPI.addHologramLine(hologram, line));

        holograms.put(location, hologram);
    }

    @Override
    public void remove(Location location, JavaPlugin plugin) {
        if (!holograms.containsKey(location)) return;

        Hologram hologram = holograms.get(location);

        hologram.delete();
        holograms.remove(location);
    }

    @Override
    public void purge(JavaPlugin plugin) {
        holograms.forEach((key, value) -> value.delete());
        holograms.clear();
    }
}