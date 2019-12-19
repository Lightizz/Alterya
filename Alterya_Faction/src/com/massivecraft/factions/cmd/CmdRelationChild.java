package com.massivecraft.factions.cmd;

import com.massivecraft.factions.struct.Rel;

public class CmdRelationChild extends FRelationCommand
{
	public CmdRelationChild() {
		aliases.add("child");
		targetRelation = Rel.CHILD;
	}
}
