package fr.alterya.faction;

import java.util.*;

import org.bukkit.*;
import org.bukkit.entity.EntityType;

import fr.alterya.faction.struct.FFlag;
import fr.alterya.faction.struct.FPerm;
import fr.alterya.faction.struct.Rel;

public class Conf
{
	public final static transient List<String> baseCommandAliases = new ArrayList<String>();
	
	public static ChatColor colorMember = ChatColor.GREEN;
	public static ChatColor colorAlly = ChatColor.DARK_PURPLE;
	public static ChatColor colorTruce = ChatColor.LIGHT_PURPLE;
	public static ChatColor colorNeutral = ChatColor.WHITE;
	public static ChatColor colorEnemy = ChatColor.RED;
	
	public static ChatColor colorNoPVP = ChatColor.GOLD;
	public static ChatColor colorFriendlyFire = ChatColor.DARK_RED;
	//public static ChatColor colorWilderness = ChatColor.DARK_GREEN;
	
	public static Map<FFlag, Boolean> factionFlagDefaults;
	public static Map<FFlag, Boolean> factionFlagIsChangeable;
	public static Map<FPerm, Set<Rel>> factionPermDefaults;
	public static Rel factionRankDefault = Rel.RECRUIT;
	
	public static double powerPlayerMax = 10.0;
	public static double powerPlayerMin = -10.0;
	public static double powerPlayerStarting = 10.0; 
	public static double powerPerMinute = 0.2;
	public static double powerPerDeath = 4.0; 
	public static boolean scaleNegativePower = false; 
	public static double scaleNegativeDivisor = 40.0;
	public static boolean powerRegenOffline = false; 
	public static double powerOfflineLossPerDay = 0.0;
	public static double powerOfflineLossLimit = 0.0; 
	public static double powerFactionMax = 0.0; 
	
	public static String prefixLeader = "**";
	public static String prefixOfficer = "*";
	public static String prefixMember = "+";
	public static String prefixRecruit = "-";
	
	public static int factionTagLengthMin = 3;
	public static int factionTagLengthMax = 10;
	public static boolean factionTagForceUpperCase = false;
	
	public static boolean newFactionsDefaultOpen = false;

	public static int factionMemberLimit = 0;

	public static String newPlayerStartingFactionID = "0";

	public static boolean canLeaveWithNegativePower = true;
	
	public static boolean chatSetFormat = false;
	public static String chatSetFormatTo = "<{faction_relcolor}§l{faction_roleprefix}§r{faction_relcolor}{faction_tag_pr}"+ChatColor.WHITE.toString()+"%s> %s";
	public static boolean chatParseTags = true;
	public static boolean chatParseTagsColored = false;
	public static Map<String, String> chatSingleFormats = new HashMap<String, String>();
	public static transient boolean chatTagHandledByAnotherPlugin = false;
	public static String chatTagFormat = "%s"+ChatColor.WHITE;
	
	public static String herochatFactionName = "Faction";
	public static String herochatFactionNick = "F";
	public static String herochatFactionFormat = "{color}[&l{nick}&r{color} &l{faction_roleprefix}&r{color}{faction_title_pr}{sender}{color}] &f{msg}";
	public static ChatColor herochatFactionColor = ChatColor.GREEN;
	public static int herochatFactionDistance = 0;
	public static boolean herochatFactionIsShortcutAllowed = false;
	public static boolean herochatFactionCrossWorld = true;
	public static boolean herochatFactionMuted = false;
	public static Set<String> herochatFactionWorlds = new HashSet<String>();
	
	public static String herochatAlliesName = "Allies";
	public static String herochatAlliesNick = "A";
	public static String herochatAlliesFormat = "{color}[&l{nick}&r&f {faction_relcolor}&l{faction_roleprefix}&r{faction_relcolor}{faction_tag_pr}{sender}{color}] &f{msg}";
	public static ChatColor herochatAlliesColor = ChatColor.DARK_PURPLE;
	public static int herochatAlliesDistance = 0;
	public static boolean herochatAlliesIsShortcutAllowed = false;
	public static boolean herochatAlliesCrossWorld = true;
	public static boolean herochatAlliesMuted = false;
	public static Set<String> herochatAlliesWorlds = new HashSet<String>();
	
