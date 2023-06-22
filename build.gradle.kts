plugins {
    id("paper-plugin")

    id("xyz.jpenilla.run-paper") version "2.1.0"
}

dependencies {
    api(project(":crazycrates-api"))

    compileOnly(libs.placeholder.api)

    implementation(libs.nbt.api)

    implementation(libs.config.me)

    implementation(libs.stick.api)

    implementation(libs.jorel.cmds)

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
        listOf(
            "de.tr7zw.changeme.nbtapi",
            "dev.jorel.commandapi",
            "dev.triumphteam",
            "org.bstats",
            "com.zaxxer",
            "ch.jalu"
        ).forEach { pack -> relocate(pack, "${rootProject.group}.$pack") }
    }

    runServer {
        minecraftVersion("1.20.1")
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