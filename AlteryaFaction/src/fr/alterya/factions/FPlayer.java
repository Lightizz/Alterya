package fr.alterya.factions;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.alterya.factions.integration.Econ;

import fr.alterya.factions.event.FPlayerLeaveEvent;
import fr.alterya.factions.event.LandClaimEvent;
import fr.alterya.factions.iface.EconomyParticipator;
import fr.alterya.factions.iface.RelationParticipator;
import fr.alterya.factions.integration.LWCFeatures;
import fr.alterya.factions.integration.SpoutFeatures;
import fr.alterya.factions.integration.Worldguard;
import fr.alterya.factions.struct.FFlag;
import fr.alterya.factions.struct.FPerm;
import fr.alterya.factions.struct.Rel;
import fr.alterya.factions.util.RelationUtil;
import fr.alterya.factions.zcore.persist.PlayerEntity;


/**
 * Logged in players always have exactly one FPlayer instance.
 * Logged out players may or may not have an FPlayer instance. They will always have one if they are part of a faction.
 * This is because only players with a faction are saved to disk (in order to not waste disk space).
 * 
 * The FPlayer is linked to a minecraft player using the player name.
 * 
 * The same instance is always returned for the same player.
 * This means you can use the == operator. No .equals method necessary.
 */

// TODO: The players are saved in non order.
public class FPlayer extends PlayerEntity implements EconomyParticipator
{	
	// FIELD: lastStoodAt
	private transient FLocation lastStoodAt = new FLocation(); // Where did this player stand the last time we checked?
	public FLocation getLastStoodAt() { return this.lastStoodAt; }
	public void setLastStoodAt(FLocation flocation) { this.lastStoodAt = flocation; }
	
	// FIELD: factionId
	private String factionId;
	public Faction getFaction() { if(this.factionId == null) {return null;} return Factions.i.get(this.factionId); }
	public String getFactionId() { return this.factionId; }
	public boolean hasFaction() { return ! factionId.equals("0"); }
	public void setFaction(Faction faction)
	{
		Faction oldFaction = this.getFaction();
		if (oldFaction != null) oldFaction.removeFPlayer(this);
		faction.addFPlayer(this);
		this.factionId = faction.getId();
		SpoutFeatures.updateTitle(this, null);
		SpoutFeatures.updateTitle(null, this);
	}
	
	// FIELD: role
	private Rel role;
	public Rel getRole() { return this.role; }
	public void setRole(Rel role) { this.role = role; SpoutFeatures.updateTitle(this, null); }
	
	// FIELD: title
	private String title;
	public String getTitle() { return this.title; }
	public void setTitle(String title) { this.title = title; }
	
	// FIELD: power
	private double power;

	// FIELD: powerBoost
	// special increase/decrease to min and max power for this player
	private double powerBoost;
	public double getPowerBoost() { return this.powerBoost; }
	public void setPowerBoost(double powerBoost) { this.powerBoost = powerBoost; }

	// FIELD: lastPowerUpdateTime
	private long lastPowerUpdateTime;
	
	// FIELD: lastLoginTime
	private long lastLoginTime;
	
	// FIELD: mapAutoUpdating
	private transient boolean mapAutoUpdating;
	public void setMapAutoUpdating(boolean mapAutoUpdating) { this.mapAutoUpdating = mapAutoUpdating; }
	public boolean isMapAutoUpdating() { return mapAutoUpdating; }
	
	// FIELD: autoClaimEnabled
	private transient Faction autoClaimFor;
	public Faction getAutoClaimFor() { return autoClaimFor; }
	public void setAutoClaimFor(Faction faction) { this.autoClaimFor = faction; }
		
	private transient boolean hasAdminMode = false;
	public boolean hasAdminMode() { return this.hasAdminMode; }
	public void setHasAdminMode(boolean val) { this.hasAdminMode = val; }
	
	// FIELD: loginPvpDisabled
	private transient boolean loginPvpDisabled;
	
