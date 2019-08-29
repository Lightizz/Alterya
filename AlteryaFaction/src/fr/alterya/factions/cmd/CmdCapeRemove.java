package fr.alterya.factions.cmd;

import fr.alterya.factions.integration.SpoutFeatures;
import fr.alterya.factions.struct.Permission;
import fr.alterya.factions.util.RelationUtil;

public class CmdCapeRemove extends CapeCommand
{
	
	public CmdCapeRemove()
	{
		this.aliases.add("rm");
		this.aliases.add("rem");
		this.aliases.add("remove");
		this.aliases.add("del");
		this.aliases.add("delete");
		this.permission = Permission.CAPE_REMOVE.node;
	}
	
	@Override
	public void perform()
	{
		if (currentCape == null)
		{
			msg("<h>%s <i>n'a pas de cape.", capeFaction.describeTo(fme, true));
		}
		else
		{
			capeFaction.setCape(null);
			SpoutFeatures.updateCape(capeFaction, null);
			msg("<h>%s <i> a enlevé la cape <h>%s<i>.", RelationUtil.describeThatToMe(fme, fme, true), capeFaction.describeTo(fme));
			capeFaction.msg("<h>%s <i>a enlevé la cape <h>%s<i>.", RelationUtil.describeThatToMe(fme, capeFaction, true), capeFaction.describeTo(capeFaction));
		}
	}
}
