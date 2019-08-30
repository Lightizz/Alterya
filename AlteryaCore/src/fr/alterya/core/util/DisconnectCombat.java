package fr.alterya.core.util;

import java.util.HashMap;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.core.MainCore;

public class DisconnectCombat extends BukkitRunnable implements Listener
{
	MainCore main;

	public static int timer = 10;
	
	Player attacker;
	Player player;
	
	public static HashMap<String, Boolean> onCombatPlayers = new HashMap<>();

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		onCombatPlayers.put(player.getUniqueId().toString(), false);
	}
	
	@EventHandler
	public void onStartCombat(EntityDamageEvent e) {
		Player player = (Player) e.getEntity();
		Player attacker = player.getKiller();
		this.attacker = attacker;
		this.player = player;
		player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 50);
		attacker.playSound(attacker.getLocation(), Sound.ORB_PICKUP, 50, 50);
		player.sendMessage(MainCore.prefix + "§aVous êtes maintenant en combat !");
		attacker.sendMessage(MainCore.prefix + "§aVous êtes maintenant en combat !");
		onCombatPlayers.replace(player.getUniqueId().toString(), true);
		onCombatPlayers.replace(attacker.getUniqueId().toString(), true);
	}
	
	public static boolean isOnCombat(Player player) {
		if(onCombatPlayers.containsKey(player.getUniqueId().toString())) {
			return true;
		}
		return false;
	}
	
	public void stopCombat(Player player) {
		onCombatPlayers.replace(player.getUniqueId().toString(), false);
		player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 50);
		player.sendMessage(MainCore.prefix + "§aVous n'êtes plus en combat !");
	}
	
	@Override
	public void run()
	{
		if(timer == 0) {
			stopCombat(this.attacker);
			stopCombat(this.player);
			cancel();
		}
		timer --;
	}
}
