package fr.alterya.core.playerMenu;

import java.util.Date;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.alterya.core.util.ItemBuilder;

public class MenuManager
{
	public HashMap<String, Boolean> questSuccesPlayers = new HashMap<String, Boolean>();
	
	public static int lastDay;
	public static int day;
	
	public ItemStack clock = ItemBuilder.createItem("§aQuêtes journalières", Material.WATCH);
	public ItemStack stats = ItemBuilder.createItem("§3Statistiques", Material.SKULL_ITEM);
	public ItemStack recipes = ItemBuilder.createItem("§cRecettes", Material.BOOK);
	
	public static Inventory menu = Bukkit.createInventory(null, 1*9, "§bMenu joueur");
	public Inventory invQuest = Bukkit.createInventory(null, 3*9, "§aQuêtes journalières");
	public static Inventory invStats = Bukkit.createInventory(null, 3*9, "§3Statistiques");
	public static Inventory invRecipes = Bukkit.createInventory(null, 3*9, "§cRecettes");
	
	public static ItemStack g = ItemBuilder.createItem(" ", Material.STAINED_GLASS_PANE);
	
	public static Date d = new Date();
	
	@SuppressWarnings("deprecation")
	public MenuManager() {
		setItemsMenu();
		setItemsCrafts();
		//setItemsQuests();
		day = d.getDate();
	}
	
	public void openMenu(Player player) {
		player.openInventory(menu);
	}
	
	public void setItemsMenu() {
		menu.setItem(2, clock);
		menu.setItem(4, stats);
		menu.setItem(6, recipes);
	}
	
	public void setItemsStats(Player opener) {
		invStats.setItem(0, g);
		invStats.setItem(1, g);
		invStats.setItem(2, g);
		invStats.setItem(3, g);
		invStats.setItem(4, g);
		invStats.setItem(5, g);
		invStats.setItem(6, g);
		invStats.setItem(7, g);
		invStats.setItem(8, g);
		
		/*
		invStats.setItem(9, ItemBuilder.createItem("Eliminations : ", Material.IRON_SWORD));
		invStats.setItem(9, ItemBuilder.createItem("Morts : ", Material.IRON_SWORD));
		invStats.setItem(9, ItemBuilder.createItem(" : ", Material.IRON_SWORD));
		invStats.setItem(9, ItemBuilder.createItem("Kills : ", Material.IRON_SWORD));
		
		invRecipes.setItem(13, g);
		invStats.setItem(9, ItemBuilder.createItem("Kills : ", Material.IRON_SWORD));
		invStats.setItem(9, ItemBuilder.createItem("Kills : ", Material.IRON_SWORD));
		invStats.setItem(9, ItemBuilder.createItem("Kills : ", Material.IRON_SWORD));
		invStats.setItem(9, ItemBuilder.createItem("Kills : ", Material.IRON_SWORD));
		*/
		
		invStats.setItem(18, g);
		invStats.setItem(19, g);
		invStats.setItem(20, g);
		invStats.setItem(21, g);
		invStats.setItem(22, g);
		invStats.setItem(23, g);
		invStats.setItem(24, g);
		invStats.setItem(25, g);
		invStats.setItem(26, g);
	}
	
	public void setItemsCrafts() {
		ItemStack customCREEP = new ItemStack(Material.MONSTER_EGG, (byte) 0);
		ItemMeta cC = customCREEP.getItemMeta();
		cC.setDisplayName("Bombe");
		customCREEP.setItemMeta(cC);
		
		invRecipes.setItem(0, g);
		invRecipes.setItem(1, g);
		invRecipes.setItem(2, g);
		invRecipes.setItem(3, g);
		invRecipes.setItem(4, g);
		invRecipes.setItem(5, g);
		invRecipes.setItem(6, g);
		invRecipes.setItem(7, g);
		invRecipes.setItem(8, g);
		
		invRecipes.setItem(9, ItemBuilder.createItem("Epée en Alteryum", Material.DIAMOND_SWORD));
		invRecipes.setItem(10, customCREEP);
		invRecipes.setItem(11, ItemBuilder.createItem("Hache en Alteryum", Material.DIAMOND_AXE));
		invRecipes.setItem(12, ItemBuilder.createItem("Pioche en Remedia", Material.DIAMOND_PICKAXE));
		invRecipes.setItem(13, g);
		invRecipes.setItem(14, ItemBuilder.createItem("Plastron en Alteryum", Material.DIAMOND_CHESTPLATE));
		invRecipes.setItem(15, ItemBuilder.createItem("Jambière en Alteryum", Material.DIAMOND_LEGGINGS));
		invRecipes.setItem(16, ItemBuilder.createItem("Botte en Alteryum", Material.DIAMOND_BOOTS));
		invRecipes.setItem(17, ItemBuilder.createItem("Casque en Alteryum", Material.DIAMOND_HELMET));
		
		invRecipes.setItem(18, g);
		invRecipes.setItem(19, g);
		invRecipes.setItem(20, g);
		invRecipes.setItem(21, g);
		invRecipes.setItem(22, g);
		invRecipes.setItem(23, g);
		invRecipes.setItem(24, g);
		invRecipes.setItem(25, g);
		invRecipes.setItem(26, g);
	}
	
	/*
	public void setItemsQuests() {
		invQuest.setItem(4, ItemBuilder.createItem(Material.BED, "§8Description de la quête : ", Arrays.asList("")));
		invQuest.setItem(11, ItemBuilder.createItem(Material.GOLDEN_APPLE, "§aFaire la quête", Arrays.asList("")));
		invQuest.setItem(26, Shop.backDoor);
	}
	*/
}
