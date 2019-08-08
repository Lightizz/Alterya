package fr.alterya.core;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.alterya.core.Settings.Nodes;
import fr.alterya.core.command.BasicsPlayerCommands;
import fr.alterya.core.command.CmdGivemoney;
import fr.alterya.core.command.CmdMoney;
import fr.alterya.core.command.CmdSendmoney;
import fr.alterya.core.command.CmdSetmoney;
import net.md_5.bungee.api.ChatColor;
import fr.alterya.core.command.CmdShop;
import fr.alterya.core.command.CmdTakemoney;
import fr.alterya.core.command.CmdTopmoney;
import fr.alterya.core.manager.AccountManager;
import fr.alterya.core.manager.account.AccountData;
import fr.alterya.core.manager.account.Interest;

public class Main extends JavaPlugin
{
	public static String prefix = ChatColor.GOLD + "[Core] ";
	
	@SuppressWarnings("unused")
	private FileConfiguration cfg;
	
	Main m;
	
	Player player;
	
	private Interest interest;
	
	Shop shop;
	
	@Override
	public void onEnable() 
	{
		System.out.println("The plugin AlteryaCore is now ON !");
		
		getCommand("ec").setExecutor(new BasicsPlayerCommands());
		getCommand("craft").setExecutor(new BasicsPlayerCommands());
		getCommand("discord").setExecutor(new BasicsPlayerCommands());
		getCommand("ping").setExecutor(new BasicsPlayerCommands());
		getCommand("tipeee").setExecutor(new BasicsPlayerCommands());
		getCommand("shop").setExecutor(new CmdShop());
		getCommand("money").setExecutor(new CmdMoney());
		getCommand("topmoney").setExecutor(new CmdTopmoney());
		getCommand("sendmoney").setExecutor(new CmdSendmoney());
		getCommand("givemoney").setExecutor(new CmdGivemoney());
		getCommand("setmoney").setExecutor(new CmdSetmoney());
		getCommand("takemoney").setExecutor(new CmdTakemoney());
	
		initializeAccounts();
		
		initializeAccountData();
		
		getServer().getPluginManager().registerEvents(new fr.alterya.money.listeners.PlayerListener(), this);
		
		initializeInterest();
	}
	
	@Override
	public void onDisable() 
	{
		if(interest != null)
			interest.saveState();
		
		System.out.println("The plugin AlteryaCore is now OFF !");
	}
	
	private void initializeAccounts() {
		new AccountManager();
	}
	
	private void initializeAccountData() {
		
		new AccountData();
		
	}
	
	private void initializeInterest() {
		
		if(Nodes.INTEREST.getBoolean() == false) return;
		
		interest = new Interest();
		
		interest.runTaskTimer(this, interest.getTimeUntilExecute() * 20L, Nodes.INTERESTINTERVAL.getInt() * 20L);
		
	}
}
