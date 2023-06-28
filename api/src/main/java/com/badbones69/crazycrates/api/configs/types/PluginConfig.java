package com.badbones69.crazycrates.api.configs.types;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.configurationdata.CommentsConfiguration;
import ch.jalu.configme.properties.Property;
import org.bukkit.configuration.file.YamlConfiguration;
import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public class PluginConfig extends YamlConfiguration implements SettingsHolder {

    public PluginConfig() {}

    @Override
    public void registerComments(CommentsConfiguration conf) {
        String[] header = {
                "Github: https://github.com/Crazy-Crew",
                "",
                "Issues: https://github.com/Crazy-Crew/CrazyCrates/issues",
                "Features: https://github.com/Crazy-Crew/CrazyCrates/discussions/categories/features"
        };

        conf.setComment("settings", header);
    }

    @Comment({
            "Choose what prefix you want to use for the permission i.e crazycrates.command.player.help",
            "",
            "Warning: any changes requires a restart!"
    })
    public static final Property<String> COMMAND_PERMISSION = newProperty("settings.permission", "crazycrates");

    @Comment({
            "Choose what language you want the plugin to be in.",
            "",
            "Available Languages: en-US"
    })
    public static final Property<String> LOCALE_FILE = newProperty("settings.language", "en-US");

    @Comment("How many commands should be displayed per page in /crazycrates help?")
    public static final Property<Integer> MAX_HELP_PAGE_ENTRIES = newProperty("settings.max-help-page-entries", 10);

    @Comment("Whether the command prefix should be enabled.")
    public static final Property<Boolean> COMMAND_PREFIX_TOGGLE = newProperty("settings.prefix-toggle", true);

    @Comment("The command prefix that is shown at the beginning of every message.")
    public static final Property<String> COMMAND_PREFIX = newProperty("settings.command-prefix", "&b[&3CrazyCrates&b] &r");

    @Comment("Whether you want to have verbose logging enabled.")
    public static final Property<Boolean> VERBOSE_LOGGING = newProperty("settings.verbose-logging", true);

    @Comment("Should auto save be enabled?")
    public static final Property<Boolean> AUTO_SAVE_TOGGLE = newProperty("settings.saving.toggle", true);

    @Comment("How long in between auto save intervals? Value is in milliseconds! 300,000 = 5 minutes")
    public static final Property<Integer> AUTO_SAVE_TIME = newProperty("settings.saving.time", 300000);

    @Comment("Whether you want statistics sent to https://bstats.org.")
    public static final Property<Boolean> TOGGLE_METRICS = newProperty("settings.toggle-metrics", true);

    //@Comment({
    //        "Available Values: json / yaml",
    //        "This is only related to how user data is stored!",
    //        "Defaults to users.yml"
    //})
    //public static final Property<DataSupport> DATA_TYPE = newProperty(DataSupport.class, "data.type", DataSupport.yaml);
}