	// FIELD: account
	public String getAccountId() { return this.getId(); }
	
	// -------------------------------------------- //
	// Construct
	// -------------------------------------------- //
	
	// GSON need this noarg constructor.
	public FPlayer()
	{
		this.resetFactionData(false);
		this.power = Conf.powerPlayerStarting;
		this.lastPowerUpdateTime = System.currentTimeMillis();
		this.lastLoginTime = System.currentTimeMillis();
		this.mapAutoUpdating = false;
		this.autoClaimFor = null;
		this.loginPvpDisabled = (Conf.noPVPDamageToOthersForXSecondsAfterLogin > 0) ? true : false;
		this.powerBoost = 0.0;

		if ( ! Conf.newPlayerStartingFactionID.equals("0") && Factions.i.exists(Conf.newPlayerStartingFactionID))
		{
			this.factionId = Conf.newPlayerStartingFactionID;
		}
	}
	
	public final void resetFactionData(boolean doSpoutUpdate)
	{
		if (this.factionId != null && Factions.i.exists(this.factionId)) // Avoid infinite loop! TODO: I think that this is needed is a sign we need to refactor.
		{
			Faction currentFaction = this.getFaction();
			if (currentFaction != null)
			{
				currentFaction.removeFPlayer(this);
			}
		}

		this.factionId = "0"; // The default neutral faction

		this.role = Rel.MEMBER;
		this.title = "";
		this.autoClaimFor = null;

		if (doSpoutUpdate)
		{
			SpoutFeatures.updateTitle(this, null);
			SpoutFeatures.updateTitle(null, this);
			SpoutFeatures.updateCape(this.getPlayer(), null);
		}
	}
	
	public void resetFactionData()
	{
		this.resetFactionData(true);
	}
	
	// -------------------------------------------- //
	// Getters And Setters
	// -------------------------------------------- //
	
	public long getLastLoginTime()
	{
		return lastLoginTime;
	}

	public void setLastLoginTime(long lastLoginTime)
	{
		losePowerFromBeingOffline();
		this.lastLoginTime = lastLoginTime;
		this.lastPowerUpdateTime = lastLoginTime;
		if (Conf.noPVPDamageToOthersForXSecondsAfterLogin > 0)
		{
			this.loginPvpDisabled = true;
		}
	}

	public boolean hasLoginPvpDisabled()
	{
		if (!loginPvpDisabled)
		{
			return false;
		}
		if (this.lastLoginTime + (Conf.noPVPDamageToOthersForXSecondsAfterLogin * 1000) < System.currentTimeMillis())
		{
			this.loginPvpDisabled = false;
			return false;
		}
		return true;
	}
	
	//----------------------------------------------//
	// Title, Name, Faction Tag and Chat
	//----------------------------------------------//
	public String getName()
	{
		return getId();
	}
	
	public String getTag()
	{
		if ( ! this.hasFaction())
		{
			return "";
		}
		return this.getFaction().getTag();
	}
	
	// Base concatenations:
	
	public String getNameAndSomething(String something)
	{
		String ret = this.role.getPrefix();
		if (something.length() > 0) {
			ret += something+" ";
		}
		ret += this.getName();
		return ret;
	}
	
	public String getNameAndTitle()
	{
		return this.getNameAndSomething(this.getTitle());
	}
	
	public String getNameAndTag()
	{
		return this.getNameAndSomething(this.getTag());
	}
	
	// Colored concatenations:
	// These are used in information messages
	
	public String getNameAndTitle(Faction faction)
	{
		return this.getColorTo(faction)+this.getNameAndTitle();
	}
	public String getNameAndTitle(FPlayer fplayer)
	{
		return this.getColorTo(fplayer)+this.getNameAndTitle();
	}
	
	// Chat Tag: 
	// These are injected into the format of global chat messages.
	
	public String getChatTag()
	{
		if ( ! this.hasFaction()) {
			return "";
		}
		
		return String.format(Conf.chatTagFormat, this.role.getPrefix()+this.getTag());
	}
	
