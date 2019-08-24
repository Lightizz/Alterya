package fr.alterya.factions.cmd;

import fr.alterya.factions.FPlayer;
import fr.alterya.factions.struct.Permission;
import fr.alterya.factions.struct.Rel;

public class CmdDemote extends FCommand
{
	
	public CmdDemote()
	{
		super();
		this.aliases.add("demote");
		
		this.requiredArgs.add("player name");
		//this.optionalArgs.put("", "");
		
		//this.permission = Permission.DEMOTE.node;
		this.disableOnLock = true;
		
		//To demote someone from member -> recruit you must be an officer.
		//To demote someone from officer -> member you must be a leader.
		//We'll handle this internally
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		FPlayer you = this.argAsBestFPlayerMatch(0);
		if (you == null) return;
		
		if (you.getFaction() != myFaction)
		{
			msg("%s<b> n'est pas un membre de votre faction.", you.describeTo(fme, true));
			return;
		}
		
		if (you == fme)
		{
			msg("<b>Vous ne pouvez pas vous demote");
			return;
		}

		if (you.getRole() == Rel.MEMBER)
		{
			if (!fme.getRole().isAtLeast(Rel.OFFICER)) {
				msg("<b>Vous devez être un officer pour demote un membre vers le grade de recrue.");
				return;
			}
			you.setRole(Rel.RECRUIT);
			myFaction.msg("%s<i> a été demote vers le grade de recrue.", you.describeTo(myFaction, true));
		}
		else if (you.getRole() == Rel.OFFICER)
		{
			if (!fme.getRole().isAtLeast(Rel.LEADER)) {
				msg("<b>Vous devez être le chef de votre faction pour demote un officier au grade de membre.");
				return;
			}
			you.setRole(Rel.MEMBER);
			myFaction.msg("%s<i> a été demote vers le grade de membre.", you.describeTo(myFaction, true));
		}
	}
	
}
