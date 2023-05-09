package com.badbones69.crazycrates.api.v2.holograms.types;

import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Modules.Holograms.CMIHologram;
import com.badbones69.crazycrates.api.v2.holograms.interfaces.Holograms;
import com.badbones69.crazycrates.objects.CrateHologram;
import net.Zrips.CMILib.Container.CMILocation;
import org.bukkit.Location;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CMIHologramSupport implements Holograms {

    private final HashMap<Location, CMIHologram> holograms = new HashMap<>();

    @Override
    public void create(Location location, CrateHologram crateHologram) {
        if (!crateHologram.isEnabled()) return;

        double height = crateHologram.getHeight();

        CMILocation cmiLocation = new CMILocation(location.add(0.5, height, 0.5));

        AtomicInteger size = new AtomicInteger(CMI.getInstance().getHologramManager().getHolograms().size());

        CMIHologram hologram = new CMIHologram("CrazyCrates-" + size.incrementAndGet(), cmiLocation);

        CMI.getInstance().getHologramManager().addHologram(hologram);

        hologram.update();

        holograms.put(location, hologram);
    }

    @Override
    public void remove(Location location) {
        if (!holograms.containsKey(location)) return;

        CMIHologram hologram = holograms.get(location);

        hologram.remove();
        holograms.remove(location);
    }

    @Override
    public void purge() {
        holograms.forEach((Key, value) -> value.remove());
        holograms.clear();
    }
}