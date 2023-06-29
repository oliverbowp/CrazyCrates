plugins {
    id("paper-plugin")
}

repositories {
    flatDir {
        dirs("libs")
    }

    maven("https://repo.aikar.co/content/groups/aikar/")
}

dependencies {
    api(project(":crazycrates-api"))

    compileOnly("me.clip", "placeholderapi", "2.11.3")
    compileOnly("com.github.decentsoftware-eu", "decentholograms", "2.8.2")

    implementation("de.tr7zw", "item-nbt-api", "2.11.3")

    implementation("dev.triumphteam", "triumph-cmd-bukkit", "2.0.0-SNAPSHOT")

    implementation("co.aikar", "acf-paper", "0.5.1-SNAPSHOT")

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