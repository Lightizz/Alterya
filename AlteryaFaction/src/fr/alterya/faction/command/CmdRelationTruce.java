package fr.alterya.faction.command;

import fr.alterya.faction.struct.Rel;

public class CmdRelationTruce extends FRelationCommand
{
	public CmdRelationTruce()
	{
		aliases.add("truce");
		targetRelation = Rel.TRUCE;
	}
}
