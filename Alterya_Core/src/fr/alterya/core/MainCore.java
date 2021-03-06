package fr.alterya.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import fr.alterya.core.command.BasicsPlayerCommands;
import fr.alterya.core.command.CmdFurnace;
import fr.alterya.core.command.CmdGiveMoney;
import fr.alterya.core.command.CmdHome;
import fr.alterya.core.command.CmdIgnore;
import fr.alterya.core.command.CmdMoney;
import fr.alterya.core.command.CmdMsg;
import fr.alterya.core.command.CmdMute;
import fr.alterya.core.command.CmdPay;
import fr.alterya.core.command.CmdPurgeMoney;
import fr.alterya.core.command.CmdRank;
import fr.alterya.core.command.CmdSetMoney;
import fr.alterya.core.command.CmdSetSpawn;
import fr.alterya.core.command.CmdShop;
import fr.alterya.core.command.CmdSpawn;
import fr.alterya.core.command.CmdStaffList;
import fr.alterya.core.command.CmdTakeMoney;
import fr.alterya.core.command.CmdTempBan;
import fr.alterya.core.command.CmdTpMute;
import fr.alterya.core.command.CmdTpa;
import fr.alterya.core.command.CmdTpno;
import fr.alterya.core.command.CmdTrade;
import fr.alterya.core.command.CmdUUID;
import fr.alterya.core.listeners.PlayerListener;
import fr.alterya.core.listeners.ShopListener;
import fr.alterya.core.listeners.TradeListener;
import fr.alterya.core.rank.Rank;
import fr.alterya.core.shop.Shop;
import fr.alterya.core.util.DCommand;
import fr.alterya.core.util.TradeUtil;

public class MainCore extends JavaPlugin
{
	public static String prefix = ChatColor.AQUA + "[Alterya] ";
	public static void log(LogType logType, String message) { System.out.println("[Log] (" + logType.string() + ") " + message); }
	
	private Scoreboard scEmpty = Bukkit.getScoreboardManager().getNewScoreboard();
	
	public static List<String> ignoreMPPlayer = new ArrayList<>();
	
	public ItemStack p = new ItemStack(Material.POTION, (byte) 373);
	public ItemStack b = new ItemStack(Material.BOOK_AND_QUILL);
	public ItemStack i = new ItemStack(Material.ITEM_FRAME);
	
	public TradeListener tradeL;
	public TradeUtil tradeUtil;
	public Shop shop;
	public Rank rank;
	public Recipes recipes;
	public Player player;
	
	@Override
	public void onLoad() {
		rank = new Rank(this, player); 
		shop = new Shop();
		recipes = new Recipes(this);
		tradeUtil = new TradeUtil(this);
		tradeL = new TradeListener(this);
	}	
	
