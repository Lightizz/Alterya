package fr.alterya.factions.cmd;

import fr.alterya.factions.P;

import fr.alterya.factions.struct.Permission;

public class CmdAdmin extends FCommand
{
	public CmdAdmin()
	{
		super();
		this.aliases.add("admin");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("on/off", "flip");
		
		this.permission = Permission.ADMIN.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		fme.setHasAdminMode(this.argAsBool(0, ! fme.hasAdminMode()));
		
		if ( fme.hasAdminMode())
		{
			fme.msg("<i>Vous avez activ� le mode de contournement d'administrateur.");
			P.p.log(fme.getName() + " a activ� le mode bypass admin.");
		}
		else
		{
			fme.msg("<i>Vous avez d�sactiv� le mode de contournement de l'administrateur.");
			P.p.log(fme.getName() + " Mode de d�rivation admin d�sactiv�.");
		}
	}
}
