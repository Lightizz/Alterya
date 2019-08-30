package fr.alterya.core.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.alterya.core.MainCore;
import fr.alterya.core.money.MoneyManager;
import fr.alterya.core.shop.PrisesList;
import fr.alterya.core.shop.Shop;
import fr.alterya.core.util.ItemBuilder;

public class ShopInterractEvent implements Listener 
{	
	PrisesList prisesList;
	Shop shop;
	
	//Partie qui gère l'objet de retour
	@EventHandler
	public void onDoorInterract(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if(e.getCurrentItem().getType() == Material.ANVIL) {
			e.setCancelled(true);
			player.getOpenInventory().close();
		}
	}
	
	//Partie contrôlant l'interface principale du shop
	@EventHandler
	public void onShopInterractMain(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory mainInv = e.getInventory();
		
		if(mainInv == null) return;
		
		if(e.getInventory().getName() == Shop.shopInvMain.getName()) {
			ItemStack itemClicked = e.getCurrentItem();
			if(itemClicked.getType() == Material.EMPTY_MAP) {
				e.setCancelled(true);
				player.getOpenInventory().close();
				player.openInventory(Shop.shopInvUtils);
				player.updateInventory();
				return;
			}else if(itemClicked.getType() == Material.BLAZE_POWDER) {
				e.setCancelled(true);
				player.getOpenInventory().close();
				player.openInventory(Shop.shopInvAlchemy);
				player.updateInventory();
				return;
			}else if(itemClicked.getType() == Material.STONE) {
				e.setCancelled(true);
				player.getOpenInventory().close();
				player.openInventory(Shop.shopInvBlocks);
				player.updateInventory();
				return;
			}else if(itemClicked.getType() == Material.IRON_AXE) {
				e.setCancelled(true);
				player.getOpenInventory().close();
				player.openInventory(Shop.shopInvPillage);
				player.updateInventory();
				return;
			}else if(itemClicked.getType() == Material.DIAMOND_HOE) {
				e.setCancelled(true);
				player.getOpenInventory().close();
				player.openInventory(Shop.shopInvFarming);
				player.updateInventory();
				return;
			}else if(itemClicked.getType() == Material.IRON_INGOT) {
				e.setCancelled(true);
				player.getOpenInventory().close();
				player.openInventory(Shop.shopInvMinerals);
				player.updateInventory();
				return;
			}
		}
	}

	//Partie pour ouvrir l'inventaire de confirmation d'achat / de vente
	@EventHandler
	public void onPartInterract(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory mainInv = e.getInventory();
		
		if(mainInv == null) return;
		
		if(e.getInventory().getName() == Shop.shopInvBlocks.getName() 
		 ||e.getInventory().getName() == Shop.shopInvAlchemy.getName()
		 ||e.getInventory().getName() == Shop.shopInvFarming.getName()
		 ||e.getInventory().getName() == Shop.shopInvMinerals.getName()
		 ||e.getInventory().getName() == Shop.shopInvPillage.getName()
		 ||e.getInventory().getName() == Shop.shopInvUtils.getName()) {
			ItemStack itemClicked = e.getCurrentItem();
			ItemMeta itemM = itemClicked.getItemMeta();
			for(PrisesList item : PrisesList.values()) {
				if(itemClicked.getType() == item.getMaterial()) {
					itemM.setDisplayName(item.getName());
				}
			}
			itemClicked.setItemMeta(itemM);
			Shop.setItemInConfirmInv();
			if(itemClicked.getType() == Material.AIR || itemClicked.getType() == Material.ANVIL) {return;}
			Shop.shopInvSellBuy.setItem(13, itemClicked);
			e.setCancelled(true);
			player.getOpenInventory().close();
			player.openInventory(Shop.shopInvSellBuy);
		}
	}

