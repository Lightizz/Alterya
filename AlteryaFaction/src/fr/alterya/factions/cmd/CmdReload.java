package fr.alterya.factions.cmd;

import fr.alterya.factions.P;

import fr.alterya.factions.Board;
import fr.alterya.factions.Conf;
import fr.alterya.factions.FPlayers;
import fr.alterya.factions.Factions;
import fr.alterya.factions.struct.Permission;

public class CmdReload extends FCommand
{
	
	public CmdReload()
	{
		super();
		this.aliases.add("reload");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("file", "all");
		
		this.permission = Permission.RELOAD.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		long timeInitStart = System.currentTimeMillis();
		String file = this.argAsString(0, "all").toLowerCase();
		
		String fileName;
		
		if (file.startsWith("c"))
		{
			Conf.load();
			fileName = "conf.json";
		}
		else if (file.startsWith("b"))
		{
			Board.load();
			fileName = "board.json";
		}
		else if (file.startsWith("f"))
		{
			Factions.i.loadFromDisc();
			fileName = "factions.json";
		}
		else if (file.startsWith("p"))
		{
			FPlayers.i.loadFromDisc();
			fileName = "players.json";
		}
		else if (file.startsWith("a"))
		{
			fileName = "all";
			Conf.load();
			FPlayers.i.loadFromDisc();
			Factions.i.loadFromDisc();
			Board.load();
		}
		else
		{
			P.p.log("RELOAD CANCELLED - SPECIFIED FILE INVALID");
			msg("<b>Invalid file specified. <i>Valid files: all, conf, board, factions, players");
			return;
		}
		
		long timeReload = (System.currentTimeMillis()-timeInitStart);
		
		msg("<i>Reloaded <h>%s <i>from disk, took <h>%dms<i>.", fileName, timeReload);
	}
	
}
