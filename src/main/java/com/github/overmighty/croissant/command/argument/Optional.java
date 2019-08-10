package com.github.overmighty.croissant.command.argument;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A parameter of the executor method of a command class annotated with
 * {@code Optional} indicates, except for the first parameter which must be the
 * {@link org.bukkit.command.CommandSender}, an optional command argument that
 * does not have to be provided by the command sender for the command to be
 * executed.
 * <p>
 * When an optional argument is not provided by a command sender, {@code null}
 * is passed as parameter to the executor method. As a consequence, parameters
 * that represent optional arguments must be of a non-primitive type to avoid
 * runtime exceptions.
 *
 * @see Default
 * @see com.github.overmighty.croissant.command.CroissantCommand
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Optional {
}
