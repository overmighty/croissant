package com.github.overmighty.croissant.command.argument;

import com.github.overmighty.croissant.command.CommandExecutor;
import com.github.overmighty.croissant.command.TestCommand;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class OptionalTest extends TestCommand {

    OptionalTest() {
        super("optional");
    }

    @CommandExecutor
    void run(CommandSender sender, @Optional String arg1, @Optional Integer arg2,
             @Optional Boolean arg3) {
        super.parsedArgs = new Object[] { arg1, arg2, arg3 };
    }

    @ParameterizedTest(name = "Test the parsing of @Optional arguments ({index}/7)")
    @MethodSource
    void testOptionalArgumentsParsing(String[] args, Object[] expected) {
        super.execute(args);
        Assertions.assertArrayEquals(expected, super.parsedArgs);
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> testOptionalArgumentsParsing() {
        return Stream.of(
            Arguments.arguments(
                new String[0],
                new Object[] { null, null, null }
            ),
            Arguments.arguments(
                new String[] { "test" },
                new Object[] { "test", null, null }
            ),
            Arguments.arguments(
                new String[] { "test", "0" },
                new Object[] { "test", 0, null }
            ),
            Arguments.arguments(
                new String[] { "test", "0", "abc" },
                null
            ),
            Arguments.arguments(
                new String[] { "test", "x", "true" },
                null
            ),
            Arguments.arguments(
                new String[] { "test", "0", "true" },
                new Object[] { "test", 0, true }
            ),
            Arguments.arguments(
                new String[] { "croissant", "123", "false" },
                new Object[] { "croissant", 123, false }
            )
        );
    }

}
