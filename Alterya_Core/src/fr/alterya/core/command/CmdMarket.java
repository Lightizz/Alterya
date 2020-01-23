package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.alterya.core.MainCore;
import fr.alterya.core.market.MarketManager;
import net.md_5.bungee.api.ChatColor;

public class CmdMarket implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
	{
		if(msg.equalsIgnoreCase("market")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(args.length == 0) {
					Inventory marketInv = Bukkit.createInventory(null, 6*9, "§0Marché noir");
					player.openInventory(marketInv);
					MarketManager.prepareMarket(marketInv);
					return true;
				}else {
					player.sendMessage(MainCore.prefix + ChatColor.AQUA + "La commande s'utilise comme ça : §e" + cmd.getUsage() + ChatColor.AQUA + " .");
					return true;
				}
			}else {
				sender.sendMessage(MainCore.prefix + ChatColor.AQUA + "Vous devez être un joueur.");
				return true;
			}
		}
		return false;
	}
}
