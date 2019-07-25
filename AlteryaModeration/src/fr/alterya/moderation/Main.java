package fr.alterya.moderation;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	@Override
	public void onEnable() 
	{
		System.out.println("The plugin AlteryaModeration is now ON !");
	}
	
	@Override
	public void onDisable() 
	{
		System.out.println("The plugin AlteryaModeration is now OFF !");
	}
}
