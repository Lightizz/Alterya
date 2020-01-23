package fr.alterya.core.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.alterya.core.MainCore;
import fr.alterya.core.command.CmdMarket;
import fr.alterya.core.market.MarketManager;
import fr.alterya.core.util.FileWriter;
import fr.alterya.core.util.ItemBuilder;
import net.md_5.bungee.api.ChatColor;

public class MarketListener implements Listener
{
	public static int lastID = 0;
	
	CmdMarket cmdM;
	
	public MarketListener() {
		lastID = MarketManager.fw2.getInt("last_id");
		MarketManager.fw2.saveConfig();
	}
	
	@EventHandler
	public void onMarketInterract(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if(e.getInventory().getName().equalsIgnoreCase("§0Marché noir") && e.getInventory() == player.getOpenInventory().getTopInventory()) {
			if(e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getItemMeta().getDisplayName().equals(" ")) {
				e.setCancelled(true);
				return;
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Page précédente")) {
				e.setCancelled(true);
				//Go to previous page
				return;
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Déposer un item") && e.getCursor().getType() != Material.AIR && e.getCurrentItem().getType() == Material.HOPPER) {
				e.setCancelled(true);
				ItemStack cursor = e.getCursor();
				ItemMeta cim = cursor.getItemMeta();
				cursor.setItemMeta(cim);
				Inventory addItemInv = Bukkit.createInventory(null, 3*9, "§0Déposer un item");
				addItemInv.setItem(1, ItemBuilder.createItemB("+1000$", Material.WOOL, 5));
				addItemInv.setItem(2, ItemBuilder.createItemB("+10$", Material.WOOL, 5));
				addItemInv.setItem(3, ItemBuilder.createItemB("+1$", Material.WOOL, 5));
				
				addItemInv.setItem(4, ItemBuilder.createItem(" ", Material.GOLD_INGOT));
				
				addItemInv.setItem(5, ItemBuilder.createItemB("-1$", Material.WOOL, 14));
				addItemInv.setItem(6, ItemBuilder.createItemB("-10$", Material.WOOL, 14));
				addItemInv.setItem(7, ItemBuilder.createItemB("-1000$", Material.WOOL, 14));
				
				addItemInv.setItem(10, ItemBuilder.createItemB("+64", Material.WOOL, 5));
				addItemInv.setItem(11, ItemBuilder.createItemB("+10", Material.WOOL, 5));
				addItemInv.setItem(12, ItemBuilder.createItemB("+1", Material.WOOL, 5));
				
				addItemInv.setItem(13, cursor);
				
				addItemInv.setItem(14, ItemBuilder.createItemB("-1", Material.WOOL, 14));
				addItemInv.setItem(15, ItemBuilder.createItemB("-10", Material.WOOL, 14));
				addItemInv.setItem(16, ItemBuilder.createItemB("-64", Material.WOOL, 14));
				
				addItemInv.setItem(18, ItemBuilder.createItem("Infos", Material.BED));
				
				addItemInv.setItem(25, ItemBuilder.createItem("§4§lAnnuler", Material.ARROW));
				addItemInv.setItem(26, ItemBuilder.createItem("§aConfirmer", Material.HOPPER));
				player.closeInventory();
				player.openInventory(addItemInv);
				return;
			}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Page suivante")) {
				e.setCancelled(true);
				//Go to next page
				return;
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Quitter")) {
				e.setCancelled(true);
				player.closeInventory();
				return;
			}else {
				e.setCancelled(true);
				//buy item
				return;
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDepositItem(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		FileWriter fw = MarketManager.fw;
		if(e.getInventory().getName().contains("Déposer un item") && e.getInventory() == player.getOpenInventory().getTopInventory()) {
			e.setCancelled(true);
			ItemStack infos = e.getInventory().getItem(18);
			ItemStack iPrize = e.getInventory().getItem(4);
			ItemMeta pim = iPrize.getItemMeta();
			if(pim.getDisplayName() == " ") {
				pim.setDisplayName("" + 10);
			}
			ItemStack iToDeposit = e.getInventory().getItem(13); 
			ItemMeta cim = iToDeposit.getItemMeta();
			iToDeposit.setItemMeta(cim);
			iPrize.setItemMeta(pim);
			if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Annuler")) {
				player.closeInventory();
				Inventory marketInv = Bukkit.createInventory(null, 6*9, "§0Marché noir");
				player.openInventory(marketInv);
				MarketManager.prepareMarket(marketInv);
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Confirmer")) {
				if(! player.getInventory().contains(iToDeposit)) {
					player.sendMessage(MainCore.prefix + ChatColor.AQUA + "Vous n'avez pas la quantité d'item demandée.");
					return;
				}
				
				fw.config.createSection(String.valueOf(lastID));
				fw.setValue(String.valueOf(lastID) + ".type", iToDeposit.getType().getId());
				fw.setValue(String.valueOf(lastID) + ".amount", iToDeposit.getAmount());
				fw.config.createSection(String.valueOf(lastID) + ".enchantments");
				
				Set<Enchantment> es = iToDeposit.getEnchantments().keySet();
				List<Integer> esi = new ArrayList<>();
				
				for(Enchantment e1 : es) { esi.add(e1.getId()); }
				
				fw.setValue(String.valueOf(lastID) + ".enchantments", esi);
				
				Collection<Integer> enchantmentsLvl_ = iToDeposit.getEnchantments().values();
				List<Integer> enchantmentsLvl = new ArrayList<>();
				
				for(int i : enchantmentsLvl_) { enchantmentsLvl.add(i); }
				
				fw.setValue(String.valueOf(lastID) + ".enchantmentsLvl", enchantmentsLvl);
				fw.setValue(String.valueOf(lastID) + ".seller", player.getUniqueId().toString());
				fw.setValue(String.valueOf(lastID) + ".prize", Double.valueOf(pim.getDisplayName()));
				fw.saveConfig();
				
				lastID ++;
				MarketManager.fw2.setValue("last_id", lastID);
				lastID = MarketManager.fw2.getInt("last_id");
				MarketManager.fw2.saveConfig();
				
				player.closeInventory();
				Inventory marketInv = Bukkit.createInventory(null, 6*9, "§0Marché noir");
				player.openInventory(marketInv);
				MarketManager.prepareMarket(marketInv);
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("+64")) {
				iToDeposit.setAmount(64);
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("+10")) {
				iToDeposit.setAmount(iToDeposit.getAmount() + 10);
				if(iToDeposit.getAmount() > 64) {
					iToDeposit.setAmount(64);
				}
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("+1")) {
				iToDeposit.setAmount(iToDeposit.getAmount() + 1);
				if(iToDeposit.getAmount() > 64) {
					iToDeposit.setAmount(64);
				}
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("-64")) {
				iToDeposit.setAmount(1);
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("-10")) {
				iToDeposit.setAmount(iToDeposit.getAmount() - 10);
				if(iToDeposit.getAmount() < 1) {
					iToDeposit.setAmount(1);
				}
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("-1")) {
				iToDeposit.setAmount(iToDeposit.getAmount() - 1);
				if(iToDeposit.getAmount() < 1) {
					iToDeposit.setAmount(1);
				}
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("+1000$")) {
				int i = Integer.valueOf(pim.getDisplayName());
				i = i + 1000;
				pim.setDisplayName("" + i);
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("+10$")) {
				int i = Integer.valueOf(pim.getDisplayName());
				i = i + 10;
				pim.setDisplayName("" + i);
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("+1$")) {
				int i = Integer.valueOf(pim.getDisplayName());
				i = i + 1;
				pim.setDisplayName("" + i);
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("-1000$")) {
				int i = Integer.valueOf(pim.getDisplayName());
				i = i - 1000;
				if(i < 1) {
					pim.setDisplayName("" + 1);
				}
				pim.setDisplayName("" + i);
				if(i < 1) {
					pim.setDisplayName("" + 1);
				}
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("-10$")) {
				int i = Integer.valueOf(pim.getDisplayName());
				i = i - 10;
				if(i < 1) {
					pim.setDisplayName("" + 1);
				}
				pim.setDisplayName("" + i);
				if(i < 1) {
					pim.setDisplayName("" + 1);
				}
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("-1$")) {
				int i = Integer.valueOf(pim.getDisplayName());
				i = i - 1;
				if(i < 1) {
					pim.setDisplayName("" + 1);
				}
				pim.setDisplayName("" + i);
				if(i < 1) {
					pim.setDisplayName("" + 1);
				}
			}
			ItemMeta ii = infos.getItemMeta();
			ii.setDisplayName("Infos :");
			ii.setLore(Arrays.asList("Prix : " + pim.getDisplayName() + "$", "Nombre d'item : " + iToDeposit.getAmount()));
			infos.setItemMeta(ii);
			player.updateInventory();
			iPrize.setItemMeta(pim);
			iToDeposit.setItemMeta(cim);
		}
	}
}
