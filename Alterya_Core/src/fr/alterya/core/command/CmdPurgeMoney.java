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

public class CmdPurgeMoney implements CommandExecutor
{	
	@SuppressWarnings("unused")
	private final Rank rank;
	public MainCore main;
	
	public CmdPurgeMoney(Rank rank, MainCore main) {
		this.rank = rank;
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		if(message.equalsIgnoreCase("purgemoney")) {
			Player player = (Player) sender;
			if(sender instanceof Player) {
				if(Rank.config.getInt(player.getUniqueId().toString()) < 8) {
					player.sendMessage(MainCore.prefix + "§4Vous n'êtes pas OP, Administrateur ou Responsable, vous ne pouvez pas effectuer cette commande !");
					return true;
				}
				if(args.length != 1) {
					player.sendMessage(MainCore.prefix + "La commande est /purgemoney <joueur>.");
					return true;
				}else if(args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					MoneyManager manager = new MoneyManager(player.getUniqueId().toString());
					
					if(manager.moneyBankExist(target.getUniqueId().toString()) == false) {
						player.sendMessage(MainCore.prefix + "Ce joueur est introuvable.");
					}
					
					manager.purgeMoney(target.getUniqueId().toString());
					player.sendMessage(MainCore.prefix + "La money de " + target.getName() + " a été purgée.");
					MainCore.log(LogType.INFO, "La money de " + target.getName() + " a été purgée par " + player.getDisplayName() + ".");
					return true;
				}
			}else if(!(sender instanceof Player)){
				player.sendMessage(MainCore.prefix + "§4Vous devez être un joueur pour effectuer cette commande.");
				return true;
			}
		}
		return false;
	}
}
