package com.badbones69.crazycrates.api.v2;

import com.badbones69.crazycrates.api.v2.storage.types.JsonStorage;
import com.badbones69.crazycrates.api.v2.storage.DataManager;
import us.crazycrew.crazycore.paper.CrazyCore;
import java.nio.file.Path;

public class ApiManager {

    private CrazyCore crazyCore;
    private final Path path;

    private DataManager dataManager;

    public ApiManager(Path path) {
        this.path = path;
    }

    public ApiManager build() {
        this.crazyCore = new CrazyCore(path, "CrazyCrates");

        // Load thy file.
        JsonStorage.load(this.crazyCore.getFileHandler(), this.path);

        // Create data manager class.
        this.dataManager = new DataManager();

        return this;
    }

    public CrazyCore getCrazyCore() {
        return this.crazyCore;
    }

    public DataManager getDataManager() {
        return this.dataManager;
    }
}