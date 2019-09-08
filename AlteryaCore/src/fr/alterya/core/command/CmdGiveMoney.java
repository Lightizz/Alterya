package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.LogType;
import fr.alterya.core.MainCore;
import fr.alterya.core.money.MoneyManager;
import fr.alterya.core.rank.Rank;

public class CmdGiveMoney implements CommandExecutor
{	
	private final Rank rank;
	public MainCore main;
	
	public CmdGiveMoney(Rank rank, MainCore main) {
		this.rank = rank;
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		if(message.equalsIgnoreCase("givemoney")) {
			Player player = (Player) sender;
			if(sender instanceof Player) {
				if(rank.config.getInt(player.getUniqueId().toString()) < 9) {
					player.sendMessage(MainCore.prefix + "§4Vous n'êtes pas OP, Administrateur ou Responsable, vous ne pouvez pas effectuer cette commande !");
					return true;
				}
				if(args.length != 2) {
					player.sendMessage(MainCore.prefix + "La commande est /givemoney <joueur> <montant>.");
					return true;
				}else if(args.length == 2) {
					Player target = Bukkit.getPlayer(args[0]);
					MoneyManager manager = new MoneyManager(player.getUniqueId().toString());
					double amount = Double.valueOf(args[1]);
					
					//Si le joueur indiqué n'existe pas :
					if(manager.moneyBankExist(target.getUniqueId().toString()) == false) { player.sendMessage(MainCore.prefix + "Ce joueur est introuvable."); return true; }
					
					manager.addMoney(target.getUniqueId().toString(), amount);
					player.sendMessage(MainCore.prefix + target.getName() + " a reçu " + amount + " $.");
					MainCore.log(LogType.INFO, "Le joueur " + target.getDisplayName() + " a reçu " + amount + "$ par " + player.getDisplayName() + ".");
					return true;
				}
			}else {
				player.sendMessage(MainCore.prefix + "§4Vous devez être un joueur pour effectuer cette commande.");
				return true;
			}
		}
		return false;
	}
}
