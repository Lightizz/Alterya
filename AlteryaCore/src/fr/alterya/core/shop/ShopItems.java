package fr.alterya.core.shop;

import org.bukkit.Material;

public enum ShopItems
{	
	//Blocks
	DIRT(0.0, 0.0, 0, Material.DIRT, "Terre"),
	COBBLESTONE(0.0, 0.0, 1, Material.COBBLESTONE, "Pierre taillé"),
	STONE(0.0, 0.0, 2, Material.STONE, "Pierre"),
	GRAVEL(0.0, 0.0, 3, Material.GRAVEL, "Gravier"),
	SAND(0.0, 0.0, 4, Material.SAND, "Sable"),
	PACKED_ICE(0.0, 0.0, 6, Material.PACKED_ICE, "Glace compacté"),
	ICE(0.0, 0.0, 7, Material.ICE, "Glace"),
	WOOD(0.0, 0.0, 8, Material.WOOD, "Bois"),
	OBSIDIAN(0.0, 0.0, 13, Material.OBSIDIAN, "Obsidian"),
	GRASS(0.0, 0.0, 14, Material.GRASS, "Herbe"),
	
	//Plantes / loots (Farming)
	//Plantes
	MELON_SEEDS(0.0, 0.0, 21, Material.MELON_SEEDS, "Graines de melon"),
	PUMPKIN_SEEDS(0.0, 0.0, 22, Material.PUMPKIN_SEEDS, "Graines de citrouille"),
	SEEDS(0.0, 0.0, 23, Material.SEEDS, "Graines de blé"),
	CARROT(0.0, 0.0, 24, Material.CARROT_ITEM, "Carotte"),
	POTATO(0.0, 0.0, 25, Material.POTATO_ITEM, "Pomme de terre"),
	SUGAR_CANE(0.0, 0.0, 26, Material.SUGAR_CANE, "Canne à sucre"),
	APPLE(0.0, 0.0, 27, Material.APPLE, "Pomme rouge"),
	CACTUS(0.0, 0.0, 28, Material.CACTUS, "Cactus"),
	COCOA(0.0, 0.0, 29, Material.COCOA, "Cacao"),
	VINE(0.0, 0.0, 30, Material.VINE, "Vigne"),
	
	//Loots 
	STRING(0.0, 0.0, 31, Material.STRING, "File"),
	BLAZE_ROD(0.0, 0.0, 32, Material.BLAZE_ROD, "Baton de Blaze"),
	BONE(0.0, 0.0, 33, Material.BONE, "Os"),
	ROTTEN_FLESH(0.0, 0.0, 34, Material.ROTTEN_FLESH, "Viande de zombie"),
	GUNPOWDER(0.0, 0.0, 35, Material.SULPHUR, "Poudre à cannon"),
	ARROW(0.0, 0.0, 36, Material.ARROW, "Flèche"),
	LEATHER(0.0, 0.0, 37, Material.LEATHER, "Cuir"),
	EGG(0.0, 0.0, 38, Material.EGG, "Oeuf"),
	FEATHER(0.0, 0.0, 39, Material.FEATHER, "Plume"),
	SLIME_BALL(0.0, 0.0, 40, Material.SLIME_BALL, "Boule de slime"),
	
	//Alchimie 
	EMPTY_BOTTLE(0.0, 0.0, 41, Material.GLASS_BOTTLE, "Bouteille vide"),
	BREWING_STAND(0.0, 0.0, 42, Material.BREWING_STAND, "Alambic"),
	MAGMA_CREAM(0.0, 0.0, 43, Material.MAGMA_CREAM, "Crème de magma"),
	GOLDEN_MELON(0.0, 0.0, 44, Material.SPECKLED_MELON, "Melon en or"),
	GOLDEN_CARROT(0.0, 0.0, 45, Material.GOLDEN_CARROT, "Carotte en or"),
	GHAST_TEAR(0.0, 0.0, 46, Material.GHAST_TEAR, "Larme de ghast"),
	FERMENTED_SPIDER_EYE(0.0, 0.0, 47, Material.FERMENTED_SPIDER_EYE, "Oeil d'araignée fermenté"),
	RED_MUSHROOM(0.0, 0.0, 48, Material.RED_MUSHROOM, "Champignon rouge"),
	BROWN_MUSHROOM(0.0, 0.0, 49, Material.BROWN_MUSHROOM, "Champignon marron"),
	NETHER_WARTS(0.0, 0.0, 50, Material.NETHER_WARTS, "Verrues du nether"),
	GLOWSTONE(0.0, 0.0, 51, Material.GLOWSTONE_DUST, "Poudre lumineuse"),
	MILK_BUCKET(0.0, 0.0, 52, Material.MILK_BUCKET, "Seau de lait"),
	
	//Divers
	WOOL(0.0, 0.0, 58, Material.WOOL, "Laine blanche"),
	INK_SACK(0.0, 0.0, 59, Material.INK_SACK, "Poche d'encre"),
	COOKED_STEAK(0.0, 0.0, 67, Material.COOKED_BEEF, "Steak cuit"),
	COOKED_CHIKEN(0.0, 0.0, 68, Material.COOKED_CHICKEN, "Cuisse de poulet cuite"),
	COOKED_PORCKCHOP(0.0, 0.0, 69, Material.GRILLED_PORK, "Côte de porc cuite"),
	
	//Minerais
	COAL(0.0, 0.0, 15, Material.COAL, "Charbon"),
	EMERALD(0.0, 0.0, 16, Material.EMERALD, "Emeraude"),
	IRON(0.0, 0.0, 17, Material.IRON_INGOT, "Lingot de fer"),
	GOLD(0.0, 0.0, 18, Material.GOLD_INGOT, "Or"),
	DIAMOND(0.0, 0.0, 19, Material.DIAMOND, "Diamant"),
	REDSTONE(0.0, 0.0, 20, Material.REDSTONE, "Poudre de redstone"),
	
	//Pillage
	SOUL_SAND(0.0, 0.0, 53, Material.SOUL_SAND, "Sable des âmes"),
	WITHER_SKULL(0.0, 0.0, 54, Material.SKULL, "Tête de Wither"),
	ENDER_PEARL(0.0, 0.0, 57, Material.ENDER_PEARL, "Perle de l'ender");
	
	private final double buyPrise, sellPrise;
	private final int id;
	private final Material itemMaterial;
	private final String name;
	
	private ShopItems(double buyPrise, double sellPrise, int id, Material itemMaterial, String name) {
		this.buyPrise = buyPrise;
		this.sellPrise = sellPrise;
		this.id = id;
		this.itemMaterial = itemMaterial;
		this.name = name;
	}
	
	//Methode pour récupérer les infos sur les item présentable dans le shop
	public double getBuyPriseS() {
		return buyPrise;
	}
	public double getSellPriseS() {
		return sellPrise;
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
	public String getName() {
		return name;
	}
}
