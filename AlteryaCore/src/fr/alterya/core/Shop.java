package fr.alterya.core;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.alterya.core.money.Money;

/*
Author and resp of the shop: Lightiz
*/

public class Shop implements Listener {

	Money money;
	
	public Shop (){
		//Set all items in their inventory to prepare the shop
		setItemsInShopMain();
		setItemInShopBlocks();
		setItemInShopPlants();
		setItemInShopUtils();
		setItemInShopMinerals();
		setItemsInShopSellBuy();
	}

	//Create all inventorys
	public Inventory shopInvMain = Bukkit.createInventory(null, 5*9, "�dShop");
	
	public Inventory shopInvBlocks = Bukkit.createInventory(null, 5*9, "�eBlocks");
	public Inventory shopInvUtils = Bukkit.createInventory(null, 5*9, "�eUtils");
	public Inventory shopInvPlants = Bukkit.createInventory(null, 5*9, "�ePlants");
	public Inventory shopInvMinerals = Bukkit.createInventory(null, 5*9, "�eMinerals");
	
	public Inventory shopInvSellBuy = Bukkit.createInventory(null, 5*9, "�eSell / Buy items");
	
	//Blocks prises
	public float stonePrise = 0.1f;
	public float stoneSellCount = 0f;
	
	public float dirtPrise = 0.1f;
	public float dirtSellCount = 0f;
	
	public float sandPrise = 0.1f;
	public float sandSellCount = 0f;
	
	//Utils prises
	public float bonesPrise = 150f;
	public float bonesSellCount = 75f;
	
	//Minerals prises
	public float diamondPrise = 5000f;
	public float diamondSellCount = 1500f;
	
	public float goldPrise = 2500f;
	public float goldSellCount = 1000f;
	
	public float emeraldPrise = 3250f;
	public float emeraldSellCount = 2250f;
	
	public float ironPrise = 850f;
	public float ironSellCount = 400f;
	
	public float coalPrise = 50f;
	public float coalSellCount = 5f;
	
	//Plants prises
	public float seedsPrise = 2.5f;
	public float seedsSellCount = 1f;
	
	public float sugarCanePrise = 10f;
	public float sugarCaneSellCount = 5.5f;
	
