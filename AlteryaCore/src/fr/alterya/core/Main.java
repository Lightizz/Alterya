package fr.alterya.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.alterya.core.command.BasicsPlayerCommands;
import fr.alterya.core.command.CmdBaltop;
import fr.alterya.core.command.CmdGiveMoney;
import fr.alterya.core.command.CmdHome;
import fr.alterya.core.command.CmdKit;
import fr.alterya.core.command.CmdMoney;
import fr.alterya.core.command.CmdPay;
import fr.alterya.core.command.CmdPurgeMoney;
import fr.alterya.core.command.CmdSetMoney;
import fr.alterya.core.command.CmdShop;
import fr.alterya.core.command.CmdTakeMoney;
import fr.alterya.core.command.RankCommand;
import fr.alterya.core.event.ShopInterractEvent;
import fr.alterya.core.listeners.PlayerListener;
import fr.alterya.core.rank.Rank;
import fr.alterya.core.shop.Shop;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin
{
	public static String prefix = ChatColor.GOLD + "[Core] ";
		
	Shop shop;
	
	Player player;
	
	public Rank rank;
	
	@Override
	public void onLoad() {

		rank = new Rank(this, player);
	}	
	
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
		
		getCommand("money").setExecutor(new CmdMoney(rank));
		getCommand("pay").setExecutor(new CmdPay(rank));
		getCommand("takemoney").setExecutor(new CmdTakeMoney(rank));
		getCommand("givemoney").setExecutor(new CmdGiveMoney(rank));
		getCommand("purgemoney").setExecutor(new CmdPurgeMoney(rank));
		getCommand("setmoney").setExecutor(new CmdSetMoney(rank));
		getCommand("baltop").setExecutor(new CmdBaltop());
		
		getCommand("home").setExecutor(new CmdHome(this.rank, this));
		getCommand("delhome").setExecutor(new CmdHome(this.rank, this));
		getCommand("sethome").setExecutor(new CmdHome(this.rank, this));
		getCommand("homeinfo").setExecutor(new CmdHome(this.rank, this));
		
		rank.initScoreboard();
		
		Bukkit.getPluginManager().registerEvents(new PlayerListener(rank), this);
		
		getCommand("rank").setExecutor(new RankCommand(rank));
		getCommand("unrank").setExecutor(new RankCommand(rank));
		getCommand("kit").setExecutor(new CmdKit());
		
		getServer().getPluginManager().registerEvents(new ShopInterractEvent(), this);
	}
	
	@Override
	public void onDisable() 
	{	
		System.out.println("The plugin AlteryaCore is now OFF !");
	}
}
