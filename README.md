<div align="center">
    <h1>Croissant</h1>
    <blockquote>
        <p>🥐 Lightweight yet useful library for Bukkit/Spigot/Paper plugins.</p>
    </blockquote>
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

Croissant supports Bukkit/Spigot/Paper 1.8.x to 1.14.x.

## Installation

TO DO

## Usage

TO DO

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

---

This project is licensed under the MIT license.
