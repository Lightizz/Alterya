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
import fr.alterya.core.MainCore;

/*
Author and resp of the rank plugin: Lightiz
*/

public class CmdPromote implements CommandExecutor, TabCompleter {
	
	private final Rank rank;
	
	MainCore main;
	
	public CmdPromote(Rank rank, MainCore main) {
		this.rank = rank;
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args) {
		Player player = (Player) sender;
		// /rankinfo
		if(message.equalsIgnoreCase("rankinfo")) {
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
			
				if(! player.hasPermission(this.main.getConfig().getString("Groups." + "Administrateur" + ".permissions")) || ! player.hasPermission(this.main.getConfig().getString("Groups." + "Responsable" + ".permissions"))) {
					player.sendMessage(MainCore.prefix + "§4Vous n'avez pas un grade suffisant pour effectuer cette commande.");
					return true;
				}
				
				if(id0 == 0 || id == null) {
					if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
						rank.deletPlayer(target.getUniqueId().toString());
						player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.JOUEUR.getRankName());
						System.out.println(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.JOUEUR.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 8) {
					rank.deletPlayer(target.getUniqueId().toString());
					rank.addRank(RankList.SOUVENIR, target);
					player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.SOUVENIR.getRankName());
					System.out.println(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.SOUVENIR.getRankName() + " [Log message]");
					return true;
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 8) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.MODERATEUR_PLUS) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.MEMOIRE, target);
						player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MEMOIRE.getRankName());
						System.out.println(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MEMOIRE.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 8) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.MODERATEUR_PLUS) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.SAGE, target);
						player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.SAGE.getRankName());
						System.out.println(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.SAGE.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.DEVELOPPEUR, target);
						player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.DEVELOPPEUR.getRankName());
						System.out.println(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.DEVELOPPEUR.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.ARCHITECTE, target);
						player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.ARCHITECTE.getRankName());
						System.out.println(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.ARCHITECTE.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.GUIDE, target);
						player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.GUIDE.getRankName());
						System.out.println(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.GUIDE.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.MODERATEUR, target);
						player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MODERATEUR.getRankName());
						System.out.println(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MODERATEUR.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.MODERATEUR_PLUS, target);
						player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MODERATEUR_PLUS.getRankName());
						System.out.println(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MODERATEUR_PLUS.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.RESPONSABLE, target);
						player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.RESPONSABLE.getRankName());
						System.out.println(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.RESPONSABLE.getRankName() + " [Log message]");
						return true;
					}
				}else if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) == RankList.RESPONSABLE) {
						rank.deletPlayer(target.getUniqueId().toString());
						rank.addRank(RankList.ADMINISTRATEUR, target);
						player.sendMessage(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.ADMINISTRATEUR.getRankName());
						System.out.println(MainCore.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.ADMINISTRATEUR.getRankName() + " [Log message]");
						return true;
					}
				}
			}	
			// /r demote <player>
			if(message.equalsIgnoreCase("demote")) {
				if(rank.config.getInt(player.getUniqueId().toString()) < 9) {
					player.sendMessage(MainCore.prefix + "Vous n'êtes pas OP, Administrateur ou Responsable, vous ne pouvez pas effectuer cette commande !");
					return true;
				}
				if(args.length != 2 && rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					player.sendMessage(MainCore.prefix + "La commande est : /r demote <joueur ciblé> !");
				}else if(args.length == 2 && rank.config.getInt(player.getUniqueId().toString()) >= 9) {
					Player target = Bukkit.getPlayer(args[0]);
					rank.deletPlayer(target.getUniqueId().toString());
					player.sendMessage(MainCore.prefix + target.getName() + " a été dérank !");
					System.out.println(MainCore.prefix + target.getName() + " a été dérank !" + " [Log message]");
					return true;
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