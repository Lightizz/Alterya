package fr.alterya.core.event;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.core.MainCore;
import fr.alterya.core.util.StructureBuilder;

public class KOTHEvent implements Listener
{
	private KOTHEventManager ekManager;
	private MainCore m;
	
	public static int timer = 0;
	
	public BukkitRunnable r = new BukkitRunnable() {@Override public void run(){}};
	
	public KOTHEvent(MainCore main, KOTHEventManager kothEventManager)
	{
		m = main;
		ekManager = kothEventManager;
	}

	public void run() {
		r = new BukkitRunnable() {
			@Override
			public void run()
			{
				
			}
		};
	}
	
	public void startEvent() {
		int randomPosX = 0 + (int)(Math.random() * ((30000 - 0) + 1));
		int randomPosY = 64 + (int)(Math.random() * ((70 - 64) + 1));
		int randomPosZ = 0 + (int)(Math.random() * ((30000 - 0) + 1));
		
		StructureBuilder.buildKOTHStructure(randomPosX, randomPosY, randomPosZ);
		
		//Alert the map than a koth is starting
		Bukkit.broadcastMessage("");
	}
	
	public void endEvent() {
		
	}
}
