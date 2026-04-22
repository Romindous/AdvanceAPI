plugins {
    id("java")
    id("maven-publish")
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.21"
}

group = "eu.endercentral.crazy_advancements"
version = "2.26.12"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

sourceSets {
    main {
        java {
            srcDir("src/")
        }
        resources {
            srcDir("resources/")
        }
    }
}

dependencies {
    paperweight.paperDevBundle("26.1.2.build.9-alpha")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(25)
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