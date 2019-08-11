package fr.alterya.core;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import fr.alterya.core.manager.AccountManager;
import fr.alterya.core.manager.account.AccountException;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent evt) {
		try {
			AccountManager.createAccount(evt.getPlayer().getUniqueId().toString(), "default");
		} catch (AccountException e) {
		
		}
	}
}
