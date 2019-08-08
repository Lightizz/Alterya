package fr.alterya.core.utils;

public class NumberUtil {

	public static String removeNoneDigits(String string) {
		
		return string.replaceAll("\\D+","");
		
	}
	
}
