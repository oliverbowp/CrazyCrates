plugins {
    id("paper-plugin")
}

repositories {
    maven("https://api.modrinth.com/maven/")
}

dependencies {
    api(libs.configme)

    compileOnly(libs.decent.holograms)

    compileOnly(libs.fancy.holograms)
    compileOnly(libs.fancy.npcs)

    compileOnly(libs.cmi.api)
    compileOnly(libs.cmi.lib)

    implementation(libs.stick)
}