package com.badbones69.crazycrates.api.configs.types;

import com.badbones69.crazycrates.api.configs.types.legacy.MessageEnum;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Locale extends YamlConfiguration {

    private final File file;
    private final JavaPlugin plugin;

    public Locale(File file, JavaPlugin plugin) {
        this.file = file;

        this.plugin = plugin;
    }

    public void load() {
        try {
            load(this.file);

            ConfigurationSection legacySection = getConfigurationSection("Messages");

            if (legacySection != null) {
                for (MessageEnum value : MessageEnum.values()) {
                    value.setMessage(getConfiguration(), this.plugin);

                    save();
                }

                set("Messages", null);
                save();
            }
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
    }

    public File getFile() {
        return this.file;
    }

    public Configuration getConfiguration() {
        return this;
    }

    public void save() {
        try {
            save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String UNKNOWN_COMMAND() {
        return getString("misc.unknown-command", "<red>The command {command} is not known.</red>");
    }

    public String NO_TELEPORTING() {
        return getString("misc.no-teleporting", "<red>You may not teleport away while opening</red> <gold>{crate}.</gold>");
    }

    public String NO_COMMANDS() {
        return getString("misc.no-commands", "<red>You are not allowed to use commands while opening</red> <gold>{crate}.</gold>");
    }

    public String NO_KEYS() {
        return getString("misc.no-keys", "<red>You need a</red> {key} <red>in your hand to use</red> <gold>{crate}.</gold>");
    }

    public String NO_VIRTUAL_KEYS() {
        return getString("misc.no-virtual-keys", "<red>You need</red> {key} <red>to open</red> <gold>{crate}.</gold>");
    }

    public String FEATURE_DISABLED() {
        return getString("misc.feature-disabled", "<red>This feature is disabled. We have no ETA on when this will function.</red>");
    }

    public String CORRECT_USAGE() {
        return getString("misc.correct-usage", "<red>The correct usage for this command is</red> <gold>{usage}</gold>");
    }

    public String NO_PRIZES_FOUND() {
        return getString("errors.no-prizes.found", "<red>{crate} contains no prizes that you can win.</red>");
    }

    public String NO_SCHEMATICS_FOUND() {
        return getString("errors.no-schematics-found", "<red>No schematic were found, Please re-generate them by deleting the folder or checking for errors!</red>");
    }

    public String PRIZE_ERROR() {
        return getString("errors.prize-error", "<red>An error has occurred in</red> <gold>{crate}</gold> <red>for</red> <gold>#{prize}. Contact your owner!</gold>");
    }

    public String INTERNAL_ERROR() {
        return getString("errors.internal-error", "<red>An internal error has occurred. Please check the console for the full error.</red>");
    }

    public String OPTIONAL_ARGUMENT() {
        if (get("player.requirements.optional-argument") == null) {
            set("player.requirements.optional-argument", "<green>This argument is optional</green>");

            save();
            load();
        }

        return getString("player.requirements.optional-argument", "<green>This argument is optional</green>");
    }

    public String REQUIRED_ARGUMENT() {
        if (get("player.requirements.required-argument") == null) {
            set("player.requirements.required-argument", "<red>This argument is not optional</red>");

            save();
            load();
        }

        return getString("player.requirements.required-argument", "<red>This argument is not optional</red>");
    }

    public String TOO_MANY_ARGS() {
        return getString("player.requirements.too-many-args", "<red>You put more arguments then I can handle.</red>");
    }

    public String NOT_ENOUGH_ARGS() {
        return getString("player.requirements.not-enough-args", "<red>You did not supply enough arguments.</red>");
    }

    public String MUST_BE_PLAYER() {
        return getString("player.requirements.must-be-player", "<red>You must be a player to use this command.</red>");
    }

    public String MUST_BE_CONSOLE_SENDER() {
        return getString("player.requirements.must-be-console-sender", "<red>You must be using console to use this command.</red>");
    }

    public String MUST_BE_LOOKING_AT_BLOCK() {
        return getString("player.requirements.must-be-looking-at-block", "<red>You must be looking at a block.</red>");
    }

    public String TARGET_NOT_ONLINE() {
        return getString("player.target-not-online", "<red>The player</red> <gold>{player}</gold> <red>is not online.</red>");
    }

    public String TARGET_SAME_PLAYER() {
        return getString("player.target-same-player", "<red>You cannot use this command on yourself.</red>");
    }

    public String NO_PERMISSION() {
        return getString("player.no-permission", "<red>You do not have permission to use that command!</red>");
    }

    public String INVENTORY_NOT_EMPTY() {
        return getString("player.inventory-not-empty", "<red>Inventory is not empty, Please make room before opening</red> <gold>{crate}.</gold>");
    }

    public String OBTAINING_KEYS() {
        return getString("player.obtaining-keys", "<gray>You have been given</gray> <gold>{amount} {key}</gold> <gray>keys.</gray>");
    }

    public String TOO_CLOSE_TO_ANOTHER_PLAYER() {
        return getString("player.too-close-to-another-player", "<red>You are too close to a player that is opening their crate.</red>");
    }

    public String NOT_A_CRATE() {
        return getString("crates.requirements.not-a-crate", "<red>There is no crate called</red> <gold>{crate}.</gold>");
    }

    public String NOT_A_NUMBER() {
        return getString("crates.requirements.not-a-number", "<gold>{number}</gold> <red>is not a number.</red>");
    }

    public String NOT_ON_BLOCK() {
        return getString("crates.not-on-block", "<red>You must be standing on a block to use</red> <gold>{crate}.</gold>");
    }

    public String OUT_OF_TIME() {
        return getString("crates.out-of-time", "<red>You took</red> <green>5 Minutes</green> <red>to open the</red> <gold>{crate}</gold> <red>so it closed.</red>");
    }

    public String PREVIEW_DISABLED() {
        return getString("crates.preview-disabled", "<red>The preview for</red> <gold>{crate}</gold> <gray>is currently disabled.</gray>");
    }

    public String ALREADY_OPEN() {
        return getString("crates.already-open", "<red>You are already opening</red> <gold>{crate}.</gold>");
    }

    public String IN_USE() {
        return getString("crates.in-use", "<gold>{crate}</gold> <red>is already in use. Please wait until it finishes!</red>");
    }

    public String CANNOT_BE_VIRTUAL_CRATE() {
        return getString("crates.cannot-be-a-virtual-crate", "<gold>{crate}</gold> <red>cannot be used as a Virtual Crate. You have it set to</red> <gold>{cratetype}.</gold>");
    }

    public String NEED_MORE_ROOM() {
        return getString("crates.need-more-room", "<red>There is not enough space to open {crate} here.</red>");
    }

    public String WORLD_DISABLED() {
        return getString("crates.world-disabled", "<red>Crates are disabled in</red> <gold>{world}.</gold>");
    }

    public List<String> PHYSICAL_CRATE_CREATED() {
        List<String> physicalCrateCreated = getStringList("crates.physical-crate.created");

        if (get("crates.physical-crate.created") == null) {
            set("crates.physical-crate.created", List.of(
                    "<gray>You have set that block to</gray> <gold>{crate}.</gold>",
                    "<gray>To remove</gray> <gold>{crate},</gold> <gray>Shift Click Break in Creative to remove.</gray>"
            ));

            save();

            return physicalCrateCreated;
        }

        return physicalCrateCreated;
    }

    public String PHYSICAL_CRATE_REMOVED() {
        return getString("crates.physical-crate.removed", "<gray>You have removed</gray> <gold>{id}.</gold>");
    }

    public String OPENED_A_CRATE() {
        return getString("command.open.opened-a-crate", "<gray>You have opened the</gray> <gold>{crate}</gold> <gray>crate for</gray> <gold>{player}.</gold>");
    }

    public String GIVEN_PLAYER_KEYS() {
        return getString("command.give.given-player-keys", "<gray>You have given</gray> <gold>{player} {amount} keys.</gold>");
    }

    public String CANNOT_GIVE_PLAYER_KEYS() {
        return getString("command.give.cannot-give-player-keys", "<gray>You have been given</gray> <gold>{amount} {key}</gold> <gray>virtual keys because your inventory was full.</gray>");
    }

    public String GIVEN_EVERYONE_KEYS() {
        return getString("command.give.given-everyone-keys", "<gray>You have given everyone</gray> <gold>{amount} keys.</gold>");
    }

    public String TAKE_PLAYER_KEYS() {
        return getString("command.take.take-player-keys", "<gray>You have taken</gray> <gold>{amount} key(s)</gold> <gray>from</gray> <gold>{player}.</gold>");
    }

    public String TAKE_OFFLINE_PLAYER_KEYS() {
        return getString("command.take.take-offline-player-keys", "<gray>You have taken</gray> <gold>{amount} key(s)</gold> <gray>from the offline player</gray> <gold>{player}.</gold>");
    }

    public String NO_ITEM_IN_HAND() {
        return getString("command.additem.no-item-in-hand", "<red>You need to have an item in your hand to add it to</red> <gold>{crate}.</gold>");
    }

    public String ADD_ITEM_FROM_HAND() {
        return getString("command.additem.add-item-from-hand", "<gray>The item has been added to</gray> <gold>{crate}</gold> <gray>as</gray> <gold>Prize #{prize}.</gold>");
    }

    public String RELOAD_PLUGIN() {
        return getString("command.reload.reload-plugin", "<gold>You have reloaded the plugin!</gold>");
    }

    public String NOT_ENOUGH_KEYS() {
        return getString("command.transfer-not-enough-keys", "<red>You do not have enough keys to transfer.</red>");
    }

    public String TRANSFERRED_KEYS() {
        return getString("command.transfer.transferred-keys", "<gray>You have transferred</gray> <red>{amount} {crate}</red> <gray>keys to</gray> <red>{player}.</red>");
    }

    public String TRANSFERRED_KEYS_RECEIVED() {
        return getString("command.transfer.transferred-keys-received", "<gray>You have received</gray> <red>{amount} {crate}</red> <gray>keys from</gray> <red>{player}.</red>");
    }

    public String NO_VIRTUAL_KEYS_PERSONAL() {
        return getString("command.keys.personal.no-virtual-keys", "<dark_gray>(</dark_gray><gold>!</gold><dark_gray>)</dark_gray> <gray>You currently do not have any virtual keys.</gray>");
    }

    public List<String> NO_VIRTUAL_KEYS_PERSONAL_HEADER() {
        List<String> noVirtualKeysPersonalHeader = getStringList("command.keys.personal.header");

        if (get("command.keys.personal.header") == null) {
            set("command.keys.personal.header", List.of(
                    "<dark_gray>(</dark_gray><gold>!</gold><dark_gray>)</dark_gray> <gray>A list of your current amount of keys.</gray>"
            ));

            save();

            return noVirtualKeysPersonalHeader;
        }

        return noVirtualKeysPersonalHeader;
    }

    public String NO_VIRTUAL_KEYS_OTHER() {
        return getString("command.keys.other-player.no-virtual-keys", "<dark_gray>(</dark_gray><gold>!</gold><dark_gray>)</dark_gray> <gray>The player</gray> <red>{player}</red> <gray>does not have any keys.</gray>");
    }

    public List<String> NO_VIRTUAL_KEYS_OTHER_HEADER() {
        List<String> noVirtualKeysOtherHeader = getStringList("command.keys.other-player.header");

        if (get("command.keys.other-player.header") == null) {
            set("command.keys.other-player.header", List.of(
                    "<dark_gray>(</dark_gray><gold>!</gold><dark_gray>)</dark_gray> <gray>A list of</gray> <red>{player}''s</red> <gray>current amount of keys.</gray>"
            ));

            return noVirtualKeysOtherHeader;
        }

        return noVirtualKeysOtherHeader;
    }

    public String CRATE_FORMAT() {
        return getString("keys.crate-format", "{crate} <dark_gray>»</dark_gray> <gold>{keys} keys.</gold>");
    }
}