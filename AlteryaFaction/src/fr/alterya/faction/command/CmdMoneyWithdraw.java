package fr.alterya.faction.command;

import fr.alterya.faction.Conf;
import fr.alterya.faction.iface.EconomyParticipator;
import fr.alterya.faction.Main;
import fr.alterya.faction.integration.Econ;
import fr.alterya.faction.struct.Permission;

import org.bukkit.ChatColor;


public class CmdMoneyWithdraw extends FCommand
{
	public CmdMoneyWithdraw()
	{
		this.aliases.add("w");
		this.aliases.add("withdraw");
		
		this.requiredArgs.add("amount");
		this.optionalArgs.put("faction", "your");
		
		this.permission = Permission.MONEY_WITHDRAW.node;
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
		if (faction == null) return;
		boolean success = Econ.transferMoney(fme, faction, fme, amount);

		if (success && Conf.logMoneyTransactions)
			Main.main.log(ChatColor.stripColor(Main.main.txt.parse("%s withdrew %s from the faction bank: %s", fme.getName(), Econ.moneyString(amount), faction.describeTo(null))));
	}
}
