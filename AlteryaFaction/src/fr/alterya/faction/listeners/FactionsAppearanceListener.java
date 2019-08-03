package fr.alterya.faction.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import fr.alterya.faction.Conf;
import fr.alterya.faction.Main;
import fr.alterya.faction.integration.SpoutFeatures;


public class FactionsAppearanceListener implements Listener
{
	public Main main;
	public FactionsAppearanceListener(Main main)
	{
		this.main = main;
	}
	
	public void fullTwoWay(Player player)
	{
		SpoutFeatures.updateTitleShortly(player, null);
		SpoutFeatures.updateTitleShortly(null, player);
		SpoutFeatures.updateCapeShortly(player, null);
		SpoutFeatures.updateCapeShortly(null, player);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent event)
	{		
		fullTwoWay(event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerTeleport(PlayerTeleportEvent event)
	{
		if (event.getFrom().getWorld().equals(event.getTo().getWorld())) return;
		fullTwoWay(event.getPlayer());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
		fullTwoWay(event.getPlayer());
	}
	
	// -------------------------------------------- //
	// HEALTH BAR
	// -------------------------------------------- //
	
	public static void possiblyUpdateHealthBar(Entity entity)
	{
		if ( ! Conf.spoutHealthBarUnderNames) return;
		if ( ! (entity instanceof Player)) return;
		Player player = (Player)entity;
		SpoutFeatures.updateTitle(player, null);
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void monitorEntityDamageEvent(EntityDamageEvent event)
	{
		possiblyUpdateHealthBar(event.getEntity());
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void monitorEntityRegainHealthEvent(EntityRegainHealthEvent event)
	{
		possiblyUpdateHealthBar(event.getEntity());
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void monitorPlayerRespawnEvent(PlayerRespawnEvent event)
	{
		possiblyUpdateHealthBar(event.getPlayer());
	}
	
}