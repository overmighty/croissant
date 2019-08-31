package com.github.overmighty.croissant;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Provides methods for configuring the Croissant library. The library must be
 * initialized by calling {@link Croissant#setPlugin(JavaPlugin)} before the
 * Croissant command framework and the Croissant GUI framework can be used.
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
     * Sets the Bukkit {@link JavaPlugin} that is using the library. Required to
     * use the command framework and the GUI framework.
     *
     * @param plugin the plugin that is using the library
     */
    public static void setPlugin(JavaPlugin plugin) {
        Croissant.plugin = plugin;
    }

}
