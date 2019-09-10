package com.github.overmighty.croissant.command.argument;

/**
 * Resolves raw command arguments of a certain expected type into appropriate
 * objects.
 *
 * @param <T> the type of objects to resolve raw command arguments into
 * @see ArgumentType
 */
@FunctionalInterface
public interface ArgumentResolver<T> {

    /**
     * Resolves a raw command argument.
     *
     * @param argument the argument to resolve
     * @return the resolved argument, or {@code null} if it could not be
     *         resolved
     */
    T resolve(Argument argument);

}
