package fr.alterya.money.utils;

import java.util.concurrent.TimeUnit;

public class TimeUtil {

	public static long getTimeSeconds() {
		
		long timeMillis = System.currentTimeMillis();
		
		return TimeUnit.MILLISECONDS.toSeconds(timeMillis);
		
	}
	
}
