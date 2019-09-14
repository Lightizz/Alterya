package fr.alterya.core.rank;

/*
Author and resp of the rank /perms plugin: Lightiz
*/

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Rank { 
	private Scoreboard scoreboard;
	static Plugin plugin;
	
	static Player player;
	
	public FileConfiguration config;
	public File file;
	
	public static int rankCount = 0;
	
	@SuppressWarnings("static-access")
	public Rank(Plugin plugin, Player player) 
	{	
		this.player = player;
		this.plugin = plugin;
		initConfig();
		
		if(rankCount == 0) {
			this.addRank(RankList.JOUEUR, player);
		}
	}
	
	//Récuperer la class main
	public final Plugin getPlugin() {
		return plugin;
	}
	
	//Récuperer le scoreboard
	public final Scoreboard getScoreboard() {
		return scoreboard;
	}
	
	//Récuperer la class
	public static Rank getRank() {
		return new Rank(plugin, player);
	}
	
	//Initialiser la configuration
	private void initConfig() {
		File f = new File("ServerData");
		if(!f.exists()) f.mkdirs();
		file = new File(f, "PlayersRank.yml");
		if(!file.exists()) {
			try {
				file.createNewFile();
			}catch (IOException ioe) { ioe.printStackTrace();}
		}
		config = YamlConfiguration.loadConfiguration(file);
		saveConfig();
	}
	
	//Initialiser le scoreboard
	public void initScoreboard() {
		
		if(scoreboard != null) throw new UnsupportedOperationException("ScoreBoard déja initialisé.");
		scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		
		for(RankList rankList : RankList.values()) {
			
			Team team = scoreboard.registerNewTeam(rankList.getName());
			team.setPrefix(rankList.getPrefix());
		}
	}
	
	//Charger le rang d'un joueur
	public void loadPlayer(Player player1) {
		player = player1;
		String uuid = player1.getUniqueId().toString();
		if(config.contains(uuid)) {
			return;
		}
		if(!config.contains(uuid)) {
			config.set(uuid, 0);
			saveConfig();
		}
		
		config.set(uuid, 0);
	}
	
	//Supprimer un rang (= réinitialiser)
	public void deletPlayer(String uuid) {
		if(config.contains(uuid)) {
			config.set(uuid, 0);
			saveConfig();
		}
		config.set(uuid, 0);
		saveConfig();
	}
	
	//Récuprer le rang à partir d'un joueur
	public RankList getPlayerRank (String uuid, Player player) {
		uuid = player.getUniqueId().toString();
		if(!config.contains(uuid)) {
			loadPlayer(player);
		}
			return getRankById(config.getInt(uuid));
	}
	
	//Récuperer le rang à partir d'un uuid
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
	
	//Ajouter un rang
	public void addRank(RankList rankList, Player target) {
		if(rankList == RankList.ADMINISTRATEUR && rankCount != 1) {
			config.set(target.getUniqueId().toString(), 10);
			rankCount = 1;
			saveConfig();
		}else if(rankList == RankList.RESPONSABLE && rankCount != 1) {
			config.set(target.getUniqueId().toString(), 9);
			rankCount = 1;
			saveConfig();
		}else if(rankList == RankList.ARCHITECTE && rankCount != 1) {
			config.set(target.getUniqueId().toString(), 5);
			rankCount = 1;
			saveConfig();
		}else if(rankList == RankList.DEVELOPPEUR && rankCount != 1) {
			config.set(target.getUniqueId().toString(), 4);
			rankCount = 1;
			saveConfig();
		}else if(rankList == RankList.GUIDE && rankCount != 1) {
			config.set(target.getUniqueId().toString(), 6);
			rankCount = 1;
			saveConfig();
		}else if(rankList == RankList.MODERATEUR && rankCount != 1) {
			config.set(target.getUniqueId().toString(), 7);
			rankCount = 1;
			saveConfig();
		}else if(rankList == RankList.MODERATEUR_PLUS && rankCount != 1) {
			config.set(target.getUniqueId().toString(), 8);
			rankCount = 1;
			saveConfig();
		}else if(rankList == RankList.SOUVENIR && rankCount != 1) {
			config.set(target.getUniqueId().toString(), 1);
			rankCount = 1;
			saveConfig();
		}else if(rankList == RankList.MEMOIRE && rankCount != 1) {
			config.set(target.getUniqueId().toString(), 2);
			rankCount = 1;
			saveConfig();
		}else if(rankList == RankList.SAGE && rankCount != 1) {
			config.set(target.getUniqueId().toString(), 3);
			rankCount = 1;
			saveConfig();
		}
	}
	
	//Retirer / réinitialiser le rang
	public void removeRank(RankList rankList, Player target) {
		if(rankList == RankList.ADMINISTRATEUR && rankCount != 0) {
			config.set(target.getUniqueId().toString(), 0);
			rankCount = 0;
			saveConfig();
		}else if(rankList == RankList.RESPONSABLE && rankCount != 0) {
			config.set(target.getUniqueId().toString(), 0);
			rankCount = 0;
			saveConfig();
		}else if(rankList == RankList.ARCHITECTE && rankCount != 0) {
			config.set(target.getUniqueId().toString(), 0);
			rankCount = 0;
			saveConfig();
		}else if(rankList == RankList.DEVELOPPEUR && rankCount != 0) {
			config.set(target.getUniqueId().toString(), 0);
			rankCount = 0;
			saveConfig();
		}else if(rankList == RankList.GUIDE && rankCount != 0) {
			config.set(target.getUniqueId().toString(), 0);
			rankCount = 0;
			saveConfig();
		}else if(rankList == RankList.MODERATEUR && rankCount != 0) {
			config.set(target.getUniqueId().toString(), 0);
			rankCount = 0;
			saveConfig();
		}else if(rankList == RankList.MODERATEUR_PLUS && rankCount != 0) {
			config.set(target.getUniqueId().toString(), 0);
			rankCount = 0;
			saveConfig();
		}else if(rankList == RankList.SOUVENIR && rankCount != 0) {
			config.set(target.getUniqueId().toString(), 0);
			rankCount = 0;
			saveConfig();
		}else if(rankList == RankList.MEMOIRE && rankCount != 0) {
			config.set(target.getUniqueId().toString(), 0);
			rankCount = 0;
			saveConfig();
		}else if(rankList == RankList.SAGE && rankCount != 0) {
			config.set(target.getUniqueId().toString(), 0);
			rankCount = 0;
			saveConfig();
		}
	}
	
	public void saveConfig() {
		try {
			config.save(file);
		} catch (IOException ioe) {ioe.printStackTrace();}
			
		}
}