	// Colored Chat Tag
	public String getChatTag(Faction faction)
	{
		if ( ! this.hasFaction()) {
			return "";
		}
		
		return this.getRelationTo(faction).getColor()+getChatTag();
	}
	
	public String getChatTag(FPlayer fplayer)
	{
		if ( ! this.hasFaction())
		{
			return "";
		}
		
		return this.getColorTo(fplayer)+getChatTag();
	}
	
	// -------------------------------
	// Relation and relation colors
	// -------------------------------
	
	@Override
	public String describeTo(RelationParticipator observer, boolean ucfirst)
	{
		return RelationUtil.describeThatToMe(this, observer, ucfirst);
	}
	
	@Override
	public String describeTo(RelationParticipator observer)
	{
		return RelationUtil.describeThatToMe(this, observer);
	}
	
	@Override
	public Rel getRelationTo(RelationParticipator observer)
	{
		return RelationUtil.getRelationOfThatToMe(this, observer);
	}
	
	@Override
	public Rel getRelationTo(RelationParticipator observer, boolean ignorePeaceful)
	{
		return RelationUtil.getRelationOfThatToMe(this, observer, ignorePeaceful);
	}
	
	public Rel getRelationToLocation()
	{
		return Board.getFactionAt(new FLocation(this)).getRelationTo(this);
	}
	
	@Override
	public ChatColor getColorTo(RelationParticipator observer)
	{
		return RelationUtil.getColorOfThatToMe(this, observer);
	}
	
	//----------------------------------------------//
	// Health
	//----------------------------------------------//
	public void heal(int amnt)
	{
		Player player = this.getPlayer();
		if (player == null)
		{
			return;
		}
		player.setHealth(player.getHealth() + amnt);
	}
	
	
	//----------------------------------------------//
	// Power
	//----------------------------------------------//
	public double getPower()
	{
		this.updatePower();
		return this.power;
	}
	
	protected void alterPower(double delta)
	{
		this.power += delta;
		if (this.power > this.getPowerMax())
			this.power = this.getPowerMax();
		else if (this.power < this.getPowerMin())
			this.power = this.getPowerMin();
	}
	
	public double getPowerMax()
	{
		return Conf.powerPlayerMax + this.powerBoost;
	}
	
	public double getPowerMin()
	{
		return Conf.powerPlayerMin + this.powerBoost;
	}
	
	public int getPowerRounded()
	{
		return (int) Math.round(this.getPower());
	}
	
	public int getPowerMaxRounded()
	{
		return (int) Math.round(this.getPowerMax());
	}
	
	public int getPowerMinRounded()
	{
		return (int) Math.round(this.getPowerMin());
	}
	
	protected void updatePower()
	{
		if (this.isOffline())
		{
			losePowerFromBeingOffline();
			if (!Conf.powerRegenOffline)
			{
				return;
			}
		}
		long now = System.currentTimeMillis();
		long millisPassed = now - this.lastPowerUpdateTime;
		this.lastPowerUpdateTime = now;

		Player thisPlayer = this.getPlayer();
		if (thisPlayer != null && thisPlayer.isDead()) return;  // don't let dead players regain power until they respawn

		int millisPerMinute = 60*1000;		
		double powerPerMinute = Conf.powerPerMinute;
		if(Conf.scaleNegativePower && this.power < 0)
		{
			powerPerMinute += (Math.sqrt(Math.abs(this.power)) * Math.abs(this.power)) / Conf.scaleNegativeDivisor;
		}
		this.alterPower(millisPassed * powerPerMinute / millisPerMinute);
		
	}

	protected void losePowerFromBeingOffline()
	{
		if (Conf.powerOfflineLossPerDay > 0.0 && this.power > Conf.powerOfflineLossLimit)
		{
			long now = System.currentTimeMillis();
			long millisPassed = now - this.lastPowerUpdateTime;
			this.lastPowerUpdateTime = now;

			double loss = millisPassed * Conf.powerOfflineLossPerDay / (24*60*60*1000);
			if (this.power - loss < Conf.powerOfflineLossLimit)
			{
				loss = this.power;
			}
			this.alterPower(-loss);
		}
	}
	
