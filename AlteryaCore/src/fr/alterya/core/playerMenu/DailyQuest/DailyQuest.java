package fr.alterya.core.playerMenu.DailyQuest;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.alterya.core.MainCore;
import fr.alterya.core.playerMenu.MenuManager;
import fr.alterya.core.util.ItemBuilder;

public class DailyQuest
{
	public MainCore mainCore;
	public MenuManager manager;
	public Player player;
	
	public DailyQuest(MainCore mainCore, MenuManager manager, Player player) {
		this.manager = manager;
		this.mainCore = mainCore;
		this.player = player;
		initQuests();
	}
	
	@SuppressWarnings("deprecation")
	public void initQuests() {
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 1", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 2", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 3", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 4", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 5", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 6", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 7", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 8", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 9", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 10", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 11", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 12", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 13", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 14", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 15", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 16", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 17", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 18", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 19", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 20", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 21", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 22", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 23", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 24", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 25", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 26", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 27", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 28", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 29", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 30", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 31", Arrays.asList("Ceci n'est pas la quête du jour"), 1));
		manager.invQuest.setItem(31, null);
		manager.invQuest.setItem(32, null);
		manager.invQuest.setItem(33, null);
		manager.invQuest.setItem(34, null);
		manager.invQuest.setItem(35, null);
		manager.invQuest.setItem(36, null);
		manager.invQuest.setItem(37, null);
		manager.invQuest.setItem(38, null);
		manager.invQuest.setItem(39, null);
		manager.invQuest.setItem(40, null);
		manager.invQuest.setItem(41, null);
		manager.invQuest.setItem(42, null);
		manager.invQuest.setItem(43, null);
		manager.invQuest.setItem(44, null);
		
		for(ItemStack i : manager.invQuest.getContents()) {
			if(i == null) {return;}
			if(i.getItemMeta().getDisplayName().endsWith(" " + manager.d.getDay())) {
				i.getItemMeta().setLore(Arrays.asList(manager.getQuestFile(player.getUniqueId().toString()).getString("Day " + i.getItemMeta().getDisplayName().lastIndexOf(2) + ".Description"), "§aCliquez pour faire la quête !" ));
			}
		}
	}
}
