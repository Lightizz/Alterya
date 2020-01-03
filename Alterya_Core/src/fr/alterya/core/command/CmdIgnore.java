package fr.alterya.core.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.MainCore;

public class CmdIgnore implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
	{
		if(msg.equalsIgnoreCase("ignore")) {
			if(! (sender instanceof Player)) {
				sender.sendMessage(MainCore.prefix + "Vous devez être un joueur pour faire cela.");
				return true;
			}
			Player player = (Player) sender;
			if(args.length != 0) {
				player.sendMessage(MainCore.prefix + ChatColor.AQUA + "La commande est §r" + cmd.getUsage() + ChatColor.AQUA + ".");
				return true;
			}
			
			if(MainCore.ignoreMPPlayer.contains(player.getUniqueId().toString())) {
				MainCore.ignoreMPPlayer.remove(player.getUniqueId().toString());
				player.sendMessage(MainCore.prefix + ChatColor.RED + "Mode d'ignorance des messages privé OFF");
				return true;
			}
			MainCore.ignoreMPPlayer.add(player.getUniqueId().toString());
			player.sendMessage(MainCore.prefix + ChatColor.GREEN + "Mode d'ignorance des messages privé ON");
			return true;
		}
		return false;
	}
}
