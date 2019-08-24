package fr.alterya.factions.cmd;

import fr.alterya.factions.Board;
import fr.alterya.factions.Conf;
import fr.alterya.factions.FLocation;

public class CmdMap extends FCommand
{
	public CmdMap()
	{
		super();
		this.aliases.add("map");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("on/off", "once");
		
		//this.permission = Permission.MAP.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		if (this.argIsSet(0))
		{
			if (this.argAsBool(0, ! fme.isMapAutoUpdating()))
			{
				// Turn on

				// if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
				if ( ! payForCommand(Conf.econCostMap, "to show the map", "for showing the map")) return;

				fme.setMapAutoUpdating(true);
				msg("<i>Carte automatique <green>activ�e.");
				
				// And show the map once
				showMap();
			}
			else
			{
				// Turn off
				fme.setMapAutoUpdating(false);
				msg("<i>Carte automatique <red>d�sactiv�e.");
			}
		}
		else
		{
			// if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
			if ( ! payForCommand(Conf.econCostMap, "to show the map", "for showing the map")) return;

			showMap();
		}
	}
	
	public void showMap()
	{
		sendMessage(Board.getMap(myFaction, new FLocation(fme), fme.getPlayer().getLocation().getYaw()));
	}
	
}