	@Override
	public void onEnable() {	
		System.out.println("AlteryaCore [ON]");
		
		Rank.initConfig();
		
		// Cr�er les commandes
		new DCommand("Message", "/msg <joueur> <message>", "Envoie un message priv� au joueur cilb�", null, Collections.singletonList("m"), new CmdMsg(this), this);
		new DCommand("Ignore", "/ignore", "Emp�che le joueur de re�evoir les messages priv�s", null, Collections.singletonList(""), new CmdIgnore(), this);
		new DCommand("Ec", "/ec", "Permet d'ouvrir ton enderchest", null, Collections.singletonList("enderchest"), new BasicsPlayerCommands(this, rank), this);
		new DCommand("Craft", "/craft", "Permet d'ouvrir une table de craft", null, Collections.singletonList(""), new BasicsPlayerCommands(this, rank), this);
		new DCommand("Furnace", "/furnace", "Permet de faire cuire l'item dans la main de l'envoyeur de la commande", null, Collections.singletonList(""), new CmdFurnace(this), this);
		new DCommand("Feed", "/feed", "Met la barre de faim au maximum", null, Collections.singletonList(""), new BasicsPlayerCommands(this, rank), this);
		new DCommand("Ping", "/ping", "Envoie le ping du joueur sur le serveur", null, Collections.singletonList(""), new BasicsPlayerCommands(this, rank), this);
		
		new DCommand("UUID", "/uuid", "Commande pour voir l'UUID d'un joueur", "getUUID", Collections.singletonList(""), new CmdUUID(this, rank), this);
		
		new DCommand("Staff", "/staff", "Affiche tous les membres du staff connect�s sur le serveur", null, Collections.singletonList(""), new CmdStaffList(rank), this);
		
		new DCommand("setspawn", "/setspawn", "Positionne le spawn officiel pour le monde acutel", "spawn.set", Collections.singletonList(""), new CmdSetSpawn(rank), this);
		new DCommand("spawn", "/spawn", "Teleport le joueur au spawn", null, Collections.singletonList("s"), new CmdSpawn(this), this);
		
		new DCommand("Tpa", "/tpa <joueur>", "Envoie une requ�te de t�l�portation � la cible", null, Collections.singletonList(""), new CmdTpa(this), this);
		new DCommand("Tpyes", "/tpyes <joueur>", "Accepte la qu�te de t�l�portation", null, Collections.singletonList(""), new CmdTpa(this), this);
		new DCommand("Tpno", "/tpno <joueur>", "Refuse la requ�te de t�l�portation", null, Collections.singletonList(""), new CmdTpno(), this);
		
		new DCommand("Shop", "/shop", "Ouvre l'interface du shop", null, Collections.singletonList(""), new CmdShop(this), this);
		
		new DCommand("Money", "/money", "Envoie le montant de la bank du joueur", null, Collections.singletonList(""), new CmdMoney(this.rank), this);
		new DCommand("Pay", "/pay <cible> <montant>", "Permet de payer un autre joueur", null, Collections.singletonList(""), new CmdPay(this.rank), this);
		new DCommand("Takemoney", "/takemoney <cible> <montant>", "Prend la money d'un joueur (OP)", null, Collections.singletonList(""), new CmdTakeMoney(this.rank, this), this);
		new DCommand("Givemoney", "/givemoney <cible> <montant>", "Donne de la money � un joueur (OP) (Pas pris sur la money de l'executeur de la commande)", null, Collections.singletonList(""), new CmdGiveMoney(this.rank, this), this);
		new DCommand("Purgemoney", "/purgemoney <cible>", "Remet la money du joueur au montant de d�part (50 $) (OP)", null, Collections.singletonList(""), new CmdPurgeMoney(rank, this), this);
		new DCommand("Setmoney", "/setmoney <cible> <montant>", "Met le montant de la bank de la cible au montant indiqu�", null, Collections.singletonList(""), new CmdSetMoney(rank, this), this);
		
		new DCommand("Home", "/home <nom>", "Teleporte l'executeur de la commande au home choisi � partir du nom indiqu�", null, Collections.singletonList(""), new CmdHome(rank, this), this);
		new DCommand("Delhome", "/delhome <nom>", "Supprime le home indiqu� dans la commande de la liste des homes du joueur", null, Collections.singletonList(""), new CmdHome(rank, this), this);
		new DCommand("Sethome", "/sethome <nom>", "Ajouter le home indiqu� dans la commande de la liste des homes du joueur", null, Collections.singletonList(""), new CmdHome(rank, this), this);
		new DCommand("Homeinfo", "/homeinfo", "Affiche la liste des homes position�s", null, Collections.singletonList("homes"), new CmdHome(rank, this), this);
		
		new DCommand("Promote", "/promote <id> <joueur>", "Promouvoir un joueur au rang indiqu� avec l'id", null, Collections.singletonList(""), new CmdRank(rank, this), this);
		new DCommand("Demote", "/demote <joueur>", "Remettre le rang d'un joueur � 0", null, Collections.singletonList(""), new CmdRank(rank, this), this);
		new DCommand("Rankinfo", "/rankinfo", "Affiche les infos sur les rangs", null, Collections.singletonList("ranks"), new CmdRank(rank, this), this);
		
		new DCommand("Mute", "/mute <joueur> <temps>", "Mute un joueur", null, Collections.singletonList(""), new CmdMute(rank, this), this);
		new DCommand("UnMute", "/unmute <joueur>", "D�-mute un joueur", null, Collections.singletonList(""), new CmdMute(rank, this), this);
		new DCommand("TempBan", "/tempban <joueur> <temps> <raison>", "Banni temporairement un joueur de serveur", null, Collections.singletonList(""), new CmdTempBan(rank, this), this);
		new DCommand("TpMute", "/tpmute <joueur> <temps> <raison>", "Emp�che le joueur d'utiliser les comandes de t�l�portation", null, Collections.singletonList(""), new CmdTpMute(rank, this), this);
		new DCommand("UnTpMute", "/untpmute <joueur> <temps> <raison>", "D�-emp�che le joueur d'utiliser les comandes de t�l�portation", null, Collections.singletonList(""), new CmdTpMute(rank, this), this);
		
		new DCommand("Trade", "/trade <joueur>", "Propose un �change d'item au joueur cibl�", null, Collections.singletonList(""), new CmdTrade(tradeUtil, this), this);
		
		removeCraft(p.getType());
		removeCraft(b.getType());
		removeCraft(i.getType());
		
		//Enregistrer tous les �venements 
		getServer().getPluginManager().registerEvents(new PlayerListener(rank, this), this);
		getServer().getPluginManager().registerEvents(new ShopListener(), this);
		getServer().getPluginManager().registerEvents(tradeL, this);
	}
	
