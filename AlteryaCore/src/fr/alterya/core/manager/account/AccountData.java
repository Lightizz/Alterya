package fr.alterya.core.manager.account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.alterya.core.Settings.Nodes;
import fr.alterya.money.Main;
import fr.alterya.money.storage.Database;
import fr.alterya.money.storage.Sqlite;

public class AccountData {

	private static Main plugin;

	private static Database db;

	public AccountData() {
		
		if(Nodes.DATABASEMYSQL.getBoolean()) {
			
		} else {
			db = new Sqlite("accounts.db", plugin.getDataFolder().getAbsolutePath());
		}
		db.execute("CREATE TABLE IF NOT EXISTS `accounts` (  `id` INTEGER PRIMARY KEY,  `userid` VARCHAR(40) NOT NULL, `usergroup` VARCHAR(20) NOT NULL,  `balance` INT(11) NOT NULL);");
	}

	public static boolean createAccount(String userId, String group, int balance) {

		if(hasAccount(userId, group)) return false;
		db.execute("INSERT INTO accounts (userid, usergroup, balance) VALUES('" + userId + "', '" + group + "', " + balance + ");");
		Bukkit.getLogger().info("[BConomy] User account " + userId + " has been created for " + group);
		return true;
	}
	
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
	
	public static void removeAccount(String userId, String group) {
		
		db.execute("DELETE FROM accounts WHERE userid = '" + userId + "' AND usergroup = '" + group + "'");
	}

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
		return 0;
	}
	
	public static void setAccountBalance(String userId, String group, double newBalance) {
		
		db.update("UPDATE accounts SET balance = " + newBalance + " WHERE userid = '" + userId + "' AND usergroup = '" + group + "'");
		
	}
	

	@SuppressWarnings("deprecation")
	public static void updateAllBalances(boolean online, int amount) {
		
		if(online) {
			
			for(Player p : Bukkit.getOnlinePlayers()) {
				
				new Account(p.getUniqueId().toString(), "default", plugin).getHoldings().add(amount);
				
			}
			
		} else {
		
			db.update("UPDATE accounts SET balance = balance + " + amount + ";");
			
		}
		
	}

	public static List<Account> getTopAccounts(int amount) {
		
		if(amount > 50) amount = 50;
		
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
