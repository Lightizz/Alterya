package fr.alterya.money.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class HoldingsChangeEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	
	private String accountName;
	
	private String userId;
	
	private boolean cancel;
	
	private double oldBal;
	
	private double newBal;
	
	public HoldingsChangeEvent(String userId, String accountName, double oldBal, double newBal) {
		
		this.accountName = accountName;
		
		this.userId = userId;
		
		this.oldBal = oldBal;
		
		this.newBal = newBal;
		
	}
	
	public HandlerList getHandlers() {
		
	    return handlers;
	    
	}
	 
	public static HandlerList getHandlerList() {
		
	    return handlers;
	    
	}
	
	public boolean isCancelled() {
		
		return cancel;
		
	}
	
	public void setCancelled(boolean cancel) {
		
		this.cancel = cancel;
		
	}

	public String getAccountName() {
		
		return accountName;
		
	}
	
	public String getUserId() {
		
		return userId;
		
	}
	
	public double getOldBal() {
		
		return oldBal;
		
	}

	public double getNewBal() {
		
		return newBal;
		
	}

}
