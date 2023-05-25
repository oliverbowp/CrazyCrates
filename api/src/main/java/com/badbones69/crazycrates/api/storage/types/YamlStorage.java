package com.badbones69.crazycrates.api.storage.types;

import com.badbones69.crazycrates.api.storage.objects.UserData;
import com.ryderbelserion.stick.paper.storage.FileExtension;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class YamlStorage extends FileExtension {

    public YamlStorage(Path path) {
        super("users.yml", path, StorageType.YAML);
    }

    protected ConcurrentHashMap<UUID, UserData> userData = new ConcurrentHashMap<>();
}