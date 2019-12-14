package fr.alterya.core.event;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.alterya.core.MainCore;
import fr.alterya.core.command.CmdFurnace;
import fr.alterya.factions.Board;
import fr.alterya.factions.Conf;
import fr.alterya.factions.FLocation;
import fr.alterya.factions.FPlayer;
import fr.alterya.factions.FPlayers;
import fr.alterya.factions.Faction;
import fr.alterya.factions.Factions;

public class TotemEvent implements Listener
{
	private TotemEventManager etManager;
	private static Objective objective;
	
	Player me;
	FPlayer fme;
	Faction myFaction;
	
	public BukkitRunnable r = new BukkitRunnable() {@Override public void run(){}};
	
	public static ItemStack obsiB1 = new ItemStack(Material.ENDER_STONE);
	public static ItemMeta obsiM1 = obsiB1.getItemMeta();
	public static ItemStack obsiB2 = new ItemStack(Material.ENDER_STONE);
	public static ItemMeta obsiM2 = obsiB1.getItemMeta();
	public static ItemStack obsiB3 = new ItemStack(Material.ENDER_STONE);
	public static ItemMeta obsiM3 = obsiB1.getItemMeta();
	public static ItemStack obsiB4 = new ItemStack(Material.ENDER_STONE);
	public static ItemMeta obsiM4 = obsiB1.getItemMeta();
	public static ItemStack obsiB5 = new ItemStack(Material.ENDER_STONE);
	public static ItemMeta obsiM5 = obsiB1.getItemMeta();
	
	public static int blockCountRemainting = 5;
	
	public static boolean isEventOn = false;
	
	Map<String, Integer> playersBlocksDestroyedCount = new HashMap<>();
	
	public static List<ItemStack> obsiBList = Arrays.asList(obsiB1, obsiB2, obsiB3, obsiB4, obsiB5);
	
	public static int timer = 0;
	
	public static Location eventLoc1;
	public static Location eventLoc2;
	public static Location eventLoc3;
	public static Location eventLoc4;
	public static Location eventLoc5;
	
	public static List<Location> locList = Arrays.asList(eventLoc1, eventLoc2, eventLoc3, eventLoc4, eventLoc5);
	
	private static Score blockCount;
	private static Score headerFac;
	private static Score timeRemainting;
	
	private Scoreboard scEmpty = Bukkit.getScoreboardManager().getNewScoreboard();
	
	public MainCore m;
	
	public TotemEvent(MainCore main, TotemEventManager e) {
		m = main;
		etManager = e;
		
		obsiM1.setDisplayName("ObsiEvent1");
		obsiM2.setDisplayName("ObsiEvent2");
		obsiM3.setDisplayName("ObsiEvent3");
		obsiM4.setDisplayName("ObsiEvent4");
		obsiM5.setDisplayName("ObsiEvent5");
		
		obsiB1.setItemMeta(obsiM1);
		obsiB2.setItemMeta(obsiM2);
		obsiB3.setItemMeta(obsiM3);
		obsiB4.setItemMeta(obsiM4);
		obsiB5.setItemMeta(obsiM5);
	}

