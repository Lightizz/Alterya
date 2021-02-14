package fr.alterya.core.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.core.MainCore;
import fr.alterya.core.Trade;
import fr.alterya.core.util.TradeUtil;

public class CmdTrade implements CommandExecutor
{
	public TradeUtil tradeUtil;
	public MainCore m;
	
	public CmdTrade(TradeUtil tradeUtil, MainCore main) {
		this.tradeUtil = tradeUtil;
		m = main;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	    if (sender instanceof Player) {
	      Player p = (Player)sender;
	      if (commandLabel.equalsIgnoreCase("Trade")) {
	        if (args.length == 1) {
	          if (args[0].equalsIgnoreCase("accept")) {
	            if (this.tradeUtil.getTradeFromAccepter(p) != null) {
	              Trade trade = this.tradeUtil.getTradeFromAccepter(p);
	              trade.setTradeAccepted(true);
	              this.tradeUtil.addTrade(trade);
	            } else {
	              p.sendMessage(ChatColor.AQUA + "AlteryaCore> " + ChatColor.GRAY + "Vous n'êtes actuellement impliqué dans aucun échange.");
	            } 
	          } else if (args[0].equalsIgnoreCase("decline")) {
	            if (this.tradeUtil.getTradeFromAccepter(p) != null) {
	              Trade trade = this.tradeUtil.getTradeFromAccepter(p);
	              if (!trade.isTradeAccepted()) {
	                trade.cancelTrade(true);
	              } else {
	                p.sendMessage(ChatColor.AQUA + "AlteryaCore> " + ChatColor.GRAY + "L'échange a déjà été accepté");
	              } 
	            } else {
	              p.sendMessage(ChatColor.AQUA + "AlteryaCore> " + ChatColor.GRAY + "Vous n'êtes actuellement impliqué dans aucun échange.");
	            }
	          
	          } else if (Bukkit.getOfflinePlayer(args[0]).isOnline()) {
	            Player accepter = Bukkit.getPlayer(args[0]);
	            if (p.getWorld().getName().equalsIgnoreCase(accepter.getWorld().getName())) {
	              if (p.getLocation().distance(accepter.getLocation()) <= 10.0D) {
	                if (this.tradeUtil.getTradeFromAccepter(p) == null && this.tradeUtil.getTradeFromRequester(p) == null) {
	                  if (this.tradeUtil.getTradeFromAccepter(accepter) == null && this.tradeUtil.getTradeFromRequester(accepter) == null) {
	                    Trade trade = new Trade(m, p, accepter);
	                    this.tradeUtil.addTrade(trade);
	                  } else {
	                    p.sendMessage(ChatColor.AQUA + "AlteryaCore> " + ChatColor.GRAY + "The player " + ChatColor.AQUA + accepter.getName() + ChatColor.GRAY + " is currently involved in a different trade");
	                  } 
	                } else {
	                  p.sendMessage(ChatColor.AQUA + "AlteryaCore> " + ChatColor.GRAY + "You are currently involved in a different trade");
	                } 
	              } else {
	                p.sendMessage(ChatColor.AQUA + "AlteryaCore> " + ChatColor.GRAY + "Vous devez être à au moins 10 bloc d'un joueur pour échanger avec lui.");
	              } 
	            } else {
	              p.sendMessage(ChatColor.AQUA + "AlteryaCore> " + ChatColor.GRAY + "Vous devez être dans le même monde que le joueur pour échanger.");
	            } 
	          } else {
	            p.sendMessage(ChatColor.AQUA + "AlteryaCore> " + ChatColor.GRAY + "Le joueur " + ChatColor.AQUA + args[0] + ChatColor.GRAY + " n'est pas en ligne.");
	          } 
	        } else {
	          
	          p.sendMessage(ChatColor.AQUA + "AlteryaCore> " + ChatColor.GRAY + "Incorrect Usage, Correct Usage: " + ChatColor.AQUA + "/Trade (PlayerName)" + ChatColor.GRAY + ", " + ChatColor.AQUA + "/Trade Accept" + ChatColor.GRAY + ", or " + ChatColor.AQUA + "/Trade Decline");
	        } 
	      }
	    } 
	    return false;
	  }
}
