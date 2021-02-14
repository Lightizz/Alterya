package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.MainCore;
import fr.alterya.core.money.MoneyManager;
import fr.alterya.core.rank.Rank;

public class CmdMoney implements CommandExecutor
{
	@SuppressWarnings("unused")
	private Rank rank;
	
	public CmdMoney(Rank rank) {
		this.rank = rank;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		if(message.equalsIgnoreCase("money") && sender instanceof Player) {
			Player player = (Player) sender;
			MoneyManager manager = new MoneyManager(player.getUniqueId().toString());
			if(args.length == 1 && Rank.config.getInt(player.getUniqueId().toString()) >= 8) {
				Player target = Bukkit.getPlayer(args[0]);
				if(manager.moneyBankExist(target.getUniqueId().toString()) == true) {
					player.sendMessage(MainCore.prefix + "§2Money de " + target.getName() + ": §e" + manager.getMoney(target.getUniqueId().toString()) + " $");
					return true;
				}if(manager.moneyBankExist(target.getUniqueId().toString()) == false) {
					player.sendMessage(MainCore.prefix + "Ce joueur est introuvable.");
				}
			}else if(args.length == 0){
				player.sendMessage("§2Money: §e" + manager.getMoney(player.getUniqueId().toString()) + " $");
				return true;
			}
		}
		return false;
	}
}

