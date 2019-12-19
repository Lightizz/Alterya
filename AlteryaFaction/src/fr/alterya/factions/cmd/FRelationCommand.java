package fr.alterya.factions.cmd;

import org.bukkit.Bukkit;

import fr.alterya.factions.Conf;
import fr.alterya.factions.Faction;
import fr.alterya.factions.event.FactionRelationEvent;
import fr.alterya.factions.integration.SpoutFeatures;
import fr.alterya.factions.struct.FFlag;
import fr.alterya.factions.struct.Rel;

public abstract class FRelationCommand extends FCommand
{
	public Rel targetRelation;
	
	public FRelationCommand()
	{
		super();
		this.requiredArgs.add("faction");
		//this.optionalArgs.put("", "");
		
		//this.permission = Permission.RELATION.node;
		this.disableOnLock = true;
		
		senderMustBePlayer = true;
		senderMustBeMember = false;
		senderMustBeOfficer = true;
		senderMustBeLeader = false;
	}
	
	Faction them = this.argAsFaction(0);
	
	@Override
	public void perform()
	{
		if (them == null) return;
		
		/*if ( ! them.isNormal())
		{
			msg("<b>Nope! You can't.");
			return;
		}*/
		
		if (them == myFaction)
		{
			msg("<b>Vous ne pouvez pas déclarer une relation à vous-même.");
			return;
		}

		if (myFaction.getRelationWish(them) == targetRelation)
		{
			msg("<b>Vous avez déjà cette relation souhaitée avec %s.", them.getTag());
			return;
		}

		// if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
		if ( ! payForCommand(targetRelation.getRelationCost(), "changer un souhait relationnel ", " pour changer un souhait relationnel")) return;

		// try to set the new relation
		Rel oldRelation = myFaction.getRelationTo(them, true);
		myFaction.setRelationWish(them, targetRelation);
		Rel currentRelation = myFaction.getRelationTo(them, true);

		// if the relation change was successful
		if (targetRelation == currentRelation)
		{
			// trigger the faction relation event
			FactionRelationEvent relationEvent = new FactionRelationEvent(myFaction, them, oldRelation, currentRelation);
			Bukkit.getServer().getPluginManager().callEvent(relationEvent);

			them.msg("%s<i> est maintenant %s.", myFaction.describeTo(them, true), targetRelation.getDescFactionOne());
			myFaction.msg("%s<i> est maintenant %s.", them.describeTo(myFaction, true), targetRelation.getDescFactionOne());
		}
		// inform the other faction of your request
		else
		{
			them.msg("%s<i> souhaite être %s.", myFaction.describeTo(them, true), targetRelation.getColor()+targetRelation.getDescFactionOne());
			them.msg("<i>Tapez <c>/"+Conf.baseCommandAliases.get(0)+" "+targetRelation+" "+myFaction.getTag()+"<i> pour accepter.");
			myFaction.msg("%s<i> ont été informés que vous souhaitez être %s<i>.", them.describeTo(myFaction, true), targetRelation.getColor()+targetRelation.getDescFactionOne());
		}
		
		// TODO: The ally case should work!!
		//   * this might have to be bumped up to make that happen, & allow ALLY,NEUTRAL only
		if ( targetRelation != Rel.TRUCE && them.getFlag(FFlag.PEACEFUL))
		{
			them.msg("<i>Cela n'aura aucun effet tant que votre faction sera pacifique.");
			myFaction.msg("<i>\n" + 
					"Cela n'aura aucun effet tant que leur faction sera pacifique.");
		}
		
		if ( targetRelation != Rel.TRUCE && myFaction.getFlag(FFlag.PEACEFUL))
		{
			them.msg("<i>Cela n'aura aucun effet tant que leur faction sera pacifique.");
			myFaction.msg("<i>Cela n'aura aucun effet tant que votre faction sera pacifique.");
		}

		SpoutFeatures.updateTitle(myFaction, them);
		SpoutFeatures.updateTitle(them, myFaction);
		SpoutFeatures.updateTerritoryDisplayLoc(null);
	}
}
