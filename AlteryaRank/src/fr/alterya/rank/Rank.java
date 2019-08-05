package fr.alterya.rank;

/*
Author and resp of the rank plugin: Lightiz
*/

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.minecraft.util.com.google.common.collect.Maps;

public final class Rank { 
	private Scoreboard scoreboard;
	private final Plugin plugin;
	private final Map<String, RankList> playerRanks = Maps.newHashMap();
	
	Player player;
	
	private FileConfiguration config;
	private File file;
	
	public static int rankCount = 0;
	
	public Rank(Plugin plugin) 
	{	
		this.plugin = plugin;
		initConfig();
		
		if(rankCount == 0) {
			this.addRank(RankList.JOUEUR, player);
		}
	}
	
	public final Plugin getPlugin() 
	{
		return plugin;
	}
	
	public final Scoreboard getScoreboard() 
	{
		return scoreboard;
	}
	
	private void initConfig() {
		File f = new File("plugins/Rank");
		if(!f.exists()) f.mkdirs();
		file = new File(f, "rank.yml");
		if(!file.exists()) {
			try {
				file.createNewFile();
			}catch (IOException ioe) { ioe.printStackTrace();}
		}
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	public void initScoreboard() {
		
		if(scoreboard != null) throw new UnsupportedOperationException("ScoreBoard déja initialisé.");
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		
		for(RankList rankList : RankList.values()) {
			
			Team team = scoreboard.registerNewTeam(rankList.getName());
			team.setPrefix(rankList.getPrefix());
		}
	}
	
	public void loadPlayer(Player player) {
		String uuid = player.getUniqueId() .toString();
		if(playerRanks.containsKey(uuid)) return;
		if(!config.contains(uuid)) {
			config.set(uuid, 1);
			saveConfig();
		}
		
		playerRanks.put(uuid, getRankById(config.getInt(uuid)));
		
		scoreboard.getTeam(playerRanks.get(uuid).getName()).addEntry(player.getName());
	}
	
	public void deletPlayer(Player player) {
		if(playerRanks.containsKey(player.getUniqueId().toString())) {
			playerRanks.remove(player.getUniqueId().toString());
		}
		playerRanks.remove(player.getUniqueId().toString());
	}
	
	public RankList getPlayerRank (Player player) {
		if(!playerRanks.containsKey(player.getUniqueId().toString())); loadPlayer(player);
		return playerRanks.get(player.getUniqueId().toString());
	}
	
	public RankList getRankById(int id) {
		for(RankList rankList : RankList.values()) {
			if(rankList.GetId() == id) return rankList;
		}
		return RankList.JOUEUR;
	}
	
	public void addRank(RankList rankList, Player target) {
		if(rankList == RankList.ADMINISTRATEUR && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.ADMINISTRATEUR);
			rankCount = 1;
		}else if(rankList == RankList.RESPONSABLE && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.RESPONSABLE);
			rankCount = 1;
		}else if(rankList == RankList.ARCHITECTE && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.ARCHITECTE);
			rankCount = 1;
		}else if(rankList == RankList.DEVELOPPEUR && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.DEVELOPPEUR);
			rankCount = 1;
		}else if(rankList == RankList.GUIDE && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.GUIDE);
			rankCount = 1;
		}else if(rankList == RankList.MODERATEUR && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.MODERATEUR);
			rankCount = 1;
		}else if(rankList == RankList.MODERATEUR_PLUS && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.MODERATEUR_PLUS);
			rankCount = 1;
		}else if(rankList == RankList.SOUVENIR && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.SOUVENIR);
			rankCount = 1;
		}else if(rankList == RankList.MEMOIRE && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.MEMOIRE);
			rankCount = 1;
		}else if(rankList == RankList.SAGE && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.SAGE);
			rankCount = 1;
		}
	}
	
	public void removeRank(RankList rankList, Player target) {
		if(rankList == RankList.ADMINISTRATEUR && rankCount != 0) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.ADMINISTRATEUR);
			rankCount = 0;
		}else if(rankList == RankList.RESPONSABLE && rankCount != 0) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.RESPONSABLE);
			rankCount = 0;
		}else if(rankList == RankList.ARCHITECTE && rankCount != 0) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.ARCHITECTE);
			rankCount = 0;
		}else if(rankList == RankList.DEVELOPPEUR && rankCount != 0) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.DEVELOPPEUR);
			rankCount = 0;
		}else if(rankList == RankList.GUIDE && rankCount != 0) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.GUIDE);
			rankCount = 0;
		}else if(rankList == RankList.MODERATEUR && rankCount != 0) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.MODERATEUR);
			rankCount = 0;
		}else if(rankList == RankList.MODERATEUR_PLUS && rankCount != 0) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.MODERATEUR_PLUS);
			rankCount = 0;
		}else if(rankList == RankList.SOUVENIR && rankCount != 0) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.SOUVENIR);
			rankCount = 0;
		}else if(rankList == RankList.MEMOIRE && rankCount != 0) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.MEMOIRE);
			rankCount = 0;
		}else if(rankList == RankList.SAGE && rankCount != 0) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.SAGE);
			rankCount = 0;
		}
	}
	
	public void saveConfig() {
		try {
			config.save(file);
		} catch (IOException ioe) {ioe.printStackTrace();}
			
		}
}
