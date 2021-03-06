package fr.alterya.core.listeners;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.alterya.core.LogType;
import fr.alterya.core.MainCore;
import fr.alterya.core.money.MoneyManager;
import fr.alterya.core.shop.Shop;
import fr.alterya.core.shop.ShopItems;
import fr.alterya.core.util.ItemBuilder;

public class ShopListener implements Listener 
{	
	ShopItems prisesList;
	Shop shop;
	
	//Partie qui g�re l'objet de retour
	@EventHandler
	public void onDoorInterract(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if(e.getInventory() == Shop.shopInvAlchemy 
				|| e.getInventory() == Shop.shopInvUtils
				|| e.getInventory() == Shop.shopInvBlocks
				|| e.getInventory() == Shop.shopInvFarming
				|| e.getInventory() == Shop.shopInvMain
				|| e.getInventory() == Shop.shopInvMinerals
				|| e.getInventory() == Shop.shopInvPillage
				|| e.getInventory() == Shop.shopInvSellBuy) {
			if(e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem() == null) { e.setCancelled(true); return; }
			if(e.getCurrentItem().getType() == Material.ANVIL) {
				e.setCancelled(true);
				player.getOpenInventory().close();
			}
		}
	}
	
	//Partie contr�lant l'interface principale du shop
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onShopInterractMain(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory mainInv = e.getInventory();
		
		if(mainInv == null) return;
		
		//Interraction avec l'inventaire principal
		if(e.getInventory().getName() == Shop.shopInvMain.getName()) {
			ItemStack itemClicked = e.getCurrentItem();
			if(e.getCurrentItem().getType() == Material.AIR) { e.setCancelled(true); return; }
			if(itemClicked.getItemMeta().getDisplayName() == Shop.utils.getItemMeta().getDisplayName()) {
				e.setCancelled(true);
				player.getOpenInventory().close();
				player.openInventory(Shop.shopInvUtils);
				player.updateInventory();
				return;
			}else if(itemClicked.getItemMeta().getDisplayName() == Shop.alchemy.getItemMeta().getDisplayName()) {
				e.setCancelled(true);
				player.getOpenInventory().close();
				player.openInventory(Shop.shopInvAlchemy);
				player.updateInventory();
				return;
			}else if(itemClicked.getItemMeta().getDisplayName() == Shop.blocks.getItemMeta().getDisplayName()) {
				e.setCancelled(true);
				player.getOpenInventory().close();
				player.openInventory(Shop.shopInvBlocks);
				player.updateInventory();
				return;
			}else if(itemClicked.getItemMeta().getDisplayName() == Shop.pillage.getItemMeta().getDisplayName()) {
				e.setCancelled(true);
				player.getOpenInventory().close();
				player.openInventory(Shop.shopInvPillage);
				player.updateInventory();
				return;
			}else if(itemClicked.getItemMeta().getDisplayName() == Shop.farming.getItemMeta().getDisplayName()) {
				e.setCancelled(true);
				player.getOpenInventory().close();
				player.openInventory(Shop.shopInvFarming);
				player.updateInventory();
				return;
			}else if(itemClicked.getItemMeta().getDisplayName() == Shop.minerals.getItemMeta().getDisplayName()) {
				e.setCancelled(true);
				player.getOpenInventory().close();
				player.openInventory(Shop.shopInvMinerals);
				player.updateInventory();
				return;
			}
			if(e.getCurrentItem().getType() == Material.ANVIL) {
				e.setCancelled(true);
				player.getOpenInventory().close();
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
			
			if(e.getCurrentItem().getType() == Material.ANVIL) {
				e.setCancelled(true);
				player.getOpenInventory().close();
			}
			
			ItemStack itemClicked = e.getCurrentItem();
			ItemMeta itemM = itemClicked.getItemMeta();
			//Pour chaque item dans le shop regarde s'il coorespond � l'item cliqu�, si oui il lui donne le nom en fran�ais
			for(ShopItems item : ShopItems.values()) {
				if(itemClicked.getType() == item.getMaterial()) {
					itemM.setDisplayName(item.getName());
				}
			}
			itemClicked.setItemMeta(itemM);
			//Positionne les item dans l'inventaire pour confirmer vos achats / ventes
			Shop.setItemInConfirmInv();
			if(itemClicked.getType() == Material.AIR || itemClicked.getType() == Material.ANVIL) {return;}
			Shop.shopInvSellBuy.setItem(13, itemClicked);
			e.setCancelled(true);
			//Ferme et ouvre l'inventaire concerner pour �viter les bugs
			player.getOpenInventory().close();
			player.openInventory(Shop.shopInvSellBuy);
		}
	}

	//Partie concerant l'inventaire de confirmation d'achat / vente
	@EventHandler
	public void onSelectAmountPartInterract(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory mainInv = e.getInventory();
		
		if(mainInv == null) return;
		
		if(e.getInventory().getName() == Shop.shopInvSellBuy.getName()) {
			
			if(e.getCurrentItem().getType() == Material.ANVIL) {
				e.setCancelled(true);
				player.getOpenInventory().close();
			}
			
			ItemStack itemClicked = e.getCurrentItem();
			ItemStack itemAdd1 = mainInv.getItem(19);
			ItemStack itemAdd10 = mainInv.getItem(20);
			ItemStack itemAdd64 = mainInv.getItem(21);
			ItemStack itemSubstract1 = mainInv.getItem(25);
			ItemStack itemSubstract10 = mainInv.getItem(24);
			ItemStack itemSubstract64 = mainInv.getItem(23);
			if(itemClicked.getType() == Material.AIR) { e.setCancelled(true); return; }
			//Toutes les conditions qui vont avec les items Add1, Add10, etc...
			if(itemClicked.getItemMeta().getDisplayName() == itemAdd1.getItemMeta().getDisplayName()) {
				if(mainInv.getItem(13).getAmount() > 64) { e.setCancelled(true); mainInv.getItem(13).setAmount(64); return; }
				mainInv.getItem(13).setAmount(mainInv.getItem(13).getAmount() + 1);
				e.setCancelled(true);
				return;
			}else if(itemClicked.getItemMeta().getDisplayName() == itemAdd10.getItemMeta().getDisplayName()) {
				if(mainInv.getItem(13).getAmount() > 64) { e.setCancelled(true); mainInv.getItem(13).setAmount(64); return; }
				mainInv.getItem(13).setAmount(mainInv.getItem(13).getAmount() + 10);
				e.setCancelled(true);
				return;
			}else if(itemClicked.getItemMeta().getDisplayName() == itemAdd64.getItemMeta().getDisplayName()) {
				if(mainInv.getItem(13).getAmount() > 64) { e.setCancelled(true); mainInv.getItem(13).setAmount(64); return; }
				mainInv.getItem(13).setAmount(mainInv.getItem(13).getAmount() + 64);
				e.setCancelled(true);
				return;
			}else if(itemClicked.getItemMeta().getDisplayName() == itemSubstract1.getItemMeta().getDisplayName()) {
				if(mainInv.getItem(13).getAmount() <= 1) { e.setCancelled(true); mainInv.getItem(13).setAmount(64); return; }
				mainInv.getItem(13).setAmount(mainInv.getItem(13).getAmount() - 1);
				e.setCancelled(true);
				return;
			}else if(itemClicked.getItemMeta().getDisplayName() == itemSubstract10.getItemMeta().getDisplayName()) {
				if(mainInv.getItem(13).getAmount() <= 1) { e.setCancelled(true); mainInv.getItem(13).setAmount(64); return; }
				mainInv.getItem(13).setAmount(mainInv.getItem(13).getAmount() - 10);
				e.setCancelled(true);
				return;
			}else if(itemClicked.getItemMeta().getDisplayName() == itemSubstract64.getItemMeta().getDisplayName()) {
				if(mainInv.getItem(13).getAmount() <= 1) { e.setCancelled(true); mainInv.getItem(13).setAmount(64); return; }
				mainInv.getItem(13).setAmount(mainInv.getItem(13).getAmount() - 64);
				e.setCancelled(true);
				return;
			}
			e.setCancelled(true);
			return;
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onConfirmSellBuyInterract(InventoryClickEvent e) {
		Inventory mainInv = e.getInventory();
		Player player = (Player) e.getWhoClicked();
		
		if(mainInv == null) return;
		
		if(e.getInventory().getName() == Shop.shopInvSellBuy.getName()) {
			
			if(e.getCurrentItem().getType() == Material.ANVIL) {
				e.setCancelled(true);
				player.getOpenInventory().close();
			}
			
			MoneyManager manager = new MoneyManager(player.getUniqueId().toString());
			ItemStack itemClicked = e.getCurrentItem();
			for(ShopItems item : ShopItems.values()) {
				if(item.getMaterial() == mainInv.getItem(13).getType()) {
					String buyPrise = String.valueOf(item.getBuyPrise() * mainInv.getItem(13).getAmount());
					String sellPrise = String.valueOf(item.getSellPrise() * mainInv.getItem(13).getAmount());
					mainInv.setItem(4*9, ItemBuilder.createItem(Material.BED, "�dPrix s�l�ctionn�s :", Arrays.asList("�aPrix d'achat actuel : " + buyPrise, "�aPrix de vente actuel : " + sellPrise)));
					player.updateInventory();
				}
			}
			if(itemClicked.getType() == Material.AIR) { e.setCancelled(true); return; }
			if(itemClicked.getItemMeta().getDisplayName() == Shop.buy.getItemMeta().getDisplayName()) {
				ItemStack itemToGive = ItemBuilder.createItem(mainInv.getItem(13).getType(), mainInv.getItem(13).getAmount());
				if (manager.moneyBankExist(player.getUniqueId().toString()) == false) {e.setCancelled(true); return;}
				for(ShopItems item : ShopItems.values()) {
					if(itemToGive.getType() == item.getMaterial()) {
						//Retire la money
						if (manager.getMoney(player.getUniqueId().toString()) < item.getBuyPrise() * itemToGive.getAmount()) {
							player.sendMessage(MainCore.prefix + "�eVous devez avoir �a" + item.getBuyPrise() * itemToGive.getAmount() + " �a$ �epour acheter �a" + itemToGive.getAmount() + " " + item.getName() + ".");
							player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 50);
							return;
						}
						manager.substractMoney(player.getUniqueId().toString(), item.getBuyPrise() * itemToGive.getAmount());
						//Joue un son
						player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 50);
						//Envoie le message de confirmation
						player.sendMessage("�eVous avez achet� �a" + itemToGive.getAmount() + " �a" + item.getName() + "�e pour �a" + item.getBuyPrise() * itemToGive.getAmount() + "�e $.");
						MainCore.log(LogType.INFO, "Le joueur " + player.getDisplayName() + " a achet� " + itemToGive.getAmount() + " " + item.getName() + " pour " + item.getBuyPrise() * itemToGive.getAmount() + "$.");
					}
				}
				//Ajoute l'item
				player.getInventory().addItem(itemToGive);
				e.setCancelled(true);
				return;
			}else if(itemClicked.getItemMeta().getDisplayName() == Shop.sell.getItemMeta().getDisplayName()) {
				ItemStack itemToSell = ItemBuilder.createItem(mainInv.getItem(13).getType(), mainInv.getItem(13).getAmount());
				for(ShopItems item : ShopItems.values()) {
					//Voir si l'item choisi est vendable ou pas
					if(itemToSell.getType() == item.getMaterial()) {
						if(Shop.unsellableItems.containsValue(item)) {
							player.sendMessage(MainCore.prefix + "�aL'item �e\"" + item.getName() + "\"�a n'est pas vendable.");
							player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 50);
							e.setCancelled(true);
							return;
						}
					}
				}
				if(! player.getInventory().contains(itemToSell)) {
					player.sendMessage(MainCore.prefix + "�eVous devez avoir �a" + itemToSell.getAmount() + " �a" + itemToSell.getType().name() + " �edans votre inventaire.");
					player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 50);
					return;
				}
				if (manager.moneyBankExist(player.getUniqueId().toString()) == false) {e.setCancelled(true); return;}
				for(ShopItems item : ShopItems.values()) {
					if(itemToSell.getType() == item.getMaterial()) {
						//Ajoute la money
						manager.addMoney(player.getUniqueId().toString(), item.getSellPrise() * itemToSell.getAmount());
						//Joue un son
						player.playSound(player.getLocation(), Sound.ORB_PICKUP, 50, 50);
						//Envoie le message de confirmation
						player.sendMessage("�eVous avez vendu �a" + itemToSell.getAmount() + " �a" + item.getName() + "�e pour �a" + item.getBuyPrise() * itemToSell.getAmount() + "�e $.");
						MainCore.log(LogType.INFO, "Le joueur " + player.getDisplayName() + " a vendu " + itemToSell.getAmount() + " " + item.getName() + " et il a re�u " + item.getSellPrise() * itemToSell.getAmount() + "$.");
					}
				}
				//Enl�ve l'item de l'inventaire du joueur
				player.getInventory().removeItem(itemToSell);
				e.setCancelled(true);
				return;
			}
		}
	}
}