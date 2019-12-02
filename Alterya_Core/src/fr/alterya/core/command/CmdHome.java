package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.MainCore;
import fr.alterya.core.home.HomeManager;
import fr.alterya.core.rank.Rank;

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
				player.sendMessage(MainCore.prefix + "Vous devez être un joueur !");
				return true;
			}
			
			if(args.length != 1) {
				player.sendMessage(MainCore.prefix + "La commande est /sethome <nom>");
				return true;
			}
			
			if(args.length == 1) {
				HomeManager homeManager = new HomeManager(player.getUniqueId().toString());
			
				//Regarder les grades du joueur pour en déduire son nombre maximum de home
				if(homeManager.getHomes().size() >= 2 && rank.config.getInt(player.getUniqueId().toString()) == 0) {
					player.sendMessage(MainCore.prefix + "Vous avez atteint votre limite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 2 && rank.config.getInt(player.getUniqueId().toString()) == 0) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a été crée à votre position.");
					return true;
				}	
				
				if(homeManager.getHomes().size() >= 4 && rank.config.getInt(player.getUniqueId().toString()) == 1) {
					player.sendMessage(MainCore.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 4 && rank.config.getInt(player.getUniqueId().toString()) == 1) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a été crée à votre position.");
					return true;
				}
				
				if(homeManager.getHomes().size() >= 6 && rank.config.getInt(player.getUniqueId().toString()) == 2) {
					player.sendMessage(MainCore.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 6 && rank.config.getInt(player.getUniqueId().toString()) == 2) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a été crée à votre position.");
					return true;
				}
				
				if(homeManager.getHomes().size() >= 10 && rank.config.getInt(player.getUniqueId().toString()) == 3) {
					player.sendMessage(MainCore.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 10 && rank.config.getInt(player.getUniqueId().toString()) == 3) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a été crée à votre position.");
					return true;
				}
				
				if(homeManager.getHomes().size() >= 4 && rank.config.getInt(player.getUniqueId().toString()) == 4 || rank.config.getInt(player.getUniqueId().toString()) == 5) {
					player.sendMessage(MainCore.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 4 && rank.config.getInt(player.getUniqueId().toString()) == 4 || rank.config.getInt(player.getUniqueId().toString()) == 5) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a été crée à votre position.");
					return true;
				}
				
				if(homeManager.getHomes().size() >= 5 && rank.config.getInt(player.getUniqueId().toString()) == 6 || rank.config.getInt(player.getUniqueId().toString()) == 7) {
					player.sendMessage(MainCore.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 5 && rank.config.getInt(player.getUniqueId().toString()) == 6 || rank.config.getInt(player.getUniqueId().toString()) == 7) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a été crée à votre position.");
					return true;
				}
				
				if(homeManager.getHomes().size() >= 6 && rank.config.getInt(player.getUniqueId().toString()) == 7) {
					player.sendMessage(MainCore.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 6 && rank.config.getInt(player.getUniqueId().toString()) == 7) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a été crée à votre position.");
					return true;
				}
				
				if(homeManager.getHomes().size() >= 15 && rank.config.getInt(player.getUniqueId().toString()) == 9) {
					player.sendMessage(MainCore.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 15 && rank.config.getInt(player.getUniqueId().toString()) == 9) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a été crée à votre position.");
					return true;
				}
				
				if(homeManager.getHomes().size() >= 20 && rank.config.getInt(player.getUniqueId().toString()) == 10) {
					player.sendMessage(MainCore.prefix + "Vous avez atteint votrel imite de home.");
					return true;
				}else if(homeManager.getHomes().size() < 20 && rank.config.getInt(player.getUniqueId().toString()) == 10) {
					homeManager.createHome(player.getLocation(), args[0]);
					player.sendMessage("Le home " + args[0] + " a été crée à votre position.");
					return true;
				}
			}
		//	/home <nom>
		}else if(message.equalsIgnoreCase("home")) {
			if(!(sender instanceof Player)) {
				player.sendMessage(MainCore.prefix + "Vous devez être un joueur !");
				return true;
			}
			HomeManager homeManager = new HomeManager(player.getUniqueId().toString());
			
			if(args.length != 1) { player.sendMessage(MainCore.prefix + "La commande est /home <nom>"); return true; }
			if(args.length == 1) {
				if(! homeManager.homeExist(args[0])) {
					player.sendMessage("Ce home n'éxiste pas.");
					return true;
				}else if(homeManager.homeExist(args[0])) {
					player.teleport(homeManager.getHomeLocation(args[0]));
					player.sendMessage("Vous avez été téléporté à votre home " + args[0] + " avec succès.");
					return true;
				}
			}
		//	/delhome <nom>
		}else if(message.equalsIgnoreCase("delhome")) {
			if(!(sender instanceof Player)) { player.sendMessage(MainCore.prefix + "Vous devez être un joueur !"); return true; }
			
			if(args.length != 1) { player.sendMessage(MainCore.prefix + "La commande est /delhome <nom>"); return true; }
			
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
			
		//	/homeinfo (optionnel : <joueur>, pour voir les homes d'un joueur)
		}else if(message.equalsIgnoreCase("homeinfo")) {
			if(!(sender instanceof Player)) { player.sendMessage(MainCore.prefix + "Vous devez être un joueur !"); return true; }
			
			if(args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if(rank.config.getInt(target.getUniqueId().toString()) <= 7 ) { player.sendMessage(MainCore.prefix + "Vous n'avez pas le rang requis pour faire cela."); return true; }
				HomeManager homeManager = new HomeManager(target.getUniqueId().toString());
				
				//Si la cible choisi n'a aucun home
				if(homeManager.getHomes() == null) { player.sendMessage(MainCore.prefix + "Ce joueur n'a aucun home."); return true; }
				
				String out = "";
				for(String s : homeManager.getHomes()) {
					out = "§a" + s + " §r, " + out;
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
				
				//Si l'expéditeur de la commande n'a aucun home
				if(homeManager.getHomes() == null) { player.sendMessage(MainCore.prefix + "Vous n'avez aucun home."); return true; }
				
				String out = "";
				for(String s : homeManager.getHomes()) {
					out = "§a" + s + " §r, " + out;
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
