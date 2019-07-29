package fr.alterya.faction;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;

import org.bukkit.craftbukkit.libs.com.google.gson.reflect.TypeToken;
import fr.alterya.faction.integration.LWCFeatures;
import fr.alterya.faction.iface.RelationParticipator;
import fr.alterya.faction.struct.TerritoryAccess;
import fr.alterya.faction.util.AsciiCompass;
import fr.alterya.faction.zcore.util.DiscUtil;

public class Board
{
	private static transient File file = new File(Main.main.getDataFolder(), "board.json");
	private static transient HashMap<FLocation, TerritoryAccess> flocationIds = new HashMap<FLocation, TerritoryAccess>();
	
	public static String getIdAt(FLocation flocation)
	{
		if ( ! flocationIds.containsKey(flocation)) return "0";
		
		return flocationIds.get(flocation).getHostFactionID();
	}

	public static TerritoryAccess getTerritoryAccessAt(FLocation flocation)
	{
		if ( ! flocationIds.containsKey(flocation))
		{
			return new TerritoryAccess("0");
		}
		return flocationIds.get(flocation);
	}

	public static Faction getFactionAt(FLocation flocation)
	{
		return Factions.i.get(getIdAt(flocation));
	}
	public static Faction getFactionAt(Location location)
	{
		return getFactionAt(new FLocation(location));
	}
	public static Faction getFactionAt(Block block)
	{
		return getFactionAt(new FLocation(block));
	}
	
	public static void setIdAt(String id, FLocation flocation)
	{
		if (id == "0")
			removeAt(flocation);

		flocationIds.put(flocation, new TerritoryAccess(id));
	}
	
	public static void setFactionAt(Faction faction, FLocation flocation)
	{
		setIdAt(faction.getId(), flocation);
	}
	
	public static void removeAt(FLocation flocation)
	{
		if(Conf.onUnclaimResetLwcLocks && LWCFeatures.getEnabled())
			LWCFeatures.clearAllChests(flocation);

		flocationIds.remove(flocation);
	}
	
	public static void unclaimAll(String factionId)
	{
		Iterator<Entry<FLocation, TerritoryAccess>> iter = flocationIds.entrySet().iterator();
		while (iter.hasNext())
		{
			Entry<FLocation, TerritoryAccess> entry = iter.next();
			if (entry.getValue().getHostFactionID().equals(factionId))
			{
					if(Conf.onUnclaimResetLwcLocks && LWCFeatures.getEnabled())
						LWCFeatures.clearAllChests(entry.getKey());

					iter.remove();
			}
		}
	}

	public static boolean isBorderLocation(FLocation flocation)
	{
		Faction faction = getFactionAt(flocation);
		FLocation a = flocation.getRelative(1, 0);
		FLocation b = flocation.getRelative(-1, 0);
		FLocation c = flocation.getRelative(0, 1);
		FLocation d = flocation.getRelative(0, -1);
		return faction != getFactionAt(a) || faction != getFactionAt(b) || faction != getFactionAt(c) || faction != getFactionAt(d);
	}

	public static boolean isConnectedLocation(FLocation flocation, Faction faction)
	{
		FLocation a = flocation.getRelative(1, 0);
		FLocation b = flocation.getRelative(-1, 0);
		FLocation c = flocation.getRelative(0, 1);
		FLocation d = flocation.getRelative(0, -1);
		return faction == getFactionAt(a) || faction == getFactionAt(b) || faction == getFactionAt(c) || faction == getFactionAt(d);
	}
		
