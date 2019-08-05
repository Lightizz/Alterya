package fr.alterya.money.manager.account;

import static fr.alterya.money.Messages.tl;

import org.bukkit.Bukkit;

import fr.alterya.money.Main;
import fr.alterya.money.Settings.Nodes;
import fr.alterya.money.event.HoldingsChangeEvent;

public class Holdings {

	private String accountName;
	
	private String userId;
	
	@SuppressWarnings("unused")
	private Main plugin;
	
	public Holdings(String userId, String accountName, Main plugin) {
		
		this.userId = userId;
		
		this.accountName = accountName;
		
		this.plugin = plugin;
		
	}
	
	public String getAccountName() {
		
		return accountName;
		
	}
	
	public void setBalance(double newBal) {
		
		HoldingsChangeEvent event = new HoldingsChangeEvent(userId, accountName, getBalance(), newBal);
		
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		if(!event.isCancelled())
			AccountData.setAccountBalance(userId, accountName, event.getNewBal());
			
	}
	
	public double getBalance() {
		return AccountData.getAccountBalance(userId, accountName);
	}
	
	public static String format(double amount) {
		
		String symbol = amount == 1 ? Nodes.SINGLE.getString() : Nodes.MULTIPLE.getString();
		
		String formatted = amount + " " + symbol;
		
		if(Nodes.BEFORE.getBoolean()) formatted = symbol + " " + amount;
		
		return formatted;
		
	}
	
	public void subtract(int amount) throws AccountException {
		
		double balance = getBalance();
		
		if((balance - amount) < Nodes.BALANCEMIN.getInt()) 
			throw new AccountException(tl("notEnoughMoney"));
		
		setBalance(getBalance() - amount);
		
	}
	
	public void add(int amount) {
		
		setBalance(getBalance() + amount);
		
	}
	
	public boolean hasEnough(int amount) {
		
		if(amount <= getBalance()) {
			
			return true;
			
		}
		
		return false;
		
	}

}
