package fr.alterya.factions.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.alterya.factions.FPlayer;
import fr.alterya.factions.FPlayers;
import fr.alterya.factions.Faction;
import fr.alterya.factions.Factions;

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
