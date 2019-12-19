package fr.alterya.factions.cmd;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.alterya.factions.integration.Econ;
import fr.alterya.factions.P;

import fr.alterya.factions.Conf;
import fr.alterya.factions.FPlayer;
import fr.alterya.factions.FPlayers;
import fr.alterya.factions.Faction;
import fr.alterya.factions.Factions;
import fr.alterya.factions.struct.FFlag;
import fr.alterya.factions.struct.FPerm;
import fr.alterya.factions.struct.Rel;
import fr.alterya.factions.zcore.MCommand;


public abstract class FCommand extends MCommand<P>
{
	public boolean disableOnLock;
	
	public FPlayer fme;
	public Faction myFaction;
	public boolean senderMustBeMember;
	public boolean senderMustBeOfficer;
	public boolean senderMustBeLeader;
	
	public boolean isMoneyCommand;
	
	public FCommand()
	{
		super(P.p);
		
		// Due to safety reasons it defaults to disable on lock.
		disableOnLock = true;
		
		// The money commands must be disabled if money should not be used.
		isMoneyCommand = false;
		
		senderMustBeMember = false;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void execute(CommandSender sender, List<String> args, List<MCommand<?>> commandChain)
	{
		if (sender instanceof Player)
		{
			this.fme = FPlayers.i.get((Player)sender);
			this.myFaction = this.fme.getFaction();
		}
		else
		{
			this.fme = null;
			this.myFaction = null;
		}
		super.execute(sender, args, commandChain);
	}
	
	@Override
	public boolean isEnabled()
	{
		if (p.getLocked() && this.disableOnLock)
		{
			msg("<b>Factions a été verrouillé par un administrateur. Veuillez réessayer plus tard.");
			return false;
		}
		
		if (this.isMoneyCommand && ! Conf.econEnabled)
		{
			msg("<b>Les fonctionnalités d'économie de faction sont désactivées sur ce serveur.");
			return false;
		}
		
		if (this.isMoneyCommand && ! Conf.bankEnabled)
		{
			msg("<b> Le système de banque de faction est désactivé sur ce serveur.");
			return false;
		}
		
		return true;
	}
	
	@Override
	public boolean validSenderType(CommandSender sender, boolean informSenderIfNot)
	{
		boolean superValid = super.validSenderType(sender, informSenderIfNot);
		if ( ! superValid) return false;
		
		if ( ! (this.senderMustBeMember || this.senderMustBeOfficer || this.senderMustBeLeader)) return true;
		
		if ( ! (sender instanceof Player)) return false;
		
		FPlayer fplayer = FPlayers.i.get((Player)sender);
		
		if ( ! fplayer.hasFaction())
		{
			sender.sendMessage(p.txt.parse("<b> Vous n'êtes membre d'aucune faction."));
			return false;
		}
		
		if (this.senderMustBeOfficer && ! fplayer.getRole().isAtLeast(Rel.OFFICER))
		{
			sender.sendMessage(p.txt.parse("<b> Seuls les modérateurs de faction peuvent% s.", this.getHelpShort()));
			return false;
		}
		
		if (this.senderMustBeLeader && ! fplayer.getRole().isAtLeast(Rel.LEADER))
		{
			sender.sendMessage(p.txt.parse("<b>Seuls les administrateurs de faction peuvent %s.", this.getHelpShort()));
			return false;
		}
			
		return true;
	}
	
	// -------------------------------------------- //
	// Assertions
	// -------------------------------------------- //

	public boolean assertHasFaction()
	{
		if (me == null) return true;
		
		if ( ! fme.hasFaction())
		{
			sendMessage("Vous n'êtes membre d'aucune faction.");
			return false;
		}
		return true;
	}

	public boolean assertMinRole(Rel role)
	{
		if (me == null) return true;
		
		if (fme.getRole().isLessThan(role))
		{
			msg("<b>Vous <h>devez être "+role+"<b> pour "+this.getHelpShort()+".");
			return false;
		}
		return true;
	}
	
	// -------------------------------------------- //
	// Argument Readers
	// -------------------------------------------- //
	
	// FPLAYER ======================
	public FPlayer strAsFPlayer(String name, FPlayer def, boolean msg)
	{
		FPlayer ret = def;
		
		if (name != null)
		{
			FPlayer fplayer = FPlayers.i.get(name);
			if (fplayer != null)
			{
				ret = fplayer;
			}
		}
		
		if (msg && ret == null)
		{
			this.msg("<b>Aucun joueur \"<p>%s<b>\" pourrait être trouvé.", name);			
		}
		
		return ret;
	}
	public FPlayer argAsFPlayer(int idx, FPlayer def, boolean msg)
	{
		return this.strAsFPlayer(this.argAsString(idx), def, msg);
	}
	public FPlayer argAsFPlayer(int idx, FPlayer def)
	{
		return this.argAsFPlayer(idx, def, true);
	}
	public FPlayer argAsFPlayer(int idx)
	{
		return this.argAsFPlayer(idx, null);
	}
	
	// BEST FPLAYER MATCH ======================
	public FPlayer strAsBestFPlayerMatch(String name, FPlayer def, boolean msg)
	{
		FPlayer ret = def;
		
		if (name != null)
		{
			FPlayer fplayer = FPlayers.i.getBestIdMatch(name);
			if (fplayer != null)
			{
				ret = fplayer;
			}
		}
		
		if (msg && ret == null)
		{
			this.msg("<b>Aucun joueur trouvé pour \"<p>%s<b>\".", name);
		}
		
		return ret;
	}
	public FPlayer argAsBestFPlayerMatch(int idx, FPlayer def, boolean msg)
	{
		return this.strAsBestFPlayerMatch(this.argAsString(idx), def, msg);
	}
	public FPlayer argAsBestFPlayerMatch(int idx, FPlayer def)
	{
		return this.argAsBestFPlayerMatch(idx, def, true);
	}
	public FPlayer argAsBestFPlayerMatch(int idx)
	{
		return this.argAsBestFPlayerMatch(idx, null);
	}
	
	// FACTION ======================
	public Faction strAsFaction(String name, Faction def, boolean msg)
	{
		Faction ret = def;
		
		if (name != null)
		{
			Faction faction = null;
			
			// First we try an exact match
			if (faction == null)
			{
				faction = Factions.i.getByTag(name);
			}
			
			// Next we match faction tags
			if (faction == null)
			{
				faction = Factions.i.getBestTagMatch(name);
			}
				
			// Next we match player names
			if (faction == null)
			{
				FPlayer fplayer = FPlayers.i.getBestIdMatch(name);
				if (fplayer != null)
				{
					faction = fplayer.getFaction();
				}
			}
			
			if (faction != null)
			{
				ret = faction;
			}
		}
		
		if (msg && ret == null)
		{
			this.msg("<b> La faction ou le joueur \" <p>% s <b> \" est introuvable.", name);
		}
		
		return ret;
	}
	public Faction argAsFaction(int idx, Faction def, boolean msg)
	{
		return this.strAsFaction(this.argAsString(idx), def, msg);
	}
	public Faction argAsFaction(int idx, Faction def)
	{
		return this.argAsFaction(idx, def, true);
	}
	public Faction argAsFaction(int idx)
	{
		return this.argAsFaction(idx, null);
	}
	
	// FACTION FLAG ======================
	public FFlag strAsFactionFlag(String name, FFlag def, boolean msg)
	{
		FFlag ret = def;
		
		if (name != null)
		{
			FFlag flag = FFlag.parse(name);
			if (flag != null)
			{
				ret = flag;
			}
		}
		
		if (msg && ret == null)
		{
			this.msg("<b> Le drapeau de faction \"<p>%s<b>\" ne peut être trouvé.", name);
		}
		
		return ret;
	}
	public FFlag argAsFactionFlag(int idx, FFlag def, boolean msg)
	{
		return this.strAsFactionFlag(this.argAsString(idx), def, msg);
	}
	public FFlag argAsFactionFlag(int idx, FFlag def)
	{
		return this.argAsFactionFlag(idx, def, true);
	}
	public FFlag argAsFactionFlag(int idx)
	{
		return this.argAsFactionFlag(idx, null);
	}
	
	// FACTION PERM ======================
	public FPerm strAsFactionPerm(String name, FPerm def, boolean msg)
	{
		FPerm ret = def;
		
		if (name != null)
		{
			FPerm perm = FPerm.parse(name);
			if (perm != null)
			{
				ret = perm;
			}
		}
		
		if (msg && ret == null)
		{
			this.msg("<b>La permission de faction \"<p>%s<b>\" ne peut être trouvée.", name);
		}
		
		return ret;
	}
	public FPerm argAsFactionPerm(int idx, FPerm def, boolean msg)
	{
		return this.strAsFactionPerm(this.argAsString(idx), def, msg);
	}
	public FPerm argAsFactionPerm(int idx, FPerm def)
	{
		return this.argAsFactionPerm(idx, def, true);
	}
	public FPerm argAsFactionPerm(int idx)
	{
		return this.argAsFactionPerm(idx, null);
	}
	
	// FACTION REL ======================
	public Rel strAsRel(String name, Rel def, boolean msg)
	{
		Rel ret = def;
		
		if (name != null)
		{
			Rel perm = Rel.parse(name);
			if (perm != null)
			{
				ret = perm;
			}
		}
		
		if (msg && ret == null)
		{
			this.msg("<b>Le rôle \"<p>%s<b>\" ne peut être trouvé.", name);
		}
		
		return ret;
	}
	public Rel argAsRel(int idx, Rel def, boolean msg)
	{
		return this.strAsRel(this.argAsString(idx), def, msg);
	}
	public Rel argAsRel(int idx, Rel def)
	{
		return this.argAsRel(idx, def, true);
	}
	public Rel argAsRel(int idx)
	{
		return this.argAsRel(idx, null);
	}
	
	// -------------------------------------------- //
	// Commonly used logic
	// -------------------------------------------- //
	
	public boolean canIAdministerYou(FPlayer i, FPlayer you)
	{
		if ( ! i.getFaction().equals(you.getFaction()))
		{
			i.sendMessage(p.txt.parse("%s <b>n'est pas dans la même faction que vous.",you.describeTo(i, true)));
			return false;
		}
		
		if (i.getRole().isMoreThan(you.getRole()) || i.getRole().equals(Rel.LEADER) )
		{
			return true;
		}
		
		if (you.getRole().equals(Rel.LEADER))
		{
			i.sendMessage(p.txt.parse("<b>Seul l'administrateur de la faction peut le faire."));
		}
		else if (i.getRole().equals(Rel.OFFICER))
		{
			if ( i == you )
			{
				return true; //Moderators can control themselves
			}
			else
			{
				i.sendMessage(p.txt.parse("<b>Les modérateurs ne peuvent pas se contrôler ..."));
			}
		}
		else
		{
			i.sendMessage(p.txt.parse("<b>Vous devez être un modérateur de faction pour le faire."));
		}
		
		return false;
	}
	
	// if economy is enabled and they're not on the bypass list, make 'em pay; returns true unless person can't afford the cost
	public boolean payForCommand(double cost, String toDoThis, String forDoingThis)
	{
		if ( ! Econ.shouldBeUsed() || this.fme == null || cost == 0.0 || fme.hasAdminMode()) return true;

		if(Conf.bankEnabled && Conf.bankFactionPaysCosts && fme.hasFaction())
			return Econ.modifyMoney(myFaction, -cost, toDoThis, forDoingThis);
		else
			return Econ.modifyMoney(fme, -cost, toDoThis, forDoingThis);
	}

	// like above, but just make sure they can pay; returns true unless person can't afford the cost
	public boolean canAffordCommand(double cost, String toDoThis)
	{
		if ( ! Econ.shouldBeUsed() || this.fme == null || cost == 0.0 || fme.hasAdminMode()) return true;

		if(Conf.bankEnabled && Conf.bankFactionPaysCosts && fme.hasFaction())
			return Econ.hasAtLeast(myFaction, cost, toDoThis);
		else
			return Econ.hasAtLeast(fme, cost, toDoThis);
	}
}
