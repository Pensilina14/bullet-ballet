plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building a CLI application
    // You can run your app via task "run": ./gradlew run
	application

    id("org.openjfx.javafxplugin") version "0.0.9"
}

repositories {
    mavenCentral()
}

val javaFXModules = listOf(
    "base",
    "controls",
    "fxml",
    "swing",
    "graphics"
)

val supportedPlatforms = listOf("linux", "mac", "win") // All required for OOP

val javaFxVersion = "15.0.1"

val jUnitVersion = "5.7.1"

val kotlinVersion = "1.3.41"

val gdxVersion = "1.10.0"

val aiVersion = "1.8.2"

val box2DLightsVersion = "1.5"

val ashleyVersion = "1.7.3"

dependencies {
    // Example library: Guava. Add what you need (and remove Guava if you don't use it)
    // implementation("com.google.guava:guava:28.1-jre")
	implementation("org.apache.commons:commons-configuration2:2.7")
    // JavaFX: comment out if you do not need them
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }

    // LibGdx framework
    implementation("com.badlogicgames.gdx:gdx-backend-lwjgl:$gdxVersion")
    implementation("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-desktop")

    /*implementation("com.badlogicgames.gdx:gdx-ai:$aiVersion")
    implementation("com.badlogicgames.gdx:gdx:$gdxVersion")
    implementation("com.badlogicgames.gdx:gdx-box2d:$gdxVersion")
    implementation("com.badlogicgames.gdx:gdx-bullet:$gdxVersion")
    implementation("com.badlogicgames.gdx:gdx-freetype:$gdxVersion")
    implementation("com.badlogicgames.box2dlights:box2dlights:$box2DLightsVersion")
    implementation("com.badlogicgames.ashley:ashley:$ashleyVersion")
    implementation("com.badlogicgames.gdx:gdx-ai:$aiVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")

    implementation("com.badlogicgames.gdx:gdx-backend-lwjgl:$gdxVersion")
    implementation("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-desktop")
    implementation("com.badlogicgames.gdx:gdx-box2d-platform:$gdxVersion:natives-desktop")
    implementation("com.badlogicgames.gdx:gdx-bullet-platform:$gdxVersion:natives-desktop")
    implementation("com.badlogicgames.gdx:gdx-freetype-platform:$gdxVersion:natives-desktop")
    implementation("com.badlogicgames.gdx:gdx-tools:$gdxVersion")*/

    // Tiled map editor
    implementation("org.mapeditor:tiled-parent:1.4.2")
    implementation("org.mapeditor:tmxviewer:1.4.2")
    implementation("org.mapeditor:libtiled:1.4.2")
    
    /* for cross-platform jar: */
    runtimeOnly("org.openjfx:javafx-graphics:$javafx.version:win")
    runtimeOnly("org.openjfx:javafx-graphics:$javafx.version:linux")
    //runtimeOnly("org.openjfx:javafx-graphics:$javafx.version:mac")

    // Use JUnit test framework
    testImplementation("junit:junit:4.12")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

application {
    // Define the main class for the application
    mainClassName = "it.unibo.pensilina14.bullet.ballet.Launcher"
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<Jar> {
    duplicatesStrategy = org.gradle.api.file.DuplicatesStrategy.WARN

  manifest {
    attributes["Main-Class"] = "it.unibo.pensilina14.bullet.ballet.Launcher"
  }

  from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
      configurations["runtimeClasspath"].map { if(it.isDirectory) it else zipTree(it) }
    })
}