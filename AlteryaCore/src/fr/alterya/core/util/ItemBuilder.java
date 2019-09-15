package fr.alterya.core.util;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder
{
	public static ItemStack createItem(Material material) {
		ItemStack item = new ItemStack(material);
		return item;
	}
	
	public static ItemStack createItem(Material material, int amount) {
		ItemStack item = new ItemStack(material);
		item.setAmount(amount);
		return item;
	}
	
	public static ItemStack createItem(Material material, int amount, int b) {
		ItemStack item = new ItemStack(material, (byte) b);
		item.setAmount(amount);
		return item;
	}
	
	public static ItemStack createItem(String dislayName, Material material) {
		ItemStack item = new ItemStack(material);
		ItemMeta itemM = item.getItemMeta();
		itemM.setDisplayName(dislayName);
		item.setItemMeta(itemM);
		return item;
	}
	
	public static ItemStack createItem(Material material, String dislayName, List<String> lores) {
		ItemStack item = new ItemStack(material);
		ItemMeta itemM = item.getItemMeta();
		itemM.setDisplayName(dislayName);
		itemM.setLore(lores);
		item.setItemMeta(itemM);
		return item;
	}
	
	public static ItemStack createItem(Material material, String dislayName, List<String> lores, int amount) {
		ItemStack item = new ItemStack(material);
		ItemMeta itemM = item.getItemMeta();
		itemM.setDisplayName(dislayName);
		itemM.setLore(lores);
		item.setItemMeta(itemM);
		if(amount == 1) {
			item.setAmount(0);
		}
		return item;
	}
	
	public static ItemStack createItem(Material material, String[] lores) {
		ItemStack item = new ItemStack(material);
		ItemMeta itemM = item.getItemMeta();
		itemM.setLore(Arrays.asList(lores));
		item.setItemMeta(itemM);
		return item;
	}
	
	public static ItemStack createItem(Material material, Enchantment enchantment, boolean enchantmentVisibility, int enchantmentLvl) {
		ItemStack item = new ItemStack(material);
		ItemMeta itemM = item.getItemMeta();
		itemM.addEnchant(enchantment, enchantmentLvl, enchantmentVisibility);
		item.setItemMeta(itemM);
		return item;
	}
}
