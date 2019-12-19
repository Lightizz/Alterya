package fr.alterya.factions.cmd;

import fr.alterya.factions.zcore.util.TextUtil;

import fr.alterya.factions.Board;
import fr.alterya.factions.FLocation;
import fr.alterya.factions.FPlayer;
import fr.alterya.factions.Faction;
import fr.alterya.factions.integration.SpoutFeatures;
import fr.alterya.factions.struct.FPerm;
import fr.alterya.factions.struct.Permission;
import fr.alterya.factions.struct.TerritoryAccess;


public class CmdAccess extends FCommand
{
	public CmdAccess()
	{
		super();
		this.aliases.add("access");
		
		this.optionalArgs.put("view|p|f|player|faction", "view");
		this.optionalArgs.put("nom", "vous");
		
		this.setHelpShort("afficher ou accorder l'acc�s au territoire revendiqu� dans lequel vous vous trouvez");

		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		String type = this.argAsString(0);
		type = (type == null) ? "" : type.toLowerCase();
		FLocation loc = new FLocation(me.getLocation());

		TerritoryAccess territory = Board.getTerritoryAccessAt(loc);
		Faction locFaction = territory.getHostFaction();
		boolean accessAny = Permission.ACCESS_ANY.has(sender, false);

		if (type.isEmpty() || type.equals("view"))
		{
			if ( ! accessAny && ! Permission.ACCESS_VIEW.has(sender, true)) return;
			if ( ! accessAny && ! territory.doesHostFactionMatch(fme))
			{
				msg("<b>Ce territoire n'est pas contr�l� par votre faction, vous ne pouvez donc pas voir la liste d'acc�s.");
				return;
			}
			showAccessList(territory, locFaction);
			return;
		}

		if ( ! accessAny && ! Permission.ACCESS.has(sender, true)) return;
		if ( ! accessAny && ! FPerm.ACCESS.has(fme, locFaction, true)) return;

		boolean doPlayer = true;
		if (type.equals("f") || type.equals("faction"))
		{
			doPlayer = false;
		}
		else if (!type.equals("p") && !type.equals("player"))
		{
			msg("<b>Vous devez sp�cifier \"p \" ou \"joueur \" pour indiquer un joueur ou \"f \" ou \"faction \" pour indiquer une faction.");
			msg("<b>ex. /f access p SomePlayer  -or-  /f access f SomeFaction");
			msg("<b>Alternativement, vous pouvez utiliser la commande sans rien (ou \"view \") pour afficher simplement la liste d'acc�s.");
			return;
		}

		String target = "";
		boolean added;

		if (doPlayer)
		{
			FPlayer targetPlayer = this.argAsBestFPlayerMatch(1, fme);
			if (targetPlayer == null) return;
			added = territory.toggleFPlayer(targetPlayer);
			target = "Player \""+targetPlayer.getName()+"\"";
		}
		else
		{
			Faction targetFaction = this.argAsFaction(1, myFaction);
			if (targetFaction == null) return;
			added = territory.toggleFaction(targetFaction);
			target = "Faction \""+targetFaction.getTag()+"\"";
		}

		msg("<i>%s a �t� %s <i> la liste d�acc�s pour ce territoire.", target, TextUtil.parseColor(added ? "<lime>ajout� �" : "<rose>retir� de"));
		SpoutFeatures.updateAccessInfoLoc(loc);
		showAccessList(territory, locFaction);
	}

	private void showAccessList(TerritoryAccess territory, Faction locFaction)
	{
		msg("<i>Faction h�te %s a %s<i> sur ce territoire.", locFaction.getTag(), TextUtil.parseColor(territory.isHostFactionAllowed() ? "<lime>acc�s normal" : "<rose>acc�s priv�"));

		String players = territory.fplayerList();
		String factions = territory.factionList();

		if (factions.isEmpty())
			msg("L'acc�s n'a �t� explicitement accord� � aucune faction.");
		else
			msg("Factions avec acc�s explicite : " + factions);

		if (players.isEmpty())
			msg("Aucun joueur n'a �t� explicitement autoris� � acc�der.");
		else
			msg("Joueurs avec acc�s explicite : " + players);
	}
}
