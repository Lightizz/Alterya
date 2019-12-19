package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Conf;
import com.massivecraft.factions.Faction;

public class CmdAuthoriseAp extends FCommand
{
	public CmdAuthoriseAp() {
		super();
		this.aliases.add("authoriseap");
		
		this.requiredArgs.add("faction");
		//this.optionalArgs.put("", "");
		
		//this.permission = Permission.AUTHAP.node;
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
