package fr.alterya.factions.cmd;

import java.net.URL;

import fr.alterya.factions.integration.SpoutFeatures;
import fr.alterya.factions.struct.Permission;
import fr.alterya.factions.util.RelationUtil;

public class CmdCapeSet extends CapeCommand
{
	
	public CmdCapeSet()
	{
		this.aliases.add("set");
		this.requiredArgs.add("url");
		this.permission = Permission.CAPE_SET.node;
	}
	
	@Override
	public void perform()
	{
		String newCape = this.argAsString(0);
		
		if (isUrlValid(newCape))
		{
			capeFaction.setCape(newCape);
			SpoutFeatures.updateCape(capeFaction, null);
			msg("<h>%s <i>mettre le cap de <h>%s<i> à \"<h>%s<i>\".", RelationUtil.describeThatToMe(fme, fme, true), capeFaction.describeTo(fme), newCape);
			capeFaction.msg("<h>%s <i>mettre le cap de <h>%s<i> à \"<h>%s<i>\".", RelationUtil.describeThatToMe(fme, capeFaction, true), capeFaction.describeTo(capeFaction), newCape);
		}
		else
		{
			msg("<i>\"<h>%s<i>\" n'est pas un URL valide.", newCape);
		}
	}
	
	public static boolean isUrlValid(String urlString)
	{
		try
		{
			new URL(urlString);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