	public void run() {
		r = new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run()
			{	
				if(blockCountRemainting <= 0) {
					Bukkit.broadcastMessage(MainCore.prefix + "§4§lLe totem a été miné entièrement ! L'event est donc finit, prochain totem dans §r§e6 heures§4§l.");
					for(Player p : Bukkit.getOnlinePlayers()) {
						p.playSound(p.getLocation(), Sound.ORB_PICKUP, 50, 50);
					}
					isEventOn = false;
					endEvent();
					cancel();
					etManager = new TotemEventManager(CmdFurnace.mainCore);
					etManager.restartTimerTotem();
					return;
				}
				
				blockCount = objective.getScore(ChatColor.YELLOW + String.valueOf(blockCountRemainting));
				timeRemainting = objective.getScore(ChatColor.YELLOW + String.valueOf(timer));
				//15 min = 900 sec
				if(timer >= 900 && isEventOn == true) {
					isEventOn = false;
					endEvent();
					Bukkit.broadcastMessage(MainCore.prefix + "§4§lLe totem n'a pas été miner à temps ! Le totem a dispawn, prochain totem dans §r§e6 heures§4§l.");
					for(Player p : Bukkit.getOnlinePlayers()) {
						p.playSound(p.getLocation(), Sound.ORB_PICKUP, 50, 50);
					}
					cancel();
					etManager = new TotemEventManager(CmdFurnace.mainCore);
					etManager.restartTimerTotem();
					return;
				}
				timer ++;
			}
		};
		r.runTaskTimer(CmdFurnace.mainCore, 0, 20);
	}
	
	@SuppressWarnings({
			"deprecation"
	})
	public void startEvent() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setScoreboard(scEmpty);
		}
		int randomPosX = 450 + (int)(Math.random() * ((1500 - 450) + 1));
		int randomPosY = 64 + (int)(Math.random() * ((70 - 64) + 1));
		int randomPosZ = 450 + (int)(Math.random() * ((1500 - 450) + 1));
		
		eventLoc1 = new Location(Bukkit.getWorld("world"), randomPosX, randomPosY, randomPosZ);
		eventLoc1.getBlock().setType(obsiB1.getType());
		eventLoc2 = new Location(eventLoc1.getWorld(), eventLoc1.getX(), eventLoc1.getY() + 1, eventLoc1.getZ());
		eventLoc3 = new Location(eventLoc1.getWorld(), eventLoc1.getX(), eventLoc1.getY() + 2, eventLoc1.getZ());
		eventLoc4 = new Location(eventLoc1.getWorld(), eventLoc1.getX(), eventLoc1.getY() + 3, eventLoc1.getZ());
		eventLoc5 = new Location(eventLoc1.getWorld(), eventLoc1.getX(), eventLoc1.getY() + 4, eventLoc1.getZ());
		eventLoc2.getBlock().setType(obsiB2.getType());
		eventLoc3.getBlock().setType(obsiB3.getType());
		eventLoc4.getBlock().setType(obsiB4.getType());
		eventLoc5.getBlock().setType(obsiB5.getType());
		
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		objective = board.registerNewObjective("Totem", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.AQUA + "Totem");
		
		Score _blockCount = objective.getScore(ChatColor.GREEN + "Block(s): ");
		blockCount = objective.getScore(ChatColor.RED + String.valueOf(blockCountRemainting));
		
		Score _timeRemainting = objective.getScore(ChatColor.GREEN + "Temps : ");
		timeRemainting = objective.getScore(ChatColor.YELLOW + String.valueOf(timer));
		
		Score _blocksPos = objective.getScore(ChatColor.GREEN + "Coordonnées : ");
		Score blocksPosX = objective.getScore(ChatColor.GREEN + " X= §e" + randomPosX);
		Score blocksPosY = objective.getScore(ChatColor.GREEN + " Y= §e" + randomPosY);
		Score blocksPosZ = objective.getScore(ChatColor.GREEN + " Z= §e" + randomPosZ);
		
		Score _headerFac = objective.getScore(ChatColor.GREEN + "1ère Faction :");
		headerFac = objective.getScore("");
		
		_blockCount.setScore(9);
		blockCount.setScore(8);
		_timeRemainting.setScore(7);
		timeRemainting.setScore(6);
		_blocksPos.setScore(5);
		blocksPosX.setScore(4);
		blocksPosY.setScore(3);
		blocksPosZ.setScore(2);
		_headerFac.setScore(1);
		headerFac.setScore(0);
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setScoreboard(board);
		}
		
		blockCountRemainting = obsiBList.size();
		Bukkit.broadcastMessage(MainCore.prefix + "§4§lUn Totem est apparu en X: §r§e" + String.valueOf(randomPosX) + "§4§l, Y: §r§e" + String.valueOf(randomPosY) + "§4§l, Z: §r§e" + String.valueOf(randomPosZ) + "§4§l.");
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.playSound(p.getLocation(), Sound.ORB_PICKUP, 50, 50);
		}
		isEventOn = true;
	}

	@SuppressWarnings("deprecation")
	public void endEvent() {
		playersBlocksDestroyedCount.clear();
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setScoreboard(scEmpty);
		}
		eventLoc1.getBlock().setType(Material.AIR);
		eventLoc2.getBlock().setType(Material.AIR);
		eventLoc3.getBlock().setType(Material.AIR);
		eventLoc4.getBlock().setType(Material.AIR);
		eventLoc5.getBlock().setType(Material.AIR);
		
		blockCountRemainting = 5;
		timer = 0;
	}

	@SuppressWarnings({
			"unlikely-arg-type", "deprecation"
	})
	@EventHandler
	public void onBlocksBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		Block bb = e.getBlock();
		
		Faction warzone = Factions.i.getByTag("WarZone");
		if(Conf.apMustBeInClaimedTerritory && Board.getFactionAt(new FLocation(eventLoc1)) == warzone) {
			if(bb.getType() == obsiB1.getType()
					|| bb.getType() == obsiB2.getType() 
					|| bb.getType() == obsiB3.getType() 
					|| bb.getType() == obsiB4.getType() 
					|| bb.getType() == obsiB5.getType() 
					&& isEventOn == true && e.getBlock().getWorld() == eventLoc1.getWorld()) {
				e.getBlock().getLocation().getWorld().playEffect(e.getBlock().getLocation(), Effect.SMALL_SMOKE, 152);
				if(playersBlocksDestroyedCount.containsKey(player)) {
					int oldCount = playersBlocksDestroyedCount.get(player.getUniqueId().toString());
					playersBlocksDestroyedCount.remove(player.getUniqueId().toString());
					playersBlocksDestroyedCount.put(player.getUniqueId().toString(), oldCount ++);
				}
				playersBlocksDestroyedCount.put(player.getUniqueId().toString(), 1);

				fme = FPlayers.i.get(e.getPlayer());
				myFaction = fme.getFaction();
				Faction faction = myFaction;
				headerFac = objective.getScore(faction.getTag());
				if(faction.getTag().equalsIgnoreCase("§2Wilderness")
						|| faction.getTag().equalsIgnoreCase("SafeZone")
						|| faction.getTag().equalsIgnoreCase("WarZone")
						|| faction.getTag().equalsIgnoreCase("ApZone")){
					headerFac = objective.getScore("Sans fac. " + player.getDisplayName());
				}
				blockCountRemainting --;
				if(blockCountRemainting == 0) {
					return;
				}
				Bukkit.broadcastMessage(MainCore.prefix + "§4§lLe joueur §e§e" + e.getPlayer().getDisplayName() + "§4§l a cassé un block du totem ! Il reste §r§e" + String.valueOf(blockCountRemainting) + "§4§l blocks, faites vite !");
				for(Player p : Bukkit.getOnlinePlayers()) {
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 50, 50);
				}
			}
		}
	}
	
	@EventHandler
	public void onItemDrop(ItemSpawnEvent e) {
		Faction warzone = Factions.i.getByTag("WarZone");
		if(e.getEntity().getItemStack().getType() == Material.ENDER_STONE) {
			if(Conf.apMustBeInClaimedTerritory && Board.getFactionAt(eventLoc1) == warzone) {
				e.getEntity().remove();
				e.setCancelled(true);
			}
		}
	}
}
