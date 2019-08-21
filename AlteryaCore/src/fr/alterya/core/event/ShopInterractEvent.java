package fr.alterya.core.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.alterya.core.Main;
import fr.alterya.core.shop.PrisesList;
import fr.alterya.core.shop.Shop;

public class ShopInterractEvent implements Listener 
{	
	PrisesList prisesList;
	
	Shop shop;
	
	@EventHandler
	public void onDoorInterract(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if(e.getCursor().getType() == Material.WOODEN_DOOR) {
			e.setCancelled(true);
			player.getOpenInventory().close();
		}
	}
	
	@SuppressWarnings("static-access")
	@EventHandler
	public void onShopInterractMain(InventoryClickEvent e) {
		if(e.isCancelled() == true) {
			e.setCancelled(false);
		}
		Player player = (Player) e.getWhoClicked();		
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
				}else if(itemClicked == Material.AIR) {
					e.setCancelled(true);
					return;
				}
			}
		}
	}

	@SuppressWarnings({
			"unlikely-arg-type", "static-access"
	})
	public void onShopInterractOthers(InventoryClickEvent e) {
		if(e.isCancelled() == true) {
			e.setCancelled(false);
		}
		Player player = (Player) e.getWhoClicked();
		if(e.getClickedInventory() == shop.shopInvUtils && e.getAction() != InventoryAction.PICKUP_ONE 
				|| e.getAction() != InventoryAction.PICKUP_ALL 
				|| e.getAction() != InventoryAction.PICKUP_HALF
				|| e.getAction() != InventoryAction.PICKUP_SOME) {
			Material clickedMaterial = e.getCurrentItem().getType();
			if(! clickedMaterial.equals(shop.prisesList)) {
				e.setCancelled(true);
				return;
			}else if(clickedMaterial.equals(shop.prisesList)) {
				ItemStack clickedItem = e.getCurrentItem();
				clickedItem.getType().equals(clickedMaterial);
				e.setCancelled(true);
				player.getOpenInventory().close();
				shop.openInvSellBuy(player, clickedItem);
				player.updateInventory();
			}else if(clickedMaterial == Material.AIR) {
				e.setCancelled(true);
				return;
			}
		}
	}
	
	@SuppressWarnings({
			"static-access", "unlikely-arg-type"
	})
	public void onShopInterractBlocks(InventoryClickEvent e) {
		if(e.isCancelled() == true) {
			e.setCancelled(false);
		}
		Player player = (Player) e.getWhoClicked();
		if(e.getClickedInventory() == shop.shopInvBlocks && e.getAction() != InventoryAction.PICKUP_ONE 
				|| e.getAction() != InventoryAction.PICKUP_ALL 
				|| e.getAction() != InventoryAction.PICKUP_HALF
				|| e.getAction() != InventoryAction.PICKUP_SOME) {
			Material clickedMaterial = e.getCurrentItem().getType();
			if(! clickedMaterial.equals(shop.prisesList)) {
				e.setCancelled(true);
				return;
			}else if(clickedMaterial.equals(shop.prisesList)) {
				ItemStack clickedItem = e.getCurrentItem();
				clickedItem.getType().equals(clickedMaterial);
				e.setCancelled(true);
				player.getOpenInventory().close();
				shop.openInvSellBuy(player, clickedItem);
				player.updateInventory();
			}else if(clickedMaterial == Material.AIR) {
				e.setCancelled(true);
				return;
			}
		}
	}
	
	@SuppressWarnings({
			"static-access", "unlikely-arg-type"
	})
	public void onShopInterractFarming(InventoryClickEvent e) {
		if(e.isCancelled() == true) {
			e.setCancelled(false);
		}
		Player player = (Player) e.getWhoClicked();
		if(e.getClickedInventory() == shop.shopInvFarming && e.getAction() != InventoryAction.PICKUP_ONE 
				|| e.getAction() != InventoryAction.PICKUP_ALL 
				|| e.getAction() != InventoryAction.PICKUP_HALF
				|| e.getAction() != InventoryAction.PICKUP_SOME) {
			Material clickedMaterial = e.getCurrentItem().getType();
			if(! clickedMaterial.equals(shop.prisesList)) {
				e.setCancelled(true);
				return;
			}else if(clickedMaterial.equals(shop.prisesList)) {
				ItemStack clickedItem = e.getCurrentItem();
				clickedItem.getType().equals(clickedMaterial);
				e.setCancelled(true);
				player.getOpenInventory().close();
				shop.openInvSellBuy(player, clickedItem);
				player.updateInventory();
			}else if(clickedMaterial == Material.AIR) {
				e.setCancelled(true);
				return;
			}
		}
	}
	
	@SuppressWarnings({
			"static-access", "unlikely-arg-type"
	})
	public void onShopInterractPillage(InventoryClickEvent e) {
		if(e.isCancelled() == true) {
			e.setCancelled(false);
		}
		Player player = (Player) e.getWhoClicked();
		if(e.getClickedInventory() == shop.shopInvPillage && e.getAction() != InventoryAction.PICKUP_ONE 
				|| e.getAction() != InventoryAction.PICKUP_ALL 
				|| e.getAction() != InventoryAction.PICKUP_HALF
				|| e.getAction() != InventoryAction.PICKUP_SOME) {
			Material clickedMaterial = e.getCurrentItem().getType();
			if(! clickedMaterial.equals(shop.prisesList)) {
				e.setCancelled(true);
				return;
			}else if(clickedMaterial.equals(shop.prisesList)) {
				ItemStack clickedItem = e.getCurrentItem();
				clickedItem.getType().equals(clickedMaterial);
				e.setCancelled(true);
				player.getOpenInventory().close();
				shop.openInvSellBuy(player, clickedItem);
				player.updateInventory();
			}else if(clickedMaterial == Material.AIR) {
				e.setCancelled(true);
				return;
			}
		}
	}
	
	@SuppressWarnings({
			"static-access", "unlikely-arg-type"
	})
	public void onShopInterractAlchemy(InventoryClickEvent e) {
		if(e.isCancelled() == true) {
			e.setCancelled(false);
		}
		Player player = (Player) e.getWhoClicked();
		if(e.getClickedInventory() == shop.shopInvAlchemy && e.getAction() != InventoryAction.PICKUP_ONE 
				|| e.getAction() != InventoryAction.PICKUP_ALL 
				|| e.getAction() != InventoryAction.PICKUP_HALF
				|| e.getAction() != InventoryAction.PICKUP_SOME) {
			Material clickedMaterial = e.getCurrentItem().getType();
			if(! clickedMaterial.equals(shop.prisesList)) {
				e.setCancelled(true);
				return;
			}else if(clickedMaterial.equals(shop.prisesList)) {
				ItemStack clickedItem = e.getCurrentItem();
				clickedItem.getType().equals(clickedMaterial);
				e.setCancelled(true);
				player.getOpenInventory().close();
				shop.openInvSellBuy(player, clickedItem);
				player.updateInventory();
			}else if(clickedMaterial == Material.AIR) {
				e.setCancelled(true);
				return;
			}
		}
	}
	
	@SuppressWarnings({
			"static-access", "unlikely-arg-type"
	})
	public void onShopInterractOres(InventoryClickEvent e) {
		if(e.isCancelled() == true) {
			e.setCancelled(false);
		}
		Player player = (Player) e.getWhoClicked();
		if(e.getClickedInventory() == shop.shopInvMinerals && e.getAction() != InventoryAction.PICKUP_ONE 
				|| e.getAction() != InventoryAction.PICKUP_ALL 
				|| e.getAction() != InventoryAction.PICKUP_HALF
				|| e.getAction() != InventoryAction.PICKUP_SOME) {
			Material clickedMaterial = e.getCurrentItem().getType();
			if(! clickedMaterial.equals(shop.prisesList)) {
				e.setCancelled(true);
				return;
			}else if(clickedMaterial.equals(shop.prisesList)) {
				ItemStack clickedItem = e.getCurrentItem();
				clickedItem.getType().equals(clickedMaterial);
				e.setCancelled(true);
				player.getOpenInventory().close();
				shop.openInvSellBuy(player, clickedItem);
				player.updateInventory();
			}else if(clickedMaterial == Material.AIR) {
				e.setCancelled(true);
				return;
			}
		}
	}

	@SuppressWarnings({
			"deprecation", "unused"
	})
	public void onSellBuyInterract(InventoryClickEvent e) {
		if(e.isCancelled() == true) {
			e.setCancelled(false);
		}
		Material clickedMaterial0 = e.getCurrentItem().getType();
		Player player = (Player) e.getWhoClicked();
		shop.setInvSellBuy(player);
		if(e.getClickedInventory() == Shop.shopInvSellBuy && shop.getShopPartOpened(player).equals(e.getClickedInventory())) {
			if(e.getCurrentItem() == shop.Buy) {
				Material clickedMaterial = e.getCurrentItem().getType();
				ItemStack clickedItem = e.getCurrentItem();
				clickedItem.getType().equals(clickedMaterial);
				PrisesList pl__ = PrisesList.valueOf(clickedMaterial.toString());
				prisesList = pl__;
					
				double amount = 0.00d;
					
				try {
					amount = Double.parseDouble(prisesList.getBuyPriseS());
				} catch(NumberFormatException e3) {
					e.setCancelled(true);
					return;
				}
				
				player.sendMessage(Main.prefix + "�aVous avez achet� x" + new ItemStack(prisesList.getMaterial()).getAmount() + " " + prisesList.getMaterial().toString() + " pour �e" + " $ �a!");
				if(player.getInventory().contains(prisesList.getMaterial()) && player.getInventory().contains(clickedItem.getAmount())) {
					clickedItem.getType().equals(clickedMaterial);
					player.getInventory().remove(clickedMaterial);
					e.setCancelled(true);
					return;
				}
			}else if(e.getCurrentItem() == shop.sell) {
				Material clickedMaterial = e.getCurrentItem().getType();
				ItemStack clickedItem = e.getCurrentItem();
				clickedItem.getType().equals(clickedMaterial);
				PrisesList pl__ = PrisesList.valueOf(clickedMaterial.toString());
				prisesList = pl__;
					
				double amount = 0.00d;
					
				try {
					amount = Double.parseDouble(prisesList.getSellPriseS());
				} catch(NumberFormatException e3) {
					e.setCancelled(true);
					return;
				}
				player.getInventory().addItem(new ItemStack(prisesList.getMaterial()));
				e.setCancelled(true);
				return;
			}else if(e.getCurrentItem() == shop.backDoor) {
				e.setCancelled(true);
				player.getOpenInventory().close();
				player.updateInventory();
				return;
			}else if(clickedMaterial0 == Material.AIR) {
				e.setCancelled(true);
				return;
			}
		}
	}
}