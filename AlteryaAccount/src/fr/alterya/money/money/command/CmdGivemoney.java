package fr.alterya.money.money.command;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.alterya.money.money.Main;
import fr.alterya.money.money.manager.AccountManager;
import fr.alterya.money.money.manager.account.AccountException;
import fr.alterya.money.money.manager.account.Holdings;

public class CmdGivemoney extends AccountCommand implements CommandExecutor {

	private Main plugin;
	
	public CmdGivemoney(Main plugin) {
		
		this.plugin = plugin;
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length < 2) return false;
		
		int amount = 0;
		
		try {
			
			amount = Integer.parseInt(args[1]);
			
		} catch(NumberFormatException e) {
			
			return false;
			
		}
		
		@SuppressWarnings("deprecation")
		OfflinePlayer reciever = Bukkit.getOfflinePlayer(args[0]);
		
		try {
			
			AccountManager.getAccount(reciever.getUniqueId().toString(), "default").getHoldings().add(amount);;
			
			sender.sendMessage("§aVous avez envoyé à " + reciever.getName() + " §e" + Holdings.format(amount) + " $ !");
			
		} catch (AccountException e) {
			
			sender.sendMessage(e.getMessage());
			
		}
		
		return true;
	
	}

}
