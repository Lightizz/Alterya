package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.money.Money;

/*
Author and resp of the money: Lightiz
*/

public class CommandPay implements CommandExecutor
{
	Money money;
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		Player player = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("pay") && sender instanceof Player) {
			String moneyToTransfertString = args[1];
			double moneyToTransfertDouble = Double.valueOf(moneyToTransfertString);
			
			String receiverName = args[0];
			Player receiver = Bukkit.getServer().getPlayer(receiverName);
			
			money.transfertPlayerMoney(player.getUniqueId().toString(), receiver.getUniqueId().toString(), moneyToTransfertDouble);
			
			System.out.println(ChatColor.RED + player.getName() + " à envoyé " + moneyToTransfertDouble + "$ à " + receiver.getName());
			
			return true;
		}

		return false;
	}
}