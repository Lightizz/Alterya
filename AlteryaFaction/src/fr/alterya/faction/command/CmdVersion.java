package fr.alterya.faction.command;

import fr.alterya.faction.Main;
import fr.alterya.faction.struct.Permission;


public class CmdVersion extends FCommand
{
	public CmdVersion()
	{
		this.aliases.add("version");
		
		//this.requiredArgs.add("");
		//this.optionalArgs.put("", "");
		
		this.permission = Permission.VERSION.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}

	@Override
	public void perform()
	{
		msg("<i>Vous êtes en " + Main.main.getDescription().getFullName());
	}
}
