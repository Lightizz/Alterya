package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.Main;
import fr.alterya.core.money.MoneyManager;
import fr.alterya.core.rank.Rank;

public class CmdPurgeMoney implements CommandExecutor
{	
	private final Rank rank;
	public Main main;
	
	public CmdPurgeMoney(Rank rank, Main main) {
		this.rank = rank;
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		if(command.getName().equalsIgnoreCase("purgemoney")) {
			Player player = (Player) sender;
			if(sender instanceof Player && rank.config.getInt(player.getUniqueId().toString()) >= 8) {
				if(args.length != 1) {
					player.sendMessage(Main.prefix + "La commande est /purgemoney <joueur>.");
					return true;
				}else if(args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					MoneyManager manager = new MoneyManager(player.getUniqueId().toString());
					
					if(manager.moneyBankExist(target.getUniqueId().toString()) == false) {
						player.sendMessage(Main.prefix + "Ce joueur est introuvable.");
					}
					
					manager.purgeMoney(target.getUniqueId().toString());
					player.sendMessage(Main.prefix + "La money de " + target.getName() + " a été purger.");
					return true;
				}
			}else if(!(sender instanceof Player)){
				player.sendMessage(Main.prefix + "§4Vous devez être un joueur pour effectuer cette commande.");
				return true;
			}
		}
		return false;
	}
}
