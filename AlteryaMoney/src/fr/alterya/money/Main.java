package fr.alterya.money;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.alterya.core.Settings;
import fr.alterya.core.Settings.Nodes;
import fr.alterya.core.command.CmdGivemoney;
import fr.alterya.core.command.CmdMoney;
import fr.alterya.core.command.CmdSendmoney;
import fr.alterya.core.command.CmdSetmoney;
import fr.alterya.core.command.CmdTakemoney;
import fr.alterya.core.command.CmdTopmoney;
import fr.alterya.core.manager.AccountManager;
import fr.alterya.core.manager.account.AccountData;
import fr.alterya.core.manager.account.Interest;

public class Main extends JavaPlugin {

	private FileConfiguration cfg;

	public static String prefix = "§e[&4Account&e] §r";
	
	Player player;
	
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
		
		//initializeMessages();
		
		initializeAccounts();
		
		initializeAccountData();
		
		getServer().getPluginManager().registerEvents(new fr.alterya.core.PlayerListener(), this);
		
		initializeInterest();

		//getServer().getServicesManager().register(Economy.class, new fr.alterya.money.Vault(), (Vault)Bukkit.getPluginManager().getPlugin("Vault"), ServicePriority.Normal);

	}
	
	@Override
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
