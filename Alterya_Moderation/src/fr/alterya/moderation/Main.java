package fr.alterya.moderation;

import java.util.Collections;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import fr.alterya.core.rank.Rank;
import fr.alterya.moderation.commands.CommandGm;
import fr.alterya.moderation.commands.CommandStaff;
import fr.alterya.moderation.commands.Commands;
import fr.alterya.moderation.gui.GuiManager;
import fr.alterya.moderation.listeners.PlayerListener;
import fr.alterya.moderation.tools.DCommand;

public class Main extends JavaPlugin implements Listener{
	
	public static String prefix = ChatColor.GOLD + "[Modération] ";
	
	private static Main instance;
	private GuiManager guiManager;
	
	Player player;
	
	public Rank rank;
	
	@Override
	public void onLoad() {
		this.rank = new Rank(this, player);
	}
	
	@Override
	public void onEnable() {
		instance = this;
		System.out.println("AlteryaModeration [ON]");
	
		this.guiManager = new GuiManager(this);
		this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		this.getServer().getPluginManager().registerEvents(this, this);
		
		new DCommand("Vanish", "/vanish", "Passe en mode Invicible", null, Collections.singletonList(""), new CommandStaff(this), this);
		new DCommand("Admin", "/admin", "Mode Modérateur", null, Collections.singletonList(""), new Commands(this), this);
		new DCommand("Alert", "/alert", "Annonce un message à tous", null, Collections.singletonList(""), new Commands(this), this);
		new DCommand("Rb", "/rb", "Restart le serveur", null, Collections.singletonList(""), new Commands(this), this);
		new DCommand("Gm", "/gm", "Change de mode Rapidement", null, Collections.singletonList(""), new CommandGm(this), this);
		new DCommand("ClearChat", "/clearchat", "Nettoie le Chat", null, Collections.singletonList(""), new Commands(this), this);
	}
	
	@Override
	public void onDisable() { System.out.println("AlteryaModeration [OFF] "); }
	
    public GuiManager getGuiManager() {	return guiManager; }

	public static Main getInstance() { return instance; }
}


