package fr.alterya.core.command;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fr.alterya.core.LogType;
import fr.alterya.core.MainCore;
import fr.alterya.core.rank.Rank;
import fr.alterya.core.rank.RankList;

/*
Author and resp of the rank plugin: Lightiz
*/

public class CmdRank implements CommandExecutor, TabCompleter {
	
	private final Rank rank;
	MainCore main;
	
	public CmdRank(Rank rank, MainCore main) {
		this.rank = rank;
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args) {
		Player player = (Player) sender;
		// /rankinfo
		if(message.equalsIgnoreCase("rankinfo")) {
			if(rank.config.getInt(player.getUniqueId().toString()) < 8) {
				player.sendMessage(MainCore.prefix + "§4Vous n'êtes pas OP, Administrateur ou Responsable, vous ne pouvez pas effectuer cette commande !");
				return true;
			}
			
			String message1 = MainCore.prefix + "Voici les info sur les rangs et groupes de permissions : ";
			player.sendMessage(message1);
			
			for(RankList rankList : RankList.values()) {
				String part = "";
				part = "" + rankList.getPrefixColor() + rankList.getRankName() + " §r: " + "ID = " + String.valueOf(rankList.GetId()) + " ; Power = " + String.valueOf(rankList.getPower()) + " ; Prefix = " + rankList.getPrefix() + " \n"; 
				player.sendMessage(part);
			}
			return true;
			// /promote <ID> <joueur>
		}else if(message.equalsIgnoreCase("promote")) {
			Player target = Bukkit.getPlayer(args[1]);
			String id = args[0];
			int id0 = Integer.valueOf(id);
			
			if(rank.config.getInt(player.getUniqueId().toString()) < 9) {
				player.sendMessage(MainCore.prefix + "§4Vous n'êtes pas OP, Administrateur ou Responsable, vous ne pouvez pas effectuer cette commande !");
				return true;
			}
			
			if(id0 == 0 || id == null) { //Regarder le grade afficher
				if(rank.config.getInt(player.getUniqueId().toString()) >= 8) { //Regarder le grade de l'expéditeur de la commande
					rank.deletPlayer(target.getUniqueId().toString()); //Supprimer le joueur des listes pour éviter les bugs
					//Message au joueur
					player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.JOUEUR.getRankName());
					//Message pour les logs
					MainCore.log(LogType.WARNING, "Le joueur " + target.getName() + " a reçu le grade de " + RankList.JOUEUR.getRankName());
					return true;
				}
			}else if(id0 == 1) {
				if(rank.config.getInt(player.getUniqueId().toString()) >= 8) {
					rank.deletPlayer(target.getUniqueId().toString());
					rank.addRank(RankList.SOUVENIR, target); /* Donner le rang */
					player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.SOUVENIR.getRankName());
					MainCore.log(LogType.WARNING, "Le joueur " + target.getName() + " a reçu le grade de " + RankList.SOUVENIR.getRankName());
					return true;
				}
			}else if(id0 == 2) {
				if(rank.config.getInt(player.getUniqueId().toString()) >= 8) {
					rank.deletPlayer(target.getUniqueId().toString());
					rank.addRank(RankList.MEMOIRE, target);
					player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MEMOIRE.getRankName());
					MainCore.log(LogType.WARNING, "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MEMOIRE.getRankName());
					return true;
				}
			}else if(id0 == 3) {
				if(rank.config.getInt(player.getUniqueId().toString()) >= 8) {
					rank.deletPlayer(target.getUniqueId().toString());
					rank.addRank(RankList.SAGE, target);
					player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.SAGE.getRankName());
					MainCore.log(LogType.WARNING, "Le joueur " + target.getName() + " a reçu le grade de " + RankList.SAGE.getRankName());
					return true;
				}
			}else if(id0 == 4) {
				if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					rank.deletPlayer(target.getUniqueId().toString());
					rank.addRank(RankList.DEVELOPPEUR, target);
					player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.DEVELOPPEUR.getRankName());
					MainCore.log(LogType.WARNING, "Le joueur " + target.getName() + " a reçu le grade de " + RankList.DEVELOPPEUR.getRankName());
					return true;
				}
			}else if(id0 == 5) {
				if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					rank.deletPlayer(target.getUniqueId().toString());
					rank.addRank(RankList.ARCHITECTE, target);
					player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.ARCHITECTE.getRankName());
					MainCore.log(LogType.WARNING, "Le joueur " + target.getName() + " a reçu le grade de " + RankList.ARCHITECTE.getRankName());System.out.println(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.ARCHITECTE.getRankName() + " [Log message]");
					return true;
				}
			}else if(id0 == 6) {
				if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					rank.deletPlayer(target.getUniqueId().toString());
					rank.addRank(RankList.GUIDE, target);
					player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.GUIDE.getRankName());
					MainCore.log(LogType.WARNING, "Le joueur " + target.getName() + " a reçu le grade de " + RankList.GUIDE.getRankName());
					return true;
				}
			}else if(id0 == 7) {
				if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					rank.deletPlayer(target.getUniqueId().toString());
					rank.addRank(RankList.MODERATEUR, target);
					player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MODERATEUR.getRankName());
					MainCore.log(LogType.WARNING, "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MODERATEUR.getRankName());
					return true;
				}
			}else if(id0 == 8) {
				if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					rank.deletPlayer(target.getUniqueId().toString());
					rank.addRank(RankList.MODERATEUR_PLUS, target);
					player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MODERATEUR_PLUS.getRankName());
					MainCore.log(LogType.WARNING, "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MODERATEUR_PLUS.getRankName());
					return true;
				}
			}else if(id0 == 9) {
				if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					rank.deletPlayer(target.getUniqueId().toString());
					rank.addRank(RankList.RESPONSABLE, target);
					player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.RESPONSABLE.getRankName());
					MainCore.log(LogType.WARNING, "Le joueur " + target.getName() + " a reçu le grade de " + RankList.RESPONSABLE.getRankName());
					return true;
				}
			}else if(id0 == 10) {
				if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					rank.deletPlayer(target.getUniqueId().toString());
					rank.addRank(RankList.ADMINISTRATEUR, target);
					player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.ADMINISTRATEUR.getRankName());
					MainCore.log(LogType.WARNING, "Le joueur " + target.getName() + " a reçu le grade de " + RankList.ADMINISTRATEUR.getRankName());
					return true;
				}
			}
		}	
			// /r demote <player>
		if(message.equalsIgnoreCase("demote")) {
			if(rank.config.getInt(player.getUniqueId().toString()) < 9) {
				player.sendMessage(MainCore.prefix + "§4Vous n'êtes pas OP, Administrateur ou Responsable, vous ne pouvez pas effectuer cette commande !");
				return true;
			}
			if(args.length != 1 && rank.config.getInt(player.getUniqueId().toString()) >= 9) {
				player.sendMessage(MainCore.prefix + "La commande est : /r demote <joueur ciblé> !");
				return true;
			}else if(args.length == 1 && rank.config.getInt(player.getUniqueId().toString()) >= 9) {
				Player target = Bukkit.getPlayer(args[0]);
				rank.deletPlayer(target.getUniqueId().toString());
				player.sendMessage(MainCore.prefix + target.getName() + " a été dérank !");
				MainCore.log(LogType.WARNING, "Le joueur " + target.getName() + " a été dérank !");
				return true;
			}
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String message, String[] args) { return null; }

	public Rank getRank() { return rank; }
}