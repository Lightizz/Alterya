package fr.alterya.core.manager.account;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.core.Main;
import fr.alterya.core.Settings.Nodes;
import fr.alterya.core.storage.Flatfile;
import fr.alterya.core.utils.TimeUtil;

public class Interest extends BukkitRunnable {

	private Main plugin;
	
	private Random rand;
	
	private long lastRun;
	
	private Flatfile interestFile;
	
	public Interest() {
		
		interestFile = new Flatfile(plugin, "interest.yml", plugin.getDataFolder(), "interest.yml");
		
		rand = new Random();
		
		if(interestFile.getLong("Interest.Last") == 0) {
			
			lastRun = TimeUtil.getTimeSeconds();
			
		} else {
		
			lastRun = interestFile.getLong("Interest.Last");
		
		}
		
	}
	
	@Override
	public void run() {
		
		int amount = rand.nextInt(Nodes.INTERESTMAX.getInt()) + Nodes.INTERESTMIN.getInt();
		
		if(Nodes.INTERESTANNOUNCE.getBoolean()) Bukkit.broadcastMessage("&2Its interest day! &a" + Holdings.format(amount) + " &2has been paid to your account.");
			
		AccountData.updateAllBalances(Nodes.INTERESTONLINE.getBoolean(), amount);
		
		saveState();
		
	}
	
	public void saveState() {
		
		interestFile.setLong("Interest.State", getTimeUntilExecute());
		
		long currentTime = TimeUtil.getTimeSeconds();
		
		interestFile.setLong("Interest.Last", currentTime);
		
		lastRun = currentTime;
		
	}
	
	public long getTimeUntilExecute() {
		
		long currentTime = TimeUtil.getTimeSeconds();
		
		long interval = Nodes.INTERESTINTERVAL.getInt();
		
		return (lastRun + interval) - currentTime;
		
	}

}
