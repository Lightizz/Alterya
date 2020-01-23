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
	    customas.setDisplayName(ChatColor.GOLD + "Epée en Alteryum");
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
	    customah.setDisplayName(ChatColor.GOLD + "Hache en Alteryum");
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
	    customap.setDisplayName(ChatColor.RED + "Pioche en Alteryum");
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
	    customch.setDisplayName(ChatColor.GOLD + "Casque en Alteryum");
	    customch.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
	    customH.setItemMeta(customch);
	    ShapedRecipe recetteAH = new ShapedRecipe(customH);
	    recetteAH.shape(new String[] { "EDE", "E E", "   " });
	    recetteAH.setIngredient('D', Material.DIAMOND_BLOCK);
	    recetteAH.setIngredient('E', Material.EMERALD_BLOCK);
	    this.main.getServer().addRecipe(recetteAH);
	    
	    ItemStack customC = new ItemStack(Material.DIAMOND_CHESTPLATE);
	    ItemMeta customcc = customC.getItemMeta();
	    customcc.setDisplayName(ChatColor.GOLD + "Plastron en Alteryum");
	    customcc.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
	    customC.setItemMeta(customcc);
	    ShapedRecipe recetteAC = new ShapedRecipe(customC);
	    recetteAC.shape(new String[] { "D D", "EEE", "EEE" });
	    recetteAC.setIngredient('D', Material.DIAMOND_BLOCK);
	    recetteAC.setIngredient('E', Material.EMERALD_BLOCK);
	    this.main.getServer().addRecipe(recetteAC);
	      
	    ItemStack customL = new ItemStack(Material.DIAMOND_LEGGINGS);
	    ItemMeta customcl = customL.getItemMeta();
	    customcl.setDisplayName(ChatColor.GOLD + "Jambière en Alteryum");
	    customcl.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
	    customL.setItemMeta(customcl);
	    ShapedRecipe recetteAL = new ShapedRecipe(customL);
	    recetteAL.shape(new String[] { "EDE", "E E", "E E" });
	    recetteAL.setIngredient('D', Material.DIAMOND_BLOCK);
	    recetteAL.setIngredient('E', Material.EMERALD_BLOCK);
	    this.main.getServer().addRecipe(recetteAL);
	      
	    ItemStack customB = new ItemStack(Material.DIAMOND_BOOTS);
	    ItemMeta customcb = customB.getItemMeta();
	    customcb.setDisplayName(ChatColor.GOLD + "Botte en Alteryum");
	    customcb.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
	    customB.setItemMeta(customcb);
	    ShapedRecipe recetteAB = new ShapedRecipe(customB);
	    recetteAB.shape(new String[] { "D D", "E E", "   " });
	    recetteAB.setIngredient('D', Material.DIAMOND_BLOCK);
	   	recetteAB.setIngredient('E', Material.EMERALD_BLOCK);
	   	this.main.getServer().addRecipe(recetteAB);
	   	
	   	ItemStack customObsidianRenforced = new ItemStack(Material.OBSIDIAN);
	    ItemMeta custom1 = customObsidianRenforced.getItemMeta();
	    custom1.setDisplayName(ChatColor.GOLD + "Obsidienne renforcée");
	    custom1.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
	    customObsidianRenforced.setItemMeta(custom1);
	    ShapedRecipe recetteOR = new ShapedRecipe(customObsidianRenforced);
	    recetteOR.shape(new String[] { "EOE", "OEO", "EOE" });
	    recetteOR.setIngredient('O', Material.OBSIDIAN);
	    recetteOR.setIngredient('E', Material.ENDER_STONE);
	    this.main.getServer().addRecipe(recetteOR);
	    
	    ItemStack customAlteryumApple = new ItemStack(Material.GOLDEN_APPLE);
	    ItemMeta custom2 = customAlteryumApple.getItemMeta();
	    custom2.setDisplayName(ChatColor.GOLD + "Pomme en Alteryum");
	    custom2.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
	    customAlteryumApple.setItemMeta(custom2);
	    ShapedRecipe recetteAPP = new ShapedRecipe(customAlteryumApple);
	    recetteAPP.shape(new String[] { "EDE", "DAD", "EDE" });
	    recetteAPP.setIngredient('A', Material.APPLE);
	    recetteAPP.setIngredient('D', Material.DIAMOND_BLOCK);
	    recetteAPP.setIngredient('E', Material.EMERALD_BLOCK);
	    this.main.getServer().addRecipe(recetteAPP);
	    
	    ItemStack customFactionHeart = new ItemStack(Material.BEACON);
	    ItemMeta custom3 = customAlteryumApple.getItemMeta();
	    custom3.setDisplayName(ChatColor.GOLD + "Coeur de faction");
	    custom3.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
	    customFactionHeart.setItemMeta(custom3);
	    ShapedRecipe recetteFH = new ShapedRecipe(customFactionHeart);
	    recetteFH.shape(new String[] { "ESE", "DBD", "ESE" });
	    recetteFH.setIngredient('B', Material.BEACON);
	    recetteFH.setIngredient('S', Material.NETHER_STAR);
	    recetteFH.setIngredient('D', Material.DIAMOND_BLOCK);
	    recetteFH.setIngredient('E', Material.EMERALD_BLOCK);
	    this.main.getServer().addRecipe(recetteFH);
	    
	    ItemStack customFactionHeartSupport = new ItemStack(Material.PISTON_STICKY_BASE);
	    ItemMeta custom4 = customFactionHeartSupport.getItemMeta();
	    custom4.setDisplayName(ChatColor.GOLD + "Support de Coeur de faction");
	    custom4.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
	    customFactionHeartSupport.setItemMeta(custom4);
	    ShapedRecipe recetteFHS = new ShapedRecipe(customFactionHeartSupport);
	    recetteFHS.shape(new String[] { "   ", "DED", " P " });
	    recetteFHS.setIngredient('P', Material.PISTON_BASE);
	    recetteFHS.setIngredient('D', Material.DIAMOND_BLOCK);
	    recetteFHS.setIngredient('E', Material.EMERALD_BLOCK);
	    this.main.getServer().addRecipe(recetteFHS);
	    
	    ItemStack customCurruptedSpike = new ItemStack(Material.FEATHER);
	    ItemMeta custom5 = customFactionHeartSupport.getItemMeta();
	    custom5.setDisplayName(ChatColor.GOLD + "Épine corrompu");
	    custom5.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
	    customCurruptedSpike.setItemMeta(custom5);
	    ShapedRecipe recetteCS = new ShapedRecipe(customCurruptedSpike);
	    recetteCS.shape(new String[] { "MNM", "NMN", "MNM" });
	    recetteCS.setIngredient('M', Material.DIAMOND_BLOCK);
	    recetteCS.setIngredient('N', Material.EMERALD_BLOCK);
	    this.main.getServer().addRecipe(recetteCS);
	    
	    ItemStack customAdventureC = new ItemStack(Material.LEATHER_HELMET);
	    ItemMeta customc = customH.getItemMeta();
	    customc.setDisplayName(ChatColor.GOLD + "Casque d'aventure");
	    customc.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
	    customAdventureC.setItemMeta(customc);
	    ShapedRecipe recetteADC = new ShapedRecipe(customAdventureC);
	    recetteADC.shape(new String[] { "EDE", "E E", "   " });
	    recetteADC.setIngredient('D', Material.DIAMOND_BLOCK);
	    recetteADC.setIngredient('E', Material.EMERALD_BLOCK);
	    this.main.getServer().addRecipe(recetteADC);
	    
	    ItemStack customAdventureP = new ItemStack(Material.LEATHER_CHESTPLATE);
	    ItemMeta customco = customC.getItemMeta();
	    customco.setDisplayName(ChatColor.GOLD + "Plastron d'aventure");
	    customco.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
	    customAdventureP.setItemMeta(customco);
	    ShapedRecipe recetteACO = new ShapedRecipe(customAdventureP);
	    recetteACO.shape(new String[] { "D D", "EEE", "EEE" });
	    recetteACO.setIngredient('D', Material.DIAMOND_BLOCK);
	    recetteACO.setIngredient('E', Material.EMERALD_BLOCK);
	    this.main.getServer().addRecipe(recetteACO);
	      
	    ItemStack customAdventureL = new ItemStack(Material.LEATHER_LEGGINGS);
	    ItemMeta customal = customAdventureL.getItemMeta();
	    customal.setDisplayName(ChatColor.GOLD + "Jambière d'aventure");
	    customal.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
	    customAdventureL.setItemMeta(customal);
	    ShapedRecipe recetteADL = new ShapedRecipe(customAdventureL);
	    recetteADL.shape(new String[] { "EDE", "E E", "E E" });
	    recetteADL.setIngredient('D', Material.DIAMOND_BLOCK);
	    recetteADL.setIngredient('E', Material.EMERALD_BLOCK);
	    this.main.getServer().addRecipe(recetteADL);
	      
	    ItemStack customAdventureB = new ItemStack(Material.LEATHER_BOOTS);
	    ItemMeta customadb = customB.getItemMeta();
	    customadb.setDisplayName(ChatColor.GOLD + "Botte d'aventure");
	    customadb.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
	    customAdventureB.setItemMeta(customadb);
	    ShapedRecipe recetteADB = new ShapedRecipe(customAdventureB);
	    recetteADB.shape(new String[] { "D D", "E E", "   " });
	    recetteADB.setIngredient('D', Material.DIAMOND_BLOCK);
	   	recetteADB.setIngredient('E', Material.EMERALD_BLOCK);
	   	this.main.getServer().addRecipe(recetteADB);
	}
}
