package fr.alterya.core.util;

import java.util.HashMap;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.core.MainCore;

public class DisconnectCombat extends BukkitRunnable implements Listener
{
	MainCore main;

	public static int timer = 10;
	
	Player attacker;
	Player player;
	
	public static HashMap<String, Boolean> onCombatPlayersBoolean = new HashMap<>();
	public static HashMap<String, Integer> onCombatPlayersTimers = new HashMap<>();

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		onCombatPlayersBoolean.put(player.getUniqueId().toString(), false);
	}
	
	@EventHandler
	public void onStartCombat(EntityDamageByEntityEvent e) {
		Player player = (Player) e.getEntity();
		Player attacker = (Player) e.getDamager();
		this.attacker = attacker;
		this.player = player;
		
		if(isOnCombat((Player) player) == true || isOnCombat(attacker) == true) {
			onCombatPlayersTimers.replace(player.getUniqueId().toString(), 10);
			onCombatPlayersTimers.replace(attacker.getUniqueId().toString(), 10);
			return;
		}
		
		player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 50);
		attacker.playSound(attacker.getLocation(), Sound.ORB_PICKUP, 50, 50);
		player.sendMessage(MainCore.prefix + "�aVous �tes maintenant en combat !");
		attacker.sendMessage(MainCore.prefix + "�aVous �tes maintenant en combat !");
		onCombatPlayersBoolean.replace(player.getUniqueId().toString(), true);
		onCombatPlayersBoolean.replace(attacker.getUniqueId().toString(), true);
		runTaskTimer(main, 0, 20);
		onCombatPlayersTimers.put(player.getUniqueId().toString(), timer);
		onCombatPlayersTimers.put(player.getUniqueId().toString(), timer);
	}
	
	public static boolean isOnCombat(Player player) {
		if(onCombatPlayersBoolean.containsKey(player.getUniqueId().toString()) && onCombatPlayersTimers.get(player.getUniqueId().toString()) != 0) {
			return true;
		}
		return false;
	}
	
	public void stopCombat(Player player) {
		onCombatPlayersBoolean.replace(player.getUniqueId().toString(), false);
		player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 50);
		player.sendMessage(MainCore.prefix + "�aVous n'�tes plus en combat !");
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