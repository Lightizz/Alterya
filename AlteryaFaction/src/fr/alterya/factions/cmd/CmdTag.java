package fr.alterya.factions.cmd;

import java.util.ArrayList;

import org.bukkit.Bukkit;

import fr.alterya.factions.Conf;
import fr.alterya.factions.Faction;
import fr.alterya.factions.Factions;
import fr.alterya.factions.event.FactionRenameEvent;
import fr.alterya.factions.integration.SpoutFeatures;
import fr.alterya.factions.util.MiscUtil;

public class CmdTag extends FCommand
{
	
	public CmdTag()
	{
		this.aliases.add("tag");
		this.aliases.add("name");
		
		this.requiredArgs.add("new tag");
		//this.optionalArgs.put("", "");
		
		//this.permission = Permission.TAG.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = true;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		String tag = this.argAsString(0);
		
		// TODO does not first test cover selfcase?
		if (Factions.i.isTagTaken(tag) && ! MiscUtil.getComparisonString(tag).equals(myFaction.getComparisonTag()))
		{
			msg("<b>Ce nom est déjà pris.");
			return;
		}
		
		if(myFaction == null) {
			msg("<b>Une erreur est survenue : 5F. Veuillez contacter un Administrateur en donnant l'ID de l'erreur.");
			return;
		}
		
		ArrayList<String> errors = new ArrayList<String>();
		errors.addAll(Factions.validateTag(tag));
		if (errors.size() > 0)
		{
			sendMessage(errors);
			return;
		}

		// if economy is enabled, they're not on the bypass list, and this command has a cost set, make sure they can pay
		if ( ! canAffordCommand(Conf.econCostTag, "changer le nom de la faction")) return;

		// trigger the faction rename event (cancellable)
		FactionRenameEvent renameEvent = new FactionRenameEvent(fme, tag);
		Bukkit.getServer().getPluginManager().callEvent(renameEvent);
		if(renameEvent.isCancelled()) return;

		// then make 'em pay (if applicable)
		if ( ! payForCommand(Conf.econCostTag, "changer le tag de faction ", " pour changer le tag de faction")) return;

		String oldtag = myFaction.getTag();
		myFaction.setTag(tag);

		// Inform
		myFaction.msg("%s<i> avez changé votre nom de faction pour %s", fme.describeTo(myFaction, true), myFaction.getTag(myFaction));
		for (Faction faction : Factions.i.get())
		{
			if (faction == myFaction)
			{
				continue;
			}
			faction.msg("<i>La faction %s<i> a changé leur nom pour %s.", fme.getColorTo(faction)+oldtag, myFaction.getTag(faction));
		}

		if (Conf.spoutFactionTagsOverNames)
		{
			SpoutFeatures.updateTitle(myFaction, null);
		}
	}
	
}
