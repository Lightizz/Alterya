package fr.alterya.money.money.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AccountCreateEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	
	private String accountName;

	private String userId;
	
	private double balance;
	
	private boolean cancel;
	
	public AccountCreateEvent(String userId, String accountName, double balance) {
		
		this.userId = userId;
		
		this.accountName = accountName;
		
		this.setBalance(balance);
		
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

	public double getBalance() {
		
		return balance;
		
	}

	public void setBalance(double balance) {
		
		this.balance = balance;
		
	}

}