	public void removeCraft(Material m) {
		Iterator<Recipe> it = Bukkit.getServer().recipeIterator();
		while (it.hasNext()) {
			Recipe recipe = (Recipe)it.next();
			if (recipe != null && recipe.getResult().getType() == m) {
				it.remove(); 
			}
		} 
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onDisable() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			ignoreMPPlayer.remove(p.getUniqueId().toString());
			p.setScoreboard(scEmpty);
		}
		for(Trade trade : tradeUtil.getAllTrades()) {
		      trade.cancelTrade(true);
		}
		System.out.println("AlteryaFaction [OFF]");
	}
	
	public static ItemStack getItem(Material material, int itemAmount, int itemData, String name, String... lores) {
	    ItemStack item = new ItemStack(material, itemAmount, (byte)itemData);
	    if (name != null) {
	      ItemMeta meta = item.getItemMeta();
	      meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
	      item.setItemMeta(meta);
	    } 
	    if (lores != null) {
	      List<String> lore = new ArrayList<>(); byte b; int i; String[] arrayOfString;
	      for (i = (arrayOfString = lores).length, b = 0; b < i; ) { String l = arrayOfString[b];
	        lore.add(ChatColor.translateAlternateColorCodes('&', l)); b++; }
	      
	      ItemMeta meta = item.getItemMeta();
	      meta.setLore(lore);
	      item.setItemMeta(meta);
	    } 
	    return item;
	}
	
	public boolean canPlaceItem(Player p, int rawSlot) {
	    boolean canPlace = false;
	    if (!isTopInventory(p, rawSlot)) {
	      canPlace = true;
	    }
	    if (isTopInventory(p, rawSlot)) {
	      int slot = rawSlot;
	      if ((slot >= 0 && slot < 4) || (slot >= 9 && slot < 13) || (slot >= 18 && slot < 22)) {
	        canPlace = true;
	      }
	    } 
	    return canPlace;
	  }
	  
	  public boolean isTopInventory(Player p, int rawSlot) {
	    boolean isTop = false;
	    if (p instanceof Player && 
	      rawSlot < p.getOpenInventory().getTopInventory().getSize()) {
	      isTop = true;
	    }
	    return isTop;
	  }
}
