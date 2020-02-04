package fr.alterya.core.listeners;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;

import fr.alterya.core.MainCore;
import fr.alterya.core.command.CmdMute;
import fr.alterya.core.command.CmdTempBan;
import fr.alterya.core.command.CmdTpMute;
import fr.alterya.core.command.CmdTpa;
import fr.alterya.core.rank.Rank;
import fr.alterya.core.rank.RankList;

public final class PlayerListener implements Listener {

	private final Rank rank;
	
	MainCore m;
	FPlayer fme;
	Faction myFaction;
	
	public PlayerListener(Rank rank, MainCore main) {
		this.rank = rank;
		m = main;
	}
	
	@EventHandler
	void bookAndQuill(SignChangeEvent e) {
		if(e.getLines()[0].length() == 15 
				&& e.getLines()[1].length() == 15 
				&& e.getLines()[2].length() == 15 
				&& e.getLines()[3].length() == 15) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	void playerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		e.setJoinMessage("");
		player.sendMessage(ChatColor.AQUA + "§lBienvenue sur Alterya! Vous êtes sur le monde: " + player.getWorld().getName() + "!");
		rank.loadPlayer(player);
		Rank.saveConfig();
	}
	
	@EventHandler
	void playerQuit(PlayerQuitEvent pqe) {
		pqe.setQuitMessage("");
		if(MainCore.ignoreMPPlayer.contains(pqe.getPlayer().getUniqueId().toString())) {
			MainCore.ignoreMPPlayer.remove(pqe.getPlayer().getUniqueId().toString());
		}
		Rank.saveConfig();
	}
	
	@EventHandler
	void playerTempBan(PlayerLoginEvent e) {
		Player player = e.getPlayer();
		if(CmdTempBan.timePlayersBanned.containsKey(player.getUniqueId().toString())) {
			e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§4Vous avez été banni pendant §e" + CmdTempBan.timePlayersBanned.get(player.getUniqueId().toString()) + "j§4, raison : §e" + CmdTempBan.reason_ + "§4.");
		}
	}
	
	@EventHandler
	void onPlayerMute(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		if(CmdMute.fw.getBoolean(player.getUniqueId().toString()) == true) {
			player.sendMessage(MainCore.prefix + "§4Vous êtes mute, vous ne pouvez pas parler.");
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	void onPlayerTpMute(PlayerCommandPreprocessEvent e) {
		Player player = e.getPlayer();
		if(CmdTpMute.fw.getBoolean(player.getUniqueId().toString()) == true) {
			if(e.getMessage().startsWith("/tp") || e.getMessage().startsWith("/home") || e.getMessage().startsWith("/warp") || e.getMessage().startsWith("/spawn")) {
				player.sendMessage(MainCore.prefix + ChatColor.RED + "§4Vous êtes TPmute, vous ne pouvez pas vous téléporter.");
				e.setCancelled(true);
			}
		}
	}
	
	@SuppressWarnings("static-access")
	@EventHandler
	void playerChat(AsyncPlayerChatEvent pce) {
		Player player = pce.getPlayer();
		this.fme = FPlayers.i.get(pce.getPlayer());
		this.myFaction = this.fme.getFaction();
		Faction faction = myFaction;
		RankList rankList = rank.getPlayerRank(pce.getPlayer().getUniqueId().toString(), pce.getPlayer());
		pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + pce.getMessage());
		
		if(rank.config.getInt(player.getUniqueId().toString()) != 3 || !(rank.config.getInt(player.getUniqueId().toString()) >= 4)) {
			if(pce.getMessage().contains("&")) {
				pce.setMessage(pce.getMessage().replace('&', '§'));
			}
		}
	}
	
	@EventHandler
	void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if(CmdTpa.requestedPlayers.contains(player.getUniqueId().toString()) || CmdTpa.requestSenderPlayers.contains(player.getUniqueId().toString())) {
			player.sendMessage(MainCore.prefix + ChatColor.RED + "Votre requête de téléportation a été annulée car vous avec bougé(e).");
			CmdTpa c = new CmdTpa(m);
			c.cancelTeleport();
		}
	}
}
