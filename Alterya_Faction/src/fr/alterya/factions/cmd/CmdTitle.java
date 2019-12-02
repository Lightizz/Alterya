package fr.alterya.factions.cmd;

import fr.alterya.factions.zcore.util.TextUtil;

import fr.alterya.factions.Conf;
import fr.alterya.factions.FPlayer;
import fr.alterya.factions.integration.SpoutFeatures;

public class CmdTitle extends FCommand
{
	public CmdTitle()
	{
		this.aliases.add("title");
		
		this.requiredArgs.add("player");
		this.optionalArgs.put("title", "");
		
		//this.permission = Permission.TITLE.node;
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
		if ( ! payForCommand(Conf.econCostTitle, "changer le titre d'un joueur ", " pour changer le titre d'un joueur")) return;

		you.setTitle(title);
		
		// Inform
		myFaction.msg("%s<i> a changé le titre : %s", fme.describeTo(myFaction, true), you.describeTo(myFaction, true));

		if (Conf.spoutFactionTitlesOverNames)
		{
			SpoutFeatures.updateTitle(me, null);
		}
	}
	
}
