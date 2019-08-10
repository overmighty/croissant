package com.github.overmighty.croissant;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TestServer {

    private final Server mockedServer = Mockito.mock(Server.class);

    public TestServer() {
        Logger mockedLogger = Mockito.mock(Logger.class);
        PluginManager mockedPluginManager = Mockito.mock(PluginManager.class);
        Mockito.when(this.mockedServer.getLogger()).thenReturn(mockedLogger);
        Mockito.when(this.mockedServer.getPluginManager()).thenReturn(mockedPluginManager);
        this.initOnlinePlayerList();
    }

    private void initOnlinePlayerList() {
        Player mockedPlayer = Mockito.mock(Player.class);
        Mockito.when(mockedPlayer.getName()).thenReturn("OverMighty");

        List<Player> onlinePlayers = new ArrayList<>();
        onlinePlayers.add(mockedPlayer);
        Mockito.doReturn(onlinePlayers).when(mockedServer).getOnlinePlayers();
    }

    public Server getMockedServer() {
        return mockedServer;
    }

}
