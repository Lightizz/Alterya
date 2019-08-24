package fr.alterya.factions.cmd;

import fr.alterya.factions.P;
import fr.alterya.factions.integration.Econ;

import fr.alterya.factions.Conf;
import fr.alterya.factions.iface.EconomyParticipator;

import org.bukkit.ChatColor;


public class CmdMoneyTransferFp extends FCommand
{
	public CmdMoneyTransferFp()
	{
		this.aliases.add("fp");
		
		this.requiredArgs.add("amount");
		this.requiredArgs.add("faction");
		this.requiredArgs.add("player");
		
		//this.optionalArgs.put("", "");
		
		//this.permission = Permission.MONEY_F2P.node;
		this.setHelpShort("transfer faction -> player");
		
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
		EconomyParticipator to = this.argAsBestFPlayerMatch(2);
		if (to == null) return;
		
		boolean success = Econ.transferMoney(fme, from, to, amount);

		if (success && Conf.logMoneyTransactions)
			P.p.log(ChatColor.stripColor(P.p.txt.parse("%s a transferé %s de la faction \"%s\" au joueur \"%s\"", fme.getName(), Econ.moneyString(amount), from.describeTo(null), to.describeTo(null))));
	}
}
