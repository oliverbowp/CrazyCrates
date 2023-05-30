package com.badbones69.crazycrates.api.storage.types.locations.adapters;

import com.badbones69.crazycrates.api.storage.types.locations.CrateLocation;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import java.io.IOException;
import java.util.UUID;

public class CustomTypeAdapter extends TypeAdapter<CrateLocation> {

    @Override
    public void write(JsonWriter out, Location location) throws IOException {
        out.beginObject();
        out.name("uuid").value(String.valueOf(location.getWorld().getUID()));
        out.name("x").value(location.getX());
        out.name("y").value(location.getY());
        out.name("z").value(location.getZ());
        out.endObject();
    }

    @Override
    public void write(JsonWriter out, CrateLocation value) throws IOException {

    }

    @Override
    public Location read(JsonReader reader) throws IOException {
        reader.beginObject();

        UUID uuid = null;
        double x = 0, y = 0, z = 0;
        float yaw = 0, pitch = 0;

        while (reader.hasNext()) {
            String name = reader.nextName();
            
            switch (name) {
                case "uuid" -> uuid = UUID.fromString(reader.nextString());
                case "x" -> x = reader.nextDouble();
                case "y" -> y = reader.nextDouble();
                case "z" -> z = reader.nextDouble();
                case "yaw" -> yaw = (float) reader.nextDouble();
                case "pitch" -> pitch = (float) reader.nextDouble();
                default -> reader.skipValue();
            }
        }

        reader.endObject();

        assert uuid != null;
        return new Location(Bukkit.getWorld(uuid), x, y, z, yaw, pitch);
    }
}