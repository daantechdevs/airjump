package me.DaanTech.AirJump;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    public void onEnable() {
        this.saveDefaultConfig();
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (label.equalsIgnoreCase("ajreload")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("messages.reload")));
                this.reloadConfig();
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("messages.reload-complete")));
                return true;
            }
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("messages.run-in-console")));
        }
        if (label.equalsIgnoreCase("ajlaunch")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("messages.console-launch")));
                return true;
            }
            final Player player = (Player)sender;
            if (sender.hasPermission("aj.launch")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("messages.provide-number")));
                    return true;
                }
                player.setVelocity(player.getLocation().getDirection().multiply(Integer.parseInt(args[0])).setY(Integer.parseInt(args[0])));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("messages.fly-message")));
                return true;
            }
            else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("messages.access-denied")));
            }
        }
        if (!label.equalsIgnoreCase("aj")) {
            return false;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage("AirJump Version 1.0.1");
            sender.sendMessage("Coded By DaanTech");
            return true;
        }
        if (sender.hasPermission("aj.info")) {
            sender.sendMessage("AirJump Version 1.0.1 By DaanTech");
            return true;
        }
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("messages.access-denied")));
        return true;
    }
}
