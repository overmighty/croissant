package com.github.overmighty.croissant.command.argument;

import com.github.overmighty.croissant.command.CommandExecutor;
import com.github.overmighty.croissant.command.TestCommand;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class RestTest extends TestCommand {

    RestTest() {
        super("rest");
    }

    @CommandExecutor
    void run(CommandSender sender, @Rest String message) {
        super.parsedArgs = new Object[] { message };
    }

    @ParameterizedTest(name = "Test the parsing of @Rest arguments ({index}/4)")
    @MethodSource
    void testRestArgumentsParsing(String[] args, Object[] expected) {
        super.execute(args);
        Assertions.assertArrayEquals(expected, super.parsedArgs);
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> testRestArgumentsParsing() {
        return Stream.of(
            Arguments.arguments(
                new String[0],
                null
            ),
            Arguments.arguments(
                new String[] { "test" },
                new Object[] { "test" }
            ),
            Arguments.arguments(
                new String[] { "Hello,", "World!" },
                new Object[] { "Hello, World!" }
            ),
            Arguments.arguments(
                new String[] { "Yet", "another", "JUnit", "test" },
                new Object[] { "Yet another JUnit test" }
            )
        );
    }

    @ParameterizedTest(name = "Test the tab-completion of @Rest arguments ({index}/3)")
    @MethodSource
    void testRestArgumentsCompletion(String[] args, List<String> expected) {
        Assertions.assertEquals(expected, super.tabComplete(args));
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> testRestArgumentsCompletion() {
        return Stream.of(
            Arguments.arguments(
                new String[] { "" },
                Collections.singletonList("OverMighty")
            ),
            Arguments.arguments(
                new String[] { "test1", "" },
                Collections.singletonList("OverMighty")
            ),
            Arguments.arguments(
                new String[] { "test1", "test2", "" },
                Collections.singletonList("OverMighty")
            )
        );
    }

}
