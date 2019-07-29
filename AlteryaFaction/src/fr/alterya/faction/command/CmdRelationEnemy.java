package fr.alterya.faction.command;

import fr.alterya.faction.struct.Rel;

public class CmdRelationEnemy extends FRelationCommand
{
	public CmdRelationEnemy()
	{
		aliases.add("enemy");
		targetRelation = Rel.ENEMY;
	}
}
