package com.github.overmighty.croissant.command.argument;

import org.bukkit.command.CommandSender;

import java.lang.reflect.Parameter;

/**
 * A command argument.
 */
public class Argument {

    private final Parameter parameter;
    private final String value;
    private final CommandSender sender;

    /**
     * Constructs a new {@code Argument}.
     *
     * @param parameter the parameter that represents the argument in the
     *                  command's executor method
     * @param value     the raw value for the argument
     * @param sender    the command sender that provided the value
     */
    public Argument(Parameter parameter, String value, CommandSender sender) {
        this.parameter = parameter;
        this.value = value;
        this.sender = sender;
    }

    /**
     * Returns the parameter that represents the argument in the command's
     * executor method.
     *
     * @return the parameter that represents the argument
     */
    @SuppressWarnings("unused")
    public Parameter getParameter() {
        return parameter;
    }

    /**
     * Returns the raw value for the argument.
     *
     * @return the argument's value
     */
    @SuppressWarnings("WeakerAccess")
    public String getValue() {
        return value;
    }

    /**
     * Returns the command sender that provided the raw value for the argument.
     *
     * @return the command sender that provided the value
     */
    public CommandSender getSender() {
        return sender;
    }

}
