package fr.alterya.factions.cmd;

import fr.alterya.factions.struct.Rel;

public class CmdRelationEnemy extends FRelationCommand
{
	public CmdRelationEnemy()
	{
		aliases.add("enemy");
		targetRelation = Rel.ENEMY;
	}
}
