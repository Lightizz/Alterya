package fr.alterya.core.command;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.core.MainCore;
import fr.alterya.core.rank.Rank;

/*
Author : Lightiz
*/

public class CmdKit extends BukkitRunnable implements CommandExecutor
{
	public int timer = 0;
	Player player0;
	MainCore main;
	Rank rank = new Rank(main, player0);
	
	public CmdKit(MainCore m) {
		main = m;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args) {
		Player player = (Player) sender;
		player0 = player;
		if(message.equalsIgnoreCase("kit")) {
			if(Rank.config.getInt(player.getUniqueId().toString()) == 2) {
				if(timer != 0) {
					int timeRemaiting = 172800 - timer;
					player.sendMessage(MainCore.prefix + "Il vous reste " + timeRemaiting + "sec avant de pouvoir re-utiliser le /kit !");
				}else if(timer == 0) {
					giveMemoireKit(player);
					player.sendMessage(MainCore.prefix + "Voici votre kit du grade Mémoire !");
					runTaskTimer(main, 0, 20);
					return true;
				}
			}else if(Rank.config.getInt(player.getUniqueId().toString()) == 3) {
				if(timer != 0) {
					int timeRemaiting = 259200 - timer;
					player.sendMessage(MainCore.prefix + "Il vous reste " + timeRemaiting + "sec avant de pouvoir re-utiliser le /kit !");
				}else if(timer == 0) {
					giveSageKit(player);
					player.sendMessage(MainCore.prefix + "Voici votre kit du grade Sage !");
					runTaskTimer(main, 0, 20);
					return true;
				}
			}else if(Rank.config.getInt(player.getUniqueId().toString()) == 1) {
				if(timer != 0) {
					int timeRemaiting = 172800 - timer;
					player.sendMessage(MainCore.prefix + "Il vous reste " + timeRemaiting + "sec avant de pouvoir re-utiliser le /kit !");
				}else if(timer == 0) {
					giveSouvenirKit(player);
					player.sendMessage(MainCore.prefix + "Voici votre kit du grade Souvenir !");
					runTaskTimer(main, 0, 20);
					return true;
				}	
			}else {
				player.sendMessage(MainCore.prefix + "§aVous§e n'avez aucun grade permettant le /kit, vous ne pouvez pas reçevoir de kit !");
				return true;
			}
		}
		return false;
	}
	
	//Préparer et donner les kits 
	public void giveSageKit(Player player) {
		ItemStack kitBoots = new ItemStack(Material.IRON_BOOTS);
		ItemMeta bootsMeta = kitBoots.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Kit boots");
		bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		bootsMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		kitBoots.setItemMeta(bootsMeta);
		
		ItemStack kitChestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta chestplateMeta = kitChestplate.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Kit chestplate");
		chestplateMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		bootsMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		kitChestplate.setItemMeta(chestplateMeta);
		
		ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta leggingsMeta = leggings.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Kit leggings");
		leggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		bootsMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		leggings.setItemMeta(leggingsMeta);
		
		ItemStack helmet = new ItemStack(Material.IRON_HELMET);
		ItemMeta helmetMeta = helmet.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Kit helmet");
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
		bootsMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		helmet.setItemMeta(helmetMeta);
		
		ItemStack sword = new ItemStack(Material.IRON_SWORD);
		ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.setDisplayName(ChatColor.RED + "Kit sword");
		swordMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
		swordMeta.addEnchant(Enchantment.DURABILITY, 1, true);
		sword.setItemMeta(swordMeta);
		
		ItemStack goldenApple = new ItemStack((Material.GOLDEN_APPLE), 5);
		
		player.getInventory().addItem(sword);
		player.getInventory().addItem(helmet);
		player.getInventory().addItem(kitChestplate);
		player.getInventory().addItem(leggings);
		player.getInventory().addItem(kitBoots);
		player.getInventory().addItem(goldenApple);
		player.updateInventory();
	}
	
	public void giveMemoireKit(Player player) {
		ItemStack kitBoots = new ItemStack(Material.IRON_BOOTS);
		ItemMeta bootsMeta = kitBoots.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Kit boots");
		bootsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		kitBoots.setItemMeta(bootsMeta);
		
		ItemStack kitChestplate = new ItemStack(Material.IRON_CHESTPLATE);
		ItemMeta chestplateMeta = kitChestplate.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Kit chestplate");
		chestplateMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		kitChestplate.setItemMeta(chestplateMeta);
		
		ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
		ItemMeta leggingsMeta = leggings.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Kit leggings");
		leggingsMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		leggings.setItemMeta(leggingsMeta);
		
		ItemStack helmet = new ItemStack(Material.IRON_HELMET);
		ItemMeta helmetMeta = helmet.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.RED + "Kit helmet");
		helmetMeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
		helmet.setItemMeta(helmetMeta);
		
		ItemStack goldenApple = new ItemStack((Material.GOLDEN_APPLE), 5);
		
		player.getInventory().addItem(helmet);
		player.getInventory().addItem(leggings);
		player.getInventory().addItem(kitChestplate);
		player.getInventory().addItem(kitBoots);
		player.getInventory().addItem(goldenApple);
		player.updateInventory();
	}

	public void giveSouvenirKit(Player player) {
		player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
		player.getInventory().addItem(new ItemStack(Material.LEATHER_HELMET));
		player.getInventory().addItem(new ItemStack(Material.LEATHER_CHESTPLATE));
		player.getInventory().addItem(new ItemStack(Material.LEATHER_LEGGINGS));
		player.getInventory().addItem(new ItemStack(Material.LEATHER_BOOTS));
		player.updateInventory();
	}

	//Le timer pour utiliser les kits
	@Override
	public void run()
	{
		//1h = 3 600 sec ; 24h = 86 400 sec ; 48h = 172 800 sec ; 72h = 259 200 sec
		if(Rank.config.getInt(player0.getUniqueId().toString()) == 2 && timer >= 172800) {
			cancel();
		}else if(Rank.config.getInt(player0.getUniqueId().toString()) == 3 && timer >= 259200) {
			cancel();
		}else if(Rank.config.getInt(player0.getUniqueId().toString()) == 1 && timer >= 172800) {
			cancel();
		}
		timer ++;
	}
}
