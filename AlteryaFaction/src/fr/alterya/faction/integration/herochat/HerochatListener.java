package fr.alterya.faction.integration.herochat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.dthielke.herochat.ChannelChatEvent;
import com.dthielke.herochat.Herochat;
import fr.alterya.faction.Conf;
import fr.alterya.faction.FPlayer;
import fr.alterya.faction.FPlayers;
import fr.alterya.faction.Main;
import fr.alterya.faction.listeners.FactionsChatListener;

public class HerochatListener implements Listener
{
	Main main;
	public HerochatListener(Main main)
	{
		this.main = main;
		Herochat.getChannelManager().addChannel(new FactionChannel());
		Herochat.getChannelManager().addChannel(new AlliesChannel());
	}
	
	/**
	 * Due to limitations in the new version of Herochat we can not offer relation colored tags.
	 */
	@EventHandler(priority = EventPriority.NORMAL)
	public void onChannelChatEvent(ChannelChatEvent event)
	{
		// Should we even parse?
		if ( ! Conf.chatParseTags) return;
		if (Conf.chatTagHandledByAnotherPlugin) return;
		
		Player from = event.getSender().getPlayer();
		FPlayer fpfrom = FPlayers.i.get(from);
		String format = event.getFormat();
		
		format = format.replaceAll("&r", "§r");
		
		String formatWithoutColor = FactionsChatListener.parseTags(format, from, fpfrom);
				
		event.setFormat(formatWithoutColor);
	}
}
