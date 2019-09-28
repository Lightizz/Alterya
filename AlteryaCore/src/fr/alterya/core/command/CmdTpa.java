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
			player0.sendMessage(MainCore.prefix + "�2Vous�e ne pouvez pas vous t�l�porter � vous m�me.");
			return true;
		}
		Player target0 = Bukkit.getPlayer(args[0]);
		
		if(message.equalsIgnoreCase("tpa")) {
			if(args[0] == null) {
				player0.sendMessage(MainCore.prefix + "La commande est /tpa <joueur>.");
				return true;
			}
			
			//Cr�er les joueurs
			Player player = (Player) sender;
			Player target = Bukkit.getPlayer(args[0]);
			
			player1 = player;
			target1 = target;
			
			//Envoyer les messages
			player.sendMessage(MainCore.prefix + "�eVous avez demander � �2" + target.getName() + " �esi vous pouvez vous t�l�porter � sa position.");
			
			target.sendMessage(MainCore.prefix + "�eLe joueur �2" + target.getName() + "�e vous demande si il peut se t�l�porter � votre location.");
			target.sendMessage("�eFaites �2/tpyes �epour accepter.");
			target.sendMessage("�eOu faites �2/tpno �epour refuser.");
			
			MainCore.log(LogType.INFO, "Le joueur " + player.getDisplayName() + " a demander � " + target.getDisplayName() + " si il peut se t�l�port � sa postion.");
			
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
			target.sendMessage(MainCore.prefix + "�eRequ�te accept�e, le joueur �2" + player.getName() + "�e sera t�l�porter � vous dans�2 5�e sec.");
			player.sendMessage(MainCore.prefix + "�eLe joueur �2" + target.getName() + "�e a accepter votre demande, vous serez t�l�porter dans�2 5�e sec.");

			//D�marre le timer de 5 sec avant de se faire t�l�porter
			this.runTaskTimer(mainCore, 0, 20);
			
			player1 = player;
			target1 = target;
			
			//Retire les joueurs des listes
			requestSenderPlayers.remove(player.getUniqueId().toString());
			requestedPlayers.remove(target.getUniqueId().toString());
			
			MainCore.log(LogType.INFO, "Le joueur " + player.getDisplayName() + " s'est t�l�porter � " + target.getDisplayName() + ".");
			
			return true;
		}
		return false;
	}

	//Void pour confirmer la t�l�portation
	public void confirmTeleport(Player player, Player target) {
		player.teleport(target.getLocation());
			
		target.sendMessage(MainCore.prefix + "�eLe joueur �2" + player.getName() + "�e a �t� t�l�porter.");
		player.sendMessage(MainCore.prefix + "�eVous avez �t� t�l�porter � �2" + target.getName() + "�e avec succ�s.");
	}
	
	public void cancelTeleport() {
		target1.sendMessage(MainCore.prefix + "�eLe joueur �2" + player1.getDisplayName() + "�e a annul� la requ�te.");
		requestedPlayers.remove(target1.getUniqueId().toString());
		requestSenderPlayers.remove(player1.getUniqueId().toString());
		player1.sendMessage(MainCore.prefix + "�2Vous�e avez fait un mouvement, requ�te de t�l�portation annul�e.");
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
