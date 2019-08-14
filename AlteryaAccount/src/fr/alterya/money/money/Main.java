package fr.alterya.money.money;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import fr.alterya.money.money.Settings.Nodes;
import fr.alterya.money.money.command.CmdGivemoney;
import fr.alterya.money.money.command.CmdMain;
import fr.alterya.money.money.command.CmdMoney;
import fr.alterya.money.money.command.CmdSendmoney;
import fr.alterya.money.money.command.CmdSetmoney;
import fr.alterya.money.money.command.CmdTakemoney;
import fr.alterya.money.money.command.CmdTopmoney;
import fr.alterya.money.money.listener.PlayerListener;
import fr.alterya.money.money.manager.AccountManager;
import fr.alterya.money.money.manager.account.AccountData;
import fr.alterya.money.money.manager.account.Interest;

import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	
	private FileConfiguration cfg;

	private Interest interest;

	public void onEnable() {
		
		this.getCommand("money").setExecutor(new CmdMoney(this));
		
		this.getCommand("topmoney").setExecutor(new CmdTopmoney(this));
		
		this.getCommand("sendmoney").setExecutor(new CmdSendmoney(this));
		
		//this.getCommand("sendallmoney").setExecutor(new CmdSendallmoney(this));
		
		this.getCommand("accountcommand").setExecutor(new CmdMain(this));
		
		this.getCommand("givemoney").setExecutor(new CmdGivemoney(this));
		
		this.getCommand("setmoney").setExecutor(new CmdSetmoney(this));
		
		this.getCommand("takemoney").setExecutor(new CmdTakemoney(this));
		
		//this.getCommand("purgemoney").setExecutor(null);
		
		this.saveDefaultConfig();

		this.cfg = this.getConfig();
		
		new Settings(this, cfg);
		
		//new Data(this);
		
		//initializeMessages();
		
		initializeAccounts();

		initializeAccountData();
		
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

		initializeInterest();

		getServer().getServicesManager().register(Economy.class, new fr.alterya.money.money.Vault(), (Vault)Bukkit.getPluginManager().getPlugin("Vault"), ServicePriority.Normal);
	}
	
	public void onDisable() {
		if(interest != null)
			interest.saveState();
	}

	/*
	private void initializeMessages() {
		new Messages(this);
	}
	*/
	
	private void initializeAccounts() {
		new AccountManager(this);
	}

	private void initializeAccountData() {
		new AccountData(this);
	}
	
	private void initializeInterest() {
		
		if(Nodes.INTEREST.getBoolean() == false) return;
		
		interest = new Interest(this);
		interest.runTaskTimer(this, interest.getTimeUntilExecute() * 20L, Nodes.INTERESTINTERVAL.getInt() * 20L);
	}
}
