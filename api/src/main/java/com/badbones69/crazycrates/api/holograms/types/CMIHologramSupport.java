package com.badbones69.crazycrates.api.holograms.types;

/*
import com.Zrips.CMI.CMI;
import com.Zrips.CMI.Modules.Holograms.CMIHologram;
import com.badbones69.crazycrates.api.holograms.interfaces.HologramManager;
import com.badbones69.crazycrates.objects.CrateHologram;
import net.Zrips.CMILib.Container.CMILocation;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.UUID;

public class CMIHologramSupport implements HologramManager {

    private final HashMap<Location, CMIHologram> holograms = new HashMap<>();

    @Override
    public void create(Location location, CrateHologram crateHologram, JavaPlugin plugin) {
        if (!crateHologram.isEnabled()) return;

        double height = crateHologram.getHeight();

        CMILocation cmiLocation = new CMILocation(location.add(0.5, height, 0.5));

        CMIHologram hologram = new CMIHologram("CrazyCrates-" + UUID.randomUUID(), cmiLocation);

        CMI.getInstance().getHologramManager().addHologram(hologram);

        hologram.update();

        holograms.put(location, hologram);
    }

    @Override
    public void remove(Location location, JavaPlugin plugin) {
        if (!holograms.containsKey(location)) return;

        CMIHologram hologram = holograms.get(location);

        hologram.remove();
        holograms.remove(location);
    }

    @Override
    public void purge(JavaPlugin plugin) {
        holograms.forEach((Key, value) -> value.remove());
        holograms.clear();
    }
}
 */