package com.badbones69.crazycrates.api.enums;

import com.badbones69.crazycrates.api.holograms.interfaces.HologramManager;
import com.badbones69.crazycrates.api.holograms.types.DecentHologramSupport;
import com.badbones69.crazycrates.api.holograms.types.FancyHologramSupport;
import com.badbones69.crazycrates.api.holograms.types.InternalHologramSupport;

public enum HologramSupport {

    decent_holograms(DecentHologramSupport.class),
    //cmi_holograms(CMIHologramSupport.class),
    fancy_holograms(FancyHologramSupport.class),
    internal_holograms(InternalHologramSupport.class);

    private final Class<? extends HologramManager> classObject;

    HologramSupport(Class<? extends HologramManager> classObject) {
        this.classObject = classObject;
    }

    public Class<? extends HologramManager> getClassObject() {
        return this.classObject;
    }
}