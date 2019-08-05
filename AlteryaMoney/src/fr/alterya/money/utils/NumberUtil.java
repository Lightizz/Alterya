package fr.alterya.money.utils;

public class NumberUtil {

	public static String removeNoneDigits(String string) {
		
		return string.replaceAll("\\D+","");
		
	}
	
}
