package fr.alterya.core.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
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
	
	@SuppressWarnings("static-access")
	@EventHandler
	void playerChat(AsyncPlayerChatEvent pce) {
		Player player = pce.getPlayer();
		this.fme = FPlayers.i.get(pce.getPlayer());
		this.myFaction = this.fme.getFaction();
		Faction faction = myFaction;
		RankList rankList = rank.getPlayerRank(pce.getPlayer().getUniqueId().toString(), pce.getPlayer());
		pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + pce.getMessage());
		
		if(rank.config.getInt(player.getUniqueId().toString()) != 3 
				|| !(rank.config.getInt(player.getUniqueId().toString()) >= 4)) {
			if(pce.getFormat().startsWith("&a")) {
				pce.setMessage(pce.getMessage().replaceAll("&a", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.GREEN + pce.getMessage());
				return;
			}
			if(pce.getFormat().startsWith("&b")) {
				pce.setMessage(pce.getMessage().replaceAll("&b", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.AQUA + pce.getMessage());
				return;
			}
			if(pce.getFormat().startsWith("&c")) {
				pce.setMessage(pce.getMessage().replaceAll("&c", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.RED + pce.getMessage());
				return;
			}
			if(pce.getFormat().startsWith("&d")) {
				pce.setMessage(pce.getMessage().replaceAll("&d", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.LIGHT_PURPLE + pce.getMessage());
				return;
			}
			if(pce.getFormat().startsWith("&e")) {
				pce.setMessage(pce.getMessage().replaceAll("&e", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.YELLOW + pce.getMessage());
				return;
			}
			if(pce.getFormat().startsWith("&f")) {
				pce.setMessage(pce.getMessage().replaceAll("&f", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.WHITE + pce.getMessage());
				return;
			}
			if(pce.getFormat().startsWith("&r")) {
				pce.setMessage(pce.getMessage().replaceAll("&r", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.RESET + pce.getMessage());
				return;
			}
			if(pce.getFormat().startsWith("&1")) {
				pce.setMessage(pce.getMessage().replaceAll("&1", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.DARK_BLUE + pce.getMessage());
				return;
			}
			if(pce.getFormat().startsWith("&2")) {
				pce.setMessage(pce.getMessage().replaceAll("&2", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.DARK_GREEN + pce.getMessage());
				return;
			}
			if(pce.getFormat().startsWith("&3")) {
				pce.setMessage(pce.getMessage().replaceAll("&3", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.DARK_AQUA + pce.getMessage());
				return;
			}
			if(pce.getFormat().startsWith("&4")) {
			pce.setMessage(pce.getMessage().replaceAll("&4", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.DARK_RED + pce.getMessage());
				return;
			}
			if(pce.getFormat().startsWith("&5")) {
				pce.setMessage(pce.getMessage().replaceAll("&5", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.DARK_PURPLE + pce.getMessage());
				return;
			}
			if(pce.getFormat().startsWith("&6")) {
				pce.setMessage(pce.getMessage().replaceAll("&6", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.GOLD + pce.getMessage());
				return;
			}
			if(pce.getFormat().startsWith("&7")) {
				pce.setMessage(pce.getMessage().replaceAll("&7", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.GRAY + pce.getMessage());
				return;
			}
			if(pce.getFormat().startsWith("&8")) {
				pce.setMessage(pce.getMessage().replaceAll("&8", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.DARK_GRAY + pce.getMessage());
				return;
			}
			if(pce.getFormat().startsWith("&9")) {
			pce.setMessage(pce.getMessage().replaceAll("&9", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.BLUE + pce.getMessage());
				return;
			}
			if(pce.getFormat().startsWith("&0")) {
				pce.setMessage(pce.getMessage().replaceAll("&0", ""));
				pce.setFormat("§r[" + faction.getTag(fme) + "§r] " + rankList.getPrefix() + pce.getPlayer().getName() + rankList.getChatSeparator() + ChatColor.BLACK + pce.getMessage());
				return;
			}
			if(pce.getFormat().startsWith("&l")) {
				pce.setMessage(pce.getMessage().replaceAll("&l", ""));
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
