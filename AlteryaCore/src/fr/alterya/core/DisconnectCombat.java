package fr.alterya.core;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DisconnectCombat extends BukkitRunnable implements Listener
{
	Main main;
	
	public DisconnectCombat(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onCombat(EntityDamageEvent e) {
		Player player = (Player) e.getEntity();
		if(e.getCause() == DamageCause.CONTACT) {
			this.runTaskTimer(main, 0L, 20L);
		}
	}
	
	@EventHandler
	public void onPlayerChating(PlayerCommandPreprocessEvent e) {
		Player player = e.getPlayer();
		if(this.timer != 0 && e.getMessage().contains("/hub") 
				||e.getMessage().contains("/lobby")
				||e.getMessage().contains("/home")
				||e.getMessage().contains("/tpa")
				||e.getMessage().contains("/tpahere")
				||e.getMessage().contains("/f home")) {
				e.setCancelled(true);
				player.sendMessage(Main.prefix + "§4 Vous êtes en combat, vous ne pouvez pas faire ça.");
		}
	}
	
	int timer = 5;
	
	@Override
	public void run()
	{
		if(timer == 0) {
			cancel();
			return;
		}
		timer --;
	}
}
