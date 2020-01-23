package fr.alterya.core.command;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

public class CmdWarp implements CommandExecutor
{
	public int timer = 0;
	BukkitRunnable run;
	
	MainCore m;
	Rank r;
	
	public static FileWriter fw = new FileWriter("ServerData/Warps", "Warps");
	
	public CmdWarp(MainCore main, Rank rank) {
		this.m = main;
		this.r = rank;
		
		reloadWarpsConfigs();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
	{
		if(msg.equalsIgnoreCase("warp")) {
			reloadWarpsConfigs();
			
			if(! (sender instanceof Player)) { sender.sendMessage(MainCore.prefix + ChatColor.AQUA + "Vous devez être un joueur pour faire cela."); return true; }
			Player player = (Player) sender;
			if(args.length != 1) { player.sendMessage(MainCore.prefix + ChatColor.RED + "Vous devez être un joueur pour faire cela."); return true; }
			
			Location warpLocation = new Location(Bukkit.getWorld("world"), 0, 0, 0);
			if(args[0].equalsIgnoreCase("nether")) {
				warpLocation.setWorld(Bukkit.getWorld("nether_warp"));
				warpLocation.setX(fw.getFloat("nether.x"));
				warpLocation.setY(fw.getDouble("nether.y"));
				warpLocation.setZ(fw.getFloat("nether.z"));
				warpLocation.setYaw(fw.getFloat("nether.yaw"));
				warpLocation.setPitch(fw.getFloat("nether.pitch"));
				
				RankList rl = m.rank.getRankById(Rank.config.getInt(player.getUniqueId().toString()));
				player.sendMessage(MainCore.prefix + ChatColor.AQUA + "Vous serez téléporter au warp nether dans " + rl.getTpCountdown() + " sec.");
				
				run = new BukkitRunnable(){
					@Override
					public void run()
					{
						if(timer >= rl.getTpCountdown()) {
							String serverName = "nether_warp";
							ByteArrayOutputStream byt = new ByteArrayOutputStream();
							DataOutputStream out = new DataOutputStream(byt);
							
							try {
								out.writeUTF("Connect");
								out.writeUTF(serverName);
							} catch (Exception e) { e.printStackTrace(); }
							
							//player.sendPluginMessage(m, "nether_warp", byt.toByteArray());
							//player.teleport(warpLocation);
							player.sendMessage(MainCore.prefix + ChatColor.GREEN + "§dVous§a avez été téléporté au warp nether avec succès !");
							timer = 0;
							cancel();
							return;
						}
						timer ++;
					}
				};
				run.runTaskTimer(m, 0L, 20L);
				return true;
			}else {
				player.sendMessage(MainCore.prefix + ChatColor.AQUA + "Ce warp n'éxiste pas !");
			}
		}
		return false;
	}
	
	public static void reloadWarpsConfigs() {
		fw.config.createSection("nether");
		fw.setValue("nether.world", "warp_nether");
		fw.setValue("nether.x", 0);
		fw.setValue("nether.y", 50);
		fw.setValue("nether.z", 0);
		fw.setValue("nether.yaw", 0F);
		fw.setValue("nether.pitch", 0F);
		fw.saveConfig();
	}
}
