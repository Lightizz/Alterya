package fr.alterya.core.home;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

import fr.alterya.core.util.FileWriter;

public class HomeManager
{	
	Plugin plugin;
	private FileWriter fw;
	
	public HomeManager(String uuid) {
		fw = new FileWriter("ServerData/Homes", uuid.toString());
	}
	
	public void createHome(Location homeLoc, String homeName) {
		fw.setValue(homeName + ".world", homeLoc.getWorld().getName());
		fw.setValue(homeName + ".x", homeLoc.getX());
		fw.setValue(homeName + ".y", homeLoc.getY());
		fw.setValue(homeName + ".z", homeLoc.getZ());
		fw.setValue(homeName + ".yaw", homeLoc.getYaw());
		fw.setValue(homeName + ".pitch", homeLoc.getPitch());
		
		fw.saveConfig();
	}
	
	//Regarder si le home indiqu� existe
	public boolean homeExist(String homeName) {
		return fw.getString(homeName) != null;
	}
	
	//R�cuperer la liste des homes d'un joueur
	public Set<String> getHomes() {
		return fw.getKeys(false);
	}
	
	//R�cuperer la location d'un home
	public Location getHomeLocation(String homeName) {
		return new Location(Bukkit.getWorld(fw.getString(homeName + ".world"))
				, fw.getDouble(homeName + ".x")
				, fw.getDouble(homeName + ".y")
				, fw.getDouble(homeName + ".z")
				, (float) fw.getDouble(homeName + ".yaw")
				, (float) fw.getDouble(homeName + ".pitch"));
	}
	
	//Supprimer un home de la liste du joueur
	public void deleteHome(String homeName) {
		fw.setValue(homeName, null);
		fw.saveConfig();
	}
}