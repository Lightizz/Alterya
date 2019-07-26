package fr.alterya.core;

import org.bukkit.plugin.java.JavaPlugin;

import fr.alterya.core.command.BasicsPlayerCommands;

public class Main extends JavaPlugin
{
	public static String prefix = "§e§l[&4Core&e&l]";
	
	@Override
	public void onEnable() 
	{
		System.out.println("The plugin AlteryaCore is now ON !");
		
		getCommand("ec").setExecutor(new BasicsPlayerCommands());
		getCommand("craft").setExecutor(new BasicsPlayerCommands());
		getCommand("discord").setExecutor(new BasicsPlayerCommands());
		getCommand("ping").setExecutor(new BasicsPlayerCommands());
	}
	
	@Override
	public void onDisable() 
	{
		System.out.println("The plugin AlteryaCore is now OFF !");
	}
}
