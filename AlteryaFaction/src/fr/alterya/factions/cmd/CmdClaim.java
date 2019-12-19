package fr.alterya.factions.cmd;

import fr.alterya.factions.Conf;
import fr.alterya.factions.FLocation;
import fr.alterya.factions.Faction;
import fr.alterya.factions.util.SpiralTask;

public class CmdClaim extends FCommand
{
	
	public CmdClaim()
	{
		super();
		this.aliases.add("claim");
		
		//this.requiredArgs.add("");
		this.optionalArgs.put("faction", "votre");
		this.optionalArgs.put("radius", "1");
		
		//this.permission = Permission.CLAIM.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		if(this.fme.getFaction() != null) {
			// Read and validate input
			final Faction forFaction = this.argAsFaction(0, myFaction);
			int radius = this.argAsInt(1, 1);

			if (radius < 1)
			{
				msg("<b>Si vous spécifiez un rayon, il doit être au moins égal à 1.");
				return;
			}

			if (radius < 2)
			{
				// single chunk
				fme.attemptClaim(forFaction, me.getLocation(), true);
			}
			else
			{
				/*
				// radius claim
				if (! Permission.CLAIM_RADIUS.has(sender, false))
				{
					msg("<b>You do not have permission to claim in a radius.");
					return;
				}*/

				new SpiralTask(new FLocation(me), radius)
				{
					private int failCount = 0;
					private final int limit = Conf.radiusClaimFailureLimit - 1;

					@Override
					public boolean work()
					{
						boolean success = fme.attemptClaim(forFaction, this.currentLocation(), true);
						if (success)
							failCount = 0;
						else if ( ! success && failCount++ >= limit)
						{
							this.stop();
							return false;
						}

						return true;
					}
				};
			}
		}
	}
}
