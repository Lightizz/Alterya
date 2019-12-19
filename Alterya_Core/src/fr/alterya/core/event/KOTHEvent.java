package fr.alterya.core.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.alterya.core.MainCore;
import fr.alterya.core.command.CmdFurnace;
import fr.alterya.core.util.StructureBuilder;

public class KOTHEvent implements Listener
{
	private static Objective objective;
	private KOTHEventManager ekManager;
	//private MainCore m;
	
	public static boolean isEventOn = false;
	
	public static int enterZoneTimerTASKID;
	public static int eventTimerTASKID;
	
	public static int randomPosX;
	public static int randomPosY;
	public static int randomPosZ;
	
	public static int timerZone = 0;
	public static int timerEvent = 0;
	
	private Scoreboard scEmpty = Bukkit.getScoreboardManager().getNewScoreboard();
	
	public BukkitRunnable eR = new BukkitRunnable() {@Override public void run(){}};
	public BukkitRunnable eZ = new BukkitRunnable() {@Override public void run(){}};
	
	public KOTHEvent(MainCore main, KOTHEventManager kothEventManager)
	{
		//m = main;
		ekManager = kothEventManager;
	}

	public void runEnterZoneTimer() {
		eZ = new BukkitRunnable() {
			@Override
			public void run()
			{
				if(isSomeoneInZone() == false) {
					Bukkit.broadcastMessage(MainCore.prefix + "§4§lLe joueur capturant la zone est sortit ! Le timer est donc de nouveau à§r§e 0§4§l.");
					cancel();
					timerZone = 0;
					return;
				}
				
				//5 min = 300 sec
				if(timerZone >= 300) {
					isEventOn = false;
					cancel();
					timerZone = 0;
					endEvent();
					Bukkit.broadcastMessage(MainCore.prefix + "§4§lUn joueur a capturé la zone pendant 5 minutes ! Il a donc gagné toutes les récompenses pour sa faction et lui même ! Prochain KOTH dans 10 heures.");
					eR.cancel();
					ekManager = new KOTHEventManager(CmdFurnace.mainCore);
					ekManager.restartTimerTotem();
					return;
				}
				
				timerZone ++;
			}
		};
		eZ.runTaskTimer(CmdFurnace.mainCore, 0, 20);
	}
	
	@SuppressWarnings("deprecation")
	public boolean isSomeoneInZone() {
		boolean isInRegion = false;
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.getLocation().getX() < StructureBuilder.kothLocCorner$1.getX() + 6 && p.getLocation().getX() > StructureBuilder.kothLocCorner$1.getX()
			&& p.getLocation().getZ() < StructureBuilder.kothLocCorner$1.getZ() + 6 && p.getLocation().getX() > StructureBuilder.kothLocCorner$1.getZ()) {
				isInRegion = true;
			}
		}
		return isInRegion;
	}
	
	public boolean isTimerZoneOn() {
		if(timerZone != 0) {
			return true;
		}
		return false;
	}
	
	public void runEventTimer() {
		eR = new BukkitRunnable() {
			@Override
			public void run()
			{
				if(isSomeoneInZone() == true) {
					if(isTimerZoneOn() == true) {
						return;
					}else {
						Bukkit.broadcastMessage(MainCore.prefix + "§4§lUn joueur a commencé à capturer la zone !");
						runEnterZoneTimer();
					}
				}
				
				// 20 min = 1 200 s
				if(timerEvent >= 1200) {
					isEventOn = false;
					endEvent();
					cancel();
					timerEvent = 0;
					Bukkit.broadcastMessage(MainCore.prefix + "§4§lPersonne n'a réussi à capturer la zone de KOTH à temps ! L'event est donc terminé, le prochain est dans §r§e10 heures §4§l!");
					ekManager = new KOTHEventManager(CmdFurnace.mainCore);
					ekManager.restartTimerTotem();
					return;
				}
				
				timerEvent ++;
			}
		};
		eR.runTaskTimer(CmdFurnace.mainCore, 0, 20);
	}
	
	@SuppressWarnings("deprecation")
	public void startEvent() {
		randomPosX = 0 + (int)(Math.random() * ((30000 - 0) + 1));
		randomPosY = 64 + (int)(Math.random() * ((70 - 64) + 1));
		randomPosZ = 0 + (int)(Math.random() * ((30000 - 0) + 1));
		
		StructureBuilder.buildKOTHStructure(randomPosX, randomPosY, randomPosZ);
		
		Bukkit.broadcastMessage(MainCore.prefix + "§4§lUn KOTH viens de commencé en §r§eX= " + randomPosX + " Y= " + randomPosY + " Z= " + randomPosZ + "§4§l !");
		
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		objective = board.registerNewObjective("KOTH", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.AQUA + "KOTH");
		
		Score _location = objective.getScore(ChatColor.GREEN + "Location : ");
		Score location1 = objective.getScore(ChatColor.YELLOW + "X= " + ChatColor.GOLD + randomPosX);
		Score location2 = objective.getScore(ChatColor.YELLOW + "Y= " + ChatColor.GOLD + randomPosY);
		Score location3 = objective.getScore(ChatColor.YELLOW + "Z= " + ChatColor.GOLD + randomPosZ);
		
		Score _actualOnPos = objective.getScore(ChatColor.GREEN + "Capturé par : ");
		Score actualOnPos = objective.getScore(ChatColor.YELLOW + "Personne");
		
		Score _timeRemainting = objective.getScore(ChatColor.GREEN + "Temps :");
		Score timeRemainting = objective.getScore(ChatColor.YELLOW + String.valueOf(timerEvent));
		
		_location.setScore(7);
		location1.setScore(6);
		location2.setScore(5);
		location3.setScore(4);
		_actualOnPos.setScore(3);
		actualOnPos.setScore(2);
		_timeRemainting.setScore(1);
		timeRemainting.setScore(0);
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setScoreboard(board);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void endEvent() {
		StructureBuilder.removeKOTHStructure();
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setScoreboard(scEmpty);
		}
	}
}
