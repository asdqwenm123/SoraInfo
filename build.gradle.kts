plugins {
    id("java")
    id("io.papermc.paperweight.userdev") version "1.7.1"
    kotlin("jvm")
}

group = "io.github.asdqwenm123"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "citizens-repo"
        url = uri("https://maven.citizensnpcs.co/repo")
    }
    maven {
        name = "jitpack"
        url = uri("https://jitpack.io/")
    }
}

dependencies {
    paperweight.paperDevBundle("1.19.2-R0.1-SNAPSHOT")
    implementation("io.github.monun:kommand-api:3.1.7")
    implementation(kotlin("stdlib-jdk8"))
    compileOnly("net.citizensnpcs:citizens-main:2.0.35-SNAPSHOT") {
        exclude(group = "*", module = "*")
    }
    compileOnly("com.github.decentsoftware-eu:decentholograms:2.8.9")
    implementation("net.dv8tion:JDA:5.0.1")
}

tasks.assemble {
    dependsOn(tasks.reobfJar)
}

kotlin {
    jvmToolchain(17)
}

tasks.jar {
    destinationDirectory = File("/home/sorayumi/Desktop/cpvp/lobby/plugins")
}
