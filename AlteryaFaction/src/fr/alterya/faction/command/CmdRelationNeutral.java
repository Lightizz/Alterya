package fr.alterya.faction.command;

import fr.alterya.faction.struct.Rel;

public class CmdRelationNeutral extends FRelationCommand
{
	public CmdRelationNeutral()
	{
		aliases.add("neutral");
		targetRelation = Rel.NEUTRAL;
	}
}
