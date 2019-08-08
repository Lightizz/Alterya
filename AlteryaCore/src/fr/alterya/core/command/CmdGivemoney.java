package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.money.Main;
import fr.alterya.money.manager.AccountManager;
import fr.alterya.money.manager.account.AccountException;
import fr.alterya.money.manager.account.Holdings;

public class CmdGivemoney implements CommandExecutor {

	@SuppressWarnings("unused")
	private Main plugin;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player)sender;
		
		if(cmd.getName().equalsIgnoreCase("givemoney")) {
			if(args.length < 2) {
				return false;
			}
			
			int amount = 0;
			
			try {
				amount = Integer.parseInt(args[1]);
			} catch(NumberFormatException e) {
				return false;	
			}
			Player reciever = Bukkit.getPlayer(args[0]);
			try {
				AccountManager.getAccount(reciever.getUniqueId().toString(), "default").getHoldings().add(amount);;
				player.sendMessage("§aVous avez donné " + reciever.getName() + " §e" + Holdings.format(amount));
			} catch (AccountException e) {
				player.sendMessage(e.getMessage());
			}
			return true;
		}
		return false;
	}
}
