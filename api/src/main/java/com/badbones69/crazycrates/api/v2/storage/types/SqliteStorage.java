package com.badbones69.crazycrates.api.v2.storage.types;

import com.badbones69.crazycrates.api.v2.storage.objects.UserData;
import com.ryderbelserion.stick.paper.storage.FileExtension;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SqliteStorage extends FileExtension {

    public SqliteStorage(Path path) {
        super("users.db", path, StorageType.SQLITE);
    }

    protected ConcurrentHashMap<UUID, UserData> userData = new ConcurrentHashMap<>();
}