package com.github.overmighty.croissant.command.argument;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A command executor method parameter annotated with {@code Default} indicates,
 * except for the first parameter which must be the
 * {@link org.bukkit.command.CommandSender}, an optional command argument with a
 * default value.
 *
 * @see Optional
 * @see com.github.overmighty.croissant.command.CommandExecutor
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Default {

    /**
     * Returns the default value for the command argument if it wasn't provided
     * by the command sender.
     *
     * @return the command argument's default value
     */
    String value();

}
