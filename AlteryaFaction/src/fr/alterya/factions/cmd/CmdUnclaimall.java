package fr.alterya.factions.cmd;

import org.bukkit.Bukkit;

import fr.alterya.factions.P;
import fr.alterya.factions.integration.Econ;

import fr.alterya.factions.Board;
import fr.alterya.factions.Conf;
import fr.alterya.factions.event.LandUnclaimAllEvent;
import fr.alterya.factions.integration.SpoutFeatures;

public class CmdUnclaimall extends FCommand
{	
	public CmdUnclaimall()
	{
		this.aliases.add("unclaimall");
		this.aliases.add("declaimall");
		
		//this.requiredArgs.add("");
		//this.optionalArgs.put("", "");
		
		//this.permission = Permission.UNCLAIM_ALL.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = true;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		if (Econ.shouldBeUsed())
		{
			double refund = Econ.calculateTotalLandRefund(myFaction.getLandRounded());
			if(Conf.bankEnabled && Conf.bankFactionPaysLandCosts)
			{
				if ( ! Econ.modifyMoney(myFaction, refund, "pour unclaim toutes les terres", "pour unclaim toutes les terres")) return;
			}
			else
			{
				if ( ! Econ.modifyMoney(fme, refund, "pour unclaim toutes les terres", "pour unclaim toutes les terres")) return;
			}
		}

		LandUnclaimAllEvent unclaimAllEvent = new LandUnclaimAllEvent(myFaction, fme);
	Bukkit.getServer().getPluginManager().callEvent(unclaimAllEvent);
		// this event cannot be cancelled

		Board.unclaimAll(myFaction.getId());
		myFaction.msg("%s<i> a unclaim TOUTES les terres.", fme.describeTo(myFaction, true));
		SpoutFeatures.updateTerritoryDisplayLoc(null);

		if (Conf.logLandUnclaims)
			P.p.log(fme.getName()+" a unclaim tout pour la faction : "+myFaction.getTag());
	}
	
}
