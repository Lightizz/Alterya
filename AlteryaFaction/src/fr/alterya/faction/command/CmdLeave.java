package fr.alterya.faction.command;

import fr.alterya.faction.struct.Permission;

public class CmdLeave extends FCommand {
	
	public CmdLeave()
	{
		super();
		this.aliases.add("leave");
		
		//this.requiredArgs.add("");
		//this.optionalArgs.put("", "");
		
		this.permission = Permission.LEAVE.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = true;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		fme.leave(true);
	}
	
}
