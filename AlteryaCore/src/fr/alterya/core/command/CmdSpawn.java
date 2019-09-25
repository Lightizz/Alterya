package fr.alterya.core.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.core.MainCore;
import fr.alterya.core.util.FileWriter;

public class CmdSpawn extends BukkitRunnable implements CommandExecutor
{
	Player player;
	int timer = 5;
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
			this.player = player;
			player.sendMessage(MainCore.prefix + "§dVous§a serez téléporter au spawn dans 5 sec.");
			runTaskTimer(mainCore, 0, 20);
			return true;
		}
		return false;
	}

	public void confirmTeleport(Player player) {
		Location l = new Location(player.getWorld(), fw.getDouble("Spawn 1.X"), fw.getDouble("Spawn 1.Y"), fw.getDouble("Spawn 1.Z"));
		player.teleport(l);
		player.sendMessage(MainCore.prefix + "§dVous§a avez été téléporté au spawn avec succès !");
	}
	
	@Override
	public void run()
	{
		player.sendMessage(" " + timer);
		if(timer == 0) {
			timer = 0;
			confirmTeleport(player);
			cancel();
			return;
		}
		timer --;
	}
}
