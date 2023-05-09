package com.badbones69.crazycrates.api.v2.holograms.enums;

import com.badbones69.crazycrates.api.v2.storage.interfaces.UserManager;
import com.badbones69.crazycrates.api.v2.storage.managers.JsonManager;

public enum DataSupport {

    json(JsonManager.class);

    private final Class<? extends UserManager> classObject;

    DataSupport(Class<? extends UserManager> classObject) {
        this.classObject = classObject;
    }

    public Class<? extends UserManager> getClassObject() {
        return this.classObject;
    }
}