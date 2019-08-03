package fr.alterya.faction;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.bukkit.craftbukkit.libs.com.google.gson.reflect.TypeToken;
import fr.alterya.faction.zcore.persist.PlayerEntityCollection;

public class FPlayers extends PlayerEntityCollection<FPlayer>
{
	public static FPlayers i = new FPlayers();
	
	Main main = Main.main;
	
	private FPlayers()
	{
		super
		(
			FPlayer.class,
			new CopyOnWriteArrayList<FPlayer>(),
			new ConcurrentSkipListMap<String, FPlayer>(String.CASE_INSENSITIVE_ORDER),
			new File(Main.main.getDataFolder(), "players.json"),
			Main.main.gson
		);
		
		this.setCreative(true);
	}
	
	@Override
	public Type getMapType()
	{
		return new TypeToken<Map<String, FPlayer>>(){}.getType();
	}
	
	public void clean()
	{
		for (FPlayer fplayer : this.get())
		{
			if ( ! Factions.i.exists(fplayer.getFactionId()))
			{
				main.log(Main.prefix + "Réinitialiser le contenu de faction (faction introuvable) pour joueur !" + fplayer.getName());
				fplayer.resetFactionData(false);
			}
		}
	}
}
