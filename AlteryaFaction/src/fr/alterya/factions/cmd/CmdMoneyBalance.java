package fr.alterya.factions.cmd;

import fr.alterya.factions.integration.Econ;

import fr.alterya.factions.Faction;
import fr.alterya.factions.struct.Permission;

public class CmdMoneyBalance extends FCommand
{
	public CmdMoneyBalance()
	{
		super();
		this.aliases.add("b");
		this.aliases.add("balance");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("faction", "your");
		
		//this.permission = Permission.MONEY_BALANCE.node;
		this.setHelpShort("montrer le solde de la faction");
		
		senderMustBePlayer = false;
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
		}
			
		if (faction == null) {
			msg("<b>Une erreur est survenue : 3F. Veuillez contacter un Administrateur en donnant l'ID de l'erreur.");
			return;
		}
		if (faction != myFaction && ! Permission.MONEY_BALANCE_ANY.has(sender, true)) return;
		
		Econ.sendBalanceInfo(fme, faction);
	}
	
}
