package fr.alterya.factions.cmd;

import fr.alterya.factions.FPlayer;
import fr.alterya.factions.struct.Rel;

public class CmdPromote extends FCommand
{
	
	public CmdPromote()
	{
		super();
		this.aliases.add("promote");
		
		this.requiredArgs.add("player name");
		//this.optionalArgs.put("", "");
		
		//this.permission = Permission.PROMOTE.node;
		this.disableOnLock = true;
		
		//To promote someone from recruit -> member you must be an officer.
		//To promote someone from member -> officer you must be a leader.
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
		if (you == null) {
			msg("<b>Une erreur est survenue : 6F. Veuillez contacter un Administrateur en donnant l'ID de l'erreur.");
			return;
		}
		
		if (you.getFaction() != myFaction)
		{
			msg("%s<b> n'est pas un membre de votre faction.", you.describeTo(fme, true));
			return;
		}
		
		if (you == fme)
		{
			msg("<b>La cible de peut pas être vous même.");
			return;
		}

		if (you.getRole() == Rel.RECRUIT)
		{
			if (!fme.getRole().isAtLeast(Rel.OFFICER)) {
				msg("<b>Vous devez être un officier pour promouvoir quelqu'un à membre.");
				return;
			}
			you.setRole(Rel.MEMBER);
			myFaction.msg("%s<i> a été promu au rang de membre de votre faction.", you.describeTo(myFaction, true));
		}
		else if (you.getRole() == Rel.MEMBER)
		{
			if (!fme.getRole().isAtLeast(Rel.LEADER)) {
				msg("<b>Vous devez être le leader pour promouvoir quelqu'un à officier.");
				return;
			}
			// Give
			you.setRole(Rel.OFFICER);
			myFaction.msg("%s<i> a été promu au rang d'officier de votre faction.", you.describeTo(myFaction, true));
		}
	}
	
}
