package fr.alterya.faction.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.alterya.faction.FPlayer;
import fr.alterya.faction.Faction;

public class FPlayerJoinEvent extends Event implements Cancellable
{
	private static final HandlerList handlers = new HandlerList();

	FPlayer fplayer;
	Faction faction;
	PlayerJoinReason reason;
	boolean cancelled = false;
	public enum PlayerJoinReason
	{
		CREATE, LEADER, COMMAND
	}
	public FPlayerJoinEvent(FPlayer fp, Faction f, PlayerJoinReason r)
	{ 
		fplayer = fp;
		faction = f;
		reason = r;
	}

	public FPlayer getFPlayer()
	{
		return fplayer;
	}
	public Faction getFaction()
	{
		return faction;
	}
	public PlayerJoinReason getReason()
	{
		return reason;	
	}
	public HandlerList getHandlers() 
	{
		return handlers;
	}

	public static HandlerList getHandlerList() 
	{
		return handlers;
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