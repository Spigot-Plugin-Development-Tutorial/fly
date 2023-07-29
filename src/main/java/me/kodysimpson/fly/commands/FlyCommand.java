package me.kodysimpson.fly.commands;

import me.kodysimpson.fly.Fly;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.UUID;

public class FlyCommand implements CommandExecutor {

    private final Fly plugin;
    private final HashSet<UUID> flyingPeople = new HashSet<>(); //set of people who are flying

    public FlyCommand(Fly plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args){

        if (sender instanceof Player player){

            //See if the player has permission to run the command
            if (!player.hasPermission("illuminatifly.fly")){
                player.sendMessage(ChatColor.GREEN + "You don't have the " + ChatColor.YELLOW + "illuminatifly.fly " + ChatColor.GREEN + "permission required to use this command.");
                return true;
            }

            //get the on and off messages from the config
            String onMessage = plugin.getConfig().getString("on-message");
            String offMessage = plugin.getConfig().getString("off-message");

            //make sure the messages are not null
            if (onMessage == null || offMessage == null){
                player.sendMessage(ChatColor.RED + "You must set the on-message and off-message in the config.yml file.");
                return true;
            }

            //toggle the player's flight
            if (flyingPeople.contains(player.getUniqueId())){ //if the player is in the set, remove them and turn off flight
                flyingPeople.remove(player.getUniqueId());
                player.setAllowFlight(false); //turn off flight
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', offMessage));
            }else{ //if the player is not in the set, add them and turn on flight
                flyingPeople.add(player.getUniqueId());
                player.setAllowFlight(true); //turn on flight
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', onMessage));
            }

        }else{
            sender.sendMessage("You must be a player to run this command.");
        }

        return true;
    }
}
