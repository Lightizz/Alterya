package fr.alterya.money;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import fr.alterya.money.Settings.Nodes;
import fr.alterya.money.manager.AccountManager;
import fr.alterya.money.manager.account.AccountData;
import fr.alterya.money.manager.account.Interest;
import fr.alterya.money.command.CmdGivemoney;
import fr.alterya.money.command.CmdSendmoney;
import fr.alterya.money.command.CmdSetmoney;
import fr.alterya.money.command.CmdTakemoney;
import fr.alterya.money.command.CmdTopmoney;

import fr.alterya.money.command.CmdMoney;

import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {

	private FileConfiguration cfg;

	public static String prefix = "§e[&4Rank&e] §r";
	
	private Interest interest;
	
	@Override
	public void onEnable() {
		
		this.getCommand("money").setExecutor(new CmdMoney(this));
		this.getCommand("topmoney").setExecutor(new CmdTopmoney(this));
		this.getCommand("sendmoney").setExecutor(new CmdSendmoney(this));
		this.getCommand("givemoney").setExecutor(new CmdGivemoney(this));
		this.getCommand("setmoney").setExecutor(new CmdSetmoney(this));
		this.getCommand("takemoney").setExecutor(new CmdTakemoney(this));
	
		this.saveDefaultConfig();
		
		this.cfg = this.getConfig();
		
		new Settings(this, cfg);
		
		initializeMessages();
		
		initializeAccounts();
		
		initializeAccountData();
		
		getServer().getPluginManager().registerEvents(new fr.alterya.money.listeners.PlayerListener(), this);
		
		initializeInterest();

		getServer().getServicesManager().register(Economy.class, new fr.alterya.money.Vault(), (Vault)Bukkit.getPluginManager().getPlugin("Vault"), ServicePriority.Normal);

	}
	
	@Override
	public void onDisable() {
		if(interest != null)
			interest.saveState();
	}

	private void initializeMessages() {
		new Messages(this);
	}
	
	private void initializeAccounts() {
		new AccountManager(this);
	}
	
	/**
	 * Create an instance of the account data class
	 * used for underlying data structures
	 */
	private void initializeAccountData() {
		
		new AccountData(this);
		
	}
	
	private void initializeInterest() {
		
		if(Nodes.INTEREST.getBoolean() == false) return;
		
		interest = new Interest(this);
		
		interest.runTaskTimer(this, interest.getTimeUntilExecute() * 20L, Nodes.INTERESTINTERVAL.getInt() * 20L);
		
	}

}
