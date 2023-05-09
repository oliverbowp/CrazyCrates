package com.badbones69.crazycrates.api.v2.holograms.enums;

import com.badbones69.crazycrates.api.v2.holograms.interfaces.Holograms;
import com.badbones69.crazycrates.api.v2.holograms.types.CMIHologramSupport;
import com.badbones69.crazycrates.api.v2.holograms.types.DecentHologramSupport;

public enum HologramSupport {

    decent_holograms(DecentHologramSupport.class),
    cmi_holograms(CMIHologramSupport.class);
    //fancy_holograms(FancyHologram.class);

    private final Class<? extends Holograms> classObject;

    HologramSupport(Class<? extends Holograms> classObject) {
        this.classObject = classObject;
    }

    public Class<? extends Holograms> getClassObject() {
        return this.classObject;
    }
}