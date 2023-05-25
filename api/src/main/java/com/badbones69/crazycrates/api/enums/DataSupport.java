package com.badbones69.crazycrates.api.enums;

import com.badbones69.crazycrates.api.storage.interfaces.UserManager;
import com.badbones69.crazycrates.api.storage.managers.JsonManager;
import com.badbones69.crazycrates.api.storage.managers.YamlManager;

public enum DataSupport {

    json(JsonManager.class),
    yaml(YamlManager.class);

    private final Class<? extends UserManager> classObject;

    DataSupport(Class<? extends UserManager> classObject) {
        this.classObject = classObject;
    }

    public Class<? extends UserManager> getClassObject() {
        return this.classObject;
    }
}