package fr.alterya.faction.command;

import fr.alterya.faction.Conf;
import fr.alterya.faction.FPlayer;
import fr.alterya.faction.integration.SpoutFeatures;
import fr.alterya.faction.struct.Permission;
import fr.alterya.faction.zcore.util.TextUtil;

public class CmdTitle extends FCommand
{
	public CmdTitle()
	{
		this.aliases.add("title");
		
		this.requiredArgs.add("player");
		this.optionalArgs.put("title", "");
		
		this.permission = Permission.TITLE.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = true;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		FPlayer you = this.argAsBestFPlayerMatch(0);
		if (you == null) return;
		
		args.remove(0);
		String title = TextUtil.implode(args, " ");
		
		if ( ! canIAdministerYou(fme, you)) return;

		// if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
		if ( ! payForCommand(Conf.econCostTitle, "to change a players title", "for changing a players title")) return;

		you.setTitle(title);
		
		// Inform
		myFaction.msg("%s<i> changed a title: %s", fme.describeTo(myFaction, true), you.describeTo(myFaction, true));

		if (Conf.spoutFactionTitlesOverNames)
		{
			SpoutFeatures.updateTitle(me, null);
		}
	}
	
}
