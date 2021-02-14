package fr.alterya.moderation.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.alterya.core.rank.Rank;
import fr.alterya.moderation.Main;

public class Commands implements CommandExecutor {

	public Main main;
	
	public Commands(Main main) {
		this.main = main;
	}
	
    @SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	Player player = (Player) sender;
		String prefix = ChatColor.GRAY + "[" + ChatColor.AQUA + "Modo" + ChatColor.GRAY + "]";
        
        if(label.equalsIgnoreCase("admin") && Rank.config.getInt(player.getUniqueId().toString()) >= 9) {
    		ItemStack customban = new ItemStack(Material.PAPER, 1);
    		ItemMeta customM = customban.getItemMeta();
    		customM.setDisplayName("§4Liste des joueurs");
    		customban.setItemMeta(customM);
    		if(player.getInventory().contains(customban) == true) {
    			player.getInventory().remove(customban);
    			player.sendMessage(prefix + " Desactivé");
    			return true;
    		}
    		player.getInventory().addItem(customban);
    		player.updateInventory();
    		player.sendMessage(prefix + " Activé");
    		return true;
        	}
        
        if(label.equalsIgnoreCase("alert") && Rank.config.getInt(player.getUniqueId().toString()) >= 9) {
        	if(args.length == 0) {
    			player.sendMessage("La commande est : /alert <Message>");
    		}
    		
    		if(args.length >= 1) {
    			StringBuilder bc = new StringBuilder();
    			for(String part : args) {
    				bc.append(part + " ");
    			}
    			Bukkit.broadcastMessage(ChatColor.GOLD + "[" + ChatColor.DARK_RED + ChatColor.BOLD + "CONSOLE" + ChatColor.GOLD +"]"+ ChatColor.BOLD + " " + bc.toString());
    			}
        }
        
        if(label.equalsIgnoreCase("rb") && Rank.config.getInt(player.getUniqueId().toString()) >= 10) {
        	Bukkit.broadcastMessage(ChatColor.GOLD + "[" + ChatColor.DARK_RED + ChatColor.BOLD + "CONSOLE" + ChatColor.GOLD +"]"+ ChatColor.BOLD + " Le serveur va redémarrer !!!");
        	Bukkit.shutdown();
        }
        
        if(label.equalsIgnoreCase("clearchat") && Rank.config.getInt(player.getUniqueId().toString()) >= 8) {
        	
        	 Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
             Bukkit.broadcastMessage("");
            
             Bukkit.broadcastMessage(ChatColor.GOLD + "|-------------------+====+-------------------|");
             Bukkit.broadcastMessage(ChatColor.BOLD + "        Le chat est maintenant nettoyé.");
             Bukkit.broadcastMessage(ChatColor.GOLD + "|-------------------+====+-------------------|");
         }
        return false;
    }
}