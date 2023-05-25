package com.badbones69.crazycrates.api.v2.enums;

import com.badbones69.crazycrates.api.v2.holograms.interfaces.HologramManager;
import com.badbones69.crazycrates.api.v2.holograms.types.CMIHologramSupport;
import com.badbones69.crazycrates.api.v2.holograms.types.DecentHologramSupport;
import com.badbones69.crazycrates.api.v2.holograms.types.FancyHologramSupport;

public enum HologramSupport {

    decent_holograms(DecentHologramSupport.class),
    cmi_holograms(CMIHologramSupport.class),
    fancy_holograms(FancyHologramSupport.class);

    private final Class<? extends HologramManager> classObject;

    HologramSupport(Class<? extends HologramManager> classObject) {
        this.classObject = classObject;
    }

    public Class<? extends HologramManager> getClassObject() {
        return this.classObject;
    }
}