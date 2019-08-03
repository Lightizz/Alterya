package fr.alterya.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.alterya.core.Shop;

/*
Author and resp of the shop: Lightiz
*/

public class CmdShop implements CommandExecutor, Listener  {

	Shop shop;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		Player player = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("shop") && sender instanceof Player) {
			player.openInventory(shop.shopInvMain);
			return true;
		}
		return false;
	}
	
	public void onDamage(EntityDamageEvent eventDamage) {
		Player player = (Player) eventDamage.getEntity();
		
		if(player.getOpenInventory() == shop.shopInvBlocks 
			||player.getOpenInventory() == shop.shopInvMain
			||player.getOpenInventory() == shop.shopInvPlants
			||player.getOpenInventory() == shop.shopInvUtils
			||player.getOpenInventory() == shop.shopInvMinerals
			||player.getOpenInventory() == shop.shopInvSellBuy) {
			player.getOpenInventory().close();
		}
	}
}
