plugins {
    id("paper-plugin")

    id("xyz.jpenilla.run-paper") version "2.1.0"
}

repositories {
    flatDir {
        dirs("libs")
    }
}

dependencies {
    api(project(":crazycrates-api"))

    compileOnly("me.clip", "placeholderapi", "2.11.3")
    compileOnly("com.github.decentsoftware-eu", "decentholograms", "2.8.2")

    implementation("de.tr7zw", "item-nbt-api", "2.11.3")

    compileOnly("dev.triumphteam", "triumph-cmd-bukkit", "2.0.0-SNAPSHOT")

    implementation("org.bstats", "bstats-bukkit", "3.0.0")
}

tasks {
    reobfJar {
        val file = File("$rootDir/jars")

        if (!file.exists()) file.mkdirs()

        outputJar.set(layout.buildDirectory.file("$file/${rootProject.name}-${rootProject.version}.jar"))
    }

    shadowJar {
        fun reloc(pkg: String) = relocate(pkg, "${rootProject.group}.dep.$pkg")

        reloc("de.tr7zw.changeme.nbtapi")
        reloc("org.bstats")
        reloc("ch.jalu")
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