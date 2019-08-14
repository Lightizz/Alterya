package fr.alterya.money.money.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import fr.alterya.money.money.Main;
import fr.alterya.money.money.manager.AccountManager;
import fr.alterya.money.money.manager.account.AccountException;

public class PlayerListener implements Listener {

	private Main plugin;
	
	public PlayerListener(Main plugin) {
		
		this.plugin = plugin;
		
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent evt) {
		
		try {
			
			AccountManager.createAccount(evt.getPlayer().getUniqueId().toString(), "default");
		
		} catch (AccountException e) {
			
			
			
		}
		
	}
	
}
