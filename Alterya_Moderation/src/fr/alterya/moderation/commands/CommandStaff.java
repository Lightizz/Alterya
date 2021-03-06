package fr.alterya.moderation.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.alterya.core.rank.Rank;
import fr.alterya.moderation.Main;

public class CommandStaff implements CommandExecutor {
	
	public Main main;
	
	public CommandStaff(Main main) {
		this.main = main;
	}

	private ArrayList<Player> vanished = new ArrayList<Player>();

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "You cannot vanish!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("vanish") && Rank.config.getInt(p.getUniqueId().toString()) >= 9) {
			Player player = (Player) sender;
			
			if (!vanished.contains(p)) {
				for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
					pl.hidePlayer(p);
					player.addPotionEffect (new PotionEffect (PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1)); 
				}
				vanished.add(p);
				p.sendMessage(ChatColor.GREEN + "You have been vanished!");
				return true;
			}
			else {
				for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
					pl.showPlayer(p);
					player.removePotionEffect(PotionEffectType.INVISIBILITY);
				}
				vanished.remove(p);
				p.sendMessage(ChatColor.GREEN + "You have been unvanished!");
				return true;
			}
		}
		return true;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		for (Player p : vanished) {
			e.getPlayer().hidePlayer(p);
		}
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		vanished.remove(e.getPlayer());
	}
}
