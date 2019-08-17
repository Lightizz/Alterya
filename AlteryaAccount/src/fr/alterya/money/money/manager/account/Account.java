package fr.alterya.money.money.manager.account;

import fr.alterya.money.money.MainAccount;

public class Account {
	
	private String accountName;
	
	private String userId;
	
	private MainAccount plugin;
	
	public Account(String userId, String accountName, MainAccount plugin) {
		
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
