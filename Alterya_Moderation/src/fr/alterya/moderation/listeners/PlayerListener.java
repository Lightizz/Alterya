package fr.alterya.moderation.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.alterya.moderation.Main;
import fr.alterya.moderation.gui.template.GuiPlayers;

public class PlayerListener implements Listener {
	
	private final Main plugin;

	public PlayerListener(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(player.getItemInHand().getType() == Material.PAPER && player.getItemInHand().getItemMeta().getDisplayName() == "§4Liste des joueurs") {
			this.plugin.getGuiManager().openGui(player, new GuiPlayers(this.plugin));
		}
	}
	
}
