package fr.alterya.core.manager;

import java.util.List;

import org.bukkit.Bukkit;

import fr.alterya.core.event.AccountCreateEvent;
import fr.alterya.core.event.AccountDeleteEvent;
import fr.alterya.money.Main;
import fr.alterya.money.manager.account.Account;
import fr.alterya.money.manager.account.AccountData;
import fr.alterya.money.manager.account.AccountException;

public class AccountManager {

	private static Main plugin;
	
	public static boolean hasAccount(String userId, String group) {
		
		return AccountData.hasAccount(userId, group);
		
	}
	
	public static Account getAccount(String userId, String group) throws AccountException {
		
		if(hasAccount(userId, group)) {
			
			return new Account(userId, group, plugin);
			
		} else {
			
			throw new AccountException("&cCe compte n'éxiste pas.");
			
		}
		
	}
	
	/**
	 * Create a new account
	 * @param accountNumber
	 * @throws AccountException
	 */
	public static void createAccount(String userId, String group) throws AccountException {
		
		if(hasAccount(userId, group)) throw new AccountException("&cCe compte éxiste déjà.");
		
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
		
		if(!hasAccount(userId, group)) {
			throw new AccountException("&cCe compte n'éxiste pas.");
		}
		
		AccountDeleteEvent event = new AccountDeleteEvent(userId, group);
		Bukkit.getServer().getPluginManager().callEvent(event);
		
		if(!event.isCancelled()) {
			AccountData.removeAccount(userId, group);
		}
	}
	
	public static List<Account> getTopAccounts() {
		return AccountData.getTopAccounts(10);
	}
	//public String getWorldGroup(String worldName) {
	//}
}
