package fr.alterya.core.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.Main;
import fr.alterya.core.shop.Shop;

public class CmdShop implements CommandExecutor
{
	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{	
		if(sender instanceof Player && command.getName().equalsIgnoreCase("shop")) {
			initShop();
			Player player = (Player) sender;
			Shop shop = this.initShop();
			player.openInventory(shop.shopInvMain);
			return true;
		}else if(!(sender instanceof Player)) {
			Player player = (Player) sender;
			player.sendMessage(Main.prefix + ChatColor.RED + "Vous devez être un joueur pour effectuer cette commande.");
			return true;
		}else if(args.length != 0) {
			Player player = (Player)sender;
			player.sendMessage(Main.prefix + ChatColor.AQUA + "La commande est /shop.");
			return true;
 		}
		return false;
	}
	
	public Shop initShop() {
		return new Shop();
	}
}
