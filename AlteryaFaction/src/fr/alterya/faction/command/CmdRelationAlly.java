package fr.alterya.faction.command;

import fr.alterya.faction.struct.Rel;

public class CmdRelationAlly extends FRelationCommand
{
	public CmdRelationAlly()
	{
		aliases.add("ally");
		targetRelation = Rel.ALLY;
	}
}
