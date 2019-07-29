package fr.alterya.faction.integration.capi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import fr.alterya.faction.Main;

public class CapiFeatures
{
	public static void setup()
	{
		Plugin plug = Bukkit.getServer().getPluginManager().getPlugin("capi");
		if (plug != null && plug.getClass().getName().equals("com.massivecraft.capi.P"))
		{
			Main.main.log("Integration with the CAPI plugin was successful");
			Bukkit.getPluginManager().registerEvents(new PluginCapiListener(Main.main), Main.main);
		}
	}
}
