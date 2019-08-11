package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.Main;
import fr.alterya.core.manager.AccountManager;
import fr.alterya.core.manager.account.Account;
import fr.alterya.core.manager.account.Holdings;

import java.util.List;
import java.util.UUID;

public class CmdTopmoney implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player)sender;
		
		List<Account> accounts = AccountManager.getTopAccounts();
		
		if(accounts.isEmpty()) {
			
			player.sendMessage("Une erreur est survenue, veuillez contacter un Administrateur, Responsable, ou un Développeur.");
		
			return true;
			
		}
			
		String message = "&c&l-= Top 10 balances =-\n";
		
		for(Account account : accounts) {
			
			message += "&a" + Bukkit.getOfflinePlayer(UUID.fromString(account.getUserId())).getName() + ": &e" + Holdings.format(account.getHoldings().getBalance()) + "\n";
			
		}
		
		player.sendMessage(message);
		
		return true;
		
	}

}