	public void onDeath()
	{
		this.updatePower();
		this.alterPower(-Conf.powerPerDeath);
	}
	
	//----------------------------------------------//
	// Territory
	//----------------------------------------------//
	public boolean isInOwnTerritory()
	{
		return Board.getFactionAt(new FLocation(this)) == this.getFaction();
	}
	
	/*public boolean isInOthersTerritory()
	{
		Faction factionHere = Board.getFactionAt(new FLocation(this));
		return factionHere != null && factionHere.isNormal() && factionHere != this.getFaction();
	}*/

	/*public boolean isInAllyTerritory()
	{
		return Board.getFactionAt(new FLocation(this)).getRelationTo(this) == Rel.ALLY;
	}*/

	/*public boolean isInNeutralTerritory()
	{
		return Board.getFactionAt(new FLocation(this)).getRelationTo(this) == Rel.NEUTRAL;
	}*/

	public boolean isInEnemyTerritory()
	{
		return Board.getFactionAt(new FLocation(this)).getRelationTo(this) == Rel.ENEMY;
	}

	public void sendFactionHereMessage()
	{
		if (SpoutFeatures.updateTerritoryDisplay(this))
		{
			return;
		}
		Faction factionHere = Board.getFactionAt(this.getLastStoodAt());
		String msg = P.p.txt.parse("<i>")+" ~ "+factionHere.getTag(this);
		if (factionHere.getDescription().length() > 0)
		{
			msg += " - "+factionHere.getDescription();
		}
		this.sendMessage(msg);
	}
	
	// -------------------------------
	// Actions
	// -------------------------------
	
	public void leave(boolean makePay)
	{
		Faction myFaction = this.getFaction();
		makePay = makePay && Econ.shouldBeUsed() && ! this.hasAdminMode();

		if (myFaction == null)
		{
			resetFactionData();
			return;
		}

		boolean perm = myFaction.getFlag(FFlag.PERMANENT);
		
		if (!perm && this.getRole() == Rel.LEADER && myFaction.getFPlayers().size() > 1)
		{
			msg("<b>Vous devez d'abord donner le r�le d'administrateur � quelqu'un d'autre.");
			return;
		}

		if (!Conf.canLeaveWithNegativePower && this.getPower() < 0)
		{
			msg("<b>Vous ne pouvez pas partir tant que votre pouvoir n'est pas positif.");
			return;
		}

		// if economy is enabled and they're not on the bypass list, make sure they can pay
		if (makePay && ! Econ.hasAtLeast(this, Conf.econCostLeave, "to leave your faction.")) return;

		FPlayerLeaveEvent leaveEvent = new FPlayerLeaveEvent(this,myFaction,FPlayerLeaveEvent.PlayerLeaveReason.LEAVE);
		Bukkit.getServer().getPluginManager().callEvent(leaveEvent);
		if (leaveEvent.isCancelled()) return;

		// then make 'em pay (if applicable)
		if (makePay && ! Econ.modifyMoney(this, -Conf.econCostLeave, "to leave your faction.", "for leaving your faction.")) return;

		// Am I the last one in the faction?
		if (myFaction.getFPlayers().size() == 1)
		{
			// Transfer all money
			if (Econ.shouldBeUsed())
				Econ.transferMoney(this, myFaction, this, Econ.getBalance(myFaction.getAccountId()));
		}
		
		if (myFaction.isNormal())
		{
			for (FPlayer fplayer : myFaction.getFPlayersWhereOnline(true))
			{
				fplayer.msg("%s<i> a quitt� %s<i>.", this.describeTo(fplayer, true), myFaction.describeTo(fplayer));
			}

			if (Conf.logFactionLeave)
				P.p.log(this.getName()+" a quitt� la faction : "+myFaction.getTag());
		}
		
		this.resetFactionData();
		myFaction.updateLastOnlineTime();

		if (myFaction.isNormal() && !perm && myFaction.getFPlayers().isEmpty())
		{
			// Remove this faction
			for (FPlayer fplayer : FPlayers.i.getOnline())
			{
				fplayer.msg("<i>%s<i> a �t� dissoute.", myFaction.describeTo(fplayer, true));
			}

			myFaction.detach();
			if (Conf.logFactionDisband)
				P.p.log("La faction "+myFaction.getTag()+" ("+myFaction.getId()+") a �t� dissous � cause du dernier joueur ("+this.getName()+") a quitter.");
		}
	}

