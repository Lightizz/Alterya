package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.MainCore;
import fr.alterya.core.money.MoneyManager;

public class CmdBaltop implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		if(message.equalsIgnoreCase("baltop")) {
			Player player = (Player) sender;
			if(sender instanceof Player) {
				if(args.length != 0) {
					player.sendMessage(MainCore.prefix + "La commande est /baltop.");
					return true;
				}else if(args.length == 0) {
					MoneyManager manager = new MoneyManager(player.getUniqueId().toString());
					
					String[] accountList1 = manager.getMoneyTop(player);
					String message1 = "§4§l->Top 10 money accounts<-\n";
					
					for(String part : accountList1) {
						Player player1 = Bukkit.getPlayer(part);
						message1 = "§2" + player1.getName() + " §r: §e" + manager.getMoney(player1.getUniqueId().toString()) + "§2$\n";
					}
					player.sendMessage(message1);
					return true;
				}
			}else {
				player.sendMessage("§4Vous devez être un joueur pour effectuer cette commande.");
				return true;
			}	
		}
		return false;
	}
}