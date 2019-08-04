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

import fr.alterya.core.Main;
import fr.alterya.rank.Rank;
import fr.alterya.rank.RankList;
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
	            player.sendMessage(ChatColor.GOLD + ">> " + ChatColor.AQUA + "https://discord.gg/rTR4FNF" + ChatColor.GOLD + "<<" );
	            
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
	            if(rank.getPlayerRank(player) == RankList.SOUVENIR) {
	            	
	            }else if(rank.getPlayerRank(player) == RankList.SAGE) {
	            	
	            }else if(rank.getPlayerRank(player) == RankList.MEMOIRE) {
	            	
	            }else if(rank.getPlayerRank(player) == RankList.JOUEUR) {
	            	
	            }
	            player.openInventory(player.getEnderChest());
	            return true;
	        }
	        return false;
	}
	
	@EventHandler
	public void onInterract(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if(player.getEnderChest().getContents().length == 3*9 && rank.getPlayerRank(player) == RankList.SOUVENIR && event.getClickedInventory() == player.getEnderChest()) {
			if(event.getSlot() == 26 
					||event.getSlot() == 27
					||event.getSlot() == 25
					||event.getSlot() == 24
					||event.getSlot() == 23
					||event.getSlot() == 22
					||event.getSlot() == 21
					||event.getSlot() == 20
					||event.getSlot() == 19
					||event.getSlot() == 18) {
				event.setCancelled(true);
				player.sendMessage(Main.prefix + ChatColor.RED + "Vous n'avez pas le grade suffisant pour avoir accès à cette bar de votre ender chest !");
			}else {
				event.setCancelled(false);
			}
		}else if(player.getEnderChest().getContents().length == 3*9 && rank.getPlayerRank(player) == RankList.JOUEUR && event.getClickedInventory() == player.getEnderChest()) {
			if(event.getSlot() == 26 
					||event.getSlot() == 27
					||event.getSlot() == 25
					||event.getSlot() == 24
					||event.getSlot() == 23
					||event.getSlot() == 22
					||event.getSlot() == 21
					||event.getSlot() == 20
					||event.getSlot() == 19
					||event.getSlot() == 18
					||event.getSlot() == 17
					||event.getSlot() == 16
					||event.getSlot() == 15
					||event.getSlot() == 14
					||event.getSlot() == 13
					||event.getSlot() == 12
					||event.getSlot() == 11
					||event.getSlot() == 10
					||event.getSlot() == 9) {
				event.setCancelled(true);
				player.sendMessage(Main.prefix + ChatColor.RED + "Vous n'avez pas le grade suffisant pour avoir accès à cette bar de votre ender chest !");
			}else {
				event.setCancelled(false);
			}
		}
	}
	
	public double getPing(Player player) 
	{
		CraftPlayer pingC = (CraftPlayer) player;
		EntityPlayer pingE = pingC.getHandle();
		
		return pingE.ping;
	}
	
	public int getMoney(Player player, int money) 
	{
		return money;
	}
}
