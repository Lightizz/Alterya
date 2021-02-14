package fr.alterya.moderation.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.PlayerInventory;

import fr.alterya.moderation.Main;
import fr.alterya.moderation.gui.AbstractGui;
import fr.alterya.moderation.gui.GuiManager;

public class GuiListener implements Listener {
    private final GuiManager manager;

    public GuiListener(GuiManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem() == null) return;
            AbstractGui gui = manager.getPlayerGui(player);

            if (gui != null) {
                if (event.getInventory() instanceof PlayerInventory)
                    return;

                String action = gui.getAction(event.getSlot());

                if (action != null)
                    gui.onClick(player, event.getCurrentItem(), action, event.getClick());

                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (manager.getPlayerGui(event.getPlayer()) != null)
            manager.removeClosedGui((Player) event.getPlayer());
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
    	Player player = event.getPlayer();
    	if(GuiManager.freezPlayerList.contains(player.getUniqueId().toString())) {
    		event.setCancelled(true);
    		player.sendMessage(Main.prefix + "Vous êtes freez vous ne pouvez pas bouger.");
    	}
    }
}