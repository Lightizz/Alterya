package fr.alterya.core.playerMenu.DailyQuest;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;

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
	
	public void initQuests() {
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 1", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day1.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 2", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day2.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 3", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day3.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 4", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day4.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 5", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day5.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 6", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day6.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 7", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day7.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 8", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day8.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 9", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day9.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 10", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day10.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 11", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day11.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 12", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day12.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 13", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day13.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 14", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day14.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 15", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day15.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 16", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day16.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 17", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day17.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 18", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day18.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 19", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day19.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 20", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day20.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 21", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day21.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 22", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day22.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 23", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day23.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 24", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day24.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 25", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day25.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 26", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day26.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 27", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day27.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 28", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day28.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 29", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day29.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 30", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day30.Description")), "§aCliquez pour faire la quête !"), 1));
		manager.invQuest.addItem(ItemBuilder.createItem(Material.APPLE, "Jour 31", Arrays.asList(String.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day31.Description")), "§aCliquez pour faire la quête !"), 1));
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
	}
}
