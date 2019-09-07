package fr.alterya.core;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Recipes {
	
	MainCore main;
	
	public Recipes (MainCore main) {
		this.main = main;
		
		@SuppressWarnings("deprecation")
		ItemStack customCREEP = new ItemStack(Material.MONSTER_EGG, (byte) 0, (short) 50, null);
	    ItemMeta customcreep = customCREEP.getItemMeta();
	    customcreep.setDisplayName(ChatColor.RED + "Bombe");
	    customcreep.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
	    customCREEP.setItemMeta(customcreep);
	      
	    ShapedRecipe recetteCREEP = new ShapedRecipe(customCREEP);
	    recetteCREEP.shape(new String[] { "TTT", "TRT", "SSS" });
	    recetteCREEP.setIngredient('S', Material.SULPHUR);
	    recetteCREEP.setIngredient('T', Material.TNT);
	    recetteCREEP.setIngredient('R', Material.REDSTONE);
	    this.main.getServer().addRecipe(recetteCREEP);
	  
	    ItemStack customALTERYOMS = new ItemStack(Material.DIAMOND_SWORD);
	    ItemMeta customas = customALTERYOMS.getItemMeta();
	    customas.setDisplayName(ChatColor.GOLD + "Epée en Alteryom");
	    customas.addEnchant(Enchantment.DAMAGE_ALL, 6, true);
	    customALTERYOMS.setItemMeta(customas);
	      
	    ShapedRecipe recetteAS = new ShapedRecipe(customALTERYOMS);
	    recetteAS.shape(new String[] { " A ", "EAE", " S " });
	    recetteAS.setIngredient('A', Material.DIAMOND_BLOCK);
	    recetteAS.setIngredient('E', Material.EMERALD_BLOCK);
	    recetteAS.setIngredient('S', Material.BLAZE_ROD);
	    this.main.getServer().addRecipe(recetteAS);
	  
	    ItemStack customALTERYOMA = new ItemStack(Material.DIAMOND_AXE);
	    ItemMeta customah = customALTERYOMA.getItemMeta();
	    customah.setDisplayName(ChatColor.GOLD + "Hache en Alteryom");
	    customah.addEnchant(Enchantment.DAMAGE_ALL, 6, true);
	    customah.addEnchant(Enchantment.DIG_SPEED, 6, true);
	    customALTERYOMA.setItemMeta(customah);
	      
	    ShapedRecipe recetteAA = new ShapedRecipe(customALTERYOMA);
	    recetteAA.shape(new String[] { "E E", "DSD", " S " });
	    recetteAA.setIngredient('D', Material.DIAMOND_BLOCK);
	    recetteAA.setIngredient('E', Material.EMERALD);
	    recetteAA.setIngredient('S', Material.BLAZE_ROD);
	    this.main.getServer().addRecipe(recetteAA);
	  
	    ItemStack customALTERYOMP = new ItemStack(Material.DIAMOND_PICKAXE);
	    ItemMeta customap = customALTERYOMP.getItemMeta();
	    customap.setDisplayName(ChatColor.RED + "Pioche en Remedia");
	    customap.addEnchant(Enchantment.DIG_SPEED, 6, true);
	    customALTERYOMP.setItemMeta(customap);
	      
	    ShapedRecipe recetteAP = new ShapedRecipe(customALTERYOMP);
	    recetteAP.shape(new String[] { "EAE", " S ", " S " });
	    recetteAP.setIngredient('A', Material.DIAMOND_BLOCK);
	    recetteAP.setIngredient('E', Material.DIAMOND);
	    recetteAP.setIngredient('S', Material.BLAZE_ROD);
	    this.main.getServer().addRecipe(recetteAP);
	  
	    ItemStack customH = new ItemStack(Material.DIAMOND_HELMET);
	    ItemMeta customch = customH.getItemMeta();
	    customch.setDisplayName(ChatColor.GOLD + "Casque en Alteryom");
	    customch.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
	    customH.setItemMeta(customch);
	  
	    ShapedRecipe recetteAH = new ShapedRecipe(customH);
	    recetteAH.shape(new String[] { "EDE", "E E", "   " });
	    recetteAH.setIngredient('D', Material.DIAMOND_BLOCK);
	    recetteAH.setIngredient('E', Material.EMERALD_BLOCK);
	    this.main.getServer().addRecipe(recetteAH);
	    
	    ItemStack customC = new ItemStack(Material.DIAMOND_CHESTPLATE);
	    ItemMeta customcc = customC.getItemMeta();
	    customcc.setDisplayName(ChatColor.GOLD + "Plastron en Alteryom");
	    customcc.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
	    customC.setItemMeta(customcc);
	      
	    ShapedRecipe recetteAC = new ShapedRecipe(customC);
	    recetteAC.shape(new String[] { "D D", "EEE", "EEE" });
	    recetteAC.setIngredient('D', Material.DIAMOND_BLOCK);
	    recetteAC.setIngredient('E', Material.EMERALD_BLOCK);
	    this.main.getServer().addRecipe(recetteAC);
	      
	    ItemStack customL = new ItemStack(Material.DIAMOND_LEGGINGS);
	    ItemMeta customcl = customL.getItemMeta();
	    customcl.setDisplayName(ChatColor.GOLD + "Jambière en Alteryom");
	    customcl.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
	    customL.setItemMeta(customcl);
	  
	    ShapedRecipe recetteAL = new ShapedRecipe(customL);
	    recetteAL.shape(new String[] { "EDE", "E E", "E E" });
	    recetteAL.setIngredient('D', Material.DIAMOND_BLOCK);
	    recetteAL.setIngredient('E', Material.EMERALD_BLOCK);
	    this.main.getServer().addRecipe(recetteAL);
	      
	    ItemStack customB = new ItemStack(Material.DIAMOND_BOOTS);
	    ItemMeta customcb = customB.getItemMeta();
	    customcb.setDisplayName(ChatColor.GOLD + "Botte en Alteryom");
	    customcb.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
	    customB.setItemMeta(customcb);
	      
	    ShapedRecipe recetteAB = new ShapedRecipe(customB);
	    recetteAB.shape(new String[] { "D D", "E E", "   " });
	    recetteAB.setIngredient('D', Material.DIAMOND_BLOCK);
	   	recetteAB.setIngredient('E', Material.EMERALD_BLOCK);
	   	this.main.getServer().addRecipe(recetteAB);
	}
}
