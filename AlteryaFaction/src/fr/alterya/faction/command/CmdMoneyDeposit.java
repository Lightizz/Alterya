package fr.alterya.faction.command;

import fr.alterya.faction.Conf;
import fr.alterya.faction.Main;
import fr.alterya.faction.iface.EconomyParticipator;
import fr.alterya.faction.integration.Econ;
import fr.alterya.faction.struct.Permission;

import org.bukkit.ChatColor;


public class CmdMoneyDeposit extends FCommand
{
	
	public CmdMoneyDeposit()
	{
		super();
		this.aliases.add("d");
		this.aliases.add("deposit");
		
		this.requiredArgs.add("amount");
		this.optionalArgs.put("faction", "your");
		
		this.permission = Permission.MONEY_DEPOSIT.node;
		this.setHelpShort("deposit money");
		
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
		boolean success = Econ.transferMoney(fme, fme, faction, amount);

		if (success && Conf.logMoneyTransactions)
			Main.main.log(ChatColor.stripColor(Main.main.txt.parse("%s deposited %s in the faction bank: %s", fme.getName(), Econ.moneyString(amount), faction.describeTo(null))));
	}
	
}
