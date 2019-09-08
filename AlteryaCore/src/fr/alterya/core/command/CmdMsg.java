package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.LogType;
import fr.alterya.core.MainCore;

public class CmdMsg implements CommandExecutor
{
	public MainCore mainCore;
	
	public CmdMsg(MainCore mainCore) {
		this.mainCore = mainCore;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		if(message.equalsIgnoreCase("msg") || message.equalsIgnoreCase("m")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(MainCore.prefix + "§4Vous devez être un joueur pour faire cela.");
				return true;
			}
			Player player = (Player) sender;
			if(args.length != 2) {
				player.sendMessage("La commande est " + command.getUsage() + ".");
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			String messageToSend = args[1];
			
			MainCore.log(LogType.INFO, "§e(Privé) " + player.getDisplayName() + " §r: " + messageToSend);
			target.sendMessage("§e(Privé) " + player.getDisplayName() + " §r: " + messageToSend);
			player.sendMessage("§e(Privé) " + player.getDisplayName() + " §a(Vous)" + " §r: " + messageToSend);
			
			return true;
		}
		return false;
	}
}
