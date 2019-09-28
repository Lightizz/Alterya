package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.core.LogType;
import fr.alterya.core.MainCore;
import fr.alterya.core.rank.Rank;
import fr.alterya.core.util.FileWriter;

public class CmdMute extends BukkitRunnable implements CommandExecutor
{
	public int timer = 0;
	
	Player player_;
	Player target_;
	
	int timeMutedMin;
	int timeMutedSec;
	
	Rank r;
	MainCore m;
	
	public static FileWriter fw;
	
	public CmdMute(Rank rank, MainCore main) {
		m = main;
		r = rank;
		fw = new FileWriter("ServerData", "MutedPlayers");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		if(message.equalsIgnoreCase("mute")) {
			if(args.length != 2) { sender.sendMessage("§eLa commande est §a" + command.getUsage() + "§e."); return true; }
			if(! (sender instanceof Player)) { sender.sendMessage("Vous devez être un joueur."); return true; }
			
			Player player = (Player) sender;
			player_ = player;
			
			if(m.rank.config.getInt(player.getUniqueId().toString()) <= 5) {
				player.sendMessage(MainCore.prefix + "§eVous n'avez pas le grade requis pour faire cela.");
				return true;
			}
			
			Player target = Bukkit.getPlayer(args[0]);
			target_ = target;
			
			if(fw.getBoolean(target.getUniqueId().toString()) == true) {
				player.sendMessage(MainCore.prefix + "§eCe joueur est déjà mute.");
				return true;
			}
			
			timeMutedMin = Integer.valueOf(args[1]);
			timeMutedSec = timeMutedMin * 60;
			
			fw.setValue(target.getUniqueId().toString(), true);
			fw.saveConfig();
			
			target.sendMessage(MainCore.prefix + "§eVous §aavez été mute par §e" + player.getDisplayName() + "§a pendant §e" + args[1] + "m§a.");
			player.sendMessage(MainCore.prefix + "§eVous §aavez mute §e" + target.getDisplayName() + "§a pendant §e" + args[1] + "m§a.");
			
			runTaskTimer(m, 0, 20);

			MainCore.log(LogType.INFO, "Le joueur " + player.getDisplayName() + " a mute " + target.getDisplayName() + ".");
			
			return true;
		}
		if(message.equalsIgnoreCase("unmute")) {
			if(args.length != 1) { sender.sendMessage("§eLa commande est §a" + command.getUsage() + "§e."); return true; }
			if(! (sender instanceof Player)) { sender.sendMessage("Vous devez être un joueur."); return true; }
			
			Player player = (Player) sender;
			
			if(m.rank.config.getInt(player.getUniqueId().toString()) <= 5) {
				player.sendMessage(MainCore.prefix + "§eVous n'avez pas le grade requis pour faire cela.");
				return true;
			}
			
			Player target = Bukkit.getPlayer(args[0]);
			
			if(fw.getBoolean(target.getUniqueId().toString()) == false) {
				player.sendMessage(MainCore.prefix + "§eCe joueur est déjà unmute.");
				return true;
			}
			
			fw.setValue(target.getUniqueId().toString(), false);
			fw.saveConfig();
			
			player.sendMessage(MainCore.prefix + "§eVous §aavez unmute §e" + target.getDisplayName() + "§a.");
			
			if(timer != 0) {
				timer = timeMutedSec;
			}
			
			MainCore.log(LogType.INFO, "Le joueur " + player.getDisplayName() + " a unmute " + target.getDisplayName() + ".");
			
			return true;
		}
		return false;
	}

	@Override
	public void run()
	{
		if(timer >= timeMutedSec) {
			cancel();
			fw.setValue(target_.getUniqueId().toString(), false);
		}
		timer ++;
	}
}
