package com.badbones69.crazycrates.api.storage.types.file.sqlite;

import com.badbones69.crazycrates.api.storage.objects.UserData;
import com.ryderbelserion.stick.paper.storage.FileExtension;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public sealed class SqliteStorage extends FileExtension permits SqliteUserManager {

    public SqliteStorage(Path path) {
        super("users.db", path, StorageType.SQLITE);
    }

    protected ConcurrentHashMap<UUID, UserData> userData = new ConcurrentHashMap<>();
}