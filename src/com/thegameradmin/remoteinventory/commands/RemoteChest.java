package com.thegameradmin.remoteinventory.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoteChest implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) { return true; }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("remotechest")) {
            if (args.length <= 2) {
                player.sendMessage(ChatColor.RED + "Please use the correct format: /remotechest <x> <y> <z> [world]");
                return true;
            }
            int x;
            int y;
            int z;
            try {
                x = Integer.parseInt(args[0]);
                y = Integer.parseInt(args[1]);
                z = Integer.parseInt(args[2]);
            } catch (Exception e) {
                player.sendMessage(ChatColor.RED + "Error: Failed to parse coordinates! Did you use a non-integer character?");
                return true;
            }

            World chestWorld = player.getWorld();
            if (args.length >= 4) {
                if (Bukkit.getServer().getWorld(args[3]) != null) {
                    chestWorld = Bukkit.getServer().getWorld(args[3]);
                } else {
                    player.sendMessage(ChatColor.RED + "Error: World name (" + args[3] + ") is invalid! Did you use the wrong capitalization?");
                    return true;
                }
            }
            Location chestLocation = new Location(chestWorld, x, y, z);
            if (chestLocation.getBlock().getState() instanceof DoubleChest) {
                DoubleChest doubleChest = ((DoubleChest) chestLocation.getBlock().getState());
                Chest leftChest = (Chest) doubleChest.getLeftSide();
                Chest rightChest = (Chest) doubleChest.getRightSide();
                player.openInventory(doubleChest.getInventory());
                return true;
            } else if (chestLocation.getBlock().getState() instanceof Chest) {
                Chest chestToOpen = (Chest) chestLocation.getBlock().getState();
                player.openInventory(chestToOpen.getInventory());
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "Error: No chest found, did you mean to open it in a different world?");
                return true;
            }
        }

        return true;
    }
}
