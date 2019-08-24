package fr.alterya.factions.cmd;

import fr.alterya.factions.Board;
import fr.alterya.factions.Conf;
import fr.alterya.factions.FPlayers;
import fr.alterya.factions.Factions;
import fr.alterya.factions.struct.Permission;

public class CmdSaveAll extends FCommand
{
	
	public CmdSaveAll()
	{
		super();
		this.aliases.add("saveall");
		this.aliases.add("save");
		
		//this.requiredArgs.add("");
		//this.optionalArgs.put("", "");
		
		this.permission = Permission.SAVE.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		FPlayers.i.saveToDisc();
		Factions.i.saveToDisc();
		Board.save();
		Conf.save();
		msg("<i>Factions saved to disk!");
	}
	
}