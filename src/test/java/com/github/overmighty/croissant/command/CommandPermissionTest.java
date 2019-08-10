package com.github.overmighty.croissant.command;

import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CommandPermissionTest extends TestCommand {

    private final CommandSender sender = Mockito.mock(CommandSender.class);

    CommandPermissionTest() {
        super("permission");
        super.setPermission("croissant.test");
    }

    @CommandExecutor
    void run(CommandSender sender) {
        super.ranSuccessfully = true;
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    @DisplayName("Test command without permission: expect failure")
    void testPlayerOnlyCommandAsNonPlayerExpectFailure() {
        Mockito.when(sender.hasPermission(super.getPermission())).thenReturn(false);
        super.executeAs(sender);
        Assertions.assertFalse(super.ranSuccessfully);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    @DisplayName("Test command with permission: expect success")
    void testPlayerOnlyCommandAsPlayerExpectSuccess() {
        Mockito.when(sender.hasPermission(super.getPermission())).thenReturn(true);
        super.executeAs(sender);
        Assertions.assertTrue(super.ranSuccessfully);
    }

}
