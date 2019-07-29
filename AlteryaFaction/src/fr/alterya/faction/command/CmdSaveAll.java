package fr.alterya.faction.command;

import fr.alterya.faction.Board;
import fr.alterya.faction.Conf;
import fr.alterya.faction.FPlayers;
import fr.alterya.faction.Factions;
import fr.alterya.faction.struct.Permission;

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