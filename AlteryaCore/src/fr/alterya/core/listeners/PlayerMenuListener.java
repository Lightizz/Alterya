package fr.alterya.core.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import fr.alterya.core.MainCore;
import fr.alterya.core.playerMenu.MenuManager;
import fr.alterya.core.util.ItemBuilder;

public class PlayerMenuListener implements Listener
{
	public MenuManager manager;
	MainCore m;
	
	public PlayerMenuListener(MainCore mainCore) {
		m = mainCore;
	}
	
	@EventHandler
	void onInventoryInterractMain(InventoryClickEvent e) {
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
	
	Inventory craftInv = Bukkit.createInventory(null, 3*9, "Craft");
	
	@EventHandler
	void onInventoryInterractRecipes(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		manager = new MenuManager();
		if(e.getInventory().getName() == craftInv.getName()) {
			if(e.getCurrentItem().getType() == Material.WORKBENCH) {
				e.setCancelled(true);
				player.openWorkbench(null, true);
				return;
			}
			if(e.getCurrentItem().getType() == Material.ANVIL) {
				e.setCancelled(true);
				player.getOpenInventory().close();
				return;
			}
			e.setCancelled(true);
			return;
		}
		if(e.getInventory().getName() == MenuManager.invRecipes.getName()) {
			if(e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE || e.getCurrentItem().getType() == Material.AIR) { e.setCancelled(true); return; }
			
			if(e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
				e.setCancelled(true);
				craftInv.clear();
				player.openInventory(craftInv);
				
				player.getOpenInventory().setItem(0, MenuManager.g);
				player.getOpenInventory().setItem(1, MenuManager.g);
				player.getOpenInventory().setItem(2, MenuManager.g);
				
				player.getOpenInventory().setItem(9, MenuManager.g);
				player.getOpenInventory().setItem(10, ItemBuilder.createItem("§1Ouvrir une table de craft", Material.WORKBENCH));
				player.getOpenInventory().setItem(11, MenuManager.g);
				
				player.getOpenInventory().setItem(20, MenuManager.g);
				player.getOpenInventory().setItem(18, MenuManager.g);
				player.getOpenInventory().setItem(19, MenuManager.g);
				
				player.getOpenInventory().setItem(4, ItemBuilder.createItem(Material.DIAMOND_BLOCK));
				player.getOpenInventory().setItem(13, ItemBuilder.createItem(Material.DIAMOND_BLOCK));
				
				player.getOpenInventory().setItem(12, ItemBuilder.createItem(Material.EMERALD_BLOCK));
				player.getOpenInventory().setItem(14, ItemBuilder.createItem(Material.EMERALD_BLOCK));
				
				player.getOpenInventory().setItem(22, ItemBuilder.createItem(Material.BLAZE_ROD));
				
				player.getOpenInventory().setItem(6, MenuManager.g);
				player.getOpenInventory().setItem(7, MenuManager.g);
				player.getOpenInventory().setItem(8, MenuManager.g);
				
				player.getOpenInventory().setItem(17, MenuManager.g);
				player.getOpenInventory().setItem(16, ItemBuilder.createItem("§4§lFermer le menu", Material.ANVIL));
				player.getOpenInventory().setItem(15, MenuManager.g);
				
				player.getOpenInventory().setItem(26, MenuManager.g);
				player.getOpenInventory().setItem(25, MenuManager.g);
				player.getOpenInventory().setItem(24, MenuManager.g);
			}
			
			if(e.getCurrentItem().getType() == Material.MONSTER_EGG) {
				e.setCancelled(true);
				craftInv.clear();
				player.openInventory(craftInv);
				
				player.getOpenInventory().setItem(0, MenuManager.g);
				player.getOpenInventory().setItem(1, MenuManager.g);
				player.getOpenInventory().setItem(2, MenuManager.g);
				
				player.getOpenInventory().setItem(9, MenuManager.g);
				player.getOpenInventory().setItem(10, ItemBuilder.createItem("§1Ouvrir une table de craft", Material.WORKBENCH));
				player.getOpenInventory().setItem(11, MenuManager.g);
				
				player.getOpenInventory().setItem(20, MenuManager.g);
				player.getOpenInventory().setItem(18, MenuManager.g);
				player.getOpenInventory().setItem(19, MenuManager.g);
				
				player.getOpenInventory().setItem(3, ItemBuilder.createItem(Material.TNT));
				player.getOpenInventory().setItem(4, ItemBuilder.createItem(Material.TNT));
				player.getOpenInventory().setItem(5, ItemBuilder.createItem(Material.TNT));
				
				player.getOpenInventory().setItem(12, ItemBuilder.createItem(Material.TNT));
				player.getOpenInventory().setItem(13, ItemBuilder.createItem(Material.REDSTONE));
				player.getOpenInventory().setItem(14, ItemBuilder.createItem(Material.TNT));
				
				player.getOpenInventory().setItem(21, ItemBuilder.createItem(Material.SULPHUR));
				player.getOpenInventory().setItem(22, ItemBuilder.createItem(Material.SULPHUR));
				player.getOpenInventory().setItem(23, ItemBuilder.createItem(Material.SULPHUR));
				
				player.getOpenInventory().setItem(6, MenuManager.g);
				player.getOpenInventory().setItem(7, MenuManager.g);
				player.getOpenInventory().setItem(8, MenuManager.g);
				
				player.getOpenInventory().setItem(17, MenuManager.g);
				player.getOpenInventory().setItem(16, ItemBuilder.createItem("§4§lFermer le menu", Material.ANVIL));
				player.getOpenInventory().setItem(15, MenuManager.g);
				
				player.getOpenInventory().setItem(26, MenuManager.g);
				player.getOpenInventory().setItem(25, MenuManager.g);
				player.getOpenInventory().setItem(24, MenuManager.g);
			}
			
			if(e.getCurrentItem().getType() == Material.DIAMOND_AXE) {
				e.setCancelled(true);
				craftInv.clear();
				player.openInventory(craftInv);
				
				player.getOpenInventory().setItem(0, MenuManager.g);
				player.getOpenInventory().setItem(1, MenuManager.g);
				player.getOpenInventory().setItem(2, MenuManager.g);
				
				player.getOpenInventory().setItem(9, MenuManager.g);
				player.getOpenInventory().setItem(10, ItemBuilder.createItem("§1Ouvrir une table de craft", Material.WORKBENCH));
				player.getOpenInventory().setItem(11, MenuManager.g);
				
				player.getOpenInventory().setItem(20, MenuManager.g);
				player.getOpenInventory().setItem(18, MenuManager.g);
				player.getOpenInventory().setItem(19, MenuManager.g);
				
				player.getOpenInventory().setItem(3, ItemBuilder.createItem(Material.EMERALD));
				player.getOpenInventory().setItem(5, ItemBuilder.createItem(Material.EMERALD));
				
				player.getOpenInventory().setItem(12, ItemBuilder.createItem(Material.DIAMOND_BLOCK));
				player.getOpenInventory().setItem(13, ItemBuilder.createItem(Material.BLAZE_ROD));
				player.getOpenInventory().setItem(14, ItemBuilder.createItem(Material.DIAMOND_BLOCK));
				
				player.getOpenInventory().setItem(22, ItemBuilder.createItem(Material.BLAZE_ROD));

				player.getOpenInventory().setItem(6, MenuManager.g);
				player.getOpenInventory().setItem(7, MenuManager.g);
				player.getOpenInventory().setItem(8, MenuManager.g);
				
				player.getOpenInventory().setItem(17, MenuManager.g);
				player.getOpenInventory().setItem(16, ItemBuilder.createItem("§4§lFermer le menu", Material.ANVIL));
				player.getOpenInventory().setItem(15, MenuManager.g);
				
				player.getOpenInventory().setItem(26, MenuManager.g);
				player.getOpenInventory().setItem(25, MenuManager.g);
				player.getOpenInventory().setItem(24, MenuManager.g);
			}
			
			if(e.getCurrentItem().getType() == Material.DIAMOND_PICKAXE) {
				e.setCancelled(true);
				craftInv.clear();
				player.openInventory(craftInv);
				
				player.getOpenInventory().setItem(0, MenuManager.g);
				player.getOpenInventory().setItem(1, MenuManager.g);
				player.getOpenInventory().setItem(2, MenuManager.g);
				
				player.getOpenInventory().setItem(9, MenuManager.g);
				player.getOpenInventory().setItem(10, ItemBuilder.createItem("§1Ouvrir une table de craft", Material.WORKBENCH));
				player.getOpenInventory().setItem(11, MenuManager.g);
				
				player.getOpenInventory().setItem(20, MenuManager.g);
				player.getOpenInventory().setItem(18, MenuManager.g);
				player.getOpenInventory().setItem(19, MenuManager.g);
				
				player.getOpenInventory().setItem(3, ItemBuilder.createItem(Material.DIAMOND));
				player.getOpenInventory().setItem(4, ItemBuilder.createItem(Material.DIAMOND_BLOCK));
				player.getOpenInventory().setItem(5, ItemBuilder.createItem(Material.DIAMOND));
				
				player.getOpenInventory().setItem(13, ItemBuilder.createItem(Material.BLAZE_ROD));
				
				player.getOpenInventory().setItem(22, ItemBuilder.createItem(Material.BLAZE_ROD));

				player.getOpenInventory().setItem(6, MenuManager.g);
				player.getOpenInventory().setItem(7, MenuManager.g);
				player.getOpenInventory().setItem(8, MenuManager.g);
				
				player.getOpenInventory().setItem(17, MenuManager.g);
				player.getOpenInventory().setItem(16, ItemBuilder.createItem("§4§lFermer le menu", Material.ANVIL));
				player.getOpenInventory().setItem(15, MenuManager.g);
				
				player.getOpenInventory().setItem(26, MenuManager.g);
				player.getOpenInventory().setItem(25, MenuManager.g);
				player.getOpenInventory().setItem(24, MenuManager.g);
			}
			
			if(e.getCurrentItem().getType() == Material.DIAMOND_HELMET) {
				e.setCancelled(true);
				craftInv.clear();
				player.openInventory(craftInv);
				
				player.getOpenInventory().setItem(0, MenuManager.g);
				player.getOpenInventory().setItem(1, MenuManager.g);
				player.getOpenInventory().setItem(2, MenuManager.g);
				
				player.getOpenInventory().setItem(9, MenuManager.g);
				player.getOpenInventory().setItem(10, ItemBuilder.createItem("§1Ouvrir une table de craft", Material.WORKBENCH));
				player.getOpenInventory().setItem(11, MenuManager.g);
				
				player.getOpenInventory().setItem(20, MenuManager.g);
				player.getOpenInventory().setItem(18, MenuManager.g);
				player.getOpenInventory().setItem(19, MenuManager.g);
				
				player.getOpenInventory().setItem(3, ItemBuilder.createItem(Material.EMERALD));
				player.getOpenInventory().setItem(4, ItemBuilder.createItem(Material.DIAMOND_BLOCK));
				player.getOpenInventory().setItem(5, ItemBuilder.createItem(Material.EMERALD));
				
				player.getOpenInventory().setItem(12, ItemBuilder.createItem(Material.EMERALD));
				player.getOpenInventory().setItem(14, ItemBuilder.createItem(Material.EMERALD));

				player.getOpenInventory().setItem(6, MenuManager.g);
				player.getOpenInventory().setItem(7, MenuManager.g);
				player.getOpenInventory().setItem(8, MenuManager.g);
				
				player.getOpenInventory().setItem(17, MenuManager.g);
				player.getOpenInventory().setItem(16, ItemBuilder.createItem("§4§lFermer le menu", Material.ANVIL));
				player.getOpenInventory().setItem(15, MenuManager.g);
				
				player.getOpenInventory().setItem(26, MenuManager.g);
				player.getOpenInventory().setItem(25, MenuManager.g);
				player.getOpenInventory().setItem(24, MenuManager.g);
			}
			
			if(e.getCurrentItem().getType() == Material.DIAMOND_CHESTPLATE) {
				e.setCancelled(true);
				craftInv.clear();
				player.openInventory(craftInv);
				
				player.getOpenInventory().setItem(0, MenuManager.g);
				player.getOpenInventory().setItem(1, MenuManager.g);
				player.getOpenInventory().setItem(2, MenuManager.g);
				
				player.getOpenInventory().setItem(9, MenuManager.g);
				player.getOpenInventory().setItem(10, ItemBuilder.createItem("§1Ouvrir une table de craft", Material.WORKBENCH));
				player.getOpenInventory().setItem(11, MenuManager.g);
				
				player.getOpenInventory().setItem(20, MenuManager.g);
				player.getOpenInventory().setItem(18, MenuManager.g);
				player.getOpenInventory().setItem(19, MenuManager.g);
				
				player.getOpenInventory().setItem(3, ItemBuilder.createItem(Material.DIAMOND_BLOCK));
				player.getOpenInventory().setItem(5, ItemBuilder.createItem(Material.DIAMOND_BLOCK));
				
				player.getOpenInventory().setItem(12, ItemBuilder.createItem(Material.EMERALD_BLOCK));
				player.getOpenInventory().setItem(13, ItemBuilder.createItem(Material.EMERALD_BLOCK));
				player.getOpenInventory().setItem(14, ItemBuilder.createItem(Material.EMERALD_BLOCK));
				
				player.getOpenInventory().setItem(21, ItemBuilder.createItem(Material.EMERALD_BLOCK));
				player.getOpenInventory().setItem(22, ItemBuilder.createItem(Material.EMERALD_BLOCK));
				player.getOpenInventory().setItem(23, ItemBuilder.createItem(Material.EMERALD_BLOCK));

				player.getOpenInventory().setItem(6, MenuManager.g);
				player.getOpenInventory().setItem(7, MenuManager.g);
				player.getOpenInventory().setItem(8, MenuManager.g);
				
				player.getOpenInventory().setItem(17, MenuManager.g);
				player.getOpenInventory().setItem(16, ItemBuilder.createItem("§4§lFermer le menu", Material.ANVIL));
				player.getOpenInventory().setItem(15, MenuManager.g);
				
				player.getOpenInventory().setItem(26, MenuManager.g);
				player.getOpenInventory().setItem(25, MenuManager.g);
				player.getOpenInventory().setItem(24, MenuManager.g);
			}
			
			if(e.getCurrentItem().getType() == Material.DIAMOND_LEGGINGS) {
				e.setCancelled(true);
				craftInv.clear();
				player.openInventory(craftInv);
				
				player.getOpenInventory().setItem(0, MenuManager.g);
				player.getOpenInventory().setItem(1, MenuManager.g);
				player.getOpenInventory().setItem(2, MenuManager.g);
				
				player.getOpenInventory().setItem(9, MenuManager.g);
				player.getOpenInventory().setItem(10, ItemBuilder.createItem("§1Ouvrir une table de craft", Material.WORKBENCH));
				player.getOpenInventory().setItem(11, MenuManager.g);
				
				player.getOpenInventory().setItem(20, MenuManager.g);
				player.getOpenInventory().setItem(18, MenuManager.g);
				player.getOpenInventory().setItem(19, MenuManager.g);
				
				player.getOpenInventory().setItem(3, ItemBuilder.createItem(Material.EMERALD_BLOCK));
				player.getOpenInventory().setItem(4, ItemBuilder.createItem(Material.DIAMOND_BLOCK));
				player.getOpenInventory().setItem(5, ItemBuilder.createItem(Material.EMERALD_BLOCK));
				
				player.getOpenInventory().setItem(12, ItemBuilder.createItem(Material.EMERALD_BLOCK));
				player.getOpenInventory().setItem(14, ItemBuilder.createItem(Material.EMERALD_BLOCK));
				
				player.getOpenInventory().setItem(21, ItemBuilder.createItem(Material.EMERALD_BLOCK));
				player.getOpenInventory().setItem(23, ItemBuilder.createItem(Material.EMERALD_BLOCK));

				player.getOpenInventory().setItem(6, MenuManager.g);
				player.getOpenInventory().setItem(7, MenuManager.g);
				player.getOpenInventory().setItem(8, MenuManager.g);
				
				player.getOpenInventory().setItem(17, MenuManager.g);
				player.getOpenInventory().setItem(16, ItemBuilder.createItem("§4§lFermer le menu", Material.ANVIL));
				player.getOpenInventory().setItem(15, MenuManager.g);
				
				player.getOpenInventory().setItem(26, MenuManager.g);
				player.getOpenInventory().setItem(25, MenuManager.g);
				player.getOpenInventory().setItem(24, MenuManager.g);
			}
			
			if(e.getCurrentItem().getType() == Material.DIAMOND_BOOTS) {
				e.setCancelled(true);
				craftInv.clear();
				player.openInventory(craftInv);
				
				player.getOpenInventory().setItem(0, MenuManager.g);
				player.getOpenInventory().setItem(1, MenuManager.g);
				player.getOpenInventory().setItem(2, MenuManager.g);
				
				player.getOpenInventory().setItem(9, MenuManager.g);
				player.getOpenInventory().setItem(10, ItemBuilder.createItem("§1Ouvrir une table de craft", Material.WORKBENCH));
				player.getOpenInventory().setItem(11, MenuManager.g);
				
				player.getOpenInventory().setItem(20, MenuManager.g);
				player.getOpenInventory().setItem(18, MenuManager.g);
				player.getOpenInventory().setItem(19, MenuManager.g);
				
				player.getOpenInventory().setItem(12, ItemBuilder.createItem(Material.DIAMOND_BLOCK));
				player.getOpenInventory().setItem(14, ItemBuilder.createItem(Material.DIAMOND_BLOCK));
				
				player.getOpenInventory().setItem(21, ItemBuilder.createItem(Material.EMERALD_BLOCK));
				player.getOpenInventory().setItem(23, ItemBuilder.createItem(Material.EMERALD_BLOCK));

				player.getOpenInventory().setItem(6, MenuManager.g);
				player.getOpenInventory().setItem(7, MenuManager.g);
				player.getOpenInventory().setItem(8, MenuManager.g);
				
				player.getOpenInventory().setItem(17, MenuManager.g);
				player.getOpenInventory().setItem(16, ItemBuilder.createItem("§4§lFermer le menu", Material.ANVIL));
				player.getOpenInventory().setItem(15, MenuManager.g);
				
				player.getOpenInventory().setItem(26, MenuManager.g);
				player.getOpenInventory().setItem(25, MenuManager.g);
				player.getOpenInventory().setItem(24, MenuManager.g);
			}
		}
	}
}
