package fr.alterya.core.shop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.alterya.core.util.ItemBuilder;

/*
Author : Lightiz
*/

public class Shop implements Listener {
	
	ShopItems pl_;

	public HashMap<Integer, ShopItems> ShopItemsList = new HashMap<>();
	
	public static HashMap<Integer, ShopItems> unsellableItems = new HashMap<>();
	
	public HashMap<Integer, ShopItems> pillageList = new HashMap<>();
	public HashMap<Integer, ShopItems> oresList = new HashMap<>();
	public HashMap<Integer, ShopItems> othersList = new HashMap<>();
	public HashMap<Integer, ShopItems> alchemyList = new HashMap<>();
	public HashMap<Integer, ShopItems> blocksList = new HashMap<>();
	public HashMap<Integer, ShopItems> farmingList = new HashMap<>();
	
	public Shop (){
		//Place les item dans leurs listes respectives
		setAlchemyItems();
		setBlocksItems();
		setFarmingItems();
		setOresItems();
		setOthersItems();
		setPillageItems();
		setUnsellableItems();
		setShopItems();
		
		//Placer tout les items dans leurs inventaires pour préparer le shop
		setItemsInShopMain();
		setItemInShopBlocks();
		setItemInShopFarming();
		setItemInShopUtils();
		setItemInShopMinerals();
		setItemInShopAlchemy();
		setItemInShopPillage();
	}
	
	//Créer les listes d'items
	static List<String> lores = Arrays.asList("Ici, vous trouverez des items pour faire de l'achimie !", "Cliquez pour plus d'informations !");
	public static ItemStack alchemy = ItemBuilder.createItem(Material.BLAZE_POWDER, "§1Items dédiés à l'alchimie", lores);
	
	static List<String> lores0 = Arrays.asList("Ici, vous trouverez des blocks de toutes sortes d'items / de loots !", "Cliquez pour plus d'informations !");
	public static ItemStack farming = ItemBuilder.createItem(Material.DIAMOND_HOE, "§1Plantes et végétations", lores0);
	
	static List<String> lores1 = Arrays.asList("Ici, vous trouverez tout les minerais !", "Cliquez pour plus d'informations !");
	public static ItemStack minerals = ItemBuilder.createItem(Material.IRON_INGOT, "§1Minerais", lores1);
	
	static List<String> lores2 = Arrays.asList("Ici, vous trouverez des blocks de toutes sortes et des blocks décoratifs !", "Cliquez pour plus d'informations !");
	public static ItemStack blocks = ItemBuilder.createItem(Material.STONE, "§1Blocks / Décorations", lores2);
	
	static List<String> lores3 = Arrays.asList("Ici, vous trouverez des items pour réaliser des pillages !", "Cliquez pour plus d'informations !");
	public static ItemStack pillage = ItemBuilder.createItem(Material.IRON_AXE, "§1Items utilisés pour les pillages", lores3);
	
	static List<String> lores4 = Arrays.asList("Ici, vous trouverez des items de toutes sortes confondues mais qui ne sont ni des blocks ni des plantes ni des minéraux, ni autre !", "Cliquez pour plus d'informations !");
	public static ItemStack utils = ItemBuilder.createItem(Material.EMPTY_MAP, "§1Items sans catégorie attribuée", lores4);
	
	public static ItemStack sell = ItemBuilder.createItem("§1Vendre", Material.ARROW);
	
	static List<String> lores6 = Arrays.asList("§4§lRetour / Fermer l'inventaire");
	public static ItemStack backDoor = ItemBuilder.createItem("§4§lQuitter", Material.ANVIL);
	
	public static ItemStack buy = ItemBuilder.createItem("§1Acheter", Material.BEACON);
	
