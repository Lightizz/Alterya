package fr.alterya.factions.cmd;

import org.bukkit.Bukkit;

import fr.alterya.factions.P;

import fr.alterya.factions.Conf;
import fr.alterya.factions.FPlayer;
import fr.alterya.factions.Faction;
import fr.alterya.factions.event.FPlayerLeaveEvent;
import fr.alterya.factions.struct.FPerm;
import fr.alterya.factions.struct.Rel;

public class CmdKick extends FCommand
{
	
	public CmdKick()
	{
		super();
		this.aliases.add("kick");
		
		this.requiredArgs.add("player");
		//this.optionalArgs.put("", "");
		
		//this.permission = Permission.KICK.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{	
		FPlayer you = this.argAsBestFPlayerMatch(0);
		if (you == null) {
			msg("<b>Vous devez d'abord créer une faction.");
			return;
		}
		
		if (fme == you)
		{
			msg("<b>Vous ne pouvez pas vous kick.");
			msg("<i>Vous devriez vouloir: %s", p.cmdBase.cmdLeave.getUseageTemplate(false));
			return;
		}
		
		if (you.getRole() == Rel.LEADER && !(this.senderIsConsole || fme.hasAdminMode()))
		{
			msg("<b>Le chef ne peut pas être kick.");
			return;
		}

		if ( ! Conf.canLeaveWithNegativePower && you.getPower() < 0)
		{
			msg("<b>Vous ne pouvez pas kick ce membre tant que son pouvoir n'est pas positif.");
			return;
		}
		
		Faction yourFaction = you.getFaction();

		if (fme != null && ! FPerm.KICK.has(fme, yourFaction)) return;

		// if economy is enabled, they're not on the bypass list, and this command has a cost set, make sure they can pay
		if ( ! canAffordCommand(Conf.econCostKick, "virer quelqu'un de la faction")) return;

		// trigger the leave event (cancellable) [reason:kicked]
		FPlayerLeaveEvent event = new FPlayerLeaveEvent(you, you.getFaction(), FPlayerLeaveEvent.PlayerLeaveReason.KICKED);
		Bukkit.getServer().getPluginManager().callEvent(event);
		if (event.isCancelled()) return;

		// then make 'em pay (if applicable)
		if ( ! payForCommand(Conf.econCostKick, "donner un coup de pied à quelqu'un de la faction ", " pour avoir donné un coup de pied à quelqu'un de la faction")) return;

		yourFaction.msg("%s<i> a kick %s<i> de la faction.", fme.describeTo(yourFaction, true), you.describeTo(yourFaction, true));
		you.msg("%s<i> vous a kick de %s<i>.", fme.describeTo(you, true), yourFaction.describeTo(you));
		if (yourFaction != myFaction)
		{
			fme.msg("<i>Vous avez kick %s<i> de la faction %s<i>!", you.describeTo(fme), yourFaction.describeTo(fme));
		}

		if (Conf.logFactionKick)
			P.p.log((senderIsConsole ? "Une commande de la console" : fme.getName())+" a kick "+you.getName()+" de la faction: "+yourFaction.getTag());

		if (you.getRole() == Rel.LEADER)
			yourFaction.promoteNewLeader();

		yourFaction.deinvite(you);
		you.resetFactionData();
	}
	
}
