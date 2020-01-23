package fr.alterya.core.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.alterya.core.MainCore;
import fr.alterya.core.rank.Rank;
import net.minecraft.server.v1_7_R4.EntityPlayer;

public class BasicsPlayerCommands implements CommandExecutor, Listener {
	
	MainCore m;
	Rank rank;
	
	public BasicsPlayerCommands(MainCore main, Rank r) {
		m = main;
		r = rank;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args) {		
		
		if(message.equalsIgnoreCase("feed") && sender instanceof Player) {
			Player player = (Player) sender;
			if(Rank.config.getInt(player.getUniqueId().toString()) < 3) {
				player.sendMessage(MainCore.prefix + "§eVous n'avez pas le grade requis pour faire cela.");
				return true;
			}
			if(Rank.config.getInt(player.getUniqueId().toString()) >= 3) {
				player.setFoodLevel(20);
				player.sendMessage("§eVotre barre de faim est maintenant au maximum");
				return true;
			}
		}
		
		//	/ping
		if(message.equalsIgnoreCase("ping") && sender instanceof Player) {
			Player player = (Player) sender;
			if(getPing(player) < 0) {
				player.sendMessage(MainCore.prefix + "§4Une erreur est survenue : 3C. Veuillez contacter un staff en donner l'identifiant (Exemple d'ID : 0C)");
				return true;
			}
			//faire un countdown 
			player.sendMessage(ChatColor.YELLOW + "Votre ping : " + ChatColor.GOLD + getPing(player) + " ms");
			return true;
	    }
	        
		//	/discord
		if(message.equalsIgnoreCase("discord")) {
			Player player = (Player) sender;
	        player.sendMessage(ChatColor.GOLD + "|>> " + ChatColor.AQUA + "https://discord.gg/rTR4FNF" + ChatColor.GOLD + "<<|");
	        return true;
	    }
	      
		//	/tipeee
	    if(message.equalsIgnoreCase("tipeee")) {
	    	Player player = (Player) sender;
	    	player.sendMessage(ChatColor.GOLD + "|>> " + ChatColor.AQUA + "https://fr.tipeee.com/alterya-pvp" + ChatColor.GOLD + "<<|");
	        return true;
	    }
	        
	    //	/craft
	    if(message.equalsIgnoreCase("craft") && sender instanceof Player) {
	    	Player player = (Player) sender;
	    	player.openWorkbench(null, true);
	    	return true;
	    }
	    
	    /*
	    /ec		|		/enderchest
	    Souvenir = 2 lignes
	    Sage = 3 lignes
	    Mémoire = 3 lignes
	    Joueur = 1 lignes
	     */
	    if(message.equalsIgnoreCase("ec") && sender instanceof Player) {
	    	Player player = (Player) sender;
	    	if(player.getEnderChest() == null) {
				player.sendMessage(MainCore.prefix + "§4Une erreur est survenue : 2C. Veuillez contacter un staff en donner l'identifiant (Exemple d'ID : 0C)");
				return true;
			}
	    	player.openInventory(player.getEnderChest());
	    	return true;
	    }else if(message.equalsIgnoreCase("enderchest") && sender instanceof Player) {
	    	Player player = (Player) sender;
	    	if(player.getEnderChest() == null) {
				player.sendMessage(MainCore.prefix + "§4Une erreur est survenue : 2C. Veuillez contacter un staff en donner l'identifiant (Exemple d'ID : 0C)");
				return true;
			}
	    	player.openInventory(player.getEnderChest());
	    	return true;
	    }
	    return false;
	}
	
	@EventHandler
	void onInterract(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if(event.getInventory() == player.getEnderChest()) {
			if(event.getSlot() == 0
					|| event.getSlot() == 1
					|| event.getSlot() == 2
					|| event.getSlot() == 3
					|| event.getSlot() == 4
					|| event.getSlot() == 5
					|| event.getSlot() == 6
					|| event.getSlot() == 7
					|| event.getSlot() == 8
					|| event.getSlot() == 9
					|| event.getSlot() == 10
					|| event.getSlot() == 11
					|| event.getSlot() == 12
					|| event.getSlot() == 13
					|| event.getSlot() == 14
					|| event.getSlot() == 15
					|| event.getSlot() == 16
					|| event.getSlot() == 17
					&& Rank.config.getInt(player.getUniqueId().toString()) == 0) {
				event.setCancelled(true);
				player.sendMessage(MainCore.prefix + "Vu n'avez pas la permission d'utiliser ces slots.");
				return;
			}
			if(event.getSlot() == 0
					|| event.getSlot() == 1
					|| event.getSlot() == 2
					|| event.getSlot() == 3
					|| event.getSlot() == 4
					|| event.getSlot() == 5
					|| event.getSlot() == 6
					|| event.getSlot() == 7
					|| event.getSlot() == 8 
					&& Rank.config.getInt(player.getUniqueId().toString()) == 1) {
				event.setCancelled(true);
				player.sendMessage(MainCore.prefix + "Vu n'avez pas la permission d'utiliser ces slots.");
				return;
			}
		}
	}
	
	//Récupérer le ping du joueur (en ms)
	public double getPing(Player player) {
		CraftPlayer pingC = (CraftPlayer) player;
		EntityPlayer pingE = pingC.getHandle();
		
		return pingE.ping;
	}
}
