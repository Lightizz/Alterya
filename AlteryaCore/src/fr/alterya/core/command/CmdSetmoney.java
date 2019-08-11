package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.alterya.core.Main;
import fr.alterya.core.manager.AccountManager;
import fr.alterya.core.manager.account.AccountException;
import fr.alterya.core.manager.account.Holdings;

public class CmdSetmoney implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length < 2) return false;
		
		int amount = 0;
		
		try {
			
			amount = Integer.parseInt(args[1]);
			
		} catch(NumberFormatException e) {
			
			return false;
			
		}
		
		OfflinePlayer reciever = Bukkit.getOfflinePlayer(args[0]);
		
		try {
			
			AccountManager.getAccount(reciever.getUniqueId().toString(), "default").getHoldings().setBalance(amount);
			
			sender.sendMessage("&aYou have set " + reciever.getName() + "(s) to &e" + Holdings.format(amount));
			
		} catch (AccountException e) {
			
			sender.sendMessage(e.getMessage());
			
		}
		
		return true;
		
	}

}
