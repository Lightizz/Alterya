package fr.alterya.moderation.gui.template;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.alterya.moderation.Main;
import fr.alterya.moderation.gui.AbstractGui;

public class GuiCustom extends AbstractGui {

	private final Main plugin;
	private Player target;
	private int topluck;
	private long tempdejeu;
	private long tempensec;

	ArrayList<Player> freezPlayerList = new ArrayList<Player>();

	public GuiCustom(Main plugin, Player target) {
		super(plugin);
		this.plugin = plugin;
		this.target = target;
	}

	@Override
	public void display(Player player) {

		this.inventory = this.plugin.getServer().createInventory(null, 6 * 9, "" + target.getName());

		if (target.getStatistic(Statistic.MINE_BLOCK, Material.STONE) == 0)
			topluck = 0;
		else
			topluck = target.getStatistic(Statistic.MINE_BLOCK, Material.DIAMOND_ORE) * 100
					/ target.getStatistic(Statistic.MINE_BLOCK, Material.STONE);

		tempdejeu = target.getStatistic(Statistic.PLAY_ONE_TICK) / 1200;
		tempensec = tempdejeu / 60;

		ItemStack customSta = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		ItemMeta customS = customSta.getItemMeta();
		customS.setDisplayName(ChatColor.DARK_PURPLE + "Statistiques");
		customS.setLore(Arrays.asList(
				"" + ChatColor.DARK_RED + "Stone Miner >" + ChatColor.WHITE
						+ target.getStatistic(Statistic.MINE_BLOCK, Material.STONE),
				"" + ChatColor.DARK_RED + "Diams Miner >" + ChatColor.WHITE
						+ target.getStatistic(Statistic.MINE_BLOCK, Material.DIAMOND_ORE),
				ChatColor.GOLD + "TopLuck >" + topluck + "%"));
		customSta.setItemMeta(customS);

		ItemStack customTemp = new ItemStack(Material.COMPASS, 1);
		ItemMeta customT = customTemp.getItemMeta();
		customT.setDisplayName(ChatColor.LIGHT_PURPLE + "Temp de jeu");
		customT.setLore(Arrays.asList("" + tempensec + "H"));
		customTemp.setItemMeta(customT);

		ItemStack customGlas = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 15);
		ItemMeta customG = customGlas.getItemMeta();
		customG.setDisplayName(" ");
		customGlas.setItemMeta(customG);

		this.setSlotData(ChatColor.GOLD + "Teleport Vers", Material.ENDER_PEARL, 29, null, "tp");
		this.setSlotData(ChatColor.GOLD + "Teleport Ici", Material.ENDER_CHEST, 30, null, "tphere");
		this.setSlotData(ChatColor.AQUA + "Fermer", Material.WOOD_DOOR, 53, null, "close");
		this.setSlotData(ChatColor.RED + "Kill", Material.DEAD_BUSH, 12, null, "Mort");
		this.setSlotData(ChatColor.DARK_GREEN + "Inventaire", Material.CHEST, 40, null, "Inventaire");
		this.setSlotData(ChatColor.DARK_RED + "Ban", Material.LAVA_BUCKET, 13, null, "Ban");
		this.setSlotData(ChatColor.LIGHT_PURPLE + "Temp de jeu", Material.LAVA_BUCKET, 22, null, "temp");
		this.setSlotData(ChatColor.DARK_RED + "Dé-Ban", Material.WATER_BUCKET, 14, null, "UnBan");
		this.setSlotData(ChatColor.YELLOW + "Kick", Material.ARROW, 33, null, "Kick");
		this.setSlotData(ChatColor.BLUE + "Freez", Material.ICE, 32, null, "freeze");
		this.setSlotData(customSta, 22, "");
		this.setSlotData(customTemp, 31, "");
		
		for(int i = 0; i != 12; i++){
					this.setSlotData(customGlas, i, null);
		}
	
		for(int i = 15; i != 22; i++){
			this.setSlotData(customGlas, i, null);
		}
		for(int i = 23; i != 29; i++){
			this.setSlotData(customGlas, i, null);
		}
		for(int i = 34; i != 40; i++){
			this.setSlotData(customGlas, i, null);
		}
		for(int i = 41; i != 53; i++){
			this.setSlotData(customGlas, i, null);
		}
		
		if(player.isOp() == true) {
		player.openInventory(this.inventory);
		}
	}
	@SuppressWarnings("deprecation")
	@Override
	public void onClick(Player player, ItemStack stack, String action, ClickType clickType) {
		if(player.isOp() == true) {
		if (action.equalsIgnoreCase("close")) {
			this.plugin.getGuiManager().closeGui(player);
		} else if (action.equalsIgnoreCase("tp")) {
			player.teleport(target.getLocation());
			player.sendMessage("Teleport succefull");
		} else if (action.equalsIgnoreCase("Mort")) {
			target.setHealth(0);
			player.sendMessage("Joueur Mort");
		} else if (action.equalsIgnoreCase("Inventaire")) {
			player.openInventory(target.getInventory());

		} else if (action.equalsIgnoreCase("Ban")) {
			target.kickPlayer("§8You have been banned by " + "§4" + player.getName());
			target.setBanned(true);
		} else if (action.equalsIgnoreCase("UnBan")) {
			target.setBanned(false);
		} else if (action.equalsIgnoreCase("Kick")) {

			target.kickPlayer(ChatColor.GOLD + "Kick Par " + ChatColor.RED + player.getName());

		} else if (action.equalsIgnoreCase("tphere")) {
			target.teleport(player);
        
		}
		return;
		}
	}
}





