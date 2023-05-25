package com.badbones69.crazycrates.api.v2.storage.types;

import com.badbones69.crazycrates.api.v2.storage.objects.UserData;
import com.google.gson.annotations.Expose;
import com.ryderbelserion.stick.paper.storage.FileExtension;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class JsonStorage extends FileExtension {

    public JsonStorage(Path path) {
        super("users.json", path, StorageType.JSON);
    }

    @Expose
    protected static ConcurrentHashMap<UUID, UserData> userData = new ConcurrentHashMap<>();
}