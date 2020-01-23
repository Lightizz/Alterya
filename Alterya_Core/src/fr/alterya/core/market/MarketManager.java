package fr.alterya.core.market;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.alterya.core.MainCore;
import fr.alterya.core.util.FileWriter;
import fr.alterya.core.util.ItemBuilder;

public class MarketManager
{
	public MainCore m;
	
	public static FileWriter fw = new FileWriter("ServerData/Market", "Items");
	public static FileWriter fw2 = new FileWriter("ServerData/Market", "Infos");
	
	public MarketManager(MainCore main) {
		m = main;
	}
	
	private static void initMarketInv(Inventory marketInv) {
		ItemStack glass = ItemBuilder.createItem(" ", Material.STAINED_GLASS_PANE);
		marketInv.setItem(0, glass);
		marketInv.setItem(1, glass);
		marketInv.setItem(2, glass);
		marketInv.setItem(3, ItemBuilder.createItemB("§4Page précédente", Material.WOOL, 14));
		marketInv.setItem(4, ItemBuilder.createItemB("§aPage suivante", Material.WOOL, 5));
		marketInv.setItem(5, glass);
		marketInv.setItem(6, ItemBuilder.createItem("§dDéposer un item", Material.HOPPER));
		marketInv.setItem(7, glass);
		marketInv.setItem(8, ItemBuilder.createItem("§4§lQuitter", Material.ANVIL));
	}
	
	public static void prepareMarket(Inventory marketInv) {
		initMarketInv(marketInv);
		HashMap<Integer, ItemStack> items = new HashMap<>();
		
		for(String s : fw.getKeys(false)) {
			if(fw.getList(s + ".enchantments").isEmpty() == true || fw.getList(s + ".enchantmentsLvl").isEmpty() == true) {
				ItemStack i = ItemBuilder.createItem(Material.getMaterial(s + ".type")
						, fw.getInt(s + ".amount"));
				items.put(fw.getInt(s), i);
			} else {
				Map<Enchantment, Integer> em = new HashMap<>();
				@SuppressWarnings("deprecation")
				Enchantment e = Enchantment.getById(fw.getInt(s + ".enchantments"));
				em.put(e, fw.getInt(s + "enchantmentsLvl"));
				ItemStack i = ItemBuilder.createItem(Material.getMaterial(s + ".type")
													, fw.getInt(s + ".amount")
													, em);
				items.put(fw.getInt(s), i);
			}
		}
		
		for (Integer i : items.keySet()) {
			ItemStack item = items.get(i);
			marketInv.setItem(i, item);
		}
	}
}
