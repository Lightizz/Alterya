package fr.alterya.pvp.task;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.pvp.Main;
import fr.alterya.pvp.hunt.HuntManager;

/*
Author and resp of the hunt : Lightiz
*/

public class HuntTimer extends BukkitRunnable
{
	private HuntManager hunt;
	
	public int timer = 0;
	
	@Override
	public void run()
	{	
		if(timer == 60) {
			Bukkit.broadcastMessage(Main.prefix + "Il reste " + timer + " sec avant le changement de cible !");
		}
		
		if(timer == 120) 
		{
			timer = 0;
			hunt.swapTargetedPlayer();
			Bukkit.broadcastMessage(Main.prefix + "Une nouvelle personne est rechercher : " + hunt.getPlayerTargetedName() + " ! Tuez le pour remporter la récompense de 2000$ !");
		}else {
			
			timer++;
		}
	}
}

