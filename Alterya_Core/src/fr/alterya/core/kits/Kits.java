package fr.alterya.core.kits;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import fr.alterya.core.util.ItemBuilder;

public class Kits
{
	public static void giveSageKit(Player player) {
		ItemStack kitBoots = new ItemStack(Material.IRON_BOOTS);
		ItemMeta bootsMeta = kitBoots.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Bottes de Kit");
		bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		bootsMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		kitBoots.setItemMeta(bootsMeta);
		
		ItemStack kitChestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta chestplateMeta = kitChestplate.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Plastron de Kit");
		chestplateMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		chestplateMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		kitChestplate.setItemMeta(chestplateMeta);
		
		ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta leggingsMeta = leggings.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Jambières de Kit");
		leggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		leggingsMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		leggings.setItemMeta(leggingsMeta);
		
		ItemStack helmet = new ItemStack(Material.IRON_HELMET);
		ItemMeta helmetMeta = helmet.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Casque de Kit");
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, true);
		helmetMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		helmet.setItemMeta(helmetMeta);
		
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.setDisplayName(ChatColor.RED + "Épée de Kit");
		swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
		swordMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		sword.setItemMeta(swordMeta);
		
		ItemStack goldenApple = new ItemStack((Material.GOLDEN_APPLE), 5);
		ItemStack cookedSteak = new ItemStack((Material.COOKED_BEEF), 32);
		
		ItemStack potion = new ItemStack(Material.POTION, 10);
        PotionMeta potionmeta = (PotionMeta) potion.getItemMeta();
        potionmeta.setMainEffect(PotionEffectType.HEAL);
        PotionEffect heal = new PotionEffect(PotionEffectType.HEAL, 1000, 1);
        potionmeta.addCustomEffect(heal, true);
        potionmeta.setDisplayName("Potion de Kit");
        potion.setItemMeta(potionmeta);
        Potion p = new Potion(PotionType.INSTANT_HEAL);
        p.setSplash(true);
        potion = p.toItemStack(10);

