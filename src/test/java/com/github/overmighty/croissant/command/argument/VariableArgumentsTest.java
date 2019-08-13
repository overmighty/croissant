package com.github.overmighty.croissant.command.argument;

import com.github.overmighty.croissant.command.CommandExecutor;
import com.github.overmighty.croissant.command.TestCommand;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class VariableArgumentsTest extends TestCommand {

    VariableArgumentsTest() {
        super("varargs");
    }

    @CommandExecutor
    void run(CommandSender sender, Boolean... args) {
        super.parsedArgs = args;
    }

    @ParameterizedTest(name = "Test the parsing of variable arguments ({index}/5)")
    @MethodSource
    void testVariableArgumentsParsing(String[] args, Object[] expected) {
        super.execute(args);
        Assertions.assertArrayEquals(expected, super.parsedArgs);
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> testVariableArgumentsParsing() {
        return Stream.of(
            Arguments.arguments(
                new String[0],
                null
            ),
            Arguments.arguments(
                new String[] { "abc" },
                null
            ),
            Arguments.arguments(
                new String[] { "true" },
                new Object[] { true }
            ),
            Arguments.arguments(
                new String[] { "true", "false", "true" },
                new Object[] { true, false, true }
            ),
            Arguments.arguments(
                new String[] { "true", "false", "true", "abc" },
                null
            )
        );
    }

    @ParameterizedTest(name = "Test the tab-completion of variable arguments ({index}/3)")
    @MethodSource
    void testVariableArgumentsCompletion(String[] args, List<String> expected) {
        Assertions.assertEquals(expected, super.tabComplete(args));
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> testVariableArgumentsCompletion() {
        return Stream.of(
            Arguments.arguments(
                new String[] { "" },
                Arrays.asList("true", "false")
            ),
            Arguments.arguments(
                new String[] { "true", "" },
                Arrays.asList("true", "false")
            ),
            Arguments.arguments(
                new String[] { "true", "false", "" },
                Arrays.asList("true", "false")
            )
        );
    }

}
