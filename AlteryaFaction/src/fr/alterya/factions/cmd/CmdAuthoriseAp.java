package fr.alterya.factions.cmd;

import fr.alterya.factions.Conf;
import fr.alterya.factions.Faction;
import fr.alterya.factions.struct.Permission;

public class CmdAuthoriseAp extends FCommand
{
	public CmdAuthoriseAp() {
		super();
		this.aliases.add("authoriseap");
		
		this.requiredArgs.add("faction");
		//this.optionalArgs.put("", "");
		
		this.permission = Permission.AUTHAP.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void perform() {
		Faction targetedFaction = this.argAsFaction(0);
		targetedFaction.hasRight = true;
		Conf.apAuthorised = true;
		msg("§eLa faction §2" + targetedFaction.getTag() + "§e a maintenant le droit de poser un avant poste.");
	}
}
