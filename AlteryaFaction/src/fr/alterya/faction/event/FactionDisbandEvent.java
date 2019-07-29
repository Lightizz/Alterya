package fr.alterya.faction.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.alterya.faction.FPlayer;
import fr.alterya.faction.FPlayers;
import fr.alterya.faction.Faction;
import fr.alterya.faction.Factions;

public class FactionDisbandEvent extends Event implements Cancellable
{
	private static final HandlerList handlers = new HandlerList();

	private boolean cancelled;
	private String id;
	private Player sender;

	public FactionDisbandEvent(Player sender, String factionId)
	{
		cancelled = false;
		this.sender = sender;
		this.id = factionId;
	}

	public HandlerList getHandlers() 
	{
		return handlers;
	}

	public static HandlerList getHandlerList() 
	{
		return handlers;
	}

	public Faction getFaction()
	{
		return Factions.i.get(id);
	}

	public FPlayer getFPlayer()
	{
		return FPlayers.i.get(sender);
	}

	public Player getPlayer()
	{
		return sender;
	}

	@Override
	public boolean isCancelled() 
	{
		return cancelled;
	}

	@Override
	public void setCancelled(boolean c) 
	{
		cancelled = c;
	}
}
