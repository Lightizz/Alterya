package fr.alterya.moderation.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import fr.alterya.moderation.MainModeration;
import fr.alterya.moderation.listeners.GuiListener;

// SamaGames extends Class

public class GuiManager {

    protected final MainModeration plugin;
    private final ConcurrentHashMap<UUID, AbstractGui> currentGUIs;
    private final HashMap<UUID, String> workersIds;

    public static ArrayList<String> freezPlayerList = new ArrayList<String>();
    
    public GuiManager(MainModeration plugin) {
        this.plugin = plugin;
        this.currentGUIs = new ConcurrentHashMap<>();
        this.workersIds = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(new GuiListener(this), plugin);
    }

    public void openGui(Player player, AbstractGui gui) {
        if (this.currentGUIs.containsKey(player.getUniqueId()))
            this.closeGui(player);

        this.currentGUIs.put(player.getUniqueId(), gui);
        gui.display(player);
    }

    public void closeGui(Player player) {
        player.closeInventory();
        this.removeClosedGui(player);
    }

    public void removeClosedGui(Player player) {
        if (this.currentGUIs.containsKey(player.getUniqueId())) {
            this.getPlayerGui(player).onClose(player);
            this.currentGUIs.remove(player.getUniqueId());
        }
    }

    public AbstractGui getPlayerGui(HumanEntity player) {
        return getPlayerGui(player.getUniqueId());
    }

    public AbstractGui getPlayerGui(UUID player) {
        if (this.currentGUIs.containsKey(player))
            return this.currentGUIs.get(player);

        return null;
    }

    public ConcurrentHashMap<UUID, AbstractGui> getPlayersGui() {
        return this.currentGUIs;
    }

    public HashMap<UUID, String> getWorkersIds() {
        return workersIds;
    }
}

