package fr.alterya.factions.cmd;

import fr.alterya.factions.Conf;
import fr.alterya.factions.FPlayer;
import fr.alterya.factions.struct.FPerm;

public class CmdInvite extends FCommand
{
	public CmdInvite()
	{
		super();
		this.aliases.add("invite");
		this.aliases.add("inv");
		
		this.requiredArgs.add("player");
		//this.optionalArgs.put("", "");
		
		//this.permission = Permission.INVITE.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = true;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		FPlayer you = this.argAsBestFPlayerMatch(0);
		if (you == null) return;
		
		if (you.getFaction() == myFaction)
		{
			msg("%s<i> est déjà un membre de %s", you.getName(), myFaction.getTag());
			msg("<i>Tu pourrais vouloir : " +  p.cmdBase.cmdKick.getUseageTemplate(false));
			return;
		}

		if (fme != null && ! FPerm.INVITE.has(fme, myFaction)) return;
		
		// if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
		if ( ! payForCommand(Conf.econCostInvite, "inviter quelqu'un ", " pour inviter quelqu'un")) return;

		myFaction.invite(you);
		
		you.msg("%s<i> vous a invité dans %s", fme.describeTo(you, true), myFaction.describeTo(you));
		myFaction.msg("%s<i> a été invité %s<i> dans votre faction.", fme.describeTo(myFaction, true), you.describeTo(myFaction));
	}
	
}
