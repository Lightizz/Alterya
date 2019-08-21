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

public class RankCommand implements CommandExecutor, TabCompleter {
	
	private final Rank rank;
	
	public RankCommand(Rank rank) {
		this.rank = rank;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args) {
		if(command.getName().equalsIgnoreCase("rank")) {
			Player player = (Player) sender;
				if(args.length == 0) {
					player.sendMessage(Main.prefix + "La commande est /rank <id du rang> <joueur cibler> (Cela sert à donner un rang à un joueur) ou /rank info (Cela permet d'obtenir toutes les informations sur les rangs)");
				}
				
				if(args.length == 1 && args[0] == "info") {
					player.sendMessage(Main.prefix + "§eVoici les rangs présents sur le serveur §r:");
					player.sendMessage("-Joueur : ID = 0 ; Power = 1 ; Nombre de home : 2");
					player.sendMessage("-§aSouvenir §r: ID = 1 ; Power = 3 ; Nombre de home : 4");
					player.sendMessage("-§1Mémoire §r: ID = 2 ; Power = 5 ; Nombre de home : 6");
					player.sendMessage("-§dSage §r: ID = 3 ; Power = 10 ; Nombre de home : 10");
					player.sendMessage("-§5Développeur §r: ID = 4 ; Power = 15 ; Nombre de home : 4");
					player.sendMessage("-§5Architecte §r: ID = 5 ;  Power = 15 ; Nombre de home : 4");
					player.sendMessage("-§aGuide §r: ID = 6 ; Power = 20 ; Nombre de home : 6");
					player.sendMessage("-§eModérateur §r: ID = 7 ; Power = 40 ; Nombre de home : 10");
					player.sendMessage("-§6Modérateur+ §r: ID = 8 ; Power = 60 ; Nombre de home : 15");
					player.sendMessage("-§4Responsable §r: ID = 9 ; Power = 80 ; Nombre de home : ∞");
					player.sendMessage("-§4Administrateur §r: ID = 10 ; Power = 100 ; Nombre de home : ∞");
				}
				
				if(args.length == 2) 
				{		
					Player target = Bukkit.getPlayer(args[1]);
					String id = args[0];
					int id0 = Integer.valueOf(id);
					
					if(rank.getPlayerRank(player.getUniqueId().toString(), player) != RankList.ADMINISTRATEUR || rank.getPlayerRank(player.getUniqueId().toString(), player) != RankList.RESPONSABLE) {
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
			}
		if(command.getName().equalsIgnoreCase("unrank")) {
			Player player = (Player) sender;
			if(args.length != 1 && rank.config.getInt(player.getUniqueId().toString()) >= 9) {
				player.sendMessage(Main.prefix + "La commande est : /unrank <joueur ciblé> !");
			}else if(args.length == 1 && rank.config.getInt(player.getUniqueId().toString()) >= 9) {
				Player target = Bukkit.getPlayer(args[0]);
				rank.deletPlayer(target.getUniqueId().toString());
				player.sendMessage(Main.prefix + target.getName() + " a été dérank !");
				System.out.println(Main.prefix + target.getName() + " a été dérank !" + " [Log message]");
				return true;
			}
			if(rank.config.getInt(player.getUniqueId().toString()) < 9) {
				player.sendMessage(Main.prefix + "Vous n'êtes pas OP, Administrateur ou Responsable, vous ne pouvez pas effectuer cette commande !");
				return true;
			}
		}
		return false;
	}


	@Override
	public List<String> onTabComplete(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		return null;
	}

	public Rank getRank() {
		return rank;
	}

}