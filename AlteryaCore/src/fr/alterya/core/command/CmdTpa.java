package fr.alterya.core.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.alterya.core.LogType;
import fr.alterya.core.MainCore;

public class CmdTpa extends BukkitRunnable implements CommandExecutor
{
	public static List<String> requestedPlayers = new ArrayList<>();
	public static List<String> requestSenderPlayers = new ArrayList<>();
	
	public static int timer = 0;
	
	public static Player player1;
	public static Player target1;
	
	public MainCore mainCore;
	
	public CmdTpa(MainCore mainCore) {
		this.mainCore = mainCore;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		Player player0 = (Player) sender;
		
		if(player0 == Bukkit.getPlayer(args[0])) {
			player0.sendMessage(MainCore.prefix + "§2Vous§e ne pouvez pas vous téléporter à vous même.");
			return true;
		}
		Player target0 = Bukkit.getPlayer(args[0]);
		
		if(message.equalsIgnoreCase("tpa")) {
			if(args[0] == null) {
				player0.sendMessage(MainCore.prefix + "La commande est /tpa <joueur>.");
				return true;
			}
			
			//Créer les joueurs
			Player player = (Player) sender;
			Player target = Bukkit.getPlayer(args[0]);
			
			player1 = player;
			target1 = target;
			
			//Envoyer les messages
			player.sendMessage(MainCore.prefix + "§eVous avez demander à §2" + target.getName() + " §esi vous pouvez vous téléporter à sa position.");
			
			target.sendMessage(MainCore.prefix + "§eLe joueur §2" + target.getName() + "§e vous demande si il peut se téléporter à votre location.");
			target.sendMessage("§eFaites §2/tpyes §epour accepter.");
			target.sendMessage("§eOu faites §2/tpno §epour refuser.");
			
			MainCore.log(LogType.INFO, "Le joueur " + player.getDisplayName() + " a demander à " + target.getDisplayName() + " si il peut se téléport à sa postion.");
			
			//Ajouter les joueurs dans les listes
			requestSenderPlayers.add(player.getUniqueId().toString());
			requestedPlayers.add(target.getUniqueId().toString());
			
			return true;
		}else if(message.equalsIgnoreCase("tpyes") && requestSenderPlayers.contains(player0.getUniqueId().toString()) || requestedPlayers.contains(target0.getUniqueId().toString())) {
			Player player = (Player) sender;
			if(args[0] == null) {
				player0.sendMessage(MainCore.prefix + "La commande est /tpyes <joueur>.");
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			
			//Envoyer les messages
			target.sendMessage(MainCore.prefix + "§eRequête acceptée, le joueur §2" + player.getName() + "§e sera téléporter à vous dans§2 5§e sec.");
			player.sendMessage(MainCore.prefix + "§eLe joueur §2" + target.getName() + "§e a accepter votre demande, vous serez téléporter dans§2 5§e sec.");

			//Démarre le timer de 5 sec avant de se faire téléporter
			this.runTaskTimer(mainCore, 0, 20);
			
			player1 = player;
			target1 = target;
			
			//Retire les joueurs des listes
			requestSenderPlayers.remove(player.getUniqueId().toString());
			requestedPlayers.remove(target.getUniqueId().toString());
			
			MainCore.log(LogType.INFO, "Le joueur " + player.getDisplayName() + " s'est téléporter à " + target.getDisplayName() + ".");
			
			return true;
		}
		return false;
	}

	//Void pour confirmer la téléportation
	public void confirmTeleport(Player player, Player target) {
		player.teleport(target.getLocation());
			
		target.sendMessage(MainCore.prefix + "§eLe joueur §2" + player.getName() + "§e a été téléporter.");
		player.sendMessage(MainCore.prefix + "§eVous avez été téléporter à §2" + target.getName() + "§e avec succès.");
	}
	
	public void cancelTeleport() {
		target1.sendMessage(MainCore.prefix + "§eLe joueur §2" + player1.getDisplayName() + "§e a annulé la requête.");
		requestedPlayers.remove(target1.getUniqueId().toString());
		requestSenderPlayers.remove(player1.getUniqueId().toString());
		player1.sendMessage(MainCore.prefix + "§2Vous§e avez fait un mouvement, requête de téléportation annulée.");
		cancel();
	}
	
	@Override
	public void run()
	{
		if(timer >= 5) {
			confirmTeleport(player1, target1);
			cancel();
		}
		timer ++;
	}
}
