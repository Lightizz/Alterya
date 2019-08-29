package fr.alterya.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.util.DisconnectCombat;

public class CmdCombatTime implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		if(message.equalsIgnoreCase("combattime")) {
			Player player = (Player) sender;
			if (sender instanceof Player && DisconnectCombat.isOnCombat(player.getUniqueId().toString()) == true) {
				player.sendMessage("Vous êtes en combat, il vous reste " + DisconnectCombat.onCombatPlayers.get(player.getUniqueId().toString()) + " sec, une fois à 0 si vous ne vous faites pas attaquer, vous ne serez plsu en combat");
				return true;
			}else if (sender instanceof Player && DisconnectCombat.isOnCombat(player.getUniqueId().toString()) == false) {
				player.sendMessage("Vous n'êtes pas en combat.");
				return true;
			}else {
				player.sendMessage("<b>Une erreur est survenue : 0C. Veuillez contacter un Administrateur, un Responsable ou un Développeur en donnant l'ID de l'erreur.");
				return true;
			}
		}
		return false;
	}
}
