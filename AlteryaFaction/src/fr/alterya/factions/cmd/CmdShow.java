package fr.alterya.factions.cmd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import fr.alterya.factions.integration.Econ;
import fr.alterya.factions.zcore.util.TextUtil;

import fr.alterya.factions.Conf;
import fr.alterya.factions.FPlayer;
import fr.alterya.factions.Faction;
import fr.alterya.factions.struct.FFlag;
import fr.alterya.factions.struct.Rel;

public class CmdShow extends FCommand
{
	public CmdShow()
	{
		this.aliases.add("f");
		this.aliases.add("show");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("faction", "your");
		
		//this.permission = Permission.SHOW.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}

	@Override
	public void perform()
	{
		Faction faction = myFaction;
		if (this.argIsSet(0))
		{
			faction = this.argAsFaction(0);
			if (faction == null) return;
		}

		// if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
		if ( ! payForCommand(Conf.econCostShow, "afficher les informations de faction ", " pour afficher les informations de faction")) return;

		Collection<FPlayer> admins = faction.getFPlayersWhereRole(Rel.LEADER);
		Collection<FPlayer> mods = faction.getFPlayersWhereRole(Rel.OFFICER);
		Collection<FPlayer> normals = faction.getFPlayersWhereRole(Rel.MEMBER);
		Collection<FPlayer> recruits = faction.getFPlayersWhereRole(Rel.RECRUIT);
		
		msg(p.txt.titleize(faction.getTag(fme)));
		msg("<a>Description: <i>%s", faction.getDescription());
		
		// Display important flags
		// TODO: Find the non default flags, and display them instead.
		if (faction.getFlag(FFlag.PERMANENT))
		{
			msg("<a>Cette faction est permanente - restant même sans aucun membre.");
		}
		
		if (faction.getFlag(FFlag.PEACEFUL))
		{
			msg("<a>Cette faction est pacifique - en trêve avec tout le monde.");
		}
		
		msg("<a>Joining: <i>"+(faction.getOpen() ? "aucune invitation n'est requise " : " une invitation est requise"));

		double powerBoost = faction.getPowerBoost();
		String boost = (powerBoost == 0.0) ? "" : (powerBoost > 0.0 ? " (bonus: " : " (penalité: ") + powerBoost + ")";
		msg("<a>Claims / Power / Maxpower: <i> %d/%d/%d %s", faction.getLandRounded(), faction.getPowerRounded(), faction.getPowerMaxRounded(), boost);

		// show the land value
		if (Econ.shouldBeUsed())
		{
			double value = Econ.calculateTotalLandValue(faction.getLandRounded());
			double refund = value * Conf.econClaimRefundMultiplier;
			if (value > 0)
			{
				String stringValue = Econ.moneyString(value);
				String stringRefund = (refund > 0.0) ? (" ("+Econ.moneyString(refund)+" depreciated)") : "";
				msg("<a>Total land value: <i>" + stringValue + stringRefund);
			}
			
			//Show bank contents
			if(Conf.bankEnabled)
			{
				msg("<a>La bank contient: <i>"+Econ.moneyString(Econ.getBalance(faction.getAccountId())));
			}
		}

		String sepparator = p.txt.parse("<i>")+", ";
		
		// List the relations to other factions
		Map<Rel, List<String>> relationTags = faction.getFactionTagsPerRelation(fme, true);
		
		if (faction.getFlag(FFlag.PEACEFUL))
		{
			sendMessage(p.txt.parse("<a>En pacifique avec :<i> *everyone*"));
		}
		else
		{
			sendMessage(p.txt.parse("<a>En trêve avec : ") + TextUtil.implode(relationTags.get(Rel.TRUCE), sepparator));
		}
		
		sendMessage(p.txt.parse("<a>Allié à: ") + TextUtil.implode(relationTags.get(Rel.ALLY), sepparator));
		sendMessage(p.txt.parse("<a>Enemies: ") + TextUtil.implode(relationTags.get(Rel.ENEMY), sepparator));
		
		// List the members...
		List<String> memberOnlineNames = new ArrayList<String>();
		List<String> memberOfflineNames = new ArrayList<String>();
		
		for (FPlayer follower : admins)
		{
			if (follower.isOnlineAndVisibleTo(me))
			{
				memberOnlineNames.add(follower.getNameAndTitle(fme));
			}
			else
			{
				memberOfflineNames.add(follower.getNameAndTitle(fme));
			}
		}
		
		for (FPlayer follower : mods)
		{
			if (follower.isOnlineAndVisibleTo(me))
			{
				memberOnlineNames.add(follower.getNameAndTitle(fme));
			}
			else
			{
				memberOfflineNames.add(follower.getNameAndTitle(fme));
			}
		}
		
		for (FPlayer follower : normals)
		{
			if (follower.isOnlineAndVisibleTo(me))
			{
				memberOnlineNames.add(follower.getNameAndTitle(fme));
			}
			else
			{
				memberOfflineNames.add(follower.getNameAndTitle(fme));
			}
		}
		
		for (FPlayer follower : recruits)
		{
			if (follower.isOnline())
			{
				memberOnlineNames.add(follower.getNameAndTitle(fme));
			}
			else
			{
				memberOfflineNames.add(follower.getNameAndTitle(fme));
			}
		}
		sendMessage(p.txt.parse("<a>Membres en ligne : ") + TextUtil.implode(memberOnlineNames, sepparator));
		sendMessage(p.txt.parse("<a>Membres hors ligne : ") + TextUtil.implode(memberOfflineNames, sepparator));
	}
	
}
