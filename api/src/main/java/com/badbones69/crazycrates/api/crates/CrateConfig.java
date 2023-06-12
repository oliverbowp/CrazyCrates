package com.badbones69.crazycrates.api.crates;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CrateConfig extends YamlConfiguration {

    private final File file;

    private final String path = "Crate.";

    public CrateConfig(File file) {
        this.file = file;
    }

    public void load() {
        try {
            load(file);

            if (getString("Crate") != null) {
                set("crate-settings.type", getString(path + "CrateType"));
                set("crate-settings.name", getString(path + "CrateName"));

                set(path + "CrateType", null);
                set(path + "CrateName", null);

                set("crate-settings.key.name", getString(path + "PhysicalKey.Name"));
                set("crate-settings.key.lore", getStringList(path + "PhysicalKey.Lore"));
                set("crate-settings.key.display.item", getString(path + "PhysicalKey.Item"));
                set("crate-settings.key.display.glowing", getBoolean(path + "PhysicalKey.Glowing"));

                set(path + "PhysicalKey.Name", null);
                set(path + "PhysicalKey.Lore", null);
                set(path + "PhysicalKey.Item", null);
                set(path + "PhysicalKey.Glowing", null);

                set("holograms.toggle", getString(path + "Hologram.Toggle"));
                set("holograms.height", getString(path + "Hologram.Height"));
                set("holograms.display.messages", getString(path + "Hologram.Message"));

                set(path + "Hologram.Toggle", null);
                set(path + "Hologram.Height", null);
                set(path + "Hologram.Message", null);

                set("crate-preview.toggle", getBoolean(path + "Preview.Toggle"));
                set("crate-preview.name", getString(path + "Preview-Name"));
                set("crate-preview.rows", getInt(path + "Preview-ChestLines"));

                set(path + "Preview.Toggle", null);
                set(path + "Preview-Name", null);
                set(path + "Preview-ChestLines", null);

                set("crate-preview.glass.toggle", getBoolean(path + "Preview.Glass.Toggle"));
                set("crate-preview.glass.name", getString(path + "Preview.Glass.Name"));
                set("crate-preview.glass.item", getString(path + "Preview.Glass.Item"));

                set(path + "Preview.Glass.Toggle", null);
                set(path + "Preview.Glass.Name", null);
                set(path + "Preview.Glass.Item", null);

                set("starting-keys.toggle", false);
                set("starting-keys.amount", getInt(path + "StartingKeys"));

                set(path + "StartingKeys", null);

                set("misc.mass-open.min", 3);
                set("misc.mass-open.max", getInt(path + "Max-Mass-Open"));

                set(path + "Max-Mass-Open", null);

                set("misc.opening-broadcast.toggle", getBoolean(path + "OpeningBroadCast"));
                set("misc.opening-broadcast.message", getString(path + "BroadCast"));

                set(path + "OpeningBroadCast", null);
                set(path + "BroadCast", null);

                set("gui.toggle", getBoolean(path + "InGUI"));
                set("gui.slot", getInt(path + "Slot"));

                set(path + "InGUI", null);
                set(path + "Slot", null);

                set("gui.display.name", getString(path + "Name"));
                set("gui.display.lore", getStringList(path + "Lore"));

                set("gui.display.item", getStringList(path + "Item"));

                set(path + "Name", null);
                set(path + "Lore", null);
                set(path + "Item", null);

                set("gui.glowing", getBoolean(path + "Glowing"));

                set(path + "Glowing", null);

                ConfigurationSection section = getConfigurationSection("Prizes");

                if (section != null) {
                    section.getKeys(true).forEach(value -> {

                    });
                }

                save(file);
            }
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return this.file;
    }
}