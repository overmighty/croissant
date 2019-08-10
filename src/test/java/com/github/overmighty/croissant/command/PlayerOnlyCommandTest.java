package com.github.overmighty.croissant.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PlayerOnlyCommandTest extends TestCommand {

    PlayerOnlyCommandTest() {
        super("player-only");
        super.setPlayerOnly(true);
    }

    @CommandExecutor
    void run(CommandSender sender) {
        super.ranSuccessfully = true;
    }

    @Test
    @DisplayName("Test player-only command as non-player: expect failure")
    void testPlayerOnlyCommandAsNonPlayerExpectFailure() {
        super.execute();
        Assertions.assertFalse(super.ranSuccessfully);
    }

    @Test
    @DisplayName("Test player-only command as player: expect success")
    void testPlayerOnlyCommandAsPlayerExpectSuccess() {
        Player player = Mockito.mock(Player.class);
        super.executeAs(player);
        Assertions.assertTrue(super.ranSuccessfully);
    }

}
