package fr.alterya.core.money;

import java.util.Map;

import org.bukkit.entity.Player;

import fr.alterya.core.Main;
import net.minecraft.util.com.google.common.collect.Maps;

/*
Author and resp of the money: Lightiz
*/

public class MoneyManager
{
	Money money;
	Player player;
	
	private Map<String, Money> playersMoney = Maps.newHashMap();
	
	public void onMapEmpty(Map<String, Money> mapToCheck) 
	{
		if(player.getLevel() > 100 && playersMoney.isEmpty() == false) 
		{
			money.addPlayerMoney(player.getUniqueId().toString(), 10000);
			player.sendMessage(Main.prefix + "Bravo vous avez passer le level 100, vous recevez 10 000 $ de récompense !");
		}
	}
}
