package fr.alterya.factions.cmd;

import fr.alterya.factions.struct.Rel;

public class CmdRelationChildren extends FRelationCommand
{
	public CmdRelationChildren()
	{
		myFaction.setRelationWish(them, Rel.CHILDREN);
		aliases.add("children");
		targetRelation = Rel.CHILDREN;
	}
}
