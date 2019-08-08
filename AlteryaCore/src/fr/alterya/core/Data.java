package fr.alterya.core;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.alterya.money.Main;

public class Data {

	private static FileConfiguration cfg;
	
	private static File persistanceFile;
	
	@SuppressWarnings("unused")
	private Main plugin;
	
	public Data(Main plugin) {
		this.plugin = plugin;
		persistanceFile = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "persistance.yml");
		if(!persistanceFile.exists()) {
			plugin.saveResource("data.yml", false);
		}
		cfg = YamlConfiguration.loadConfiguration(persistanceFile);
	}
	
	public static long getLong(String node) {
		return cfg.getLong(node);
	}
	
	public static void setLong(String node, long value) {
		cfg.set(node, value);
		save();
	}
	
	private static void save() {
		try {	
			cfg.save(persistanceFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}