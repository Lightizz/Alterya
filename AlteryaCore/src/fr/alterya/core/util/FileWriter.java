package fr.alterya.core.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileWriter
{
	private File file;
	private YamlConfiguration config;
	
	public FileWriter(String filePath, String fileName) {
		//this.config = YamlConfiguration.loadConfiguration(this.file);
		//this.file = new File(filePath, fileName);
		initConfig(filePath, fileName);
	}
	
	public void initConfig(String filePath, String fileName) {
		File f = new File(filePath, fileName);
		if(!f.exists()) f.mkdirs();
		file = new File(f, fileName + ".yml");
		if(!file.exists()) {
			try {
				file.createNewFile();
			}catch (IOException ioe) { ioe.printStackTrace();}
		}
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	public FileWriter setValue(String valuePath, Object value) {
		config.set(valuePath, value);
		return this;
	}
	
	public boolean exist() {
		return file.exists();
	}
	
	public Object getObject(String valuePath) {
		return config.get(valuePath);
	}
	
	public double getDouble(String valuePath) {
		return config.getDouble(valuePath);
	}
	
	public int getInt(String valuePath) {
		return config.getInt(valuePath);
	}
	
	public String getString(String valuePath) {
		return config.getString(valuePath);
	}
	
	public boolean getBoolean(String valuePath) {
		return config.getBoolean(valuePath);
	}
	
	public long getLong(String valuePath) {
		return config.getLong(valuePath);
	}
	
	public List<String> getStringList(String valuePath) {
		return config.getStringList(valuePath);
	}
	
	public Set<String> getKeys(boolean deep){
		return config.getKeys(deep);
	}
	
	public ConfigurationSection getConfigurationSection(String Section) {
		return config.getConfigurationSection(Section);
	}
	
	public void saveConfig() {
		try {
			config.save(file);
		} catch (IOException ioe) {ioe.printStackTrace();}
			
		}

}
