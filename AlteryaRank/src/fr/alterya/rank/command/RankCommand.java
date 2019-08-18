package fr.alterya.rank.command;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fr.alterya.rank.Main;
import fr.alterya.rank.Rank;
import fr.alterya.rank.RankList;

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
			if(player.isOp() == true) {
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
					if(player.isOp() == true) {
						
						String id = args[0];
						int id0 = Integer.valueOf(id);
						
						if(id0 == 0) {
							rank.addRank(RankList.JOUEUR, target); 
							player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.JOUEUR.getRankName());
							return true;
						}else if(id0 == 1) {
							rank.addRank(RankList.SOUVENIR, target);
							player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.SOUVENIR.getRankName());
							return true;
						}else if(id0 == 2) {
							rank.addRank(RankList.MEMOIRE, target);
							player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MEMOIRE.getRankName());
							return true;
						}else if(id0 == 3) {
							rank.addRank(RankList.SAGE, target);
							player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.SAGE.getRankName());
							return true;
						}else if(id0 == 4) {
							rank.addRank(RankList.DEVELOPPEUR, target);
							player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.DEVELOPPEUR.getRankName());
							return true;
						}else if(id0 == 5) {
							rank.addRank(RankList.ARCHITECTE, target);
							player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.ARCHITECTE.getRankName());
							return true;
						}else if(id0 == 6) {
							rank.addRank(RankList.GUIDE, target);
							player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.GUIDE.getRankName());
							return true;
						}else if(id0 == 7) {
							rank.addRank(RankList.MODERATEUR, target);
							player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MODERATEUR.getRankName());
							return true;
						}else if(id0 == 8) {
							rank.addRank(RankList.MODERATEUR_PLUS, target);
							player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.MODERATEUR_PLUS.getRankName());
							return true;
						}else if(id0 == 9) {
							rank.addRank(RankList.RESPONSABLE, target);
							player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.RESPONSABLE.getRankName());
							return true;
						}else if(id0 == 10) {
							rank.addRank(RankList.ADMINISTRATEUR, target);
							player.sendMessage(Main.prefix + "Le joueur " + target.getName() + " a reçu le grade de " + RankList.ADMINISTRATEUR.getRankName());
							return true;
						}
					}
				}
			}
		}
		if(command.getName().equalsIgnoreCase("unrank")) {
			Player player = (Player) sender;
			if(args.length != 1 && player.isOp() == true) {
				player.sendMessage(Main.prefix + "La commande est : /unrank <joueur ciblé> !");
			}else if(args.length == 1 && player.isOp() == true) {
				Player target = player.getServer().getPlayer(args[0]);
				rank.deletPlayer(target.getUniqueId().toString());
				player.sendMessage(Main.prefix + target.getName() + " a été dérank !");
				return true;
			}
			if(player.isOp() == false) {
				player.sendMessage(Main.prefix + "Vous n'êtes pas OP, Administrateur ou Responsable : vous ne pouvez pas effectuer cette commande !");
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