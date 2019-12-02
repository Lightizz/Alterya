package fr.alterya.factions.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

import fr.alterya.factions.P;

import fr.alterya.factions.integration.SpoutFeatures;


public class FactionsServerListener implements Listener
{
	public P p;
	public FactionsServerListener(P p)
	{
		this.p = p;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPluginDisable(PluginDisableEvent event)
	{
		SpoutFeatures.setup();
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPluginEnable(PluginEnableEvent event)
	{
		SpoutFeatures.setup();
	}
}