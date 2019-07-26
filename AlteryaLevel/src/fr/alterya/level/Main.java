package fr.alterya.level;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	@Override
	public void onEnable() 
	{
		System.out.println("The plugin AlteryaLevel is now ON !");
	}
	
	@Override
	public void onDisable() 
	{
		System.out.println("The plugin AlteryaLevel is now OFF !");
	}
}
