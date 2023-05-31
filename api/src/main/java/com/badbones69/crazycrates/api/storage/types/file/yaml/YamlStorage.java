package com.badbones69.crazycrates.api.storage.types.file.yaml;

import com.badbones69.crazycrates.api.storage.objects.UserData;
import com.ryderbelserion.stick.paper.storage.FileExtension;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public sealed class YamlStorage extends FileExtension permits YamlUserManager {

    public YamlStorage(Path path) {
        super("users.yml", path, StorageType.YAML);
    }

    protected ConcurrentHashMap<UUID, UserData> userData = new ConcurrentHashMap<>();
}