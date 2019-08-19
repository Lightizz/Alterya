package fr.alterya.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.Main;
import fr.alterya.core.home.HomeManager;
import fr.alterya.rank.Rank;
import fr.alterya.rank.RankList;

public class CmdHome implements CommandExecutor
{	
	Main main;
	
	Rank rank;
	
	public CmdHome(Rank rank, Main main) {
		this.rank = rank;
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{		
		Player player = (Player) sender;
		
		//	/sethome <nom>
		if(command.getName().equalsIgnoreCase("sethome")) {
			if(!(sender instanceof Player)) {
				player.sendMessage(Main.prefix + "Vous devez être un joueur !");
				return true;
			}
			
			if(args.length != 1) {
				player.sendMessage(Main.prefix + "La commande est /sethome <nom>");
				return true;
			}
			
			if(args.length == 1) {
				HomeManager homeManager = new HomeManager(player.getUniqueId().toString());
			
				if(homeManager.getHomes().size() >= 2 && main.rank.getPlayerRank(player.getUniqueId().toString()) == RankList.JOUEUR) {
					player.sendMessage(Main.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 2 && main.rank.getPlayerRank(player.getUniqueId().toString()) == RankList.JOUEUR) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a été crée à votre position.");
					return true;
				}	
				
				if(homeManager.getHomes().size() >= 4 && main.rank.getPlayerRank(player.getUniqueId().toString()) == RankList.SOUVENIR) {
					player.sendMessage(Main.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 4 && main.rank.getPlayerRank(player.getUniqueId().toString()) == RankList.SOUVENIR) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a été crée à votre position.");
					return true;
				}
				
				if(homeManager.getHomes().size() >= 6 && main.rank.getPlayerRank(player.getUniqueId().toString()) == RankList.MEMOIRE) {
					player.sendMessage(Main.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 6 && main.rank.getPlayerRank(player.getUniqueId().toString()) == RankList.MEMOIRE) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a été crée à votre position.");
					return true;
				}
				
				if(homeManager.getHomes().size() >= 10 && main.rank.getPlayerRank(player.getUniqueId().toString()) == RankList.SAGE) {
					player.sendMessage(Main.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 10 && main.rank.getPlayerRank(player.getUniqueId().toString()) == RankList.SAGE) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a été crée à votre position.");
					return true;
				}
				
				if(homeManager.getHomes().size() >= 20 && main.rank.getPlayerRank(player.getUniqueId().toString()) == RankList.RESPONSABLE) {
					player.sendMessage(Main.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 20 && main.rank.getPlayerRank(player.getUniqueId().toString()) == RankList.RESPONSABLE) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a été crée à votre position.");
					return true;
				}
				
				if(homeManager.getHomes().size() >= Integer.MAX_VALUE && main.rank.getPlayerRank(player.getUniqueId().toString()) == RankList.ADMINISTRATEUR) {
					player.sendMessage(Main.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < Integer.MAX_VALUE && main.rank.getPlayerRank(player.getUniqueId().toString()) == RankList.ADMINISTRATEUR) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a été crée à votre position.");
					return true;
				}
			}
		//	/home <nom>
		}else if(command.getName().equalsIgnoreCase("home")) {
			if(!(sender instanceof Player)) {
				player.sendMessage(Main.prefix + "Vous devez être un joueur !");
				return true;
			}
			
			HomeManager homeManager = new HomeManager(player.getUniqueId().toString());
			
			if(args.length != 1) {				
				player.sendMessage(Main.prefix + "La commande est /home <nom>");
				return true;
			}
			
			if(args.length == 1) {
				if(! homeManager.homeExist(args[0])) {
					player.sendMessage("Ce home n'éxiste pas.");
					return true;
				}else if(homeManager.homeExist(args[0])) {
					player.teleport(homeManager.getHomeLocation(args[0]));
					player.sendMessage("Vous avez été téléporter à votre home " + args[0] + "avec succès !");
					return true;
				}
			}
		//	/delhome <nom>
		}else if(command.getName().equalsIgnoreCase("delhome")) {
			if(!(sender instanceof Player)) {
				player.sendMessage(Main.prefix + "Vous devez être un joueur !");
				return true;
			}
			
			if(args.length != 1) {
				player.sendMessage(Main.prefix + "La commande est /delhome <nom>");
				return true;
			}
			
			if(args.length == 1) {
				HomeManager homeManager = new HomeManager(player.getUniqueId().toString());
				if(! homeManager.homeExist(args[0])) {
					player.sendMessage("Ce home n'éxiste pas.");
					return true;
				}else if(homeManager.homeExist(args[0])) {
					homeManager.deleteHome(args[0]);
					player.sendMessage("Vous avez supprimer votre home " + args[0] + " avec succès !");
					return true;
				}
			}
		//	/homeinfo
		}else if(command.getName().equalsIgnoreCase("homeinfo")) {
			if(!(sender instanceof Player)) {
				player.sendMessage(Main.prefix + "Vous devez être un joueur !");
				return true;
			}
			
			if(args.length == 0) {
				HomeManager homeManager = new HomeManager(player.getUniqueId().toString());
				
				if(homeManager.getHomes() == null) {
					player.sendMessage(Main.prefix + "Vous n'avez aucun home.");
					return true;
				}
				
				String out = "";
				for(String s : homeManager.getHomes()) {
					out = out + " ," + s;
				}
				
				out = out.trim();
				
				player.sendMessage("Vos homes :" + out);
				
				return true;
			}
			
			if(args.length != 0) {
				player.sendMessage(Main.prefix + "La commande est /homeinfo");
				return true;
			}
		}
			
		return false;
	}
}
