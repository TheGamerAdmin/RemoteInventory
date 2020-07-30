package com.thegameradmin.remoteinventory;

import com.thegameradmin.remoteinventory.commands.RemoteChest;
import com.thegameradmin.remoteinventory.commands.RemotePlayerInv;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class RemoteInventory extends JavaPlugin {

    @Override
    public void onEnable() {
        RemotePlayerInv remotePlayerInv = new RemotePlayerInv();
        RemoteChest remoteChest = new RemoteChest();
        Objects.requireNonNull(getCommand("remoteplayerinv")).setExecutor(remotePlayerInv);
        Objects.requireNonNull(getCommand("remotechest")).setExecutor(remoteChest);
    }

    @Override
    public void onDisable() {

    }

}