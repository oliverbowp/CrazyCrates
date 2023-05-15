package com.badbones69.crazycrates.api.v2.holograms.types;

import com.badbones69.crazycrates.api.v2.holograms.interfaces.Holograms;
import com.badbones69.crazycrates.objects.CrateHologram;
import de.oliver.fancyholograms.Hologram;
import de.oliver.fancyholograms.utils.HologramSpigotAdapter;
import net.minecraft.world.entity.Display;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import java.util.HashMap;
import java.util.UUID;

public class FancyHologramsSupport implements Holograms {

    private final HashMap<Location, Hologram> holograms = new HashMap<>();

    @Override
    public void create(Location location, CrateHologram crateHologram) {
        if (!crateHologram.isEnabled()) return;

        double height = crateHologram.getHeight();

        Hologram hologram = new Hologram(UUID.randomUUID().toString(), location.add(0.5, height, 0.5), crateHologram.getMessages(), Display.BillboardConstraints.CENTER, 1f, null, 0, 1, -1, null);

        hologram.create();

        HologramSpigotAdapter spigotAdapter = HologramSpigotAdapter.fromHologram(hologram);

        Bukkit.getOnlinePlayers().forEach(spigotAdapter::spawn);

        holograms.put(location, hologram);
    }

    @Override
    public void remove(Location location) {
        if (!holograms.containsKey(location)) return;

        Hologram hologram = holograms.get(location);

        HologramSpigotAdapter spigotAdapter = HologramSpigotAdapter.fromHologram(hologram);

        Bukkit.getOnlinePlayers().forEach(spigotAdapter::remove);

        hologram.delete();

        holograms.remove(location);
    }

    @Override
    public void purge() {
        holograms.forEach((key, value) -> {
            HologramSpigotAdapter spigotAdapter = HologramSpigotAdapter.fromHologram(value);

            Bukkit.getOnlinePlayers().forEach(spigotAdapter::remove);

            value.delete();
        });

        holograms.clear();
    }
}