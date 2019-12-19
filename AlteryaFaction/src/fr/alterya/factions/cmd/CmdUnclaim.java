package fr.alterya.factions.cmd;

import org.bukkit.Bukkit;

import fr.alterya.factions.integration.Econ;
import fr.alterya.factions.P;

import fr.alterya.factions.Board;
import fr.alterya.factions.Conf;
import fr.alterya.factions.FLocation;
import fr.alterya.factions.Faction;
import fr.alterya.factions.event.LandUnclaimEvent;
import fr.alterya.factions.integration.SpoutFeatures;
import fr.alterya.factions.struct.FPerm;

public class CmdUnclaim extends FCommand
{
	public CmdUnclaim()
	{
		this.aliases.add("unclaim");
		this.aliases.add("declaim");
		
		//this.requiredArgs.add("");
		//this.optionalArgs.put("", "");
		
		//this.permission = Permission.UNCLAIM.node;
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

		if(myFaction == null) {
			msg("<b>Une erreur est survenue : 10F. Veuillez contacter un Administrateur en donnant l'ID de l'erreur.");
			return;
		}
		
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
				if ( ! Econ.modifyMoney(myFaction, refund, "pour unclaim cette terre", "pour unclaim cette terre")) return;
			}
			else
			{
				if ( ! Econ.modifyMoney(fme, refund, "pour unclaim cette terre", "pour unclaim cette terre")) return;
			}
		}

		Board.removeAt(flocation);
		SpoutFeatures.updateTerritoryDisplayLoc(flocation);
		myFaction.msg("%s<i> avez unclaim plusieurs terres.", fme.describeTo(myFaction, true));

		if (Conf.logLandUnclaims)
			P.p.log(fme.getName() + " a unclaim une terre aux coordonnées :" + flocation.getCoordString() + ", à la faction : " + otherFaction.getTag());
	}
	
}
