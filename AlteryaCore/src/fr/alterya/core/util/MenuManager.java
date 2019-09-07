package fr.alterya.core.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.bukkit.ChatColor;

public class MenuManager
{
	Player player;
	
	public MenuManager(Player player) {
		this.player = player;
		setItems();
	}
	
	public ItemStack clock = ItemBuilder.createItem(Material.WATCH, ChatColor.AQUA + "Quêtes journalières");
	public ItemStack stats = ItemBuilder.createItem(Material.SKULL_ITEM, ChatColor.YELLOW + "Statistiques");
	public ItemStack recipes = ItemBuilder.createItem(Material.BOOK, ChatColor.AQUA + "Recetes");
	
	public Inventory menu = Bukkit.createInventory(null, 1*9, "Menu joueur");
	public Inventory invQuest = Bukkit.createInventory(null, 5*9, ChatColor.AQUA + "Quêtes journalières");
	public Inventory invStats = Bukkit.createInventory(null, 5*9, ChatColor.YELLOW + "Statistiques");
	public Inventory invRecipes = Bukkit.createInventory(null, 5*9, ChatColor.AQUA + "Recetes");
	
	public void openMenu(Player player) {
		player.openInventory(menu);
	}
	
	public void setItems() {
		ItemStack customGlas = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
		ItemMeta customG = customGlas.getItemMeta();
		customG.setDisplayName(" ");
		customGlas.setItemMeta(customG);
		
		menu.setItem(0, customGlas);
		menu.setItem(1, customGlas);
		menu.setItem(2, clock);
		menu.setItem(3, customGlas);
		menu.setItem(4, stats);
		menu.setItem(5, customGlas);
		menu.setItem(6, recipes);
		menu.setItem(7, customGlas);
		menu.setItem(8, customGlas);
	}
}
