package fr.alterya.core.listeners;

import org.bukkit.Material;
import org.bukkit.PortalType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.alterya.core.MainCore;

public class ServerListener implements Listener {
	
	MainCore m;
	
	public ServerListener(MainCore main) {
		m = main;
	}
	
	@EventHandler
	void vilagerSpawn(EntitySpawnEvent e) {
		Entity entity = e.getEntity();
		for(Entity e1 : entity.getWorld().getEntities()) {
			if(e1.getType() == EntityType.VILLAGER) {
				e1.remove();
			}
		}
		if(entity.getType() == EntityType.VILLAGER) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	void lockInvisibilityPotion(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(p.getInventory().contains(new ItemStack(Material.POTION, (byte) 373).getType())) {
			p.getInventory().remove(new ItemStack(Material.POTION, (byte) 373).getType());
		}
	}
	
	@EventHandler
	void onCreatePortal(EntityCreatePortalEvent e) {
		if(e.getPortalType() == PortalType.ENDER || e.getPortalType() == PortalType.NETHER) {
			e.setCancelled(true);
		}
	}
}
