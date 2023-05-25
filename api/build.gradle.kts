plugins {
    id("paper-plugin")
}

dependencies {
    api(libs.configme)

    compileOnly(libs.holographic.displays)
    compileOnly(libs.decent.holograms)

    compileOnly(libs.fancy.holograms)

    compileOnly(libs.cmi.api)
    compileOnly(libs.cmi.lib)

    implementation("com.ryderbelserion.stick:stick-api:2.1.0.15")
}