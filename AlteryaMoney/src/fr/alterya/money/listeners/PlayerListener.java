package fr.alterya.money.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import fr.alterya.money.manager.AccountManager;
import fr.alterya.money.manager.account.AccountException;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent evt) {
		try {
			AccountManager.createAccount(evt.getPlayer().getUniqueId().toString(), "default");
		} catch (AccountException e) {
		
		}
	}
}
