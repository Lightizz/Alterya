package fr.alterya.core.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.core.LogType;
import fr.alterya.core.MainCore;
import fr.alterya.core.rank.Rank;
import fr.alterya.core.util.FileWriter;

@SuppressWarnings("deprecation")
public class CmdTpMute implements CommandExecutor, Listener
{
public int timer = 0;
	
	Player player_;
	Player target_;
	
	BukkitRunnable run;
	
	int timeMutedHour;
	int timeMutedSec;
	
	Rank r;
	MainCore m;
	
	public static FileWriter fw;
	
	public CmdTpMute(Rank rank, MainCore main) {
		m = main;
		r = rank;
		fw = new FileWriter("ServerData", "TpMutedPlayers");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		if(message.equalsIgnoreCase("tpmute")) {
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
				player.sendMessage(MainCore.prefix + "§eCe joueur est déjà tpmute.");
				return true;
			}
			
			timeMutedHour = Integer.valueOf(args[1]);
			timeMutedSec = timeMutedHour * 3600;
			
			fw.setValue(target.getUniqueId().toString(), true);
			fw.saveConfig();
			
			target.sendMessage(MainCore.prefix + "§eVous §aavez été tpmute par §e" + player.getDisplayName() + "§a pendant §e" + args[1] + "m§a.");
			player.sendMessage(MainCore.prefix + "§eVous §aavez tpmute §e" + target.getDisplayName() + "§a pendant §e" + args[1] + "m§a.");
			
			run = new BukkitRunnable(){
				@Override
				public void run()
				{
					if(timer == timeMutedSec && fw.getBoolean(target.getUniqueId().toString()) == true) {
						fw.setValue(target.getUniqueId().toString(), false);
						fw.saveConfig();
						target.sendMessage(MainCore.prefix + "§aVous §eêtes maintenant untpmute.");
						timer = 0;
						cancel();
					}
					timer ++;
				}
			};
			
			MainCore.log(LogType.INFO, "Le joueur " + player.getDisplayName() + " a tpmute " + target.getDisplayName() + ".");
			
			return true;
		}
		if(message.equalsIgnoreCase("untpmute")) {
			if(args.length != 1) { sender.sendMessage("§eLa commande est §a" + command.getUsage() + "§e."); return true; }
			if(! (sender instanceof Player)) { sender.sendMessage("Vous devez être un joueur."); return true; }
			
			Player player = (Player) sender;
			
			if(m.rank.config.getInt(player.getUniqueId().toString()) <= 5) {
				player.sendMessage(MainCore.prefix + "§eVous n'avez pas le grade requis pour faire cela.");
				return true;
			}
			
			Player target = Bukkit.getPlayer(args[0]);
			
			if(fw.getBoolean(target.getUniqueId().toString()) == false) {
				player.sendMessage(MainCore.prefix + "§eCe joueur est déjà untpmute.");
				return true;
			}
			
			fw.setValue(target.getUniqueId().toString(), false);
			fw.saveConfig();
			
			player.sendMessage(MainCore.prefix + "§eVous §aavez untpmute §e" + target.getDisplayName() + "§a.");
			
			if(timer != 0) {
				timer = timeMutedSec;
			}
			
			MainCore.log(LogType.INFO, "Le joueur " + player.getDisplayName() + " a untpmute " + target.getDisplayName() + ".");
			
			return true;
		}
		return false;
	}
	
	public List<String> commands = Arrays.asList("home", "spawn", "tp", "tpa", "tpahere", "tphere", "f ap", "f home");
	
	@EventHandler
	void onPlayerSendCommand(PlayerChatEvent e) {
		Player sender = e.getPlayer();
		for(String cmd : commands) {
			if(e.getMessage().startsWith("/" + cmd)) {
	            e.setCancelled(true);
	            return;
	        }
	    }
		sender.sendMessage(MainCore.prefix + ChatColor.RED + "Vous êtes *TpMute* ce qui signifie que tant que vous êtes dans ce status, vous ne pouvz pas utiliser de commande de téléportation.");
		e.setCancelled(true);
		return;
	}
}
