package fr.alterya.core;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

import fr.alterya.core.shop.PrisesList;
import fr.alterya.money.money.manager.AccountManager;
import fr.alterya.money.money.manager.account.Account;
import fr.alterya.money.money.manager.account.AccountException;

/*
Author and resp of the shop: Lightiz
*/

public class Shop implements Listener {
	
	/*public static Dye red;
	public static Dye gray;
	public static Dye green;
	public static Dye cyan;
	public static Dye bleue;
	public static Dye light_bleue;
	public static Dye orange;
	public static Dye yellow;*/
	
	public final List<PrisesList> unsellableItems;
	
	public final List<PrisesList> pillageList;
	public final List<PrisesList> oresList;
	public final List<PrisesList> othersList;
	public final List<PrisesList> alchemyList;
	public final List<PrisesList> blocksList;
	public final List<PrisesList> farmingList;
	
	Shop (){
		//Set all items in their inventory to prepare the shop
		setItemsInShopMain();
		setItemInShopBlocks();
		setItemInShopPlants();
		setItemInShopUtils();
		setItemInShopMinerals();
		setItemsInShopSellBuy();
		
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
	
	//Create all inventorys
	public static Inventory shopInvMain = Bukkit.createInventory(null, 5*9, "§dShop");
	
	public static Inventory shopInvBlocks = Bukkit.createInventory(null, 5*9, "§eBlocks");
	public static Inventory shopInvUtils = Bukkit.createInventory(null, 5*9, "§eUtils");
	public static Inventory shopInvPlants = Bukkit.createInventory(null, 5*9, "§ePlants");
	public static Inventory shopInvMinerals = Bukkit.createInventory(null, 5*9, "§eMinerals");
	
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
		
		ItemStack plants = new ItemStack(Material.YELLOW_FLOWER);
		ItemMeta plantsMeta = plants.getItemMeta();
		plantsMeta.setDisplayName("§1Plantes et végétations");
		plantsMeta.setLore(Arrays.asList("Ici, vous trouverez des blocks de toutes sortes et des blocks décoratifs !", "Clickez pour plus d'informations !"));
		plants.setItemMeta(plantsMeta);
		
		ItemStack utils = new ItemStack(Material.EMPTY_MAP);
		ItemMeta utilsMeta = utils.getItemMeta();
		utilsMeta.setDisplayName("§1Items sans catégorie attribuée");
		utilsMeta.setLore(Arrays.asList("Ici, vous trouverez des items de toutes sortes confondues mais qui ne sont ni des blocks ni des plantes ni des minéraux !", "Clickez pour plus d'informations !"));
		utils.setItemMeta(utilsMeta);
		
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
		doorMeta.setDisplayName("§4Fermer / Retour");
		backDoor.setItemMeta(doorMeta);
		
		//1st line
		shopInvMain.setItem(0, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(1, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(2, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(3, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(4, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(5, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(6, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(7, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(8, new ItemStack(Material.THIN_GLASS));
		
		//2nd line
		shopInvMain.setItem(9, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(10, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(11, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(12, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(13, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(14, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(15, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(16, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(17, new ItemStack(Material.THIN_GLASS));
		
		//3rd line
		shopInvMain.setItem(18, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(19, blocks);
		shopInvMain.setItem(20, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(21, minerals);
		shopInvMain.setItem(22, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(23, plants);
		shopInvMain.setItem(24, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(25, utils);
		shopInvMain.setItem(26, new ItemStack(Material.THIN_GLASS));
		
		//Line 4
		shopInvMain.setItem(27, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(28, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(29, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(30, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(31, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(32, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(33, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(34, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(35, new ItemStack(Material.THIN_GLASS));
		
		//Line 5
		shopInvMain.setItem(36, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(37, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(38, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(39, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(40, backDoor);
		shopInvMain.setItem(41, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(42, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(43, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(44, new ItemStack(Material.THIN_GLASS));
	}
	
	//Set items in sell / buy page of the shop
	public void setItemsInShopSellBuy() {
		
	}
	
	//Set items in blocks page of the shop
	public void setItemInShopBlocks() {
		
		ItemStack stone = new ItemStack(Material.STONE);
		ItemMeta stoneMeta = stone.getItemMeta();
		stoneMeta.setDisplayName("Pierre");
		stoneMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + stonePrise + "$", "Prix de vente / u :" + stoneSellCount + "$"));
		stone.setItemMeta(stoneMeta);
		
		ItemStack dirt = new ItemStack(Material.DIRT);
		ItemMeta dirtMeta = dirt.getItemMeta();
		dirtMeta.setDisplayName("Terre");
		dirtMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + dirtPrise + "$", "Prix de vente / u :" + dirtSellCount + "$"));
		dirt.setItemMeta(dirtMeta);
		
		ItemStack sand = new ItemStack(Material.SAND);
		ItemMeta sandMeta = sand.getItemMeta();
		sandMeta.setDisplayName("Sable");
		sandMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + sandPrise + "$", "Prix de vente / u :" + sandSellCount + "$"));
		sand.setItemMeta(sandMeta);
		
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
		doorMeta.setDisplayName("§4Fermer / Retour");
		backDoor.setItemMeta(doorMeta);
		
		//1st line
		shopInvMain.setItem(0, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(1, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(2, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(3, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(4, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(5, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(6, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(7, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(8, new ItemStack(Material.THIN_GLASS));
				
		//2nd line
		shopInvMain.setItem(9, stone);
		shopInvMain.setItem(10, dirt);
		shopInvMain.setItem(11, sand);
		shopInvMain.setItem(12, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(13, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(14, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(15, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(16, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(17, new ItemStack(Material.THIN_GLASS));
				
		//3rd line
		shopInvMain.setItem(18, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(19, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(20, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(21, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(22, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(23, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(24, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(25, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(26, new ItemStack(Material.THIN_GLASS));
			
		//Line 4
		shopInvMain.setItem(27, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(28, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(29, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(30, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(31, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(32, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(33, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(34, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(35, new ItemStack(Material.THIN_GLASS));
				
		//Line 5
		shopInvMain.setItem(36, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(37, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(38, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(39, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(40, backDoor);
		shopInvMain.setItem(41, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(42, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(43, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(44, new ItemStack(Material.THIN_GLASS));
	}
	
	//Set items in minerals page of the shop
	public void setItemInShopMinerals() {
		
		ItemStack diamond = new ItemStack(Material.DIAMOND);
		ItemMeta diamondMeta = diamond.getItemMeta();
		diamondMeta.setDisplayName("Diamant");
		diamondMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + diamondPrise + "$", "Prix de vente / u : " + diamondSellCount + "$"));
		diamond.setItemMeta(diamondMeta);
		
		ItemStack gold = new ItemStack(Material.GOLD_INGOT);
		ItemMeta goldMeta = gold.getItemMeta();
		goldMeta.setDisplayName("Lingot d'or");
		goldMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + goldPrise + "$", "Prix de vente / u : " + goldSellCount + "$"));
		gold.setItemMeta(goldMeta);
		
		ItemStack emerald = new ItemStack(Material.EMERALD);
		ItemMeta emeraldMeta = emerald.getItemMeta();
		emeraldMeta.setDisplayName("Emeraude");
		emeraldMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + emeraldPrise + "$", "Prix de vente / u : " + emeraldSellCount + "$"));
		emerald.setItemMeta(emeraldMeta);
		
		ItemStack iron = new ItemStack(Material.IRON_INGOT);
		ItemMeta ironMeta = iron.getItemMeta();
		ironMeta.setDisplayName("Lingot de fer");
		ironMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + ironPrise + "$", "Prix de vente / u : " + ironSellCount + "$"));
		iron.setItemMeta(ironMeta);
		
		ItemStack coal = new ItemStack(Material.COAL);
		ItemMeta coalMeta = coal.getItemMeta();
		coalMeta.setDisplayName("Charbon");
		coalMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + coalPrise + "$", "Prix de vente / u : " + coalSellCount + "$"));
		coal.setItemMeta(coalMeta);
		
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
		doorMeta.setDisplayName("§4Fermer / Retour");
		backDoor.setItemMeta(doorMeta);
		
		//1st line
		shopInvMain.setItem(0, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(1, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(2, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(3, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(4, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(5, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(6, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(7, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(8, new ItemStack(Material.THIN_GLASS));
				
		//2nd line
		shopInvMain.setItem(9, diamond);
		shopInvMain.setItem(10, gold);
		shopInvMain.setItem(11, emerald);
		shopInvMain.setItem(12, iron);
		shopInvMain.setItem(13, coal);
		shopInvMain.setItem(14, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(15, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(16, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(17, new ItemStack(Material.THIN_GLASS));
				
		//3rd line
		shopInvMain.setItem(18, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(19, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(20, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(21, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(22, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(23, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(24, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(25, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(26, new ItemStack(Material.THIN_GLASS));
				
		//Line 4
		shopInvMain.setItem(27, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(28, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(29, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(30, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(31, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(32, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(33, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(34, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(35, new ItemStack(Material.THIN_GLASS));
				
		//Line 5
		shopInvMain.setItem(36, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(37, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(38, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(39, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(40, backDoor);
		shopInvMain.setItem(41, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(42, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(43, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(44, new ItemStack(Material.THIN_GLASS));
	}
	
	//Set items in utils page of the shop
	public void setItemInShopUtils() {
	
		ItemStack bones = new ItemStack(Material.BONE);
		ItemMeta bonesMeta = bones.getItemMeta();
		bonesMeta.setDisplayName("Lingot d'or");
		bonesMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + bonesPrise + "$", "Prix de vente / u : " + bonesSellCount + "$"));
		bones.setItemMeta(bonesMeta);
		
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
		doorMeta.setDisplayName("§4Fermer / Retour");
		backDoor.setItemMeta(doorMeta);
		
		//1st line
		shopInvMain.setItem(0, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(1, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(2, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(3, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(4, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(5, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(6, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(7, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(8, new ItemStack(Material.THIN_GLASS));
				
		//2nd line
		shopInvMain.setItem(9, bones);
		shopInvMain.setItem(10, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(11, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(12, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(13, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(14, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(15, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(16, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(17, new ItemStack(Material.THIN_GLASS));
				
		//3rd line
		shopInvMain.setItem(18, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(19, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(20, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(21, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(22, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(23, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(24, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(25, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(26, new ItemStack(Material.THIN_GLASS));
				
		//Line 4
		shopInvMain.setItem(27, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(28, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(29, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(30, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(31, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(32, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(33, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(34, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(35, new ItemStack(Material.THIN_GLASS));
				
		//Line 5
		shopInvMain.setItem(36, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(37, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(38, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(39, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(40, backDoor);
		shopInvMain.setItem(41, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(42, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(43, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(44, new ItemStack(Material.THIN_GLASS));
	}
	
	//Set items in plants page of the shop
	public void setItemInShopPlants() {

		
		ItemStack seeds = new ItemStack(Material.SEEDS);
		ItemMeta seedsMeta = seeds.getItemMeta();
		seedsMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + seedsPrise + "$", "Prix de vente / u :" + seedsSellCount + "$"));
		seedsMeta.setDisplayName("Graines de blé");
		seeds.setItemMeta(seedsMeta);
		
		ItemStack sugarCan = new ItemStack(Material.SUGAR_CANE);
		ItemMeta sugarCanMeta = sugarCan.getItemMeta();
		sugarCanMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + sugarCanePrise + "$", "La vente de canne à sucre est bloquée."));
		sugarCanMeta.setDisplayName("Canne à sucre");
		sugarCan.setItemMeta(sugarCanMeta);
		
		ItemStack cactus = new ItemStack(Material.CACTUS);
		ItemMeta cactusMeta = cactus.getItemMeta();
		cactusMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + cactusPrise + "$", "La vente de cactus est bloquée."));
		cactusMeta.setDisplayName("Cactus");
		cactus.setItemMeta(cactusMeta);
		
		ItemStack cocoa = new ItemStack(Material.COCOA);
		ItemMeta cocoaMeta = cocoa.getItemMeta();
		cocoaMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + cocoaPrise + "$", "La vente de cactus est bloquée."));
		cocoaMeta.setDisplayName("Cacao");
		cocoa.setItemMeta(cocoaMeta);
		
		ItemStack melon = new ItemStack(Material.MELON);
		ItemMeta melonMeta = melon.getItemMeta();
		melonMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + melonPrise + "$", "La vente de cactus est bloquée."));
		melonMeta.setDisplayName("Pastèque / Melon d'eau");
		melon.setItemMeta(melonMeta);
		
		ItemStack pumpkin = new ItemStack(Material.PUMPKIN);
		ItemMeta pumpkinMeta = pumpkin.getItemMeta();
		pumpkinMeta.setLore(Arrays.asList("§2Prix d'achat / u : " + pumpkinPrise + "$", "La vente de cactus est bloquée."));
		pumpkinMeta.setDisplayName("Citoruille");
		pumpkin.setItemMeta(pumpkinMeta);
		
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
		doorMeta.setDisplayName("§4Fermer / Retour");
		backDoor.setItemMeta(doorMeta);
		
		//1st line
		shopInvMain.setItem(0, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(1, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(2, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(3, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(4, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(5, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(6, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(7, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(8, new ItemStack(Material.THIN_GLASS));
				
		//2nd line
		shopInvMain.setItem(9, seeds);
		shopInvMain.setItem(10, sugarCan);
		shopInvMain.setItem(11, cocoa);
		shopInvMain.setItem(12, cactus);
		shopInvMain.setItem(13, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(14, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(15, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(16, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(17, new ItemStack(Material.THIN_GLASS));
				
		//3rd line
		shopInvMain.setItem(18, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(19, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(20, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(21, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(22, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(23, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(24, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(25, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(26, new ItemStack(Material.THIN_GLASS));
				
		//Line 4
		shopInvMain.setItem(27, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(28, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(29, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(30, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(31, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(32, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(33, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(34, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(35, new ItemStack(Material.THIN_GLASS));
				
		//Line 5
		shopInvMain.setItem(36, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(37, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(38, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(39, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(40, backDoor);
		shopInvMain.setItem(41, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(42, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(43, new ItemStack(Material.THIN_GLASS));
		shopInvMain.setItem(44, new ItemStack(Material.THIN_GLASS));
	}

	//The void event for the main shop inventory
	@SuppressWarnings("static-access")
	public void onInventoryInterractMain(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);
		if(event.getCurrentItem() == new ItemStack(Material.EMPTY_MAP) && event.getClickedInventory() == shopInvMain 
				&& event.getAction() == event.getAction().PICKUP_ALL 
				|| event.getAction() == event.getAction().PICKUP_HALF
				|| event.getAction() == event.getAction().PICKUP_ONE
				|| event.getAction() == event.getAction().SWAP_WITH_CURSOR
				|| event.getAction() == event.getAction().COLLECT_TO_CURSOR
				|| event.getAction() == event.getAction().CLONE_STACK) {
			player.getOpenInventory().close();
			player.openInventory(shopInvUtils);
			player.updateInventory();
		}else if(event.getCurrentItem() == new ItemStack(Material.YELLOW_FLOWER) && event.getClickedInventory() == shopInvMain 
				&& event.getAction() == event.getAction().PICKUP_ALL 
				|| event.getAction() == event.getAction().PICKUP_HALF
				|| event.getAction() == event.getAction().PICKUP_ONE
				|| event.getAction() == event.getAction().SWAP_WITH_CURSOR
				|| event.getAction() == event.getAction().COLLECT_TO_CURSOR
				|| event.getAction() == event.getAction().CLONE_STACK) {
			player.getOpenInventory().close();
			player.openInventory(shopInvPlants);
			player.updateInventory();
		}else if(event.getCurrentItem() == new ItemStack(Material.STONE) && event.getClickedInventory() == shopInvMain 
				&& event.getAction() == event.getAction().PICKUP_ALL 
				|| event.getAction() == event.getAction().PICKUP_HALF
				|| event.getAction() == event.getAction().PICKUP_ONE
				|| event.getAction() == event.getAction().SWAP_WITH_CURSOR
				|| event.getAction() == event.getAction().COLLECT_TO_CURSOR
				|| event.getAction() == event.getAction().CLONE_STACK) {
			player.getOpenInventory().close();
			player.openInventory(shopInvBlocks);
			player.updateInventory();
		}else if(event.getCurrentItem() == new ItemStack(Material.IRON_INGOT) && event.getClickedInventory() == shopInvMain 
				&& event.getAction() == event.getAction().PICKUP_ALL 
				|| event.getAction() == event.getAction().PICKUP_HALF
				|| event.getAction() == event.getAction().PICKUP_ONE
				|| event.getAction() == event.getAction().SWAP_WITH_CURSOR
				|| event.getAction() == event.getAction().COLLECT_TO_CURSOR
				|| event.getAction() == event.getAction().CLONE_STACK) {
			player.getOpenInventory().close();
			player.openInventory(shopInvMinerals);
			player.updateInventory();
		}
	}

	//The void event for the blocks shop inventory
	@SuppressWarnings("unused")
	public void onInventoryInterractBlocks(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack currentStack = event.getCurrentItem();
		event.setCancelled(true);
		
		Account account = null;
		
		try {
			account = AccountManager.getAccount(player.getUniqueId().toString(), "default");
		} catch(AccountException e) {
			player.sendMessage(e.getMessage());
		}
		
		if(event.getClickedInventory() == shopInvBlocks && event.getCursor().getType().isBlock() == true) {
			
			ItemStack stackChosen = event.getCurrentItem();
			
			currentStack = stackChosen;
			
			ItemStack more = new ItemStack(Material.ARROW);
			ItemMeta moreMeta = more.getItemMeta();
			moreMeta.setDisplayName("Plus");
			more.setItemMeta(moreMeta);
			
			ItemStack less = new ItemStack(Material.ANVIL);
			ItemMeta lessMeta = less.getItemMeta();
			lessMeta.setDisplayName("Moins");
			less.setItemMeta(lessMeta);
			
			ItemStack sell = new ItemStack(Material.NAME_TAG);
			ItemMeta sellMeta = sell.getItemMeta();
			sellMeta.setDisplayName("Vendre");
			sell.setItemMeta(sellMeta);
			
			ItemStack buy = new ItemStack(Material.CHEST);
			ItemMeta buyMeta = buy.getItemMeta();
			buyMeta.setDisplayName("Acheter");
			buy.setItemMeta(buyMeta);
			
			ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
			ItemMeta doorMeta = backDoor.getItemMeta();
			doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
			doorMeta.setDisplayName("§4Fermer / Retour");
			backDoor.setItemMeta(doorMeta);
			
			//1
			shopInvSellBuy.setItem(0, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(1, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(2, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(3, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(4, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(5, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(6, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(7, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(8, new ItemStack(Material.THIN_GLASS));
			
			//2
			shopInvSellBuy.setItem(9, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(10, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(11, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(12, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(13, event.getCurrentItem());
			shopInvSellBuy.setItem(14, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(15, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(16, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(17, new ItemStack(Material.THIN_GLASS));
			
			//3
			shopInvSellBuy.setItem(18, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(19, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(20, less);
			shopInvSellBuy.setItem(21, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(22, currentStack);
			shopInvSellBuy.setItem(23, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(24, more);
			shopInvSellBuy.setItem(25, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(26, new ItemStack(Material.THIN_GLASS));
			
			//4
			shopInvSellBuy.setItem(27, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(28, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(29, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(30, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(31, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(32, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(33, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(34, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(35, new ItemStack(Material.THIN_GLASS));
			
			//5
			shopInvSellBuy.setItem(36, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(37, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(38, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(39, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(40, backDoor);
			shopInvSellBuy.setItem(41, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(42, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(43, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(44, new ItemStack(Material.THIN_GLASS));
			
			player.openInventory(shopInvSellBuy);
			
			if(event.getCurrentItem().getType().equals(Material.ANVIL)) {
				int addStack = currentStack.getAmount();
				addStack --;
				currentStack.setAmount(addStack);
			}else if(event.getCurrentItem().getType().equals(Material.ARROW)) {
				int addStack = currentStack.getAmount();
				addStack ++;
				currentStack.setAmount(addStack);
				}
			}
		
			if(event.getCurrentItem().getType().equals(Material.NAME_TAG)) {
				Material objectToSell = event.getCurrentItem().getType();
			
				if(objectToSell.equals(Material.DIRT)) {
					double playerMoney = account.getHoldings().getBalance();
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " blocks de terre !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ dirtSellCount;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}		
				}else if(objectToSell.equals(Material.STONE)) {
					double playerMoney = account.getHoldings().getBalance();
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " blocks de pierre !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ stoneSellCount;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}
				}else if(objectToSell.equals(Material.SAND)) {
					double playerMoney = account.getHoldings().getBalance();
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " blocks de sable !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ sandSellCount;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}
				}
			}else if(event.getCurrentItem().getType().equals(Material.CHEST)) {
				Material objectToBuy = event.getCurrentItem().getType();

				if(objectToBuy.equals(Material.DIRT)) {
					double playerMoney = account.getHoldings().getBalance();
					if(dirtPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + dirtPrise + "$ pour acheter cette quantité de terre !");
					}else if(dirtPrise < account.getHoldings().getBalance()) {
						playerMoney =- dirtPrise;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}	
				}else if(objectToBuy.equals(Material.STONE)) {
					double playerMoney = account.getHoldings().getBalance();
					if(stonePrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + stonePrise + "$ pour acheter cette quantité de pierre !");
					}else if(stonePrise < account.getHoldings().getBalance()) {
						playerMoney =- stonePrise;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}
				}else if(objectToBuy.equals(Material.SAND)) {
					double playerMoney = account.getHoldings().getBalance();
					if(sandPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + sandPrise + "$ pour acheter cette quantité de sable !");
					}else if(sandPrise < account.getHoldings().getBalance()) {
						playerMoney =- sandPrise;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}		
				}	
			}	
		}
	
	//The void event for the utils shop inventory
	@SuppressWarnings("unused")
	public void onInventoryInterractUtils(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack currentStack = event.getCurrentItem();
		event.setCancelled(true);
		
		Account account = null;
		
		try {
			account = AccountManager.getAccount(player.getUniqueId().toString(), "default");
		} catch(AccountException e) {
			player.sendMessage(e.getMessage());
		}
		
		if(event.getClickedInventory() == shopInvUtils && event.getCursor().getType().isBlock() == true) {
			
			ItemStack stackChosen = event.getCurrentItem();
			
			currentStack = stackChosen;
			
			ItemStack more = new ItemStack(Material.ARROW);
			ItemMeta moreMeta = more.getItemMeta();
			moreMeta.setDisplayName("Plus");
			more.setItemMeta(moreMeta);
			
			ItemStack less = new ItemStack(Material.ANVIL);
			ItemMeta lessMeta = less.getItemMeta();
			lessMeta.setDisplayName("Moins");
			less.setItemMeta(lessMeta);
			
			ItemStack sell = new ItemStack(Material.NAME_TAG);
			ItemMeta sellMeta = sell.getItemMeta();
			sellMeta.setDisplayName("Vendre");
			sell.setItemMeta(sellMeta);
			
			ItemStack buy = new ItemStack(Material.CHEST);
			ItemMeta buyMeta = buy.getItemMeta();
			buyMeta.setDisplayName("Acheter");
			buy.setItemMeta(buyMeta);
			
			ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
			ItemMeta doorMeta = backDoor.getItemMeta();
			doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
			doorMeta.setDisplayName("§4Fermer / Retour");
			backDoor.setItemMeta(doorMeta);
			
			//1
			shopInvSellBuy.setItem(0, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(1, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(2, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(3, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(4, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(5, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(6, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(7, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(8, new ItemStack(Material.THIN_GLASS));
			
			//2
			shopInvSellBuy.setItem(9, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(10, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(11, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(12, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(13, event.getCurrentItem());
			shopInvSellBuy.setItem(14, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(15, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(16, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(17, new ItemStack(Material.THIN_GLASS));
			
			//3
			shopInvSellBuy.setItem(18, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(19, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(20, less);
			shopInvSellBuy.setItem(21, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(22, currentStack);
			shopInvSellBuy.setItem(23, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(24, more);
			shopInvSellBuy.setItem(25, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(26, new ItemStack(Material.THIN_GLASS));
			
			//4
			shopInvSellBuy.setItem(27, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(28, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(29, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(30, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(31, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(32, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(33, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(34, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(35, new ItemStack(Material.THIN_GLASS));
			
			//5
			shopInvSellBuy.setItem(36, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(37, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(38, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(39, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(40, backDoor);
			shopInvSellBuy.setItem(41, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(42, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(43, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(44, new ItemStack(Material.THIN_GLASS));
			
			player.openInventory(shopInvSellBuy);
			
			if(event.getCurrentItem().getType().equals(Material.ANVIL)) {
				int addStack = currentStack.getAmount();
				addStack --;
				currentStack.setAmount(addStack);
			}else if(event.getCurrentItem().getType().equals(Material.ARROW)) {
				int addStack = currentStack.getAmount();
				addStack ++;
				currentStack.setAmount(addStack);
				}
			}
		
			if(event.getCurrentItem().getType().equals(Material.NAME_TAG)) {
				Material objectToSell = event.getCurrentItem().getType();
			
				if(objectToSell.equals(Material.BONE)) {
					double playerMoney = account.getHoldings().getBalance();
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " blocks de terre !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ dirtSellCount;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}		
			}else if(event.getCurrentItem().getType().equals(Material.CHEST)) {
				Material objectToBuy = event.getCurrentItem().getType();

				if(objectToBuy.equals(Material.BONE)) {
					double playerMoney = account.getHoldings().getBalance();
					if(bonesPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + PrisesList.COAL.getBuyPrise() + "$ pour acheter cette quantité de terre !");
					}else if(dirtPrise < account.getHoldings().getBalance()) {
						playerMoney =- bonesPrise;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}
				}
			}
		}
	}
	
	//The void event for the plants shop inventory
	@SuppressWarnings("unused")
	public void onInventoryInterractPlants(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack currentStack = event.getCurrentItem();
		event.setCancelled(true);
		
		Account account = null;
		
		try {
			account = AccountManager.getAccount(player.getUniqueId().toString(), "default");
		} catch(AccountException e) {
			player.sendMessage(e.getMessage());
		}
		
		if(event.getClickedInventory() == shopInvPlants && event.getCursor().getType().isBlock() == true) {
				
			ItemStack stackChosen = event.getCurrentItem();
				
			currentStack = stackChosen;
		
			ItemStack more = new ItemStack(Material.ARROW);
			ItemMeta moreMeta = more.getItemMeta();
			moreMeta.setDisplayName("Plus");
			more.setItemMeta(moreMeta);
				
			ItemStack less = new ItemStack(Material.ANVIL);
			ItemMeta lessMeta = less.getItemMeta();
			lessMeta.setDisplayName("Moins");
			less.setItemMeta(lessMeta);
				
			ItemStack sell = new ItemStack(Material.NAME_TAG);
			ItemMeta sellMeta = sell.getItemMeta();
			sellMeta.setDisplayName("Vendre");
			sell.setItemMeta(sellMeta);
			
			ItemStack buy = new ItemStack(Material.CHEST);
			ItemMeta buyMeta = buy.getItemMeta();
			buyMeta.setDisplayName("Acheter");
			buy.setItemMeta(buyMeta);
				
			ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
			ItemMeta doorMeta = backDoor.getItemMeta();
			doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
			doorMeta.setDisplayName("§4Fermer / Retour");
			backDoor.setItemMeta(doorMeta);
				
			//1
			shopInvSellBuy.setItem(0, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(1, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(2, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(3, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(4, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(5, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(6, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(7, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(8, new ItemStack(Material.THIN_GLASS));
				
			//2
			shopInvSellBuy.setItem(9, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(10, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(11, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(12, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(13, event.getCurrentItem());
			shopInvSellBuy.setItem(14, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(15, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(16, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(17, new ItemStack(Material.THIN_GLASS));
				
			//3
			shopInvSellBuy.setItem(18, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(19, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(20, less);
			shopInvSellBuy.setItem(21, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(22, currentStack);
			shopInvSellBuy.setItem(23, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(24, more);
			shopInvSellBuy.setItem(25, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(26, new ItemStack(Material.THIN_GLASS));
				
			//4
			shopInvSellBuy.setItem(27, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(28, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(29, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(30, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(31, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(32, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(33, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(34, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(35, new ItemStack(Material.THIN_GLASS));
			
			//5
			shopInvSellBuy.setItem(36, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(37, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(38, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(39, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(40, backDoor);
			shopInvSellBuy.setItem(41, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(42, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(43, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(44, new ItemStack(Material.THIN_GLASS));
				
			player.openInventory(shopInvSellBuy);
				
			if(event.getCurrentItem().getType().equals(Material.ANVIL)) {
				int addStack = currentStack.getAmount();
				addStack --;
				currentStack.setAmount(addStack);
			}else if(event.getCurrentItem().getType().equals(Material.ARROW)) {
				int addStack = currentStack.getAmount();
				addStack ++;
				currentStack.setAmount(addStack);
				}
			}
			
			if(event.getCurrentItem().getType().equals(Material.NAME_TAG)) {
				Material objectToSell = event.getCurrentItem().getType();
				
				if(objectToSell.equals(Material.SUGAR_CANE)) {
					player.sendMessage("La canne à sucre n'est pas vendable.");		
				}else if(objectToSell.equals(Material.SEEDS)) {
					double playerMoney = account.getHoldings().getBalance();
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " graines de blé !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ seedsSellCount;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}
				}else if(objectToSell.equals(Material.MELON)) {
					player.sendMessage("La pastèque n'est pas vendable.");
				}else if(objectToSell.equals(Material.COCOA)) {
					player.sendMessage("La pastèque n'est pas vendable.");
				}else if(objectToSell.equals(Material.PUMPKIN)) {
					player.sendMessage("La pastèque n'est pas vendable.");
				}else if(objectToSell.equals(Material.CACTUS)) {
					player.sendMessage("La pastèque n'est pas vendable.");
				}
			}else if(event.getCurrentItem().getType().equals(Material.CHEST)) {
					Material objectToBuy = event.getCurrentItem().getType();

					if(objectToBuy.equals(Material.SUGAR_CANE)) {
						double playerMoney = account.getHoldings().getBalance();
						if(sugarCanePrise > playerMoney) {
							player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + sugarCanePrise + "$ pour acheter cette quantité de canne à sucre !");
						}else if(sugarCanePrise < account.getHoldings().getBalance()) {
							playerMoney =- sugarCanePrise;
							playerMoney = account.getHoldings().getBalance();
							player.getInventory().addItem(currentStack);
							player.openInventory(shopInvMain);
							
							player.updateInventory();
						}	
				}else if(objectToBuy.equals(Material.SEEDS)) {
					double playerMoney = account.getHoldings().getBalance();
					if(seedsPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + seedsPrise + "$ pour acheter cette quantité de graine de blé !");
					}else if(seedsPrise < account.getHoldings().getBalance()) {
						playerMoney =- seedsPrise;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}
				}else if(objectToBuy.equals(Material.MELON)) {
					double playerMoney = account.getHoldings().getBalance();
					if(melonPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + melonPrise + "$ pour acheter cette quantité de graine de blé !");
					}else if(melonPrise < account.getHoldings().getBalance()) {
						playerMoney =- melonPrise;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}
				}else if(objectToBuy.equals(Material.PUMPKIN)) {
					double playerMoney = account.getHoldings().getBalance();
					if(pumpkinPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + pumpkinPrise + "$ pour acheter cette quantité de graine de blé !");
					}else if(pumpkinPrise < account.getHoldings().getBalance()) {
						playerMoney =- pumpkinPrise;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}
				}else if(objectToBuy.equals(Material.CACTUS)) {
					double playerMoney = account.getHoldings().getBalance();
					if(cactusPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + cactusPrise + "$ pour acheter cette quantité de graine de blé !");
					}else if(cactusPrise < account.getHoldings().getBalance()) {
						playerMoney =- cactusPrise;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}
				}else if(objectToBuy.equals(Material.COCOA)) {
					double playerMoney = account.getHoldings().getBalance();
					if(cocoaPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + cocoaPrise + "$ pour acheter cette quantité de graine de blé !");
					}else if(cocoaPrise < account.getHoldings().getBalance()) {
						playerMoney =- cocoaPrise;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}
				}
			}	
		}
	
	//The void event for the minerals shop inventory
	@SuppressWarnings("unused")
	public void onInventoryInterractMinerals(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack currentStack = event.getCurrentItem();
		event.setCancelled(true);
		
		Account account = null;
		
		try {
			account = AccountManager.getAccount(player.getUniqueId().toString(), "default");
		} catch(AccountException e) {
			player.sendMessage(e.getMessage());
		}
		
		if(event.getClickedInventory() == shopInvMinerals && event.getCursor().getType().isBlock() == true) {
			
			ItemStack stackChosen = event.getCurrentItem();
			
			currentStack = stackChosen;
			
			ItemStack more = new ItemStack(Material.ARROW);
			ItemMeta moreMeta = more.getItemMeta();
			moreMeta.setDisplayName("Plus");
			more.setItemMeta(moreMeta);
			
			ItemStack less = new ItemStack(Material.ANVIL);
			ItemMeta lessMeta = less.getItemMeta();
			lessMeta.setDisplayName("Moins");
			less.setItemMeta(lessMeta);
			
			ItemStack sell = new ItemStack(Material.NAME_TAG);
			ItemMeta sellMeta = sell.getItemMeta();
			sellMeta.setDisplayName("Vendre");
			sell.setItemMeta(sellMeta);
			
			ItemStack buy = new ItemStack(Material.CHEST);
			ItemMeta buyMeta = buy.getItemMeta();
			buyMeta.setDisplayName("Acheter");
			buy.setItemMeta(buyMeta);
			
			ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
			ItemMeta doorMeta = backDoor.getItemMeta();
			doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arrière !"));
			doorMeta.setDisplayName("§4Fermer / Retour");
			backDoor.setItemMeta(doorMeta);
			
			//1
			shopInvSellBuy.setItem(0, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(1, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(2, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(3, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(4, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(5, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(6, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(7, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(8, new ItemStack(Material.THIN_GLASS));
			
			//2
			shopInvSellBuy.setItem(9, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(10, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(11, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(12, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(13, event.getCurrentItem());
			shopInvSellBuy.setItem(14, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(15, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(16, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(17, new ItemStack(Material.THIN_GLASS));
			
			//3
			shopInvSellBuy.setItem(18, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(19, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(20, less);
			shopInvSellBuy.setItem(21, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(22, currentStack);
			shopInvSellBuy.setItem(23, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(24, more);
			shopInvSellBuy.setItem(25, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(26, new ItemStack(Material.THIN_GLASS));
			
			//4
			shopInvSellBuy.setItem(27, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(28, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(29, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(30, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(31, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(32, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(33, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(34, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(35, new ItemStack(Material.THIN_GLASS));
			
			//5
			shopInvSellBuy.setItem(36, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(37, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(38, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(39, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(40, backDoor);
			shopInvSellBuy.setItem(41, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(42, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(43, new ItemStack(Material.THIN_GLASS));
			shopInvSellBuy.setItem(44, new ItemStack(Material.THIN_GLASS));
			
			player.openInventory(shopInvSellBuy);
			
			if(event.getCurrentItem().getType().equals(Material.ANVIL)) {
				int addStack = currentStack.getAmount();
				addStack --;
				currentStack.setAmount(addStack);
			}else if(event.getCurrentItem().getType().equals(Material.ARROW)) {
				int addStack = currentStack.getAmount();
				addStack ++;
				currentStack.setAmount(addStack);
				}
			}
		
			if(event.getCurrentItem().getType().equals(Material.NAME_TAG)) {
				Material objectToSell = event.getCurrentItem().getType();
			
				if(objectToSell.equals(Material.DIAMOND)) {
					double playerMoney = account.getHoldings().getBalance();
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " diamant(s) !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ diamondSellCount;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}		
				}else if(objectToSell.equals(Material.GOLD_INGOT)) {
					double playerMoney = account.getHoldings().getBalance();
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " lingot(s) d'or !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ goldSellCount;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}
				}else if(objectToSell.equals(Material.IRON_INGOT)) {
					double playerMoney = account.getHoldings().getBalance();
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " lingot(s) de fer !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ ironSellCount;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}
				}else if(objectToSell.equals(Material.COAL)) {
					double playerMoney = account.getHoldings().getBalance();
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " charbon(s)!");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ coalSellCount;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}
				}else if(objectToSell.equals(Material.EMERALD)) {
					double playerMoney = account.getHoldings().getBalance();
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " emeraude(s) !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ emeraldSellCount;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}
				}
			}else if(event.getCurrentItem().getType().equals(Material.CHEST)) {
				Material objectToBuy = event.getCurrentItem().getType();

				if(objectToBuy.equals(Material.DIAMOND)) {
					double playerMoney = account.getHoldings().getBalance();
					if(diamondPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + diamondPrise + "$ pour acheter cette quantité de diamant(s) !");
					}else if(diamondPrise < account.getHoldings().getBalance()) {
						playerMoney =- diamondPrise;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}	
				}else if(objectToBuy.equals(Material.GOLD_INGOT)) {
					double playerMoney = account.getHoldings().getBalance();
					if(goldPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + goldPrise + "$ pour acheter cette quantité de lingot(s) d'or !");
					}else if(goldPrise < account.getHoldings().getBalance()) {
						playerMoney =- goldPrise;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}
				}else if(objectToBuy.equals(Material.EMERALD)) {
					double playerMoney = account.getHoldings().getBalance();
					if(emeraldPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + emeraldPrise + "$ pour acheter cette quantité d'émeraude(s) !");
					}else if(emeraldPrise < account.getHoldings().getBalance()) {
						playerMoney =- emeraldPrise;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}		
				}else if(objectToBuy.equals(Material.IRON_INGOT)) {
					double playerMoney = account.getHoldings().getBalance();
					if(ironPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + ironPrise + "$ pour acheter cette quantité de ligot(s) de fer !");
					}else if(ironPrise < account.getHoldings().getBalance()) {
						playerMoney =- ironPrise;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}		
				}else if(objectToBuy.equals(Material.COAL)) {
					double playerMoney = account.getHoldings().getBalance();
					if(coalPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + coalPrise + "$ pour acheter cette quantité de charbon !");
					}else if(coalPrise < account.getHoldings().getBalance()) {
						playerMoney =- coalPrise;
						playerMoney = account.getHoldings().getBalance();
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}		
				}	
			}
	}
}
