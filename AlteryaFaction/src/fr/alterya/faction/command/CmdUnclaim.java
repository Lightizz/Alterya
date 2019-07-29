package fr.alterya.faction.command;

import org.bukkit.Bukkit;

import fr.alterya.faction.Board;
import fr.alterya.faction.Conf;
import fr.alterya.faction.event.LandUnclaimEvent;
import fr.alterya.faction.integration.Econ;
import fr.alterya.faction.integration.SpoutFeatures;
import fr.alterya.faction.FLocation;
import fr.alterya.faction.Faction;
import fr.alterya.faction.Main;
import fr.alterya.faction.struct.FPerm;
import fr.alterya.faction.struct.Permission;

public class CmdUnclaim extends FCommand
{
	public CmdUnclaim()
	{
		this.aliases.add("unclaim");
		this.aliases.add("declaim");
		
		//this.requiredArgs.add("");
		//this.optionalArgs.put("", "");
		
		this.permission = Permission.UNCLAIM.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		FLocation flocation = new FLocation(fme);
		Faction otherFaction = Board.getFactionAt(flocation);

		if ( ! FPerm.TERRITORY.has(sender, otherFaction, true)) return;

		LandUnclaimEvent unclaimEvent = new LandUnclaimEvent(flocation, otherFaction, fme);
		Bukkit.getServer().getPluginManager().callEvent(unclaimEvent);
		if(unclaimEvent.isCancelled()) return;
	
		//String moneyBack = "<i>";
		if (Econ.shouldBeUsed())
		{
			double refund = Econ.calculateClaimRefund(myFaction.getLandRounded());
			
			if(Conf.bankEnabled && Conf.bankFactionPaysLandCosts)
			{
				if ( ! Econ.modifyMoney(myFaction, refund, "to unclaim this land", "for unclaiming this land")) return;
			}
			else
			{
				if ( ! Econ.modifyMoney(fme      , refund, "to unclaim this land", "for unclaiming this land")) return;
			}
		}

		Board.removeAt(flocation);
		SpoutFeatures.updateTerritoryDisplayLoc(flocation);
		myFaction.msg("%s<i> unclaimed some land.", fme.describeTo(myFaction, true));

		if (Conf.logLandUnclaims)
			Main.main.log(fme.getName()+" unclaimed land at ("+flocation.getCoordString()+") from the faction: "+otherFaction.getTag());
	}
	
}
