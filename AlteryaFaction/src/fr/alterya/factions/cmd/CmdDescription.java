package fr.alterya.factions.cmd;

import fr.alterya.factions.zcore.util.TextUtil;

import fr.alterya.factions.Conf;
import fr.alterya.factions.FPlayer;
import fr.alterya.factions.FPlayers;

public class CmdDescription extends FCommand
{
	public CmdDescription()
	{
		super();
		this.aliases.add("desc");
		
		this.requiredArgs.add("desc");
		this.errorOnToManyArgs = false;
		//this.optionalArgs
		
		//this.permission = Permission.DESCRIPTION.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = true;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		if(this.myFaction == null) return;
		// if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
		if ( ! payForCommand(Conf.econCostDesc, "changer de description de faction ", " pour changer de description de faction")) return;

		myFaction.setDescription(TextUtil.implode(args, " ").replaceAll("(&([a-f0-9]))", "& $2"));  // since "&" color tags seem to work even through plain old FPlayer.sendMessage() for some reason, we need to break those up

		if ( ! Conf.broadcastDescriptionChanges)
		{
			fme.msg("Vous avez changez votre description pour <h>%s<i> to:", myFaction.describeTo(fme));
			fme.sendMessage(myFaction.getDescription());
			return;
		}

		// Broadcast the description to everyone
		for (FPlayer fplayer : FPlayers.i.getOnline())
		{
			fplayer.msg("<h>%s<i> a changé leur description pour :", myFaction.describeTo(fplayer));
			fplayer.sendMessage(myFaction.getDescription());  // players can inject "&" or "`" or "<i>" or whatever in their description, thus exploitable (masquerade as server messages or whatever); by the way, &k is particularly interesting looking
		}
	}
	
}
