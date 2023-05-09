plugins {
    id("paper-plugin")
}

dependencies {
    api("ch.jalu:configme:1.3.0")

    compileOnly(libs.holographic.displays)
    compileOnly(libs.decent.holograms)

    compileOnly(libs.fancy.holograms)

    compileOnly(libs.cmi.api)
    compileOnly(libs.cmi.lib)

    api(libs.crazycore)
}