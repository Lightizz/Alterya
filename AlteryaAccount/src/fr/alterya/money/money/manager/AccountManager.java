package fr.alterya.money.money.manager;

import java.util.List;

import org.bukkit.Bukkit;

import fr.alterya.money.money.Main;
import fr.alterya.money.money.event.AccountCreateEvent;
import fr.alterya.money.money.event.AccountDeleteEvent;
import fr.alterya.money.money.manager.account.Account;
import fr.alterya.money.money.manager.account.AccountData;
import fr.alterya.money.money.manager.account.AccountException;

public class AccountManager {

	private static Main plugin;
	
	public AccountManager(Main plugin) {
		
		this.plugin = plugin;
		
	}
	
	public static boolean hasAccount(String userId, String group) {
		
		return AccountData.hasAccount(userId, group);
		
	}
	
	public static Account getAccount(String userId, String group) throws AccountException {
		
		if(hasAccount(userId, group)) {
			
			return new Account(userId, group, plugin);
			
		} else {
			
			throw new AccountException("§cThis account does not exist!");
			
		}
		
	}
	
	/**
	 * Create a new account
	 * @param accountNumber
	 * @throws AccountException
	 */
	public static void createAccount(String userId, String group) throws AccountException {
		
		if(hasAccount(userId, group)) throw new AccountException("§cThis account already exists!");
		
		AccountCreateEvent event = new AccountCreateEvent(userId, group, 30);
		
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		if(!event.isCancelled()) {
				
			AccountData.createAccount(userId, group, event.getBalance());
			
		}
		
	}
	
	/**
	 * Delete an account
	 * @param accountNumber
	 * @throws AccountException
	 */
	public static void deleteAccount(String userId, String group) throws AccountException {
		
		if(!hasAccount(userId, group)) throw new AccountException("§cThis account does not exist!");
		
		AccountDeleteEvent event = new AccountDeleteEvent(userId, group);
		
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		if(!event.isCancelled()) {
			
			AccountData.removeAccount(userId, group);
			
		}
		
	}
	
	/**
	 * Get the biggest holdings of all accounts
	 * @return
	 */
	public static List<Account> getTopAccounts() {
		
		return AccountData.getTopAccounts(10);
		
	}
	
	//public String getWorldGroup(String worldName) {
		
		
		
	//}
	
}
