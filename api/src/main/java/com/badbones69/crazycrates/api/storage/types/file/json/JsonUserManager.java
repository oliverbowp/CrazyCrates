package com.badbones69.crazycrates.api.storage.types.file.json;

import com.badbones69.crazycrates.api.crates.CrateManager;
import com.badbones69.crazycrates.api.storage.interfaces.UserManager;
import com.badbones69.crazycrates.api.storage.objects.UserData;
import com.badbones69.crazycrates.objects.Crate;
import com.google.gson.GsonBuilder;
import com.ryderbelserion.stick.paper.Stick;
import com.ryderbelserion.stick.paper.storage.enums.StorageType;
import com.ryderbelserion.stick.paper.storage.types.file.json.adapters.LocationTypeAdapter;
import org.bukkit.Location;
import java.io.File;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public non-sealed class JsonUserManager extends JsonStorage implements UserManager {

    private final Path path;
    private final CrateManager crate;

    public JsonUserManager(Path path, CrateManager crate) {
        super(path);

        // Assign the path.
        this.path = path;

        this.crate = crate;
    }

    GsonBuilder builder = new GsonBuilder().disableHtmlEscaping()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .excludeFieldsWithoutExposeAnnotation()
            .registerTypeAdapter(Location.class, new LocationTypeAdapter());

    @Override
    public void load(Stick stick) {
        JsonStorage jsonStorage = new JsonStorage(this.path);

        jsonStorage.setGsonBuilder(builder);

        stick.getFileHandler().addFile(jsonStorage);
    }

    @Override
    public void save(Stick stick) {
        stick.getFileHandler().saveFile(new JsonStorage(this.path));
    }

    @Override
    public void convert(File file, UUID uuid, StorageType storageType, Crate crate) {

    }

    @Override
    public void convertLegacy(File file, UUID uuid, StorageType storageType, Crate crate) {

    }

    @Override
    public void addUser(UUID uuid, Crate crate) {
        convert(new File(this.path.toFile(), "data.yml"), uuid, StorageType.JSON, crate);

        if (!userData.containsKey(uuid)) userData.put(uuid, new UserData(uuid));
    }

    @Override
    public UserData getUser(UUID uuid, Crate crate) {
        addUser(uuid, crate);

        return userData.get(uuid);
    }

    @Override
    public void addKey(UUID uuid, int amount, Crate crate) {
        if (userData.containsKey(uuid)) userData.get(uuid).addKey(crate, amount); else addUser(uuid, crate);
    }

    @Override
    public void removeKey(UUID uuid, int amount, Crate crate) {
        if (userData.containsKey(uuid)) userData.get(uuid).removeKey(crate, amount); else addUser(uuid, crate);
    }

    @Override
    public Map<String, Integer> getKeys(UUID uuid, Crate crate) {
        return getUser(uuid, crate).getKeys();
    }

    @Override
    public Map<UUID, UserData> getUsers(UUID uuid) {
        return Collections.unmodifiableMap(userData);
    }
}