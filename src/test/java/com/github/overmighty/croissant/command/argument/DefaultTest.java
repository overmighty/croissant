package com.github.overmighty.croissant.command.argument;

import com.github.overmighty.croissant.command.CommandExecutor;
import com.github.overmighty.croissant.command.TestCommand;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class DefaultTest extends TestCommand {

    DefaultTest() {
        super("default");
    }

    @CommandExecutor
    void run(CommandSender sender, @Default("test") String arg1, @Default("0") Integer arg2,
             @Default("true") Boolean arg3) {
        super.parsedArgs = new Object[] { arg1, arg2, arg3 };
    }

    @ParameterizedTest(name = "Test the parsing of @Default arguments ({index}/7)")
    @MethodSource
    void testDefaultArgumentsParsing(String[] args, Object[] expected) {
        super.execute(args);
        Assertions.assertArrayEquals(expected, super.parsedArgs);
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> testDefaultArgumentsParsing() {
        return Stream.of(
            Arguments.arguments(
                new String[0],
                new Object[] { "test", 0, true }
            ),
            Arguments.arguments(
                new String[] { "test" },
                new Object[] { "test", 0, true }
            ),
            Arguments.arguments(
                new String[] { "test", "0" },
                new Object[] { "test", 0, true }
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
