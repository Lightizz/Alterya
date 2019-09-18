package fr.alterya.core.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.alterya.core.MainCore;
import fr.alterya.core.playerMenu.MenuManager;

public class PlayerMenuListener implements Listener
{
	public MenuManager manager;
	
	@EventHandler
	public void onInventoryInterractMain(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		manager = new MenuManager(player.getUniqueId().toString());
		if(e.getInventory().getName() == MenuManager.menu.getName()) {
			if(e.getCurrentItem().getType() == manager.clock.getType()) {
				e.setCancelled(true);
				player.openInventory(manager.invQuest);
				return;
			}
			if(e.getCurrentItem().getType() == manager.recipes.getType()) {
				e.setCancelled(true);
				player.openInventory(MenuManager.invRecipes);
				return;
			}
			if(e.getCurrentItem().getType() == manager.stats.getType()) {
				e.setCancelled(true);
				player.openInventory(MenuManager.invStats);
				return;
			}
			if(e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE || e.getCurrentItem() == null) {
				e.setCancelled(true);
				return;
			}
		}
	}
	
	@EventHandler
	public void onInventoryInterractDailyQuest(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		this.manager = new MenuManager(player.getUniqueId().toString());
		if(e.getInventory().getName() == manager.invQuest.getName()) {
			if(e.getCurrentItem().getType() == Material.APPLE || e.getCurrentItem().getType() == Material.AIR) {
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 1") {
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day1.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 
				
				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day1.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day1.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day1").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day1.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day1.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 2") {
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day2.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				}
				
				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day2.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day2.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day2").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day2.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day2.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 3") {
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day3.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				}

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day3.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day3.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day3").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day3.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day3.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 4") {
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day4.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day4.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day4.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day4").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day4.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day4.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 5") {
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day5.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day5.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day5.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day5").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day5.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day5.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 6") {
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day6.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day6.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day6.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day6").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day6.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day6.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 7") {
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day7.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day7.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day7.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day7").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day7.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day7.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 8") {
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day8.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day8.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day8.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day8").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day8.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day8.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 9") {
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day9.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day9.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day9.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day9").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day9.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day9.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 10") {
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day10.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day10.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day10.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day10").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day10.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day10.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 11") {
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day11.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day11.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day11.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day11").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day11.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day11.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 12") {
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day12.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day12.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day12.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day12").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day12.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day12.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 13") {
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day13.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day13.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day13.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day13").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day13.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day13.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 14") {
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day14.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day14.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day14.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day14").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day14.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day14.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 15") {
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day15.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day15.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day15.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day15").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day15.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day15.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 16") {

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day16.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day16.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day16.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day16").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day16.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day16.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 17") {

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day17.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day17.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day17.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day17").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day17.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day17.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 18") {

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day18.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day18.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day18.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day18").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day18.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day18.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 19") {

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day19.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day19.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day19.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day19").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day19.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day19.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 20") {

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day20.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day20.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day20.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day20").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day20.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day20.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 21") {

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day21.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day21.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day21.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day21").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day21.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day21.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 22") {

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day22.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day22.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day22.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day22").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day22.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day22.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 23") {

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day23.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day23.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day23.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day23").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day23.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day23.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 24") {

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day24.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day24.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day24.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day24").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day24.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day24.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 25") {

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day25.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day25.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day25.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day25").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day25.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day25.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 26") {

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day26.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day26.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day26.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day26").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day26.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day26.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 27") {

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day27.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day27.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day27.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day27").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day27.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day27.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 28") {

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day28.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day28.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day28.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day28").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day28.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day28.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 29") {

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day29.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day29.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day29.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day29").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day29.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day29.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 30") {

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day30.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day30.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day30.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day30").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day30.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day30.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getItemMeta().getDisplayName() == "Jour 31") {

				if(! e.getCurrentItem().getItemMeta().getLore().contains("§aCliquez pour faire la quête !")) {
					player.sendMessage("Cette quête n'est pas celle du jour !");
					e.setCancelled(true);
					return;
				}
				
				if((manager.getQuestFile(player.getUniqueId().toString()).config.getBoolean("Day31.IsSucces") == true)) {
					player.sendMessage(MainCore.prefix + "Vous avez déjà accomplis cette quête.");
					e.setCancelled(true);
					return;
				} 
				ItemStack itemToRemove = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day31.ItemRequire")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day31.ItemRequireAmount"));
				if(! player.getInventory().contains(itemToRemove)) {
					player.sendMessage(MainCore.prefix + "§aVous n'avez pas l'item demandé pour réussir la quête dans votre inventaire.");
					e.setCancelled(true);
					return;
				}
				player.getInventory().remove(itemToRemove);
				manager.getQuestFile(player.getUniqueId().toString()).config.getConfigurationSection("Day31").set("IsSucces", true);
				ItemStack itemToGive = new ItemStack(Material.valueOf(manager.getQuestFile(player.getUniqueId().toString()).getString("Day31.ItemSucces")), manager.getQuestFile(player.getUniqueId().toString()).getInt("Day31.ItemSuccesAmount"));
				player.getInventory().addItem(itemToGive);
				manager.getQuestFile(player.getUniqueId().toString()).saveConfig();
				player.updateInventory();
				e.setCancelled(true);
				return;
			}
			e.setCancelled(true);
			return;
		}
	}
}
