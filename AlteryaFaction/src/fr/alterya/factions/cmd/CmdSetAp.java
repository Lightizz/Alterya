package fr.alterya.factions.cmd;

import fr.alterya.factions.Board;
import fr.alterya.factions.Conf;
import fr.alterya.factions.FLocation;
import fr.alterya.factions.Faction;
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
			fme.msg("<b>Désolé, les f ap sont désactivé sur ce monde..");
			return;
		}
		
		Faction faction = this.argAsFaction(0, myFaction);
		if (faction == null) return;
		
		if ( ! FPerm.SETAP.has(sender, faction, true)) return;
		
		if
		(
			! fme.hasAdminMode()
			&&
			Conf.apMustBeInClaimedTerritory
			&& 
			Board.getFactionAt(new FLocation(me)) != faction
		)
		{
			fme.msg("<b>Désolé, votre avant poste de faction ne peut être situé que sur votre propre territoire(claim).");
			return;
		}

		// if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
		if ( ! payForCommand(Conf.econCostSetAp, "définir l'avant poste de la faction ", " pour définir l'avant poste de la faction")) return;

		if(Faction.hasRight == true) {
			Conf.apAuthorised = true;
		}
		
		if(Conf.apAuthorised == true) {
			faction.setAp(me.getLocation());
		}
		
		if(Conf.apAuthorised == false) {
			fme.msg("<b>Vous n'avez pas les permissions d'un Responsalbe ou d'un Administrateur pour poser un avant poste");
			return;
		}
		
		faction.msg("%s<i> a définit l'avant poste pour votre faction. Vous pouvez maintenant utiliser:", fme.describeTo(myFaction, true));
		faction.sendMessage(p.cmdBase.cmdAp.getUseageTemplate());
		if (faction != myFaction)
		{
			fme.msg("<b>Vous avez défini l'avant poste pour la faction "+faction.getTag(fme)+"<i>.");
		}
	}
	
}

