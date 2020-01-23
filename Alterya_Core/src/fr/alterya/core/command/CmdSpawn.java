package fr.alterya.core.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.core.MainCore;
import fr.alterya.core.rank.Rank;
import fr.alterya.core.rank.RankList;
import fr.alterya.core.util.FileWriter;

public class CmdSpawn implements CommandExecutor
{
	public int timer = 0;
	BukkitRunnable run;
	
	public static FileWriter fw;
	MainCore mainCore;
	
	public CmdSpawn(MainCore mainCore) {
		this.mainCore = mainCore;
		fw = new FileWriter("ServerData/ServerInfos", "SpawnPos");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		if(message.equalsIgnoreCase("spawn")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Vous devez être un joueur pour faire cela.");
				return true;
			}
			Player player = (Player) sender;
			Location l = new Location(player.getWorld(), fw.getDouble(player.getWorld().getName() + "_spawn.X"), fw.getDouble(player.getWorld().getName() + "_spawn.Y"), fw.getDouble(player.getWorld().getName() + "_spawn.Z"));
			RankList rl = mainCore.rank.getRankById(Rank.config.getInt(player.getUniqueId().toString()));
			player.sendMessage("Vous serez téléporter au spawn dans " + rl.getTpCountdown() + " sec.");
			
			run = new BukkitRunnable(){
				@Override
				public void run()
				{
					if(timer >= rl.getTpCountdown()) {
						player.teleport(l);
						player.sendMessage(MainCore.prefix + "§dVous§a avez été téléporté au spawn avec succès !");
						timer = 0;
						cancel();
						return;
					}
					timer ++;
				}
			};
			run.runTaskTimer(mainCore, 0L, 20L);
			return true;
		}
		return false;
	}
}