        player.getInventory().addItem(potion);
		player.getInventory().addItem(cookedSteak);
		player.getInventory().addItem(sword);
		player.getInventory().addItem(helmet);
		player.getInventory().addItem(kitChestplate);
		player.getInventory().addItem(leggings);
		player.getInventory().addItem(kitBoots);
		player.getInventory().addItem(goldenApple);
		player.updateInventory();
	}
	
	public static void giveMemoireKit(Player player) {
		ItemStack kitBoots = new ItemStack(Material.IRON_BOOTS);
		ItemMeta bootsMeta = kitBoots.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Bottes de Kit");
		bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		bootsMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		kitBoots.setItemMeta(bootsMeta);
		
		ItemStack kitChestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta chestplateMeta = kitChestplate.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Plastron de Kit");
		bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		bootsMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		kitChestplate.setItemMeta(chestplateMeta);
		
		ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta leggingsMeta = leggings.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Jambières de Kit");
		leggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		leggingsMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		leggings.setItemMeta(leggingsMeta);
		
		ItemStack helmet = new ItemStack(Material.IRON_HELMET);
		ItemMeta helmetMeta = helmet.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Casque de Kit");
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		helmetMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		helmet.setItemMeta(helmetMeta);
		
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.setDisplayName(ChatColor.RED + "Épée de kit");
		swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		swordMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		sword.setItemMeta(swordMeta);
		
		ItemStack goldenApple = new ItemStack((Material.GOLDEN_APPLE), 5);
		ItemStack cookedSteak = new ItemStack((Material.COOKED_BEEF), 16);
		
		player.getInventory().addItem(helmet);
		player.getInventory().addItem(leggings);
		player.getInventory().addItem(kitChestplate);
		player.getInventory().addItem(kitBoots);
		player.getInventory().addItem(goldenApple);
		player.getInventory().addItem(cookedSteak);
		player.updateInventory();
	}

	public static void giveSouvenirKit(Player player) {
		ItemStack kitBoots = new ItemStack(Material.IRON_BOOTS);
		ItemMeta bootsMeta = kitBoots.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Bottes de Kit");
		bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		bootsMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		kitBoots.setItemMeta(bootsMeta);
		
		ItemStack kitChestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta chestplateMeta = kitChestplate.getItemMeta();
		chestplateMeta.setDisplayName(ChatColor.RED + "Plastron de Kit");
		chestplateMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		chestplateMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		kitChestplate.setItemMeta(chestplateMeta);
		
		ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta leggingsMeta = leggings.getItemMeta();
		leggingsMeta.setDisplayName(ChatColor.RED + "Jambières de Kit");
		leggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		leggingsMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		leggings.setItemMeta(leggingsMeta);
		
		ItemStack helmet = new ItemStack(Material.IRON_HELMET);
		ItemMeta helmetMeta = helmet.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Casque de Kit");
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		helmetMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		helmet.setItemMeta(helmetMeta);
		
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.setDisplayName(ChatColor.RED + "Épée de kit");
		swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		swordMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		sword.setItemMeta(swordMeta);
		
		ItemStack goldenApple = new ItemStack((Material.GOLDEN_APPLE), 2);
		ItemStack cookedSteak = new ItemStack((Material.COOKED_BEEF), 10);
		
		player.getInventory().addItem(helmet);
		player.getInventory().addItem(leggings);
		player.getInventory().addItem(kitChestplate);
		player.getInventory().addItem(kitBoots);
		player.getInventory().addItem(goldenApple);
		player.getInventory().addItem(cookedSteak);
		player.updateInventory();
	}

	public static void givePVPKit(Player player) {
		ItemStack kitBoots = new ItemStack(Material.IRON_BOOTS);
		ItemMeta bootsMeta = kitBoots.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Bottes de Kit");
		bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		bootsMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		kitBoots.setItemMeta(bootsMeta);
		
		ItemStack kitChestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta chestplateMeta = kitChestplate.getItemMeta();
		chestplateMeta.setDisplayName(ChatColor.RED + "Plastron de Kit");
		chestplateMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		chestplateMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		kitChestplate.setItemMeta(chestplateMeta);
		
		ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta leggingsMeta = leggings.getItemMeta();
		leggingsMeta.setDisplayName(ChatColor.RED + "Jambières de Kit");
		leggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		leggingsMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		leggings.setItemMeta(leggingsMeta);
		
		ItemStack helmet = new ItemStack(Material.IRON_HELMET);
		ItemMeta helmetMeta = helmet.getItemMeta();
		helmetMeta.setDisplayName(ChatColor.RED + "Casque de Kit");
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		helmetMeta.addEnchant(Enchantment.DURABILITY, 2, true);
		helmet.setItemMeta(helmetMeta);
		
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.setDisplayName(ChatColor.RED + "Épée de kit");
		swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
		sword.setItemMeta(swordMeta);
		
		ItemStack goldenApple = new ItemStack((Material.GOLDEN_APPLE), 4);
		ItemStack cookedSteak = new ItemStack((Material.COOKED_BEEF), 10);
		
		ItemStack potion = new ItemStack(Material.POTION, 12);
        PotionMeta potionmeta = (PotionMeta) potion.getItemMeta();
        potionmeta.setMainEffect(PotionEffectType.HEAL);
        PotionEffect heal = new PotionEffect(PotionEffectType.HEAL, 1000, 1);
        potionmeta.addCustomEffect(heal, true);
        potionmeta.setDisplayName("Potion de Kit");
        potion.setItemMeta(potionmeta);
        Potion p = new Potion(PotionType.INSTANT_HEAL);
        p.setSplash(true);
        potion = p.toItemStack(12);
        
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bowMeta = bow.getItemMeta();
        bowMeta.setDisplayName("Arc de Kit");
        bowMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        bow.setItemMeta(bowMeta);

        ItemStack arrows = ItemBuilder.createItem(Material.ARROW, "Flèches de Kit", 64);
        ItemStack enderPearls = ItemBuilder.createItem(Material.ENDER_PEARL, "Perle de l'end de Kit", 8);
        
        player.getInventory().addItem(enderPearls);
        player.getInventory().addItem(arrows);
        player.getInventory().addItem(bow);
        player.getInventory().addItem(potion);
		player.getInventory().addItem(helmet);
		player.getInventory().addItem(leggings);
		player.getInventory().addItem(kitChestplate);
		player.getInventory().addItem(kitBoots);
		player.getInventory().addItem(goldenApple);
		player.getInventory().addItem(cookedSteak);
		player.updateInventory();
	}

	public static void giveEnchantmentKit(Player player) {
		
	}
	
	public static void giveAlchemyKit(Player player) {
		
	}

	public static void giveDuidKit(Player player) {
	
	}

	public static void giveSurvivalKit(Player player) {
	
	}
	
	public static void giveMinageKit(Player player) {
		
	}
	
	public static void giveBuilderKit(Player player) {
		
	}
	
	public static void givePillageKit(Player player) {
		
	}

	public static void giveFarmerKit(Player player) {
	
	}
	
	public static void giveEleveurKit(Player player) {
		
	}
	
	public static void giveExplorerKit(Player player) {
		
	}
	
	public static void giveGathererKit(Player player) {
		
	}
}
