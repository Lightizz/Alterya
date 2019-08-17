package fr.alterya.money.money.command;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.alterya.money.money.MainAccount;
import fr.alterya.money.money.manager.AccountManager;
import fr.alterya.money.money.manager.account.AccountException;
import fr.alterya.money.money.manager.account.Holdings;

public class CmdTakemoney extends AccountCommand implements CommandExecutor {

	private MainAccount plugin;
	
	public CmdTakemoney(MainAccount plugin) {
		
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
		
		OfflinePlayer reciever = Bukkit.getOfflinePlayer(args[0]);
		
		try {
			
			AccountManager.getAccount(reciever.getUniqueId().toString(), "default").getHoldings().subtract(amount);
			
			sender.sendMessage("§aYou have taken §e" + Holdings.format(amount) + " §afrom " +  reciever.getName() + "(s) account");
			
		} catch (AccountException e) {
			
			sender.sendMessage(e.getMessage());
			
		}
		
		return true;
	
	}

}
