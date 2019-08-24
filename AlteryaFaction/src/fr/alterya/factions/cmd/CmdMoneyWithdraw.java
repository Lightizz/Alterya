package fr.alterya.factions.cmd;

import fr.alterya.factions.P;
import fr.alterya.factions.integration.Econ;

import fr.alterya.factions.Conf;
import fr.alterya.factions.iface.EconomyParticipator;

import org.bukkit.ChatColor;


public class CmdMoneyWithdraw extends FCommand
{
	public CmdMoneyWithdraw()
	{
		this.aliases.add("w");
		this.aliases.add("withdraw");
		
		this.requiredArgs.add("amount");
		this.optionalArgs.put("faction", "your");
		
		//this.permission = Permission.MONEY_WITHDRAW.node;
		this.setHelpShort("withdraw money");
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		double amount = this.argAsDouble(0, 0d);
		EconomyParticipator faction = this.argAsFaction(1, myFaction);
		if (faction == null) {
			msg("<b>Une erreur est survenue : F. Veuillez contacter un Administrateur en donnant l'ID de l'erreur.");
			return;
		}
		boolean success = Econ.transferMoney(fme, faction, fme, amount);

		if (success && Conf.logMoneyTransactions)
			P.p.log(ChatColor.stripColor(P.p.txt.parse("%s a retiré %s de la bank de faction : %s", fme.getName(), Econ.moneyString(amount), faction.describeTo(null))));
	}
}
