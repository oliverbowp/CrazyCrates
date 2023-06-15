package com.badbones69.crazycrates.objects;

import com.badbones69.crazycrates.api.crates.CrateConfig;
import com.badbones69.crazycrates.api.crates.types.CrateType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Crate {

    private final String crateName;
    private final CrateType crateType;

    private final String keyName;
    private final List<String> keyLore;
    private final ItemStack keyItem;
    private final boolean isGlowing;

    private CrateManager crateManager;
    private final CrateHologram crateHologram;

    public Crate(CrateConfig crateConfig) {
        this.crateName = crateConfig.getCrateName();
        this.crateType = CrateType.getFromName(crateConfig.getType());

        this.keyName = crateConfig.getKeyName();
        this.keyLore = crateConfig.getKeyLore();
        this.keyItem = crateConfig.getKeyItem();
        this.isGlowing = crateConfig.isKeyGlowing();

        this.crateHologram = new CrateHologram(crateConfig.isHologramEnabled(), crateConfig.getHologramHeight(), crateConfig.getHologramMessages());
    }

    public String getCrateName() {
        return this.crateName;
    }

    public CrateType getCrateType() {
        return this.crateType;
    }

    public CrateManager getCrateManager() {
        return this.crateManager;
    }

    public CrateHologram getCrateHologram() {
        return this.crateHologram;
    }
}