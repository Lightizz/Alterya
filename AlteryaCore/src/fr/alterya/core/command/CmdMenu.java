package fr.alterya.core.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.MainCore;
import fr.alterya.core.playerMenu.MenuManager;

public class CmdMenu extends MenuManager implements CommandExecutor
{
	public MainCore main;
	
	public CmdMenu(MainCore mainCore) {
		this.main = mainCore;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		if(!(sender instanceof Player)) { sender.sendMessage(ChatColor.RED + "Vous devez être un joueur pour faire cela."); return true; }
		Player player = (Player) sender;
		if(message.equalsIgnoreCase("menu")) {
			if(args.length != 0) { player.sendMessage(MainCore.prefix + "La commande est /menu."); return true; }
			openMenu(player);
			return true;
		}
		return false;
	}
}
