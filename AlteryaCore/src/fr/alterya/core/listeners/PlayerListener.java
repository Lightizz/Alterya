package fr.alterya.core.listeners;

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
		pce.setFormat("§r[" + faction.getTag(fme)+ "§r] " + rankList.getPrefix()+pce.getPlayer().getName()+rankList.getChatSeparator()+pce.getMessage());
	}
	
}
