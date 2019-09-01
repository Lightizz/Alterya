package fr.alterya.factions.cmd;

import org.bukkit.Bukkit;

import fr.alterya.factions.FPlayer;
import fr.alterya.factions.FPlayers;
import fr.alterya.factions.Faction;
import fr.alterya.factions.event.FPlayerJoinEvent;
import fr.alterya.factions.struct.Permission;
import fr.alterya.factions.struct.Rel;
import fr.alterya.factions.util.RelationUtil;

public class CmdLeader extends FCommand
{	
	public CmdLeader()
	{
		super();
		this.aliases.add("leader");
		
		this.requiredArgs.add("player");
		this.optionalArgs.put("faction", "votre");
		
		//this.permission = Permission.LEADER.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		FPlayer newLeader = this.argAsBestFPlayerMatch(0);
		if (newLeader == null) {
			msg("<b>Une erreur est survenue : 1F. Veuillez contacter un Administrateur en donnant l'ID de l'erreur.");
			return;
		}
		
		Faction targetFaction = this.argAsFaction(1, myFaction);
		if (targetFaction == null) {
			msg("<b>Une erreur est survenue : 2F. Veuillez contacter un Administrateur en donnant l'ID de l'erreur.");
			return;
		}
		
		FPlayer targetFactionCurrentLeader = targetFaction.getFPlayerLeader();
		
		// We now have fplayer and the target faction
		if (this.senderIsConsole || fme.hasAdminMode() || Permission.LEADER_ANY.has(sender, false))
		{
			// Do whatever you wish
		}
		else
		{
			// Follow the standard rules
			if (fme.getRole() != Rel.LEADER || targetFaction != myFaction)
			{
				sender.sendMessage(p.txt.parse("<b>Vous devez être un leader de faction pour %s.", this.getHelpShort()));
				return;
			}
			
			if (newLeader.getFaction() != myFaction)
			{
				msg("%s<i> n'est pas en membre de votre faction.", newLeader.describeTo(fme, true));
				return;
			}
			
			if (newLeader == fme)
			{
				msg("<b>La cible ne peut pas être vous même.");
				return;
			}
		}

		// only perform a FPlayerJoinEvent when newLeader isn't actually in the faction
		if (newLeader.getFaction() != targetFaction)
		{
			FPlayerJoinEvent event = new FPlayerJoinEvent(FPlayers.i.get(me),targetFaction,FPlayerJoinEvent.PlayerJoinReason.LEADER);
			Bukkit.getServer().getPluginManager().callEvent(event);
			if (event.isCancelled()) return;
		}

		// if target player is currently leader, demote and replace him
		if (targetFactionCurrentLeader == newLeader)
		{
			targetFaction.promoteNewLeader();
			msg("<i>Vous avez demote %s<i> de la position de leader.", newLeader.describeTo(fme, true));
			newLeader.msg("<i>Vous avez été demote du rôle de leader par %s<i>.", senderIsConsole ? "Un administrateur" : fme.describeTo(newLeader, true));
			return;
		}

		// Perform the switching
		if (targetFactionCurrentLeader != null)
		{
			targetFactionCurrentLeader.setRole(Rel.OFFICER);
		}
		newLeader.setFaction(targetFaction);
		newLeader.setRole(Rel.LEADER);
		msg("<i>Vous avez promote %s<i> au rôle de leader.", newLeader.describeTo(fme, true));
		
		// Inform all players
		for (FPlayer fplayer : FPlayers.i.getOnline())
		{
			fplayer.msg("%s<i> avez donné %s<i> le chef de %s<i>.", senderIsConsole ? "Un administrateur" : RelationUtil.describeThatToMe(fme, fplayer, true), newLeader.describeTo(fplayer), targetFaction.describeTo(fplayer));
		}
	}
}
