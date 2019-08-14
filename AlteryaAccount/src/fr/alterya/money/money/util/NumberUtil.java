package fr.alterya.money.money.util;

public class NumberUtil {

	public static String removeNoneDigits(String string) {
		
		return string.replaceAll("\\D+","");
		
	}
	
}
