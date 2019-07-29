package fr.alterya.faction.integration.herochat;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import fr.alterya.faction.Main;

public class HerochatFeatures implements Listener
{
	public static void setup()
	{
		Plugin plug = Bukkit.getServer().getPluginManager().getPlugin("Herochat");
		if (plug == null) return;
		if (!plug.getClass().getName().equals("com.dthielke.herochat.Herochat")) return;
		Bukkit.getPluginManager().registerEvents(new HerochatListener(Main.main), Main.main);
		Main.main.log("Integration with Herochat successful");
	}
}
