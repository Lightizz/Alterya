package fr.alterya.core.util;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.core.Main;
import net.minecraft.server.v1_7_R4.DamageSource;
import net.minecraft.server.v1_7_R4.EntityHuman;

public class DisconnectCombat extends BukkitRunnable implements Listener
{
	Main main;

	public static int timer = 10;
	
	public static boolean isOnCombat_ = false;
	
	Player attacker;
	Player player;
	
	@EventHandler
	public void startComabat(EntityDamageEvent e) {
		Player player = (Player) e.getEntity();
		this.player = player;
		if(e.getCause() == DamageCause.CONTACT && isOnCombat() == false || isOnCombat_ == true) {
			Player attacker = (Player) DamageSource.playerAttack((EntityHuman) player.getKiller());
			this.attacker = attacker;
			attacker.sendMessage(Main.prefix + "§4Vous êtes maintenant en combat !");
			player.sendMessage(Main.prefix + "§4Vous êtes maintenant en combat !");
			this.runTaskTimer(main, 0L, 20L);
			isOnCombat_ = true;
		}else if(e.getCause() == DamageCause.CONTACT && isOnCombat() == true || isOnCombat_ == true) {
			timer = 10;
		}
		return;
	}
	
	
	
	public static boolean isOnCombat() {
		if(!(timer <= 0) || isOnCombat_ == true) {
			isOnCombat_ = true;
			return true;
		}
		isOnCombat_ = false;
		return false;
	}
	
	@Override
	public void run()
	{
		if(timer <= 0) cancel();
		timer --;
	}
}
