plugins {
    id("paper-plugin")
}

dependencies {
    compileOnly(libs.config.me)

    compileOnly(libs.decent.holograms)

    compileOnly(libs.fancy.holograms)
    compileOnly(libs.fancy.npcs)

    //compileOnly(libs.cmi.api)
    //compileOnly(libs.cmi.lib)

    compileOnly(libs.stick.api)
}