package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.MainCore;
import fr.alterya.core.rank.Rank;

public class CmdUUID implements CommandExecutor
{
	MainCore m;
	Rank r;
	
	public CmdUUID(MainCore main, Rank rank) {
		rank = r;
		main = m;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
	{
		if(args.length == 1) {
			Player player = (Player) sender;
			Player target = Bukkit.getPlayer(args[0]);
			if(Rank.config.getInt(player.getUniqueId().toString()) >= 9) {
				player.sendMessage(target.getUniqueId().toString());
				return true;
			}
		}
		return false;
	}
}
