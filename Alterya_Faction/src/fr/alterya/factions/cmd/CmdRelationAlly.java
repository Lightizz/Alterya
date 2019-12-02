package fr.alterya.factions.cmd;

import fr.alterya.factions.struct.Rel;

public class CmdRelationAlly extends FRelationCommand
{
	public CmdRelationAlly()
	{
		aliases.add("ally");
		targetRelation = Rel.ALLY;
	}
}
