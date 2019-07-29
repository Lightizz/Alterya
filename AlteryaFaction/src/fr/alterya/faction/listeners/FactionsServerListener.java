package fr.alterya.faction.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

import fr.alterya.faction.Main;
import fr.alterya.faction.integration.SpoutFeatures;


public class FactionsServerListener implements Listener
{
	public Main main;
	public FactionsServerListener(Main main)
	{
		this.main = main;
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