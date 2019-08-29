package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.MainCore;
import fr.alterya.core.money.MoneyManager;
import fr.alterya.core.rank.Rank;

public class CmdPay implements CommandExecutor
{
	@SuppressWarnings("unused")
	private final Rank rank;
	
	public CmdPay(Rank rank) {
		this.rank = rank;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		if(message.equalsIgnoreCase("pay")) {
		Player player = (Player) sender;
		if(sender instanceof Player) {
				if(args.length != 2) {
					player.sendMessage(MainCore.prefix + "La commande est /pay <joueur> <montant>.");
					return true;
				}else if(args.length == 2) {
					MoneyManager manager = new MoneyManager(player.getUniqueId().toString());
					Player target = Bukkit.getPlayer(args[0]);
					double amount = Double.valueOf(args[1]);
						
					if(manager.moneyBankExist(target.getUniqueId().toString()) == false) {
						player.sendMessage(MainCore.prefix + "Ce joueur est introuvable.");
					}else {
						manager.addMoney(target.getUniqueId().toString(), amount);
						manager.substractMoney(player.getUniqueId().toString(), amount);
						player.sendMessage(MainCore.prefix + "§1Vous avez envoyé §e" + amount + " $ à §e" + target.getName() + "§r.");
						target.sendMessage(MainCore.prefix + "§1Vous avez reçu §e " + amount + " $ de §e" + player.getName() + "§r.");
						return true;
					}
				}
			}
		}else {
			Player player = (Player) sender;
			player.sendMessage("§4Vous devez être un joueur pour effectuer cette commande.");
			return true;
		}
		return false;
	}
}