	public static ItemStack add1 = ItemBuilder.createItem("§lAjouter 1", Material.RED_MUSHROOM);
	public static ItemStack add10 = ItemBuilder.createItem("§lAjouter 10", Material.RED_MUSHROOM);
	public static ItemStack add64 = ItemBuilder.createItem("§lAjouter 64", Material.RED_MUSHROOM);
	public static ItemStack substract1 = ItemBuilder.createItem("§lRetirer 1", Material.BROWN_MUSHROOM);
	public static ItemStack substract10 = ItemBuilder.createItem("§lRetirer 10", Material.BROWN_MUSHROOM);
	public static ItemStack substract64 = ItemBuilder.createItem("§lRetirer 64", Material.BROWN_MUSHROOM);
	
	public static ItemStack witherSkull = new ItemStack(Material.SKULL, (byte) 397);
	public static ItemStack redSand = new ItemStack(Material.SAND, (byte) 12);
	
	public void setUnsellableItems() {
		//Minerais
//		unsellableItems.put(ShopItems.EMERALD.getID(), ShopItems.EMERALD);
		unsellableItems.put(ShopItems.REDSTONE.getID(), ShopItems.REDSTONE);
		unsellableItems.put(ShopItems.DIAMOND.getID(), ShopItems.DIAMOND);
		unsellableItems.put(ShopItems.QUARTZ.getID(), ShopItems.QUARTZ);
		
		//Farming
		unsellableItems.put(ShopItems.MELON_SEEDS.getID(), ShopItems.MELON_SEEDS);
		unsellableItems.put(ShopItems.PUMPKIN_SEEDS.getID(), ShopItems.PUMPKIN_SEEDS);
		unsellableItems.put(ShopItems.SEEDS.getID(), ShopItems.SEEDS);
		unsellableItems.put(ShopItems.CARROT.getID(), ShopItems.CARROT);
		unsellableItems.put(ShopItems.POTATO.getID(), ShopItems.POTATO);
		unsellableItems.put(ShopItems.COCOA.getID(), ShopItems.COCOA);
		unsellableItems.put(ShopItems.CACTUS.getID(), ShopItems.CACTUS);
		//unsellableItems.put(ShopItems.VINE.getID(), ShopItems.VINE);
		unsellableItems.put(ShopItems.SUGAR_CANE.getID(), ShopItems.SUGAR_CANE);
		
		//Loots
//		unsellableItems.put(ShopItems.EMPTY_BOTTLE.getID(), ShopItems.EMPTY_BOTTLE);
//		unsellableItems.put(ShopItems.BREWING_STAND.getID(), ShopItems.BREWING_STAND);
//		unsellableItems.put(ShopItems.GOLDEN_MELON.getID(), ShopItems.GOLDEN_MELON);
//		unsellableItems.put(ShopItems.GOLDEN_CARROT.getID(), ShopItems.GOLDEN_CARROT);
		unsellableItems.put(ShopItems.BROWN_MUSHROOM.getID(), ShopItems.BROWN_MUSHROOM);
		unsellableItems.put(ShopItems.RED_MUSHROOM.getID(), ShopItems.RED_MUSHROOM);
		unsellableItems.put(ShopItems.NETHER_WARTS.getID(), ShopItems.NETHER_WARTS);
//		unsellableItems.put(ShopItems.MILK_BUCKET.getID(), ShopItems.MILK_BUCKET);
		
		//Pillage
//		unsellableItems.put(ShopItems.SOUL_SAND.getID(), ShopItems.SOUL_SAND);
		unsellableItems.put(ShopItems.WITHER_SKULL.getID(), ShopItems.WITHER_SKULL);
		unsellableItems.put(ShopItems.ENDER_PEARL.getID(), ShopItems.ENDER_PEARL);
		
		//Divers
		//unsellableItems.put(ShopItems.WOOL.getID(), ShopItems.WOOL);
		//unsellableItems.put(ShopItems.INK_SACK.getID(), ShopItems.INK_SACK);
		unsellableItems.put(ShopItems.COOKED_STEAK.getID(), ShopItems.COOKED_STEAK);
		unsellableItems.put(ShopItems.COOKED_CHIKEN.getID(), ShopItems.COOKED_CHIKEN);
		unsellableItems.put(ShopItems.COOKED_PORCKCHOP.getID(), ShopItems.COOKED_PORCKCHOP);
		
		//Blocks
		//unsellableItems.put(ShopItems.GRASS.getID(), ShopItems.GRASS);
		unsellableItems.put(ShopItems.DIRT.getID(), ShopItems.DIRT);
		unsellableItems.put(ShopItems.OBSIDIAN.getID(), ShopItems.SEEDS);
		unsellableItems.put(ShopItems.COBBLESTONE.getID(), ShopItems.SEEDS);
		unsellableItems.put(ShopItems.STONE.getID(), ShopItems.SEEDS);
		unsellableItems.put(ShopItems.RED_SAND.getID(), ShopItems.SEEDS);
		unsellableItems.put(ShopItems.SAND.getID(), ShopItems.SEEDS);
		//unsellableItems.put(ShopItems.PACKED_ICE.getID(), ShopItems.PACKED_ICE);
		//unsellableItems.put(ShopItems.ICE.getID(), ShopItems.ICE);
		unsellableItems.put(ShopItems.GRAVEL.getID(), ShopItems.GRAVEL);
	}
	public void setPillageItems() {
		pillageList.put(ShopItems.ENDER_PEARL.getID(), ShopItems.ENDER_PEARL);
		pillageList.put(ShopItems.WITHER_SKULL.getID(), ShopItems.WITHER_SKULL);
//		pillageList.put(ShopItems.SOUL_SAND.getID(), ShopItems.SOUL_SAND);
	}
	public void setOresItems() {
//		oresList.put(ShopItems.EMERALD.getID(), ShopItems.EMERALD);
		oresList.put(ShopItems.DIAMOND.getID(), ShopItems.DIAMOND);
		oresList.put(ShopItems.QUARTZ.getID(), ShopItems.QUARTZ);
		oresList.put(ShopItems.GOLD.getID(), ShopItems.GOLD);
		oresList.put(ShopItems.IRON.getID(), ShopItems.IRON);
		oresList.put(ShopItems.COAL.getID(), ShopItems.COAL);
		oresList.put(ShopItems.REDSTONE.getID(), ShopItems.REDSTONE);
	}
	public void setOthersItems() {
		//othersList.put(ShopItems.WOOL.getID(), ShopItems.WOOL);
		//othersList.put(ShopItems.INK_SACK.getID(), ShopItems.INK_SACK);
		othersList.put(ShopItems.COOKED_STEAK.getID(), ShopItems.COOKED_STEAK);
		othersList.put(ShopItems.COOKED_CHIKEN.getID(), ShopItems.COOKED_CHIKEN);
		othersList.put(ShopItems.COOKED_PORCKCHOP.getID(), ShopItems.COOKED_PORCKCHOP);
	}
	public void setAlchemyItems() {
//		alchemyList.put(ShopItems.EMPTY_BOTTLE.getID(), ShopItems.EMPTY_BOTTLE);
//		alchemyList.put(ShopItems.BREWING_STAND.getID(), ShopItems.BREWING_STAND);
//		alchemyList.put(ShopItems.MAGMA_CREAM.getID(), ShopItems.MAGMA_CREAM);
//		alchemyList.put(ShopItems.GOLDEN_MELON.getID(), ShopItems.GOLDEN_MELON);
//		alchemyList.put(ShopItems.GOLDEN_CARROT.getID(), ShopItems.GOLDEN_CARROT);
		alchemyList.put(ShopItems.GHAST_TEAR.getID(), ShopItems.GHAST_TEAR);
//		alchemyList.put(ShopItems.FERMENTED_SPIDER_EYE.getID(), ShopItems.FERMENTED_SPIDER_EYE);
		alchemyList.put(ShopItems.SPIDER_EYE.getID(), ShopItems.SPIDER_EYE);
		alchemyList.put(ShopItems.RED_MUSHROOM.getID(), ShopItems.RED_MUSHROOM);
		alchemyList.put(ShopItems.BROWN_MUSHROOM.getID(), ShopItems.BROWN_MUSHROOM);
		alchemyList.put(ShopItems.NETHER_WARTS.getID(), ShopItems.NETHER_WARTS);
		alchemyList.put(ShopItems.GLOWSTONE.getID(), ShopItems.GLOWSTONE);
//		alchemyList.put(ShopItems.MILK_BUCKET.getID(), ShopItems.MILK_BUCKET);
	}
	public void setBlocksItems() {
		blocksList.put(ShopItems.COBBLESTONE.getID(), ShopItems.COBBLESTONE);
		blocksList.put(ShopItems.GRAVEL.getID(), ShopItems.GRAVEL);
		//blocksList.put(ShopItems.ICE.getID(), ShopItems.ICE);
		//blocksList.put(ShopItems.PACKED_ICE.getID(), ShopItems.PACKED_ICE);
		//blocksList.put(ShopItems.GRASS.getID(), ShopItems.GRASS);
		blocksList.put(ShopItems.SAND.getID(), ShopItems.SAND);
		blocksList.put(ShopItems.RED_SAND.getID(), ShopItems.RED_SAND);
		blocksList.put(ShopItems.WOOD.getID(), ShopItems.WOOD);
		blocksList.put(ShopItems.OBSIDIAN.getID(), ShopItems.OBSIDIAN);
		blocksList.put(ShopItems.STONE.getID(), ShopItems.STONE);
	}
	public void setFarmingItems() {
		//Plantes
		farmingList.put(ShopItems.MELON_SEEDS.getID(), ShopItems.MELON_SEEDS);
		farmingList.put(ShopItems.PUMPKIN_SEEDS.getID(), ShopItems.PUMPKIN_SEEDS);
		farmingList.put(ShopItems.SEEDS.getID(), ShopItems.SEEDS);
		farmingList.put(ShopItems.CARROT.getID(), ShopItems.CARROT);
		farmingList.put(ShopItems.POTATO.getID(), ShopItems.POTATO);			 
		farmingList.put(ShopItems.SUGAR_CANE.getID(), ShopItems.SUGAR_CANE);
		farmingList.put(ShopItems.APPLE.getID(), ShopItems.APPLE);
		farmingList.put(ShopItems.CACTUS.getID(), ShopItems.CACTUS);
		farmingList.put(ShopItems.COCOA.getID(), ShopItems.COCOA);
		//farmingList.put(ShopItems.VINE.getID(), ShopItems.VINE);
		
		//Loots 
		farmingList.put(ShopItems.STRING.getID(), ShopItems.STRING);
		farmingList.put(ShopItems.BLAZE_ROD.getID(), ShopItems.BLAZE_ROD);
		farmingList.put(ShopItems.BONE.getID(), ShopItems.BONE);
		farmingList.put(ShopItems.ROTTEN_FLESH.getID(), ShopItems.ROTTEN_FLESH);
		farmingList.put(ShopItems.GUNPOWDER.getID(), ShopItems.GUNPOWDER);
		farmingList.put(ShopItems.ARROW.getID(), ShopItems.ARROW);
		farmingList.put(ShopItems.LEATHER.getID(), ShopItems.LEATHER);
		farmingList.put(ShopItems.EGG.getID(), ShopItems.EGG);
		farmingList.put(ShopItems.FEATHER.getID(), ShopItems.FEATHER);
		farmingList.put(ShopItems.SLIME_BALL.getID(), ShopItems.SLIME_BALL);
	}
	
