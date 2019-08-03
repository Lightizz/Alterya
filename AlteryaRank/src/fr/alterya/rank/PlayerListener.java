package fr.alterya.rank;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class PlayerListener implements Listener {

	private final Rank rank;
	
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
		rank.deletPlayer(pqe.getPlayer());
		
	}
	
	@EventHandler
	private void playerChat(AsyncPlayerChatEvent pce) {
		RankList rankList = rank.getPlayerRank(pce.getPlayer());
		pce.setFormat(rankList.getPrefix()+pce.getPlayer().getName()+rankList.getChatSeparator()+pce.getMessage());
		
	}
	
}
