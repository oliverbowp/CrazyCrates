package com.badbones69.crazycrates.api.configs.types.sections;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.configurationdata.CommentsConfiguration;
import ch.jalu.configme.properties.Property;
import com.badbones69.crazycrates.api.enums.HologramSupport;

import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public class PluginSupportSection implements SettingsHolder {

    public PluginSupportSection() {}

    @Override
    public void registerComments(CommentsConfiguration conf) {
        String[] header = {
                "=========================================================",
                "",
                "This section is solely related to plugin dependencies.",
                "You can now have easy control over what gets enabled or not",
                "",
                "========================================================="
        };

        conf.setComment("support", header);
    }

    @Comment({
            "PlaceholderAPI is required for this option to be true!"
    })
    public static final Property<Boolean> PLACEHOLDERAPI_SUPPORT = newProperty("support.placeholderapi", false);

    @Comment({
            "ItemsAdder is required for this option to be true!"
    })
    public static final Property<Boolean> ITEMS_ADDER_SUPPORT = newProperty("support.custom-items.itemsadder", false);

    @Comment({
            "Whether you want holograms support to be enabled or not.",
            "You must have a holograms plugin on your server before this is enabled."
    })
    public static final Property<Boolean> HOLOGRAMS_SUPPORT_ENABLED = newProperty("support.holograms.toggle", false);

    @Comment({
            "Available Values: decent_holograms, cmi_holograms, fancy_holograms, internal_holograms",
            "You must have at least one of these plugins on your server in order for this to work!"
    })
    public static final Property<HologramSupport> HOLOGRAMS_SUPPORT_TYPE = newProperty(HologramSupport.class, "support.holograms.type", HologramSupport.decent_holograms);
}