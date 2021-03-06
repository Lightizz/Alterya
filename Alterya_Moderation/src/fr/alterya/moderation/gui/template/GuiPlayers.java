package fr.alterya.moderation.gui.template;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import fr.alterya.moderation.Main;
import fr.alterya.moderation.gui.AbstractGui;
import fr.alterya.moderation.tools.ItemBuilder;

public class GuiPlayers extends AbstractGui {

	private final Main plugin;
	
	public GuiPlayers(Main plugin) {
		super(plugin);
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void display(Player player) {
		this.inventory = plugin.getServer().createInventory(null, 6*9, "Joueurs");
		
		int slot = 0;
		for(Player p : plugin.getServer().getOnlinePlayers()) {
			this.setSlotData(ChatColor.GOLD + p.getName(),
					new ItemBuilder(Material.SKULL_ITEM).setDurability((short) 3).setSkullOwner(p.getName()).toItemStack(),
					slot,
					new String[] {"", ""},
					"" + p.getName());
			slot++;
		}
		
		player.openInventory(this.inventory);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(Player player, ItemStack stack, String action, ClickType clickType) {
		plugin.getGuiManager().openGui(player, new GuiCustom(this.plugin, Bukkit.getPlayer(action)));
	}
}
