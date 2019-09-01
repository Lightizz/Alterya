package fr.alterya.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.alterya.core.rank.Rank;
import fr.alterya.core.rank.RankList;
import fr.alterya.factions.FPlayer;
import fr.alterya.factions.FPlayers;
import fr.alterya.factions.Faction;

public final class PlayerListener implements Listener {

	private final Rank rank;
	
	public FPlayer fme;
	public Faction myFaction;
	
	public PlayerListener(Rank rank) {
		this.rank = rank;
	}
	
	@EventHandler
	private void playerJoin(PlayerJoinEvent pje) {
		rank.loadPlayer(pje.getPlayer());
		pje.getPlayer().setScoreboard(rank.getScoreboard());
		
	}
	
	@EventHandler
	private void playerQuit(PlayerQuitEvent pqe) {
		rank.deletPlayer(pqe.getPlayer().getUniqueId().toString());
	}
	
	@EventHandler
	private void playerChat(AsyncPlayerChatEvent pce) {
		this.fme = FPlayers.i.get(pce.getPlayer());
		this.myFaction = this.fme.getFaction();
		Faction faction = myFaction;
		RankList rankList = rank.getPlayerRank(pce.getPlayer().getUniqueId().toString(), pce.getPlayer());
		//Positionne la faction du joueur + son rang + son pseudo avant sont message
		pce.setFormat("§r[" + faction.getTag(fme)+ "§r] " + rankList.getPrefix()+pce.getPlayer().getName()+rankList.getChatSeparator()+pce.getMessage());
	}
	
	@EventHandler
	private void playerChatColorEvent(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		//Regarde si le joueur veut mettre de la couleur
		if(rank.config.getInt(player.getUniqueId().toString()) >= 6) {
			if(e.getFormat().startsWith("&a")) {
				e.setFormat("§a" + e.getMessage());
				return;
			}
			if(e.getFormat().startsWith("&b")) {
				e.setFormat("§b" + e.getMessage());
				return;
			}
			if(e.getFormat().startsWith("&c")) {
				e.setFormat("§c" + e.getMessage());
				return;
			}
			if(e.getFormat().startsWith("&d")) {
				e.setFormat("§d" + e.getMessage());
				return;
			}
			if(e.getFormat().startsWith("&e")) {
				e.setFormat("§e" + e.getMessage());
				return;
			}
			if(e.getFormat().startsWith("&f")) {
				e.setFormat("§f" + e.getMessage());
				return;
			}
			if(e.getFormat().startsWith("&r")) {
				e.setFormat("§r" + e.getMessage());
				return;
			}
			if(e.getFormat().startsWith("&1")) {
				e.setFormat("§1" + e.getMessage());
				return;
			}
			if(e.getFormat().startsWith("&2")) {
				e.setFormat("§2" + e.getMessage());
				return;
			}
			if(e.getFormat().startsWith("&3")) {
				e.setFormat("§3" + e.getMessage());
				return;
			}
			if(e.getFormat().startsWith("&4")) {
				e.setFormat("§4" + e.getMessage());
				return;
			}
			if(e.getFormat().startsWith("&5")) {
				e.setFormat("§5" + e.getMessage());
				return;
			}
			if(e.getFormat().startsWith("&6")) {
				e.setFormat("§6" + e.getMessage());
				return;
			}
			if(e.getFormat().startsWith("&7")) {
				e.setFormat("§7" + e.getMessage());
				return;
			}
			if(e.getFormat().startsWith("&8")) {
				e.setFormat("§8" + e.getMessage());
				return;
			}
			if(e.getFormat().startsWith("&9")) {
				e.setFormat("§9" + e.getMessage());
				return;
			}
			if(e.getFormat().startsWith("&0")) {
				e.setFormat("§0" + e.getMessage());
				return;
			}
			if(e.getFormat().startsWith("&l")) {
				e.setFormat("§l" + e.getMessage());
				return;
			}
		}
	}
}
