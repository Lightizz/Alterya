package fr.alterya.factions;

import java.io.File;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;

import org.bukkit.ChatColor;

import org.bukkit.craftbukkit.libs.com.google.gson.reflect.TypeToken;

import fr.alterya.factions.integration.Econ;
import fr.alterya.factions.zcore.util.TextUtil;

import fr.alterya.factions.struct.FFlag;
import fr.alterya.factions.struct.FPerm;
import fr.alterya.factions.struct.Rel;
import fr.alterya.factions.util.MiscUtil;
import fr.alterya.factions.zcore.persist.EntityCollection;

public class Factions extends EntityCollection<Faction>
{
	public static Factions i = new Factions();
	
	P p = P.p;
	
	private Factions()
	{
		super
		(
			Faction.class,
			new CopyOnWriteArrayList<Faction>(),
			new ConcurrentHashMap<String, Faction>(),
			new File(P.p.getDataFolder(), "factions.json"),
			P.p.gson
		);
	}
	
	@Override
	public Type getMapType()
	{
		return new TypeToken<Map<String, Faction>>(){}.getType();
	}
	
	@Override
	public boolean loadFromDisc()
	{
		if ( ! super.loadFromDisc()) return false;
		
		//----------------------------------------------//
		// Create Default Special Factions
		//----------------------------------------------//
		if ( ! this.exists("0"))
		{
			Faction faction = this.create("0");
			faction.setTag(ChatColor.DARK_GREEN+"Wilderness");
			faction.setDescription("");
			this.setFlagsForWilderness(faction);
		}
		if ( ! this.exists("-1"))
		{
			Faction faction = this.create("-1");
			faction.setTag("SafeZone");
			faction.setDescription("Libre de PVP et de monstres");
			
			this.setFlagsForSafeZone(faction);
		}
		if ( ! this.exists("-2"))
		{
			Faction faction = this.create("-2");
			faction.setTag("WarZone");
			faction.setDescription("L'enfer est vide, tous les d�mons sont ici.");
			this.setFlagsForWarZone(faction);
		}
		if ( ! this.exists("-3"))
		{
			Faction faction = this.create("-3");
			faction.setTag("ApZone");
			faction.setDescription("Zone d'avant poste.");
			this.setFlagsForWarZone(faction);
		}
		
		//----------------------------------------------//
		// Fix From Old Formats
		//----------------------------------------------//
		Faction wild = this.get("0");
		Faction safeZone = this.get("-1");
		Faction warZone = this.get("-2");
		Faction apzone = this.get("-3");
		
		// Remove troublesome " " from old pre-1.6.0 names
		if (safeZone != null && safeZone.getTag().contains(" "))
			safeZone.setTag("SafeZone");
		if (warZone != null && warZone.getTag().contains(" "))
			warZone.setTag("WarZone");
		if(apzone != null && apzone.getTag().contains(" "))
			apzone.setTag("ApZone");
		
		// Set Flags if they are not set already.
		if (wild != null && ! wild.getFlag(FFlag.PERMANENT))
			setFlagsForWilderness(wild);
		if (safeZone != null && ! safeZone.getFlag(FFlag.PERMANENT))
			setFlagsForSafeZone(safeZone);
		if (warZone != null && ! warZone.getFlag(FFlag.PERMANENT))
			setFlagsForWarZone(warZone);
		if(apzone != null && ! apzone.getFlag(FFlag.PERMANENT))
			setFlagsForApZone(apzone);
		
		// populate all faction player lists
		for (Faction faction : i.get())
		{
			faction.refreshFPlayers();
		}

		return true;
	}
	
