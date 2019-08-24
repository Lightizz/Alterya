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
		this.optionalArgs.put("faction", "your");
		
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
			fme.msg("<b>Désolé, les f homes sont désactivé sur ce monde..");
			return;
		}
		
		Faction faction = this.argAsFaction(0, myFaction);
		if (faction == null) return;
		
		// Can the player set the home for this faction?
		if ( ! FPerm.SETHOME.has(sender, faction, true)) return;
		
		// Can the player set the faction home HERE?
		if
		(
			! fme.hasAdminMode()
			&&
			Conf.homesMustBeInClaimedTerritory
			&& 
			Board.getFactionAt(new FLocation(me)) != faction
		)
		{
			fme.msg("<b>Désolé, votre home de faction ne peut être situé que sur votre propre territoire(claim).");
			return;
		}

		// if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
		if ( ! payForCommand(Conf.econCostSethome, "to set the faction home", "for setting the faction home")) return;

		faction.setHome(me.getLocation());
		
		faction.msg("%s<i> définir la maison pour votre faction. Vous pouvez maintenant utiliser:", fme.describeTo(myFaction, true));
		faction.sendMessage(p.cmdBase.cmdHome.getUseageTemplate());
		if (faction != myFaction)
		{
			fme.msg("<b>Vous avez défini la maison pour la faction "+faction.getTag(fme)+"<i>.");
		}
	}
	
}
