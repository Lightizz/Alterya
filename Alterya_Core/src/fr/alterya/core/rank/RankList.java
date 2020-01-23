package fr.alterya.core.rank;

/*
Resp of the rank / perms : Lightiz
*/

public enum RankList {
	
	/*
	§4 = "dark_red"
	§c = "red"
	§6 = "gold"
	§e = "yellow"
	§2 = "dark_green"
	§a = "green"
	§b = "aqua"
	§3 = "dark_aqua"
	§1 = "dark_blue"
	§9 = "blue"
	§d = "light_purple"
	§5 = "dark_purple"
	§f = "white"
	§7 = "gray"
	§8 = "dark_gray"
	§0 = "black"
	*/
	JOUEUR(0, 1, "Joueur", "", "", " > ", 5),
	SOUVENIR(1, 3, "Souvenir", "§a[Souvenir] ", "§a", " > ", 3),
	MEMOIRE(2, 5, "Mémoire","§1[Mémoire] ", "§1", " > ", 2),
	SAGE(3, 10, "Sage", "§d[Sage] ", "§d", " > ", 0),
	DEVELOPPEUR(4, 15, "Développeur", "§5[Développeur] ", "§5", " > ", 3),
	REDACTOR(11, 15, "Rédacteur", "§5[Rédacteur]", "§5", " > ", 3),
	GFX(12, 15, "Graphiste", "§5[Graphiste]", "§5", " > ", 3),
	ARCHITECTE(5, 15, "Achitecte","§5[Architecte] ", "§5", " > ", 3),
	GUIDE(6, 20, "Guide", "§a[Guide] ", "§a", " > ", 3),
	MODERATEUR(7, 40, "Modérateur","§e[Modérateur] ", "§e", " > ", 2),
	MODERATEUR_PLUS(8, 60, "Modérateur+", "§6[Modérateur+] ", "§6", " > §6", 0),
	RESPONSABLE(9, 80, "Responsable", "§4§l[Responsable] ", "§4", " > §4", 0),
	ADMINISTRATEUR(10, 100, "Administateur", "§4§l[Administrateur] ", "§4", " >> §4", 0);
	
	//Fields
	private final int power, id, tpcountdown;
	private final String rankName, prefix, chatSeparator, prefixColor;
	
	//Constructor
	private RankList(int id, int power, String rankName, String prefix, String prefixColor, String chatSeparator, int tpcountdown) {
		this.id = id;
		this.power = power;
		this.rankName = rankName;
		this.prefix = prefix;
		this.chatSeparator = chatSeparator;
		this.prefixColor = prefixColor;
		this.tpcountdown = tpcountdown;
	}
	
	//Method Getter
	public final String getRankName() {
		return rankName;
	}
	public final int GetId() {
		return id;
	}
	public final int getPower() {
		return power;
	}
	public final String getPrefix() {
		return prefix;	
	}
	public final String getName() {
		return this.toString();
	}
	public final String getChatSeparator() {
		return chatSeparator;
	}
	public final String getPrefixColor() {
		return prefixColor;
	}
	public final int getTpCountdown() {
		return tpcountdown;
	}
}

