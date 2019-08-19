package fr.alterya.rank;

/*
Author and resp of the rank plugin: Lightiz
*/

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
//import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.minecraft.util.com.google.common.collect.Maps;

public final class Rank { 
	private Scoreboard scoreboard;
	static Plugin plugin;
	public Map<String, RankList> playerRanks = Maps.newHashMap();
	
	static Player player;
	
	private FileConfiguration config;
	private File file;
	
	public static int rankCount = 0;
	
	public Rank(Plugin plugin, Player player) 
	{	
		this.player = player;
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
	
	public static Rank getRank() {
		return new Rank(plugin, player);
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
	
	public void loadPlayer(Player player1) {
		player = player1;
		String uuid = player1.getUniqueId().toString();
		if(playerRanks.containsKey(uuid)) {
			return;
		}
		if(!config.contains(uuid)) {
			config.set(uuid, 0);
			saveConfig();
			playerRanks.put(uuid, RankList.JOUEUR);
		}
		
		playerRanks.put(uuid, getRankById(config.getInt(uuid)));
		
		scoreboard.getTeam(playerRanks.get(uuid).getName()).addEntry(player1.getName());
	}
	
	public void deletPlayer(String uuid) {
		if(config.contains(uuid)) {
			config.set(uuid, null);
			playerRanks.put(uuid, RankList.JOUEUR);
		}
		playerRanks.remove(uuid);
		config.set(uuid, null);
	}
	
	public RankList getPlayerRank (String uuid, Player player) {
		uuid = player.getUniqueId().toString();
		if(!config.contains(uuid)) {
			loadPlayer(player);
		}
			return getRankById(config.getInt(uuid));
	}
	
	public RankList getRankById(int id) {
			if(id == 0) {
				return RankList.JOUEUR;
			}else if(id == 1) {
				return RankList.SOUVENIR;
			}else if(id == 2) {
				return RankList.MEMOIRE;
			}else if(id == 3) {
				return RankList.SAGE;
			}else if(id == 4) {
				return RankList.DEVELOPPEUR;
			}else if(id == 5) {
				return RankList.ARCHITECTE;
			}else if(id == 6) {
				return RankList.GUIDE;
			}else if(id == 7) {
				return RankList.MODERATEUR;
			}else if(id == 8) {
				return RankList.MODERATEUR_PLUS;
			}else if(id == 9) {
				return RankList.RESPONSABLE;
			}else if(id == 10) {
				return RankList.ADMINISTRATEUR;
			}
			return null;
	}
	
	public void addRank(RankList rankList, Player target) {
		if(rankList == RankList.ADMINISTRATEUR && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.ADMINISTRATEUR);
			config.set(target.getUniqueId().toString(), 10);
			rankCount = 1;
		}else if(rankList == RankList.RESPONSABLE && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.RESPONSABLE);
			config.set(target.getUniqueId().toString(), 9);
			rankCount = 1;
		}else if(rankList == RankList.ARCHITECTE && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.ARCHITECTE);
			config.set(target.getUniqueId().toString(), 5);
			rankCount = 1;
		}else if(rankList == RankList.DEVELOPPEUR && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.DEVELOPPEUR);
			config.set(target.getUniqueId().toString(), 4);
			rankCount = 1;
		}else if(rankList == RankList.GUIDE && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.GUIDE);
			config.set(target.getUniqueId().toString(), 6);
			rankCount = 1;
		}else if(rankList == RankList.MODERATEUR && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.MODERATEUR);
			config.set(target.getUniqueId().toString(), 7);
			rankCount = 1;
		}else if(rankList == RankList.MODERATEUR_PLUS && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.MODERATEUR_PLUS);
			config.set(target.getUniqueId().toString(), 8);
			rankCount = 1;
		}else if(rankList == RankList.SOUVENIR && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.SOUVENIR);
			config.set(target.getUniqueId().toString(), 1);
			rankCount = 1;
		}else if(rankList == RankList.MEMOIRE && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.MEMOIRE);
			config.set(target.getUniqueId().toString(), 2);
			rankCount = 1;
		}else if(rankList == RankList.SAGE && rankCount != 1) {
			playerRanks.put(target.getUniqueId().toString(), RankList.SAGE);
			config.set(target.getUniqueId().toString(), 3);
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
