package com.github.overmighty.croissant.command.argument;

import java.util.List;

/**
 * Suggests completions for partial command arguments of a certain expected
 * type.
 *
 * @see ArgumentType
 */
@FunctionalInterface
public interface ArgumentCompleter {

    /**
     * Returns completion suggestions for a partial command argument.
     *
     * @param argument the argument to complete
     * @return the list of completions to suggest to the command sender
     */
    List<String> complete(Argument argument);

}
