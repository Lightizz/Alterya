package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.Main;
import fr.alterya.core.money.MoneyManager;
import fr.alterya.core.rank.Rank;

public class CmdTakeMoney implements CommandExecutor
{
	private final Rank rank;
	public Main main;
	
	public CmdTakeMoney(Rank rank, Main main) {
		this.rank = rank;
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		if(command.getName().equalsIgnoreCase("takemoney")) {
			Player player = (Player) sender;
			if(sender instanceof Player && rank.config.getInt(player.getUniqueId().toString()) >= 8) {
				if(args.length != 2) {
					player.sendMessage(Main.prefix + "La commande est /takemoney <joueur> <montant>.");
					return true;
				}else if(args.length == 2) {
					Player target = Bukkit.getPlayer(args[0]);
					MoneyManager manager = new MoneyManager(player.getUniqueId().toString());
					double amount = Double.valueOf(args[1]);
					
					if(manager.moneyBankExist(target.getUniqueId().toString()) == false) {
						player.sendMessage(Main.prefix + "Ce joueur est introuvable.");
					}
					
					manager.substractMoney(target.getUniqueId().toString(), amount);
					player.sendMessage(Main.prefix + "Vous avez pris " + amount + " $ à " + target.getName() + ".");
					return true;
				}
			}else {
				player.sendMessage(Main.prefix + "§4Vous devez être un joueur pour effectuer cette commande.");
				return true;
			}
		}
		return false;
	}
}
