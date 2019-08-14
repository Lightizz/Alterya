package fr.alterya.money.money.command;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.money.money.Main;
import fr.alterya.money.money.manager.AccountManager;
import fr.alterya.money.money.manager.account.Account;
import fr.alterya.money.money.manager.account.AccountException;
import fr.alterya.money.money.manager.account.Holdings;

public class CmdSendmoney extends AccountCommand implements CommandExecutor {

	private Main plugin;
	
	public CmdSendmoney(Main plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage("§cThis command can only be run by players");
			return true;
			
		}
		
		if(args.length < 2) return false;
		
		int amount = 0;
			
		try {
			amount = Integer.parseInt(args[1]);
		} catch(NumberFormatException e) {
			return false;
		}

		Player payer = (Player)sender;
		Holdings payerHoldings = new Account(payer.getUniqueId().toString(), "default", plugin).getHoldings();
		
		if(args[0].equalsIgnoreCase("all")) amount = (Bukkit.getOnlinePlayers().length-1) * amount;
		
		try {
			payerHoldings.subtract(amount);
		} catch (AccountException e) {
			payer.sendMessage(e.getMessage());
			return true;
		}
		
		String formattedBal = Holdings.format(amount);
		OfflinePlayer reciever = Bukkit.getOfflinePlayer(args[0]);
		
		if(!reciever.hasPlayedBefore() || !AccountManager.hasAccount(reciever.getUniqueId().toString(), "default")) {
			sender.sendMessage("§cThis account does not exist!");
			return true;
		}
		new Account(reciever.getUniqueId().toString(), "default", plugin).getHoldings().add(amount);
		
		payer.sendMessage("§aVous avez envoyé §e" + formattedBal + " §aà " + reciever.getName());
		
		if(reciever.isOnline()) {
			
			reciever.getPlayer().sendMessage("§aVous avez reçu §e" + formattedBal + " §ade " + payer.getDisplayName());
	
		}
		return true;
	}
}
