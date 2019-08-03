package fr.alterya.pvp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.pvp.Main;
import fr.alterya.pvp.hunt.HuntManager;
import fr.alterya.pvp.task.HuntTimer;
import fr.alterya.rank.Rank;
import fr.alterya.rank.RankList;

/*
Author and resp of the hunt : Lightiz
*/

public class CmdHunt implements CommandExecutor
{
	HuntTimer huntTimer;
	HuntManager huntM;
	
	Rank rank;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		Player player = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("hunt") && sender instanceof Player) {
			
			if(args.length == 0) {
				player.sendMessage(Main.prefix + "Cette commande n'est pas répertorié, faites /help AlteryaPVP pour toutes les commandes sur le plugin PVP !");
			
				return true;
			}else if(args.length == 1) {
				
				String arg1 = args[0];
				
				if(arg1 == "time") {
					player.sendMessage(Main.prefix + "Il reste " + huntTimer.timer + "avant le prochain changement de cible !" );
					return true;
					
				}else if(arg1 == "target") {
					player.sendMessage(Main.prefix + "Le joueur cible actuel est " + huntM.getPlayerTargetedName() + " !");
					return true;
					
				}else if(arg1 == "swap" && rank.getPlayerRank(player).equals(RankList.ADMINISTRATEUR) || rank.getPlayerRank(player).equals(RankList.RESPONSABLE)) {
					player.sendMessage(Main.prefix + "Le changement a ét& éffectué !");
					huntM.swapTargetedPlayer();
					return true;
					
				}
			}
		}
		return false;
	}
}
