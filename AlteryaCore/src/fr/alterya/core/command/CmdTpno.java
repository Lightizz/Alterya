package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.MainCore;

public class CmdTpno implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		Player player0 = (Player) sender;
		Player target0 = Bukkit.getPlayer(args[0]);
		
		if(message.equalsIgnoreCase("tpno") && CmdTpa.requestSenderPlayers.contains(player0.getUniqueId().toString()) || CmdTpa.requestedPlayers.contains(target0.getUniqueId().toString())) {
			if(args[0] == null) {
				player0.sendMessage(MainCore.prefix + "La commande est /tpno <joueur>.");
			}
			
			Player player = (Player) sender;
			Player target = Bukkit.getPlayer(args[0]);
				
			target.sendMessage(MainCore.prefix + "§eVous avez refuser la requête de §2" + player.getName() + " §e.");
			player.sendMessage(MainCore.prefix + "§eLe joueur §2" + target.getName() + "§e a refusé votre requête.");
				
			CmdTpa.requestSenderPlayers.remove(player.getUniqueId().toString());
			CmdTpa.requestedPlayers.remove(target.getUniqueId().toString());
				
			return true;
		}
		return false;
	}
}
