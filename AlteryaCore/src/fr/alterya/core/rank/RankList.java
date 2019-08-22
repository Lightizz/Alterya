package fr.alterya.core.rank;

/*
Resp of the rank / perms : Lightiz
*/

public enum RankList {
	
	/*
	�4 = "dark_red"
	�c = "red"
	�6 = "gold"
	�e = "yellow"
	�2 = "dark_green"
	�a = "green"
	�b = "aqua"
	�3 = "dark_aqua"
	�1 = "dark_blue"
	�9 = "blue"
	�d = "light_purple"
	�5 = "dark_purple"
	�f = "white"
	�7 = "gray"
	�8 = "dark_gray"
	�0 = "black"
	*/
	JOUEUR(0, 1, "Joueur", "", "", " > "),
	SOUVENIR(1, 3, "Souvenir", "�a[Souvenir] ", "�a", " > "),
	MEMOIRE(2, 5, "M�moire","�1[M�moire] ", "�1", " > "),
	SAGE(3, 10, "Sage", "�d[Sage] ", "�d", " > "),
	DEVELOPPEUR(4, 15, "D�veloppeur", "�5[D�veloppeur] ", "�5", " > "),
	ARCHITECTE(5, 15, "Achitecte","�5[Architecte] ", "�5", " > "),
	GUIDE(6, 20, "Guide", "�a[Guide] ", "�a", " > "),
	MODERATEUR(7, 40, "Mod�rateur","�e[Mod�rateur] ", "�e", " > "),
	MODERATEUR_PLUS(8, 60, "Mod�rateur+", "�6[Mod�rateur+] ", "�6", " > �6"),
	RESPONSABLE(9, 80, "Responsable", "�4�l[Responsable] ", "�4", " > �4"),
	ADMINISTRATEUR(10, 100, "Administateur", "�4�l[Administrateur] ", "�4", " >> �4");
	
	//Fields
	private final int power, id;
	private final String rankName, prefix, chatSeparator, prefixColor;
	
	//Constructor
	private RankList(int id, int power, String rankName, String prefix, String prefixColor, String chatSeparator) {
		this.id = id;
		this.power = power;
		this.rankName = rankName;
		this.prefix = prefix;
		this.chatSeparator = chatSeparator;
		this.prefixColor = prefixColor;
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
}

