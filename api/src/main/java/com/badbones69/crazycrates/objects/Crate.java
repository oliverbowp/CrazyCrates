package com.badbones69.crazycrates.objects;

import com.badbones69.crazycrates.api.crates.types.CrateType;

public class Crate {

    private final String crateName;
    private final CrateType crateType;
    private CrateManager crateManager;
    private final CrateHologram crateHologram;

    public Crate(String crateName, CrateType crateType, CrateHologram crateHologram) {
        this.crateName = crateName;
        this.crateType = crateType;

        this.crateHologram = crateHologram != null ? crateHologram : new CrateHologram();
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