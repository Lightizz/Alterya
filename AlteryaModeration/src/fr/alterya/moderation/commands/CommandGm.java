package fr.alterya.moderation.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import fr.alterya.moderation.MainModeration;


public class CommandGm implements CommandExecutor, Listener{

	public MainModeration main;
	
	public CommandGm(MainModeration main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		 Player player = (Player)sender;
		 
		 if(label.equalsIgnoreCase("gm") && main.rank.config.getInt(player.getUniqueId().toString()) >= 7){
			 if(args.length == 0) {
				 player.sendMessage(ChatColor.GOLD + "[" + ChatColor.GREEN + "GM" + ChatColor.GOLD + "]" + ChatColor.GRAY + " /gm" + " §7[§a 0 §7/§a 1 §7/ §a2 §7]");
			 }
			 if (args.length == 1) {
				 if (args [0].equalsIgnoreCase("0")) {
					 player.setGameMode(GameMode.SURVIVAL);
					 player.sendMessage(ChatColor.GOLD + "[" + ChatColor.GREEN + "GM" + ChatColor.GOLD + "]" + " »" + ChatColor.WHITE + " Mode Survie :" + ChatColor.GREEN + " 0");
					 player.playSound(player.getLocation(), Sound.NOTE_PIANO, 3.0F, 1.0F);
				 }
				 if(args[0].equalsIgnoreCase("1")) {
					 player.setGameMode(GameMode.CREATIVE);
					 player.sendMessage(ChatColor.GOLD + "[" + ChatColor.GREEN + "GM" + ChatColor.GOLD + "]" + " »" + ChatColor.WHITE + " Mode Créatif :" + ChatColor.GREEN + " 1");
					 player.playSound(player.getLocation(), Sound.NOTE_PIANO, 3.0F, 1.0F);
				 }
				 if(args[0].equalsIgnoreCase("2")) {
					 player.setGameMode(GameMode.ADVENTURE);
					 player.sendMessage(ChatColor.GOLD + "[" + ChatColor.GREEN + "GM" + ChatColor.GOLD + "]" + " »" + ChatColor.WHITE + " Mode Aventure :" + ChatColor.GREEN + " 2");
					 player.playSound(player.getLocation(), Sound.NOTE_PIANO, 3.0F, 1.0F);
			 		}
		 		}
		 }
	return false;
	}
}
