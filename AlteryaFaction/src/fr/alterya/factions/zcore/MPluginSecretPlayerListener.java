package fr.alterya.factions.zcore;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import fr.alterya.factions.zcore.persist.EM;
import fr.alterya.factions.zcore.persist.Entity;
import fr.alterya.factions.zcore.persist.EntityCollection;
import fr.alterya.factions.zcore.persist.PlayerEntityCollection;

public class MPluginSecretPlayerListener implements Listener
{
	public MPlugin p;
	public MPluginSecretPlayerListener(MPlugin p)
	{
		this.p = p;
		Bukkit.getPluginManager().registerEvents(this, this.p);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerPreLogin(PlayerLoginEvent event)
	{
		for (EntityCollection<? extends Entity> ecoll : EM.class2Entities.values())
		{
			if (ecoll instanceof PlayerEntityCollection)
			{
				ecoll.get(event.getPlayer().getName());
			}
		}
	}
}