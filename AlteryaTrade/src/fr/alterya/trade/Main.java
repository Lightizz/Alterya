package fr.alterya.trade;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	@Override
	public void onEnable() 
	{
		System.out.println("The plugin AlteryaTrade is now ON !");
	}
	
	@Override
	public void onDisable() 
	{
		System.out.println("The plugin AlteryaTrade is now OFF !");
	}
}
