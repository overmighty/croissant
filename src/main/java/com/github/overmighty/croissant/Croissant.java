package com.github.overmighty.croissant;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Provides methods for configuring the Croissant library.
 */
public class Croissant {

    private static JavaPlugin plugin;

    /**
     * Returns the Bukkit {@link JavaPlugin} that is using the library.
     *
     * @return the plugin that is using the library
     */
    public static JavaPlugin getPlugin() {
        return plugin;
    }

    /**
     * Sets the Bukkit {@link JavaPlugin} that is using the library. While the plugin is not set,
     * the command framework and the GUI framework cannot function.
     *
     * @param plugin the plugin that is using the library
     */
    public static void setPlugin(JavaPlugin plugin) {
        Croissant.plugin = plugin;
    }

}
