package fr.alterya.core.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.alterya.core.MainCore;
import fr.alterya.core.playerMenu.MenuManager;

public class PlayerMenuListener implements Listener
{
	public MenuManager manager;
	MainCore m;
	
	public PlayerMenuListener(MainCore mainCore) {
		m = mainCore;
	}
	
	@EventHandler
	public void onInventoryInterractMain(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		manager = new MenuManager();
		if(e.getInventory().getName() == MenuManager.menu.getName()) {
			if(e.getCurrentItem().getType() == manager.clock.getType()) {
				e.setCancelled(true);
				//player.openInventory(manager.invQuest);
				player.sendMessage(MainCore.prefix + "§aCette option n'est pas encore disponnible sur le serveur.");
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
}
