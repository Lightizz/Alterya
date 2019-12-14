package fr.alterya.core.util;

import java.util.HashMap;

import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
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
		if(e.getEntityType() == EntityType.PLAYER && e.getDamager().getType() == EntityType.PLAYER) {
			Player player = (Player) e.getEntity();
			Player attacker = (Player) e.getDamager();
			
			this.attacker = attacker;
			this.player = (Player) player;
			
			if(isOnCombat((Player) player) == true || isOnCombat(attacker) == true) {
				onCombatPlayersTimers.replace(player.getUniqueId().toString(), 10);
				onCombatPlayersTimers.replace(attacker.getUniqueId().toString(), 10);
				return;
			}
			
			//Joue un son d'alerte
			((Player) player).playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 50);
			attacker.playSound(attacker.getLocation(), Sound.ORB_PICKUP, 50, 50);
			
			//Envoie les messages
			((Player) player).sendMessage(MainCore.prefix + "§aVous êtes maintenant en combat !");
			attacker.sendMessage(MainCore.prefix + "§aVous êtes maintenant en combat !");
			
			//Active le mode en comabt pour les joueurs
			onCombatPlayersBoolean.replace(player.getUniqueId().toString(), true);
			onCombatPlayersBoolean.replace(attacker.getUniqueId().toString(), true);
			
			//Commence le timer
			runTaskTimer(main, 0, 20);
			
			//Ajoute les timers des joueurs
			onCombatPlayersTimers.put(player.getUniqueId().toString(), timer);
			onCombatPlayersTimers.put(player.getUniqueId().toString(), timer);
		}
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
