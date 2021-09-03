plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building a CLI application
    // You can run your app via task "run": ./gradlew run
    application

    id("org.openjfx.javafxplugin") version "0.0.9"
}

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    google()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    maven(url = "https://oss.sonatype.org/content/repositories/releases/")
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
val roboVMVersion = "2.3.12"
val box2DLightsVersion = "1.5"
val ashleyVersion = "1.7.3"
val aiVersion = "1.8.2"
val gdxControllersVersion = "2.1.0"

dependencies {
    // Example library: Guava. Add what you need (and remove Guava if you don't use it)
    // implementation("com.google.guava:guava:28.1-jre")

    // JavaFX: comment out if you do not need them
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }

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
    implementation("com.badlogicgames.gdx:gdx-tools:$gdxVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")

    // JUnit API and testing engine
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    // Enables JUnit 5 Jupiter module
    useJUnitPlatform()
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
  manifest {
    attributes["Main-Class"] = "it.unibo.pensilina14.bullet.ballet.Launcher"
  }

  from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
      configurations["runtimeClasspath"].map { if(it.isDirectory) it else zipTree(it) }
    })
}