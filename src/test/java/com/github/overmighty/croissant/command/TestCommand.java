package com.github.overmighty.croissant.command;

import com.github.overmighty.croissant.Croissant;
import com.github.overmighty.croissant.TestServer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.List;

public class TestCommand extends CroissantCommand {

    private static final JavaPlugin plugin = Mockito.mock(JavaPlugin.class);
    private static final CommandSender mockedSender = Mockito.mock(CommandSender.class);
    private static final CommandHandler handler;

    @SuppressWarnings("WeakerAccess")
    protected boolean ranSuccessfully;
    protected Object[] parsedArgs;

    static {
        Bukkit.setServer(new TestServer().getMockedServer());
        Croissant.setPlugin(plugin);
        handler = new CommandHandler();
    }

    protected TestCommand(String name) {
        super(name);
        super.setHandler(handler);
    }

    protected void execute(String... args) {
        super.execute(mockedSender, super.getName(), args);
    }

    @SuppressWarnings("WeakerAccess")
    protected void executeAs(CommandSender sender, String... args) {
        super.execute(sender, super.getName(), args);
    }

    protected List<String> tabComplete(String... args) {
        return super.tabComplete(mockedSender, super.getName(), args);
    }

    @BeforeEach
    void resetExecutionResults() {
        this.ranSuccessfully = false;
        this.parsedArgs = null;
    }

}
