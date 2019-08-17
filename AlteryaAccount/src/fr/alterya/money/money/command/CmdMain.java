package fr.alterya.money.money.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import fr.alterya.money.money.MainAccount;
import fr.alterya.money.money.Messages;

public class CmdMain extends AccountCommand implements CommandExecutor {

	private MainAccount plugin;
	
	public CmdMain(MainAccount plugin) {
		
		this.plugin = plugin;
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		sender.sendMessage("§c§l-= Account/Money Commands =-");
		
		for(String cmdName : plugin.getDescription().getCommands().keySet()) {
			
			PluginCommand pCmd = Bukkit.getPluginCommand(cmdName);
			
			if(sender.hasPermission(pCmd.getPermission()))
				sender.sendMessage("§2" + cmdName + " - §a" + pCmd.getDescription());
			
		}
		
		return true;
	
	}

}
