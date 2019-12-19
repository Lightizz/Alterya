package fr.alterya.factions.cmd;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import fr.alterya.factions.Board;
import fr.alterya.factions.Conf;
import fr.alterya.factions.FLocation;
import fr.alterya.factions.FPlayer;
import fr.alterya.factions.FPlayers;
import fr.alterya.factions.Faction;
import fr.alterya.factions.integration.EssentialsFeatures;
import fr.alterya.factions.struct.FFlag;
import fr.alterya.factions.struct.Rel;


public class CmdHome extends FCommand
{
	
	public CmdHome()
	{
		super();
		this.aliases.add("home");
		
		//this.requiredArgs.add("");
		//this.optionalArgs.put("", "");
		
		//this.permission = Permission.HOME.node;
		this.disableOnLock = false;
		
		senderMustBePlayer = true;
		senderMustBeMember = true;
		senderMustBeOfficer = false;
		senderMustBeLeader = false;
	}
	
	@Override
	public void perform()
	{
		// TODO: Hide this command on help also.
		if ( ! Conf.homesEnabled)
		{
			fme.msg("<b>Désolé, les f homes sont désactivés sur ce monde.");
			return;
		}

		if (myFaction == null) return;
		
		if ( ! Conf.homesTeleportCommandEnabled)
		{
			fme.msg("<b>Désolé, les téléportation au f homes sont désactivés sur ce monde.");
			return;
		}
		
		if ( ! myFaction.hasHome())
		{
			fme.msg("<b>Votre faction n'a pas de f home " + (fme.getRole().isLessThan(Rel.OFFICER) ? "<i> Demander à votre chef de le faire:" : "<i>Vous devez:"));
			fme.sendMessage(p.cmdBase.cmdSethome.getUseageTemplate());
			return;
		}
		
		if ( ! Conf.homesTeleportAllowedFromEnemyTerritory && fme.isInEnemyTerritory())
		{
			fme.msg("<b>Vous ne pouvez pas vous teleporter à votre f home dans un claim qui ne vous appartiens pas.");
			return;
		}
		
		if ( ! Conf.homesTeleportAllowedFromDifferentWorld && me.getWorld().getUID() != myFaction.getHome().getWorld().getUID())
		{
			fme.msg("<b>Vous ne pouvez pas teleporter à votre f home dans un autre monde.");
			return;
		}
		
		Faction faction = Board.getFactionAt(new FLocation(me.getLocation()));
		Location loc = me.getLocation().clone();
		
		// if player is not in a safe zone or their own faction territory, only allow teleport if no enemies are nearby
		if
		(
			Conf.homesTeleportAllowedEnemyDistance > 0
			&&
			faction.getFlag(FFlag.PVP)
			&&
			(
				! fme.isInOwnTerritory()
				||
				(
					fme.isInOwnTerritory()
					&&
					! Conf.homesTeleportIgnoreEnemiesIfInOwnTerritory
				)
			)
		)
		{
			World w = loc.getWorld();
			double x = loc.getX();
			double y = loc.getY();
			double z = loc.getZ();

			for (Player p : me.getServer().getOnlinePlayers())
			{
				if (p == null || !p.isOnline() || p.isDead() || p == me || p.getWorld() != w)
					continue;

				FPlayer fp = FPlayers.i.get(p);
				if (fme.getRelationTo(fp) != Rel.ENEMY)
					continue;

				Location l = p.getLocation();
				double dx = Math.abs(x - l.getX());
				double dy = Math.abs(y - l.getY());
				double dz = Math.abs(z - l.getZ());
				double max = Conf.homesTeleportAllowedEnemyDistance;

				// box-shaped distance check
				if (dx > max || dy > max || dz > max)
					continue;

				fme.msg("<b>Vous ne pouvez pas vous téléporter chez vous dans votre faction tant qu'un ennemi est à l'intérieur. " + Conf.homesTeleportAllowedEnemyDistance + " blocks de vous.");
				return;
			}
		}

		// if Essentials teleport handling is enabled and available, pass the teleport off to it (for delay and cooldown)
		if (EssentialsFeatures.handleTeleport(me, myFaction.getHome())) return;

		// if economy is enabled, they're not on the bypass list, and this command has a cost set, make 'em pay
		if ( ! payForCommand(Conf.econCostHome, "se téléporter dans votre foyer de faction "," pour se téléporter dans votre foyer de faction")) return;

		// Create a smoke effect
		if (Conf.homesTeleportCommandSmokeEffectEnabled)
		{
			List<Location> smokeLocations = new ArrayList<Location>();
			smokeLocations.add(loc);
			smokeLocations.add(loc.add(0, 1, 0));
			smokeLocations.add(myFaction.getHome());
			smokeLocations.add(myFaction.getHome().clone().add(0, 1, 0));
			//SmokeUtil.spawnCloudRandom(smokeLocations, 3f);
		}

		me.teleport(myFaction.getHome());
	}
	
}