	public void setShopItems() {
		for(ShopItems pl : ShopItems.values()) {
			this.pl_ = pl;
			ShopItemsList.put(pl.getID(), pl);
		}
	}
	
	//Créer toutes les parties du shop
	public static Inventory shopInvMain = Bukkit.createInventory(null, 2*9, "§dShop");
	
	public static Inventory shopInvBlocks = Bukkit.createInventory(null, 5*9, "§aBlocks");
	public static Inventory shopInvUtils = Bukkit.createInventory(null, 5*9, "§aDivers");
	public static Inventory shopInvFarming = Bukkit.createInventory(null, 5*9, "§aFarming");
	public static Inventory shopInvMinerals = Bukkit.createInventory(null, 5*9, "§aMinerais");
	public static Inventory shopInvPillage = Bukkit.createInventory(null, 5*9, "§aPillage");
	public static Inventory shopInvAlchemy = Bukkit.createInventory(null, 5*9, "§aAlchimie");
	
	public static Inventory shopInvSellBuy = Bukkit.createInventory(null, 5*9, "§aSell / Buy items");
	
	public static void setItemInConfirmInv() {
		shopInvSellBuy.setItem(29, buy);
		shopInvSellBuy.setItem(33, sell);
		shopInvSellBuy.setItem(40, backDoor);
		shopInvSellBuy.setItem(19, add1);
		shopInvSellBuy.setItem(20, add10);
		shopInvSellBuy.setItem(21, add64);
		shopInvSellBuy.setItem(25, substract1);
		shopInvSellBuy.setItem(24, substract10);
		shopInvSellBuy.setItem(23, substract64);
	}
	
