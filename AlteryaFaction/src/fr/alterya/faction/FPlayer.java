package fr.alterya.faction;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.alterya.faction.event.FPlayerLeaveEvent;
import fr.alterya.faction.event.LandClaimEvent;
import fr.alterya.faction.iface.EconomyParticipator;
import fr.alterya.faction.iface.RelationParticipator;
import fr.alterya.faction.integration.Econ;
import fr.alterya.faction.integration.LWCFeatures;
import fr.alterya.faction.integration.SpoutFeatures;
import fr.alterya.faction.integration.Worldguard;
import fr.alterya.faction.struct.FFlag;
import fr.alterya.faction.struct.FPerm;
import fr.alterya.faction.struct.Rel;
import fr.alterya.faction.util.RelationUtil;
import fr.alterya.faction.zcore.persist.PlayerEntity;

public class FPlayer extends PlayerEntity implements EconomyParticipator
{	
	private transient FLocation lastStoodAt = new FLocation();
	public FLocation getLastStoodAt() { return this.lastStoodAt; }
	public void setLastStoodAt(FLocation flocation) { this.lastStoodAt = flocation; }
	
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
	
	private Rel role;
	public Rel getRole() { return this.role; }
	public void setRole(Rel role) { this.role = role; SpoutFeatures.updateTitle(this, null); }
	
	private String title;
	public String getTitle() { return this.title; }
	public void setTitle(String title) { this.title = title; }
	
	private double power;

	private double powerBoost;
	public double getPowerBoost() { return this.powerBoost; }
	public void setPowerBoost(double powerBoost) { this.powerBoost = powerBoost; }

	private long lastPowerUpdateTime;
	
	private long lastLoginTime;
	
	private transient boolean mapAutoUpdating;
	public void setMapAutoUpdating(boolean mapAutoUpdating) { this.mapAutoUpdating = mapAutoUpdating; }
	public boolean isMapAutoUpdating() { return mapAutoUpdating; }
	
	private transient Faction autoClaimFor;
	public Faction getAutoClaimFor() { return autoClaimFor; }
	public void setAutoClaimFor(Faction faction) { this.autoClaimFor = faction; }
		
	private transient boolean hasAdminMode = false;
	public boolean hasAdminMode() { return this.hasAdminMode; }
	public void setHasAdminMode(boolean val) { this.hasAdminMode = val; }
	
	private transient boolean loginPvpDisabled;
	
	public String getAccountId() { return this.getId(); }
	
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
		if (this.factionId != null && Factions.i.exists(this.factionId))
		{
			Faction currentFaction = this.getFaction();
			if (currentFaction != null)
			{
				currentFaction.removeFPlayer(this);
			}
		}

		this.factionId = "0"; 

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
		
	public String getNameAndTitle(Faction faction)
	{
		return this.getColorTo(faction)+this.getNameAndTitle();
	}
	public String getNameAndTitle(FPlayer fplayer)
	{
		return this.getColorTo(fplayer)+this.getNameAndTitle();
	}
	
	public String getChatTag()
	{
		if ( ! this.hasFaction()) {
			return "";
		}
		
		return String.format(Conf.chatTagFormat, this.role.getPrefix()+this.getTag());
	}
	
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

