package fr.alterya.factions.cmd;

import fr.alterya.factions.Board;
import fr.alterya.factions.Conf;
import fr.alterya.factions.FLocation;
import fr.alterya.factions.Faction;
import fr.alterya.factions.struct.FPerm;

public class CmdSethome extends FCommand
{
	public CmdSethome()
	{
		this.aliases.add("sethome");
		
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
		if ( ! Conf.homesEnabled)
		{
			fme.msg("<b>D�sol�, les f homes sont d�sactiv� sur ce monde..");
			return;
		}
		
		Faction faction = this.argAsFaction(0, myFaction);
		if (faction == null) return;
		
		if ( ! FPerm.SETHOME.has(sender, faction, true)) return;
		
		if
		(
			! fme.hasAdminMode()
			&&
			Conf.homesMustBeInClaimedTerritory
			&& 
			Board.getFactionAt(new FLocation(me)) != faction
		)
		{
			fme.msg("<b>D�sol�, votre home de faction ne peut �tre situ� que sur votre propre territoire(claim).");
			return;
		}

		// if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
		if ( ! payForCommand(Conf.econCostSethome, "d�finir la maison de la faction ", " pour d�finir la maison de la faction")) return;

		faction.setHome(me.getLocation());
		
		faction.msg("%s<i> a d�finit la maison pour votre faction. Vous pouvez maintenant utiliser:", fme.describeTo(myFaction, true));
		faction.sendMessage(p.cmdBase.cmdHome.getUseageTemplate());
		if (faction != myFaction)
		{
			fme.msg("<b>Vous avez d�fini la maison pour la faction "+faction.getTag(fme)+"<i>.");
		}
	}
	
}