	//----------------------------------------------//
	// Flag Setters
	//----------------------------------------------//
	public void setFlagsForWilderness(Faction faction)
	{
		faction.setOpen(false);
		
		faction.setFlag(FFlag.PERMANENT, true);
		faction.setFlag(FFlag.PEACEFUL, false);
		faction.setFlag(FFlag.INFPOWER, true);
		faction.setFlag(FFlag.POWERLOSS, true);
		faction.setFlag(FFlag.PVP, true);
		faction.setFlag(FFlag.FRIENDLYFIRE, false);
		faction.setFlag(FFlag.MONSTERS, true);
		faction.setFlag(FFlag.EXPLOSIONS, true);
		faction.setFlag(FFlag.FIRESPREAD, true);
		//faction.setFlag(FFlag.LIGHTNING, true);
		faction.setFlag(FFlag.ENDERGRIEF, true);
		
		faction.setPermittedRelations(FPerm.BUILD, Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY);
		faction.setPermittedRelations(FPerm.DOOR, Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY);
		faction.setPermittedRelations(FPerm.CONTAINER, Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY);
		faction.setPermittedRelations(FPerm.BUTTON, Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY);
		faction.setPermittedRelations(FPerm.LEVER, Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY);
	}
	
	public void setFlagsForApZone(Faction faction) {
		faction.setOpen(false);
		
		faction.setFlag(FFlag.PERMANENT, true);
		faction.setFlag(FFlag.PEACEFUL, true);
		faction.setFlag(FFlag.INFPOWER, true);
		faction.setFlag(FFlag.POWERLOSS, false);
		faction.setFlag(FFlag.PVP, true);
		faction.setFlag(FFlag.FRIENDLYFIRE, false);
		faction.setFlag(FFlag.MONSTERS, false);
		faction.setFlag(FFlag.EXPLOSIONS, false);
		faction.setFlag(FFlag.FIRESPREAD, false);
		//faction.setFlag(FFlag.LIGHTNING, false);
		faction.setFlag(FFlag.ENDERGRIEF, false);
		
		faction.setPermittedRelations(FPerm.DOOR, Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY);
		faction.setPermittedRelations(FPerm.CONTAINER, Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY);
		faction.setPermittedRelations(FPerm.BUTTON, Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY);
		faction.setPermittedRelations(FPerm.LEVER, Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY);
		faction.setPermittedRelations(FPerm.TERRITORY, Rel.LEADER, Rel.OFFICER, Rel.MEMBER);
	}
	
	public void setFlagsForSafeZone(Faction faction)
	{
		faction.setOpen(false);
		
		faction.setFlag(FFlag.PERMANENT, true);
		faction.setFlag(FFlag.PEACEFUL, true);
		faction.setFlag(FFlag.INFPOWER, true);
		faction.setFlag(FFlag.POWERLOSS, false);
		faction.setFlag(FFlag.PVP, false);
		faction.setFlag(FFlag.FRIENDLYFIRE, false);
		faction.setFlag(FFlag.MONSTERS, false);
		faction.setFlag(FFlag.EXPLOSIONS, false);
		faction.setFlag(FFlag.FIRESPREAD, false);
		//faction.setFlag(FFlag.LIGHTNING, false);
		faction.setFlag(FFlag.ENDERGRIEF, false);
		
		faction.setPermittedRelations(FPerm.DOOR, Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY);
		faction.setPermittedRelations(FPerm.CONTAINER, Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY);
		faction.setPermittedRelations(FPerm.BUTTON, Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY);
		faction.setPermittedRelations(FPerm.LEVER, Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY);
		faction.setPermittedRelations(FPerm.TERRITORY, Rel.LEADER, Rel.OFFICER, Rel.MEMBER);	
	}
	
