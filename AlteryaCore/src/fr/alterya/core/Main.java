package fr.alterya.core;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.alterya.core.command.BasicsPlayerCommands;
import fr.alterya.core.command.CmdHome;
import fr.alterya.core.command.CmdShop;
import fr.alterya.core.event.ShopInterractEvent;
import fr.alterya.core.shop.Shop;
import fr.alterya.rank.Rank;
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
		getCommand("home").setExecutor(new CmdHome(this.rank, this));
		getCommand("delhome").setExecutor(new CmdHome(this.rank, this));
		getCommand("sethome").setExecutor(new CmdHome(this.rank, this));
		getCommand("homeinfo").setExecutor(new CmdHome(this.rank, this));
		
		getServer().getPluginManager().registerEvents(new ShopInterractEvent(), this);
	}
	
	@Override
	public void onDisable() 
	{	
		System.out.println("The plugin AlteryaCore is now OFF !");
	}
}
