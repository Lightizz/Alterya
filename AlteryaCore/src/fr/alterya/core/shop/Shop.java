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
	
	PrisesList pl_;
	
	/*public static Dye red;
	public static Dye gray;
	public static Dye green;
	public static Dye cyan;
	public static Dye bleue;
	public static Dye light_bleue;
	public static Dye orange;
	public static Dye yellow;*/
	
	public HashMap<Integer, PrisesList> prisesList = new HashMap<>();
	
	public static HashMap<Integer, PrisesList> unsellableItems = new HashMap<>();
	
	public HashMap<Integer, PrisesList> pillageList = new HashMap<>();
	public HashMap<Integer, PrisesList> oresList = new HashMap<>();
	public HashMap<Integer, PrisesList> othersList = new HashMap<>();
	public HashMap<Integer, PrisesList> alchemyList = new HashMap<>();
	public HashMap<Integer, PrisesList> blocksList = new HashMap<>();
	public HashMap<Integer, PrisesList> farmingList = new HashMap<>();
	
	public Shop (){
		//Place les item dans leurs listes respectives
		setAlchemyItems();
		setBlocksItems();
		setFarmingItems();
		setOresItems();
		setOthersItems();
		setPillageItems();
		setUnsellableItems();
		setPrisesList();
		
		//Placer tout les items dans leurs inventaires pour préparer le shop
		setItemsInShopMain();
		setItemInShopBlocks();
		setItemInShopFarming();
		setItemInShopUtils();
		setItemInShopMinerals();
		setItemInShopAlchemy();
		setItemInShopPillage();
		
		//setDyesColors();
	}
	
	//Créer les listes d'items
	List<String> lores = Arrays.asList("Ici, vous trouverez des items pour faire de l'achimie !", "Cliquez pour plus d'informations !");
	public ItemStack alchemy = ItemBuilder.createItem(Material.BLAZE_POWDER, "§1Items dédiés à l'alchimie", (String[]) lores.toArray());
	
	List<String> lores0 = Arrays.asList("Ici, vous trouverez des blocks de toutes sortes d'items / de loots !", "Cliquez pour plus d'informations !");
	public ItemStack farming = ItemBuilder.createItem(Material.DIAMOND_HOE, "§1Plantes et végétations", (String[]) lores0.toArray());
	
	List<String> lores1 = Arrays.asList("Ici, vous trouverez tout les minerais !", "Cliquez pour plus d'informations !");
	public ItemStack minerals = ItemBuilder.createItem(Material.IRON_INGOT, "§1Minerais", (String[]) lores.toArray());
	
	List<String> lores2 = Arrays.asList("Ici, vous trouverez des blocks de toutes sortes et des blocks décoratifs !", "Cliquez pour plus d'informations !");
	public ItemStack blocks = ItemBuilder.createItem(Material.STONE, "§1Blocks / Décorations", (String[]) lores.toArray());
	
	List<String> lores3 = Arrays.asList("Ici, vous trouverez des items pour réaliser des pillages !", "Cliquez pour plus d'informations !");
	public ItemStack pillage = ItemBuilder.createItem(Material.IRON_AXE, "§1Items utilisés pour les pillages", (String[]) lores.toArray());
	
	List<String> lores4 = Arrays.asList("Ici, vous trouverez des items de toutes sortes confondues mais qui ne sont ni des blocks ni des plantes ni des minéraux, ni autre !", "Cliquez pour plus d'informations !");
	public ItemStack utils = ItemBuilder.createItem(Material.EMPTY_MAP, "§1Items sans catégorie attribuée", (String[]) lores.toArray());
	
	static List<String> lores5 = Arrays.asList("Prix de vente séléctionner : ");
	public static ItemStack sell = ItemBuilder.createItem(Material.ARROW, "§1Vendre", (String []) lores5.toArray());
	
	static List<String> lores6 = Arrays.asList("§4§lRetour / Fermer l'inventaire");
	public static ItemStack backDoor = ItemBuilder.createItem(Material.ANVIL, "§4§lQuitter", (String []) lores6.toArray());
	
	static List<String> lores7 = Arrays.asList("Prix d'achat séléctionner : ");
	public static ItemStack buy = ItemBuilder.createItem(Material.BEACON, "§1Acheter", (String []) lores7.toArray());
	
	public static ItemStack add1 = ItemBuilder.createItem(Material.RED_MUSHROOM, "§lAjouter 1");
	public static ItemStack add10 = ItemBuilder.createItem(Material.RED_MUSHROOM, "§lAjouter 10");
	public static ItemStack add64 = ItemBuilder.createItem(Material.RED_MUSHROOM, "§lAjouter 64");
	public static ItemStack substract1 = ItemBuilder.createItem(Material.BROWN_MUSHROOM, "§lRetirer 1");
	public static ItemStack substract10 = ItemBuilder.createItem(Material.BROWN_MUSHROOM, "§lRetirer 10");
	public static ItemStack substract64 = ItemBuilder.createItem(Material.BROWN_MUSHROOM, "§lRetirer 64");
	
	/*
	public void setDyesColors() {
		red.setColor(DyeColor.RED);
		bleue.setColor(DyeColor.BLUE);
		light_bleue.setColor(DyeColor.LIGHT_BLUE);
		green.setColor(DyeColor.GREEN);
		gray.setColor(DyeColor.GRAY);
		yellow.setColor(DyeColor.YELLOW);
		cyan.setColor(DyeColor.CYAN);
		orange.setColor(DyeColor.ORANGE);
	}
	*/
	
	public void setUnsellableItems() {
		//Minerais
		unsellableItems.put(PrisesList.EMERALD.getID(), PrisesList.EMERALD);
		
		//Farming
		unsellableItems.put(PrisesList.MELON_SEEDS.getID(), PrisesList.MELON_SEEDS);
		unsellableItems.put(PrisesList.SEEDS.getID(), PrisesList.SEEDS);
		unsellableItems.put(PrisesList.PUMPKIN_SEEDS.getID(), PrisesList.PUMPKIN_SEEDS);
		unsellableItems.put(PrisesList.COCOA.getID(), PrisesList.COCOA);
		unsellableItems.put(PrisesList.CACTUS.getID(), PrisesList.CACTUS);
		unsellableItems.put(PrisesList.VINE.getID(), PrisesList.VINE);
		unsellableItems.put(PrisesList.SUGAR_CANE.getID(), PrisesList.SUGAR_CANE);
		
		//Loots
		unsellableItems.put(PrisesList.EMPTY_BOTTLE.getID(), PrisesList.EMPTY_BOTTLE);
		unsellableItems.put(PrisesList.BREWING_STAND.getID(), PrisesList.BREWING_STAND);
		unsellableItems.put(PrisesList.GOLDEN_MELON.getID(), PrisesList.GOLDEN_MELON);
		unsellableItems.put(PrisesList.GOLDEN_CARROT.getID(), PrisesList.GOLDEN_CARROT);
		unsellableItems.put(PrisesList.BROWN_MUSHROOM.getID(), PrisesList.BROWN_MUSHROOM);
		unsellableItems.put(PrisesList.RED_MUSHROOM.getID(), PrisesList.RED_MUSHROOM);
		unsellableItems.put(PrisesList.NETHER_WARTS.getID(), PrisesList.NETHER_WARTS);
		unsellableItems.put(PrisesList.MILK_BUCKET.getID(), PrisesList.MILK_BUCKET);
		
		//Pillage
		unsellableItems.put(PrisesList.SOUL_SAND.getID(), PrisesList.SOUL_SAND);
		unsellableItems.put(PrisesList.WITHER_SKULL.getID(), PrisesList.WITHER_SKULL);
		
		//Divers
		unsellableItems.put(PrisesList.WOOL.getID(), PrisesList.WOOL);
		unsellableItems.put(PrisesList.INK_SACK.getID(), PrisesList.INK_SACK);
		unsellableItems.put(PrisesList.COOKED_STEAK.getID(), PrisesList.COOKED_STEAK);
		unsellableItems.put(PrisesList.COOKED_CHIKEN.getID(), PrisesList.COOKED_CHIKEN);
		unsellableItems.put(PrisesList.COOKED_PORCKCHOP.getID(), PrisesList.COOKED_PORCKCHOP);
		
		//Blocks
		unsellableItems.put(PrisesList.GRASS.getID(), PrisesList.GRASS);
		unsellableItems.put(PrisesList.DIRT.getID(), PrisesList.DIRT);
		unsellableItems.put(PrisesList.PACKED_ICE.getID(), PrisesList.PACKED_ICE);
		unsellableItems.put(PrisesList.ICE.getID(), PrisesList.ICE);
		unsellableItems.put(PrisesList.GRAVEL.getID(), PrisesList.GRAVEL);
	}
	public void setPillageItems() {
		pillageList.put(PrisesList.ENDER_PEARL.getID(), PrisesList.ENDER_PEARL);
		pillageList.put(PrisesList.WITHER_SKULL.getID(), PrisesList.WITHER_SKULL);
		pillageList.put(PrisesList.SOUL_SAND.getID(), PrisesList.SOUL_SAND);
	}
	public void setOresItems() {
		oresList.put(PrisesList.EMERALD.getID(), PrisesList.EMERALD);
		oresList.put(PrisesList.DIAMOND.getID(), PrisesList.DIAMOND);
		oresList.put(PrisesList.GOLD.getID(), PrisesList.GOLD);
		oresList.put(PrisesList.IRON.getID(), PrisesList.IRON);
		oresList.put(PrisesList.COAL.getID(), PrisesList.COAL);
		oresList.put(PrisesList.REDSTONE.getID(), PrisesList.REDSTONE);
	}
	public void setOthersItems() {
		othersList.put(PrisesList.WOOL.getID(), PrisesList.WOOL);
		othersList.put(PrisesList.INK_SACK.getID(), PrisesList.INK_SACK);
		othersList.put(PrisesList.COOKED_STEAK.getID(), PrisesList.COOKED_STEAK);
		othersList.put(PrisesList.COOKED_CHIKEN.getID(), PrisesList.COOKED_CHIKEN);
		othersList.put(PrisesList.COOKED_PORCKCHOP.getID(), PrisesList.COOKED_PORCKCHOP);
	}
	public void setAlchemyItems() {
		alchemyList.put(PrisesList.EMPTY_BOTTLE.getID(), PrisesList.EMPTY_BOTTLE);
		alchemyList.put(PrisesList.BREWING_STAND.getID(), PrisesList.BREWING_STAND);
		alchemyList.put(PrisesList.MAGMA_CREAM.getID(), PrisesList.MAGMA_CREAM);
		alchemyList.put(PrisesList.GOLDEN_MELON.getID(), PrisesList.GOLDEN_MELON);
		alchemyList.put(PrisesList.GOLDEN_CARROT.getID(), PrisesList.GOLDEN_CARROT);
		alchemyList.put(PrisesList.GHAST_TEAR.getID(), PrisesList.GHAST_TEAR);
		alchemyList.put(PrisesList.FERMENTED_SPIDER_EYE.getID(), PrisesList.FERMENTED_SPIDER_EYE);
		alchemyList.put(PrisesList.RED_MUSHROOM.getID(), PrisesList.RED_MUSHROOM);
		alchemyList.put(PrisesList.BROWN_MUSHROOM.getID(), PrisesList.BROWN_MUSHROOM);
		alchemyList.put(PrisesList.NETHER_WARTS.getID(), PrisesList.NETHER_WARTS);
		alchemyList.put(PrisesList.GLOWSTONE.getID(), PrisesList.GLOWSTONE);
		alchemyList.put(PrisesList.MILK_BUCKET.getID(), PrisesList.MILK_BUCKET);
	}
	public void setBlocksItems() {
		blocksList.put(PrisesList.COBBLESTONE.getID(), PrisesList.COBBLESTONE);
		blocksList.put(PrisesList.GRAVEL.getID(), PrisesList.GRAVEL);
		blocksList.put(PrisesList.ICE.getID(), PrisesList.ICE);
		blocksList.put(PrisesList.PACKED_ICE.getID(), PrisesList.PACKED_ICE);
		blocksList.put(PrisesList.GRASS.getID(), PrisesList.GRASS);
		blocksList.put(PrisesList.SAND.getID(), PrisesList.SAND);
		blocksList.put(PrisesList.WOOD.getID(), PrisesList.WOOD);
		blocksList.put(PrisesList.OBSIDIAN.getID(), PrisesList.OBSIDIAN);
		blocksList.put(PrisesList.STONE.getID(), PrisesList.STONE);
	}
	public void setFarmingItems() {
		//Plantes
		farmingList.put(PrisesList.MELON_SEEDS.getID(), PrisesList.MELON_SEEDS);
		farmingList.put(PrisesList.PUMPKIN_SEEDS.getID(), PrisesList.PUMPKIN_SEEDS);
		farmingList.put(PrisesList.SEEDS.getID(), PrisesList.SEEDS);
		farmingList.put(PrisesList.CARROT.getID(), PrisesList.CARROT);
		farmingList.put(PrisesList.POTATO.getID(), PrisesList.POTATO);			 
		farmingList.put(PrisesList.SUGAR_CANE.getID(), PrisesList.SUGAR_CANE);
		farmingList.put(PrisesList.APPLE.getID(), PrisesList.APPLE);
		farmingList.put(PrisesList.CACTUS.getID(), PrisesList.CACTUS);
		farmingList.put(PrisesList.COCOA.getID(), PrisesList.COCOA);
		farmingList.put(PrisesList.VINE.getID(), PrisesList.VINE);
		
		//Loots 
		farmingList.put(PrisesList.STRING.getID(), PrisesList.STRING);
		farmingList.put(PrisesList.BLAZE_ROD.getID(), PrisesList.BLAZE_ROD);
		farmingList.put(PrisesList.BONE.getID(), PrisesList.BONE);
		farmingList.put(PrisesList.ROTTEN_FLESH.getID(), PrisesList.ROTTEN_FLESH);
		farmingList.put(PrisesList.GUNPOWDER.getID(), PrisesList.GUNPOWDER);
		farmingList.put(PrisesList.ARROW.getID(), PrisesList.ARROW);
		farmingList.put(PrisesList.LEATHER.getID(), PrisesList.LEATHER);
		farmingList.put(PrisesList.EGG.getID(), PrisesList.EGG);
		farmingList.put(PrisesList.FEATHER.getID(), PrisesList.FEATHER);
		farmingList.put(PrisesList.SLIME_BALL.getID(), PrisesList.SLIME_BALL);
	}
	
	public void setPrisesList() {
		for(PrisesList pl : PrisesList.values()) {
			this.pl_ = pl;
			prisesList.put(pl.getID(), pl);
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
		/*
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
		doorMeta.setDisplayName("§4Fermer / Retour");
		backDoor.setItemMeta(doorMeta);*/
		
		//3rd line
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
		for(PrisesList pl : blocksList.values()) {
			pl_ = pl;
			List<String> lores8 = Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "§2Prix de vente / u : " + pl.getSellPrise() + "$");
			ItemStack item = ItemBuilder.createItem(pl.getMaterial(), pl.getName(), (String []) lores8.toArray());
			shopInvBlocks.addItem(item);
		}
		shopInvBlocks.setItem(40, backDoor);
	}
	
	//Set items in minerals page of the shop
	public void setItemInShopMinerals() {	
		for(PrisesList pl : oresList.values()) {
			pl_ = pl;
			List<String> lores8 = Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "§2Prix de vente / u : " + pl.getSellPrise() + "$");
			ItemStack item = ItemBuilder.createItem(pl.getMaterial(), pl.getName(), (String []) lores8.toArray());
			shopInvMinerals.addItem(item);
		}
		shopInvMinerals.setItem(40, backDoor);
	}
	
	//Set items in utils page of the shop
	public void setItemInShopUtils() {
		for(PrisesList pl : othersList.values()) {
			pl_ = pl;
			List<String> lores8 = Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "§2Prix de vente / u : " + pl.getSellPrise() + "$");
			ItemStack item = ItemBuilder.createItem(pl.getMaterial(), pl.getName(), (String []) lores8.toArray());
			shopInvUtils.addItem(item);
		}
		shopInvUtils.setItem(40, backDoor);
	}
	
	//Set items in plants page of the shop
	public void setItemInShopFarming() {
		for(PrisesList pl : farmingList.values()) {
			pl_ = pl;
			List<String> lores8 = Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "§2Prix de vente / u : " + pl.getSellPrise() + "$");
			ItemStack item = ItemBuilder.createItem(pl.getMaterial(), pl.getName(), (String []) lores8.toArray());
			shopInvFarming.addItem(item);
		}
		shopInvFarming.setItem(40, backDoor);
	}

	//Set items in pillage page of the shop
	public void setItemInShopPillage() {
		for(PrisesList pl : pillageList.values()) {
			pl_ = pl;
			List<String> lores8 = Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "§2Prix de vente / u : " + pl.getSellPrise() + "$");
			ItemStack item = ItemBuilder.createItem(pl.getMaterial(), pl.getName(), (String []) lores8.toArray());
			shopInvPillage.addItem(item);
		}
		shopInvPillage.setItem(40, backDoor);
	}
	
	//Set items in alchemy page of the shop
	public void setItemInShopAlchemy() {
		for(PrisesList pl : alchemyList.values()) {
			pl_ = pl;
			List<String> lores8 = Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "§2Prix de vente / u : " + pl.getSellPrise() + "$");
			ItemStack item = ItemBuilder.createItem(pl.getMaterial(), pl.getName(), (String []) lores8.toArray());
			shopInvAlchemy.addItem(item);
		}
		shopInvAlchemy.setItem(40, backDoor);
	}	
}
