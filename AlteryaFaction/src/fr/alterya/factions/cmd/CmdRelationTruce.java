package fr.alterya.factions.cmd;

import fr.alterya.factions.struct.Rel;

public class CmdRelationTruce extends FRelationCommand
{
	public CmdRelationTruce()
	{
		aliases.add("truce");
		targetRelation = Rel.TRUCE;
	}
}
