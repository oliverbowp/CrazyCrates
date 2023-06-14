package com.badbones69.crazycrates.api.crates;

import ch.jalu.configme.SettingsManager;
import com.badbones69.crazycrates.api.configs.types.PluginSettings;
import com.badbones69.crazycrates.api.crates.types.CrateType;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class CrateConfig extends YamlConfiguration {

    private final File file;
    private final JavaPlugin plugin;

    private final boolean verbose;

    private final String path = "Crate.";

    public CrateConfig(File file, JavaPlugin plugin, SettingsManager settingsManager) {
        this.file = file;

        this.plugin = plugin;

        this.verbose = settingsManager.getProperty(PluginSettings.VERBOSE_LOGGING);
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
                set("holograms.display.lines", getString(path + "Hologram.Message"));

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
                        set(newPath + ".display.lore.lines", lore);

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

    public String getCrateName() {
        return getFile().getName().replace(".yml", "");
    }

    /**
     * Save the file.
     */
    public void save() {
        try {
            save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return crate type
     */
    public String getType() {
        String name = getString("crate-settings.type");

        if (name == null) {
            if (this.verbose) {
                List.of(
                        "Warning: crate-settings.type is missing or is null.",
                        "Adding the default value to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("crate-settings.type", "CSGO");

            save();
        }

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
            if (this.verbose) {
                List.of(
                        "Warning: crate-settings.name is missing.",
                        "Adding the default value to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("crate-settings.name", "&cYou should change this in " + this.file.getName());

            save();
        }

        return getString("crate-settings.name", "&cYou should change this in " + this.file.getName());
    }

    /**
     * Get the display name of the key and check if it isn't null.
     *
     * @return key name
     */
    public String getKeyName() {
        String name = getString("crate-settings.key.name");

        if (name == null) {
            if (this.verbose) {
                List.of(
                        "Warning: crate-settings.key.name is missing.",
                        "Adding the default value to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("crate-settings.key.name", "&cYou should change this in " + this.file.getName());

            save();
        }

        return getString("crate-settings.key.name", "&cYou should change this in " + this.file.getName());
    }

    /**
     * Check if the lore is empty and print out a debug message if verbose logging is enabled.
     *
     * @return lore
     */
    public List<String> getKeyLore() {
        List<String> lore = getStringList("crate-settings.key.lore");

        if (lore.isEmpty() || getString("crate-settings.key.lore") == null) {
            if (this.verbose) {
                List.of(
                        "Warning: crate-settings.key.lore is empty or missing.",
                        "Adding the default value or returning an empty list to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            if (getString("crate-settings.key.lore") == null) {
                set("crate-settings.key.lore", List.of(
                        "&cYou should change this in " + file.getName()
                ));

                if (this.verbose) this.plugin.getLogger().warning("Adding the default value to " + this.file.getName());

                save();

                return lore;
            }

            return Collections.emptyList();
        }

        return lore;
    }

    /**
     * @return get the key's material
     */
    public Material getKeyItem() {
        String material = getString("crate-settings.key.display.item");

        if (material == null) {
            if (this.verbose) {
                List.of(
                        "Warning: crate-settings.key.display.item is missing/invalid.",
                        "Adding the default value to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));

                this.plugin.getLogger().warning("Added the default values back.");
            }

            set("crate-settings.key.display.item", "TRIPWIRE_HOOK");

            save();
        }

        return Material.getMaterial(getString("crate-settings.key.display.item", "TRIPWIRE_HOOK"));
    }

    /**
     * @return whether the key glows
     */
    public boolean isKeyGlowing() {
        String value = getString("crate-settings.key.display.glowing");

        if (value == null) {
            if (this.verbose) {
                List.of(
                        "Warning: crate-settings.key.display.glowing is missing/invalid.",
                        "Adding the default value to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("crate-settings.key.display.glowing", false);

            save();
        }

        return getBoolean("crate-settings.key.display.glowing", false);
    }

    /**
     * Check if holograms are enabled.
     *
     * @return true or false
     */
    public boolean isHologramEnabled() {
        String value = getString("holograms.toggle");

        if (value == null) {
            if (this.verbose) {
                List.of(
                        "Warning: holograms.toggle is missing/invalid.",
                        "Adding the default value to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("holograms.toggle", false);

            save();
        }

        return getBoolean("holograms.toggle", false);
    }

    /**
     * How far above the crate should the hologram be?
     *
     * @return the distance
     */
    public double getHologramHeight() {
        String value = getString("holograms.height");

        if (value == null) {
            if (this.verbose) {
                List.of(
                        "Warning: holograms.height is missing/invalid.",
                        "Adding the default value to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("holograms.height", 1.5);

            save();
        }

        return getDouble("holograms.height", 1.5);
    }

    /**
     * @return list of text to display
     */
    public List<String> getHologramMessages() {
        List<String> lines = getStringList("holograms.display.lines");

        String lore = getString("holograms.display.lines");

        if (lines.isEmpty() || getString("holograms.display.lines") == null) {
            if (this.verbose) {
                List.of(
                        "Warning: holograms.display.lines is empty or missing.",
                        "Adding the default value or returning an empty list."
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            if (lore == null) {
                set("holograms.display.lines", List.of(
                        "&cYou should change this in " + this.file.getName()
                ));

                if (this.verbose) this.plugin.getLogger().warning("Added the default values back to " + this.file.getName());

                save();

                return lines;
            }

            return Collections.emptyList();
        }

        return lines;
    }

    public boolean isCratePreviewEnabled() {
        String glass = getString("crate-preview.toggle");

        if (glass == null) {
            if (this.verbose) {
                List.of(
                        "Warning: crate-preview.toggle is missing.",
                        "Added the default values back to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("crate-preview.toggle", false);

            save();
        }

        return getBoolean("crate-preview.toggle", false);
    }

    public String getCratePreviewName() {
        String name = getString("crate-preview.name");

        if (name == null) {
            if (this.verbose) {
                List.of(
                        "Warning: crate-preview.name is missing.",
                        "Added the default values back to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("crate-preview.name", "&cWarning: You should change this in " + this.file.getName());

            save();
        }

        return getString("crate-preview.name");
    }

    public int getCratePreviewRows() {
        String rows = getString("crate-preview.rows");

        if (rows == null) {
           if (this.verbose) {
               List.of(
                       "Warning: crate-preview.rows is missing.",
                       "Added the default values back to " + this.file.getName()
               ).forEach(line -> this.plugin.getLogger().warning(line));
           }

           set("crate-preview.rows", 6);

           save();
        }

        return getInt("crate-preview.rows", 6);
    }

    public boolean isCratePreviewGlassEnabled() {
        String glass = getString("crate-preview.glass.toggle");

        if (glass == null) {
            if (this.verbose) {
                List.of(
                        "Warning: crate-preview.glass.toggle is missing.",
                        "Added the default values back to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("crate-preview.glass.toggle", false);

            save();
        }

        return getBoolean("crate-preview.glass.toggle", false);
    }

    public String getCratePreviewGlassName() {
        String name = getString("crate-preview.glass.name");

        if (name == null) {
            if (this.verbose) {
                List.of(
                        "Warning: crate-preview.glass.name is missing.",
                        "Added the default values back to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("crate-preview.glass.name", " ");

            save();
        }

        return getString("crate-preview.glass.name", " ");
    }

    public String getCratePreviewGlassItem() {
        String name = getString("crate-preview.glass.item");

        if (name == null) {
            if (this.verbose) {
                List.of(
                        "Warning: crate-preview.glass.item is missing.",
                        "Added the default values back to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("crate-preview.glass.item", "GRAY_STAINED_GLASS_PANE");

            save();
        }

        return getString("crate-preview.glass.item", "GRAY_STAINED_GLASS_PANE");
    }

    public boolean isStartingKeysEnabled() {
        String toggle = getString("starting-keys.toggle");

        if (toggle == null) {
            if (this.verbose) {
                List.of(
                        "Warning: starting-keys.toggle is missing.",
                        "Added the default values back to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("starting-keys.toggle", false);

            save();
        }

        return getBoolean("starting-keys.toggle", false);
    }

    public int getStartingKeysAmount() {
        String amount = getString("starting-keys.amount");

        if (amount == null) {
            if (this.verbose) {
                List.of(
                        "Warning: starting-keys.amount is missing.",
                        "Added the default values back to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("starting-keys.amount", 1);

            save();
        }

        return getInt("starting-keys.amount", 1);
    }

    public boolean isMassOpenEnabled() {
        String amount = getString("misc.mass-open.toggle");

        if (amount == null) {
            if (this.verbose) {
                List.of(
                        "Warning: misc.mass-open.toggle is missing.",
                        "Added the default values back to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("misc.mass-open.toggle", false);

            save();
        }

        return getBoolean("misc.mass-open.toggle", false);
    }

    public int getMassOpenMin() {
        String amount = getString("misc.mass-open.min");

        if (amount == null) {
            if (this.verbose) {
                List.of(
                        "Warning: misc.mass-open.min is missing.",
                        "Added the default values back to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("misc.mass-open.min", 3);

            save();
        }

        return getInt("misc.mass-open.min", 3);
    }

    public int getMassOpenMax() {
        String amount = getString("misc.mass-open.max");

        if (amount == null) {
            if (this.verbose) {
                List.of(
                        "Warning: misc.mass-open.max is missing.",
                        "Added the default values back to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("misc.mass-open.max", 10);

            save();
        }

        return getInt("misc.mass-open.max", 10);
    }

    public boolean isOpeningBroadcastEnabled() {
        String toggle = getString("misc.opening-broadcast.toggle");

        if (toggle == null) {
            if (this.verbose) {
                List.of(
                        "Warning: misc.opening-broadcast.toggle is missing.",
                        "Added the default values back to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("misc.opening-broadcast.toggle", false);

            save();
        }

        return getBoolean("misc.opening-broadcast.toggle", false);
    }

    public String getOpeningBroadcastMessage() {
        String message = getString("misc.opening-broadcast.message");

        if (message == null) {
            if (this.verbose) {
                List.of(
                        "Warning: misc.opening-broadcast.message is missing.",
                        "Added the default values back to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("misc.opening-broadcast.message", "&cWarning: You should change this in " + this.file.getName());

            save();
        }

        return getString("misc.opening-broadcast.message", "Change this in " + this.file.getName());
    }

    public boolean isGuiEnabled() {
        String toggle = getString("gui.toggle");

        if (toggle == null) {
            if (this.verbose) {
                List.of(
                        "Warning: gui.toggle is missing.",
                        "Added the default values back to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("gui.toggle", true);

            save();
        }

        return getBoolean("gui.toggle", true);
    }

    public int getGuiSlot() {
        String toggle = getString("gui.slot");

        if (toggle == null) {
            if (this.verbose) {
                List.of(
                        "Warning: gui.slot is missing.",
                        "Added the default values back to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("gui.slot", 21);

            save();
        }

        return getInt("gui.slot", 22);
    }

    public String getGuiDisplayName() {
        String name = getString("gui.display.name");

        if (name == null) {
            if (this.verbose) {
                List.of(
                        "Warning: gui.display.name is missing.",
                        "Added the default values back to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("gui.display.name", "&cWarning: You should change this in " + this.file.getName());

            save();
        }

        return getString("gui.display.name", "Change this in " + this.file.getName());
    }

    public List<String> getGuiDisplayLore() {
        List<String> lines = getStringList("gui.display.lore");

        String lore = getString("gui.display.lore");

        if (lines.isEmpty() || getString("gui.display.lore") == null) {
            if (this.verbose) {
                List.of(
                        "Warning: gui.display.lore is empty or missing.",
                        "Adding the default value or returning an empty list."
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            if (lore == null) {
                set("gui.display.lore", List.of(
                        "&cYou should change this in " + this.file.getName()
                ));

                if (this.verbose) this.plugin.getLogger().warning("Added the default values back to " + this.file.getName());

                save();

                return lines;
            }

            return Collections.emptyList();
        }

        return lines;
    }

    public String getGuiDisplayItem() {
        String name = getString("gui.display.item");

        if (name == null) {
            if (this.verbose) {
                List.of(
                        "Warning: gui.display.item is missing.",
                        "Added the default values back to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("gui.display.item", "CHEST");

            save();
        }

        return getString("gui.display.name", "CHEST");
    }

    public boolean isGuiItemGlowing() {
        String toggle = getString("gui.glowing");

        if (toggle == null) {
            if (this.verbose) {
                List.of(
                        "Warning: gui.glowing is missing.",
                        "Added the default values back to " + this.file.getName()
                ).forEach(line -> this.plugin.getLogger().warning(line));
            }

            set("gui.glowing", true);

            save();
        }

        return getBoolean("gui.glowing", false);
    }

    public List<PrizeConfig> getPrizes() {
        ArrayList<PrizeConfig> prizes = new ArrayList<>();

        ConfigurationSection prizeSection = getConfigurationSection("prizes");

        if (prizeSection == null) return Collections.unmodifiableList(prizes);

        Set<String> keys = prizeSection.getKeys(false);

        for (String reward : keys) {
            ConfigurationSection section = prizeSection.getConfigurationSection(reward);

            if (section == null) continue;

            PrizeConfig prizeConfig = new PrizeConfig(
                    CrateType.getFromName(getType()),
                    getCrateName(),
                    section.getString("display.name", "&cError"),
                    section.getString("display.item", "STONE"),
                    section.getString("display.player", ""),
                    section.getInt("display.amount", 1),
                    section.getStringList("display.enchantments"),
                    section.getBoolean("display.lore.toggle", false),
                    section.getStringList("display.lore.lines"));

            prizes.add(prizeConfig);
        }

        return Collections.unmodifiableList(prizes);
    }
}