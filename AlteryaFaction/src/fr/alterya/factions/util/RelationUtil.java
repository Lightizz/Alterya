package fr.alterya.factions.util;

import org.bukkit.ChatColor;

import fr.alterya.factions.zcore.util.TextUtil;

import fr.alterya.factions.Conf;
import fr.alterya.factions.FPlayer;
import fr.alterya.factions.Faction;
import fr.alterya.factions.iface.RelationParticipator;
import fr.alterya.factions.struct.FFlag;
import fr.alterya.factions.struct.Rel;

public class RelationUtil
{
	public static String describeThatToMe(RelationParticipator that, RelationParticipator me, boolean ucfirst)
	{
		String ret = "";

		if (that == null)
		{
			return "A server admin";
		}
		
		Faction thatFaction = getFaction(that);
		if (thatFaction == null) return "ERROR"; // ERROR

		Faction myFaction = getFaction(me);
//		if (myFaction == null) return thatFaction.getTag(); // no relation, but can show basic name or tag

		if (that instanceof Faction)
		{
			if (me instanceof FPlayer && myFaction == thatFaction)
			{
				ret = "your faction";
			}
			else
			{
				ret = thatFaction.getTag();
			}
		}
		else if (that instanceof FPlayer)
		{
			FPlayer fplayerthat = (FPlayer) that;
			if (that == me)
			{
				ret = "you";
			}
			else if (thatFaction == myFaction)
			{
				ret = fplayerthat.getNameAndTitle();
			}
			else
			{
				ret = fplayerthat.getNameAndTag();
			}
		}

		if (ucfirst)
		{
			ret = TextUtil.upperCaseFirst(ret);
		}

		return "" + getColorOfThatToMe(that, me) + ret;
	}

	public static String describeThatToMe(RelationParticipator that, RelationParticipator me)
	{
		return describeThatToMe(that, me, false);
	}

	public static Rel getRelationOfThatToMe(RelationParticipator that, RelationParticipator me)
	{
		return getRelationOfThatToMe(that, me, false);
	}

	public static Rel getRelationOfThatToMe(RelationParticipator that, RelationParticipator me, boolean ignorePeaceful)
	{
		Rel ret = null;
		
		Faction myFaction = getFaction(me);
		if (myFaction == null) return Rel.NEUTRAL; // ERROR

		Faction thatFaction = getFaction(that);
		if (thatFaction == null) return Rel.NEUTRAL; // ERROR
		
		// The faction with the lowest wish "wins"
		if (thatFaction.getRelationWish(myFaction).isLessThan(myFaction.getRelationWish(thatFaction)))
		{
			ret = thatFaction.getRelationWish(myFaction);
		}
		else
		{
			ret = myFaction.getRelationWish(thatFaction);
		}

		if (myFaction.equals(thatFaction))
		{
			ret = Rel.MEMBER;
			// Do officer and leader check
			//P.p.log("getRelationOfThatToMe the factions are the same for "+that.getClass().getSimpleName()+" and observer "+me.getClass().getSimpleName());
			if (that instanceof FPlayer)
			{
				ret = ((FPlayer)that).getRole();
				//P.p.log("getRelationOfThatToMe it was a player and role is "+ret);
			}
		}
		else if (!ignorePeaceful && (thatFaction.getFlag(FFlag.PEACEFUL) || myFaction.getFlag(FFlag.PEACEFUL)))
		{
			ret = Rel.TRUCE;
		}

		return ret;
	}

	public static Faction getFaction(RelationParticipator rp)
	{
		if (rp instanceof Faction)
		{
			return (Faction) rp;
		}

		if (rp instanceof FPlayer)
		{
			return ((FPlayer) rp).getFaction();
		}

		// ERROR
		return null;
	}

	public static ChatColor getColorOfThatToMe(RelationParticipator that, RelationParticipator me)
	{
		Faction thatFaction = getFaction(that);
		if (thatFaction != null && thatFaction != getFaction(me))
		{
			if (thatFaction.getFlag(FFlag.FRIENDLYFIRE) == true)
			{
				return Conf.colorFriendlyFire;
			}
			
			if (thatFaction.getFlag(FFlag.PVP) == false)
			{
				return Conf.colorNoPVP;
			}
		}
		return getRelationOfThatToMe(that, me).getColor();
	}
}
