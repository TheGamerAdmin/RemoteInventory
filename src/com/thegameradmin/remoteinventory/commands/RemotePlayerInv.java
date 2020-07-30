package com.thegameradmin.remoteinventory.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemotePlayerInv implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) { return true; }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("remoteplayerinv")) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "Please use this format: /remoteplayerinv <player>");
                return true;
            }
            Player otherPlayer = Bukkit.getServer().getPlayerExact(args[0]);
            if (otherPlayer == null) {
                player.sendMessage(ChatColor.RED + "Error: Player name (" + args[0] + ") is invalid! Is the player online?");
                return true;
            }
            player.openInventory(otherPlayer.getInventory());
        }

        return true;
    }
}