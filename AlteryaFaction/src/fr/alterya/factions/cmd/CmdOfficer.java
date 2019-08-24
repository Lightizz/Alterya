package fr.alterya.factions.cmd;

import fr.alterya.factions.FPlayer;
import fr.alterya.factions.Faction;
import fr.alterya.factions.struct.Permission;
import fr.alterya.factions.struct.Rel;

public class CmdOfficer extends FCommand
{
	
	public CmdOfficer()
	{
		super();
		this.aliases.add("officer");
		
		this.requiredArgs.add("player name");
		//this.optionalArgs.put("", "");
		
		//this.permission = Permission.OFFICER.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		FPlayer you = this.argAsBestFPlayerMatch(0);
		if (you == null) return;

		boolean permAny = Permission.OFFICER_ANY.has(sender, false);
		Faction targetFaction = you.getFaction();

		if (targetFaction != myFaction && !permAny)
		{
			msg("%s<b> n'est pas un membre de votre faction.", you.describeTo(fme, true));
			return;
		}
		
		if(fme.getFaction() == null || myFaction == null) {
			msg("<b>Une erreur est survenue : 1F. Veuillez contacter un Administrateur en donnant l'ID de l'erreur.");
			return;
		}
		
		if (fme != null && fme.getRole() != Rel.LEADER && !permAny)
		{
			msg("<b>VOus n'êtes pas le leader de votre faction.");
			return;
		}

		if (you == fme && !permAny)
		{
			msg("<b>La cible ne peut pas être vous même.");
			return;
		}

		if (you.getRole() == Rel.LEADER)
		{
			msg("<b>Le joueur ciblé est un chef de faction. Rétrogradez le d'abord.");
			return;
		}

		if (you.getRole() == Rel.OFFICER)
		{
			// Revoke
			you.setRole(Rel.MEMBER);
			targetFaction.msg("%s<i> n'est plus officier dans votre faction.", you.describeTo(targetFaction, true));
			msg("<i>Vous avez supprimé le statut d'officier de% s <i>.", you.describeTo(fme, true));
		}
		else
		{
			// Give
			you.setRole(Rel.OFFICER);
			targetFaction.msg("%s<i> a été promu officier dans votre faction.", you.describeTo(targetFaction, true));
			msg("<i>Vous avez promu% s <i> au poste de responsable.", you.describeTo(fme, true));
		}
	}
	
}
