package fr.alterya.core;

import org.bukkit.plugin.java.JavaPlugin;

import fr.alterya.core.command.BasicsPlayerCommands;
import net.md_5.bungee.api.ChatColor;
import fr.alterya.core.command.CmdShop;

public class Main extends JavaPlugin
{
	public static String prefix = ChatColor.GOLD + "[Core] ";
	
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
	}
	
	@Override
	public void onDisable() 
	{
		System.out.println("The plugin AlteryaCore is now OFF !");
	}
}
