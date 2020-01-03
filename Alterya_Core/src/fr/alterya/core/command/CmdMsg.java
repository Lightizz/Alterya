package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
			Player target = Bukkit.getPlayer(args[0]);
			
			String messageToSend = "";
			
			messageToSend = args[1];
		    for (int ii = 2; ii != args.length; ii++) {
		        messageToSend = String.valueOf(messageToSend) + " " + args[ii];
		    }			
			
			if(target.isOnline() == false) {
				player.sendMessage(MainCore.prefix + ChatColor.AQUA + "Le joueur §e" + target.getDisplayName() + ChatColor.AQUA + " n'est pas en ligne.");
				return true;
			}
			
			if(MainCore.ignoreMPPlayer.contains(target.getUniqueId().toString()) && mainCore.rank.config.getInt(player.getUniqueId().toString()) < 9) {
				player.sendMessage(MainCore.prefix + ChatColor.AQUA + "Le joueur que vous avez demandé a bloqué ses MP.");
				MainCore.log(LogType.INFO, "§e(Privé) (Bloqued by " + target.getDisplayName() + ")" + player.getDisplayName() + " §r: " + messageToSend);
				return true;
			}else if(MainCore.ignoreMPPlayer.contains(target.getUniqueId().toString()) && mainCore.rank.config.getInt(player.getUniqueId().toString()) >= 9) {
				target.sendMessage("§e(Privé) " + player.getDisplayName() + " §r: " + messageToSend);
				player.sendMessage("§e(Privé) " + player.getDisplayName() + " §a(Vous)" + " §r: " + messageToSend);
				MainCore.log(LogType.INFO, "§e(Privé) " + player.getDisplayName() + " §r: " + messageToSend);
				return true;
			}
			
			target.sendMessage("§e(Privé) " + player.getDisplayName() + " §r: " + messageToSend);
			player.sendMessage("§e(Privé) " + player.getDisplayName() + " §a(Vous)" + " §r: " + messageToSend);
			MainCore.log(LogType.INFO, "§e(Privé) " + player.getDisplayName() + " §r: " + messageToSend);
			return true;
		}
		return false;
	}
}
