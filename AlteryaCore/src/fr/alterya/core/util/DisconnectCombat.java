package fr.alterya.core.util;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.core.MainCore;
import net.minecraft.server.v1_7_R4.DamageSource;
import net.minecraft.server.v1_7_R4.EntityHuman;

public class DisconnectCombat extends BukkitRunnable implements Listener
{
	MainCore main;

	public static int timer = 10;
	
	Player attacker;
	Player player;
	
	public static HashMap<String, String> onCombatPlayers = new HashMap<>();

	@EventHandler
	public void startComabat(EntityDamageEvent e) {
		Player player = Bukkit.getPlayer(e.getEntity().getUniqueId());
		this.player = player;
		if(e.getCause() == DamageCause.ENTITY_ATTACK && ! onCombatPlayers.containsKey(player.getUniqueId().toString()) || ! onCombatPlayers.containsKey(attacker.getUniqueId().toString()) || isOnCombat(player.getUniqueId().toString()) == false) {
			Player attacker = (Player) DamageSource.playerAttack((EntityHuman) player.getKiller());
			this.attacker = attacker;
			attacker.sendMessage(MainCore.prefix + "§4Vous êtes maintenant en combat !");
			player.sendMessage(MainCore.prefix + "§4Vous êtes maintenant en combat !");
			this.runTaskTimer(main, 0L, 20L);
			onCombatPlayers.put(player.getUniqueId().toString(), String.valueOf(timer));
			onCombatPlayers.put(attacker.getUniqueId().toString(), String.valueOf(timer));
		}else if(e.getCause() == DamageCause.ENTITY_ATTACK && onCombatPlayers.containsKey(player.getUniqueId().toString()) || onCombatPlayers.containsKey(attacker.getUniqueId().toString()) || isOnCombat(player.getUniqueId().toString()) == true) {
			Player attacker = (Player) DamageSource.playerAttack((EntityHuman) player.getKiller());
			this.attacker = attacker;
			onCombatPlayers.get(player.getUniqueId().toString()).equals(String.valueOf(timer));
			onCombatPlayers.get(attacker.getUniqueId().toString()).equals(String.valueOf(timer));
		}
		return;
	}
	
	@EventHandler
	public void onCommand(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		if(isOnCombat(player.getUniqueId().toString()) == true && e.getMessage().contains("/lobby") || e.getMessage().contains("/hub") || e.getMessage().contains("/tpa") || e.getMessage().contains("/tpyes") || e.getMessage().contains("/f home") || e.getMessage().contains("/home") || e.getMessage().contains("/f ap")) {
			player.sendMessage(MainCore.prefix + "Vous êtes en combat, il vous reste " + onCombatPlayers.get(player.getUniqueId().toString()) + " sec.");
		}
	}
	
	public static boolean isOnCombat(String uuid) {
		if(onCombatPlayers.containsKey(uuid)) {
			return true;
		}
		return false;
	}
	@Override
	public void run()
	{
		if(onCombatPlayers.containsKey(player.getUniqueId().toString()) || onCombatPlayers.containsKey(attacker.getUniqueId().toString()) 
				|| isOnCombat(player.getUniqueId().toString()) == true || isOnCombat(attacker.getUniqueId().toString()) == true 
				&& onCombatPlayers.get(player.getUniqueId().toString()) == String.valueOf(0) || onCombatPlayers.get(attacker.getUniqueId().toString()) == String.valueOf(0)) {
			onCombatPlayers.remove(attacker.getUniqueId().toString());
			onCombatPlayers.remove(player.getUniqueId().toString());
			cancel();
		}
		timer --;
		onCombatPlayers.get(attacker.getUniqueId().toString()).equals(String.valueOf(timer));
		onCombatPlayers.get(player.getUniqueId().toString()).equals(String.valueOf(timer));
	}
}
