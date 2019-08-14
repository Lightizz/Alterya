package fr.alterya.money.money.manager.account;

import org.bukkit.Bukkit;

import fr.alterya.money.money.Main;
import fr.alterya.money.money.Settings.Nodes;
import fr.alterya.money.money.event.HoldingsChangeEvent;

public class Holdings {

	private String accountName;
	
	private String userId;
	
	private Main plugin;
	
	public Holdings(String userId, String accountName, Main plugin) {
		
		this.userId = userId;
		
		this.accountName = accountName;
		
		this.plugin = plugin;
		
	}
	
	public String getAccountName() {
		
		return accountName;
		
	}
	
	public void setBalance(double newMoney) {
		
		HoldingsChangeEvent event = new HoldingsChangeEvent(userId, accountName, getBalance(), newMoney);
		
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
	
	public void subtract(double amount) throws AccountException {
		
		double balance = getBalance();
		
		if((balance - amount) < Nodes.BALANCEMIN.getInt()) 
			throw new AccountException("�cYou need more money to do this!");
		
		setBalance(getBalance() - amount);
		
	}
	
	public void add(double amount) {
		setBalance(getBalance() + amount);
	}
	
	public boolean hasEnough(double amount) {
		if(amount <= getBalance()) {
			return true;	
		}
		return false;
	}
}