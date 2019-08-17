package fr.alterya.money.money.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.money.money.MainAccount;
import fr.alterya.money.money.manager.AccountManager;
import fr.alterya.money.money.manager.account.Account;
import fr.alterya.money.money.manager.account.Holdings;

import java.util.List;
import java.util.UUID;

public class CmdTopmoney extends AccountCommand implements CommandExecutor {

	private MainAccount plugin;
	
	public CmdTopmoney(MainAccount plugin) {
		
		this.plugin = plugin;
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player)sender;
		
		List<Account> accounts = AccountManager.getTopAccounts();
		
		if(accounts.isEmpty()) {
			
			player.sendMessage("§cThis account does not exist!");
		
			return true;
			
		}
			
		String message = "§c§l ->- Top 10 des plus riches -<-\n";
		
		for(Account account : accounts) {
			
			message += "§a" + Bukkit.getOfflinePlayer(UUID.fromString(account.getUserId())).getName() + ": §e" + Holdings.format(account.getHoldings().getBalance()) + "\n";
			
		}
		
		player.sendMessage(message);
		
		return true;
		
	}

}
