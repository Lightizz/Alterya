package fr.alterya.faction;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.Set;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.alterya.faction.command.CmdAutoHelp;
import fr.alterya.faction.command.FCmdRoot;

import org.bukkit.Location;
import org.bukkit.Material;

import fr.alterya.faction.adapters.FFlagTypeAdapter;
import fr.alterya.faction.adapters.FPermTypeAdapter;
import fr.alterya.faction.adapters.LocationTypeAdapter;
import fr.alterya.faction.adapters.RelTypeAdapter;
import fr.alterya.faction.integration.capi.CapiFeatures;
import fr.alterya.faction.integration.herochat.HerochatFeatures;
import fr.alterya.faction.integration.Econ;
import fr.alterya.faction.integration.EssentialsFeatures;
import fr.alterya.faction.integration.LWCFeatures;
import fr.alterya.faction.integration.SpoutFeatures;
import fr.alterya.faction.integration.Worldguard;
import fr.alterya.faction.listeners.FactionsBlockListener;
import fr.alterya.faction.listeners.FactionsChatListener;
import fr.alterya.faction.listeners.FactionsEntityListener;
import fr.alterya.faction.listeners.FactionsExploitListener;
import fr.alterya.faction.listeners.FactionsAppearanceListener;
import fr.alterya.faction.listeners.FactionsPlayerListener;
import fr.alterya.faction.listeners.FactionsServerListener;
import fr.alterya.faction.struct.FFlag;
import fr.alterya.faction.struct.FPerm;
import fr.alterya.faction.struct.Rel;
import fr.alterya.faction.struct.TerritoryAccess;
import fr.alterya.faction.util.AutoLeaveTask;
import fr.alterya.faction.util.EconLandRewardTask;
import fr.alterya.faction.util.LazyLocation;
import fr.alterya.faction.zcore.MPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.com.google.gson.GsonBuilder;

public class Main extends MPlugin
{
	public static final String prefix = "§e§l[&4Faction&e&l] ";
	
	public static Main main;
	
	public final FactionsPlayerListener playerListener;
	public final FactionsChatListener chatListener;
	public final FactionsEntityListener entityListener;
	public final FactionsExploitListener exploitListener;
	public final FactionsBlockListener blockListener;
	public final FactionsServerListener serverListener;
	public final FactionsAppearanceListener appearanceListener;
	

	private boolean locked = false;
	public boolean getLocked() {return this.locked;}
	public void setLocked(boolean val) {this.locked = val; this.setAutoSave(val);}
	private Integer AutoLeaveTask = null;
	private Integer econLandRewardTaskID = null;
	
	public FCmdRoot cmdBase;
	public CmdAutoHelp cmdAutoHelp;
	
	public Main()
	{
		main = this;
		this.playerListener = new FactionsPlayerListener(this);
		this.chatListener = new FactionsChatListener(this);
		this.entityListener = new FactionsEntityListener(this);
		this.exploitListener = new FactionsExploitListener();
		this.blockListener = new FactionsBlockListener(this);
		this.serverListener = new FactionsServerListener(this);
		this.appearanceListener = new FactionsAppearanceListener(this);
	}

	@Override
	public void onEnable()
	{
		try
		{
			Class.forName("org.bukkit.craftbukkit.libs.com.google.gson.reflect.TypeToken");
		}
		catch (ClassNotFoundException ex)
		{
			this.log(Level.SEVERE, "GSON lib introuvable. Votre CraftBukkit export est plus vienx que (< 1.3.2) u incompatible.");
			this.suicide();
			return;
		}

		if ( ! preEnable()) return;
		this.loadSuccessful = false;

		Conf.load();
		FPlayers.i.loadFromDisc();
		Factions.i.loadFromDisc();
		Board.load();
		
		this.cmdAutoHelp = new CmdAutoHelp();
		this.cmdBase = new FCmdRoot();

		EssentialsFeatures.setup();
		SpoutFeatures.setup();
		Econ.setup();
		CapiFeatures.setup();
		HerochatFeatures.setup();
		LWCFeatures.setup();
		
		if(Conf.worldGuardChecking)
		{
			Worldguard.init(this);
		}

		startAutoLeaveTask(false);

		startEconLandRewardTask(false);

		getServer().getPluginManager().registerEvents(this.playerListener, this);
		getServer().getPluginManager().registerEvents(this.chatListener, this);
		getServer().getPluginManager().registerEvents(this.entityListener, this);
		getServer().getPluginManager().registerEvents(this.exploitListener, this);
		getServer().getPluginManager().registerEvents(this.blockListener, this);
		getServer().getPluginManager().registerEvents(this.serverListener, this);
		getServer().getPluginManager().registerEvents(this.appearanceListener, this);

		postEnable();
		this.loadSuccessful = true;
	}
	
