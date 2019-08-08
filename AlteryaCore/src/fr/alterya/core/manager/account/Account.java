package fr.alterya.core.manager.account;

import fr.alterya.money.Main;
import fr.alterya.money.manager.account.Holdings;

public class Account {
	
	private String accountName;
	
	private String userId;
	
	private Main plugin;
	
	public Account(String userId, String accountName, Main plugin) {
		
		this.accountName = accountName;
		
		this.userId = userId;
		
		this.plugin = plugin;
		
	}
	
	public Holdings getHoldings() {
		
		return new Holdings(userId, accountName, plugin);
		
	}
	
	public String getAccountName() {
		
		return accountName;
		
	}
	
	public String getUserId() {
		
		return userId;
		
	}

}
