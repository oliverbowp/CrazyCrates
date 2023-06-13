package com.badbones69.crazycrates.api.crates.types;

public enum CrateType {

    csgo("CSGO"),
    quad_crate("QuadCrate"),
    cosmic("Cosmic"),
    quick_crate("QuickCrate"),
    roulette("Roulette"),
    crate_on_the_go("CrateOnTheGo"),
    fire_cracker("FireCracker"),
    wonder("Wonder"),
    wheel("Wheel"),
    war("War");

    private final String crateType;

    CrateType(String crateType) {
        this.crateType = crateType;
    }

    public String getCrateType() {
        return this.crateType;
    }
}