	//Partie concerant l'inventaire de confirmation d'achat / vente
	@EventHandler
	public void onSelectAmountPartInterract(InventoryClickEvent e) {
		Inventory mainInv = e.getInventory();
		
		if(mainInv == null) return;
		
		if(e.getInventory().getName() == Shop.shopInvSellBuy.getName()) {
			ItemStack itemClicked = e.getCurrentItem();
			ItemStack itemAdd1 = mainInv.getItem(19);
			ItemStack itemAdd10 = mainInv.getItem(20);
			ItemStack itemAdd64 = mainInv.getItem(21);
			ItemStack itemSubstract1 = mainInv.getItem(25);
			ItemStack itemSubstract10 = mainInv.getItem(24);
			ItemStack itemSubstract64 = mainInv.getItem(23);
			if(itemClicked.getItemMeta().getDisplayName() == itemAdd1.getItemMeta().getDisplayName()) {
				if(mainInv.getItem(13).getAmount() > 64) { e.setCancelled(true); return; }
				mainInv.getItem(13).setAmount(mainInv.getItem(13).getAmount() + 1);
				e.setCancelled(true);
			}else if(itemClicked.getItemMeta().getDisplayName() == itemAdd10.getItemMeta().getDisplayName()) {
				if(mainInv.getItem(13).getAmount() > 64) { e.setCancelled(true); return; }
				mainInv.getItem(13).setAmount(mainInv.getItem(13).getAmount() + 10);
				e.setCancelled(true);
			}else if(itemClicked.getItemMeta().getDisplayName() == itemAdd64.getItemMeta().getDisplayName()) {
				if(mainInv.getItem(13).getAmount() > 64) { e.setCancelled(true); return; }
				mainInv.getItem(13).setAmount(mainInv.getItem(13).getAmount() + 64);
				e.setCancelled(true);
			}else if(itemClicked.getItemMeta().getDisplayName() == itemSubstract1.getItemMeta().getDisplayName()) {
				if(mainInv.getItem(13).getAmount() <= 1) { e.setCancelled(true); return; }
				mainInv.getItem(13).setAmount(mainInv.getItem(13).getAmount() - 1);
				e.setCancelled(true);
			}else if(itemClicked.getItemMeta().getDisplayName() == itemSubstract10.getItemMeta().getDisplayName()) {
				if(mainInv.getItem(13).getAmount() <= 1) { e.setCancelled(true); return; }
				mainInv.getItem(13).setAmount(mainInv.getItem(13).getAmount() - 10);
				e.setCancelled(true);
			}else if(itemClicked.getItemMeta().getDisplayName() == itemSubstract64.getItemMeta().getDisplayName()) {
				if(mainInv.getItem(13).getAmount() <= 1) { e.setCancelled(true); return; }
				mainInv.getItem(13).setAmount(mainInv.getItem(13).getAmount() - 64);
				e.setCancelled(true);
			}
			e.setCancelled(true);
			return;
		}
	}
	
	@EventHandler
	public void onConfirmSellBuyInterract(InventoryClickEvent e) {
		Inventory mainInv = e.getInventory();
		Player player = (Player) e.getWhoClicked();
		
		if(mainInv == null) return;
		
		if(e.getInventory().getName() == Shop.shopInvSellBuy.getName()) {
			MoneyManager manager = new MoneyManager(player.getUniqueId().toString());
			ItemStack itemClicked = e.getCurrentItem();
			if(itemClicked.getItemMeta().getDisplayName() == Shop.buy.getItemMeta().getDisplayName()) {
				ItemStack itemToGive = ItemBuilder.createItem(mainInv.getItem(13).getType(), mainInv.getItem(13).getAmount());
				if (manager.moneyBankExist(player.getUniqueId().toString()) == false) {e.setCancelled(true); return;}
				for(PrisesList item : PrisesList.values()) {
					if(itemToGive.getType() == item.getMaterial()) {
						manager.substractMoney(player.getUniqueId().toString(), item.getBuyPrise());
						player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 50);
						player.sendMessage("§eVous avez acheté §a" + itemToGive.getAmount() + " §a" + item.getName() + "§e pour §a" + item.getBuyPrise() + "§e $.");
					}
				}
				player.getInventory().addItem(itemToGive);
				e.setCancelled(true);
				return;
			}else if(itemClicked.getItemMeta().getDisplayName() == Shop.sell.getItemMeta().getDisplayName()) {
				ItemStack itemToGive = ItemBuilder.createItem(mainInv.getItem(13).getType(), mainInv.getItem(13).getAmount());
				for(PrisesList item : PrisesList.values()) {
					if(itemToGive.getType() == item.getMaterial()) {
						if(Shop.unsellableItems.containsValue(item)) {
							player.sendMessage(MainCore.prefix + "§aL'item §e\"" + item.getName() + "\"§a n'est pas vendable.");
							e.setCancelled(true);
							return;
						}
					}
				}
				if (manager.moneyBankExist(player.getUniqueId().toString()) == false) {e.setCancelled(true); return;}
				for(PrisesList item : PrisesList.values()) {
					if(itemToGive.getType() == item.getMaterial()) {
						manager.addMoney(player.getUniqueId().toString(), item.getSellPrise());
						player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 50);
						player.sendMessage("§eVous avez vendu §a" + itemToGive.getAmount() + " §a" + item.getName() + "§e pour §a" + item.getBuyPrise() + "§e $.");
					}
				}
				player.getInventory().removeItem(itemToGive);
				e.setCancelled(true);
				return;
			}
		}
	}
}