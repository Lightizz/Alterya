package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.MainCore;
import fr.alterya.core.home.HomeManager;
import fr.alterya.core.rank.Rank;
import fr.alterya.core.rank.RankList;

public class CmdHome implements CommandExecutor
{	
	MainCore main;
	
	Rank rank;
	
	public CmdHome(Rank rank, MainCore main) {
		this.rank = rank;
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{		
		Player player = (Player) sender;
		
		//	/sethome <nom>
		if(message.equalsIgnoreCase("sethome")) {
			if(!(sender instanceof Player)) {
				player.sendMessage(MainCore.prefix + "Vous devez �tre un joueur !");
				return true;
			}
			
			if(args.length != 1) {
				player.sendMessage(MainCore.prefix + "La commande est /sethome <nom>");
				return true;
			}
			
			if(args.length == 1) {
				HomeManager homeManager = new HomeManager(player.getUniqueId().toString());
			
				if(homeManager.getHomes().size() >= 2 && rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.JOUEUR) {
					player.sendMessage(MainCore.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 2 && rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.JOUEUR) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a �t� cr�e � votre position.");
					return true;
				}	
				
				if(homeManager.getHomes().size() >= 4 && rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.SOUVENIR) {
					player.sendMessage(MainCore.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 4 && rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.SOUVENIR) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a �t� cr�e � votre position.");
					return true;
				}
				
				if(homeManager.getHomes().size() >= 6 && rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.MEMOIRE) {
					player.sendMessage(MainCore.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 6 && rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.MEMOIRE) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a �t� cr�e � votre position.");
					return true;
				}
				
				if(homeManager.getHomes().size() >= 10 && rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.SAGE) {
					player.sendMessage(MainCore.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 10 && rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.SAGE) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a �t� cr�e � votre position.");
					return true;
				}
				
				if(homeManager.getHomes().size() >= 20 && rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
					player.sendMessage(MainCore.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 20 && rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a �t� cr�e � votre position.");
					return true;
				}
				
				if(homeManager.getHomes().size() >= Integer.MAX_VALUE && rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR) {
					player.sendMessage(MainCore.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < Integer.MAX_VALUE && rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a �t� cr�e � votre position.");
					return true;
				}
			}
		//	/home <nom>
		}else if(message.equalsIgnoreCase("home")) {
			if(!(sender instanceof Player)) {
				player.sendMessage(MainCore.prefix + "Vous devez �tre un joueur !");
				return true;
			}
			
			HomeManager homeManager = new HomeManager(player.getUniqueId().toString());
			
			if(args.length != 1) {				
				player.sendMessage(MainCore.prefix + "La commande est /home <nom>");
				return true;
			}
			
			if(args.length == 1) {
				if(! homeManager.homeExist(args[0])) {
					player.sendMessage("Ce home n'�xiste pas.");
					return true;
				}else if(homeManager.homeExist(args[0])) {
					player.teleport(homeManager.getHomeLocation(args[0]));
					player.sendMessage("Vous avez �t� t�l�porter � votre home " + args[0] + " avec succ�s !");
					return true;
				}
			}
		//	/delhome <nom>
		}else if(message.equalsIgnoreCase("delhome")) {
			if(!(sender instanceof Player)) {
				player.sendMessage(MainCore.prefix + "Vous devez �tre un joueur !");
				return true;
			}
			
			if(args.length != 1) {
				player.sendMessage(MainCore.prefix + "La commande est /delhome <nom>");
				return true;
			}
			
			if(args.length == 1) {
				HomeManager homeManager = new HomeManager(player.getUniqueId().toString());
				if(! homeManager.homeExist(args[0])) {
					player.sendMessage("Ce home n'�xiste pas.");
					return true;
				}else if(homeManager.homeExist(args[0])) {
					homeManager.deleteHome(args[0]);
					player.sendMessage("Vous avez supprimer votre home " + args[0] + " avec succ�s !");
					return true;
				}
			}
		//	/homeinfo (optionnel : <joueur>, pour voir les homes d'un joueur)
		}else if(message.equalsIgnoreCase("homeinfo")) {
			if(!(sender instanceof Player)) {
				player.sendMessage(MainCore.prefix + "Vous devez �tre un joueur !");
				return true;
			}
			
			if(args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if(rank.config.getInt(target.getUniqueId().toString()) <= 7 ) { player.sendMessage(MainCore.prefix + "Vous n'avez pas le rang requis pour faire cela."); return true; }
				HomeManager homeManager = new HomeManager(target.getUniqueId().toString());
				
				if(homeManager.getHomes() == null) {
					player.sendMessage(MainCore.prefix + "Ce joueur n'a aucun home.");
					return true;
				}
				
				String out = "";
				for(String s : homeManager.getHomes()) {
					out = "�a" + s + " �r, " + out;
				}
				
				if(out.length() <= 0) {
					player.sendMessage(MainCore.prefix + "Ce joueur n'a aucun home.");
					return true;
				}
				
				out = out.trim();
				
				int outLength = out.length();
				outLength --;
				
				out = out.substring(0, outLength);
				
				player.sendMessage("Homes de " + target.getName() + " : " + out);
				
				return true;
			}
			
			if(args.length == 0) {
				HomeManager homeManager = new HomeManager(player.getUniqueId().toString());
				
				if(homeManager.getHomes() == null) {
					player.sendMessage(MainCore.prefix + "Vous n'avez aucun home.");
					return true;
				}
				
				String out = "";
				for(String s : homeManager.getHomes()) {
					out = "�a" + s + " �r, " + out;
				}
				
				if(out.length() <= 0) {
					player.sendMessage(MainCore.prefix + "Vous n'avez aucun home.");
					return true;
				}
				
				out = out.trim();
				
				int outLength = out.length();
				outLength --;
				
				out = out.substring(0, outLength);
				
				player.sendMessage("Vos homes : " + out);
				
				return true;
			}
			
			if(args.length != 0) {
				player.sendMessage(MainCore.prefix + "La commande est /homeinfo");
				return true;
			}
		}
		return false;
	}
}
