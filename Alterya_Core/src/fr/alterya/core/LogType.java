package fr.alterya.core;

public enum LogType
{
	EMERGENCY("§4Urgence", 4),
	ERROR("Erreur", 3),
	WARNING("Attention", 2),
	INFO("Info", 1),
	;
	
	private final String string;
	private final int level;
	
	private LogType(String string, int level) {
		this.string = string;
		this.level = level;
	}
	
	public String string() {
		return string;
	}
	
	public int level() {
		return level;
	}
}
