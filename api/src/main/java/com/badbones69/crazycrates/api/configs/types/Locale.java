package com.badbones69.crazycrates.api.configs.types;

import com.badbones69.crazycrates.api.configs.types.legacy.MessageEnum;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Locale extends YamlConfiguration {

    private final File file;

    public Locale(File file) {
        this.file = file;
    }

    public void load() {
        try {
            load(this.file);

            ConfigurationSection section = getConfigurationSection("Messages");

            if (section != null) {
                for (MessageEnum value : MessageEnum.values()) {
                    value.setMessage(getConfiguration());

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
        return getString("misc.unknown-command", "&cThe command {command} is not known.");
    }

    public String NO_TELEPORTING() {
        return getString("misc.no-teleporting", "&cYou may not teleport away while opening &6{crate}.");
    }

    public String NO_COMMANDS() {
        return getString("misc.no-commands", "&cYou are not allowed to use commands while opening &6{crate}.");
    }

    public String NO_KEYS() {
        return getString("misc.no-keys", "&cYou need a {key} &cin your hand to use &6{crate}.");
    }

    public String NO_VIRTUAL_KEYS() {
        return getString("misc.no-virtual-keys", "&cYou need {key} &cto open &6{crate}.");
    }

    public String FEATURE_DISABLED() {
        return getString("misc.feature-disabled", "&cThis feature is disabled. We have no ETA on when this will function.");
    }

    public String CORRECT_USAGE() {
        return getString("misc.correct-usage", "&cThe correct usage for this command is &e{usage}");
    }

    public String NO_PRIZES_FOUND() {
        return getString("errors.no-prizes.found", "&c{crate} contains no prizes that you can win.");
    }

    public String NO_SCHEMATICS_FOUND() {
        return getString("errors.no-schematics-found", "&cNo schematic were found, Please re-generate them by deleting the folder or checking for errors!");
    }

    public String PRIZE_ERROR() {
        return getString("errors.prize-error", "&cAn error has occurred in &6{crate} &cfor &6#{prize}. &eContact your owner!");
    }

    public String INTERNAL_ERROR() {
        return getString("errors.internal-error", "&cAn internal error has occurred. Please check the console for the full error.");
    }

    public String TOO_MANY_ARGS() {
        return getString("player.requirements.too-many-args", "&cYou put more arguments then I can handle.");
    }

    public String NOT_ENOUGH_ARGS() {
        return getString("player.requirements.not-enough-args", "&cYou did not supply enough arguments.");
    }

    public String MUST_BE_PLAYER() {
        return getString("player.requirements.must-be-player", "&cYou must be a player to use this command.");
    }

    public String MUST_BE_CONSOLE_SENDER() {
        return getString("player.requirements.must-be-console-sender", "&cYou must be using console to use this command.");
    }

    public String MUST_BE_LOOKING_AT_BLOCK() {
        return getString("player.requirements.must-be-looking-at-block", "&cYou must be looking at a block.");
    }

    public String TARGET_NOT_ONLINE() {
        return getString("player.target-not-online", "&cThe player &6{player} &cis not online.");
    }

    public String TARGET_SAME_PLAYER() {
        return getString("player.target-same-player", "&cYou cannot use this command on yourself.");
    }

    public String NO_PERMISSION() {
        return getString("player.no-permission", "&cYou do not have permission to use that command!");
    }

    public String INVENTORY_NOT_EMPTY() {
        return getString("player.inventory-not-empty", "&cInventory is not empty, Please make room before opening &6{crate}.");
    }

    public String OBTAINING_KEYS() {
        return getString("player.obtaining-keys", "&7You have been given &6{amount} {key} &7keys.");
    }

    public String TOO_CLOSE_TO_ANOTHER_PLAYER() {
        return getString("player.too-close-to-another-player", "&cYou are too close to a player that is opening their crate.");
    }

    public String NOT_A_CRATE() {
        return getString("crates.requirements.not-a-crate", "&cThere is no crate called &6{crate}.");
    }

    public String NOT_A_NUMBER() {
        return getString("crates.requirements.not-a-number", "&6{number} &cis not a number.");
    }

    public String NOT_ON_BLOCK() {
        return getString("crates.not-on-block", "&cYou must be standing on a block to use &6{crate}.");
    }

    public String OUT_OF_TIME() {
        return getString("crates.out-of-time", "&cYou took &a5 Minutes &cto open the &6{crate} &cso it closed.");
    }

    public String PREVIEW_DISABLED() {
        return getString("crates.preview-disabled", "&cThe preview for &6{crate} &7is currently disabled.");
    }

    public String ALREADY_OPEN() {
        return getString("crates.already-open", "&cYou are already opening &6{crate}.");
    }

    public String IN_USE() {
        return getString("crates.in-use", "&6{crate} &cis already in use. Please wait until it finishes!");
    }

    public String CANNOT_BE_VIRTUAL_CRATE() {
        return getString("crates.cannot-be-a-virtual-crate", "&6{crate} &ccannot be used as a Virtual Crate. You have it set to &6{cratetype}.");
    }

    public String NEED_MORE_ROOM() {
        return getString("crates.need-more-room", "&cThere is not enough space to open {crate} here.");
    }

    public String WORLD_DISABLED() {
        return getString("crates.world-disabled", "&cCrates are disabled in &6{world}.");
    }

    public List<String> PHYSICAL_CRATE_CREATED() {
        List<String> physicalCrateCreated = getStringList("crates.physical-crate.created");

        if (get("crates.physical-crate.created") == null) {
            set("crates.physical-crate.created", List.of(
                    "&7You have set that block to &6{crate}.",
                    "&7To remove &6{crate}, &7Shift Click Break in Creative to remove."
            ));

            save();

            return physicalCrateCreated;
        }

        return physicalCrateCreated;
    }

    public String PHYSICAL_CRATE_REMOVED() {
        return getString("crates.physical-crate.removed", "&7You have removed &6{id}.");
    }

    public String OPENED_A_CRATE() {
        return getString("command.open.opened-a-crate", "&7You have opened the &6{crate} &7crate for &6{player}.");
    }

    public String GIVEN_PLAYER_KEYS() {
        return getString("command.give.given-player-keys", "&7You have given &6{player} {amount} keys.");
    }

    public String CANNOT_GIVE_PLAYER_KEYS() {
        return getString("command.give.cannot-give-player-keys", "&7You have been given &6{amount} {key} &7virtual keys because your inventory was full.");
    }

    public String GIVEN_EVERYONE_KEYS() {
        return getString("command.give.given-everyone-keys", "&7You have given everyone &6{amount} keys.");
    }

    public String TAKE_PLAYER_KEYS() {
        return getString("command.take.take-player-keys", "&7You have taken &6{amount} key(s) &7from &6{player}.");
    }

    public String TAKE_OFFLINE_PLAYER_KEYS() {
        return getString("command.take.take-offline-player-keys", "&7You have taken &6{amount} key(s) &7from the offline player &6{player}.");
    }

    public String NO_ITEM_IN_HAND() {
        return getString("command.additem.no-item-in-hand", "&cYou need to have an item in your hand to add it to &6{crate}.");
    }

    public String ADD_ITEM_FROM_HAND() {
        return getString("command.additem.add-item-from-hand", "&7The item has been added to &6{crate} &7as &6Prize #{prize}.");
    }

    public String RELOAD_PLUGIN() {
        return getString("command.reload.reload-plugin", "&eYou have reloaded the plugin!");
    }

    public String NOT_ENOUGH_KEYS() {
        return getString("command.transfer-not-enough-keys", "&cYou do not have enough keys to transfer.");
    }

    public String TRANSFERRED_KEYS() {
        return getString("command.transfer.transferred-keys", "&7You have transferred &c{amount} {crate} &7keys to &c{player}.");
    }

    public String TRANSFERRED_KEYS_RECEIVED() {
        return getString("command.transfer.transferred-keys-received", "&7You have received &c{amount} {crate} &7keys from &c{player}.");
    }

    public String NO_VIRTUAL_KEYS_PERSONAL() {
        return getString("command.keys.personal.no-virtual-keys", "&8(&6!&8) &7You currently do not have any virtual keys.");
    }

    public List<String> NO_VIRTUAL_KEYS_PERSONAL_HEADER() {
        List<String> noVirtualKeysPersonalHeader = getStringList("command.keys.personal.header");

        if (get("command.keys.personal.header") == null) {
            set("command.keys.personal.header", List.of(
                    "&8(&6!&8) &7A list of your current amount of keys."
            ));

            save();

            return noVirtualKeysPersonalHeader;
        }

        return noVirtualKeysPersonalHeader;
    }

    public String NO_VIRTUAL_KEYS_OTHER() {
        return getString("command.keys.other-player.no-virtual-keys", "&8(&6!&8) &7The player &c{player} &7does not have any keys.");
    }

    public List<String> NO_VIRTUAL_KEYS_OTHER_HEADER() {
        List<String> noVirtualKeysOtherHeader = getStringList("command.keys.other-player.header");

        if (get("command.keys.other-player.header") == null) {
            set("command.keys.other-player.header", List.of(
                    "&8(&6!&8) &7A list of &c{player}''s &7current amount of keys."
            ));

            return noVirtualKeysOtherHeader;
        }

        return noVirtualKeysOtherHeader;
    }

    public String CRATE_FORMAT() {
        return getString("keys.crate-format", "{crate} &8» &6{keys} keys.");
    }
}