package fr.alterya.core;

import java.util.Collections;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.alterya.core.command.BasicsPlayerCommands;
import fr.alterya.core.command.CmdBaltop;
import fr.alterya.core.command.CmdCombatTime;
import fr.alterya.core.command.CmdFurnace;
import fr.alterya.core.command.CmdGiveMoney;
import fr.alterya.core.command.CmdHome;
import fr.alterya.core.command.CmdKit;
import fr.alterya.core.command.CmdMoney;
import fr.alterya.core.command.CmdPay;
import fr.alterya.core.command.CmdPromote;
import fr.alterya.core.command.CmdPurgeMoney;
import fr.alterya.core.command.CmdSetMoney;
import fr.alterya.core.command.CmdShop;
import fr.alterya.core.command.CmdTakeMoney;
import fr.alterya.core.command.CmdTpa;
import fr.alterya.core.command.CmdTpno;
import fr.alterya.core.listeners.PlayerListener;
import fr.alterya.core.listeners.ShopListener;
import fr.alterya.core.rank.Rank;
import fr.alterya.core.rank.permissions.PermissionsManager;
import fr.alterya.core.shop.Shop;
import fr.alterya.core.util.DCommand;
import fr.alterya.core.util.DisconnectCombat;

public class MainCore extends JavaPlugin
{
	public static String prefix = ChatColor.AQUA + "[Core] ";
		
	public Shop shop;
	Player player;
	public Rank rank;
	
	public static MainCore instance;
	public static MainCore getInstance() { return instance; }
	
	@Override
	public void onLoad() { 
		rank = new Rank(this, player); 
		shop = new Shop();
	}	
	
	@Override
	public void onEnable() {		
		System.out.println("==== AlteryaCore -> ON ====");
		
		// Créer les commandes
		new DCommand("Ec", "/ec", "Permet d'ouvrir ton enderchest", null, Collections.singletonList("/enderchest"), new BasicsPlayerCommands(), this);
		new DCommand("Craft", "/craft", "Permet d'ouvrir une table de craft", null, Collections.singletonList(""), new BasicsPlayerCommands(), this);
		new DCommand("Furnace", "/furnace", "Permet de faire cuire l'item dans la main de l'envoyeur de la commande", null, Collections.singletonList(""), new CmdFurnace(this), this);
		new DCommand("Discord", "/discord", "Envoie le lien du serveur discord officiel du serveur", null, Collections.singletonList(""), new BasicsPlayerCommands(), this);
		new DCommand("Ping", "/ping", "Envoie le ping du joueur sur le serveur", null, Collections.singletonList(""), new BasicsPlayerCommands(), this);
		new DCommand("Tipeee", "/tipeee", "Envoie le lien du tipeee officiel du serveur", null, Collections.singletonList(""), new BasicsPlayerCommands(), this);
		
		new DCommand("Tpa", "/tpa <joueur>", "Envoie une requête de téléportation à la cible", null, Collections.singletonList(""), new CmdTpa(this), this);
		new DCommand("Tpyes", "/tpyes <joueur>", "Accepte la quête de téléportation", null, Collections.singletonList(""), new CmdTpa(this), this);
		new DCommand("Tpno", "/tpno <joueur>", "Refuse la requête de téléportation", null, Collections.singletonList(""), new CmdTpno(), this);
		
		new DCommand("Shop", "/shop", "Ouvre l'interface du shop", null, Collections.singletonList(""), new CmdShop(this), this);
		
		new DCommand("Money", "/money", "Envoie le montant de la bank du joueur", null, Collections.singletonList(""), new CmdMoney(this.rank), this);
		new DCommand("Pay", "/pay <cible>", "Permet de payer un autre joueur", null, Collections.singletonList(""), new CmdPay(this.rank), this);
		new DCommand("Takemoney", "/takemoney <cible> <montant>", "Prend la money d'un joueur (OP)", null, Collections.singletonList(""), new CmdTakeMoney(this.rank, this), this);
		new DCommand("Givemoney", "/givemoney <cible> <montant>", "Donne de la money à un joueur (OP) (Pas pris sur la money de l'executeur de la commande)", null, Collections.singletonList(""), new CmdGiveMoney(this.rank, this), this);
		new DCommand("Purgemoney", "/purgemoney <cible>", "Remet la money du joueur au montant de départ (50 $) (OP)", null, Collections.singletonList(""), new CmdPurgeMoney(rank, this), this);
		new DCommand("Setmoney", "/setmoney <cible> <montant>", "Met le montant de la bank de la cible au montant indiqué", null, Collections.singletonList(""), new CmdSetMoney(rank, this), this);
		new DCommand("Baltop", "/baltop", "Affiche le top 10 des joueurs les plus richent sur le serveur", null, Collections.singletonList("/moneytop"), new CmdBaltop(), this);
		
		new DCommand("Home", "/home <nom>", "Teleporte l'executeur de la commande au home choisi à partir du nom indiqué", null, Collections.singletonList(""), new CmdHome(rank, this), this);
		new DCommand("Delhome", "/delhome <nom>", "Supprime le home indiqué dans la commande de la liste des homes du joueur", null, Collections.singletonList(""), new CmdHome(rank, this), this);
		new DCommand("Sethome", "/sethome <nom>", "Ajouter le home indiqué dans la commande de la liste des homes du joueur", null, Collections.singletonList(""), new CmdHome(rank, this), this);
		new DCommand("Homeinfo", "/homeinfo", "Affiche la liste des homes positionés", null, Collections.singletonList(""), new CmdHome(rank, this), this);
		
		new DCommand("Combattime", "/combattime", "Affiche le temps restant en combat", null, Collections.singletonList(""), new CmdCombatTime(), this);
		
		new DCommand("Promote", "/promote <id> <joueur>", "Promouvoir un joueur au rang indiqué avec l'id", null, Collections.singletonList(""), new CmdPromote(rank, this), this);
		new DCommand("Demote", "/demote <joueur>", "Remettre le rang d'un joueur à 0", null, Collections.singletonList(""), new CmdPromote(rank, this), this);
		new DCommand("Rankinfo", "/rankinfo", "Affiche les infos sur les rangs", null, Collections.singletonList(""), new CmdPromote(rank, this), this);
		
		new DCommand("Kit", "/kit", "Donne le kit au joueur coorespondant à son grade", null, Collections.singletonList(""), new CmdKit(), this);
		
		rank.initScoreboard();
		
		getServer().getPluginManager().registerEvents(new PlayerListener(rank), this);
		getServer().getPluginManager().registerEvents(new ShopListener(), this);
		getServer().getPluginManager().registerEvents(new PermissionsManager(this), this);
		getServer().getPluginManager().registerEvents(new DisconnectCombat(), this);
	}
	
	@Override
	public void onDisable() 
	{	
		System.out.println("==== AlteryaCore -> OFF ====");
	}
}
