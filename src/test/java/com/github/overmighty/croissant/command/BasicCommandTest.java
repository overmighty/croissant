package com.github.overmighty.croissant.command;

import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BasicCommandTest extends TestCommand {

    BasicCommandTest() {
        super("basic");
    }

    @SuppressWarnings("unused")
    @CommandExecutor
    void run(CommandSender sender) {
        super.ranSuccessfully = true;
    }

    @Test
    @DisplayName("Test basic command: expect success")
    void testBasicCommandExpectSuccess() {
        super.execute();
        Assertions.assertTrue(this.ranSuccessfully);
    }

}
