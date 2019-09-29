package fr.alterya.core.command;

import java.util.Date;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.MainCore;
import fr.alterya.core.rank.Rank;

public class CmdTempBan implements CommandExecutor
{
	public boolean isToCancel = false;
	
	Rank r;
	MainCore m;
	
	public static String reason_;
	
	Date d = new Date();
	
	public CmdTempBan(Rank rank, MainCore main) {
		r = rank;
		m = main;
	}
	
	public static HashMap<String, Integer> timePlayersBanned = new HashMap<String, Integer>();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		if(message.equalsIgnoreCase("tempban")) {
			if(args.length != 3) {
				sender.sendMessage("La commande est : " + command.getUsage() + ".");
				return true;
			}
			
			Player player = (Player) sender;
			Player target = Bukkit.getPlayer(args[0]);
			
			if(m.rank.config.getInt(player.getUniqueId().toString()) < 8) {
				player.sendMessage(MainCore.prefix + "§aVous§e n'avez pas le grade requis pour faire cela.");
				return true;
			}
			
			String reason = args[2];
			reason = reason_;
			
			int timeBannedDay = Integer.valueOf(args[1]);
			timePlayersBanned.put(target.getUniqueId().toString(), timeBannedDay);
			
			target.kickPlayer("§4Vous avez été banni pendant §e" + timeBannedDay + "j§4, raison : §e" + args[2] + "§4.");
			target.setBanned(true);
			
			int taskID = Bukkit.getScheduler().runTaskTimer(m, new Runnable() {

				@Override
				public void run()
				{
					if(d.getDate() == timeBannedDay) {
						target.setBanned(false);
						timePlayersBanned.remove(target.getUniqueId().toString());
						isToCancel = true;
					}
				}
				
			}, 0, 20).getTaskId();
			
			if(isToCancel == true) {
				Bukkit.getScheduler().cancelTask(taskID);
			}
			
			player.sendMessage(MainCore.prefix + "§eLe joueur §a" + target.getDisplayName() + "§e a été banni pour §a" + Integer.valueOf(args[1]) + "§e jours pour la raison suivante : §a" + reason + "§e.");
			return true;
		}
		return false;
	}
}
