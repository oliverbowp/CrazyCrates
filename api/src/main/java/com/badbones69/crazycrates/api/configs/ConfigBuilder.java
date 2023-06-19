package com.badbones69.crazycrates.api.configs;

import ch.jalu.configme.configurationdata.ConfigurationData;
import ch.jalu.configme.configurationdata.ConfigurationDataBuilder;
import com.badbones69.crazycrates.api.configs.types.Config;
import com.badbones69.crazycrates.api.configs.types.PluginConfig;
import com.badbones69.crazycrates.api.configs.types.sections.PluginSupportSection;

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
    public static ConfigurationData buildPluginConfig() {
        return ConfigurationDataBuilder.createConfiguration(
                PluginConfig.class,
                PluginSupportSection.class
        );
    }

    public static ConfigurationData buildConfig() {
        return ConfigurationDataBuilder.createConfiguration(
                Config.class
        );
    }
}