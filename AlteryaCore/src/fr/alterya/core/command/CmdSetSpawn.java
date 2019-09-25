package fr.alterya.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.MainCore;
import fr.alterya.core.rank.Rank;

public class CmdSetSpawn implements CommandExecutor
{
	Rank rank;
	
	public CmdSetSpawn(Rank rank) {
		this.rank = rank;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		if(message.equalsIgnoreCase("setspawn")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("Vous devez �tre un joueur pour faire cela.");
				return true;
			}
			Player player = (Player) sender;
			if(rank.config.getInt(player.getUniqueId().toString()) >= 9) {
				CmdSpawn.fw.config.createSection("Spawn 1");
				CmdSpawn.fw.setValue("Spawn 1.X", player.getLocation().getX());
				CmdSpawn.fw.setValue("Spawn 1.Y", player.getLocation().getY());
				CmdSpawn.fw.setValue("Spawn 1.Z", player.getLocation().getZ());
				CmdSpawn.fw.saveConfig();
				player.sendMessage(MainCore.prefix + "�aLe spawn �lofficiel �r�aa �t� positionn� � votre postion.");
				return true;
			}
			player.sendMessage(MainCore.prefix + "�aVous n'avez pas le grade requis pour faire cela.");
			return true;
		}
		return false;
	}
}
