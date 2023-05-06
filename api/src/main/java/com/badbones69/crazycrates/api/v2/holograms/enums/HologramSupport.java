package com.badbones69.crazycrates.api.v2.holograms.enums;

import com.badbones69.crazycrates.api.v2.holograms.interfaces.Hologram;
import com.badbones69.crazycrates.api.v2.holograms.types.CMIHolograms;
import com.badbones69.crazycrates.api.v2.holograms.types.DecentHolograms;

public enum HologramSupport {

    decent_holograms(DecentHolograms.class),
    cmi_holograms(CMIHolograms.class);

    private final Class<? extends Hologram> classObject;

    HologramSupport(Class<? extends Hologram> classObject) {
        this.classObject = classObject;
    }

    public Class<? extends Hologram> getClassObject() {
        return this.classObject;
    }
}