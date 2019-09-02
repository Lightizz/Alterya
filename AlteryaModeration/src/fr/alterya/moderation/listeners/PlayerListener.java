package fr.alterya.moderation.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.alterya.moderation.MainModeration;
import fr.alterya.moderation.gui.template.GuiPlayers;

public class PlayerListener implements Listener {
	
	private final MainModeration plugin;

	public PlayerListener(MainModeration plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(event.getAction() == Action.RIGHT_CLICK_AIR && player.getItemInHand().getType() == Material.PAPER) {
			this.plugin.getGuiManager().openGui(player, new GuiPlayers(this.plugin));
		}
	}
	
}