	//Set items in main page of the shop
	public void setItemsInShopMain() {
		ItemStack blocks = new ItemStack(Material.STONE);
		ItemMeta blocksMeta = blocks.getItemMeta();
		blocksMeta.setDisplayName("�1Blocks / D�corations");
		blocksMeta.setLore(Arrays.asList("Ici, vous trouverez des blocks de toutes sortes et des blocks d�coratifs !", "Clickez pour plus d'informations !"));
		blocks.setItemMeta(blocksMeta);
		
		ItemStack minerals = new ItemStack(Material.IRON_INGOT);
		ItemMeta mineralsMeta = minerals.getItemMeta();
		mineralsMeta.setDisplayName("�1Minerais");
		mineralsMeta.setLore(Arrays.asList("Ici, vous trouverez tout les minerais !", "Clickez pour plus d'informations !"));
		minerals.setItemMeta(mineralsMeta);
		
		ItemStack plants = new ItemStack(Material.YELLOW_FLOWER);
		ItemMeta plantsMeta = plants.getItemMeta();
		plantsMeta.setDisplayName("�1Plantes et v�g�tations");
		plantsMeta.setLore(Arrays.asList("Ici, vous trouverez des blocks de toutes sortes et des blocks d�coratifs !", "Clickez pour plus d'informations !"));
		plants.setItemMeta(plantsMeta);
		
		ItemStack utils = new ItemStack(Material.EMPTY_MAP);
		ItemMeta utilsMeta = utils.getItemMeta();
		utilsMeta.setDisplayName("�1Items sans cat�gorie attribu�e");
		utilsMeta.setLore(Arrays.asList("Ici, vous trouverez des items de toutes sortes confondues mais qui ne sont ni des blocks ni des plantes ni des min�raux !", "Clickez pour plus d'informations !"));
		utils.setItemMeta(utilsMeta);
		
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arri�re !"));
		doorMeta.setDisplayName("�4Fermer / Retour");
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
		stoneMeta.setLore(Arrays.asList("�2Prix d'achat / u : " + stonePrise + "$", "Prix de vente / u :" + stoneSellCount + "$"));
		stone.setItemMeta(stoneMeta);
		
		ItemStack dirt = new ItemStack(Material.DIRT);
		ItemMeta dirtMeta = dirt.getItemMeta();
		dirtMeta.setDisplayName("Terre");
		dirtMeta.setLore(Arrays.asList("�2Prix d'achat / u : " + dirtPrise + "$", "Prix de vente / u :" + dirtSellCount + "$"));
		dirt.setItemMeta(dirtMeta);
		
		ItemStack sand = new ItemStack(Material.SAND);
		ItemMeta sandMeta = sand.getItemMeta();
		sandMeta.setDisplayName("Sable");
		sandMeta.setLore(Arrays.asList("�2Prix d'achat / u : " + sandPrise + "$", "Prix de vente / u :" + sandSellCount + "$"));
		sand.setItemMeta(sandMeta);
		
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arri�re !"));
		doorMeta.setDisplayName("�4Fermer / Retour");
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
		diamondMeta.setLore(Arrays.asList("�2Prix d'achat / u : " + diamondPrise + "$", "Prix de vente / u : " + diamondSellCount + "$"));
		diamond.setItemMeta(diamondMeta);
		
		ItemStack gold = new ItemStack(Material.GOLD_INGOT);
		ItemMeta goldMeta = gold.getItemMeta();
		goldMeta.setDisplayName("Lingot d'or");
		goldMeta.setLore(Arrays.asList("�2Prix d'achat / u : " + goldPrise + "$", "Prix de vente / u : " + goldSellCount + "$"));
		gold.setItemMeta(goldMeta);
		
		ItemStack emerald = new ItemStack(Material.EMERALD);
		ItemMeta emeraldMeta = emerald.getItemMeta();
		emeraldMeta.setDisplayName("Emeraude");
		emeraldMeta.setLore(Arrays.asList("�2Prix d'achat / u : " + emeraldPrise + "$", "Prix de vente / u : " + emeraldSellCount + "$"));
		emerald.setItemMeta(emeraldMeta);
		
		ItemStack iron = new ItemStack(Material.IRON_INGOT);
		ItemMeta ironMeta = iron.getItemMeta();
		ironMeta.setDisplayName("Lingot de fer");
		ironMeta.setLore(Arrays.asList("�2Prix d'achat / u : " + ironPrise + "$", "Prix de vente / u : " + ironSellCount + "$"));
		iron.setItemMeta(ironMeta);
		
		ItemStack coal = new ItemStack(Material.COAL);
		ItemMeta coalMeta = coal.getItemMeta();
		coalMeta.setDisplayName("Charbon");
		coalMeta.setLore(Arrays.asList("�2Prix d'achat / u : " + coalPrise + "$", "Prix de vente / u : " + coalSellCount + "$"));
		coal.setItemMeta(coalMeta);
		
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arri�re !"));
		doorMeta.setDisplayName("�4Fermer / Retour");
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
		bonesMeta.setLore(Arrays.asList("�2Prix d'achat / u : " + bonesPrise + "$", "Prix de vente / u : " + bonesSellCount + "$"));
		bones.setItemMeta(bonesMeta);
		
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arri�re !"));
		doorMeta.setDisplayName("�4Fermer / Retour");
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
		seedsMeta.setLore(Arrays.asList("�2Prix d'achat / u : " + seedsPrise + "$", "Prix de vente / u :" + seedsSellCount + "$"));
		seedsMeta.setDisplayName("Graines de bl�");
		seeds.setItemMeta(seedsMeta);
		
		ItemStack sugarCan = new ItemStack(Material.SUGAR_CANE);
		ItemMeta sugarCanMeta = sugarCan.getItemMeta();
		sugarCanMeta.setLore(Arrays.asList("�2Prix d'achat / u : " + sugarCanePrise + "$", "Prix de vente / u :" + sugarCaneSellCount + "$"));
		sugarCanMeta.setDisplayName("Canne � sucre");
		sugarCan.setItemMeta(sugarCanMeta);
		
		ItemStack backDoor = new ItemStack(Material.WOODEN_DOOR);
		ItemMeta doorMeta = backDoor.getItemMeta();
		doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arri�re !"));
		doorMeta.setDisplayName("�4Fermer / Retour");
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
			doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arri�re !"));
			doorMeta.setDisplayName("�4Fermer / Retour");
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
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " blocks de terre !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ dirtSellCount;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}		
				}else if(objectToSell.equals(Material.STONE)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " blocks de pierre !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ stoneSellCount;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}
				}else if(objectToSell.equals(Material.SAND)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " blocks de sable !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ sandSellCount;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}
				}
			}else if(event.getCurrentItem().getType().equals(Material.CHEST)) {
				Material objectToBuy = event.getCurrentItem().getType();

				if(objectToBuy.equals(Material.DIRT)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(dirtPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + dirtPrise + "$ pour acheter cette quantit� de terre !");
					}else if(dirtPrise < money.getPlayerMoney(player.getUniqueId().toString())) {
						playerMoney =- dirtPrise;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}	
				}else if(objectToBuy.equals(Material.STONE)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(stonePrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + stonePrise + "$ pour acheter cette quantit� de pierre !");
					}else if(stonePrise < money.getPlayerMoney(player.getUniqueId().toString())) {
						playerMoney =- stonePrise;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}
				}else if(objectToBuy.equals(Material.SAND)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(sandPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + sandPrise + "$ pour acheter cette quantit� de sable !");
					}else if(sandPrise < money.getPlayerMoney(player.getUniqueId().toString())) {
						playerMoney =- sandPrise;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
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
			doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arri�re !"));
			doorMeta.setDisplayName("�4Fermer / Retour");
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
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " blocks de terre !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ dirtSellCount;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}		
			}else if(event.getCurrentItem().getType().equals(Material.CHEST)) {
				Material objectToBuy = event.getCurrentItem().getType();

				if(objectToBuy.equals(Material.BONE)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(bonesPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + dirtPrise + "$ pour acheter cette quantit� de terre !");
					}else if(dirtPrise < money.getPlayerMoney(player.getUniqueId().toString())) {
						playerMoney =- bonesPrise;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}
				}
			}
		}
	}
	
	
	
	//The void event for the minerals shop inventory
	
	//The void event for the plants shop inventory
	@SuppressWarnings("unused")
	public void onInventoryInterractPlants(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack currentStack = event.getCurrentItem();
		event.setCancelled(true);
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
			doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arri�re !"));
			doorMeta.setDisplayName("�4Fermer / Retour");
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
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " canne(s) � sucre !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ sugarCaneSellCount;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}		
				}else if(objectToSell.equals(Material.SEEDS)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " graines de bl� !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ seedsSellCount;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}
			}else if(event.getCurrentItem().getType().equals(Material.CHEST)) {
				Material objectToBuy = event.getCurrentItem().getType();

				if(objectToBuy.equals(Material.SUGAR_CANE)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(sugarCanePrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + sugarCanePrise + "$ pour acheter cette quantit� de canne � sucre !");
					}else if(sugarCanePrise < money.getPlayerMoney(player.getUniqueId().toString())) {
						playerMoney =- sugarCanePrise;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
							
						player.updateInventory();
					}	
				}else if(objectToBuy.equals(Material.SEEDS)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(seedsPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + seedsPrise + "$ pour acheter cette quantit� de graine de bl� !");
					}else if(seedsPrise < money.getPlayerMoney(player.getUniqueId().toString())) {
						playerMoney =- seedsPrise;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}
				}
			}	
		}
	}
	
	//The void event for the minerals shop inventory
	//The void event for the utils shop inventory
	@SuppressWarnings("unused")
	public void onInventoryInterractMinerals(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack currentStack = event.getCurrentItem();
		event.setCancelled(true);
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
			doorMeta.setLore(Arrays.asList("Clickez pour fermer / retourner en arri�re !"));
			doorMeta.setDisplayName("�4Fermer / Retour");
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
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " diamant(s) !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ diamondSellCount;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}		
				}else if(objectToSell.equals(Material.GOLD_INGOT)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " lingot(s) d'or !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ goldSellCount;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}
				}else if(objectToSell.equals(Material.IRON_INGOT)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " lingot(s) de fer !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ ironSellCount;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}
				}else if(objectToSell.equals(Material.COAL)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " charbon(s)!");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ coalSellCount;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}
				}else if(objectToSell.equals(Material.EMERALD)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(! player.getInventory().contains(currentStack)) {
						player.sendMessage("Vous devez avoir " + currentStack.getAmount() + " emeraude(s) !");
					}else if(player.getInventory().contains(currentStack)) {
						playerMoney =+ emeraldSellCount;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().remove(currentStack);
						player.openInventory(shopInvMain);
					}
				}
			}else if(event.getCurrentItem().getType().equals(Material.CHEST)) {
				Material objectToBuy = event.getCurrentItem().getType();

				if(objectToBuy.equals(Material.DIAMOND)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(diamondPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + diamondPrise + "$ pour acheter cette quantit� de diamant(s) !");
					}else if(diamondPrise < money.getPlayerMoney(player.getUniqueId().toString())) {
						playerMoney =- diamondPrise;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}	
				}else if(objectToBuy.equals(Material.GOLD_INGOT)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(goldPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + goldPrise + "$ pour acheter cette quantit� de lingot(s) d'or !");
					}else if(goldPrise < money.getPlayerMoney(player.getUniqueId().toString())) {
						playerMoney =- goldPrise;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}
				}else if(objectToBuy.equals(Material.EMERALD)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(emeraldPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + emeraldPrise + "$ pour acheter cette quantit� d'�meraude(s) !");
					}else if(emeraldPrise < money.getPlayerMoney(player.getUniqueId().toString())) {
						playerMoney =- emeraldPrise;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}		
				}else if(objectToBuy.equals(Material.IRON_INGOT)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(ironPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + ironPrise + "$ pour acheter cette quantit� de ligot(s) de fer !");
					}else if(ironPrise < money.getPlayerMoney(player.getUniqueId().toString())) {
						playerMoney =- ironPrise;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}		
				}else if(objectToBuy.equals(Material.COAL)) {
					double playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
					if(coalPrise > playerMoney) {
						player.sendMessage("Vous n'avez pas asser de money ! Vous devez avoir " + coalPrise + "$ pour acheter cette quantit� de charbon !");
					}else if(coalPrise < money.getPlayerMoney(player.getUniqueId().toString())) {
						playerMoney =- coalPrise;
						playerMoney = money.getPlayerMoney(player.getUniqueId().toString());
						player.getInventory().addItem(currentStack);
						player.openInventory(shopInvMain);
						
						player.updateInventory();
					}		
				}	
			}
	}
}