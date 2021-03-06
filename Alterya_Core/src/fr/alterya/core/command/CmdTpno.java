package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.LogType;
import fr.alterya.core.MainCore;

public class CmdTpno implements CommandExecutor
{
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		Player player0 = (Player) sender;
		if(args[0] == null) {
			player0.sendMessage(MainCore.prefix + "La commande est /tpyes <joueur>.");
			return true;
		}
		Player target0 = Bukkit.getPlayer(args[0]);
		
		if(message.equalsIgnoreCase("tpno") && CmdTpa.requestSenderPlayers.contains(player0.getUniqueId().toString()) || CmdTpa.requestedPlayers.contains(target0.getUniqueId().toString())) {
			Player player = (Player) sender;
			Player target = Bukkit.getPlayer(args[0]);
				
			target.sendMessage(MainCore.prefix + "�eVous avez refuser la requ�te de �2" + player.getName() + " �e.");
			player.sendMessage(MainCore.prefix + "�eLe joueur �2" + target.getName() + "�e a refus� votre requ�te.");
				
			CmdTpa.requestSenderPlayers.remove(player.getUniqueId().toString());
			CmdTpa.requestedPlayers.remove(target.getUniqueId().toString());
				
			MainCore.log(LogType.INFO, "Le joueur " + target.getDisplayName() + " a refus� la requ�te de t�l�portation de " + player.getDisplayName() + ".");
			
			return true;
		}
		return false;
	}
}
