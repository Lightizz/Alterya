package fr.alterya.core;

import java.util.Collections;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import fr.alterya.core.command.BasicsPlayerCommands;
import fr.alterya.core.command.CmdBaltop;
import fr.alterya.core.command.CmdFurnace;
import fr.alterya.core.command.CmdGiveMoney;
import fr.alterya.core.command.CmdHome;
import fr.alterya.core.command.CmdKit;
import fr.alterya.core.command.CmdMenu;
import fr.alterya.core.command.CmdMoney;
import fr.alterya.core.command.CmdMsg;
import fr.alterya.core.command.CmdMute;
import fr.alterya.core.command.CmdPay;
import fr.alterya.core.command.CmdPromote;
import fr.alterya.core.command.CmdPurgeMoney;
import fr.alterya.core.command.CmdSetMoney;
import fr.alterya.core.command.CmdSetSpawn;
import fr.alterya.core.command.CmdShop;
import fr.alterya.core.command.CmdSpawn;
import fr.alterya.core.command.CmdStaffList;
import fr.alterya.core.command.CmdTakeMoney;
import fr.alterya.core.command.CmdTempBan;
import fr.alterya.core.command.CmdTpa;
import fr.alterya.core.command.CmdTpno;
import fr.alterya.core.event.KOTHEvent;
import fr.alterya.core.event.KOTHEventManager;
import fr.alterya.core.event.TotemEvent;
import fr.alterya.core.event.TotemEventManager;
import fr.alterya.core.listeners.PlayerListener;
import fr.alterya.core.listeners.PlayerMenuListener;
import fr.alterya.core.listeners.ShopListener;
import fr.alterya.core.rank.Rank;
import fr.alterya.core.rank.permissions.PermissionsManager;
import fr.alterya.core.shop.Shop;
import fr.alterya.core.util.DCommand;
import fr.alterya.core.util.DisconnectCombat;

public class MainCore extends JavaPlugin
{
	private Scoreboard scEmpty = Bukkit.getScoreboardManager().getNewScoreboard();
	
	public static String prefix = ChatColor.AQUA + "[Alterya] ";
		
	public ItemStack p = new ItemStack(Material.POTION, (byte) 373);
	public ItemStack b = new ItemStack(Material.BOOK_AND_QUILL);
	public ItemStack i = new ItemStack(Material.ITEM_FRAME);
	
	public Shop shop;
	public Rank rank;
	public Recipes recipes;
	public TotemEventManager etManager;
	public KOTHEventManager ekManager;
	public Player player;
	
	@Override
	public void onLoad() {
		rank = new Rank(this, player); 
		shop = new Shop();
		recipes = new Recipes(this);
	}	
	
