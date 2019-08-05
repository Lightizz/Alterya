package fr.alterya.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.Shop;

/*
Author and resp of the shop: Lightiz
*/

public class CmdShop implements CommandExecutor {
	
	Shop shop;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		Player player = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("shop") && sender instanceof Player) {
			
			shop.setItemsInShopMain();
			shop.setItemInShopBlocks();
			shop.setItemInShopPlants();
			shop.setItemInShopUtils();
			shop.setItemInShopMinerals();
			shop.setItemsInShopSellBuy();
			
			player.openInventory(Shop.shopInvMain);
			return true;
		}
		return false;
	}
}
