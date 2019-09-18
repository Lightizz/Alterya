package fr.alterya.core.playerMenu;

import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.alterya.core.MainCore;
import fr.alterya.core.playerMenu.DailyQuest.DailyQuest;
import fr.alterya.core.util.FileWriter;
import fr.alterya.core.util.ItemBuilder;

public class MenuManager
{
	DailyQuest q;
	
	FileWriter fw0;
	public FileWriter questFile;
	
	public MenuManager() {
		setItems();
	}
	
	public MenuManager(String uuid) {
		questFile = new FileWriter("ServerData/DailyQuests", uuid + " quests");
		q = new DailyQuest(MainCore.instance, this, Bukkit.getPlayer(UUID.fromString(uuid)));
		setItems();
	}
	
	public ItemStack clock = ItemBuilder.createItem("§aQuêtes journalières", Material.WATCH);
	public ItemStack stats = ItemBuilder.createItem("§3Statistiques", Material.SKULL_ITEM);
	public ItemStack recipes = ItemBuilder.createItem("§cRecetes", Material.BOOK);
	
	public static Inventory menu = Bukkit.createInventory(null, 1*9, "§bMenu joueur");
	public Inventory invQuest = Bukkit.createInventory(null, 5*9, "§aQuêtes journalières");
	public static Inventory invStats = Bukkit.createInventory(null, 5*9, "§3Statistiques");
	public static Inventory invRecipes = Bukkit.createInventory(null, 5*9, "§cRecetes");
	
	public Date d = new Date();
	
	public void setTodayQuest(ItemStack itemToShow) {
		
	}
	
	public FileWriter getQuestFile(String uuid) {
		fw0 = new FileWriter("ServerData/DailyQuests", uuid + " quests");
		
		fw0.config.createSection("Day1");
		fw0.config.createSection("Day2");
		fw0.config.createSection("Day3");
		fw0.config.createSection("Day4");
		fw0.config.createSection("Day5");
		fw0.config.createSection("Day6");
		fw0.config.createSection("Day7");
		fw0.config.createSection("Day8");
		fw0.config.createSection("Day9");
		fw0.config.createSection("Day10");
		fw0.config.createSection("Day11");
		fw0.config.createSection("Day12");
		fw0.config.createSection("Day13");
		fw0.config.createSection("Day14");
		fw0.config.createSection("Day15");
		fw0.config.createSection("Day16");
		fw0.config.createSection("Day17");
		fw0.config.createSection("Day18");
		fw0.config.createSection("Day19");
		fw0.config.createSection("Day20");
		fw0.config.createSection("Day21");
		fw0.config.createSection("Day22");
		fw0.config.createSection("Day23");
		fw0.config.createSection("Day24");
		fw0.config.createSection("Day25");
		fw0.config.createSection("Day26");
		fw0.config.createSection("Day27");
		fw0.config.createSection("Day28");
		fw0.config.createSection("Day29");
		fw0.config.createSection("Day30");
		fw0.config.createSection("Day31");
		
		fw0.config.getConfigurationSection("Day1").set("Description", "test");
		fw0.config.getConfigurationSection("Day1").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day1").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day1").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day1").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day1").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day2").set("Description", "test");
		fw0.config.getConfigurationSection("Day2").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day2").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day2").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day2").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day2").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day3").set("Description", "test");
		fw0.config.getConfigurationSection("Day3").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day3").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day3").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day3").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day3").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day4").set("Description", "test");
		fw0.config.getConfigurationSection("Day4").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day4").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day4").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day4").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day4").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day5").set("Description", "test");
		fw0.config.getConfigurationSection("Day5").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day5").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day5").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day5").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day5").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day6").set("Description", "test");
		fw0.config.getConfigurationSection("Day6").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day6").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day6").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day6").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day6").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day7").set("Description", "test");
		fw0.config.getConfigurationSection("Day7").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day7").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day7").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day7").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day7").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day8").set("Description", "test");
		fw0.config.getConfigurationSection("Day8").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day8").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day8").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day8").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day8").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day9").set("Description", "test");
		fw0.config.getConfigurationSection("Day9").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day9").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day9").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day9").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day9").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day10").set("Description", "test");
		fw0.config.getConfigurationSection("Day10").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day10").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day10").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day10").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day10").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day11").set("Description", "test");
		fw0.config.getConfigurationSection("Day11").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day11").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day11").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day11").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day11").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day12").set("Description", "test");
		fw0.config.getConfigurationSection("Day12").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day12").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day12").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day12").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day12").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day13").set("Description", "test");
		fw0.config.getConfigurationSection("Day13").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day13").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day13").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day13").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day13").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day14").set("Description", "test");
		fw0.config.getConfigurationSection("Day14").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day14").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day14").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day14").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day14").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day15").set("Description", "test");
		fw0.config.getConfigurationSection("Day15").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day15").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day15").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day15").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day15").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day16").set("Description", "test");
		fw0.config.getConfigurationSection("Day16").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day16").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day16").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day16").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day16").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day17").set("Description", "test");
		fw0.config.getConfigurationSection("Day17").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day17").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day17").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day17").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day17").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day18").set("Description", "test");
		fw0.config.getConfigurationSection("Day18").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day18").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day18").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day18").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day18").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day19").set("Description", "test");
		fw0.config.getConfigurationSection("Day19").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day19").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day19").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day19").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day19").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day20").set("Description", "test");
		fw0.config.getConfigurationSection("Day20").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day20").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day20").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day20").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day20").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day21").set("Description", "test");
		fw0.config.getConfigurationSection("Day21").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day21").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day21").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day21").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day21").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day22").set("Description", "test");
		fw0.config.getConfigurationSection("Day22").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day22").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day22").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day22").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day22").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day23").set("Description", "test");
		fw0.config.getConfigurationSection("Day23").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day23").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day23").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day23").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day23").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day24").set("Description", "test");
		fw0.config.getConfigurationSection("Day24").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day24").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day24").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day24").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day24").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day25").set("Description", "test");
		fw0.config.getConfigurationSection("Day25").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day25").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day25").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day25").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day25").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day26").set("Description", "test");
		fw0.config.getConfigurationSection("Day26").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day26").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day26").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day26").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day26").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day27").set("Description", "test");
		fw0.config.getConfigurationSection("Day27").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day27").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day27").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day27").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day27").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day28").set("Description", "test");
		fw0.config.getConfigurationSection("Day28").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day28").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day28").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day28").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day28").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day29").set("Description", "test");
		fw0.config.getConfigurationSection("Day29").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day29").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day29").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day29").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day29").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day30").set("Description", "test");
		fw0.config.getConfigurationSection("Day30").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day30").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day30").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day30").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day30").set("ItemSuccesAmount", 10);
		
		fw0.config.getConfigurationSection("Day31").set("Description", "test");
		fw0.config.getConfigurationSection("Day31").set("IsSucces", false);
		fw0.config.getConfigurationSection("Day31").set("ItemRequire", Material.GLOWSTONE_DUST.name());
		fw0.config.getConfigurationSection("Day31").set("ItemRequireAmount", 24);
		fw0.config.getConfigurationSection("Day31").set("ItemSucces", Material.BEACON.name());
		fw0.config.getConfigurationSection("Day31").set("ItemSuccesAmount", 10);
		
		fw0.saveConfig();
		
		return fw0;
	}
	
	public void openMenu(Player player) {
		player.openInventory(menu);
	}
	
	public void setItems() {
		menu.setItem(2, clock);
		menu.setItem(4, stats);
		menu.setItem(6, recipes);
	}
}
