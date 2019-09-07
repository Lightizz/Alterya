package fr.alterya.core.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.alterya.core.util.MenuManager;

public class PlayerMenuListener implements Listener
{
	@EventHandler
	public void onInventoryInterractMain(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		MenuManager manager = new MenuManager(player);
		if(e.getInventory().getName() == manager.menu.getName()) {
			if(e.getCurrentItem().getType() == manager.clock.getType()) {
				e.setCancelled(true);
				player.openInventory(manager.invQuest);
				return;
			}
			if(e.getCurrentItem().getType() == manager.recipes.getType()) {
				e.setCancelled(true);
				player.openInventory(manager.invRecipes);
				return;
			}
			if(e.getCurrentItem().getType() == manager.stats.getType()) {
				e.setCancelled(true);
				player.openInventory(manager.invStats);
				return;
			}
			if(e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
				e.setCancelled(true);
				return;
			}
		}
	}
}
