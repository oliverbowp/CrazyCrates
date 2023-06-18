package com.badbones69.crazycrates.api.configs.types;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.configurationdata.CommentsConfiguration;
import ch.jalu.configme.properties.Property;
import com.badbones69.crazycrates.api.enums.DataSupport;
import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

/**
 * @author RyderBelserion
 *
 * Description: The plugin-settings.yml options.
 */
public class PluginSettings implements SettingsHolder {

    // Empty constructor required by SettingsHolder
    public PluginSettings() {}

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

    @Comment("How many commands should be displayed per page in /crazycrates help?")
    public static final Property<Integer> MAX_HELP_PAGE_ENTRIES = newProperty("settings.max-help-page-entries", 10);

    @Comment("Whether the command prefix should be enabled.")
    public static final Property<Boolean> COMMAND_PREFIX_TOGGLE = newProperty("settings.prefix-toggle", true);

    @Comment("The command prefix that is shown at the beginning of every message.")
    public static final Property<String> COMMAND_PREFIX = newProperty("settings.command-prefix", "&b[&3CrazyCrates&b] &r");

    @Comment("Whether you want to have verbose logging enabled or not.")
    public static final Property<Boolean> VERBOSE_LOGGING = newProperty("settings.verbose-logging", true);

    @Comment("Whether you want statistics sent to https://bstats.org or not.")
    public static final Property<Boolean> TOGGLE_METRICS = newProperty("settings.toggle-metrics", true);

    @Comment({
            "Available Values: json / yaml",
            "The data type you want for the server."
    })
    public static final Property<DataSupport> DATA_TYPE = newProperty(DataSupport.class, "data.type", DataSupport.yaml);
}