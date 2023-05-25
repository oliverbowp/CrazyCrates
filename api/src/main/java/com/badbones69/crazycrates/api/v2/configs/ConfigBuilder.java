package com.badbones69.crazycrates.api.v2.configs;

import ch.jalu.configme.configurationdata.ConfigurationData;
import ch.jalu.configme.configurationdata.ConfigurationDataBuilder;
import com.badbones69.crazycrates.api.v2.configs.types.ConfigSettings;
import com.badbones69.crazycrates.api.v2.configs.types.PluginSettings;
import com.badbones69.crazycrates.api.v2.configs.types.sections.PluginSupportSection;

public class ConfigBuilder {

    /**
     * Private constructor to prevent instantiation
     */
    private ConfigBuilder() {}

    /**
     * Builds the core configuration data.
     *
     * @return configuration data object containing the main crate configurations.
     */
    public static ConfigurationData buildPluginSettings() {
        return ConfigurationDataBuilder.createConfiguration(
                PluginSettings.class,
                PluginSupportSection.class
        );
    }

    public static ConfigurationData buildConfigSettings() {
        return ConfigurationDataBuilder.createConfiguration(
                ConfigSettings.class
        );
    }
}