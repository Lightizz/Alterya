package fr.alterya.pvp;

/*
Author and resp of the hunt / déco combat : Lightiz
*/

import org.bukkit.plugin.java.JavaPlugin;

import fr.alterya.pvp.command.CmdHunt;
import fr.alterya.pvp.listeners.InCombatListener;
import fr.alterya.pvp.task.HuntTimer;

public class Main extends JavaPlugin
{
	HuntTimer huntT;
	
	public static final String prefix = "§e§l[&4PVP&e&l] ";
	
	@Override
	public void onEnable() 
	{
		System.out.println("The plugin AlteryaPVP is now ON !");
		
		huntT.runTaskTimer(this, 20, 20);
		
		getCommand("hunt").setExecutor(new CmdHunt());
		
		getServer().getPluginManager().registerEvents(new InCombatListener(), this);
	}
	
	@Override
	public void onDisable() 
	{
		System.out.println("The plugin AlteryaPVP is now OFF !");
	}
}
