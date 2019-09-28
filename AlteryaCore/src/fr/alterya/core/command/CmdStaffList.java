package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.rank.Rank;
import fr.alterya.core.rank.RankList;

public class CmdStaffList implements CommandExecutor
{
	Rank r;
	RankList rl;
	
	public CmdStaffList(Rank rank) {
		r = rank;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args)
	{
		if(message.equalsIgnoreCase("staff")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage("§4Vous devez être un joueur pour faire cela.");
				return true;
			}
			Player player = (Player) sender;
			player.sendMessage(ChatColor.BOLD + "--------------=Staff Connecté=---------------");
			player.sendMessage(" ");
			for(Player p : Bukkit.getOnlinePlayers()) {
				if(r.config.getInt(p.getUniqueId().toString()) >= 4) {
					player.sendMessage(r.getRankById(r.config.getInt(p.getUniqueId().toString())).getPrefix() + p.getDisplayName());
				}
			}
			player.sendMessage(" ");
			player.sendMessage(ChatColor.BOLD + "---------------------------------------------");
			return true;
		}
		return false;
	}
}