	public void setFlagsForWarZone(Faction faction)
	{
		faction.setOpen(false);
		
		faction.setFlag(FFlag.PERMANENT, true);
		faction.setFlag(FFlag.PEACEFUL, true);
		faction.setFlag(FFlag.INFPOWER, true);
		faction.setFlag(FFlag.POWERLOSS, true);
		faction.setFlag(FFlag.PVP, true);
		faction.setFlag(FFlag.FRIENDLYFIRE, true);
		faction.setFlag(FFlag.MONSTERS, true);
		faction.setFlag(FFlag.EXPLOSIONS, true);
		faction.setFlag(FFlag.FIRESPREAD, true);
		//faction.setFlag(FFlag.LIGHTNING, true);
		faction.setFlag(FFlag.ENDERGRIEF, true);
		
		faction.setPermittedRelations(FPerm.DOOR, Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY);
		faction.setPermittedRelations(FPerm.CONTAINER, Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY);
		faction.setPermittedRelations(FPerm.BUTTON, Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY);
		faction.setPermittedRelations(FPerm.LEVER, Rel.LEADER, Rel.OFFICER, Rel.MEMBER, Rel.RECRUIT, Rel.ALLY, Rel.TRUCE, Rel.NEUTRAL, Rel.ENEMY);
		faction.setPermittedRelations(FPerm.TERRITORY, Rel.LEADER, Rel.OFFICER, Rel.MEMBER);
	}
	
	
	//----------------------------------------------//
	// GET
	//----------------------------------------------//
	
	@Override
	public Faction get(String id)
	{
		if ( ! this.exists(id))
		{
			p.log(Level.WARNING, "ID de faction non existant "+id+" demand�e! �mission de nettoyage!");
			Board.clean();
			FPlayers.i.clean();
		}
		
		return super.get(id);
	}
	
	public Faction getNone()
	{
		return this.get("0");
	}
	
	//----------------------------------------------//
	// Faction tag
	//----------------------------------------------//
	
	public static ArrayList<String> validateTag(String str)
	{
		ArrayList<String> errors = new ArrayList<String>();
		
		if(MiscUtil.getComparisonString(str).length() < Conf.factionTagLengthMin)
		{
			errors.add(P.p.txt.parse("<i>La balise de faction ne peut pas �tre inf�rieure � <h>% s <i> caract�res.", Conf.factionTagLengthMin));
		}
		
		if(str.length() > Conf.factionTagLengthMax)
		{
			errors.add(P.p.txt.parse("<i>La balise de faction ne peut pas d�passer <h>% s <i> caract�res.", Conf.factionTagLengthMax));
		}
		
		for (char c : str.toCharArray())
		{
			if ( ! MiscUtil.substanceChars.contains(String.valueOf(c)))
			{
				errors.add(P.p.txt.parse("<i>L'�tiquette de faction doit �tre alphanum�rique.  \"<h>% s <i> \" n'est pas autoris�.", c));
			}
		}
		
		return errors;
	}
	
	public Faction getByTag(String str)
	{
		String compStr = MiscUtil.getComparisonString(str);
		for (Faction faction : this.get())
		{
			if (faction.getComparisonTag().equals(compStr))
			{
				return faction;
			}
		}
		return null;
	}
	
	public Faction getBestTagMatch(String searchFor)
	{
		Map<String, Faction> tag2faction = new HashMap<String, Faction>();
		
		// TODO: Slow index building
		for (Faction faction : this.get())
		{
			tag2faction.put(ChatColor.stripColor(faction.getTag()), faction);
		}
		
		String tag = TextUtil.getBestStartWithCI(tag2faction.keySet(), searchFor);
		if (tag == null) return null;
		return tag2faction.get(tag);
	}
	
	public boolean isTagTaken(String str)
	{
		return this.getByTag(str) != null;
	}

	public void econLandRewardRoutine()
	{
		if ( ! Econ.shouldBeUsed()) return;

		P.p.log("D�marrage de econLandRewardRoutine...");
		for (Faction faction : this.get())
		{
			int landCount = faction.getLandRounded();
			if (!faction.getFlag(FFlag.PEACEFUL) && landCount > 0)
			{
				Set<FPlayer> players = faction.getFPlayers();
				int playerCount = players.size();
				double reward = Conf.econLandReward * landCount / playerCount;
				for (FPlayer player : players)
				{
					Econ.modifyMoney(player, reward, "poss�der des terres de faction", "pour poss�der une faction " + landCount + " et divis� entre " + playerCount + " membre(s)");
				}
			}
		}
	}

}