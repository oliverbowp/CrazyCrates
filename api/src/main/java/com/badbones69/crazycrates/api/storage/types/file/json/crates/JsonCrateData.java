package com.badbones69.crazycrates.api.storage.types.file.json.crates;

import com.badbones69.crazycrates.api.storage.CrateData;
import com.google.gson.annotations.Expose;
import com.ryderbelserion.stick.core.storage.FileExtension;
import com.ryderbelserion.stick.core.storage.enums.StorageType;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

public sealed class JsonCrateData extends FileExtension permits JsonCrateManager {

    public JsonCrateData(Path path) {
        super("locations.json", path, StorageType.JSON);
    }

    @Expose
    protected static ConcurrentHashMap<String, CrateData> crates = new ConcurrentHashMap<>();
}