package fr.alterya.money.command;

import static fr.alterya.money.Messages.tl;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.money.Main;
import fr.alterya.money.Messages;
import fr.alterya.money.manager.AccountManager;
import fr.alterya.money.manager.account.Account;
import fr.alterya.money.manager.account.AccountException;
import fr.alterya.money.manager.account.Holdings;

public class CmdMoney implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;
	
	public CmdMoney(Main plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			sender.sendMessage(tl("playerOnly"));
			return true;
		}
		
		Player player = (Player)sender;
		String userId = player.getUniqueId().toString();

		if(args.length > 0) {
			userId = Bukkit.getOfflinePlayer(args[0]).getUniqueId().toString();
		}
		
		Account acc = null;
		
		try {
			acc = AccountManager.getAccount(userId, "default");
		} catch(AccountException e) {
			player.sendMessage(e.getMessage());
		}
		
		player.sendMessage(Messages.format("&2Money: &e" + Holdings.format(acc.getHoldings().getBalance())));
		return true;
	}
}