	public void heal(int amnt)
	{
		Player player = this.getPlayer();
		if (player == null)
		{
			return;
		}
		player.setHealth(player.getHealth() + amnt);
	}
	
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
		if (thisPlayer != null && thisPlayer.isDead()) return;

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
		String msg = Main.main.txt.parse("<i>")+" ~ "+factionHere.getTag(this);
		if (factionHere.getDescription().length() > 0)
		{
			msg += " - "+factionHere.getDescription();
		}
		this.sendMessage(msg);
	}
	
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
			msg("<b>You must give the admin role to someone else first.");
			return;
		}

		if (!Conf.canLeaveWithNegativePower && this.getPower() < 0)
		{
			msg("<b>You cannot leave until your power is positive.");
			return;
		}

		if (makePay && ! Econ.hasAtLeast(this, Conf.econCostLeave, "to leave your faction.")) return;

		FPlayerLeaveEvent leaveEvent = new FPlayerLeaveEvent(this,myFaction,FPlayerLeaveEvent.PlayerLeaveReason.LEAVE);
		Bukkit.getServer().getPluginManager().callEvent(leaveEvent);
		if (leaveEvent.isCancelled()) return;

		if (makePay && ! Econ.modifyMoney(this, -Conf.econCostLeave, "to leave your faction.", "for leaving your faction.")) return;

		if (myFaction.getFPlayers().size() == 1)
		{
			if (Econ.shouldBeUsed())
				Econ.transferMoney(this, myFaction, this, Econ.getBalance(myFaction.getAccountId()));
		}
		
		if (myFaction.isNormal())
		{
			for (FPlayer fplayer : myFaction.getFPlayersWhereOnline(true))
			{
				fplayer.msg("%s<i> left %s<i>.", this.describeTo(fplayer, true), myFaction.describeTo(fplayer));
			}

			if (Conf.logFactionLeave)
				Main.main.log(this.getName()+" left the faction: "+myFaction.getTag());
		}
		
		this.resetFactionData();
		myFaction.updateLastOnlineTime();

		if (myFaction.isNormal() && !perm && myFaction.getFPlayers().isEmpty())
		{
			for (FPlayer fplayer : FPlayers.i.getOnline())
			{
				fplayer.msg("<i>%s<i> was disbanded.", myFaction.describeTo(fplayer, true));
			}

			myFaction.detach();
			if (Conf.logFactionDisband)
				Main.main.log("The faction "+myFaction.getTag()+" ("+myFaction.getId()+") was disbanded due to the last player ("+this.getName()+") leaving.");
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
			error = Main.main.txt.parse("<b>This land is protected");
		}
		else if (Conf.worldsNoClaiming.contains(flocation.getWorldName()))
		{
			error = Main.main.txt.parse("<b>Sorry, this world has land claiming disabled.");
		}
		else if (this.hasAdminMode())
		{
			return true;
		}
		else if (forFaction == currentFaction)
		{
			error = Main.main.txt.parse("%s<i> already own this land.", forFaction.describeTo(this, true));
		}
		else if ( ! FPerm.TERRITORY.has(this, forFaction, true))
		{
			return false;
		}
		else if (forFaction.getFPlayers().size() < Conf.claimsRequireMinFactionMembers)
		{
			error = Main.main.txt.parse("Factions must have at least <h>%s<b> members to claim land.", Conf.claimsRequireMinFactionMembers);
		}
		else if (ownedLand >= forFaction.getPowerRounded())
		{
			error = Main.main.txt.parse("<b>You can't claim more land! You need more power!");
		}
		else if (Conf.claimedLandsMax != 0 && ownedLand >= Conf.claimedLandsMax && ! forFaction.getFlag(FFlag.INFPOWER))
		{
			error = Main.main.txt.parse("<b>Limit reached. You can't claim more land!");
		}
		else if ( ! Conf.claimingFromOthersAllowed && currentFaction.isNormal())
		{
			error = Main.main.txt.parse("<b>You may not claim land from others.");
		}
		else if (currentFaction.getRelationTo(forFaction).isAtLeast(Rel.TRUCE) && ! currentFaction.isNone())
		{
			error = Main.main.txt.parse("<b>You can't claim this land due to your relation with the current owner.");
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
				error = Main.main.txt.parse("<b>You can only claim additional land which is connected to your first claim or controlled by another faction!");
			else
				error = Main.main.txt.parse("<b>You can only claim additional land which is connected to your first claim!");
		}
		else if (currentFaction.isNormal())
		{
			if ( ! currentFaction.hasLandInflation())
			{
				error = Main.main.txt.parse("%s<i> owns this land and is strong enough to keep it.", currentFaction.getTag(this));
			}
			else if ( ! Board.isBorderLocation(flocation))
			{
				error = Main.main.txt.parse("<b>You must start claiming land at the border of the territory.");
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
		FLocation flocation = new FLocation(location);
		Faction currentFaction = Board.getFactionAt(flocation);
		
		int ownedLand = forFaction.getLandRounded();
		
		if ( ! this.canClaimForFactionAtLocation(forFaction, location, notifyFailure)) {
			return false;
		}
		
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

			if ( ! Econ.hasAtLeast(payee, cost, "to claim this land")) return false;
		}

		LandClaimEvent claimEvent = new LandClaimEvent(flocation, forFaction, this);
		Bukkit.getServer().getPluginManager().callEvent(claimEvent);
		if(claimEvent.isCancelled()) return false;

		if (mustPay && ! Econ.modifyMoney(payee, -cost, "to claim this land", "for claiming this land")) {
			return false;
		}

		if (LWCFeatures.getEnabled() && forFaction.isNormal() && Conf.onCaptureResetLwcLocks)
			LWCFeatures.clearOtherChests(flocation, this.getFaction());

		Set<FPlayer> informTheseFPlayers = new HashSet<FPlayer>();
		informTheseFPlayers.add(this);
		informTheseFPlayers.addAll(forFaction.getFPlayersWhereOnline(true));
		for (FPlayer fp : informTheseFPlayers)
		{
			fp.msg("<h>%s<i> claimed land for <h>%s<i> from <h>%s<i>.", this.describeTo(fp, true), forFaction.describeTo(fp), currentFaction.describeTo(fp));
		}
		
		Board.setFactionAt(forFaction, flocation);
		SpoutFeatures.updateTerritoryDisplayLoc(flocation);

		if (Conf.logLandClaims)
			Main.main.log(this.getName()+" claimed land at ("+flocation.getCoordString()+") for the faction: "+forFaction.getTag());

		return true;
	}
	
	@Override
	public boolean shouldBeSaved()
	{
		if (this.hasFaction()) return true;
		if (this.getPowerRounded() != this.getPowerMaxRounded() && this.getPowerRounded() != (int) Math.round(Conf.powerPlayerStarting)) return true;
		return false;
	}
	
	public void msg(String str, Object... args)
	{
		this.sendMessage(Main.main.txt.parse(str, args));
	}
}
