package fr.alterya.factions.cmd;

import fr.alterya.factions.P;
import fr.alterya.factions.integration.Econ;

import fr.alterya.factions.Conf;
import fr.alterya.factions.iface.EconomyParticipator;

import org.bukkit.ChatColor;


public class CmdMoneyTransferFf extends FCommand
{
	public CmdMoneyTransferFf()
	{
		this.aliases.add("ff");
		
		this.requiredArgs.add("amount");
		this.requiredArgs.add("faction");
		this.requiredArgs.add("faction");
		
		//this.optionalArgs.put("", "");
		
		//this.permission = Permission.MONEY_F2F.node;
		this.setHelpShort("transferer faction -> faction");
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		double amount = this.argAsDouble(0, 0d);
		EconomyParticipator from = this.argAsFaction(1);
		if (from == null) return;
		EconomyParticipator to = this.argAsFaction(2);
		if (to == null) return;
		
		boolean success = Econ.transferMoney(fme, from, to, amount);

		if (success && Conf.logMoneyTransactions)
			P.p.log(ChatColor.stripColor(P.p.txt.parse("%s a transféré %s de la faction \"%s\" à la faction \"%s\"", fme.getName(), Econ.moneyString(amount), from.describeTo(null), to.describeTo(null))));
	}
}
