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
		if(command.getName().equalsIgnoreCase("combattime")) {
			Player player = (Player) sender;
			if (sender instanceof Player && DisconnectCombat.isOnCombat() == true && DisconnectCombat.isOnCombat_ == true) {
				player.sendMessage("Vous �tes en combat, il vous reste " + DisconnectCombat.timer + " sec, une fois � 0 si vous ne vous faites pas attaquer, vous ne serez plsu en combat");
				return true;
			}else if (sender instanceof Player && DisconnectCombat.isOnCombat() == false && DisconnectCombat.isOnCombat_ == false) {
				player.sendMessage("Vous n'�tes pas en combat.");
				return true;
			}else {
				player.sendMessage("<b>Une erreur est survenue : 0C. Veuillez contacter un Administrateur, un Responsable ou un D�veloppeur en donnant l'ID de l'erreur.");
				return true;
			}
		}
		return false;
	}
}
