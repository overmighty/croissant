<div align="center">
    <h1>Croissant</h1>
    <blockquote>
        <p>🥐 Lightweight yet useful library for Bukkit/Spigot/Paper plugins.</p>
    </blockquote>
    <p>
        <a href="https://bintray.com/overmighty/maven/croissant">
            <img alt="Bintray" src="https://img.shields.io/bintray/v/overmighty/maven/croissant">
        </a>
        <a href="https://search.maven.org/artifact/com.github.overmighty/croissant">
            <img alt="Maven Central" src="https://img.shields.io/maven-central/v/com.github.overmighty/croissant">
        </a>
        <a href="https://discord.gg/u2UcCnY">
            <img alt="Discord" src="https://discordapp.com/api/guilds/526906946209447939/embed.png">
        </a>
    </p>
</div>

## Introduction

Croissant is a lightweight Java library that provides a **command framework**
and a **GUI framework** for Bukkit/Spigot/Paper plugins.

Here is a short summary of its features:

- **Command framework**
    - No need to declare commands in your plugin's plugin.yml file
    - Automated argument parsing and tab-completion
    - Arguments are inferred from
    [method parameters](src/main/java/com/github/overmighty/croissant/command/CommandExecutor.java)
    - Arguments can be either required or optional, and optional arguments can
    have default values
    - Built-in argument types + ability to create your own argument types
    - Subcommands
- **GUI framework**
    - Create interactive GUIs from fake chest inventories
    - Supports both single-page GUIs and multi-page GUIs with navigation buttons
    (referred to as "scrollable GUIs")

Croissant is currently written against the Paper 1.15.1 API, but supports
Bukkit/Spigot/Paper 1.8.x to 1.15.x.

## Installation

Releases of Croissant are published to a
[Maven repository on Bintray](https://bintray.com/overmighty/maven/croissant),
which is synced with JCenter and Maven Central.

If your project is built with Maven or a supported build tool such as Gradle,
you can easily add Croissant as a dependency for your project:

#### Maven

```xml
<dependency>
  <groupId>com.github.overmighty</groupId>
  <artifactId>croissant</artifactId>
  <version>1.0.0</version>
</dependency>
```

#### Gradle (Groovy DSL)

```gradle
repositories {
    jcenter() // or mavenCentral()
}

dependencies {
    implementation 'com.github.overmighty:croissant:1.0.0'
}
```

[Example Gradle build script](https://github.com/OverMighty/croissant-example/blob/master/build.gradle)

## Usage

The Croissant Javadoc can be browsed online [here](https://javadoc.io/doc/com.github.overmighty/croissant).

You can also look at the [example Bukkit plugin](https://github.com/OverMighty/croissant-example)
to get started with Croissant.

## Support

If you need help with Croissant, you can join my [Discord server](https://discord.gg/u2UcCnY).

## Building

**JDK 8 or later is required.**

Croissant uses [Gradle](https://gradle.org/) and a Gradle wrapper is included in
this repository.

The following command can be run on Unix-like systems to build Croissant using
the Gradle wrapper included in this repository:

```
$ ./gradlew clean build
```

On Windows, the `gradlew.bat` batch script can be used instead of the `gradlew`
POSIX shell script.

## License

This project is licensed under the [MIT license](./LICENSE).
