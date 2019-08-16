package fr.alterya.core.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.alterya.core.shop.Shop;

public class ShopInterractEvent implements Listener 
{
	Shop shop;
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void onShopInterractMain(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		e.setCancelled(true);
		
		if(e.getClickedInventory() == shop.shopInvMain && e.getAction() != InventoryAction.PICKUP_ONE) {
			if(e.getCurrentItem().getType() == Material.YELLOW_FLOWER 
				||e.getCurrentItem().getType() == Material.EMPTY_MAP 
				||e.getCurrentItem().getType() == Material.BLAZE_POWDER 
				||e.getCurrentItem().getType() == Material.STONE
				||e.getCurrentItem().getType() == Material.IRON_AXE
				||e.getCurrentItem().getType() == Material.DIAMOND_HOE
				||e.getCurrentItem().getType() == Material.IRON_INGOT) {
				Material itemClicked = e.getCurrentItem().getType();
				if(itemClicked == Material.YELLOW_FLOWER) {
					e.setCancelled(true);
					player.getOpenInventory().close();
					player.openInventory(shop.shopInvFarming);
					player.updateInventory();
					return;
				}else if(itemClicked == Material.EMPTY_MAP) {
					e.setCancelled(true);
					player.getOpenInventory().close();
					player.openInventory(shop.shopInvUtils);
					player.updateInventory();
					return;
				}else if(itemClicked == Material.BLAZE_POWDER) {
					e.setCancelled(true);
					player.getOpenInventory().close();
					player.openInventory(shop.shopInvAlchemy);
					player.updateInventory();
					return;
				}else if(itemClicked == Material.STONE) {
					e.setCancelled(true);
					player.getOpenInventory().close();
					player.openInventory(shop.shopInvBlocks);
					player.updateInventory();
					return;
				}else if(itemClicked == Material.IRON_AXE) {
					e.setCancelled(true);
					player.getOpenInventory().close();
					player.openInventory(shop.shopInvPillage);
					player.updateInventory();
					return;
				}else if(itemClicked == Material.DIAMOND_HOE) {
					e.setCancelled(true);
					player.getOpenInventory().close();
					player.openInventory(shop.shopInvFarming);
					player.updateInventory();
					return;
				}else if(itemClicked == Material.IRON_INGOT) {
					e.setCancelled(true);
					player.getOpenInventory().close();
					player.openInventory(shop.shopInvMinerals);
					player.updateInventory();
					return;
				}else {
					e.setCancelled(true);
					return;
				}
				
			}else {
				e.setCancelled(true);
				return;
			}
		}else {
			e.setCancelled(true);
			return;
		}
	}
}