	public static void clean()
	{
		Iterator<Entry<FLocation, TerritoryAccess>> iter = flocationIds.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<FLocation, TerritoryAccess> entry = iter.next();
			if ( ! Factions.i.exists(entry.getValue().getHostFactionID()))
			{
				if(Conf.onUnclaimResetLwcLocks && LWCFeatures.getEnabled())
					LWCFeatures.clearAllChests(entry.getKey());

				Main.main.log("Board cleaner removed "+entry.getValue().getHostFactionID()+" from "+entry.getKey());
				iter.remove();
			}
		}
	}	
	
	public static int getFactionCoordCount(String factionId)
	{
		int ret = 0;
		for (TerritoryAccess thatFactionId : flocationIds.values())
		{
			if(thatFactionId.getHostFactionID().equals(factionId))
			{
				ret += 1;
			}
		}
		return ret;
	}
	
	public static int getFactionCoordCount(Faction faction)
	{
		return getFactionCoordCount(faction.getId());
	}
	
	public static int getFactionCoordCountInWorld(Faction faction, String worldName)
	{
		String factionId = faction.getId();
		int ret = 0;
		Iterator<Entry<FLocation, TerritoryAccess>> iter = flocationIds.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<FLocation, TerritoryAccess> entry = iter.next();
			if (entry.getValue().getHostFactionID().equals(factionId) && entry.getKey().getWorldName().equals(worldName))
			{
				ret += 1;
			}
		}
		return ret;
	}

	public static ArrayList<String> getMap(RelationParticipator observer, FLocation flocation, double inDegrees)
	{
		ArrayList<String> ret = new ArrayList<String>();
		Faction factionLoc = getFactionAt(flocation);
		ret.add(Main.main.txt.titleize("("+flocation.getCoordString()+") "+factionLoc.getTag(observer)));
		
		int halfWidth = Conf.mapWidth / 2;
		int halfHeight = Conf.mapHeight / 2;
		FLocation topLeft = flocation.getRelative(-halfWidth, -halfHeight);
		int width = halfWidth * 2 + 1;
		int height = halfHeight * 2 + 1;
		
		height--;
		
		
		Map<Faction, Character> fList = new HashMap<Faction, Character>();
		int chrIdx = 0;
		
		for (int dz = 0; dz < height; dz++)
		{
			String row = "";
			for (int dx = 0; dx < width; dx++)
			{
				if(dx == halfWidth && dz == halfHeight)
				{
					row += ChatColor.AQUA+"+";
					continue;
				}
			
				FLocation flocationHere = topLeft.getRelative(dx, dz);
				Faction factionHere = getFactionAt(flocationHere);
				if (factionHere.isNone())
				{
					row += ChatColor.GRAY+"-";
				}
				else
				{
					if (!fList.containsKey(factionHere))
						fList.put(factionHere, Conf.mapKeyChrs[chrIdx++]);
					char fchar = fList.get(factionHere);
					row += factionHere.getColorTo(observer) + "" + fchar;
				}
			}
			ret.add(row);
		}
		
		ArrayList<String> asciiCompass = AsciiCompass.getAsciiCompass(inDegrees, ChatColor.RED, Main.main.txt.parse("<a>"));

		ret.set(1, asciiCompass.get(0)+ret.get(1).substring(3*3));
		ret.set(2, asciiCompass.get(1)+ret.get(2).substring(3*3));
		ret.set(3, asciiCompass.get(2)+ret.get(3).substring(3*3));
			
		String fRow = "";
		for(Faction keyfaction : fList.keySet())
		{
			fRow += ""+keyfaction.getColorTo(observer) + fList.get(keyfaction) + ": " + keyfaction.getTag() + " ";
		}
		fRow = fRow.trim();
		ret.add(fRow);
		
		return ret;
	}
	
	public static Map<String,Map<String,TerritoryAccess>> dumpAsSaveFormat()
	{
		Map<String,Map<String,TerritoryAccess>> worldCoordIds = new HashMap<String,Map<String,TerritoryAccess>>(); 
		
		String worldName, coords;
		TerritoryAccess data;
		
		for (Entry<FLocation, TerritoryAccess> entry : flocationIds.entrySet())
		{
			worldName = entry.getKey().getWorldName();
			coords = entry.getKey().getCoordString();
			data = entry.getValue();
			if ( ! worldCoordIds.containsKey(worldName))
			{
				worldCoordIds.put(worldName, new TreeMap<String,TerritoryAccess>());
			}
			
			worldCoordIds.get(worldName).put(coords, data);
		}
		
		return worldCoordIds;
	}
	
	public static void loadFromSaveFormat(Map<String,Map<String,TerritoryAccess>> worldCoordIds)
	{
		flocationIds.clear();
		
		String worldName;
		String[] coords;
		int x, z;
		TerritoryAccess data;
		
		for (Entry<String,Map<String,TerritoryAccess>> entry : worldCoordIds.entrySet())
		{
			worldName = entry.getKey();
			for (Entry<String,TerritoryAccess> entry2 : entry.getValue().entrySet())
			{
				coords = entry2.getKey().trim().split("[,\\s]+");
				x = Integer.parseInt(coords[0]);
				z = Integer.parseInt(coords[1]);
				data = entry2.getValue();
				flocationIds.put(new FLocation(worldName, x, z), data);
			}
		}
	}
	
	public static boolean save()
	{
		
		try
		{
			DiscUtil.write(file, Main.main.gson.toJson(dumpAsSaveFormat()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Main.main.log("Failed to save the board to disk.");
			return false;
		}
		
		return true;
	}
	
	public static boolean load()
	{
		Main.main.log("Loading board from disk");
		
		if ( ! file.exists())
		{
			Main.main.log("No board to load from disk. Creating new file.");
			save();
			return true;
		}
		
		try
		{
			Type type = new TypeToken<Map<String,Map<String,TerritoryAccess>>>(){}.getType();
			Map<String,Map<String,TerritoryAccess>> worldCoordIds = Main.main.gson.fromJson(DiscUtil.read(file), type);
			loadFromSaveFormat(worldCoordIds);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Main.main.log("Failed to load the board from disk.");
			return false;
		}
			
		return true;
	}
}



