	public static String herochatAllyName = "Allies";

	public static boolean broadcastDescriptionChanges = false;

	public static double saveToFileEveryXMinutes = 30.0;

	public static double autoLeaveAfterDaysOfInactivity = 10.0;
	public static double autoLeaveRoutineRunsEveryXMinutes = 5.0;
	public static int autoLeaveRoutineMaxMillisecondsPerTick = 5;
	public static boolean removePlayerDataWhenBanned = true;

	public static boolean worldGuardChecking = false;

	public static boolean lwcIntegration = false;
	public static boolean onUnclaimResetLwcLocks = false;
	public static boolean onCaptureResetLwcLocks = false;

	public static boolean logFactionCreate = true;
	public static boolean logFactionDisband = true;
	public static boolean logFactionJoin = true;
	public static boolean logFactionKick = true;
	public static boolean logFactionLeave = true;
	public static boolean logLandClaims = true;
	public static boolean logLandUnclaims = true;
	public static boolean logMoneyTransactions = true;
	public static boolean logPlayerCommands = true;

	public static boolean handleExploitObsidianGenerators = true;
	public static boolean handleExploitEnderPearlClipping = true;
	public static boolean handleExploitInteractionSpam = true;
	public static boolean handleExploitTNTWaterlog = false;

	public static boolean homesEnabled = true;
	public static boolean homesMustBeInClaimedTerritory = true;
	public static boolean homesTeleportToOnDeath = true;
	public static boolean homesRespawnFromNoPowerLossWorlds = true;
	public static boolean homesTeleportCommandEnabled = true;
	public static boolean homesTeleportCommandEssentialsIntegration = true;
	public static boolean homesTeleportCommandSmokeEffectEnabled = true;
	public static boolean homesTeleportAllowedFromEnemyTerritory = true;
	public static boolean homesTeleportAllowedFromDifferentWorld = true;
	public static double homesTeleportAllowedEnemyDistance = 32.0;
	public static boolean homesTeleportIgnoreEnemiesIfInOwnTerritory = true;
	
	
	public static Rel friendlyFireFromRel = Rel.TRUCE;
	public static boolean disablePVPForFactionlessPlayers = false;
	public static boolean enablePVPAgainstFactionlessInAttackersLand = false;
	
	public static int noPVPDamageToOthersForXSecondsAfterLogin = 3;

	
	public static boolean permanentFactionsDisableLeaderPromotion = false;
	
	public static boolean claimsMustBeConnected = false;
	public static boolean claimingFromOthersAllowed = true;
	public static boolean claimsCanBeUnconnectedIfOwnedByOtherFaction = true;
	public static int claimsRequireMinFactionMembers = 1;
	public static int claimedLandsMax = 0;

	public static int radiusClaimFailureLimit = 9;

	//public static double considerFactionsReallyOfflineAfterXMinutes = 0.0;
	
	public static boolean protectOfflineFactionsFromExplosions = false;
	public static double offlineExplosionProtectionDelay = 2.0;
	
	public static int actionDeniedPainAmount = 2;

	public static Set<String> permanentFactionMemberDenyCommands = new LinkedHashSet<String>();

	public static Set<String> territoryNeutralDenyCommands = new LinkedHashSet<String>();
	public static Set<String> territoryEnemyDenyCommands = new LinkedHashSet<String>();
	
	public static double territoryShieldFactor = 0.3;

	public static boolean pistonProtectionThroughDenyBuild = true;

	public final transient static Set<Material> materialsEditOnInteract = EnumSet.noneOf(Material.class);
	public final transient static Set<Material> materialsEditTools = EnumSet.noneOf(Material.class);
	public final transient static Set<Material> materialsDoor = EnumSet.noneOf(Material.class);
	public final transient static Set<Material> materialsContainer = EnumSet.noneOf(Material.class);
	
