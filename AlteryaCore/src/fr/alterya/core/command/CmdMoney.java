package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.Main;
import fr.alterya.core.manager.AccountManager;
import fr.alterya.core.manager.account.Account;
import fr.alterya.core.manager.account.AccountException;
import fr.alterya.core.manager.account.Holdings;

public class CmdMoney implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player && cmd.getName().equalsIgnoreCase("money")) {
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
			
			player.sendMessage("§2Money: §e" + Holdings.format(acc.getHoldings().getBalance()));
			return true;
		}else if(!(sender instanceof Player)) {
			sender.sendMessage("§cThis command can only be run by players");
			return true;
		}
		return false;
	}
}
