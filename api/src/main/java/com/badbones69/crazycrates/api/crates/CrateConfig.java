package com.badbones69.crazycrates.api.crates;

import com.badbones69.crazycrates.api.crates.types.CrateType;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

                //set(path + "CrateType", null);
                //set(path + "CrateName", null);

                set("crate-settings.key.name", getString(path + "PhysicalKey.Name"));
                set("crate-settings.key.lore", getStringList(path + "PhysicalKey.Lore"));
                set("crate-settings.key.display.item", getString(path + "PhysicalKey.Item"));
                set("crate-settings.key.display.glowing", getBoolean(path + "PhysicalKey.Glowing"));

                //set(path + "PhysicalKey.Name", null);
                //set(path + "PhysicalKey.Lore", null);
                //set(path + "PhysicalKey.Item", null);
                //set(path + "PhysicalKey.Glowing", null);

                set("holograms.toggle", getString(path + "Hologram.Toggle"));
                set("holograms.height", getString(path + "Hologram.Height"));
                set("holograms.display.messages", getString(path + "Hologram.Message"));

                //set(path + "Hologram.Toggle", null);
                //set(path + "Hologram.Height", null);
                //set(path + "Hologram.Message", null);

                set("crate-preview.toggle", getBoolean(path + "Preview.Toggle"));
                set("crate-preview.name", getString(path + "Preview-Name"));
                set("crate-preview.rows", getInt(path + "Preview-ChestLines"));

                //set(path + "Preview.Toggle", null);
                //set(path + "Preview-Name", null);
                //set(path + "Preview-ChestLines", null);

                set("crate-preview.glass.toggle", getBoolean(path + "Preview.Glass.Toggle"));
                set("crate-preview.glass.name", getString(path + "Preview.Glass.Name"));
                set("crate-preview.glass.item", getString(path + "Preview.Glass.Item"));

                //set(path + "Preview.Glass.Toggle", null);
                //set(path + "Preview.Glass.Name", null);
                //set(path + "Preview.Glass.Item", null);

                set("starting-keys.toggle", false);
                set("starting-keys.amount", getInt(path + "StartingKeys"));

                //set(path + "StartingKeys", null);

                set("misc.mass-open.min", 3);
                set("misc.mass-open.max", getInt(path + "Max-Mass-Open"));

                //set(path + "Max-Mass-Open", null);

                set("misc.opening-broadcast.toggle", getBoolean(path + "OpeningBroadCast"));
                set("misc.opening-broadcast.message", getString(path + "BroadCast"));

                //set(path + "OpeningBroadCast", null);
                //set(path + "BroadCast", null);

                set("gui.toggle", getBoolean(path + "InGUI"));
                set("gui.slot", getInt(path + "Slot"));

                //set(path + "InGUI", null);
                //set(path + "Slot", null);

                set("gui.display.name", getString(path + "Name"));
                set("gui.display.lore", getStringList(path + "Lore"));

                set("gui.display.item", getStringList(path + "Item"));

                //set(path + "Name", null);
                //set(path + "Lore", null);
                //set(path + "Item", null);

                set("gui.glowing", getBoolean(path + "Glowing"));

                //set(path + "Glowing", null);

                ConfigurationSection section = getConfigurationSection("Prizes");

                if (section != null) {
                    section.getKeys(false).forEach(value -> {
                        String prizePath = path + "Prizes." + value;
                        String newPath = "prizes." + value;

                        String id = getString(prizePath + ".DisplayItem", "Stone");
                        String name = getString(prizePath + ".DisplayName", "");
                        List<String> lore = getStringList(prizePath + ".Lore");

                        String player = getString(prizePath + ".Player", "");
                        set(newPath + ".player", player);
                        //set(prizePath + ".Player", null);

                        boolean glowing = getBoolean(prizePath + ".Glowing", false);
                        set(newPath + ".glowing", glowing);
                        //set(prizePath + ".Glowing", null);

                        int amount = getInt(prizePath + ".DisplayAmount", 1);
                        set(newPath + ".display.amount", amount);

                        boolean unbreakable = getBoolean(prizePath + ".Unbreakable", false);
                        set(newPath + ".unbreakable", unbreakable);

                        boolean hideItemFlags = getBoolean(prizePath + ".HideItemsFlags", false);
                        set(newPath + ".hide-item-flags", hideItemFlags);

                        List<String> enchantments = getStringList(prizePath + ".DisplayEnchantments");
                        set(newPath + ".display.enchantments", enchantments);

                        // Misc
                        boolean fireworks = getBoolean(prizePath + ".Firework");
                        set(newPath + ".fireworks.toggle", fireworks);

                        int chance = getInt(prizePath + ".Chance");
                        set(newPath + ".chance", chance);

                        int range = getInt(prizePath + ".MaxRange");
                        set(newPath + ".max-range", range);

                        List<String> blacklisted = getStringList(prizePath + ".BlackListed-Permissions");
                        set(newPath + ".permissions.blacklisted.toggle", false);
                        set(newPath + ".permissions.blacklisted.values", blacklisted);

                        List<String> commands = getStringList(prizePath + ".Commands");
                        set(newPath + ".commands.toggle", false);
                        set(newPath + ".commands.values", commands);

                        List<String> messages = getStringList(prizePath + ".Messages");
                        set(newPath + ".messages.toggle", false);
                        set(newPath + ".messages.values", messages);

                        // Alternative Prizes
                        boolean altPrizeToggle = getBoolean(prizePath + ".Alternative-Prize.Toggle");
                        List<String> altPrizeMessageList = getStringList(prizePath + ".Alternative-Prize.Messages");
                        List<String> altPrizeCommandList = getStringList(prizePath + ".Alternative-Prize.Commands");

                        // If it contains it, set the old values to new.
                        if (contains(prizePath + ".Alternative-Prize")) {
                            set(newPath + ".permissions.alternative-prize.toggle", altPrizeToggle);

                            set(newPath + ".permissions.alternative-prize.messages.values", altPrizeMessageList);
                            set(newPath + ".permissions.alternative-prize.messages.toggle", false);

                            set(newPath + ".permissions.alternative-prize.commands.values", altPrizeCommandList);
                            set(newPath + ".permissions.alternative-prize.commands.toggle", false);
                        }

                        // If it contains it, set the old values to new.
                        if (contains(prizePath + ".Editor-Items")) {
                            List<?> items = getList(prizePath + ".Editor-Items");

                            ArrayList<ItemStack> editorItems = new ArrayList<>();

                            if (items != null) {
                                items.forEach(item -> editorItems.add((ItemStack) item));

                                set(newPath + ".editor-items", editorItems);
                            }
                        }

                        set(newPath + ".display.name", name);
                        set(newPath + ".display.item", id);

                        set(newPath + ".display.lore.toggle", false);
                        set(newPath + ".display.lore.message", lore);

                        if (contains(prizePath + ".Firework")) set(newPath + ".fireworks.toggle", false);

                        // Cosmic Crate
                        if (contains(prizePath + ".Tiers") && Objects.requireNonNull(getString(path + "Type")).equalsIgnoreCase(CrateType.cosmic.getCrateType())) {
                            List<String> tiers = getStringList(prizePath + ".Tiers");

                            set(newPath + ".cosmic-settings.tiers", tiers);
                        }

                        set(newPath + ".cosmic-settings.total-prize-amount", getInt(path + "Crate-Type-Settings.Total-Prize-Amount"));
                        set(newPath + ".cosmic-settings.mystery-crate.item",  getString(path + "Crate-Type-Settings.Mystery-Crate.Item"));
                        set(newPath + ".cosmic-settings.mystery-crate.name",  getString(path + "Crate-Type-Settings.Mystery-Crate.Name"));
                        set(newPath + ".cosmic-settings.mystery-crate.lore", getStringList(path + "Crate-Type-Settings.Mystery-Crate.Lore"));

                        set(newPath + ".cosmic-settings.picked-crate.item",  getString(path + "Crate-Type-Settings.Picked-Crate.Item"));
                        set(newPath + ".cosmic-settings.picked-crate.name",  getString(path + "Crate-Type-Settings.Picked-Crate.Name"));
                        set(newPath + ".cosmic-settings.picked-crate.lore", getStringList(path + "Crate-Type-Settings.Picked-Crate.Lore"));

                        set(path + "Crate-Type-Settings", null);
                    });
                }

                set("Crate", null);

                save(file);
            }
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the file
     */
    public File getFile() {
        return this.file;
    }

    /**
     * @return crate type
     */
    public String getType() {
        return getString("crate-settings.type", "CSGO");
    }

    /**
     * Get the display name of the crate and check if it isn't null.
     *
     * @return display name
     */
    public String getDisplayName() {
        String name = getString("crate-settings.name");

        if (name == null) {
            // TODO() add logging.
        }

        return getString("crate-settings.name", "Error: crate-settings.name is not defined!");
    }

    /**
     * Get the display name of the key and check if it isn't null.
     *
     * @return key name
     */
    public String getKeyName() {
        String name = getString("crate-settings.key.name");

        if (name == null) {
            // TODO() add logging.
        }

        return getString("crate-settings.key.name", "Error: crate-settings.key.name is not defined!");
    }

    /**
     * Check if the lore is empty and print out a debug message if verbose logging is enabled.
     *
     * @return lore
     */
    public List<String> getKeyLore() {
        List<String> lore = getStringList("crate-settings.key.lore");

        if (lore.isEmpty()) {
            // TODO() add debug message
            return Collections.emptyList();
        }

        return lore;
    }

    /**
     * @return get the key's material
     */
    public Material getKeyItem() {
        return Material.matchMaterial(getString("crate-settings.key.display.item", "TRIPWIRE_HOOK"));
    }

    /**
     * @return whether the key glows
     */
    public boolean isKeyGlowing() {
        return getBoolean("crate-settings.key.display.glowing", false);
    }
}