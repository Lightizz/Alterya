package fr.alterya.core.command;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
				MoneyManager manager = new MoneyManager(player.getUniqueId().toString());
				if(args.length != 0) {
					player.sendMessage(MainCore.prefix + "La commande est /baltop.");
					return true;
				}else if(args.length == 0) {
					
					Set<String> accountList1 = manager.getMoneyBanks();
					if(manager.getMoneyBanks().isEmpty() == true || accountList1.size() < 5) {
						player.sendMessage(MainCore.prefix + "§4Une erreur est survenue : 1C. Veuillez contacter un staff en donner l'identifiant (Exemple d'ID : 0C)");
						return true;
					}
					
					List<String> accountList2 = accountList1.stream().sorted().limit(5).collect(Collectors.toList());
					
					String message1 = "§4§l->Top 5 money accounts<-\n";
					player.sendMessage(message1);
					
					for (String s : accountList1) {
						accountList2.add(s);
						Player player1 = Bukkit.getPlayer(UUID.fromString(s));
						String message2 = "§2" + player1.getPlayer().getDisplayName() + " §r: §e" + manager.getMoney(player1.getUniqueId().toString()) + "§2$\n";
						player.sendMessage(message2);
					}
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