package fr.alterya.money.money.manager.account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.alterya.money.money.MainAccount;
import fr.alterya.money.money.Settings.Nodes;
import fr.alterya.money.money.storage.Database;
import fr.alterya.money.money.storage.Sqlite;

public class AccountData {

	private static MainAccount plugin;
	
	private static Database db;
	
	/**
	 * Create a new AccountData instance 
	 * 
	 * @param plugin Account plugin instance
	 */
	public AccountData(MainAccount plugin) {
		
		AccountData.plugin = plugin;
		
		if(Nodes.DATABASEMYSQL.getBoolean()) {
			
			
			
		} else {
			
			db = new Sqlite("accounts.db", plugin.getDataFolder().getAbsolutePath());
			
		}
		
		db.execute("CREATE TABLE IF NOT EXISTS `accounts` (  `id` INTEGER PRIMARY KEY,  `userid` VARCHAR(40) NOT NULL, `usergroup` VARCHAR(20) NOT NULL,  `balance` INT(11) NOT NULL);");
		
	}
	
	/**
	 * Create an account with an account name
	 * 
	 * @param userId 
	 * @param balance The balance for the new account
	 * @group The group for the new account
	 * @return False if the account already exists
	 */
	public static boolean createAccount(String userId, String group, double balance) {

		if(hasAccount(userId, group)) return false;
		
		db.execute("INSERT INTO accounts (userid, usergroup, balance) VALUES('" + userId + "', '" + group + "', " + balance + ");");
		
		Bukkit.getLogger().info("[Acccount] User account " + userId + " has been created for " + group);
		
		return true;
		
	}

	/**
	 * Check if an account exists
	 * @param accountNumber
	 * @return
	 */
	public static boolean hasAccount(String userId, String group) {
		
		String query = "SELECT id FROM accounts WHERE userid = '" + userId + "' AND usergroup = '" + group + "';";
		
		ResultSet rs = db.select(query);
		
		try {
			
			return rs.isBeforeFirst();
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {
			
			db.closeDataConnection();
			
			try {
				
				rs.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		return false;
		
	}
	
	/**
	 * Remove an account from the database
	 * @param accountNumber
	 * @throws SQLException
	 */
	public static void removeAccount(String userId, String group) {
		
		db.execute("DELETE FROM accounts WHERE userid = '" + userId + "' AND usergroup = '" + group + "'");
		
	}
	
	/**
	 * Get the balance of an account
	 * @param accountNumber
	 * @return
	 * @throws SQLException
	 */
	public static double getAccountBalance(String userId, String group) {
		
		String query = "SELECT balance FROM accounts WHERE userid = '" + userId + "' AND usergroup = '" + group + "'";
		
		ResultSet rs = db.select(query);

		try {
			
			if(rs.next()) {
				
				return rs.getInt("balance");
				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
			
		} finally {
			
			db.closeDataConnection();
			
			try {
				
				rs.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
				
			}
			
		}
		
		return 0.00;
		
	}
	
	/**
	 * 
	 * @param accountNumber
	 * @param newBalance
	 * @throws SQLException
	 */
	public static void setAccountBalance(String userId, String group, double newBalance) {
		
		db.update("UPDATE accounts SET balance = " + newBalance + " WHERE userid = '" + userId + "' AND usergroup = '" + group + "'");
		
	}
	
	/**
	 * Change all balances on the server, online or offline
	 * @param online
	 * @param amount
	 */
	@SuppressWarnings("deprecation")
	public static void updateAllBalances(boolean online, double amount) {
		
		if(online) {
			
			for(Player p : Bukkit.getOnlinePlayers()) {
				
				new Account(p.getUniqueId().toString(), "default", plugin).getHoldings().add(amount);
				
			}
			
		} else {
		
			db.update("UPDATE accounts SET balance = balance + " + amount + ";");
			
		}
		
	}
	
	/**
	 * Get the top balances in the server
	 * Maximum amount is 50
	 * @param amount
	 * @return
	 */
	public static List<Account> getTopAccounts(double amount) {
		
		if(amount > 50.00) amount = 50.00;
		
		String query = "SELECT * FROM accounts ORDER BY balance DESC LIMIT " + amount + ";";
		
		List<Account> accounts = new ArrayList<Account>();
		
		ResultSet rs = null;
		
		try {
			
			rs = db.select(query);
			
			while(rs.next()) {

				accounts.add(new Account(rs.getString("userid"), rs.getString("usergroup"), plugin));
				
			}
			
		} catch(SQLException e) {
			
			Bukkit.getLogger().info(e.getMessage());
			
		} finally {
			
			try {
				
				rs.close();
				
			} catch (SQLException e) {

				e.printStackTrace();
				
			}
			
			db.closeDataConnection();
			
		}
		
		return accounts;
		
	}
	
}