	public boolean canClaimForFactionAtLocation(Faction forFaction, Location location, boolean notifyFailure)
	{
		String error = null;
		FLocation flocation = new FLocation(location);
		Faction myFaction = getFaction();
		Faction currentFaction = Board.getFactionAt(flocation);
		int ownedLand = forFaction.getLandRounded();
		
		if (Conf.worldGuardChecking && Worldguard.checkForRegionsInChunk(location))
		{
			// Checks for WorldGuard regions in the chunk attempting to be claimed
			error = P.p.txt.parse("<b>Cette terre est prot�g�e");
		}
		else if (Conf.worldsNoClaiming.contains(flocation.getWorldName()))
		{
			error = P.p.txt.parse("<b>D�sol�, ce monde a des revendications de terres d�sactiv�es.");
		}
		else if (this.hasAdminMode())
		{
			return true;
		}
		else if (forFaction == currentFaction)
		{
			error = P.p.txt.parse("%s<i> poss�dez d�j� cette terre.", forFaction.describeTo(this, true));
		}
		else if ( ! FPerm.TERRITORY.has(this, forFaction, true))
		{
			return false;
		}
		else if (forFaction.getFPlayers().size() < Conf.claimsRequireMinFactionMembers)
		{
			error = P.p.txt.parse("Les factions doivent avoir au moins <h>%s<b> membres � r�clamer des terres.", Conf.claimsRequireMinFactionMembers);
		}
		else if (ownedLand >= forFaction.getPowerRounded())
		{
			error = P.p.txt.parse("<b>Vous ne pouvez pas r�clamer plus de terres ! Vous avez besoin de plus de puissance !");
		}
		else if (Conf.claimedLandsMax != 0 && ownedLand >= Conf.claimedLandsMax && ! forFaction.getFlag(FFlag.INFPOWER))
		{
			error = P.p.txt.parse("<b>Limite atteinte, vous ne pouvez plus claim de terre.");
		}
		else if ( ! Conf.claimingFromOthersAllowed && currentFaction.isNormal())
		{
			error = P.p.txt.parse("<b>Vous ne pouvez pas r�clamer des terres � d'autres.");
		}
		else if (currentFaction.getRelationTo(forFaction).isAtLeast(Rel.TRUCE) && ! currentFaction.isNone())
		{
			error = P.p.txt.parse("<b>Vous ne pouvez pas r�clamer cette terre en raison de votre relation avec le propri�taire actuel.");
		}
		else if
		(
			Conf.claimsMustBeConnected
			&& ! this.hasAdminMode()
			&& myFaction.getLandRoundedInWorld(flocation.getWorldName()) > 0
			&& !Board.isConnectedLocation(flocation, myFaction)
			&& (!Conf.claimsCanBeUnconnectedIfOwnedByOtherFaction || !currentFaction.isNormal())
		)
		{
			if (Conf.claimsCanBeUnconnectedIfOwnedByOtherFaction)
				error = P.p.txt.parse("<b>Vous ne pouvez r�clamer que des terres suppl�mentaires qui sont li�es � votre premi�re revendication ou contr�l�es par une autre faction!");
			else
				error = P.p.txt.parse("<b>Vous ne pouvez r�clamer que des terres suppl�mentaires qui sont li�es � votre premi�re revendication !");
		}
		else if (currentFaction.isNormal())
		{
			if ( ! currentFaction.hasLandInflation())
			{
				 // TODO more messages WARN current faction most importantly
				error = P.p.txt.parse("%s<i> poss�de cette terre et est assez fort pour la garder.", currentFaction.getTag(this));
			}
			else if ( ! Board.isBorderLocation(flocation))
			{
				error = P.p.txt.parse("<b>Vous devez commencer � revendiquer un terrain � la fronti�re du territoire.");
			}
		}
		
		if (notifyFailure && error != null)
		{
			msg(error);
		}
		return error == null;
	}
	
