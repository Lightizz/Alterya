package fr.alterya.money.command;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.alterya.money.Main;
import fr.alterya.money.Messages;
import fr.alterya.money.manager.AccountManager;
import fr.alterya.money.manager.account.AccountException;
import fr.alterya.money.manager.account.Holdings;

public class CmdGivemoney implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;
	
	public CmdGivemoney(Main plugin) {
		
		this.plugin = plugin;
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length < 2) {
			return false;
		}
		
		int amount = 0;
		
		try {
			amount = Integer.parseInt(args[1]);
		} catch(NumberFormatException e) {
			return false;	
		}
		OfflinePlayer reciever = Bukkit.getOfflinePlayer(args[0]);
		try {
			AccountManager.getAccount(reciever.getUniqueId().toString(), "default").getHoldings().add(amount);;
			sender.sendMessage(Messages.format("&aYou have given " + reciever.getName() + " &e" + Holdings.format(amount)));
		} catch (AccountException e) {
			sender.sendMessage(e.getMessage());
		}
		return true;
	}
}
