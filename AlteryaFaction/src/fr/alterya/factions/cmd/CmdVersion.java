package fr.alterya.factions.cmd;

import fr.alterya.factions.P;

public class CmdVersion extends FCommand
{
	public CmdVersion()
	{
		this.aliases.add("version");
		
		//this.requiredArgs.add("");
		//this.optionalArgs.put("", "");
		
		//this.permission = Permission.VERSION.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}

	@Override
	public void perform()
	{
		msg("<i>Version du plugin faction : "+P.p.getDescription().getFullName());
	}
}
