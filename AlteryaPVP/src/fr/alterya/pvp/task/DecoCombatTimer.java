package fr.alterya.pvp.task;

/*
Author and resp of the déco combat : Lightiz
*/

import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.pvp.listeners.InCombatListener;

public class DecoCombatTimer extends BukkitRunnable
{
	public int timer = 0;
	
	InCombatListener combatL;
	
	@Override
	public void run()
	{
		if(timer == 5) {
			cancel();
		}
	}
}