	public boolean attemptClaim(Faction forFaction, Location location, boolean notifyFailure)
	{
		// notifyFailure is false if called by auto-claim; no need to notify on every failure for it
		// return value is false on failure, true on success
		
		FLocation flocation = new FLocation(location);
		Faction currentFaction = Board.getFactionAt(flocation);
		
		int ownedLand = forFaction.getLandRounded();
		
		if ( ! this.canClaimForFactionAtLocation(forFaction, location, notifyFailure)) return false;
		
		// TODO: Add flag no costs??
		// if economy is enabled and they're not on the bypass list, make sure they can pay
		boolean mustPay = Econ.shouldBeUsed() && ! this.hasAdminMode();
		double cost = 0.0;
		EconomyParticipator payee = null;
		if (mustPay)
		{
			cost = Econ.calculateClaimCost(ownedLand, currentFaction.isNormal());

			if (Conf.econClaimUnconnectedFee != 0.0 && forFaction.getLandRoundedInWorld(flocation.getWorldName()) > 0 && !Board.isConnectedLocation(flocation, forFaction))
				cost += Conf.econClaimUnconnectedFee;

			if(Conf.bankEnabled && Conf.bankFactionPaysLandCosts && this.hasFaction())
				payee = this.getFaction();
			else
				payee = this;

			if ( ! Econ.hasAtLeast(payee, cost, " pour revendiquer cette terre")) return false;
		}

		LandClaimEvent claimEvent = new LandClaimEvent(flocation, forFaction, this);
		Bukkit.getServer().getPluginManager().callEvent(claimEvent);
		if(claimEvent.isCancelled()) return false;

		// then make 'em pay (if applicable)
		if (mustPay && ! Econ.modifyMoney(payee, -cost, "de r�clamer cette terre ", " pour r�clamer cette terre")) return false;

		if (LWCFeatures.getEnabled() && forFaction.isNormal() && Conf.onCaptureResetLwcLocks)
			LWCFeatures.clearOtherChests(flocation, this.getFaction());

		// announce success
		Set<FPlayer> informTheseFPlayers = new HashSet<FPlayer>();
		informTheseFPlayers.add(this);
		informTheseFPlayers.addAll(forFaction.getFPlayersWhereOnline(true));
		for (FPlayer fp : informTheseFPlayers)
		{
			fp.msg("<h>%s<i> avez revendiqu�e cette terre pour <h>%s<i> de <h>%s<i>.", this.describeTo(fp, true), forFaction.describeTo(fp), currentFaction.describeTo(fp));
		}
		
		Board.setFactionAt(forFaction, flocation);
		SpoutFeatures.updateTerritoryDisplayLoc(flocation);

		if (Conf.logLandClaims)
			P.p.log(this.getName()+" terre revendiqu�e � ("+flocation.getCoordString()+") pour la faction :: "+forFaction.getTag());

		return true;
	}
	
	// -------------------------------------------- //
	// Persistance
	// -------------------------------------------- //
	
	@Override
	public boolean shouldBeSaved()
	{
		if (this.hasFaction()) return true;
		if (this.getPowerRounded() != this.getPowerMaxRounded() && this.getPowerRounded() != (int) Math.round(Conf.powerPlayerStarting)) return true;
		return false;
	}
	
	public void msg(String str, Object... args)
	{
		this.sendMessage(P.p.txt.parse(str, args));
	}
}
