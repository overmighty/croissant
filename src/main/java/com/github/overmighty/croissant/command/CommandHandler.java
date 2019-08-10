package com.github.overmighty.croissant.command;

import com.destroystokyo.paper.event.server.AsyncTabCompleteEvent;
import com.github.overmighty.croissant.Croissant;
import com.github.overmighty.croissant.command.argument.ArgumentType;
import com.github.overmighty.croissant.command.argument.BuiltInArgumentType;
import com.github.overmighty.croissant.util.ServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles {@link CroissantCommand}s.
 */
public class CommandHandler implements Listener {

    private final Map<Class<?>, ArgumentType> argumentTypes = new HashMap<>();
    private String playerOnlyMessage = ChatColor.RED + "This command can only be run by players.";
    private String usageMessagePrefix = ChatColor.RED + "Usage: ";
    private boolean playerCompleterEnabled = true;

    /**
     * Constructs a new {@code CommandHandler}. Its map of argument types will
     * be initialized to contain all constants of the
     * {@link BuiltInArgumentType} enum.
     */
    public CommandHandler() {
        for (BuiltInArgumentType type : BuiltInArgumentType.values()) {
            this.argumentTypes.put(type.getParameterType(), type.getArgumentType());
        }

        if (ServerUtil.isAsyncTabCompletionSupported()) {
            Bukkit.getPluginManager().registerEvents(this, Croissant.getPlugin());
        }
    }

    /**
     * Returns the command handler's argument types, mapped by the type of
     * executor method parameters they are bound to.
     *
     * @return the command handler's argument types
     */
    @SuppressWarnings("WeakerAccess")
    public Map<Class<?>, ArgumentType> getArgumentTypes() {
        return argumentTypes;
    }

    /**
     * Returns the message to send to non-player command senders when they try
     * to call a player-only command that is handled by this command handler.
     *
     * @return the command handler's player-only message
     */
    @SuppressWarnings("WeakerAccess")
    public String getPlayerOnlyMessage() {
        return playerOnlyMessage;
    }

    /**
     * Sets the message to send to non-player command senders when they try to
     * call a player-only command that is handled by this command handler.
     *
     * @param playerOnlyMessage the command handler's player-only message
     */
    @SuppressWarnings("unused")
    public void setPlayerOnlyMessage(String playerOnlyMessage) {
        this.playerOnlyMessage = playerOnlyMessage;
    }

    /**
     * Returns the prefix to add to the usage message of commands that are
     * handled by this command handler, when sending it to a command sender that
     * did not provide all of the command's required arguments, or provided an
     * invalid value for an argument of a type that does not have a custom error
     * message set.
     *
     * @return the command handler's usage message prefix
     */
    @SuppressWarnings("WeakerAccess")
    public String getUsageMessagePrefix() {
        return usageMessagePrefix;
    }

    /**
     * Sets the prefix to add to the usage message of commands that are handled
     * by this command handler, when sending it to a command sender that did not
     * provide all of the command's required arguments, or provided an invalid
     * value for an argument of a type that does not have a custom error message
     * set.
     *
     * @param usageMessagePrefix the command handler's usage message prefix
     */
    @SuppressWarnings("unused")
    public void setUsageMessagePrefix(String usageMessagePrefix) {
        this.usageMessagePrefix = usageMessagePrefix;
    }

    /**
     * Sets if {@link BuiltInArgumentType#PLAYER}'s {@code ArgumentCompleter}
     * is enabled for commands that are handled by this command handler or not.
     * Setting this property to {@code false} will also disable tab-completion
     * for arguments of other types that also use
     * {@link BuiltInArgumentType#PLAYER}'s {@code ArgumentCompleter}.
     * <p>
     * By default, this property is set to {@code true}.
     *
     * @param playerCompleterEnabled if {@link BuiltInArgumentType#PLAYER}'s
     *                               {@code ArgumentCompleter} is enabled for
     *                               commands that are handled by this command
     *                               handler
     */
    @SuppressWarnings("unused")
    public void setPlayerCompleterEnabled(boolean playerCompleterEnabled) {
        this.playerCompleterEnabled = playerCompleterEnabled;
    }

    /**
     * Returns {@code true} if {@link BuiltInArgumentType#PLAYER}'s
     * {@code ArgumentCompleter} is enabled for commands that are handled by
     * this command handler, and {@code false} otherwise.
     *
     * @return if {@link BuiltInArgumentType#PLAYER}'s {@code ArgumentCompleter}
     *         is enabled for commands that are handled by this command handler
     */
    @SuppressWarnings("WeakerAccess")
    public boolean isPlayerCompleterEnabled() {
        return playerCompleterEnabled;
    }

    /**
     * Registers a command to the server's command map and sets the command's
     * handler to this {@code CommandHandler}.
     *
     * @param command the command to register
     */
    @SuppressWarnings({ "WeakerAccess", "unused" })
    public void registerCommand(CroissantCommand command) {
        ServerUtil.getCommandMap().register(Croissant.getPlugin().getName(), command);
        command.setHandler(this);
    }

    @EventHandler
    public void onAsyncTabComplete(AsyncTabCompleteEvent event) {
        // Making sure that the command sender is tab-completing a command argument
        if (!event.isCommand() || !event.getBuffer().contains(" ")) {
            return;
        }

        // `.substring(1)` removes the slash at the start of the chat message
        // A negative limit argument is used for the `split` method to prevent
        // it from removing trailing empty strings
        String[] splitBuffer = event.getBuffer().substring(1).split(" +", -1);
        String commandAlias = splitBuffer[0];
        Command command = Bukkit.getCommandMap().getCommand(commandAlias);

        if (!(command instanceof CroissantCommand)) {
            return;
        }

        CroissantCommand croissantCommand = (CroissantCommand) command;

        if (croissantCommand.getHandler() != this) {
            return;
        }

        // splitBuffer[0] is the command alias, all elements after it are command arguments
        String[] args = Arrays.copyOfRange(splitBuffer, 1, splitBuffer.length);

        event.setCompletions(croissantCommand.tabComplete(event.getSender(), commandAlias, args));
        event.setHandled(true);
    }

}
