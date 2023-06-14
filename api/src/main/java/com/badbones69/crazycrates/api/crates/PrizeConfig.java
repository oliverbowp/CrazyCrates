package com.badbones69.crazycrates.api.crates;

import com.badbones69.crazycrates.api.crates.types.CrateType;

import java.util.Collections;
import java.util.List;

public class PrizeConfig {

    private final CrateType crateType;
    private final String crateName;
    private final String displayName;
    private final String item;
    private final String player;
    private final int amount;
    private final List<String> enchantments;
    private final boolean loreToggle;
    private final List<String> lore;

    public PrizeConfig(CrateType crateType, String crateName, String displayName, String item, String player, int amount, List<String> enchantments, boolean loreToggle, List<String> lore) {
        this.crateType = crateType;
        this.crateName = crateName;
        this.displayName = displayName;

        this.item = item;
        this.player = player;
        this.amount = amount;
        this.enchantments = enchantments != null ? enchantments : Collections.emptyList();

        this.loreToggle = loreToggle;

        this.lore = lore != null ? lore : Collections.emptyList();

        //if (lore != null && loreToggle) this.lore = lore; else this.lore = Collections.emptyList();
    }

    public CrateType getCrateType() {
        return this.crateType;
    }

    public String getCrateName() {
        return this.crateName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}