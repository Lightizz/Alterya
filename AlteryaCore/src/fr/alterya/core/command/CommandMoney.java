package fr.alterya.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.Main;
import fr.alterya.core.money.Money;

/*
Author and resp of the money: Lightiz
*/

public class CommandMoney implements CommandExecutor
{
	Money money;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		Player player = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("money") && sender instanceof Player) 
		{
			//Get the money of local player
			player.sendMessage(Main.prefix + "Money : " + money.getPlayerMoney(player.getUniqueId().toString()));
			
			//Set the money of annother player (must be op or admin or resp)
			if(player.isOp() == true && args[0] == "set" && args[1] != null && args[2] != null) {
				
				String targetName = args[1];
				Player target = player.getServer().getPlayer(targetName);
				
				String moneyToSetS = args[2];
				double moneyToSetD = Double.valueOf(moneyToSetS);
				
				money.setPlayerMoney(target.getUniqueId().toString(), moneyToSetD);
				
				return true;
				
			//Get the money of another player (must be op or admin or resp)
			}else if(player.isOp() == true && args[0] != null) {
				
				String targetName = args[0];
				Player target = player.getServer().getPlayer(targetName);
				
				player.sendMessage("Money de " + targetName + " : " + money.getPlayerMoney(target.getUniqueId().toString()));
				
				return true;
			}
		}
		
		return false;
	}
}
