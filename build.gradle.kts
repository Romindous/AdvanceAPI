import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    id("java")
    id("maven-publish")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.18"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

group = "eu.endercentral.crazy_advancements"
version = "2.21.7"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    paperweight.paperDevBundle("1.21.8-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(21)
        dependsOn(clean)
    }

    jar {
        //from(zipTree("libs/jedis-4.3.1.zip"))
        destinationDirectory.set(layout.buildDirectory)
        archiveFileName = "${project.name}-${version}.jar"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifact(tasks.reobfJar)
            artifact(tasks.jar).classifier = "mojmap"
        }
    }
}

bukkit {
    main = "eu.endercentral.crazy_advancements.CrazyAdvancementsAPI"
    author = "ZockerAxel"
    apiVersion = "1.21.8" // Should be always same as dev bundle version
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP

    commands {
        register("grant") {
            usage = "/grant <Player> <Manager> <Advancement> [Criteria...]"
            description = "Grants <Advancement>-[Criteria...] to <Player> in <Manager>"
            aliases = listOf("cagrant")
        }

        register("revoke") {
            usage = "/revoke <Player> <Manager> <Advancement> [Criteria...]"
            description = "Revokes <Advancement>-[Criteria...] to <Player> in <Manager>"
            aliases = listOf("carevoke")
        }

        register("setprogress") {
            usage = "/setprogress <Player> <Manager> <Advancement> <Number> [Operation]"
            description = "Sets <Advancement> Progress for <Player> in <Manager> using [Operation]"
            aliases = listOf("caprogress")
        }

        register("showtoast") {
            usage = "/showtoast <Player> <Icon> [Frame] <Message>"
            description = "Displays a Toast Advancement Message"
            aliases = listOf("catoast", "toast")
        }

        register("careload") {
            usage = "/careload [Category]"
            description = "Reloads the Crazy Advancements API. Valid categories are all, advancements, items"
        }
    }
}