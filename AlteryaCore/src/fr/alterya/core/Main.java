package fr.alterya.core;

import org.bukkit.plugin.java.JavaPlugin;

import fr.alterya.core.command.BasicsPlayerCommands;
import fr.alterya.core.command.CmdHome;
import fr.alterya.core.command.CmdShop;
import fr.alterya.core.event.ShopInterractEvent;
import fr.alterya.core.shop.Shop;
import fr.alterya.money.money.MainAccount;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin
{
	public static String prefix = ChatColor.GOLD + "[Core] ";
	
	MainAccount accountPlugin;
	
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
		getCommand("home").setExecutor(new CmdHome());
		getCommand("delhome").setExecutor(new CmdHome());
		getCommand("sethome").setExecutor(new CmdHome());
		getCommand("homeinfo").setExecutor(new CmdHome());
		
		getServer().getPluginManager().registerEvents(new ShopInterractEvent(this, accountPlugin), this);
	}
	
	@Override
	public void onDisable() 
	{	
		System.out.println("The plugin AlteryaCore is now OFF !");
	}
}
