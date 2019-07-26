package fr.alterya.rank;

public enum RankList {

	JOUEUR(0, 1, "§7", "", " > "),
	DEVELOPPEUR(1, 10, "§5[DEVELOPPEUR]", "", " > "),
	ARCHITECTE(2, 10, "§5[ARCHITECTE]", "", " > "),
	GUIDE(3, 20, "§a§l[GUIDE]", "", " §b> "),
	MODERATEUR(4, 40, "§e§l[MODERATEUR]", "",  " §b> "),
	MODERATEUR_PLUS(5, 60, "§6§l[Modérateur+]", "", " §b> §6"),
	RESPONSABLE(6, 80, "§4§l[Responsable]", "", " §b> §4"),
	ADMINISTRATEUR(7, 100, "§4§l[Admin] ", "", " §4§l>> §4");
	
	//Fields
	private final int power, id;
	private final String prefix, suffix, chatSeparator;
	
	//Constructor
	private RankList(int id, int power, String prefix, String suffix, String chatSeparator) {
		this.id = id;
		this.power = power;
		this.prefix = prefix;
		this.suffix = suffix;
		this.chatSeparator = chatSeparator;
	}
	
	//Method Getter
	public final int GetId() {
		return id;
	}
	
	public final int getPower() {
		return power;
		
	}
	public final String getPrefix() {
		return prefix;	
	}
	public final String getSuffix() {
		return suffix;	
	}
	
	public final String getName() {
		return this.toString();
	}

	public final String getChatSeparator() {
		return chatSeparator;
	}
}

