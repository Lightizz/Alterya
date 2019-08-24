package fr.alterya.factions.cmd;

import fr.alterya.factions.P;

import fr.alterya.factions.FPlayer;
import fr.alterya.factions.Faction;
import fr.alterya.factions.struct.Permission;

public class CmdPowerBoost extends FCommand
{
	public CmdPowerBoost()
	{
		super();
		this.aliases.add("powerboost");
		
		this.requiredArgs.add("p|f|player|faction");
		this.requiredArgs.add("name");
		this.requiredArgs.add("#");
		
		//this.permission = Permission.POWERBOOST.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = false;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		String type = this.argAsString(0).toLowerCase();
		boolean doPlayer = true;
		if (type.equals("f") || type.equals("faction"))
		{
			doPlayer = false;
		}
		else if (!type.equals("p") && !type.equals("player"))
		{
			msg("<b>Vous devez sp�cifier \"p\" ou \"player\" pour cibler un joueur ou \"f\" ou \"faction\" pour cibler une faction.");
			msg("<b>ex. /f powerboost p SomePlayer 0.5  -or-  /f powerboost f SomeFaction -5");
			return;
		}
		
		Double targetPower = this.argAsDouble(2);
		if (targetPower == null)
		{
			msg("<b>Vous devez sp�cifier une valeur num�rique valide pour le montant du bonus / de la p�nalit�.");
			return;
		}

		String target;

		if (doPlayer)
		{
			FPlayer targetPlayer = this.argAsBestFPlayerMatch(1);
			if (targetPlayer == null) return;
			targetPlayer.setPowerBoost(targetPower);
			target = "Player \""+targetPlayer.getName()+"\"";
		}
		else
		{
			Faction targetFaction = this.argAsFaction(1);
			if (targetFaction == null) return;
			targetFaction.setPowerBoost(targetPower);
			target = "Faction \""+targetFaction.getTag()+"\"";
		}

		msg("<i>"+target+" a maintenant un bonus de puissance / p�nalit� de "+targetPower+" aux niveaux de puissance min et max.");
		if (!senderIsConsole)
			P.p.log(fme.getName()+" a d�fini le bonus / p�nalit� de puissance pour "+target+" pour "+targetPower+".");
	}
}
