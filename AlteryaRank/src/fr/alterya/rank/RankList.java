package fr.alterya.rank;

import org.bukkit.entity.Player;

/*
Resp of the rank plugin: Lightiz
*/

public enum RankList {
	
	JOUEUR(0, 1, "", "", " > "),
	SOUVENIR(1, 3, "§a[Souvenir]", "", " > "),
	MEMOIRE(2, 5, "§1[Mémoire]", "", " > "),
	SAGE(3, 10, "§d[Sage]", "", " > "),
	DEVELOPPEUR(4, 15, "§5[Développeur]", "", " > "),
	ARCHITECTE(5, 15, "§5[Architecte]", "", " > "),
	GUIDE(6, 20, "§a§l[Guide]", "", " §b> "),
	MODERATEUR(7, 40, "§e§l[Modérateur]", "",  " §b> "),
	MODERATEUR_PLUS(8, 60, "§6§l[Modérateur+]", "", " §b> §6"),
	RESPONSABLE(9, 80, "§4§l[Responsable]", "", " §b> §4"),
	ADMINISTRATEUR(10, 100, "§4§l[Administrateur] ", "", " §4§l>> §4");
	
	//Fields
	private final int power, id;
	private final String prefix, suffix, chatSeparator;
	
	Player player;
	
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

