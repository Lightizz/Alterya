package fr.alterya.core.rank;

/*
Resp of the rank plugin: Lightiz
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
	JOUEUR(0, 1, "Joueur", "", " > "),
	SOUVENIR(1, 3, "Souvenir", "§a[Souvenir] ", " > "),
	MEMOIRE(2, 5, "Mémoire","§1[Mémoire] ", " > "),
	SAGE(3, 10, "Sage", "§d[Sage] ", " > "),
	DEVELOPPEUR(4, 15, "Développeur", "§5[Développeur] ", " > "),
	ARCHITECTE(5, 15, "Achitecte","§5[Architecte] ", " > "),
	GUIDE(6, 20, "Guide", "§a[Guide] ", " > "),
	MODERATEUR(7, 40, "Modérateur","§e[Modérateur] ", " > "),
	MODERATEUR_PLUS(8, 60, "Modérateur+", "§6[Modérateur+] ", " > §6"),
	RESPONSABLE(9, 80, "Responsable", "§4§l[Responsable] ", " > §4"),
	ADMINISTRATEUR(10, 100, "Administateur", "§4§l[Administrateur] ", " >> §4");
	
	//Fields
	private final int power, id;
	private final String rankName, prefix, chatSeparator;
	
	//Constructor
	private RankList(int id, int power, String rankName, String prefix, String chatSeparator) {
		this.id = id;
		this.power = power;
		this.rankName = rankName;
		this.prefix = prefix;
		this.chatSeparator = chatSeparator;
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
}

