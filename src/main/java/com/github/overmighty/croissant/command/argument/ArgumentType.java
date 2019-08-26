package com.github.overmighty.croissant.command.argument;

import java.util.Collections;

/**
 * A type of command arguments.
 *
 * @see com.github.overmighty.croissant.command.CroissantCommand
 */
public class ArgumentType {

    private static final ArgumentCompleter DEFAULT_COMPLETER = argument -> Collections.emptyList();

    private final ArgumentResolver<?> resolver;
    private final ArgumentCompleter completer;
    private String errorMessage;

    /**
     * Constructs a new {@code ArgumentType}.
     *
     * @param resolver  the type's argument resolver
     * @param completer the type's argument completer
     */
    @SuppressWarnings("WeakerAccess")
    public ArgumentType(ArgumentResolver<?> resolver, ArgumentCompleter completer) {
        this.resolver = resolver;
        this.completer = completer;
    }

    /**
     * Constructs a new {@code ArgumentType} that does not handle tab-completion
     * of arguments.
     *
     * @param resolver the type's argument resolver
     */
    @SuppressWarnings("WeakerAccess")
    public ArgumentType(ArgumentResolver<?> resolver) {
        this(resolver, DEFAULT_COMPLETER);
    }

    /**
     * Returns the type's argument resolver.
     *
     * @return the type's argument resolver
     */
    public ArgumentResolver<?> getResolver() {
        return resolver;
    }

    /**
     * Returns the type's argument completer.
     *
     * @return the type's argument resolver
     */
    public ArgumentCompleter getCompleter() {
        return completer;
    }

    /**
     * Returns the type's error message to send to command senders when an
     * argument could not be resolved, instead of the command's usage message.
     *
     * @return the type's error message; can be {@code null}
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the type's error message to send to command senders when an argument
     * could not be resolved, instead of the command's usage message.
     * <p>
     * By default, this is set to {@code null} and the command's usage message
     * is sent to command senders when they provide an invalid argument.
     *
     * @param errorMessage the type's error message; use {@code {value}} as a
     *                     macro for the value that was provided by the command
     *                     sender
     */
    @SuppressWarnings("unused")
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