	@Override
	public void onEnable() {	
		System.out.println("AlteryaCore [ON]");
		
		ekManager = new KOTHEventManager(this);
		ekManager.runTaskTimer(this, 0, 20);
		
		etManager = new TotemEventManager(this);
		etManager.runTaskTimer(this, 0, 20);
		
		// Créer les commandes
		new DCommand("Message", "/msg <joueur> <message>", "Envoie un message privé au joueur cilbé", null, Collections.singletonList("m"), new CmdMsg(this), this);
		new DCommand("Menu", "/menu", "Ouvre le menu du joueur", null, Collections.singletonList(""), new CmdMenu(this), this);
		new DCommand("Ec", "/ec", "Permet d'ouvrir ton enderchest", null, Collections.singletonList("enderchest"), new BasicsPlayerCommands(this, rank), this);
		new DCommand("Craft", "/craft", "Permet d'ouvrir une table de craft", null, Collections.singletonList(""), new BasicsPlayerCommands(this, rank), this);
		new DCommand("Furnace", "/furnace", "Permet de faire cuire l'item dans la main de l'envoyeur de la commande", null, Collections.singletonList(""), new CmdFurnace(this), this);
		new DCommand("Discord", "/discord", "Envoie le lien du serveur discord officiel du serveur", null, Collections.singletonList(""), new BasicsPlayerCommands(this, rank), this);
		new DCommand("Feed", "/feed", "Met la barre de faim au maximum", null, Collections.singletonList(""), new BasicsPlayerCommands(this, rank), this);
		new DCommand("Ping", "/ping", "Envoie le ping du joueur sur le serveur", null, Collections.singletonList(""), new BasicsPlayerCommands(this, rank), this);
		new DCommand("Tipeee", "/tipeee", "Envoie le lien du tipeee officiel du serveur", null, Collections.singletonList(""), new BasicsPlayerCommands(this, rank), this);
		
		new DCommand("Staff", "/staff", "Affiche tous les membres du staff connectés sur le serveur", null, Collections.singletonList(""), new CmdStaffList(rank), this);
		
		new DCommand("setspawn", "/setspawn", "Positionne le spawn officiel pour le monde acutel", "spawn.set", Collections.singletonList(""), new CmdSetSpawn(rank), this);
		new DCommand("spawn", "/spawn", "Teleport le joueur au spawn", null, Collections.singletonList("s"), new CmdSpawn(this), this);
		
		new DCommand("Tpa", "/tpa <joueur>", "Envoie une requête de téléportation à la cible", null, Collections.singletonList(""), new CmdTpa(this), this);
		new DCommand("Tpyes", "/tpyes <joueur>", "Accepte la quête de téléportation", null, Collections.singletonList(""), new CmdTpa(this), this);
		new DCommand("Tpno", "/tpno <joueur>", "Refuse la requête de téléportation", null, Collections.singletonList(""), new CmdTpno(), this);
		
		new DCommand("Shop", "/shop", "Ouvre l'interface du shop", null, Collections.singletonList(""), new CmdShop(this), this);
		
		new DCommand("Money", "/money", "Envoie le montant de la bank du joueur", null, Collections.singletonList(""), new CmdMoney(this.rank), this);
		new DCommand("Pay", "/pay <cible> <montant>", "Permet de payer un autre joueur", null, Collections.singletonList(""), new CmdPay(this.rank), this);
		new DCommand("Takemoney", "/takemoney <cible> <montant>", "Prend la money d'un joueur (OP)", null, Collections.singletonList(""), new CmdTakeMoney(this.rank, this), this);
		new DCommand("Givemoney", "/givemoney <cible> <montant>", "Donne de la money à un joueur (OP) (Pas pris sur la money de l'executeur de la commande)", null, Collections.singletonList(""), new CmdGiveMoney(this.rank, this), this);
		new DCommand("Purgemoney", "/purgemoney <cible>", "Remet la money du joueur au montant de départ (50 $) (OP)", null, Collections.singletonList(""), new CmdPurgeMoney(rank, this), this);
		new DCommand("Setmoney", "/setmoney <cible> <montant>", "Met le montant de la bank de la cible au montant indiqué", null, Collections.singletonList(""), new CmdSetMoney(rank, this), this);
		new DCommand("Baltop", "/baltop", "Affiche le top 10 des joueurs les plus richent sur le serveur", null, Collections.singletonList("moneytop"), new CmdBaltop(), this);
		
		new DCommand("Home", "/home <nom>", "Teleporte l'executeur de la commande au home choisi à partir du nom indiqué", null, Collections.singletonList(""), new CmdHome(rank, this), this);
		new DCommand("Delhome", "/delhome <nom>", "Supprime le home indiqué dans la commande de la liste des homes du joueur", null, Collections.singletonList(""), new CmdHome(rank, this), this);
		new DCommand("Sethome", "/sethome <nom>", "Ajouter le home indiqué dans la commande de la liste des homes du joueur", null, Collections.singletonList(""), new CmdHome(rank, this), this);
		new DCommand("Homeinfo", "/homeinfo", "Affiche la liste des homes positionés", null, Collections.singletonList(""), new CmdHome(rank, this), this);
		
		new DCommand("Promote", "/promote <id> <joueur>", "Promouvoir un joueur au rang indiqué avec l'id", null, Collections.singletonList(""), new CmdPromote(rank, this), this);
		new DCommand("Demote", "/demote <joueur>", "Remettre le rang d'un joueur à 0", null, Collections.singletonList(""), new CmdPromote(rank, this), this);
		new DCommand("Rankinfo", "/rankinfo", "Affiche les infos sur les rangs", null, Collections.singletonList(""), new CmdPromote(rank, this), this);
		
		new DCommand("Kit", "/kit", "Donne le kit au joueur coorespondant à son grade", null, Collections.singletonList(""), new CmdKit(this), this);
		
		new DCommand("Mute", "/mute <joueur> <temps>", "Mute un joueur", null, Collections.singletonList(""), new CmdMute(rank, this), this);
		new DCommand("UnMute", "/unmute <joueur>", "Dé-mute un joueur", null, Collections.singletonList(""), new CmdMute(rank, this), this);
		new DCommand("TempBan", "/tempban <joueur> <temps> <raison>", "Banni temporairement un joueur de serveur", null, Collections.singletonList(""), new CmdTempBan(rank, this), this);
	
		removeCraft(p.getType());
		removeCraft(b.getType());
		removeCraft(i.getType());
		
		//Initialiser le scoreboard des rangs
		rank.initScoreboard();
		
		//Enregistrer tous les évenements 
		getServer().getPluginManager().registerEvents(new PlayerListener(rank, this), this);
		getServer().getPluginManager().registerEvents(new ShopListener(), this);
		getServer().getPluginManager().registerEvents(new TotemEvent(this, etManager), this);
		getServer().getPluginManager().registerEvents(new KOTHEvent(this, ekManager), this);
		getServer().getPluginManager().registerEvents(new PlayerMenuListener(this), this);
		getServer().getPluginManager().registerEvents(new PermissionsManager(this), this);
		getServer().getPluginManager().registerEvents(new DisconnectCombat(), this);
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
	public void onDisable() 
	{	
		System.out.println("AlteryaFaction [OFF]");
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.setScoreboard(scEmpty);
		}
	}
	
	public static void log(LogType logType, String message) {
		System.out.println("[Log] (" + logType.string() + ") " + message);
	}
}
