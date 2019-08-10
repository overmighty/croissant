package com.github.overmighty.croissant.command.argument;

import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Suggests completions for partial command arguments of a certain expected
 * type.
 *
 * @see ArgumentType
 */
public interface ArgumentCompleter {

    /**
     * Returns completion suggestions for a partial command argument.
     *
     * @param value  the value of the argument to suggest completions for
     * @param sender the command sender that issued the completion request
     * @return the list of completions to suggest to the command sender
     */
    List<String> complete(String value, CommandSender sender);

}
