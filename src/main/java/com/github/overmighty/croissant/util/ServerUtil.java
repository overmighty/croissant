package com.github.overmighty.croissant.util;

import com.github.overmighty.croissant.Croissant;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;

/**
 * Provides utility methods related to the server that is running the plugin.
 */
public class ServerUtil {

    private static CommandMap commandMap;
    private static boolean paper;
    private static boolean asyncTabCompletionSupported;

    static {
        try {
            Bukkit.class.getMethod("getCommandMap");
            commandMap = Bukkit.getCommandMap();
        } catch (NoSuchMethodException e) {
            // For Bukkit/Spigot servers (only Paper exposes the server's
            // command map with a getter method)
            exposeCommandMap();
        }

        try {
            Class.forName("com.destroystokyo.paper.PaperConfig");
            paper = true;
        } catch (ClassNotFoundException e) {
            paper = false;
        }

        try {
            Class.forName("com.destroystokyo.paper.event.server.AsyncTabCompleteEvent");
            asyncTabCompletionSupported = true;
        } catch (ClassNotFoundException e) {
            asyncTabCompletionSupported = false;
        }
    }

    private static void exposeCommandMap() {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Croissant.getPlugin().getLogger()
                .severe("[Croissant] Could not expose the server's command map");
            e.printStackTrace();
        }
    }

    /**
     * Returns the server's command map.
     *
     * @return the server's command map
     */
    public static CommandMap getCommandMap() {
        return commandMap;
    }

    /**
     * Returns {@code true} if the plugin is running on a PaperMC server, and
     * {@code false} otherwise.
     * 
     * @return if the plugin is running on a PaperMC server
     */
    @SuppressWarnings("unused")
    public static boolean isPaper() {
        return paper;
    }

    /**
     * Returns {@code true} if the plugin is running on a version of the PaperMC
     * server that supports asynchronous tab-completion, and {@code false}
     * otherwise.
     * 
     * @return if the plugin is running on a version of the PaperMC server that
     *         supports asynchronous tab-completion
     */
    public static boolean isAsyncTabCompletionSupported() {
        return asyncTabCompletionSupported;
    }

}
