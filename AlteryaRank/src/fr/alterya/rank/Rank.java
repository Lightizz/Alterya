package fr.alterya.rank;

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
	
	private FileConfiguration config;
	private File file;
	
	public Rank(Plugin plugin) 
	{
		this.plugin = plugin;
		initConfig();
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
		
		if(scoreboard != null) throw new UnsupportedOperationException("ScoreBoard déja initialise.");
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		
		for(RankList rankList : RankList.values()) {
			
			Team team = scoreboard.registerNewTeam(rankList.getName());
			team.setPrefix(rankList.getPrefix());
			team.setSuffix(rankList.getSuffix());
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
		if(playerRanks.containsKey(player.getUniqueId().toString())) return;
		playerRanks.remove(player.getUniqueId() .toString());
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
		if(rankList == RankList.ADMINISTRATEUR) {
			playerRanks.put(target.getUniqueId().toString(), RankList.ADMINISTRATEUR);
		}else if(rankList == RankList.RESPONSABLE) {
			playerRanks.put(target.getUniqueId().toString(), RankList.RESPONSABLE);
		}else if(rankList == RankList.ARCHITECTE) {
			playerRanks.put(target.getUniqueId().toString(), RankList.ARCHITECTE);
		}else if(rankList == RankList.DEVELOPPEUR) {
			playerRanks.put(target.getUniqueId().toString(), RankList.DEVELOPPEUR);
		}else if(rankList == RankList.GUIDE) {
			playerRanks.put(target.getUniqueId().toString(), RankList.GUIDE);
		}else if(rankList == RankList.MODERATEUR) {
			playerRanks.put(target.getUniqueId().toString(), RankList.MODERATEUR);
		}else if(rankList == RankList.MODERATEUR_PLUS) {
			playerRanks.put(target.getUniqueId().toString(), RankList.MODERATEUR_PLUS);
		}else if(rankList == RankList.SOUVENIR) {
			playerRanks.put(target.getUniqueId().toString(), RankList.SOUVENIR);
		}else if(rankList == RankList.MEMOIRE) {
			playerRanks.put(target.getUniqueId().toString(), RankList.MEMOIRE);
		}else if(rankList == RankList.SAGE) {
			playerRanks.put(target.getUniqueId().toString(), RankList.SAGE);
		}
	}
	
	public void removeRank(RankList rankList, Player target) {
		if(rankList == RankList.ADMINISTRATEUR) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.ADMINISTRATEUR);
		}else if(rankList == RankList.RESPONSABLE) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.RESPONSABLE);
		}else if(rankList == RankList.ARCHITECTE) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.ARCHITECTE);
		}else if(rankList == RankList.DEVELOPPEUR) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.DEVELOPPEUR);
		}else if(rankList == RankList.GUIDE) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.GUIDE);
		}else if(rankList == RankList.MODERATEUR) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.MODERATEUR);
		}else if(rankList == RankList.MODERATEUR_PLUS) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.MODERATEUR_PLUS);
		}else if(rankList == RankList.SOUVENIR) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.SOUVENIR);
		}else if(rankList == RankList.MEMOIRE) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.MEMOIRE);
		}else if(rankList == RankList.SAGE) {
			playerRanks.remove(target.getUniqueId().toString(), RankList.SAGE);
		}
	}
	
	public void saveConfig() {
		try {
			config.save(file);
		} catch (IOException ioe) {ioe.printStackTrace();}
			
		}
}
