plugins {
    id("paper-plugin")
}

repositories {
    flatDir {
        dirs("${rootDir}/libs")
    }
}

dependencies {
    api("ch.jalu", "configme", "1.3.0")

    compileOnly("com.github.decentsoftware-eu", "decentholograms", "2.8.2")

    compileOnly("de.tr7zw", "item-nbt-api", "2.11.3")

    compileOnly("cmi-api:CMI-API")
    compileOnly("cmi-lib:CMILib")

    api("com.ryderbelserion.stick", "stick-paper", "2.2.1-snapshot")
}