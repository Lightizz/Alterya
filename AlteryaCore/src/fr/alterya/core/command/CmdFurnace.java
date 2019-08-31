package fr.alterya.core.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.alterya.core.MainCore;
import fr.alterya.core.util.ItemBuilder;

public class CmdFurnace implements CommandExecutor
{
	MainCore mainCore;
	
	public CmdFurnace(MainCore mainCore) {
		this.mainCore = mainCore;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		Player player = (Player) sender;
		if(message.equalsIgnoreCase("furnace")) {
			if(mainCore.rank.config.getInt(player.getUniqueId().toString()) != 3) { player.sendMessage(MainCore.prefix + "Vous n'avez pas le grade suffisant pour effectuer cette commande"); return true; }
			if(player.getItemInHand().getType() == Material.COAL_ORE) {
				int amount = player.getItemInHand().getAmount();
				ItemStack itemToReplace = ItemBuilder.createItem(Material.COAL);
				itemToReplace.setAmount(amount);
				player.setItemInHand(itemToReplace);
				return true;
			}
			if(player.getItemInHand().getType() == Material.DIAMOND_ORE) {
				int amount = player.getItemInHand().getAmount();
				ItemStack itemToReplace = ItemBuilder.createItem(Material.DIAMOND);
				itemToReplace.setAmount(amount);
				player.setItemInHand(itemToReplace);
				return true;
			}
			if(player.getItemInHand().getType() == Material.EMERALD_ORE) {
				int amount = player.getItemInHand().getAmount();
				ItemStack itemToReplace = ItemBuilder.createItem(Material.EMERALD);
				itemToReplace.setAmount(amount);
				player.setItemInHand(itemToReplace);
				return true;
			}
			if(player.getItemInHand().getType() == Material.REDSTONE_ORE) {
				int amount = player.getItemInHand().getAmount();
				ItemStack itemToReplace = ItemBuilder.createItem(Material.REDSTONE);
				itemToReplace.setAmount(amount);
				player.setItemInHand(itemToReplace);
				return true;
			}
			if(player.getItemInHand().getType() == Material.GOLD_ORE) {
				int amount = player.getItemInHand().getAmount();
				ItemStack itemToReplace = ItemBuilder.createItem(Material.GOLD_INGOT);
				itemToReplace.setAmount(amount);
				player.setItemInHand(itemToReplace);
				return true;
			}
			if(player.getItemInHand().getType() == Material.RAW_CHICKEN) {
				int amount = player.getItemInHand().getAmount();
				ItemStack itemToReplace = ItemBuilder.createItem(Material.COOKED_CHICKEN);
				itemToReplace.setAmount(amount);
				player.setItemInHand(itemToReplace);
				return true;
			}
			if(player.getItemInHand().getType() == Material.RAW_BEEF) {
				int amount = player.getItemInHand().getAmount();
				ItemStack itemToReplace = ItemBuilder.createItem(Material.COOKED_BEEF);
				itemToReplace.setAmount(amount);
				player.setItemInHand(itemToReplace);
				return true;
			}
			if(player.getItemInHand().getType() == Material.RAW_FISH) {
				int amount = player.getItemInHand().getAmount();
				ItemStack itemToReplace = ItemBuilder.createItem(Material.COOKED_FISH);
				itemToReplace.setAmount(amount);
				player.setItemInHand(itemToReplace);
				return true;
			}
			if(player.getItemInHand().getType() == Material.PORK) {
				int amount = player.getItemInHand().getAmount();
				ItemStack itemToReplace = ItemBuilder.createItem(Material.GRILLED_PORK);
				itemToReplace.setAmount(amount);
				player.setItemInHand(itemToReplace);
				return true;
			}
			if(player.getItemInHand().getType() == Material.POTATO_ITEM) {
				int amount = player.getItemInHand().getAmount();
				ItemStack itemToReplace = ItemBuilder.createItem(Material.BAKED_POTATO);
				itemToReplace.setAmount(amount);
				player.setItemInHand(itemToReplace);
				return true;
			}
		}
		return false;
	}
}
