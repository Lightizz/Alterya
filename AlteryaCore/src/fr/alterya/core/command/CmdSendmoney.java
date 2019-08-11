package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.Main;
import fr.alterya.core.manager.AccountManager;
import fr.alterya.core.manager.account.Account;
import fr.alterya.core.manager.account.AccountException;
import fr.alterya.core.manager.account.Holdings;

public class CmdSendmoney implements CommandExecutor {

	private Main plugin;
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player && cmd.getName().equalsIgnoreCase("pay")) {
			
			if(args.length < 2) {
				return false;
			}
			
			int amount = 0;
				
			try {
				amount = Integer.parseInt(args[1]);
			} catch(NumberFormatException e) {	
				return false;
			}

			Player payer = (Player)sender;
			Holdings payerHoldings = new Account(payer.getUniqueId().toString(), "default", plugin).getHoldings();
			
			if(args[0].equalsIgnoreCase("all")) {
				amount = (Bukkit.getOnlinePlayers().length -1) * amount;
			}
			
			try {
				payerHoldings.subtract(amount);
			} catch (AccountException e) {
				payer.sendMessage(e.getMessage());
				return true;
			}
			
			String formattedBal = Holdings.format(amount);
			OfflinePlayer reciever = Bukkit.getOfflinePlayer(args[0]);
			
			if(!reciever.hasPlayedBefore() || !AccountManager.hasAccount(reciever.getUniqueId().toString(), "default")) {
				
				sender.sendMessage("§cThis account already exists!");
				return true;	
			}
		
			new Account(reciever.getUniqueId().toString(), "default", plugin).getHoldings().add(amount);
			payer.sendMessage("aYou have sent §e" + formattedBal + " §ato " + reciever.getName());
			
			if(reciever.isOnline()) {
				reciever.getPlayer().sendMessage("§aYou have recieved §e" + formattedBal + " §afrom " + payer.getDisplayName());
			}
			return true;
			
		}else if(!(sender instanceof Player)) {
			sender.sendMessage("§cThis command can only be run by players");
			return true;
		}
		return false;
	}
}
