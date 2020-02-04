package fr.alterya.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.core.MainCore;
import fr.alterya.core.kits.Kits;
import fr.alterya.core.rank.Rank;

public class CmdKit implements CommandExecutor
{
	public int timer = 0;
	Player player0;
	MainCore main;
	Rank rank = new Rank(main, player0);
	
	//1h = 3 600 sec ; 24h = 86 400 sec ; 48h = 172 800 sec ; 72h = 259 200 sec
	BukkitRunnable run;
	
	public CmdKit(MainCore m) {
		main = m;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args) {
		Player player = (Player) sender;
		player0 = player;
		if(message.equalsIgnoreCase("kit") && args.length == 1) {
			if(Rank.config.getInt(player.getUniqueId().toString()) == 2 && args[0].contains("memoire")) {
				if(timer != 0) {
					int timeRemaiting = 172800 - timer;
					player.sendMessage(MainCore.prefix + "Il vous reste " + timeRemaiting + "sec avant de pouvoir re-utiliser le /kit !");
				}else if(timer == 0) {
					Kits.giveMemoireKit(player);
					player.sendMessage(MainCore.prefix + "Voici votre kit du grade Mémoire !");
					run = new BukkitRunnable(){
						@Override
						public void run()
						{
							if(timer >= 172800) {
								cancel();
								timer = 0;
								return;
							}
							timer ++;
						}
					};
					run.runTaskTimer(main, 0, 20);
					return true;
				}
			}else if(Rank.config.getInt(player.getUniqueId().toString()) == 3 && args[0].contains("sage")) {
				if(timer != 0) {
					int timeRemaiting = 259200 - timer;
					player.sendMessage(MainCore.prefix + "Il vous reste " + timeRemaiting + "sec avant de pouvoir re-utiliser le /kit !");
				}else if(timer == 0) {
					Kits.giveSageKit(player);
					player.sendMessage(MainCore.prefix + "Voici votre kit du grade Sage !");
					run = new BukkitRunnable(){
						@Override
						public void run()
						{
							if(timer >= 172800) {
								cancel();
								timer = 0;
								return;
							}
							timer ++;
						}
					};
					run.runTaskTimer(main, 0, 20);
					return true;
				}
			}else if(Rank.config.getInt(player.getUniqueId().toString()) == 1 && args[0].contains("souvenir")) {
				if(timer != 0) {
					int timeRemaiting = 172800 - timer;
					player.sendMessage(MainCore.prefix + "Il vous reste " + timeRemaiting + "sec avant de pouvoir re-utiliser le /kit !");
				}else if(timer == 0) {
					Kits.giveSouvenirKit(player);
					player.sendMessage(MainCore.prefix + "Voici votre kit du grade Souvenir !");
					run = new BukkitRunnable(){
						@Override
						public void run()
						{
							if(timer >= 172800) {
								cancel();
								timer = 0;
								return;
							}
							timer ++;
						}
					};
					run.runTaskTimer(main, 0, 20);
					return true;
				}	
			}else if(args[0].contains("pvp")) {
				if(timer != 0) {
					int timeRemaiting = 172800 - timer;
					player.sendMessage(MainCore.prefix + "Il vous reste " + timeRemaiting + "sec avant de pouvoir re-utiliser le /kit !");
				}else if(timer == 0) {
					Kits.givePVPKit(player);
					player.sendMessage(MainCore.prefix + "Voici votre kit PVP !");
					run = new BukkitRunnable(){
						@Override
						public void run()
						{
							if(timer >= 172800) {
								cancel();
								timer = 0;
								return;
							}
							timer ++;
						}
					};
					run.runTaskTimer(main, 0, 20);
					return true;
				}	
			}
		}
		return false;
	}
}
