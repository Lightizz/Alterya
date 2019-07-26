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
				player.sendMessage(Main.prefix + "La commande est /rank <id du rang> <joueur cibler>  (Cela sert à donner un rang  à un joueur) ou " + "/rank info (Cela permet d'obtenir toutes les informations sur les rangs)");
			}
			
			if(args.length == 1 && args[0] == "info" && player.isOp() == true) {
				player.sendMessage("§4§l[Rank] §eVoici les rangs présents sur le serveur §r:");
				player.sendMessage("-Joueur : ID = 0 ; Power = 0");
				player.sendMessage("-§aSouvenir : ID = 1 ; Power = 3");
				player.sendMessage("-1Mémoire : ID = 2 ; Power = 5");
				player.sendMessage("-§dSage : ID = 3 ; Power = 10");
				player.sendMessage("-§5Développeur §r: ID = 4 ; Power = 15");
				player.sendMessage("-§5Architecte §r: ID = 5 ;  Power = 15");
				player.sendMessage("-§aGuide §r: ID = 6 ; Power = 20");
				player.sendMessage("-§eModérateur §r: ID = 7 ; Power = 40");
				player.sendMessage("-§6Modérateur+ §r: ID = 8 ; Power = 60");
				player.sendMessage("-§4Responsable §r: ID = 9 ; Power = 80");
				player.sendMessage("-§4Administrateur §r: ID = 10 ; Power = 100");
			}
			
			if(args.length == 2) 
			{		
				Player target = Bukkit.getPlayer(args[1]);
				target.getAddress();
				if(player.isOp() == true) {
					
					String id = args[0];
					int id0 = Integer.valueOf(id);
					
					if(id0 == 0) {
						rank.addRank(RankList.JOUEUR, target);
						return true;
					}else if(id0 == 1) {
						rank.addRank(RankList.DEVELOPPEUR, target);
						return true;
					}else if(id0 == 2) {
						rank.addRank(RankList.ARCHITECTE, target);
						return true;
					}else if(id0 == 3) {
						rank.addRank(RankList.GUIDE, target);
						return true;
					}else if(id0 == 4) {
						rank.addRank(RankList.MODERATEUR, target);
						return true;
					}else if(id0 == 5) {
						rank.addRank(RankList.MODERATEUR_PLUS, target);
						return true;
					}else if(id0 == 6) {
						rank.addRank(RankList.RESPONSABLE, target);
						return true;
					}else if(id0 == 7) {
						rank.addRank(RankList.ADMINISTRATEUR, target);
						return true;
					}
				}
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