package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.Main;
import fr.alterya.core.money.MoneyManager;
import fr.alterya.core.rank.Rank;

public class CmdMoney implements CommandExecutor
{
	private final Rank rank;
	
	public CmdMoney(Rank rank) {
		this.rank = rank;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		if(command.getName().equalsIgnoreCase("money") && sender instanceof Player) {
			Player player = (Player) sender;
			MoneyManager manager = new MoneyManager(player.getUniqueId().toString());
				if(args.length == 1 && rank.config.getInt(player.getUniqueId().toString()) >= 8) {
					Player target = Bukkit.getPlayer(args[0]);
					if(manager.moneyBankExist(target.getUniqueId().toString()) == true) {
						player.sendMessage(Main.prefix + "§2Money de " + target.getName() + ": §e" + manager.getMoney(target.getUniqueId().toString()) + " $");
						return true;
					}if(manager.moneyBankExist(target.getUniqueId().toString()) == false) {
						player.sendMessage(Main.prefix + "Ce joueur est introuvable.");
					}
				}else if(args.length == 0){
					player.sendMessage("§2Money: §e" + manager.getMoney(player.getUniqueId().toString()) + " $");
					return true;
				}
			}
		return false;
	}
}

