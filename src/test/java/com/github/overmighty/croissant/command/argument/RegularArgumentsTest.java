package com.github.overmighty.croissant.command.argument;

import com.github.overmighty.croissant.command.CommandExecutor;
import com.github.overmighty.croissant.command.TestCommand;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

class RegularArgumentsTest extends TestCommand {

    RegularArgumentsTest() {
        super("regular-arguments");
    }

    @CommandExecutor
    void run(CommandSender sender, String arg1, int arg2, boolean arg3) {
        super.parsedArgs = new Object[] { arg1, arg2, arg3 };
    }

    @ParameterizedTest(name = "Test the parsing of regular arguments ({index}/7)")
    @MethodSource
    void testRegularArgumentsParsing(String[] args, Object[] expected) {
        super.execute(args);
        Assertions.assertArrayEquals(expected, super.parsedArgs);
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> testRegularArgumentsParsing() {
        return Stream.of(
            Arguments.arguments(new String[0],                        null),
            Arguments.arguments(new String[] { "test" },              null),
            Arguments.arguments(new String[] { "test", "0" },         null),
            Arguments.arguments(new String[] { "test", "0", "abc" },  null),
            Arguments.arguments(new String[] { "test", "x", "true" }, null),
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

    @ParameterizedTest(name = "Test the tab-completion of regular arguments ({index}/4)")
    @MethodSource
    void testRequiredArgumentsCompletion(String[] args, List<String> expected) {
        Assertions.assertEquals(expected, super.tabComplete(args));
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> testRequiredArgumentsCompletion() {
        return Stream.of(
            Arguments.arguments(
                new String[] { "" },
                Collections.singletonList("OverMighty")
            ),
            Arguments.arguments(
                new String[] { "test", "" },
                Collections.emptyList()
            ),
            Arguments.arguments(
                new String[] { "test", "0", "" },
                Arrays.asList("true", "false")
            ),
            Arguments.arguments(
                new String[] { "test", "0", "true", "" },
                Collections.emptyList()
            )
        );
    }

}
