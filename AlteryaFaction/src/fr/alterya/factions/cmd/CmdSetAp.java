package fr.alterya.factions.cmd;

import fr.alterya.factions.Board;
import fr.alterya.factions.Conf;
import fr.alterya.factions.FLocation;
import fr.alterya.factions.Faction;
import fr.alterya.factions.Factions;
import fr.alterya.factions.integration.SpoutFeatures;
import fr.alterya.factions.struct.FPerm;

public class CmdSetAp extends FCommand
{
	public CmdSetAp()
	{
		this.aliases.add("setap");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("faction", "votre");
		
		//this.permission = Permission.SETHOME.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		if ( ! Conf.apEnabled)
		{
			fme.msg("<b>D�sol�, les f ap sont d�sactiv� sur ce monde..");
			return;
		}
		
		Faction faction = this.argAsFaction(0, myFaction);
		if (faction == null) return;
		
		if ( ! FPerm.SETAP.has(sender, faction, true)) return;
		
		// if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
		if ( ! payForCommand(Conf.econCostSetAp, "d�finir l'avant poste de la faction ", " pour d�finir l'avant poste de la faction")) return;

		if(Faction.hasRight == true) {
			Conf.apAuthorised = true;
		}
		
		if(Conf.apAuthorised == false) {
			fme.msg("<b>Vous n'avez pas les permissions d'un Responsable ou d'un Administrateur pour poser un avant poste");
			return;
		}
		
		if(Conf.apMustBeInClaimedTerritory && Board.getFactionAt(new FLocation(me)) != Factions.i.getByTag("ApZone")) {
			fme.msg("�eVous �apouvez positionner votre avant poste uniquement en zone d'Ap (�eApZone�a).");
			return;
		}
		
		if(Conf.apAuthorised == true) {
			Board.removeAt(new FLocation(me));
			SpoutFeatures.updateTerritoryDisplayLoc(new FLocation(me));
			fme.attemptClaim(myFaction, me.getLocation(), true);
			if
			(
				! fme.hasAdminMode()
				&&
				Conf.apMustBeInClaimedTerritory
				&& 
				Board.getFactionAt(new FLocation(me)) != faction
			)
			{
				fme.msg("<b>D�sol�, votre avant poste de faction ne peut �tre situ� que sur votre propre territoire(claim).");
				return;
			}
			faction.setAp(me.getLocation());
		}
		
		faction.msg("%s<i> a d�finit l'avant poste pour votre faction. Vous pouvez maintenant utiliser:", fme.describeTo(myFaction, true));
		faction.sendMessage(p.cmdBase.cmdAp.getUseageTemplate());
		if (faction != myFaction)
		{
			fme.msg("<b>Vous avez d�fini l'avant poste pour la faction "+faction.getTag(fme)+"<i>.");
		}
	}
	
}