	//public static Set<Material> territoryProtectedMaterialsWhenOffline = EnumSet.noneOf(Material.class);
	//public static Set<Material> territoryDenyUseageMaterialsWhenOffline = EnumSet.noneOf(Material.class);
	
	public static transient Set<EntityType> monsters = EnumSet.noneOf(EntityType.class);

	public static boolean spoutFactionTagsOverNames = true;  
	public static boolean spoutFactionTitlesOverNames = true;
	public static boolean spoutHealthBarUnderNames = true; 
	public static String spoutHealthBarLeft = "{c}[";
	public static String spoutHealthBarSolid = "|";
	public static String spoutHealthBarBetween = "&8";
	public static String spoutHealthBarEmpty = "|";
	public static String spoutHealthBarRight = "{c}]";
	public static double spoutHealthBarSolidsPerEmpty = 1d;
	public static String spoutHealthBarColorTag = "{c}";
	public static int spoutHealthBarWidth = 30;
	public static Map<Double, String> spoutHealthBarColorUnderQuota = new LinkedHashMap<Double, String>();
	public static boolean spoutCapes = true; 
	public static int spoutTerritoryDisplayPosition = 1; 
	public static float spoutTerritoryDisplaySize = 1.0f; 
	public static boolean spoutTerritoryDisplayShowDescription = true; 
	public static boolean spoutTerritoryAccessShow = true; 
	public static boolean spoutTerritoryNoticeShow = true; 
	public static int spoutTerritoryNoticeTop = 40; 
	public static boolean spoutTerritoryNoticeShowDescription = false;
	public static float spoutTerritoryNoticeSize = 1.5f; 
	public static float spoutTerritoryNoticeLeaveAfterSeconds = 2.00f;
	
	public static boolean econEnabled = false;
	public static String econUniverseAccount = "";
	public static double econCostClaimWilderness = 30.0;
	public static double econCostClaimFromFactionBonus = 30.0;
	public static double econClaimAdditionalMultiplier = 0.5;
	public static double econClaimRefundMultiplier = 0.7;
	public static double econClaimUnconnectedFee = 0.0;
	public static double econCostCreate = 100.0;
	public static double econCostSethome = 30.0;
	public static double econCostJoin = 0.0;
	public static double econCostLeave = 0.0;
	public static double econCostKick = 0.0;
	public static double econCostInvite = 0.0;
	public static double econCostHome = 0.0;
	public static double econCostTag = 0.0;
	public static double econCostDesc = 0.0;
	public static double econCostTitle = 0.0;
	public static double econCostList = 0.0;
	public static double econCostMap = 0.0;
	public static double econCostPower = 0.0;
	public static double econCostShow = 0.0;
	public static double econCostOpen = 0.0;
	public static double econCostAlly = 0.0;
	public static double econCostTruce = 0.0;
	public static double econCostNeutral = 0.0;
	public static double econCostEnemy = 0.0;

	public static int    econLandRewardTaskRunsEveryXMinutes = 20;
	public static double econLandReward = 0.00;
	
	public static boolean bankEnabled = true;
	public static boolean bankFactionPaysCosts = true;
	public static boolean bankFactionPaysLandCosts = true; 

	public static Set<String> playersWhoBypassAllProtection = new LinkedHashSet<String>();

	public static Set<String> worldsNoClaiming = new LinkedHashSet<String>();
	public static Set<String> worldsNoPowerLoss = new LinkedHashSet<String>();
	public static Set<String> worldsIgnorePvP = new LinkedHashSet<String>();
	//public static Set<String> worldsNoWildernessProtection = new LinkedHashSet<String>();
	
	public static transient int mapHeight = 8;
	public static transient int mapWidth = 39;
	public static transient char[] mapKeyChrs = "\\/#?$%=&^ABCDEFGHJKLMNOPQRSTUVWXYZ1234567890abcdeghjmnopqrsuvwxyz".toCharArray();
	
