import java.awt.Color
import java.io.File

plugins {
    id("root-plugin")

    id("featherpatcher")
    id("com.modrinth.minotaur")
}

val releaseColor = Color(27, 217, 106)
val betaColor = Color(255, 163, 71)
val logColor = Color(37, 137, 204)

val isBeta = false
val color = if (isBeta) logColor else releaseColor
val repo = if (isBeta) "beta" else "releases"

val type = if (isBeta) "beta" else "release"
val otherType = if (isBeta) "Beta" else "Release"

val msg = "New version of ${rootProject.name} is ready! <@&929463452232192063>"

val downloads = """
    https://modrinth.com/plugin/${rootProject.name.lowercase()}/version/${rootProject.version}
""".trimIndent()

// The commit id for the "main" branch prior to merging a pull request.
//val start = "5c1fbac"

// The commit id AFTER merging the pull request so the last commit before you release.
//val end = "9deae3"

//val commitLog = getGitHistory().joinToString(separator = "") { formatGitLog(it) }

val desc = """
  # Release ${rootProject.version}
  ### Changes         
  * Fixed the effect removal for enchantments that give more than one effect.
  * Removed the old way of handling armor equips & replace it with an improved implementation using PaperMC.
  * Fixed the g-kit auto equip which didn't account for materials.
  * Improved performance by cutting down on getLore() misuse.
  * Partially remove the handling of spawners from the plugin to prevent dupes.
  * Fixes the shulker dupe that was created by CustomItems trying to also handle the BlockBreakEvents fired by blast.
           
  ### Commits
            
  <details>
          
  <summary>Other</summary>  
            
  </details>
                
  As always, report any bugs @ https://github.com/Crazy-Crew/${rootProject.name}/issues
""".trimIndent()

val versions = listOf(
    "1.19",
    "1.19.1",
    "1.19.2",
    "1.19.3",
    "1.19.4"
)

tasks {
    modrinth {
        token.set(System.getenv("MODRINTH_TOKEN"))
        projectId.set(rootProject.name.lowercase())

        versionName.set("${rootProject.name} ${rootProject.version}")
        versionNumber.set(rootProject.version.toString())

        versionType.set(type)

        val file = File("$rootDir/jars")
        if (!file.exists()) file.mkdirs()

        uploadFile.set(layout.buildDirectory.file("$file/${rootProject.name}-${rootProject.version}.jar"))

        autoAddDependsOn.set(true)

        gameVersions.addAll(versions)

        loaders.addAll(listOf("paper", "purpur"))

        changelog.set(desc)
    }
}

publishing {
    repositories {
        val repo = if (isBeta) "beta" else "releases"
        maven("https://repo.crazycrew.us/$repo") {
            name = "crazycrew"
            //credentials(PasswordCredentials::class)

            credentials {
                username = System.getenv("REPOSITORY_USERNAME")
                password = System.getenv("REPOSITORY_PASSWORD")
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = rootProject.group.toString()
            artifactId = "${rootProject.name.lowercase()}-api"
            version = rootProject.version.toString()

            from(components["java"])
        }
    }
}
