plugins {
    id("paper-plugin")
}

repositories {
    flatDir {
        dirs("${rootDir}/libs")
    }
}

dependencies {
    compileOnly(libs.config.me)

    compileOnly(libs.decent.holograms)

    compileOnly(libs.nbt.api)

    //compileOnly(libs.brigadier)

    compileOnly(libs.fancy.holograms)
    compileOnly(libs.fancy.npcs)

    compileOnly("cmi-api:CMI-API")
    compileOnly("cmi-lib:CMILib")

    compileOnly(libs.stick.api)
}