package fr.alterya.core.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.alterya.core.Main;
import fr.alterya.core.rank.Rank;
import fr.alterya.core.rank.RankList;
import net.minecraft.server.v1_7_R4.EntityPlayer;

public class BasicsPlayerCommands implements CommandExecutor, Listener {
	
	Rank rank;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args){		
			if(command.getName().equalsIgnoreCase("ping") && sender instanceof Player){
	            Player player = (Player) sender;
	            player.sendMessage(ChatColor.YELLOW + "Votre ping est : " + ChatColor.GOLD + getPing(player) + "ms");
	            return true;
	        }
	        
	        if(command.getName().equalsIgnoreCase("discord") && sender instanceof Player) {
	            Player player = (Player) sender;
	            player.sendMessage(ChatColor.GOLD + "|>> " + ChatColor.AQUA + "https://discord.gg/rTR4FNF" + ChatColor.GOLD + "<<|" );
	            
	            return true;
	        }
	        
	        if(command.getName().equalsIgnoreCase("tipeee")) {
	            Player player = (Player) sender;
	            player.sendMessage(ChatColor.GOLD + "|>> https://fr.tipeee.com/alterya-pvp <<|");
	            return true;
	        }
	        
	        if(command.getName().equalsIgnoreCase("craft") && sender instanceof Player) {
	            Player player = (Player) sender;
	            player.openWorkbench (null, true);
	            
	            return true;
	        }
	        
	        //Souvenir = 2 lignes
	        //Sage = 3 lignes
	        //Mémoire = 3 lignes
	        //Joueur = 1 lignes
	        if(command.getName().equalsIgnoreCase("ec") && sender instanceof Player) {
	        	Player player = (Player) sender;
	        	
	    		player.openInventory(player.getEnderChest());
	    		return true;
	        }
	    return false;
	}
	
	@EventHandler
	public void onInterract(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if(event.getClickedInventory() == player.getEnderChest()) {
			if(event.getCurrentItem() == new ItemStack(Material.AIR) || event.getCurrentItem() == null 
					&& event.getSlot() == 0
					&& event.getSlot() == 1
					&& event.getSlot() == 2
					&& event.getSlot() == 3
					&& event.getSlot() == 4
					&& event.getSlot() == 5
					&& event.getSlot() == 6
					&& event.getSlot() == 7
					&& event.getSlot() == 8
					&& event.getSlot() == 9
					&& event.getSlot() == 10
					&& event.getSlot() == 11
					&& event.getSlot() == 12
					&& event.getSlot() == 13
					&& event.getSlot() == 14
					&& event.getSlot() == 15
					&& event.getSlot() == 16
					&& event.getSlot() == 17 && rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.JOUEUR) {
				event.setCancelled(true);
				player.sendMessage(Main.prefix + "Vu n'avez pas la permission d'utiliser ces slots.");
			}else if(event.getCurrentItem() == new ItemStack(Material.AIR) || event.getCurrentItem() == null 
					&& event.getSlot() == 0
					&& event.getSlot() == 1
					&& event.getSlot() == 2
					&& event.getSlot() == 3
					&& event.getSlot() == 4
					&& event.getSlot() == 5
					&& event.getSlot() == 6
					&& event.getSlot() == 7
					&& event.getSlot() == 8 && rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.SOUVENIR) {
				event.setCancelled(true);
				player.sendMessage(Main.prefix + "Vu n'avez pas la permission d'utiliser ces slots.");
			}
		}
		event.setCancelled(false);
	}
	
	public double getPing(Player player) 
	{
		CraftPlayer pingC = (CraftPlayer) player;
		EntityPlayer pingE = pingC.getHandle();
		
		return pingE.ping;
	}
}
