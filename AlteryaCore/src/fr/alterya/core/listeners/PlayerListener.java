package fr.alterya.core.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.alterya.core.MainCore;
import fr.alterya.core.command.CmdMute;
import fr.alterya.core.command.CmdTempBan;
import fr.alterya.core.command.CmdTpa;
import fr.alterya.core.rank.Rank;
import fr.alterya.core.rank.RankList;
import fr.alterya.factions.FPlayer;
import fr.alterya.factions.FPlayers;
import fr.alterya.factions.Faction;

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
	void playerLogin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		player.sendMessage(ChatColor.AQUA + "§lBienvenue sur Alterya !");
	}
	
	@EventHandler
	void playerJoin(PlayerJoinEvent pje) {
		rank.loadPlayer(pje.getPlayer());
		pje.getPlayer().setScoreboard(rank.getScoreboard());
	}
	
	@EventHandler
	void playerQuit(PlayerQuitEvent pqe) {
		rank.deletPlayer(pqe.getPlayer().getUniqueId().toString());
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
	void playerChat(AsyncPlayerChatEvent pce) {
		Player player = pce.getPlayer();
		this.fme = FPlayers.i.get(pce.getPlayer());
		this.myFaction = this.fme.getFaction();
		Faction faction = myFaction;
		RankList rankList = rank.getPlayerRank(pce.getPlayer().getUniqueId().toString(), pce.getPlayer());
		//Positionne la faction du joueur + son rang + son pseudo avant sont message
		pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + pce.getMessage());
		
		if(rank.config.getInt(player.getUniqueId().toString()) != 3 
				|| !(rank.config.getInt(player.getUniqueId().toString()) >= 4)) {
			if(pce.getFormat().contains("!a")) {
				pce.setMessage(pce.getMessage().replaceAll("!a", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.GREEN + pce.getMessage());
				return;
			}
			if(pce.getFormat().contains("!b")) {
				pce.setMessage(pce.getMessage().replaceAll("!b", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.AQUA + pce.getMessage());
				return;
			}
			if(pce.getFormat().contains("!c")) {
				pce.setMessage(pce.getMessage().replaceAll("!c", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.RED + pce.getMessage());
				return;
			}
			if(pce.getFormat().contains("!d")) {
				pce.setMessage(pce.getMessage().replaceAll("!d", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.LIGHT_PURPLE + pce.getMessage());
				return;
			}
			if(pce.getFormat().contains("!e")) {
				pce.setMessage(pce.getMessage().replaceAll("!e", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.YELLOW + pce.getMessage());
				return;
			}
			if(pce.getFormat().contains("!f")) {
				pce.setMessage(pce.getMessage().replaceAll("!f", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.WHITE + pce.getMessage());
				return;
			}
			if(pce.getFormat().contains("!r")) {
				pce.setMessage(pce.getMessage().replaceAll("!r", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.RESET + pce.getMessage());
				return;
			}
			if(pce.getFormat().contains("!1")) {
				pce.setMessage(pce.getMessage().replaceAll("!1", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.DARK_BLUE + pce.getMessage());
				return;
			}
			if(pce.getFormat().contains("!2")) {
				pce.setMessage(pce.getMessage().replaceAll("!2", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.DARK_GREEN + pce.getMessage());
				return;
			}
			if(pce.getFormat().contains("!3")) {
				pce.setMessage(pce.getMessage().replaceAll("!3", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.DARK_AQUA + pce.getMessage());
				return;
			}
			if(pce.getFormat().contains("!4")) {
			pce.setMessage(pce.getMessage().replaceAll("!4", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.DARK_RED + pce.getMessage());
				return;
			}
			if(pce.getFormat().contains("!5")) {
				pce.setMessage(pce.getMessage().replaceAll("!5", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.DARK_PURPLE + pce.getMessage());
				return;
			}
			if(pce.getFormat().contains("!6")) {
				pce.setMessage(pce.getMessage().replaceAll("!6", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.GOLD + pce.getMessage());
				return;
			}
			if(pce.getFormat().contains("!7")) {
				pce.setMessage(pce.getMessage().replaceAll("!7", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.GRAY + pce.getMessage());
				return;
			}
			if(pce.getFormat().contains("!8")) {
				pce.setMessage(pce.getMessage().replaceAll("!8", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.DARK_GRAY + pce.getMessage());
				return;
			}
			if(pce.getFormat().contains("!9")) {
			pce.setMessage(pce.getMessage().replaceAll("!9", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.BLUE + pce.getMessage());
				return;
			}
			if(pce.getFormat().contains("!0")) {
				pce.setMessage(pce.getMessage().replaceAll("!0", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.BLACK + pce.getMessage());
				return;
			}
			if(pce.getFormat().contains("!l")) {
				pce.setMessage(pce.getMessage().replaceAll("!l", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.BOLD + pce.getMessage());
				return;
			}
		}
	}
	
	@EventHandler
	void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if(CmdTpa.requestedPlayers.contains(player.getUniqueId().toString()) || CmdTpa.requestSenderPlayers.contains(player.getUniqueId().toString())) {
			player.sendMessage("t");
			CmdTpa c = new CmdTpa(m);
			c.cancelTeleport();
		}
	}
}
