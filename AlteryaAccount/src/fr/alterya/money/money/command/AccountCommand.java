package fr.alterya.money.money.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AccountCommand {
	
	public boolean isConsole(CommandSender sender) {
		
		return !(sender instanceof Player);
		
	}

}
