package fr.alterya.rank;

/*
Author and resp of the rank plugin: Lightiz
*/

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.alterya.rank.command.CmdKit;
import fr.alterya.rank.command.RankCommand;

public class Main extends JavaPlugin
{
	public static String prefix = "§e§l[&4Rank&e&l] ";
	
	private Rank rank;
	
	@Override
	public void onLoad() {

		rank = new Rank(this);
	}	
	
	@Override
	public void onEnable() {
		System.out.println("The plugin AlteryaRank is now ON !");
		
		rank.initScoreboard();
		
		Bukkit.getPluginManager().registerEvents(new PlayerListener(rank), this);
		
		getCommand("rank").setExecutor(new RankCommand(rank));
		getCommand("kit").setExecutor(new CmdKit());
	}
	
	@Override
	public void onDisable() {
		System.out.println("The plugin AlteryaRank is now OFF !");
	}
}
