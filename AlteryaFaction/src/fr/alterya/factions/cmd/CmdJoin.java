package fr.alterya.factions.cmd;

import org.bukkit.Bukkit;

import fr.alterya.factions.P;

import fr.alterya.factions.Conf;
import fr.alterya.factions.FPlayer;
import fr.alterya.factions.FPlayers;
import fr.alterya.factions.Faction;
import fr.alterya.factions.event.FPlayerJoinEvent;
import fr.alterya.factions.struct.Permission;

public class CmdJoin extends FCommand
{
	public CmdJoin()
	{
		super();
		this.aliases.add("join");
		
		this.requiredArgs.add("faction");
		this.optionalArgs.put("player", "Vous");
		
		//this.permission = Permission.JOIN.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		Faction faction = this.argAsFaction(0);
		if (faction == null) return;

		FPlayer fplayer = this.argAsBestFPlayerMatch(1, fme, false);
		boolean samePlayer = fplayer == fme;

		if (!samePlayer  && ! Permission.JOIN_OTHERS.has(sender, false))
		{
			msg("<b>Vous n'avez pas la permission de déplacer un joueur dans une autre faction.");
			return;
		}

		if (faction == fplayer.getFaction())
		{
			msg("<b>%s %s déjà un membre de %s", fplayer.describeTo(fme, true), (samePlayer ? "est" : "est"), faction.getTag(fme));
			return;
		}

		if (Conf.factionMemberLimit > 0 && faction.getFPlayers().size() >= Conf.factionMemberLimit)
		{
			msg("<b>!<white>La faction %s est à la limite du nombre de %d membres, donc %s ne peut actuellement pas adhérer.", faction.getTag(fme), Conf.factionMemberLimit, fplayer.describeTo(fme, false));
			return;
		}

		if (fplayer.hasFaction())
		{
			msg("<b>%s doit quitter %s faction actuelle avant.", fplayer.describeTo(fme, true), (samePlayer ? "Vous" : "leur"));
			return;
		}

		if (!Conf.canLeaveWithNegativePower && fplayer.getPower() < 0)
		{
			msg("<b>%s ne peut pas rejoindre de faction avec un power à 0 ou moins.", fplayer.describeTo(fme, true));
			return;
		}

		if( ! (faction.getOpen() || faction.isInvited(fplayer) || fme.hasAdminMode() || Permission.JOIN_ANY.has(sender, false)))
		{
			msg("<i>Cette faction à besoins d'une invitation.");
			if (samePlayer)
				faction.msg("%s<i> a essayé de rejoindre votre faction.", fplayer.describeTo(faction, true));
			return;
		}

		// if economy is enabled, they're not on the bypass list, and this command has a cost set, make sure they can pay
		if (samePlayer && ! canAffordCommand(Conf.econCostJoin, "rejoindre une faction")) return;

		// trigger the join event (cancellable)
		FPlayerJoinEvent joinEvent = new FPlayerJoinEvent(FPlayers.i.get(me),faction,FPlayerJoinEvent.PlayerJoinReason.COMMAND);
		Bukkit.getServer().getPluginManager().callEvent(joinEvent);
		if (joinEvent.isCancelled()) return;

		// then make 'em pay (if applicable)
		if (samePlayer && ! payForCommand(Conf.econCostJoin, "rejoindre une faction ", " pour rejoindre une faction")) return;

		if (!samePlayer)
			fplayer.msg("<i>%s vous a déplacé dans la faction %s.", fme.describeTo(fplayer, true), faction.getTag(fplayer));
		faction.msg("<i>%s a rejoin votre faction.", fplayer.describeTo(faction, true));
		fme.msg("<i>%s faction rejoin avec succès %s.", fplayer.describeTo(fme, true), faction.getTag(fme));
		
		fplayer.resetFactionData();
		fplayer.setFaction(faction);
		fplayer.setRole(Conf.factionRankDefault); // They have just joined a faction, start them out on the lowest rank (default config).
	    
		faction.deinvite(fplayer);
		

		if (Conf.logFactionJoin)
		{
			if (samePlayer)
				P.p.log("%s a rejoin la faction %s.", fplayer.getName(), faction.getTag());
			else
				P.p.log("%sdéplacé le joueur% s dans la faction %s.", fme.getName(), fplayer.getName(), faction.getTag());
		}
	}
}