	static
	{
		baseCommandAliases.add("f");
		
		factionFlagDefaults = new LinkedHashMap<FFlag, Boolean>();
		for (FFlag flag : FFlag.values())
		{
			factionFlagDefaults.put(flag, flag.defaultDefaultValue);
		}
		
		factionPermDefaults = new LinkedHashMap<FPerm, Set<Rel>>();
		for (FPerm perm: FPerm.values())
		{
			factionPermDefaults.put(perm, perm.defaultDefaultValue);
		}
		
		chatSingleFormats.put("pl", " %s");
		chatSingleFormats.put("pr", "%s ");
		chatSingleFormats.put("pb", " %s ");
		
		territoryEnemyDenyCommands.add("home");
		territoryEnemyDenyCommands.add("sethome");
		territoryEnemyDenyCommands.add("spawn");
		territoryEnemyDenyCommands.add("tpahere");
		territoryEnemyDenyCommands.add("tpaccept");
		territoryEnemyDenyCommands.add("tpa");
		territoryEnemyDenyCommands.add("warp");

		materialsContainer.add(Material.DISPENSER);
		materialsContainer.add(Material.CHEST);
		materialsContainer.add(Material.FURNACE);
		materialsContainer.add(Material.BURNING_FURNACE);
		materialsContainer.add(Material.JUKEBOX);
		materialsContainer.add(Material.BREWING_STAND);
		materialsContainer.add(Material.ENCHANTMENT_TABLE);
		materialsContainer.add(Material.ANVIL);
		materialsContainer.add(Material.BEACON);
		materialsContainer.add(Material.TRAPPED_CHEST);
		materialsContainer.add(Material.DROPPER);
		materialsContainer.add(Material.HOPPER);
		
		materialsEditOnInteract.add(Material.DIODE_BLOCK_OFF);
		materialsEditOnInteract.add(Material.DIODE_BLOCK_ON);
		materialsEditOnInteract.add(Material.NOTE_BLOCK);
		materialsEditOnInteract.add(Material.CAULDRON);
		materialsEditOnInteract.add(Material.SOIL);

		materialsDoor.add(Material.WOODEN_DOOR);
		materialsDoor.add(Material.TRAP_DOOR);
		materialsDoor.add(Material.FENCE_GATE);
		
		materialsEditTools.add(Material.FIREBALL);
		materialsEditTools.add(Material.FLINT_AND_STEEL);
		materialsEditTools.add(Material.BUCKET);
		materialsEditTools.add(Material.WATER_BUCKET);
		materialsEditTools.add(Material.LAVA_BUCKET);

		monsters.add(EntityType.BLAZE);
		monsters.add(EntityType.CAVE_SPIDER);
		monsters.add(EntityType.CREEPER);
		monsters.add(EntityType.ENDERMAN);
		monsters.add(EntityType.ENDER_DRAGON);
		monsters.add(EntityType.GHAST);
		monsters.add(EntityType.GIANT);
		monsters.add(EntityType.MAGMA_CUBE);
		monsters.add(EntityType.PIG_ZOMBIE);
		monsters.add(EntityType.SILVERFISH);
		monsters.add(EntityType.SKELETON);
		monsters.add(EntityType.SLIME);
		monsters.add(EntityType.SPIDER);
		monsters.add(EntityType.WITCH);
		monsters.add(EntityType.WITHER);
		monsters.add(EntityType.ZOMBIE);
		
		spoutHealthBarColorUnderQuota.put(1.0d, "&2");
        spoutHealthBarColorUnderQuota.put(0.8d, "&a");
        spoutHealthBarColorUnderQuota.put(0.5d, "&e");
        spoutHealthBarColorUnderQuota.put(0.4d, "&6");
        spoutHealthBarColorUnderQuota.put(0.3d, "&c");
        spoutHealthBarColorUnderQuota.put(0.2d, "&4");
	}

	private static transient Conf i = new Conf();
	public static void load()
	{
		Main.main.persist.loadOrSaveDefault(i, Conf.class, "conf");
	}
	public static void save()
	{
		Main.main.persist.save(i);
	}
}

