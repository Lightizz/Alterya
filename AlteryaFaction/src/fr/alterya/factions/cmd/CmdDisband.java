package fr.alterya.factions.cmd;

import org.bukkit.Bukkit;

import fr.alterya.factions.integration.Econ;
import fr.alterya.factions.P;

import fr.alterya.factions.Conf;
import fr.alterya.factions.FPlayer;
import fr.alterya.factions.FPlayers;
import fr.alterya.factions.Faction;
import fr.alterya.factions.event.FPlayerLeaveEvent;
import fr.alterya.factions.event.FactionDisbandEvent;
import fr.alterya.factions.integration.SpoutFeatures;
import fr.alterya.factions.struct.FFlag;
import fr.alterya.factions.struct.FPerm;

public class CmdDisband extends FCommand
{
	public CmdDisband()
	{
		super();
		this.aliases.add("disband");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("faction", "votre");
		
		//this.permission = Permission.DISBAND.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void perform()
	{
		// The faction, default to your own.. but null if console sender.
		Faction faction = this.argAsFaction(0, fme == null ? null : myFaction);
		if (faction == null) return;
		
		if ( ! FPerm.DISBAND.has(sender, faction, true)) return;

		if (faction.getFlag(FFlag.PERMANENT))
		{
			msg("<i>Cette faction est permannente, donc vous ne pouvez pas la disband.");
			return;
		}

		FactionDisbandEvent disbandEvent = new FactionDisbandEvent(me, faction.getId());
		Bukkit.getServer().getPluginManager().callEvent(disbandEvent);
		if(disbandEvent.isCancelled()) return;

		// Send FPlayerLeaveEvent for each player in the faction
		for ( FPlayer fplayer : faction.getFPlayers() )
		{
			Bukkit.getServer().getPluginManager().callEvent(new FPlayerLeaveEvent(fplayer, faction, FPlayerLeaveEvent.PlayerLeaveReason.DISBAND));
		}

		// Inform all players
		for (FPlayer fplayer : FPlayers.i.getOnline())
		{
			String who = senderIsConsole ? "Un administrateur" : fme.describeTo(fplayer);
			if (fplayer.getFaction() == faction)
			{
				fplayer.msg("<h>%s<i> avez dissous votre faction.", who);
			}
			else
			{
				fplayer.msg("<h>%s<i> a dissous la faction %s.", who, faction.getTag(fplayer));
			}
		}
		if (Conf.logFactionDisband)
			P.p.log("La faction "+faction.getTag()+" ("+faction.getId()+") a été dissoute par "+(senderIsConsole ? "commande de la console" : fme.getName())+".");

		if (Econ.shouldBeUsed() && ! senderIsConsole)
		{
			//Give all the faction's money to the disbander
			double amount = Econ.getBalance(faction.getAccountId());
			Econ.transferMoney(fme, faction, fme, amount, false);
			
			if (amount > 0.0)
			{
				String amountString = Econ.moneyString(amount);
				msg("<i>On vous a donné la banque de la faction dissoute, pour un total de %s.", amountString);
				P.p.log(fme.getName() + " a été donné les contenus bancaires de "+amountString+" en dissoudant "+faction.getTag()+".");
			}
		}		
		
		faction.hasRight = false;
		faction.detach();

		SpoutFeatures.updateTitle(null, null);
		SpoutFeatures.updateCape(null, null);
	}
}
