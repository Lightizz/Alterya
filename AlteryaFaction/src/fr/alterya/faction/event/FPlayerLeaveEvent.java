package fr.alterya.faction.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.alterya.faction.FPlayer;
import fr.alterya.faction.Faction;

public class FPlayerLeaveEvent extends Event implements Cancellable
{
	private static final HandlerList handlers = new HandlerList();
	private PlayerLeaveReason reason;
	FPlayer FPlayer;
	Faction Faction;
	boolean cancelled = false;

	public enum PlayerLeaveReason
	{
		KICKED, DISBAND, RESET, JOINOTHER, LEAVE
	}

	public FPlayerLeaveEvent(FPlayer p, Faction f, PlayerLeaveReason r)
	{
		FPlayer = p;
		Faction = f;
		reason = r;
	}

	public HandlerList getHandlers() 
	{
		return handlers;
	}

	public static HandlerList getHandlerList() 
	{
		return handlers;
	}
	
	public PlayerLeaveReason getReason() 
	{
		return reason;
	}
	
	public FPlayer getFPlayer()
	{
		return FPlayer;
	}
	
	public Faction getFaction()
	{
		return Faction;
	}

	@Override
	public boolean isCancelled() 
	{
		return cancelled;
	}

	@Override
	public void setCancelled(boolean c) 
	{
		if (reason == PlayerLeaveReason.DISBAND || reason == PlayerLeaveReason.RESET)
		{
			cancelled = false;
			return;
		}
		cancelled = c;
	}
}