package fr.alterya.factions.cmd;

import fr.alterya.factions.FPlayer;

public class CmdDeinvite extends FCommand
{
	
	public CmdDeinvite()
	{
		super();
		this.aliases.add("deinvite");
		this.aliases.add("deinv");
		
		this.requiredArgs.add("joueur");
		//this.optionalArgs.put("", "");
		
		//this.permission = Permission.DEINVITE.node;
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
		if(myFaction.isInvited(you) == true) {
			if (you == null) return;
			
			if (you.getFaction() == myFaction)
			{
				msg("%s<i> est déjà membre de %s", you.getName(), myFaction.getTag());
				msg("<i>Tu pourrais vouloir : %s", p.cmdBase.cmdKick.getUseageTemplate(false));
				return;
			}
			myFaction.deinvite(you);
			
			you.msg("%s<i> a révoqué votre invitation à <h>%s<i>.", fme.describeTo(you), myFaction.describeTo(you));
			
			myFaction.msg("%s<i> a révoqué l'invitation de %s<i>.", fme.describeTo(myFaction), you.describeTo(myFaction));
		}
	}
}
