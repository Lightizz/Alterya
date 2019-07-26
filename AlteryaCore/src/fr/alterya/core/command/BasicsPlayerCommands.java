package fr.alterya.core.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_7_R4.EntityPlayer;

public class BasicsPlayerCommands implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String message, String[] args){		
			if(command.getName().equalsIgnoreCase("ping") && sender instanceof Player){
	            Player player = (Player) sender;
	            player.sendMessage("§eVotre ping est : §6" + getPing(player) + "ms");
	                
	            return true;
	        }
	        
	        if(command.getName().equalsIgnoreCase("discord") && sender instanceof Player) {
	            Player player = (Player) sender;
	            player.sendMessage("§6>> §bhttps://discord.gg/rTR4FNF §6<<" );
	            
	            return true;
	        }
	        
	        if(command.getName().equalsIgnoreCase("craft") && sender instanceof Player) {
	            Player player = (Player) sender;
	            player.openWorkbench (null, true);
	            
	            return true;
	        }
	        
	        if(command.getName().equalsIgnoreCase("ec") && sender instanceof Player) {
	            Player player = (Player) sender;
	            player.openInventory(player.getEnderChest());
	            
	            return true;
	        }
		return false;
	}
	
	public double getPing(Player player) 
	{
		CraftPlayer pingC = (CraftPlayer) player;
		EntityPlayer pingE = pingC.getHandle();
		
		return pingE.ping;
	}
}
