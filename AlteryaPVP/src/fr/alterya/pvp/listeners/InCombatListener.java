package fr.alterya.pvp.listeners;

/*
Author and resp of the hunt : Lightiz
*/

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerChatEvent;

import fr.alterya.pvp.Main;
import fr.alterya.pvp.task.DecoCombatTimer;

@SuppressWarnings("deprecation")
public class InCombatListener implements Listener
{
	Main main;
	
	DecoCombatTimer timerC;
	
	@EventHandler
	public void onCombatEvent(EntityDamageEvent eventDamage, PlayerChatEvent eventChat) {
		Player player = (Player) eventDamage.getEntity();
		
		if(eventDamage.getCause() == DamageCause.CONTACT) {
			
			timerC.runTaskTimer(main, 20, 20);
			
			if(eventChat.getMessage().equalsIgnoreCase("/home") 
					|| eventChat.getMessage().equalsIgnoreCase("/sethome") 
					|| eventChat.getMessage().equalsIgnoreCase("/delhome")
					|| eventChat.getMessage().equalsIgnoreCase("/shop")
					|| eventChat.getMessage().equalsIgnoreCase("/spawn")
					|| eventChat.getMessage().equalsIgnoreCase("/lobby")
					|| eventChat.getMessage().equalsIgnoreCase("/f home")
					|| eventChat.getMessage().equalsIgnoreCase("/fhome")
					|| eventChat.getMessage().equalsIgnoreCase("/tp")
					|| eventChat.getMessage().equalsIgnoreCase("/tpahere")
					|| eventChat.getMessage().equalsIgnoreCase("/tpa here")
					|| eventChat.getMessage().equalsIgnoreCase("/tpa")) {
				
				player.sendMessage(Main.prefix + "Vous ne pouvez pas effectuer cette commande car vous êtes en combat !");
				return;
			}
		}
	}
}
