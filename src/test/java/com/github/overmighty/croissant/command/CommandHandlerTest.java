package com.github.overmighty.croissant.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class CommandHandlerTest extends TestCommand {

    CommandHandlerTest() {
        super("command-handler");
    }

    @CommandExecutor
    void run(CommandSender sender, Player player, String string, boolean bool) {
    }

    @ParameterizedTest(name = "Test CommandHandler.setPlayerCompleterEnabled(boolean) ({index}/6)")
    @MethodSource
    void testSetPlayerCompleterEnabled(boolean enabled, String[] args, List<String> expected) {
        super.getHandler().setPlayerCompleterEnabled(enabled);
        Assertions.assertEquals(expected, super.tabComplete(args));
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> testSetPlayerCompleterEnabled() {
        return Stream.of(
            Arguments.arguments(
                true,
                new String[] { "" },
                Collections.singletonList("OverMighty")
            ),
            Arguments.arguments(
                true,
                new String[] { "test", "" },
                Collections.singletonList("OverMighty")
            ),
            Arguments.arguments(
                true,
                new String[] { "test", "test", "" },
                Arrays.asList("true", "false")
            ),
            Arguments.arguments(
                false,
                new String[] { "" },
                Collections.emptyList()
            ),
            Arguments.arguments(
                false,
                new String[] { "test", "" },
                Collections.emptyList()
            ),
            Arguments.arguments(
                true,
                new String[] { "test", "test", "" },
                Arrays.asList("true", "false")
            )
        );
    }

}
