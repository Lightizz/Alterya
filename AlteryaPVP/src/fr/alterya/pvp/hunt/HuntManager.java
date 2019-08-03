package fr.alterya.pvp.hunt;

/*
Author and resp of the hunt / déco combat : Lightiz
*/

import java.util.Random;

import org.bukkit.entity.Player;

public class HuntManager
{
	private Random random;
	
	public Player huntTarget;
	
	@SuppressWarnings("deprecation")
	public void swapTargetedPlayer() 
	{
		int onlinePlayerCount = huntTarget.getServer().getOnlinePlayers().length;
		
		random.nextInt(onlinePlayerCount);
	}
	
	public String getPlayerTargetedName() 
	{
		return huntTarget.getName();
	}
	
	public Player getPlayerTargeted() 
	{
		return huntTarget;
	}
}
