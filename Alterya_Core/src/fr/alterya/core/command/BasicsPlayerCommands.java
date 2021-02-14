package fr.alterya.core.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

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
	
	//Récupérer le ping du joueur (en ms)
	public double getPing(Player player) {
		CraftPlayer pingC = (CraftPlayer) player;
		EntityPlayer pingE = pingC.getHandle();
		
		return pingE.ping;
	}
}
