package fr.alterya.core.shop;

import org.bukkit.Material;

public enum PrisesList
{	
	//Blocks
	DIRT(0.0, 0.0, 0, Material.DIRT),
	COBBLESTONE(0.0, 0.0, 1, Material.COBBLESTONE),
	STONE(0.0, 0.0, 2, Material.STONE),
	GRAVEL(0.0, 0.0, 3, Material.GRAVEL),
	SAND(0.0, 0.0, 4, Material.SAND),
	//REDSAND(0.0, 0.0, 5, Material.RED_SAND),
	PACKED_ICE(0.0, 0.0, 6, Material.PACKED_ICE),
	ICE(0.0, 0.0, 7, Material.ICE),
	WOOD(0.0, 0.0, 8, Material.WOOD),
	//BIRCH_WOOD(0.0, 0.0, 9, Material.BIRCH_WOOD),
	//DARK_OAK_WOOD(0.0, 0.0, 10, Material.DARK_OAK_WOOD),
	//ACCACIA_WOOD(0.0, 0.0, 11, Material.ACCACIA_WOOD),
	//JUNGLE_WOOD(0.0, 0.0, 12, Material.BIRCH_WOOD),
	OBSIDIAN(0.0, 0.0, 13, Material.OBSIDIAN),
	GRASS(0.0, 0.0, 14, Material.GRASS),
	
	//Plantes / loots (Farming)
	//Plantes
	MELON_SEEDS(0.0, 0.0, 21, Material.MELON_SEEDS),
	PUMPKIN_SEEDS(0.0, 0.0, 22, Material.PUMPKIN_SEEDS),
	SEEDS(0.0, 0.0, 23, Material.SEEDS),
	CARROT(0.0, 0.0, 24, Material.CARROT),
	POTATO(0.0, 0.0, 25, Material.POTATO),
	SUGAR_CAN(0.0, 0.0, 26, Material.SUGAR_CANE),
	APPLE(0.0, 0.0, 27, Material.APPLE),
	CACTUS(0.0, 0.0, 28, Material.CACTUS),
	COCOA(0.0, 0.0, 29, Material.COCOA),
	VINE(0.0, 0.0, 30, Material.VINE),
	
	//Loots 
	STRING(0.0, 0.0, 31, Material.STRING),
	BLAZE_ROD(0.0, 0.0, 32, Material.BLAZE_ROD),
	BONE(0.0, 0.0, 33, Material.BONE),
	ROTTEN_FLESH(0.0, 0.0, 34, Material.ROTTEN_FLESH),
	GUNPOWDER(0.0, 0.0, 35, Material.SULPHUR),
	ARROW(0.0, 0.0, 36, Material.ARROW),
	LEATHER(0.0, 0.0, 37, Material.LEATHER),
	EGG(0.0, 0.0, 38, Material.EGG),
	FEATHER(0.0, 0.0, 39, Material.FEATHER),
	SLIME_BALL(0.0, 0.0, 40, Material.SLIME_BALL),
	
	//Alchimie 
	EMPTY_BOTTLE(0.0, 0.0, 41, Material.GLASS_BOTTLE),
	BREWING_STAND(0.0, 0.0, 42, Material.BREWING_STAND),
	MAGMA_CREAM(0.0, 0.0, 43, Material.MAGMA_CREAM),
	GOLDEN_MELON(0.0, 0.0, 44, Material.SPECKLED_MELON),
	GOLDEN_CARROT(0.0, 0.0, 45, Material.GOLDEN_CARROT),
	GHAST_TEAR(0.0, 0.0, 46, Material.GHAST_TEAR),
	FERMENTED_SPIDER_EYE(0.0, 0.0, 47, Material.FERMENTED_SPIDER_EYE),
	RED_MUSHROOM(0.0, 0.0, 48, Material.RED_MUSHROOM),
	BROWN_MUSHROOM(0.0, 0.0, 49, Material.BROWN_MUSHROOM),
	NETHER_WARTS(0.0, 0.0, 50, Material.NETHER_WARTS),
	GLOWSTONE(0.0, 0.0, 51, Material.GLOWSTONE_DUST),
	MILK_BUCKET(0.0, 0.0, 52, Material.MILK_BUCKET),
	
	//Divers
	WOOL(0.0, 0.0, 58, Material.WOOL),
	INK_SACK(0.0, 0.0, 59, Material.INK_SACK),
	//GREEN_DYE(0.0, 0.0, 60, ),
	//LIGHT_GREEN_DYE(0.0, 0.0, 61, ),
	//LIGHT_BLEUE_DYE(0.0, 0.0, 62, ),
	//CYAN_DYE(0.0, 0.0, 63, ),
	//RED_DYE(0.0, 0.0, 64, ),
	//ORANGE_DYE(0.0, 0.0, 65, ),
	//YELLOW_DYE(0.0, 0.0, 66, ),
	COOKED_STEAK(0.0, 0.0, 67, Material.COOKED_BEEF),
	COOKED_CHIKEN(0.0, 0.0, 68, Material.COOKED_CHICKEN),
	COOKED_PORCKCHOP(0.0, 0.0, 69, Material.GRILLED_PORK),
	
	//Minerais
	COAL(0.0, 0.0, 15, Material.COAL),
	EMERALD(0.0, 0.0, 16, Material.EMERALD),
	IRON(0.0, 0.0, 17, Material.IRON_INGOT),
	GOLD(0.0, 0.0, 18, Material.GOLD_INGOT),
	DIAMOND(0.0, 0.0, 19, Material.DIAMOND),
	REDSTONE(0.0, 0.0, 20, Material.REDSTONE),
	
	//Pillage
	SOUL_SAND(0.0, 0.0, 53, Material.SOUL_SAND),
	WITHER_SKULL(0.0, 0.0, 54, Material.SKULL),
	CREEPER_EGG(0.0, 0.0, 55, Material.MOB_SPAWNER),
	TNT(0.0, 0.0, 56, Material.TNT),
	ENDER_PEARL(0.0, 0.0, 57, Material.ENDER_PEARL);
	
	private final double buyPrise, sellPrise;
	private final int id;
	private final Material itemMaterial;
	
	private PrisesList(double buyPrise, double sellPrise, int id, Material itemMaterial) {
		this.buyPrise = buyPrise;
		this.sellPrise = sellPrise;
		this.id = id;
		this.itemMaterial = itemMaterial;
	}
	
	public String getBuyPriseS() {
		return Double.toString(buyPrise);
	}
	public String getSellPriseS() {
		return Double.toString(sellPrise);
	}
	public double getBuyPrise() {
		return buyPrise;
	}
	public double getSellPrise() {
		return sellPrise;
	}
	public int getID() {
		return id;
	}
	public Material getMaterial() {
		return itemMaterial;
	}
}
