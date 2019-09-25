package fr.alterya.core.playerMenu;

import java.util.Date;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.alterya.core.util.ItemBuilder;

public class MenuManager
{
	public HashMap<String, Boolean> questSuccesPlayers = new HashMap<String, Boolean>();
	
	public static int lastDay;
	public static int day;
	
	public ItemStack clock = ItemBuilder.createItem("§aQuêtes journalières", Material.WATCH);
	public ItemStack stats = ItemBuilder.createItem("§3Statistiques", Material.SKULL_ITEM);
	public ItemStack recipes = ItemBuilder.createItem("§cRecetes", Material.BOOK);
	
	public static Inventory menu = Bukkit.createInventory(null, 1*9, "§bMenu joueur");
	public Inventory invQuest = Bukkit.createInventory(null, 3*9, "§aQuêtes journalières");
	public static Inventory invStats = Bukkit.createInventory(null, 3*9, "§3Statistiques");
	public static Inventory invRecipes = Bukkit.createInventory(null, 3*9, "§cRecetes");
	
	public static Date d = new Date();
	
	@SuppressWarnings("deprecation")
	public MenuManager() {
		setItemsMenu();
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
	
	/*
	public void setItemsQuests() {
		invQuest.setItem(4, ItemBuilder.createItem(Material.BED, "§8Description de la quête : ", Arrays.asList("")));
		invQuest.setItem(11, ItemBuilder.createItem(Material.GOLDEN_APPLE, "§aFaire la quête", Arrays.asList("")));
		invQuest.setItem(26, Shop.backDoor);
	}
	*/
}
