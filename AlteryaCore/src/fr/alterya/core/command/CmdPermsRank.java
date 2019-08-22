package fr.alterya.core.command;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fr.alterya.core.rank.Rank;
import fr.alterya.core.rank.RankList;
import fr.alterya.core.Main;

/*
Author and resp of the rank plugin: Lightiz
*/

public class CmdPermsRank implements CommandExecutor, TabCompleter {
	
	private final Rank rank;
	
	Main main;
	
	public CmdPermsRank(Rank rank, Main main) {
		this.rank = rank;
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args) {
		Player player = (Player) sender;
		// /r info
		if(command.getName().equalsIgnoreCase("r") && command.getName().equalsIgnoreCase("rank")) {
			if(args[0] == "info") {
				String message1 = Main.prefix + "§1Voici les info sur les rangs et groupes de permissions : ";
				for(RankList rankList : RankList.values()) {
					String part = "";
					part = "" + rankList.getPrefixColor() + rankList.getRankName() + " : " + "ID : " + String.valueOf(rankList.GetId()) + " ; Power : " + String.valueOf(rankList.getPower()) + " ; Prefix : " + rankList.getPrefix() + " \n"; 
					player.sendMessage(message1 + part);
				}
				return true;
			// /r promote <ID> <joueur>
			}else if(args[0] == "promote") {
				Player target = Bukkit.getPlayer(args[2]);
				String id = args[1];
				int id0 = Integer.valueOf(id);
			
				if(! player.hasPermission(this.main.getConfig().getString("Groups." + this.main.rank.getRankById(10).getRankName() + ".permissions")) || ! player.hasPermission(this.main.getConfig().getString("Groups." + this.main.rank.getRankById(9).getRankName() + ".permissions"))) {
					player.sendMessage(Main.prefix + "§4Vous n'avez pas un grade suffisant pour effectuer cette commande.");
					return true;
				}
				
				if(id0 == 0 || id == null) {
					if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
						rank.deletPlayer(target.getUniqueId().toString());
						player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.JOUEUR.getRankName());
						System.out.println(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.JOUEUR.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.SOUVENIR, target);
						player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.SOUVENIR.getRankName());
						System.out.println(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.SOUVENIR.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.MEMOIRE, target);
						player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MEMOIRE.getRankName());
						System.out.println(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MEMOIRE.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.SAGE, target);
						player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.SAGE.getRankName());
						System.out.println(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.SAGE.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.DEVELOPPEUR, target);
						player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.DEVELOPPEUR.getRankName());
						System.out.println(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.DEVELOPPEUR.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.ARCHITECTE, target);
						player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.ARCHITECTE.getRankName());
						System.out.println(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.ARCHITECTE.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.GUIDE, target);
						player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.GUIDE.getRankName());
						System.out.println(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.GUIDE.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.MODERATEUR, target);
						player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MODERATEUR.getRankName());
						System.out.println(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MODERATEUR.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.MODERATEUR_PLUS, target);
						player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MODERATEUR_PLUS.getRankName());
						System.out.println(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MODERATEUR_PLUS.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.RESPONSABLE, target);
						player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.RESPONSABLE.getRankName());
						System.out.println(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.RESPONSABLE.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.ADMINISTRATEUR, target);
						player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.ADMINISTRATEUR.getRankName());
						System.out.println(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.ADMINISTRATEUR.getRankName() + " [Log message]");
						return true;
					}
				}
			}	
			// /r demote <player>
			if(args[0] == "demote") {
				if(rank.config.getInt(player.getUniqueId().toString()) < 9) {
					player.sendMessage(Main.prefix + "Vous n'êtes pas OP, Administrateur ou Responsable, vous ne pouvez pas effectuer cette commande !");
					return true;
				}
				if(args.length != 2 && rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					player.sendMessage(Main.prefix + "La commande est : /r demote <joueur ciblé> !");
				}else if(args.length == 2 && rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					Player target = Bukkit.getPlayer(args[1]);
					rank.deletPlayer(target.getUniqueId().toString());
					player.sendMessage(Main.prefix + target.getName() + " a été dérank !");
					System.out.println(Main.prefix + target.getName() + " a été dérank !" + " [Log message]");
					return true;
				}
			}
		}
		return false;
	}


	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String message, String[] args) {
		return null;
	}

	public Rank getRank() {
		return rank;
	}

}