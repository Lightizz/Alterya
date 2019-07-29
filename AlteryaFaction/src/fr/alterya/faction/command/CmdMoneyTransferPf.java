package fr.alterya.faction.command;

import fr.alterya.faction.Conf;
import fr.alterya.faction.iface.EconomyParticipator;
import fr.alterya.faction.Main;
import fr.alterya.faction.integration.Econ;
import fr.alterya.faction.struct.Permission;

import org.bukkit.ChatColor;


public class CmdMoneyTransferPf extends FCommand
{
	public CmdMoneyTransferPf()
	{
		this.aliases.add("pf");
		
		this.requiredArgs.add("amount");
		this.requiredArgs.add("player");
		this.requiredArgs.add("faction");
		
		//this.optionalArgs.put("", "");
		
		this.permission = Permission.MONEY_P2F.node;
		this.setHelpShort("transfer p -> f");
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		double amount = this.argAsDouble(0, 0d);
		EconomyParticipator from = this.argAsBestFPlayerMatch(1);
		if (from == null) return;
		EconomyParticipator to = this.argAsFaction(2);
		if (to == null) return;
		
		boolean success = Econ.transferMoney(fme, from, to, amount);

		if (success && Conf.logMoneyTransactions)
			Main.main.log(ChatColor.stripColor(Main.main.txt.parse("%s transferred %s from the player \"%s\" to the faction \"%s\"", fme.getName(), Econ.moneyString(amount), from.describeTo(null), to.describeTo(null))));
	}
}
