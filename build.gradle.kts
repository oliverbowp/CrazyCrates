plugins {
    id("paper-plugin")
}

dependencies {
    api(project(":crazycrates-api"))

    compileOnly(libs.placeholder.api)

    implementation(libs.nbt.api)

    implementation(libs.config.me)

    implementation(libs.stick.api)

    implementation(libs.triumph.cmds)

    implementation(libs.bstats.bukkit)
}

tasks {
    reobfJar {
        val file = File("$rootDir/jars")

        if (!file.exists()) file.mkdirs()

        outputJar.set(layout.buildDirectory.file("$file/${rootProject.name}-${rootProject.version}.jar"))
    }

    shadowJar {
        fun reloc(pkg: String) = relocate(pkg, "${rootProject.group}.dependency.$pkg")

        reloc("de.tr7zw.changeme.nbtapi")
        reloc("org.bstats")
        reloc("ch.jalu")
    }

    processResources {
        filesMatching("plugin.yml") {
            expand(
                "name" to rootProject.name,
                "group" to rootProject.group,
                "version" to rootProject.version,
                "description" to rootProject.description,
                "website" to "https://modrinth.com/plugin/${rootProject.name.lowercase()}"
            )
        }
    }
}