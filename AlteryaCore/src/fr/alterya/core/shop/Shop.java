package fr.alterya.core.shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/*
Author and resp of the shop: Lightiz
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
	
	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public List<PrisesList> prisesList = new ArrayList();
	
	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public List<PrisesList> unsellableItems = new ArrayList();
	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public List<PrisesList> pillageList = new ArrayList();
	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public List<PrisesList> oresList = new ArrayList();
	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public List<PrisesList> othersList = new ArrayList();
	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public List<PrisesList> alchemyList = new ArrayList();
	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public List<PrisesList> blocksList = new ArrayList();
	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public List<PrisesList> farmingList = new ArrayList();
	
	public Shop (){
		
		this.setAlchemyItems();
		this.setBlocksItems();
		this.setFarmingItems();
		this.setOresItems();
		this.setOthersItems();
		this.setPillageItems();
		this.setUnsellableItems();
		this.setPrisesList();
		
		//Set all items in their inventory to prepare the shop
		setItemsInShopMain();
		setItemInShopBlocks();
		setItemInShopFarming();
		setItemInShopUtils();
		setItemInShopMinerals();
		setItemsInShopSellBuy();
		setItemInShopAlchemy();
		setItemInShopPillage();
		
		//setDyesColors();
	}
	
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
	
	public Inventory getShopPartOpened(Player player) {
		if(player.getOpenInventory() != shopInvMain 
				|| player.getOpenInventory() != shopInvBlocks
				|| player.getOpenInventory() != shopInvSellBuy
				|| player.getOpenInventory() != shopInvMinerals
				|| player.getOpenInventory() != shopInvAlchemy
				|| player.getOpenInventory() != shopInvPillage
				|| player.getOpenInventory() != shopInvFarming
				|| player.getOpenInventory() != shopInvUtils) {
			return (Inventory) player.getOpenInventory();
		}
		
		return null;
	}
	
	public ItemStack sell = new ItemStack(Material.ARROW);
	public ItemMeta sellMeta = sell.getItemMeta();
	
	public ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
	public ItemMeta doorMeta = backDoor.getItemMeta();
	
	public ItemStack Buy = new ItemStack(Material.BEACON);
	public ItemMeta buyMeta = Buy.getItemMeta();
	
	public void openInvSellBuy(Player player, ItemStack itemToSellBuy) {
		setInvSellBuy(player);
		shopInvSellBuy.setItem(13, itemToSellBuy);
		player.openInventory(shopInvSellBuy);
	}
	
	public void setInvSellBuy(Player player) {
		if(shopInvSellBuy.getItem(13) != null) {
			
			doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
			doorMeta.setDisplayName("§4Fermer / Retour");
			backDoor.setItemMeta(doorMeta);
			
			buyMeta.setDisplayName("§1 Acheter");
			Buy.setItemMeta(buyMeta);

			sellMeta.setDisplayName("§1Vendre");
			sell.setItemMeta(sellMeta);
			
			shopInvSellBuy.setItem(24, backDoor);
			shopInvSellBuy.setItem(31, sell);
			shopInvSellBuy.setItem(20, Buy);
		}else {
			player.sendMessage("Vous devez séléctionner un item pour accèder à cet invenaire ! Veuillez contacter un Administrateur ou un Développeur.");
		}
	}
	
	public void setUnsellableItems() {
		//Minerais
		unsellableItems.add(PrisesList.EMERALD);
		
		//Farming
		unsellableItems.add(PrisesList.MELON_SEEDS);
		unsellableItems.add(PrisesList.SEEDS);
		unsellableItems.add(PrisesList.PUMPKIN_SEEDS);
		unsellableItems.add(PrisesList.COCOA);
		unsellableItems.add(PrisesList.CACTUS);
		unsellableItems.add(PrisesList.VINE);
		unsellableItems.add(PrisesList.SUGAR_CAN);
		
		//Loots
		unsellableItems.add(PrisesList.EMPTY_BOTTLE);
		unsellableItems.add(PrisesList.BREWING_STAND);
		unsellableItems.add(PrisesList.GOLDEN_MELON);
		unsellableItems.add(PrisesList.GOLDEN_CARROT);
		unsellableItems.add(PrisesList.BROWN_MUSHROOM);
		unsellableItems.add(PrisesList.RED_MUSHROOM);
		unsellableItems.add(PrisesList.NETHER_WARTS);
		unsellableItems.add(PrisesList.MILK_BUCKET);
		
		//Pillage
		unsellableItems.add(PrisesList.SOUL_SAND);
		unsellableItems.add(PrisesList.WITHER_SKULL);
		unsellableItems.add(PrisesList.CREEPER_EGG);
		unsellableItems.add(PrisesList.TNT);
		
		//Divers
		unsellableItems.add(PrisesList.WOOL);
		unsellableItems.add(PrisesList.INK_SACK);
		//unsellableItems.add(PrisesList.GREEN_DYE);
		//unsellableItems.add(PrisesList.LIGHT_GREEN_DYE);
		//unsellableItems.add(PrisesList.LIGHT_BLEUE_DYE);
		//unsellableItems.add(PrisesList.CYAN_DYE);
		//unsellableItems.add(PrisesList.RED_DYE);
		//unsellableItems.add(PrisesList.ORANGE_DYE);
		//unsellableItems.add(PrisesList.YELLOW_DYE);
		unsellableItems.add(PrisesList.COOKED_STEAK);
		unsellableItems.add(PrisesList.COOKED_CHIKEN);
		unsellableItems.add(PrisesList.COOKED_PORCKCHOP);
	}
	public void setPillageItems() {
		pillageList.add(PrisesList.ENDER_PEARL);
		pillageList.add(PrisesList.WITHER_SKULL);
		pillageList.add(PrisesList.CREEPER_EGG);
		pillageList.add(PrisesList.TNT);
		pillageList.add(PrisesList.SOUL_SAND);
	}
	public void setOresItems() {
		oresList.add(PrisesList.EMERALD);
		oresList.add(PrisesList.DIAMOND);
		oresList.add(PrisesList.GOLD);
		oresList.add(PrisesList.IRON);
		oresList.add(PrisesList.COAL);
		oresList.add(PrisesList.REDSTONE);
	}
	public void setOthersItems() {
		othersList.add(PrisesList.WOOL);
		othersList.add(PrisesList.INK_SACK);
		//othersList.add(PrisesList.GREEN_DYE);
		//othersList.add(PrisesList.LIGHT_GREEN_DYE);
		//othersList.add(PrisesList.LIGHT_BLEUE_DYE);
		//othersList.add(PrisesList.CYAN_DYE);
		//othersList.add(PrisesList.RED_DYE);
		//othersList.add(PrisesList.ORANGE_DYE);
		//othersList.add(PrisesList.YELLOW_DYE);
		othersList.add(PrisesList.COOKED_STEAK);
		othersList.add(PrisesList.COOKED_CHIKEN);
		othersList.add(PrisesList.COOKED_PORCKCHOP);
	}
	public void setAlchemyItems() {
		alchemyList.add(PrisesList.EMPTY_BOTTLE);
		alchemyList.add(PrisesList.BREWING_STAND);
		alchemyList.add(PrisesList.MAGMA_CREAM);
		alchemyList.add(PrisesList.GOLDEN_MELON);
		alchemyList.add(PrisesList.GOLDEN_CARROT);
		alchemyList.add(PrisesList.GHAST_TEAR);
		alchemyList.add(PrisesList.FERMENTED_SPIDER_EYE);
		alchemyList.add(PrisesList.RED_MUSHROOM);
		alchemyList.add(PrisesList.BROWN_MUSHROOM);
		alchemyList.add(PrisesList.NETHER_WARTS);
		alchemyList.add(PrisesList.GLOWSTONE);
		alchemyList.add(PrisesList.MILK_BUCKET);
	}
	public void setBlocksItems() {
		blocksList.add(PrisesList.COBBLESTONE);
		blocksList.add(PrisesList.GRAVEL);
		blocksList.add(PrisesList.ICE);
		blocksList.add(PrisesList.PACKED_ICE);
		blocksList.add(PrisesList.GRAVEL);
		blocksList.add(PrisesList.GRASS);
		blocksList.add(PrisesList.SAND);
		blocksList.add(PrisesList.WOOD);
		blocksList.add(PrisesList.OBSIDIAN);
		blocksList.add(PrisesList.STONE);
	}
	public void setFarmingItems() {
		//Plantes
		farmingList.add(PrisesList.MELON_SEEDS);
		farmingList.add(PrisesList.PUMPKIN_SEEDS);
		farmingList.add(PrisesList.SEEDS);
		farmingList.add(PrisesList.CARROT);
		farmingList.add(PrisesList.POTATO);			 
		farmingList.add(PrisesList.SUGAR_CAN);
		farmingList.add(PrisesList.APPLE);
		farmingList.add(PrisesList.CACTUS);
		farmingList.add(PrisesList.COCOA);
		farmingList.add(PrisesList.VINE);
		
		//Loots 
		farmingList.add(PrisesList.STRING);
		farmingList.add(PrisesList.BLAZE_ROD);
		farmingList.add(PrisesList.BONE);
		farmingList.add(PrisesList.ROTTEN_FLESH);
		farmingList.add(PrisesList.GUNPOWDER);
		farmingList.add(PrisesList.ARROW);
		farmingList.add(PrisesList.LEATHER);
		farmingList.add(PrisesList.EGG);
		farmingList.add(PrisesList.FEATHER);
		farmingList.add(PrisesList.SLIME_BALL);
	}
	
	public void setPrisesList() {
		for(PrisesList pl : PrisesList.values()) {
			this.pl_ = pl;
			prisesList.add(pl);
		}
	}
	
	//Create all inventorys
	public static Inventory shopInvMain = Bukkit.createInventory(null, 5*9, "§dShop");
	
	public static Inventory shopInvBlocks = Bukkit.createInventory(null, 5*9, "§eBlocks");
	public static Inventory shopInvUtils = Bukkit.createInventory(null, 5*9, "§eDivers");
	public static Inventory shopInvFarming = Bukkit.createInventory(null, 5*9, "§eFarming");
	public static Inventory shopInvMinerals = Bukkit.createInventory(null, 5*9, "§eMinerais");
	public static Inventory shopInvPillage = Bukkit.createInventory(null, 5*9, "§Pillage");
	public static Inventory shopInvAlchemy = Bukkit.createInventory(null, 5*9, "§eAlchimie");
	
	public static Inventory shopInvSellBuy = Bukkit.createInventory(null, 5*9, "§eSell / Buy items");
	
	//Set items in main page of the shop
	public void setItemsInShopMain() {
		ItemStack blocks = new ItemStack(Material.STONE);
		ItemMeta blocksMeta = blocks.getItemMeta();
		blocksMeta.setDisplayName("§1Blocks / Décorations");
		blocksMeta.setLore(Arrays.asList("Ici, vous trouverez des blocks de toutes sortes et des blocks décoratifs !", "Clickez pour plus d'informations !"));
		blocks.setItemMeta(blocksMeta);
		
		ItemStack minerals = new ItemStack(Material.IRON_INGOT);
		ItemMeta mineralsMeta = minerals.getItemMeta();
		mineralsMeta.setDisplayName("§1Minerais");
		mineralsMeta.setLore(Arrays.asList("Ici, vous trouverez tout les minerais !", "Clickez pour plus d'informations !"));
		minerals.setItemMeta(mineralsMeta);
		
		ItemStack farming = new ItemStack(Material.DIAMOND_HOE);
		ItemMeta farmingMeta = farming.getItemMeta();
		farmingMeta.setDisplayName("§1Plantes et végétations");
		farmingMeta.setLore(Arrays.asList("Ici, vous trouverez des blocks de toutes sortes et des blocks décoratifs !", "Clickez pour plus d'informations !"));
		farming.setItemMeta(farmingMeta);
		
		ItemStack utils = new ItemStack(Material.EMPTY_MAP);
		ItemMeta utilsMeta = utils.getItemMeta();
		utilsMeta.setDisplayName("§1Items sans catégorie attribuée");
		utilsMeta.setLore(Arrays.asList("Ici, vous trouverez des items de toutes sortes confondues mais qui ne sont ni des blocks ni des plantes ni des minéraux, ni autre !", "Clickez pour plus d'informations !"));
		utils.setItemMeta(utilsMeta);
		
		ItemStack pillage = new ItemStack(Material.IRON_AXE);
		ItemMeta pillageMeta = utils.getItemMeta();
		pillageMeta.setDisplayName("§1Items utilisés pour les pillages");
		pillageMeta.setLore(Arrays.asList("Ici, vous trouverez des items pour réaliser des pillages !", "Clickez pour plus d'informations !"));
		pillage.setItemMeta(pillageMeta);
		
		ItemStack alchemy = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta alchemyMeta = alchemy.getItemMeta();
		alchemyMeta.setDisplayName("§1Items dédiés à l'alchimie");
		alchemyMeta.setLore(Arrays.asList("Ici, vous trouverez des items pour faire de l'achimie !", "Clickez pour plus d'informations !"));
		alchemy.setItemMeta(alchemyMeta);
		
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
		doorMeta.setDisplayName("§4Fermer / Retour");
		backDoor.setItemMeta(doorMeta);
		
		//3rd line
		shopInvMain.setItem(19, blocks);
		shopInvMain.setItem(21, minerals);
		shopInvMain.setItem(23, farming);
		shopInvMain.setItem(25, utils);
		shopInvMain.setItem(40, backDoor);
	}
	
	//Set items in sell / buy page of the shop
	public void setItemsInShopSellBuy() {
		
	}
	
	//Set items in blocks page of the shop
	public void setItemInShopBlocks() {
		for(PrisesList pl : blocksList) {
			pl_ = pl;
			ItemStack item = new ItemStack(pl.getMaterial());
			ItemMeta itemMeta = item.getItemMeta();
			itemMeta.setDisplayName(pl.getMaterial().toString());
			itemMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "Prix de vente / u :" + pl.getSellPrise() + "$"));
			item.setItemMeta(itemMeta);
		}
		
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
		doorMeta.setDisplayName("§4Fermer / Retour");
		backDoor.setItemMeta(doorMeta);
		
		int slotToPlaceItem = 0;
		
		if(shopInvBlocks.getItem(slotToPlaceItem) != null) {
			slotToPlaceItem ++;
		}
		
		shopInvBlocks.setItem(slotToPlaceItem, new ItemStack(pl_.getMaterial()));
		
		shopInvBlocks.setItem(40, backDoor);
	}
	
	//Set items in minerals page of the shop
	public void setItemInShopMinerals() {	
		for(PrisesList pl : oresList) {
			pl_ = pl;
			ItemStack item = new ItemStack(pl.getMaterial());
			ItemMeta itemMeta = item.getItemMeta();
			itemMeta.setDisplayName(pl.getMaterial().toString());
			itemMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "Prix de vente / u :" + pl.getSellPrise() + "$"));
			item.setItemMeta(itemMeta);
		}
		
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
		doorMeta.setDisplayName("§4Fermer / Retour");
		backDoor.setItemMeta(doorMeta);

		int slotToPlaceItem = 0;
		
		if(shopInvBlocks.getItem(slotToPlaceItem) != null) {
			slotToPlaceItem ++;
		}
		
		shopInvBlocks.setItem(slotToPlaceItem, new ItemStack(pl_.getMaterial()));
		
		shopInvBlocks.setItem(40, backDoor);
	}
	
	//Set items in utils page of the shop
	public void setItemInShopUtils() {
	
		for(PrisesList pl : othersList) {
			pl_ = pl;
			ItemStack item = new ItemStack(pl.getMaterial());
			ItemMeta itemMeta = item.getItemMeta();
			itemMeta.setDisplayName(pl.getMaterial().toString());
			itemMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "Prix de vente / u :" + pl.getSellPrise() + "$"));
			item.setItemMeta(itemMeta);
		}
		
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
		doorMeta.setDisplayName("§4Fermer / Retour");
		backDoor.setItemMeta(doorMeta);

		int slotToPlaceItem = 0;
		
		if(shopInvBlocks.getItem(slotToPlaceItem) != null) {
			slotToPlaceItem ++;
		}
		
		shopInvBlocks.setItem(slotToPlaceItem, new ItemStack(pl_.getMaterial()));
		
		shopInvBlocks.setItem(40, backDoor);;
	}
	
	//Set items in plants page of the shop
	public void setItemInShopFarming() {
		for(PrisesList pl : farmingList) {
			pl_ = pl;
			ItemStack item = new ItemStack(pl.getMaterial());
			ItemMeta itemMeta = item.getItemMeta();
			itemMeta.setDisplayName(pl.getMaterial().toString());
			itemMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "Prix de vente / u :" + pl.getSellPrise() + "$"));
			item.setItemMeta(itemMeta);
		}
		
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
		doorMeta.setDisplayName("§4Fermer / Retour");
		backDoor.setItemMeta(doorMeta);

		int slotToPlaceItem = 0;
		
		if(shopInvBlocks.getItem(slotToPlaceItem) != null) {
			slotToPlaceItem ++;
		}
		
		shopInvBlocks.setItem(slotToPlaceItem, new ItemStack(pl_.getMaterial()));
		
		shopInvBlocks.setItem(40, backDoor);
	}

	//Set items in pillage page of the shop
	public void setItemInShopPillage() {
		for(PrisesList pl : pillageList) {
			pl_ = pl;
			ItemStack item = new ItemStack(pl.getMaterial());
			ItemMeta itemMeta = item.getItemMeta();
			itemMeta.setDisplayName(pl.getMaterial().toString());
			itemMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "Prix de vente / u :" + pl.getSellPrise() + "$"));
			item.setItemMeta(itemMeta);
		}
		
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
		doorMeta.setDisplayName("§4Fermer / Retour");
		backDoor.setItemMeta(doorMeta);

		int slotToPlaceItem = 0;
		
		if(shopInvBlocks.getItem(slotToPlaceItem) != null) {
			slotToPlaceItem ++;
		}
		
		shopInvBlocks.setItem(slotToPlaceItem, new ItemStack(pl_.getMaterial()));
		
		shopInvBlocks.setItem(40, backDoor);
	}
	
	//Set items in alchemy page of the shop
	public void setItemInShopAlchemy() {
		for(PrisesList pl : alchemyList) {
			pl_ = pl;
			ItemStack item = new ItemStack(pl.getMaterial());
			ItemMeta itemMeta = item.getItemMeta();
			itemMeta.setDisplayName(pl.getMaterial().toString());
			itemMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + pl.getBuyPrise() + "$", "Prix de vente / u :" + pl.getSellPrise() + "$"));
			item.setItemMeta(itemMeta);
		}
		
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
		doorMeta.setDisplayName("§4Fermer / Retour");
		backDoor.setItemMeta(doorMeta);

		int slotToPlaceItem = 0;
		
		if(shopInvBlocks.getItem(slotToPlaceItem) != null) {
			slotToPlaceItem ++;
		}
		
		shopInvBlocks.setItem(slotToPlaceItem, new ItemStack(pl_.getMaterial()));
		
		shopInvBlocks.setItem(40, backDoor);
	}	//The void event for the main shop inventory
	//The void event for the blocks shop inventory
	//The void event for the utils shop inventory
	//The void event for the plants shop inventory
	//The void event for the minerals shop inventory

}
