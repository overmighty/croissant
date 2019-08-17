package com.github.overmighty.croissant.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A method of a command class annotated with {@code CommandExecutor} is the
 * command class's executor method, which is called to execute the command.
 * <p>
 * A command class's executor method must take at least one parameter, which
 * is the {@link org.bukkit.command.CommandSender} that issued the command call.
 * If the command is player-only, the parameter may be of the
 * {@link org.bukkit.entity.Player} type to avoid casts, for example. The method
 * can take extra parameters, which will be treated as command arguments:
 * argument parsing and tab-completion will be managed by the command framework,
 * based on the types and annotations of those extra parameters, and on the
 * {@link com.github.overmighty.croissant.command.argument.ArgumentType} that
 * the command's {@link CommandHandler} binds to the type of each one of those
 * parameters (see {@link CommandHandler#getArgumentTypes()}). If a parameter is
 * of a primitive type, the {@code ArgumentType} bound to the wrapper class for
 * that type will be retrieved.
 * <p>
 * By default, arguments are required, which means that if they are not provided
 * when calling the command, the command's usage message is sent to the command
 * sender and the command's execution is aborted, but arguments can be made
 * optional by annotating them with
 * {@link com.github.overmighty.croissant.command.argument.Optional} or
 * {@link com.github.overmighty.croissant.command.argument.Default}.
 * <p>
 * Executor methods may also take varargs. In this case, each remaining argument
 * provided by the command sender will be resolved into the varargs parameter's
 * component type and put into an array, which will be passed to the method.
 * However, the varargs parameter must not be of a primitive component type, or
 * runtime exceptions will occur when executing the command. If one of those
 * types is wanted, the wrapper class for it, for example {@link Boolean} for
 * {@code boolean}, {@link Integer} for {@code int}, or {@link Double} for
 * {@code double}, must be used.
 *
 * @see CroissantCommand
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CommandExecutor {
}