	@Override
	public GsonBuilder getGsonBuilder()
	{
		return new GsonBuilder()
		.setPrettyPrinting()
		.disableHtmlEscaping()
		.excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.VOLATILE)
		.registerTypeAdapter(LazyLocation.class, new LocationTypeAdapter())
		.registerTypeAdapter(TerritoryAccess.class, new TerritoryAccess())
		.registerTypeAdapter(Rel.class, new RelTypeAdapter())
		.registerTypeAdapter(FPerm.class, new FPermTypeAdapter())
		.registerTypeAdapter(FFlag.class, new FFlagTypeAdapter());
	}

	@Override
	public void onDisable()
	{
		if (this.loadSuccessful)
		{
			Board.save();
			Conf.save();
		}
		EssentialsFeatures.unhookChat();
		if (AutoLeaveTask != null)
		{
			this.getServer().getScheduler().cancelTask(AutoLeaveTask);
			AutoLeaveTask = null;
		}
		super.onDisable();
	}

	public void startAutoLeaveTask(boolean restartIfRunning)
	{
		if (AutoLeaveTask != null)
		{
			if ( ! restartIfRunning) return;
			this.getServer().getScheduler().cancelTask(AutoLeaveTask);
		}

		if (Conf.autoLeaveRoutineRunsEveryXMinutes > 0.0)
		{
			long ticks = (long)(20 * 60 * Conf.autoLeaveRoutineRunsEveryXMinutes);
			AutoLeaveTask = getServer().getScheduler().scheduleSyncRepeatingTask(this, new AutoLeaveTask(), ticks, ticks);
		}
	}

	public void startEconLandRewardTask(boolean restartIfRunning)
	{
		if (econLandRewardTaskID != null)
		{
			if (!restartIfRunning) return;
			this.getServer().getScheduler().cancelTask(econLandRewardTaskID);
		}

		if (Conf.econEnabled &&
			Conf.econLandRewardTaskRunsEveryXMinutes > 0.0 &&
			Conf.econLandReward > 0.0)
		{
			long ticks = (long)(20 * 60 * Conf.econLandRewardTaskRunsEveryXMinutes);
			econLandRewardTaskID = getServer().getScheduler().scheduleSyncRepeatingTask(this, new EconLandRewardTask(), ticks, ticks);
		}
	}

	@Override
	public void postAutoSave()
	{
		Board.save();
		Conf.save();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] split)
	{
		this.cmdBase.execute(sender, new ArrayList<String>(Arrays.asList(split)));
		return true;
	}

	public int hookSupportVersion()
	{
		return 3;
	}

	public void handleFactionTagExternally(boolean notByFactions)
	{
		Conf.chatTagHandledByAnotherPlugin = notByFactions;
	}
	
	public boolean shouldLetFactionsHandleThisChat(AsyncPlayerChatEvent event)
	{
		if (event == null) return false;
		return (isPlayerFactionChatting(event.getPlayer()) || isFactionsCommand(event.getMessage()));
	}

	public boolean isPlayerFactionChatting(Player player)
	{
		return false;
	}
	
	public boolean isFactionsCommand(String check)
	{
		return false;
	}

	public String getPlayerFactionTag(Player player)
	{
		return getPlayerFactionTagRelation(player, null);
	}

	public String getPlayerFactionTagRelation(Player speaker, Player listener)
	{
		String tag = "~";

		if (speaker == null)
			return tag;

		FPlayer me = FPlayers.i.get(speaker);
		if (me == null)
			return tag;

		if (listener == null || !Conf.chatParseTagsColored) {
			tag = me.getChatTag().trim();
		} else {
			FPlayer you = FPlayers.i.get(listener);
			if (you == null)
				tag = me.getChatTag().trim();
			else  // everything checks out, give the colored tag
				tag = me.getChatTag(you).trim();
		}
		if (tag.isEmpty())
			tag = "~";

		return tag;
	}

	public String getPlayerTitle(Player player)
	{
		if (player == null)
			return "";

		FPlayer me = FPlayers.i.get(player);
		if (me == null)
			return "";

		return me.getTitle().trim();
	}

	public Set<String> getFactionTags()
	{
		Set<String> tags = new HashSet<String>();
		for (Faction faction : Factions.i.get())
		{
			tags.add(faction.getTag());
		}
		return tags;
	}

	public Set<String> getPlayersInFaction(String factionTag)
	{
		Set<String> players = new HashSet<String>();
		Faction faction = Factions.i.getByTag(factionTag);
		if (faction != null)
		{
			for (FPlayer fplayer : faction.getFPlayers())
			{
				players.add(fplayer.getName());
			}
		}
		return players;
	}

	public Set<String> getOnlinePlayersInFaction(String factionTag)
	{
		Set<String> players = new HashSet<String>();
		Faction faction = Factions.i.getByTag(factionTag);
		if (faction != null)
		{
			for (FPlayer fplayer : faction.getFPlayersWhereOnline(true))
			{
				players.add(fplayer.getName());
			}
		}
		return players;
	}
	
	public boolean isPlayerAllowedToBuildHere(Player player, Location location)
	{
		return FactionsBlockListener.playerCanBuildDestroyBlock(player, location.getBlock(), "", true);
	}

	public boolean isPlayerAllowedToInteractWith(Player player, Block block)
	{
		return FactionsPlayerListener.canPlayerUseBlock(player, block, true);
	}

	public boolean isPlayerAllowedToUseThisHere(Player player, Location location, Material material)
	{
		return FactionsPlayerListener.playerCanUseItemHere(player, location, material, true);
	}
}
