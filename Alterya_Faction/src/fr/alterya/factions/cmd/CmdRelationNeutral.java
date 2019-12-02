package fr.alterya.factions.cmd;

import fr.alterya.factions.struct.Rel;

public class CmdRelationNeutral extends FRelationCommand
{
	public CmdRelationNeutral()
	{
		aliases.add("neutral");
		targetRelation = Rel.NEUTRAL;
	}
}