	//Set items in main page of the shop
	public void setItemsInShopMain() {
		shopInvMain.setItem(0, blocks);
		shopInvMain.setItem(3, minerals);
		shopInvMain.setItem(5, farming);
		shopInvMain.setItem(8, utils);
		shopInvMain.setItem(11, alchemy);
		shopInvMain.setItem(13, backDoor);
		shopInvMain.setItem(15, pillage);
	}
	
	//Set items in blocks page of the shop
	public void setItemInShopBlocks() {
		for(ShopItems pl : blocksList.values()) {
			pl_ = pl;
			List<String> lores8 = Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "§2Prix de vente / u : " + pl.getSellPrise() + "$");
			ItemStack item = ItemBuilder.createItem(pl.getMaterial(), pl.getName(), lores8);
			shopInvBlocks.addItem(item);
		}
		shopInvBlocks.setItem(40, backDoor);
	}
	
	//Set items in minerals page of the shop
	public void setItemInShopMinerals() {	
		for(ShopItems pl : oresList.values()) {
			pl_ = pl;
			List<String> lores8 = Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "§2Prix de vente / u : " + pl.getSellPrise() + "$");
			ItemStack item = ItemBuilder.createItem(pl.getMaterial(), pl.getName(), lores8);
			shopInvMinerals.addItem(item);
		}
		shopInvMinerals.setItem(40, backDoor);
	}
	
	//Set items in utils page of the shop
	public void setItemInShopUtils() {
		for(ShopItems pl : othersList.values()) {
			pl_ = pl;
			List<String> lores8 = Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "§2Prix de vente / u : " + pl.getSellPrise() + "$");
			ItemStack item = ItemBuilder.createItem(pl.getMaterial(), pl.getName(), lores8);
			shopInvUtils.addItem(item);
		}
		shopInvUtils.setItem(40, backDoor);
	}
	
	//Set items in plants page of the shop
	public void setItemInShopFarming() {
		for(ShopItems pl : farmingList.values()) {
			pl_ = pl;
			List<String> lores8 = Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "§2Prix de vente / u : " + pl.getSellPrise() + "$");
			ItemStack item = ItemBuilder.createItem(pl.getMaterial(), pl.getName(), lores8);
			shopInvFarming.addItem(item);
		}
		shopInvFarming.setItem(40, backDoor);
	}

	//Set items in pillage page of the shop
	public void setItemInShopPillage() {
		for(ShopItems pl : pillageList.values()) {
			pl_ = pl;
			List<String> lores8 = Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "§2Prix de vente / u : " + pl.getSellPrise() + "$");
			ItemStack item = ItemBuilder.createItem(pl.getMaterial(), pl.getName(), lores8);
			shopInvPillage.addItem(item);
		}
		shopInvPillage.setItem(40, backDoor);
	}
	
	//Set items in alchemy page of the shop
	public void setItemInShopAlchemy() {
		for(ShopItems pl : alchemyList.values()) {
			pl_ = pl;
			List<String> lores8 = Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "§2Prix de vente / u : " + pl.getSellPrise() + "$");
			ItemStack item = ItemBuilder.createItem(pl.getMaterial(), pl.getName(), lores8);
			shopInvAlchemy.addItem(item);
		}
		shopInvAlchemy.setItem(40, backDoor);
	}	
}
