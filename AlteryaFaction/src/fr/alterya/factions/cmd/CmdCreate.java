package fr.alterya.factions.cmd;

import java.util.ArrayList;

import org.bukkit.Bukkit;

import fr.alterya.factions.P;

import fr.alterya.factions.Conf;
import fr.alterya.factions.FPlayer;
import fr.alterya.factions.FPlayers;
import fr.alterya.factions.Faction;
import fr.alterya.factions.Factions;
import fr.alterya.factions.event.FPlayerJoinEvent;
import fr.alterya.factions.event.FactionCreateEvent;
import fr.alterya.factions.struct.Rel;

public class CmdCreate extends FCommand
{
	public CmdCreate()
	{
		super();
		this.aliases.add("create");
		
		this.requiredArgs.add("faction tag");
		//this.optionalArgs.put("", "");
		
		//this.permission = Permission.CREATE.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		String tag = this.argAsString(0);
		
		if (fme.hasFaction())
		{
			msg("<b>Vous devez d'abord quitter votre faction actuelle.");
			return;
		}
		
		if (Factions.i.isTagTaken(tag))
		{
			msg("<b>Ce nom est déjà utilisé.");
			return;
		}
		
		ArrayList<String> tagValidationErrors = Factions.validateTag(tag);
		if (tagValidationErrors.size() > 0)
		{
			sendMessage(tagValidationErrors);
			return;
		}

		// if economy is enabled, they're not on the bypass list, and this command has a cost set, make sure they can pay
		if ( ! canAffordCommand(Conf.econCostCreate, "créer une nouvelle faction")) return;

		// trigger the faction creation event (cancellable)
		FactionCreateEvent createEvent = new FactionCreateEvent(me, tag);
		Bukkit.getServer().getPluginManager().callEvent(createEvent);
		if(createEvent.isCancelled()) return;
		
		// then make 'em pay (if applicable)
		if ( ! payForCommand(Conf.econCostCreate, "créer une nouvelle faction ", " pour créer une nouvelle faction")) return;

		Faction faction = Factions.i.create();

		// TODO: Why would this even happen??? Auto increment clash??
		if (faction == null)
		{
			msg("<b>Une erreur est survenue : 0F. Veuillez contacter un Administrateur en donnant l'ID de l'erreur.");
			return;
		}

		// finish setting up the Faction
	faction.setTag(tag);

	// trigger the faction join event for the creator
	FPlayerJoinEvent joinEvent = new FPlayerJoinEvent(FPlayers.i.get(me),faction,FPlayerJoinEvent.PlayerJoinReason.CREATE);
	Bukkit.getServer().getPluginManager().callEvent(joinEvent);
	// join event cannot be cancelled or you'll have an empty faction

	// finish setting up the FPlayer
		fme.setRole(Rel.LEADER);
		fme.setFaction(faction);

		for (FPlayer follower : FPlayers.i.getOnline())
		{
			follower.msg("%s<i> avez créé une nouvelle faction %s", fme.describeTo(follower, true), faction.getTag(follower));
		}
		
		msg("<i>Tu devrais maintenant : %s", p.cmdBase.cmdDescription.getUseageTemplate());

		if (Conf.logFactionCreate)
			P.p.log(fme.getName() + " a créé une nouvelle faction : " + tag);
	}
	
}
